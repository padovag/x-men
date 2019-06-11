package com.example.xmen;

class Mutante {
    public String nome;
    public String habilidades;
    public String foto;
    public int usuario_id;

    public Mutante(String nome, String habilidades, String foto, int usuario_id) {
        this.nome = nome;
        this.habilidades = habilidades;
        this.foto = foto;
        this.usuario_id = usuario_id;
    }

    public String toString() {
        return nome + "\n" + habilidades;
    }
}
