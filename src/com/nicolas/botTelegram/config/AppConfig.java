package com.nicolas.botTelegram.config;

public class AppConfig {
    public static final String TELEGRAM_BOT_TOKEN = System.getenv("TELEGRAM_BOT_TOKEN");
    public static final String TELEGRAM_CHAT_ID = System.getenv("TELEGRAM_CHAT_ID");
    public static final String NEWS_API_KEY= System.getenv("NEWS_API_KEY");
    public static final String DEEPL_API_KEY= System.getenv("DEEPl_API_KEY");
    public static final String DB_URL= System.getenv("DB_URL");
    public static final String DB_USER= System.getenv("DB_USER");
    public static final String DB_PASSWORD= System.getenv("DB_PASSWORD");
}
