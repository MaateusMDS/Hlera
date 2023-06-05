package com.hlera.model.campanha;

public enum Tipo {

    ALIMENTO(0, "Alimento"), ROUPA(1, "Roupa"), OUTRO(2, "OUTRO");

    private int id;
    private String nome;

    Tipo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public Tipo setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Tipo setNome(String nome) {
        this.nome = nome;
        return this;
    }
}
