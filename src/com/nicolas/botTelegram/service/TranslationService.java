package com.nicolas.botTelegram.service;

import com.deepl.api.DeepLClient;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.nicolas.botTelegram.config.AppConfig;
import com.nicolas.botTelegram.model.Noticia;

public class TranslationService {

    private DeepLClient deepLClient = new DeepLClient(AppConfig.DEEPL_API_KEY);


    public Noticia traduzirNoticia(Noticia noticia) {

        String tituloTraduzido =  "[EN]" + noticia.getTitulo();
        String resumoTraduzido = "[EN] " + noticia.getResumo();

        try{
            TextResult resultadoTitulo = deepLClient.translateText(noticia.getTitulo(), "en", "PT-BR");
            tituloTraduzido = resultadoTitulo.getText();

            TextResult resultadoResumo = deepLClient.translateText(noticia.getResumo(), "en", "PT-BR");
            resumoTraduzido = resultadoResumo.getText();

        }catch (DeepLException | InterruptedException e){
            System.out.println("Erro no DEEPL" + e.getMessage());
        }

        return new Noticia(tituloTraduzido, resumoTraduzido, noticia.getUrl());
    }

}
