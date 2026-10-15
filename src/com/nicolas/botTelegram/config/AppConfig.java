package com.nicolas.botTelegram.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    // Variaveis do .env (raiz do projeto); variaveis de ambiente reais tem prioridade
    private static final Map<String, String> ENV_FILE = carregarEnvFile();

    public static final String TELEGRAM_BOT_TOKEN = obter("TELEGRAM_BOT_TOKEN");
    public static final String TELEGRAM_CHAT_ID = obter("TELEGRAM_CHAT_ID");
    public static final String NEWS_API_KEY= obter("NEWS_API_KEY");
    public static final String DEEPL_API_KEY= obter("DEEPL_API_KEY");
    public static final String DB_URL= obter("DB_URL");
    public static final String DB_USER= obter("DB_USER");
    public static final String DB_PASSWORD= obter("DB_PASSWORD");

    public static final String QUERY_GEOPOLITICA_CONFLITO =
            "(Iran OR \"United States\" OR Taiwan OR Brazil OR China OR Japan OR Colombia OR Russia OR Ukraine) AND (war OR military OR conflict OR nuclear OR navy OR sovereignty)";
    public static final String QUERY_GEOPOLITICA_ECONOMIA =
            "(Iran OR \"United States\" OR Taiwan OR Brazil OR China OR Japan OR Colombia OR Russia OR Ukraine) AND (economy OR sanctions OR semiconductors OR tariffs OR exports OR \"supply chain\")";

    public static final String DOMAINS = ("reuters.com,apnews.com,afp.com,bbc.com,aljazeera.com,dw.com,france24.com,foreignpolicy.com,foreignaffairs.com,thediplomat.com,csis.org,scmp.com,japantimes.co.jp,arctictoday.com,euronews.com,warontherocks.com");

    private static String obter(String chave) {
        String valor = System.getenv(chave);
        return valor != null ? valor : ENV_FILE.get(chave);
    }

    private static Map<String, String> carregarEnvFile() {
        Map<String, String> valores = new HashMap<>();
        Path arquivo = Path.of(".env");
        if (!Files.exists(arquivo)) {
            return valores;
        }
        try {
            for (String linha : Files.readAllLines(arquivo)) {
                linha = linha.trim();
                int igual = linha.indexOf('=');
                if (linha.isEmpty() || linha.startsWith("#") || igual < 0) {
                    continue;
                }
                valores.put(linha.substring(0, igual).trim(), linha.substring(igual + 1).trim());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler .env: " + e.getMessage());
        }
        return valores;
    }
}
