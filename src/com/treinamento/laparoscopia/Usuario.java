// Usuario.java
package com.treinamento.laparoscopia.models;

public class Usuario {
    private String nome;
    private String tipo; // "estudante" ou "professor"

    public Usuario(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nome;
    }

}
