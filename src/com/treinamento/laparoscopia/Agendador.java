// Agendador.java
package com.treinamento.laparoscopia.utils;

import com.treinamento.laparoscopia.models.SessaoTreinamento;
import com.treinamento.laparoscopia.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Agendador {
    private List<SessaoTreinamento> sessoes;

    public Agendador() {
        this.sessoes = new ArrayList<>();
    }

    public void agendarSessao(SessaoTreinamento sessao) {
        sessoes.add(sessao);
        System.out.println("Sessão agendada para " + sessao.getData());
    }
}
