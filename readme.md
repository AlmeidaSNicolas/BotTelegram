# 🌍 Geopolitics Telegram Bot

> Autonomous bot in Vanilla Java for monitoring, translating, and broadcasting geopolitical news directly to Telegram.

**Status:** Work in Progress — actively developed in public sprints.

---

## 🎯 What it does

Fetches real-time news from international sources filtered by geopolitical keywords (Arctic, Taiwan, China, global tensions), translates titles and summaries from English to Portuguese, and delivers curated alerts directly to a Telegram channel — running autonomously every 2 hours with built-in deduplication and resilience.

---

## 🏗️ Architecture

The system is organized in clearly separated layers, each with a single responsibility:

```
src/com/nicolas/botTelegram/
├── model/
│   └── Noticia.java             # Immutable domain object (title, summary, url)
├── service/
│   ├── NewsService.java         # HTTP fetch from NewsAPI.org via OkHttp
│   ├── TranslationService.java  # EN → PT translation via DeepL API
│   └── TelegramService.java     # Message formatting and delivery
├── orchestrator/
│   └── BotOrchestrator.java     # Coordinates the full fetch → translate → send cycle
├── repository/
│   └── NoticiaRepository.java   # JDBC persistence + URL deduplication (coming soon)
└── config/
    └── AppConfig.java           # Centralized config via environment variables
```

**Key architectural decisions:**
- `Noticia` is immutable — full constructor, no setters
- `BotOrchestrator` owns the orchestration loop — services are single-responsibility
- `AppConfig` reads all secrets from environment variables — nothing hardcoded
- `try-catch` in the orchestrator absorbs network failures without crashing the bot

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 (Vanilla, no Spring) |
| Build Tool | Maven |
| Database | PostgreSQL |
| Infrastructure | Docker *(containerization — to be configured)* |
| HTTP Client | OkHttp 5.4.0 |
| JSON Parsing | Gson 2.14.0 |
| News Source | NewsAPI.org |
| Translation | DeepL Free Tier |
| Delivery | Telegram Bot API |

---

## ⚙️ Business Rules

- **Cron cycle:** fetch runs every 2 hours via `ScheduledExecutorService`
- **Deduplication:** UNIQUE constraint on URL at the database layer — no duplicate alerts
- **Rate limit:** max 20 messages per day for curated, high-quality delivery
- **Resilience:** network failures and API timeouts are caught and logged — the bot stays alive for the next cycle
- **Language:** all news fetched in English (`language=en`), translated to Portuguese before delivery

---

## 🚀 Current Sprint Progress

- [x] Domain model (`Noticia` — immutable)
- [x] `NewsService` — real HTTP call to NewsAPI.org
- [x] `TranslationService` — mock (DeepL integration next)
- [x] `TelegramService` — mock (Bot API integration next)
- [x] `BotOrchestrator` — full cycle running in terminal
- [x] `AppConfig` — environment variables, `.env` protected by `.gitignore`
- [x] Maven — OkHttp, Gson, PostgreSQL JDBC driver configured
- [x] JSON parsing with Gson → `Noticia` objects
- [x] `NoticiaRepository` — JDBC + deduplication
- [ ] DeepL API integration
- [ ] Telegram Bot API integration
- [ ] `ScheduledExecutorService` — automated 2h cycle
- [ ] Docker — containerization setup

---

## 🔧 Setup

### Prerequisites
- Java 17+
- Maven
- PostgreSQL

### Environment Variables

Create a `.env` file in the project root (never commit this file):

```
TELEGRAM_BOT_TOKEN=your_token_here
TELEGRAM_CHAT_ID=your_chat_id_here
NEWS_API_KEY=your_newsapi_key_here
DEEPL_API_KEY=your_deepl_key_here
```

### Running

```bash
mvn compile exec:java -Dexec.mainClass="com.nicolas.botTelegram.Main"
```

> Docker Compose and full deployment guide will be added in a future sprint.

---

## 📌 Development Workflow

Each feature sprint is developed in a separate Git branch, simulating real team workflows.

---

*Developed as a consolidation project in Software Architecture and Java Backend — 4th semester, Software Engineering.*
