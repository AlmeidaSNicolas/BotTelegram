package com.nicolas.botTelegram.service;

import com.nicolas.botTelegram.model.Noticia;

public class TelegramService {


    public void EnviarNoticia(Noticia noticia){
        System.out.println("----------------------");
        System.out.println("Recebendo dados da noticia");
        System.out.println("Titulo : " + noticia.getTitulo());
        System.out.println("Resumo : " + noticia.getResumo());
        System.out.println("URL : " + noticia.getUrl());
    }

}
