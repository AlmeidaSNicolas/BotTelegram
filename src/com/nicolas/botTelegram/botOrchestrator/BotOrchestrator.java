package com.nicolas.botTelegram.botOrchestrator;

import com.nicolas.botTelegram.model.Noticia;
import com.nicolas.botTelegram.repository.NoticiaRepository;
import com.nicolas.botTelegram.service.NewsService;
import com.nicolas.botTelegram.service.TelegramService;
import com.nicolas.botTelegram.service.TranslationService;

import java.util.ArrayList;

public class BotOrchestrator {
    private final NewsService newsService;
    private final TelegramService telegramService;
    private final TranslationService translationService;
    private final NoticiaRepository noticiaRepository;

    public BotOrchestrator(NewsService newsService, TelegramService telegramService, TranslationService translationService,  NoticiaRepository noticiaRepository) {
        this.newsService = newsService;
        this.telegramService = telegramService;
        this.translationService = translationService;
        this.noticiaRepository = noticiaRepository;
    }

    public void executarCiclo(String keyword){
        try {
            ArrayList<Noticia> noticias;
            noticias = newsService.buscarNoticias(keyword);

            for(Noticia noticia : noticias){
                if(noticiaRepository.jaExiste(noticia.getUrl())){
                    System.out.println(noticia.getTitulo());
                    System.out.println(noticia.getUrl());
                }else {
                    Noticia noticiaTraduzida = translationService.traduzirNoticia(noticia);
                    telegramService.enviarNoticia(noticiaTraduzida);
                    noticiaRepository.salvar(noticiaTraduzida);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro inesperado no ciclo do BOT");
            e.printStackTrace();
        }
    }
}
