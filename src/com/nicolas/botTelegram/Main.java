package com.nicolas.botTelegram;

import com.nicolas.botTelegram.model.Noticia;
import com.nicolas.botTelegram.service.NewsService;
import com.nicolas.botTelegram.service.TelegramService;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // 1. Instanciando os serviços independentes
        NewsService newsService = new NewsService();
        TelegramService telegramService = new TelegramService();

        // 2. Acionando a busca (retorna o ArrayList mockado em inglês)
        ArrayList<Noticia> listaDeNoticias = newsService.buscarNoticias("war");

        // 3. Laço de repetição percorrendo cada notícia recebida
        for (Noticia noticiaAtual : listaDeNoticias) {
            telegramService.EnviarNoticia(noticiaAtual);
        }

    }
}