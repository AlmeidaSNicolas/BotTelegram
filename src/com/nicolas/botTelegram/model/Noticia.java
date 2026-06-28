package com.nicolas.botTelegram.model;

public class Noticia {
    private String titulo;
    private String resumo;
    private String url;


    public Noticia(String titulo, String resumo, String url){
        this.titulo = titulo;
        this.resumo = resumo;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getUrl() {
        return url;
    }
}
