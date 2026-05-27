package com.nicolas.botTelegram.model;

public class Noticia {
    private String titulo;
    private String resumo;
    private String url;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setResumo(String resumo){
        this.resumo = resumo;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getResumo(){
        return this.resumo;
    }

    public String getUrl(){
        return this.url;
    }

}
