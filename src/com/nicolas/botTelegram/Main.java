package com.nicolas.botTelegram;

import com.nicolas.botTelegram.botOrchestrator.BotOrchestrator;
import com.nicolas.botTelegram.model.Noticia;
import com.nicolas.botTelegram.service.NewsService;
import com.nicolas.botTelegram.service.TelegramService;
import com.nicolas.botTelegram.service.TranslationService;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // 1. Instanciando os serviços independentes
        NewsService newsService = new NewsService();
        TelegramService telegramService = new TelegramService();
        TranslationService translationService = new TranslationService();
        BotOrchestrator botOrchestrator = new BotOrchestrator(newsService, telegramService, translationService);

        botOrchestrator.executarCiclo("Brazil");


    }
}