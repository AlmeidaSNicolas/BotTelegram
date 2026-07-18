package com.nicolas.botTelegram.repository;

import com.nicolas.botTelegram.model.Noticia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class NoticiaRepository {
    private Connection connection;

    public NoticiaRepository(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Noticia noticia) throws Exception{
        String sql = "INSERT INTO noticias_enviadas (titulo, url_original) VALUES (?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, noticia.getTitulo());
        stmt.setString(2, noticia.getUrl());
        stmt.executeUpdate();

    }

    public boolean jaExiste(String url) throws Exception{
        String sql = "SELECT COUNT(*) FROM noticias_enviadas WHERE url_original = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, url);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;

    }
}
