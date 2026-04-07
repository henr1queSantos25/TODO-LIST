package com.aczg.todolist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Bem-vindo ao Todo List!");

        while (true) {
            System.out.println("\n-----------------------------------");
            System.out.println("       MENU PRINCIPAL");
            System.out.println("-----------------------------------");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Todas");
            System.out.println("3. Listar por Categoria");
            System.out.println("4. Listar por Prioridade");
            System.out.println("5. Listar por Status");
            System.out.println("6. Listar por Data de Término");
            System.out.println("7. Atualizar Status (Mover Card)");
            System.out.println("8. Editar Tarefa Completa");
            System.out.println("9. Ver Estatísticas (Dashboard)");
            System.out.println("10. Deletar Tarefa");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao;
            try {
                String entrada = scanner.nextLine();
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite apenas números.");
                continue; // Volta para o início do while
            }

            if (opcao == 0) {
                System.out.println("Saindo do sistema... Até logo!");
                break;
            }

            try {
                switch (opcao) {
                    case 1: // ADICIONAR
                        System.out.println("\n--- Nova Tarefa ---");
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("Descrição: ");
                        String desc = scanner.nextLine();

                        System.out.print("Data de Término (dd/MM/yyyy): ");
                        LocalDate data = LocalDate.parse(scanner.nextLine(), formatter);

                        System.out.print("Prioridade (1 a 5): ");
                        int prioridade = Integer.parseInt(scanner.nextLine());

                        System.out.print("Categoria (ex: Trabalho, Pessoal): ");
                        String cat = scanner.nextLine();

                        Tarefa novaTarefa = new Tarefa(nome, desc, data, prioridade, cat, Status.TODO);
                        gerenciador.adicionarTarefa(novaTarefa);
                        break;

                    case 2: // LISTAR TODAS
                        gerenciador.listarTodas();
                        break;

                    case 3: // LISTAR POR CATEGORIA
                        System.out.print("Digite a categoria para filtrar: ");
                        String catBusca = scanner.nextLine();
                        gerenciador.listarPorCategoria(catBusca);
                        break;

                    case 4: // LISTAR POR PRIORIDADE
                        System.out.print("Digite o nível de prioridade (1-5): ");
                        int pBusca = Integer.parseInt(scanner.nextLine());
                        gerenciador.listarPorPrioridade(pBusca);
                        break;

                    case 5: // LISTAR POR STATUS
                        System.out.println("Filtrar por qual status?");
                        System.out.println("1- TODO (A Fazer) | 2- DOING (Fazendo) | 3- DONE (Feito)");
                        int stBusca = Integer.parseInt(scanner.nextLine());
                        Status statusFiltro = (stBusca == 1) ? Status.TODO : (stBusca == 2) ? Status.DOING : Status.DONE;
                        gerenciador.listarPorStatus(statusFiltro);
                        break;

                    case 6: // LISTAR POR DATA
                        System.out.print("Digite a data para filtrar (dd/MM/yyyy): ");
                        LocalDate dataFiltro = LocalDate.parse(scanner.nextLine(), formatter);
                        gerenciador.listarPorData(dataFiltro);
                        break;

                    case 7: // ATUALIZAR STATUS
                        System.out.print("Digite o ID da tarefa para mover: ");
                        int idStatus = Integer.parseInt(scanner.nextLine());

                        System.out.println("Novo status: 1- TODO | 2- DOING | 3- DONE");
                        int novoStVal = Integer.parseInt(scanner.nextLine());
                        Status novoStatus = (novoStVal == 1) ? Status.TODO : (novoStVal == 2) ? Status.DOING : Status.DONE;

                        gerenciador.atualizarStatus(idStatus, novoStatus);
                        break;

                    case 8: // EDITAR COMPLETA
                        System.out.print("Digite o ID da tarefa para editar: ");
                        int idEdit = Integer.parseInt(scanner.nextLine());

                        // Verifica se existe antes de pedir os dados
                        Tarefa tAntiga = gerenciador.getTarefa(idEdit);

                        if (tAntiga != null) {
                            System.out.println("Editando tarefa: " + tAntiga.getNome());
                            System.out.println("(Digite os novos dados abaixo)");

                            System.out.print("Novo Nome: ");
                            String nNome = scanner.nextLine();

                            System.out.print("Nova Descrição: ");
                            String nDesc = scanner.nextLine();

                            System.out.print("Nova Data (dd/MM/yyyy): ");
                            LocalDate nData = LocalDate.parse(scanner.nextLine(), formatter);

                            System.out.print("Nova Prioridade (1-5): ");
                            int nPrio = Integer.parseInt(scanner.nextLine());

                            System.out.print("Nova Categoria: ");
                            String nCat = scanner.nextLine();

                            gerenciador.editarTarefa(idEdit, nNome, nDesc, nData, nPrio, nCat);
                        } else {
                            System.out.println("Erro: ID não encontrado.");
                        }
                        break;

                    case 9: // ESTATÍSTICAS
                        gerenciador.mostrarEstatisticas();
                        break;

                    case 10: // DELETAR
                        System.out.print("Digite o ID da tarefa para excluir: ");
                        int idDel = Integer.parseInt(scanner.nextLine());
                        gerenciador.deletarTarefa(idDel);
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }

            } catch (DateTimeParseException e) {
                System.out.println("Erro: Formato de data inválido! Use dd/MM/yyyy (ex: 30/12/2024)");
            } catch (NumberFormatException e) {
                System.out.println("Erro: Você digitou um texto onde deveria ser um número.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }
}