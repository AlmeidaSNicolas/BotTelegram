package com.nicolas.botTelegram.service;

import com.google.gson.Gson;
import com.nicolas.botTelegram.config.AppConfig;
import com.nicolas.botTelegram.model.ArticleDTO;
import com.nicolas.botTelegram.model.NewsAPIResponseDTO;
import com.nicolas.botTelegram.model.Noticia;

import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsService {
    public ArrayList<Noticia> buscarNoticias(String keyword) throws Exception {

        String url = "https://newsapi.org/v2/everything?q=" + keyword +"&language=en&pageSize=5&apikey=" + AppConfig.NEWS_API_KEY;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        String json = response.body().string();
        Gson gson = new Gson();

        //Guardando retorno do metodo na variavel "Noticia"
        NewsAPIResponseDTO newsAPIResponseDTO = gson.fromJson(json, NewsAPIResponseDTO.class);

        ArrayList<Noticia> noticias = new ArrayList<>();

        for (ArticleDTO articleDTO : newsAPIResponseDTO.getArticles()) {
            Noticia noticia = articleDTO.converterParaNoticia();
            noticias.add(noticia);
        }

        return noticias;
    };


}
