package com.nicolas.botTelegram.service;

import com.nicolas.botTelegram.model.Noticia;

public class TranslationService {

    public Noticia traduzirNoticia(Noticia noticia) {
        String tituloTraduzido = "[PT] " + noticia.getTitulo();
        String resumoTraduzido = "[PT] " + noticia.getResumo();

        return new Noticia(tituloTraduzido, resumoTraduzido, noticia.getUrl());
    }

}
