package com.nicolas.botTelegram.config;

import java.sql.DriverManager;
import java.sql.Connection;

public class DataBaseConfig {

    public static Connection obterConexao()throws Exception{
        return DriverManager.getConnection(
                AppConfig.DB_URL,
                AppConfig.DB_USER,
                AppConfig.DB_PASSWORD
        );

    }

}