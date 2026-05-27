# 🌍 Geopolitics Telegram Bot (WIP)

Um bot autônomo para o Telegram focado no monitoramento, tradução e disparo automatizado de notícias sobre geopolítica global e tensões internacionais.

## 🚀 Objetivo do Projeto
O sistema busca dados em APIs internacionais focadas em palavras-chave específicas (ex: tensões na Ásia, Ártico, política externa dos EUA), traduz os títulos e resumos do inglês para o português e dispara alertas diretos no Telegram, mantendo um rigoroso controle contra spam e repetição de notícias.

## 🛠️ Tecnologias Utilizadas
*   **Linguagem:** Java (Vanilla) - *Construído sem frameworks robustos como Spring para maximizar performance e baixo consumo de memória.*
*   **Banco de Dados:** PostgreSQL
*   **Infraestrutura:** Docker (Containerização do BD)
*   **Integrações:**
    *   Telegram Bot API
    *   API REST de Notícias
    *   API REST de Tradução

## ⚙️ Arquitetura e Regras de Negócio (MVP)
O sistema foi projetado para rodar de forma autônoma 24/7 com foco em resiliência:
*   **Cron Job Interno:** Rotinas de busca executadas nativamente a cada 2 horas utilizando `ScheduledExecutorService`.
*   **Prevenção de Duplicidade Absoluta:** Validação de estado feita diretamente na camada de dados (Constraints UNIQUE no PostgreSQL) para impedir o reenvio de URLs já registradas.
*   **Controle de Rate Limit:** Limite rígido de 10 mensagens processadas por dia para garantir alta curadoria do feed.
*   **Tratamento de Exceções:** Sistema preparado para suportar quedas de rede e timeouts nas APIs de terceiros sem interromper o ciclo de vida da aplicação.

## 🗄️ Estrutura e Instalação
*Instruções de setup, variáveis de ambiente e deploy serão adicionadas em breve conforme o avanço do desenvolvimento.*

---
*Desenvolvido como projeto de consolidação em Arquitetura de Software e Java Backend.*