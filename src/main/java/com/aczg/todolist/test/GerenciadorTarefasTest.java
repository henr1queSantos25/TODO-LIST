package com.aczg.todolist.test;

import com.aczg.todolist.GerenciadorTarefas;
import com.aczg.todolist.Status;
import com.aczg.todolist.Tarefa;

import java.time.LocalDateTime;

public class GerenciadorTarefasTest {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  INICIANDO BATERIA DE TESTES DO TODO LIST");
        System.out.println("=================================================\n");

        testeCriarTarefa();
        testeLerTarefa();
        testeAtualizarTarefa();
        testeDeletarTarefa();

        System.out.println("\n=================================================");
        System.out.println("  TODOS OS TESTES FORAM EXECUTADOS.");
        System.out.println("=================================================");
    }

    public static void testeCriarTarefa() {
        System.out.println("-- Teste de criação de tarefa (CREATE):");

        // GIVEN
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        LocalDateTime dataHora = LocalDateTime.of(2026, 12, 31, 15, 0); // Data e Hora
        Tarefa novaTarefa = new Tarefa("Estudar Testes", "Aprender TDD", dataHora, 1, "Estudos", Status.TODO);

        // WHEN
        gerenciador.adicionarTarefa(novaTarefa);
        Tarefa tarefaCriada = gerenciador.getTarefa(0);

        // THEN
        if (tarefaCriada != null && tarefaCriada.getNome().equals("Estudar Testes")) {
            System.out.println("   --- Criando >> Tarefa: " + tarefaCriada.getNome() + ", Data Término: " + tarefaCriada.getDataHoraTermino() + ", Status: " + tarefaCriada.getStatus() + " >> criada com sucesso!\n");
        } else {
            System.out.println("   --- FALHA: A tarefa não foi criada corretamente.\n");
        }
    }

    public static void testeLerTarefa() {
        System.out.println("-- Teste de leitura de tarefa (READ):");

        // GIVEN
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        Tarefa t1 = new Tarefa("Ler livro", "Ler 20 páginas", LocalDateTime.now(), 2, "Lazer", Status.TODO);
        gerenciador.adicionarTarefa(t1);

        // WHEN
        Tarefa tarefaLida = gerenciador.getTarefa(0);

        // THEN
        if (tarefaLida != null) {
            System.out.println("   --- Lendo >> Tarefa encontrada: " + tarefaLida.getNome() + " >> leitura realizada com sucesso!\n");
        } else {
            System.out.println("   --- FALHA: Não foi possível ler a tarefa.\n");
        }
    }

    public static void testeAtualizarTarefa() {
        System.out.println("-- Teste de atualização de tarefa (UPDATE):");

        // GIVEN
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        LocalDateTime dataHora = LocalDateTime.now();
        Tarefa tarefaOriginal = new Tarefa("Fazer compras", "Ir ao mercado", dataHora, 3, "Casa", Status.TODO);
        gerenciador.adicionarTarefa(tarefaOriginal);

        // WHEN
        gerenciador.editarTarefa(0, "Fazer compras do mês", "Ir ao atacadão", dataHora, 1, "Casa");
        Tarefa tarefaAtualizada = gerenciador.getTarefa(0);

        // THEN
        if (tarefaAtualizada.getNome().equals("Fazer compras do mês") && tarefaAtualizada.getPrioridade() == 1) {
            System.out.println("   --- Atualizando >> Tarefa agora se chama: " + tarefaAtualizada.getNome() + ", Nova Prioridade: " + tarefaAtualizada.getPrioridade() + " >> atualizada com sucesso!\n");
        } else {
            System.out.println("   --- FALHA: A tarefa não foi atualizada.\n");
        }
    }

    public static void testeDeletarTarefa() {
        System.out.println("-- Teste de exclusão de tarefa (DELETE):");

        // GIVEN
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        Tarefa t1 = new Tarefa("Pagar boletos", "Luz e Água", LocalDateTime.now(), 1, "Finanças", Status.TODO);
        gerenciador.adicionarTarefa(t1);

        // WHEN
        Tarefa tarefaAntes = gerenciador.getTarefa(0);
        gerenciador.deletarTarefa(0);
        Tarefa tarefaDepois = gerenciador.getTarefa(0);

        // THEN
        if (tarefaAntes != null && tarefaDepois == null) {
            System.out.println("   --- Deletando >> Tarefa removida. Busca pela tarefa retornou Vazio >> deletada com sucesso!\n");
        } else {
            System.out.println("   --- FALHA: A tarefa não foi deletada.\n");
        }
    }
}