package com.nicolas.botTelegram.service;

import com.nicolas.botTelegram.model.Noticia;

import java.util.ArrayList;

public class NewsService {
    public ArrayList<Noticia> buscarNoticias(String keyworld){
        //TODO: Filtrar por keyword quando utilizar API real
        ArrayList<Noticia> noticias = new ArrayList<>();

        noticias.add(new Noticia("Ocean War in CUBA", "Cuba attack USA SHIPS", "thefakepage?"));
        noticias.add(new Noticia("What do USA?", "USA ATTACK SPAIN", "THEREALPAGE"));

        return noticias;
    };


}
