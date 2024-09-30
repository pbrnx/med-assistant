package com.treinamento.laparoscopia.models;

public class MaquinasSimulacao {
    private int id;
    private String nome;
    private String modelo;

    public MaquinasSimulacao() {}

    public MaquinasSimulacao(int id, String nome, String modelo) {
        this.id = id;
        this.nome = nome;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return nome + " - " + modelo; // Para exibição correta no JComboBox
    }
}
