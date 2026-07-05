package com.nicolas.botTelegram.service;

import com.nicolas.botTelegram.config.AppConfig;
import com.nicolas.botTelegram.model.Noticia;

import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsService {
    public ArrayList<Noticia> buscarNoticias(String keyword) throws Exception {

        String url = "https://newsapi.org/v2/everything?q= " + keyword +"&language=en&pagesize=5&apikey=" + AppConfig.NEWS_API_KEY;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        String json = response.body().string();
        System.out.println(json);

        ArrayList<Noticia> noticias = new ArrayList<>();
        return noticias;

    };


}
