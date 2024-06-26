// Simulador.java
package com.treinamento.laparoscopia.core;

import com.treinamento.laparoscopia.models.Procedimento;

public class Simulador {
    public String simularProcedimento(Procedimento procedimento) {
        return "Simulação do procedimento " + procedimento.getNome() + " concluída com sucesso.";
    }
}
