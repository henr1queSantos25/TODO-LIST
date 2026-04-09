package com.aczg.todolist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Tarefa implements Comparable<Tarefa> {
    private String nome;
    private String descricao;
    private LocalDateTime dataHoraTermino;
    private int prioridade; // 1 a 5
    private String categoria;
    private Status status;
    private List<Alarme> alarmes;

    public Tarefa(String nome, String descricao, LocalDateTime dataHoraTermino, int prioridade, String categoria, Status status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataHoraTermino = dataHoraTermino;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
        this.alarmes = new ArrayList<>();
    }

    public void adicionarAlarme(int minutosAntecedencia) {
        this.alarmes.add(new Alarme(minutosAntecedencia, this.dataHoraTermino));
    }

    // Getters
    public List<Alarme> getAlarmes() {
        return alarmes;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataHoraTermino() {
        return dataHoraTermino;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public Status getStatus() {
        return status;
    }

    public String getNome() {
        return nome;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataHoraTermino(LocalDateTime dataHoraTermino) {
        this.dataHoraTermino = dataHoraTermino;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Prioridade: %d | [%s] %s - %s (Data/Hora: %s, Categoria: %s, Alarmes: %d)",
                prioridade, status, nome, descricao, dataHoraTermino.format(formatter), categoria, alarmes.size());
    }

    @Override
    public int compareTo(Tarefa outra) {
        return Integer.compare(this.prioridade, outra.prioridade);
    }
}