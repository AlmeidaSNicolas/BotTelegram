package com.nicolas.botTelegram.model;

public class ArticleDTO {
    private String title;
    private String description;
    private String url;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Noticia converterParaNoticia(){
        return new Noticia(this.title, this.description, this.url);
    }

}
