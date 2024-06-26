// Relatorio.java
package com.treinamento.laparoscopia.utils;

import com.treinamento.laparoscopia.models.SessaoTreinamento;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Relatorio {
    public void gerarRelatorio(List<SessaoTreinamento> sessoes) {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(
                Files.newOutputStream(Paths.get("relatorio_sessoes.csv")), StandardCharsets.UTF_8))) {
            // Cabeçalho do CSV usando ponto e vírgula como separador
            out.println("Usuario;Data;Feedback");

            // Dados do CSV
            for (SessaoTreinamento sessao : sessoes) {
                String nome = escapeCsv(sessao.getUsuario().getNome());
                String data = escapeCsv(sessao.getData());
                String feedback = escapeCsv(sessao.getFeedback());

                out.printf("\"%s\";\"%s\";\"%s\"%n", nome, data, feedback);
            }
            System.out.println("Relatório gerado com sucesso em 'relatorio_sessoes.csv'.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o relatório: " + e.getMessage());
        }
    }

    // Método auxiliar para escapar caracteres especiais em strings para CSV
    private String escapeCsv(String input) {
        if (input == null) {
            return "";
        }
        // Escapa aspas duplas e encapsula o campo em aspas se necessário
        return input.contains(";") || input.contains("\"") || input.contains("\n")
                ? "\"" + input.replace("\"", "\"\"") + "\""
                : input;
    }
}
