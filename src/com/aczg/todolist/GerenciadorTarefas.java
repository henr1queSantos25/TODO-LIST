package com.aczg.todolist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorTarefas {
    private List<Tarefa> tarefas = new ArrayList<>();

    public Tarefa getTarefa(int id) {
        if (id >= 0 && id < tarefas.size()) {
            return tarefas.get(id);
        }
        return null;
    }

    // CREATE
    public void adicionarTarefa(Tarefa t) {
        tarefas.add(t);

        Collections.sort(tarefas);
        System.out.println("com.aczg.todolist.Tarefa adicionada e lista reordenada por prioridade!");
    }

    // READ (Listar Todas)
    public void listarTodas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        for (int i = 0; i < tarefas.size(); i++) {
            System.out.println("ID: " + i + " - " + tarefas.get(i));
        }
    }

    // UPDATE
    public void editarTarefa(int id, String novoNome, String novaDesc, LocalDate novaData, int novaPrioridade, String novaCat) {
        if (id >= 0 && id < tarefas.size()) {
            Tarefa t = tarefas.get(id);
            t.setNome(novoNome);
            t.setDescricao(novaDesc);
            t.setDataTermino(novaData);
            t.setPrioridade(novaPrioridade);
            t.setCategoria(novaCat);

            Collections.sort(tarefas);

            System.out.println("Tarefa atualizada e lista reordenada com sucesso!");
        } else {
            System.out.println("ID inválido.");
        }
    }

    // DELETE
    public void deletarTarefa(int indice) {
        if (indice >= 0 && indice < tarefas.size()) {
            tarefas.remove(indice);
            System.out.println("com.aczg.todolist.Tarefa removida com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    // Listar por Categoria
    public void listarPorCategoria(String categoria) {
        List<Tarefa> filtradas = tarefas.stream()
                .filter(t -> t.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());

        if(filtradas.isEmpty()) System.out.println("Nenhuma tarefa nesta categoria.");
        else filtradas.forEach(System.out::println);
    }

    // Listar por com.aczg.todolist.Status
    public void listarPorStatus(Status status) {
        tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .forEach(System.out::println);
    }

    // Listar Por Prioridade
    public void listarPorPrioridade(int prioridadeAlvo) {
        System.out.println("\n--- Tarefas com Prioridade " + prioridadeAlvo + " ---");
        boolean encontrou = false;

        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa t = tarefas.get(i);
            if (t.getPrioridade() == prioridadeAlvo) {
                System.out.println("ID: " + i + " - " + t);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma tarefa encontrada com esta prioridade.");
        }
    }

    // Atualizar Status
    public void atualizarStatus(int id, Status novoStatus) {
        if (id >= 0 && id < tarefas.size()) {
            Tarefa tarefa = tarefas.get(id);
            tarefa.setStatus(novoStatus);
            System.out.println("com.aczg.todolist.Status da tarefa '" + tarefa.getNome() + "' atualizado para: " + novoStatus);
        } else {
            System.out.println("Erro: Índice da tarefa não encontrado!");
        }
    }
}