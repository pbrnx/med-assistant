// SessaoTreinamento.java
package com.treinamento.laparoscopia.models;

public class SessaoTreinamento {
    private Usuario usuario;
    private Procedimento procedimento;
    private String data;
    private String feedback;

    public SessaoTreinamento(Usuario usuario, Procedimento procedimento, String data) {
        this.usuario = usuario;
        this.procedimento = procedimento;
        this.data = data;
        this.feedback = "";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public String getData() {
        return data;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", usuario.getNome(), procedimento.getNome(), data);
    }
}
