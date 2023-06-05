package com.hlera.model.familia;

public enum Grupo {

    DOADOR(0, "DOADOR"), BENEFICIARIO(1, "BENEFICIARIO");

    private int id;
    private String nome;

    Grupo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public Grupo setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Grupo setNome(String nome) {
        this.nome = nome;
        return this;
    }
}
