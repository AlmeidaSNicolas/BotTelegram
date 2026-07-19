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
│   ├── Noticia.java                # Immutable domain object (title, summary, url)
│   ├── ArticleDTO.java             # Maps raw JSON article fields from NewsAPI
│   └── NewsAPIResponseDTO.java     # Maps root JSON response from NewsAPI
├── service/
│   ├── NewsService.java            # HTTP fetch from NewsAPI.org via OkHttp + Gson parsing
│   ├── TranslationService.java     # EN → PT translation via DeepL API
│   └── TelegramService.java        # Message formatting and delivery
├── orchestrator/
│   └── BotOrchestrator.java        # Coordinates full fetch → translate → send → save cycle
├── repository/
│   └── NoticiaRepository.java      # JDBC persistence + URL deduplication
└── config/
    ├── AppConfig.java              # Centralized config via environment variables
    └── DatabaseConfig.java         # PostgreSQL connection via DriverManager
```

**Key architectural decisions:**
- `Noticia` is immutable — full constructor, no setters
- `ArticleDTO` mirrors the external JSON structure — decoupled from the domain model
- `BotOrchestrator` owns the orchestration loop — services are single-responsibility
- `AppConfig` reads all secrets from environment variables — nothing hardcoded
- `try-catch` in the orchestrator absorbs network failures without crashing the bot
- `PreparedStatement` always — never raw SQL string concatenation (SQL Injection prevention)
- Deduplication in two layers: `jaExiste()` check + `UNIQUE` constraint on the database

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 (Vanilla, no Spring) |
| Build Tool | Maven |
| Database | PostgreSQL 16 |
| Infrastructure | Docker + docker-compose |
| HTTP Client | OkHttp 5.4.0 |
| JSON Parsing | Gson 2.14.0 |
| DB Driver | PostgreSQL JDBC 42.7.12 |
| News Source | NewsAPI.org |
| Translation | DeepL Free Tier |
| Delivery | Telegram Bot API |

---

## ⚙️ Business Rules

- **Cron cycle:** fetch runs every 2 hours via `ScheduledExecutorService`
- **Deduplication:** `jaExiste(url)` checks before processing + `UNIQUE` constraint on `url_original` rejects duplicate inserts at the DB layer
- **Rate limit:** max 20 messages per day for curated, high-quality delivery
- **Resilience:** network failures and API timeouts are caught and logged — the bot stays alive for the next cycle
- **Language:** all news fetched in English (`language=en`, `pageSize=5`), translated to Portuguese before delivery

---

## 🚀 Current Sprint Progress

- [x] Domain model (`Noticia` — immutable)
- [x] `ArticleDTO` + `NewsAPIResponseDTO` — JSON → object mapping via Gson
- [x] `NewsService` — real HTTP call to NewsAPI.org + Gson parsing
- [x] `TranslationService` — mock (DeepL integration next)
- [x] `TelegramService` — mock (Bot API integration next)
- [x] `BotOrchestrator` — full pipeline: fetch → deduplicate → translate → send → save
- [x] `AppConfig` — environment variables, `.env` protected by `.gitignore`
- [x] `DatabaseConfig` — PostgreSQL connection via DriverManager
- [x] `NoticiaRepository` — JDBC: `salvar()` + `jaExiste()` with PreparedStatement
- [x] PostgreSQL 16 running in Docker with `init.sql` auto-executed on startup
- [x] Maven — OkHttp, Gson, PostgreSQL JDBC driver configured
- [ ] DeepL API integration
- [ ] Telegram Bot API integration
- [ ] `ScheduledExecutorService` — automated 2h cycle

---

## 🗄️ Database

```sql
CREATE TABLE IF NOT EXISTS noticias_enviadas (
    id           SERIAL PRIMARY KEY,
    titulo       VARCHAR(255) NOT NULL,
    url_original TEXT UNIQUE NOT NULL,
    data_envio   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

The `UNIQUE` constraint on `url_original` acts as a second line of defense against duplicate alerts — even if the application logic fails, the database rejects repeated inserts.

---

## 🔧 Setup

### Prerequisites
- Java 17+
- Maven
- Docker

### Environment Variables

Create a `.env` file in the project root (never commit this file):

```
TELEGRAM_BOT_TOKEN=your_token_here
TELEGRAM_CHAT_ID=your_chat_id_here
NEWS_API_KEY=your_newsapi_key_here
DEEPL_API_KEY=your_deepl_key_here
DB_URL=jdbc:postgresql://localhost:5432/bancoBotTelegram
DB_USER=your_db_user
DB_PASSWORD=your_db_password
```

### Running

```bash
# Start PostgreSQL via Docker
docker-compose up -d

# Build and run
mvn compile exec:java -Dexec.mainClass="com.nicolas.botTelegram.Main"
```

---

## 📌 Development Workflow

Each feature sprint is developed in a separate Git branch, simulating real team workflows. Branch naming follows the pattern of the feature being developed (e.g., `DTOservice`, `OrchestratorSprint`).

---

*Developed as a consolidation project in Software Architecture and Java Backend — 4th semester, Software Engineering.*