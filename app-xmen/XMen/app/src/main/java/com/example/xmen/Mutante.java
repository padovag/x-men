package com.example.xmen;

class Mutante {
    public String nome;
    public String habilidade;
    public String foto;
    public int usuario_id;

    public Mutante() {

    }

    public Mutante(String nome, String habilidade, String foto, int usuario_id) {
        this.nome = nome;
        this.habilidade = habilidade;
        this.foto = foto;
        this.usuario_id = usuario_id;
    }

    public String toString() {
        return nome + "\n" + habilidade;
    }
}
