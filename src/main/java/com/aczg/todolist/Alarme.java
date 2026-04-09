package com.aczg.todolist;

import java.time.LocalDateTime;

public class Alarme {
    private int antecedenciaMinutos;
    private LocalDateTime horarioDespertar;
    private boolean jaDisparado;

    public Alarme(int antecedenciaMinutos, LocalDateTime dataHoraTarefa) {
        this.antecedenciaMinutos = antecedenciaMinutos;
        this.horarioDespertar = dataHoraTarefa.minusMinutes(antecedenciaMinutos);
        this.jaDisparado = false;
    }

    public LocalDateTime getHorarioDespertar() {
        return horarioDespertar;
    }

    public boolean isJaDisparado() {
        return jaDisparado;
    }

    public void setJaDisparado(boolean jaDisparado) {
        this.jaDisparado = jaDisparado;
    }

    public int getAntecedenciaMinutos() {
        return antecedenciaMinutos;
    }
}