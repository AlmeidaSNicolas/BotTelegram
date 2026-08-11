package com.nicolas.botTelegram.model;

public class Categoria extends Noticia {
    private String categoria;

    public Categoria(String titulo, String resumo, String url, String categoria) {
        super(titulo, resumo, url);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }
}
