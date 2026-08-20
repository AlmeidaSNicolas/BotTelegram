package com.nicolas.botTelegram.config;

public class AppConfig {
    public static final String TELEGRAM_BOT_TOKEN = System.getenv("TELEGRAM_BOT_TOKEN");
    public static final String TELEGRAM_CHAT_ID = System.getenv("TELEGRAM_CHAT_ID");
    public static final String NEWS_API_KEY= System.getenv("NEWS_API_KEY");
    public static final String DEEPL_API_KEY= System.getenv("DEEPL_API_KEY");
    public static final String DB_URL= System.getenv("DB_URL");
    public static final String DB_USER= System.getenv("DB_USER");
    public static final String DB_PASSWORD= System.getenv("DB_PASSWORD");

    public static final String QUERY_GEOPOLITICA_CONFLITO =
            "(Iran OR \"United States\" OR Taiwan OR Brazil OR China OR Japan OR Colombia OR Russia OR Ukraine) AND (war OR military OR conflict OR nuclear OR navy OR sovereignty)";
    public static final String QUERY_GEOPOLITICA_ECONOMIA =
            "(Iran OR \"United States\" OR Taiwan OR Brazil OR China OR Japan OR Colombia OR Russia OR Ukraine) AND (economy OR sanctions OR semiconductors OR tariffs OR exports OR \"supply chain\")";

    public static final String DOMAINS = ("reuters.com,apnews.com,afp.com,bbc.com,aljazeera.com,dw.com,france24.com,foreignpolicy.com,foreignaffairs.com,thediplomat.com,csis.org,scmp.com,japantimes.co.jp,arctictoday.com,euronews.com,warontherocks.com");
}
