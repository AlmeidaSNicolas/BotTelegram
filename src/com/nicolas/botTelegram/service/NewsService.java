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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class NewsService {

    public ArrayList<Noticia> buscarNoticias(String query) throws Exception{
        String url = montarUrl(query);

        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();
        String json = response.body().string();

        return parsearResposta(json);
    }

    private String montarUrl(String query) {
        String encodeQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = "https://newsapi.org/v2/everything?q="+ encodeQuery +"&language=en&pageSize=8&sortBy=relevancy&searchIn=title,description&domains=" + AppConfig.DOMAINS + "&apikey=" + AppConfig.NEWS_API_KEY;
        System.out.println(url);
        return url ;
    }

     private ArrayList<Noticia> parsearResposta(String json){
        Gson gson = new Gson();

        NewsAPIResponseDTO newsAPIResponseDTO = gson.fromJson(json, NewsAPIResponseDTO.class);

        ArrayList<Noticia> noticias = new ArrayList<>();

        for (ArticleDTO articleDTO : newsAPIResponseDTO.getArticles()) {
            Noticia noticia = articleDTO.converterParaNoticia();
            noticias.add(noticia);
        }

        return noticias;
    }
}
