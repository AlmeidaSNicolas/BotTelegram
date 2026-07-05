package com.nicolas.botTelegram.botOrchestrator;

import com.nicolas.botTelegram.model.Noticia;
import com.nicolas.botTelegram.service.NewsService;
import com.nicolas.botTelegram.service.TelegramService;
import com.nicolas.botTelegram.service.TranslationService;

import java.util.ArrayList;

public class BotOrchestrator {
    private NewsService newsService;
    private TelegramService telegramService;
    private TranslationService translationService;

    public BotOrchestrator(NewsService newsService, TelegramService telegramService, TranslationService translationService) {
        this.newsService = newsService;
        this.telegramService = telegramService;
        this.translationService = translationService;
    }

    public void executarCiclo(String keyword){
        ArrayList<Noticia> noticias = null;
        try {
            noticias = newsService.buscarNoticias(keyword);
        } catch (Exception e) {
            System.out.println("Erro no ciclo: " + e.getMessage());
        }

        for(Noticia noticia : noticias){
            Noticia noticiaTraduzida = translationService.traduzirNoticia(noticia);
            telegramService.enviarNoticia(noticiaTraduzida);
        }

    }
}
