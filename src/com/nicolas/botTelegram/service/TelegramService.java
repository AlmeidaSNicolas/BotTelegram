package com.nicolas.botTelegram.service;

import com.nicolas.botTelegram.config.AppConfig;
import com.nicolas.botTelegram.model.Noticia;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TelegramService {

    public void enviarNoticia(Noticia noticia, String categoria) throws IOException {

        String mensagemMontada = montarMensagem(noticia, categoria);

        String url = "https://api.telegram.org/bot" + AppConfig.TELEGRAM_BOT_TOKEN
                + "/sendMessage?chat_id=" + AppConfig.TELEGRAM_CHAT_ID
                + "&text=" + URLEncoder.encode(mensagemMontada, StandardCharsets.UTF_8)
                + "&parse_mode=HTML"
                + "&disable_web_page_preview=true";

        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();
    }


    private String montarMensagem(Noticia noticia, String categoria){
        return "[" + categoria + "]\n\n" +
                "<b>" + noticia.getTitulo() + "</b>\n\n" +
                noticia.getResumo() + "\n\n" +
                "<a href=\"" + noticia.getUrl() + "\">Ler Materia </a>";
    }

}
