package com.nicolas.botTelegram.botOrchestrator;

import com.nicolas.botTelegram.model.Noticia;
import com.nicolas.botTelegram.repository.NoticiaRepository;
import com.nicolas.botTelegram.service.NewsService;
import com.nicolas.botTelegram.service.TelegramService;
import com.nicolas.botTelegram.service.TranslationService;

import java.util.ArrayList;

public class BotOrchestrator {
    private NewsService newsService;
    private TelegramService telegramService;
    private TranslationService translationService;
    private NoticiaRepository noticiaRepository;

    public BotOrchestrator(NewsService newsService, TelegramService telegramService, TranslationService translationService,  NoticiaRepository noticiaRepository) {
        this.newsService = newsService;
        this.telegramService = telegramService;
        this.translationService = translationService;
        this.noticiaRepository = noticiaRepository;
    }

    public void executarCiclo(String keyword){
        ArrayList<Noticia> noticias = null;
        try {
            noticias = newsService.buscarNoticias(keyword);

            for(Noticia noticia : noticias){
                if(noticiaRepository.jaExiste(noticia.getUrl())){
                    continue;
                }else {
                    Noticia noticiaTraduzida = translationService.traduzirNoticia(noticia);
                    telegramService.enviarNoticia(noticiaTraduzida);
                    noticiaRepository.salvar(noticiaTraduzida);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro no ciclo: " + e.getMessage());
            System.out.println("Error");
        }


    }
}
