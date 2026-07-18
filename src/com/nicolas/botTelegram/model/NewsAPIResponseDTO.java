package com.nicolas.botTelegram.model;

import java.util.List;

public class NewsAPIResponseDTO {
    private String status;

    private List<ArticleDTO> articles;

    public String getStatus() {
        return status;
    }

    public List<ArticleDTO> getArticles() {
        return articles;
    }

}
