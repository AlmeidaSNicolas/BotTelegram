package com.nicolas.botTelegram;

import com.nicolas.botTelegram.botOrchestrator.BotOrchestrator;
import com.nicolas.botTelegram.config.DataBaseConfig;
import com.nicolas.botTelegram.model.Noticia;
import com.nicolas.botTelegram.repository.NoticiaRepository;
import com.nicolas.botTelegram.service.NewsService;
import com.nicolas.botTelegram.service.TelegramService;
import com.nicolas.botTelegram.service.TranslationService;

import java.sql.Connection;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws Exception {

        // 1. Instanciando os serviços independentes
        NewsService newsService = new NewsService();
        TelegramService telegramService = new TelegramService();
        TranslationService translationService = new TranslationService();
        Connection connection = DataBaseConfig.obterConexao();
        NoticiaRepository noticiaRepository = new NoticiaRepository(connection);

        BotOrchestrator botOrchestrator = new BotOrchestrator(newsService, telegramService, translationService, noticiaRepository);

        botOrchestrator.executarCiclo("China");


    }
}