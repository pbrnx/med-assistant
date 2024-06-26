// Avaliador.java
package com.treinamento.laparoscopia.utils;

import com.treinamento.laparoscopia.models.SessaoTreinamento;

public class Avaliador {
    public void avaliarSessao(SessaoTreinamento sessao, String feedback) {
        sessao.setFeedback(feedback);
        System.out.println("Feedback fornecido: " + feedback);
    }
}
