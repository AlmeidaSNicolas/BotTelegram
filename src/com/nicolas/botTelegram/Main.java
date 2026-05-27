package com.nicolas.botTelegram;

import com.nicolas.botTelegram.model.Noticia;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Noticia n1 = new Noticia();

        n1.setUrl("Buscando noticias na API & traduzindo %n");
        n1.setTitulo("Noticia encontrada : "+  "%nTensões entre China vem aumentando %n");
        n1.setResumo("Enviando para o telegram: %nTensoes se tornam mais abrangentes após suposto exercicio militar da china em volta da ilha de tawian, provincia originalmente da china que se dissipou do conjunto a 80 anos");


        System.out.printf(n1.getUrl() + n1.getTitulo() + n1.getResumo());
    }
}