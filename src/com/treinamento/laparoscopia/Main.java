// Main.java
package com.treinamento.laparoscopia;

import com.treinamento.laparoscopia.ui.InterfaceUsuario;

public class Main {
    public static void main(String[] args) {
        // Usando o método padrão para iniciar uma aplicação Swing
        javax.swing.SwingUtilities.invokeLater(() -> new InterfaceUsuario());
    }
}
