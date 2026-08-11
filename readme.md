# 🌍 Geopolitics Telegram Bot

> Autonomous bot in Vanilla Java for monitoring, curating, translating, and broadcasting geopolitical news directly to Telegram.

**Status:** Live on Telegram — [t.me/geopoliticaNews](https://t.me/geopoliticaNews)

Actively developed in public sprints.

---

## 🎯 What it does

Monitors 14 hand-picked international outlets, filters for genuine geopolitical relevance using a two-axis boolean query, translates headlines and summaries from English to Portuguese via DeepL, and delivers formatted alerts to a Telegram channel — with two-layer deduplication and failure resilience.

**Coverage:**

| Tag | Topics |
|---|---|
| `[Guerra]` | Armed conflict, military movements, diplomatic tensions, nuclear programs, naval disputes, sovereignty |
| `[Economia]` | Sanctions, tariffs, trade war, semiconductors, supply chains, strategic exports |

**Regions in focus:** United States, China, Taiwan, Iran, Japan, Brazil.

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
│   ├── NewsService.java            # Curated query + HTTP fetch + Gson parsing
│   ├── TranslationService.java     # EN → PT translation via DeepL API
│   └── TelegramService.java        # HTML message formatting + Bot API delivery
├── botOrchestrator/
│   └── BotOrchestrator.java        # Coordinates fetch → dedupe → translate → send → save
├── repository/
│   └── NoticiaRepository.java      # JDBC persistence + URL deduplication
└── config/
    ├── AppConfig.java              # Environment variables + curated query constants
    └── DataBaseConfig.java         # PostgreSQL connection via DriverManager
```

**Key architectural decisions:**
- `Noticia` is immutable — full constructor, no setters
- `ArticleDTO` mirrors the external JSON structure — decoupled from the domain model
- `BotOrchestrator` owns the orchestration loop — services are single-responsibility
- Category is passed as a parameter, not stored on `Noticia` — it describes *how the article was fetched*, not what it is
- `AppConfig` reads all secrets from environment variables — nothing hardcoded
- `try-catch` in the orchestrator absorbs network failures without crashing the bot
- `PreparedStatement` always — never raw SQL string concatenation (SQL Injection prevention)
- Deduplication in two layers: `jaExiste()` check + `UNIQUE` constraint on the database

---

## 🔍 The curation layer

The hardest problem was not making the bot work — it was making it useful.

An early version searched for `USA` and returned a Love Island season finale, a bald eagle, and a travel advisory about diarrhea. Nothing was broken. The query was simply too naive: `USA` is a generic token that appears in any American text.

Three mechanisms fixed it:

**1. Two-axis boolean query.** A place alone is not enough. Every article must match a region *and* a geopolitical theme:

```java
"(Iran OR \"United States\" OR Taiwan OR Brazil OR China OR Japan) AND (war OR military OR conflict OR tensions OR defense OR nuclear OR navy OR sovereignty)"
```

**2. Domain whitelist.** NewsAPI's `everything` endpoint spans ~150,000 sources. Restricting to 14 curated outlets is deterministic — if Reuters did not publish it, it never arrives. Results dropped from 19,761 to 85.

**3. `sortBy=relevancy`.** The default `publishedAt` returns the *newest* matches, not the *best* ones — the wrong axis for a curation product.

> **Note:** the `q` parameter must be URL-encoded. Without it, the API silently returns `totalResults: 0` with `status: ok` — no error at all.

**Sources:** Reuters · AP · AFP · BBC · Al Jazeera · DW · France 24 · Foreign Policy · Foreign Affairs · The Diplomat · CSIS · South China Morning Post · Japan Times · Arctic Today

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
| Translation | DeepL API (Free Tier) |
| Delivery | Telegram Bot API |

---

## ⚙️ Business Rules

- **Cron cycle:** fetch every 2 hours via `ScheduledExecutorService` *(pending)*
- **Deduplication:** `jaExiste(url)` before processing + `UNIQUE` constraint on `url_original` at the DB layer
- **Translation economy:** deduplication runs *before* translation — no DeepL quota spent on articles that will be discarded
- **Resilience:** network failures and API timeouts are caught and logged — the bot survives for the next cycle
- **Translation fallback:** if DeepL fails, the article is delivered in English with an `[EN]` marker rather than dropped
- **Language:** all news fetched in English (`language=en`, `pageSize=5`), translated to Portuguese before delivery

---

## 💬 Message format

Messages are sent with `parse_mode=HTML` and link previews disabled, for a minimal mobile-first feed:

```
[Guerra]

<b>US launches strikes on Iran after attempted attack on troops</b>

Summary translated to Portuguese, two to three lines.

<a href="...">Ler Materia</a>
```

---

## 🚀 Sprint Progress

- [x] Domain model (`Noticia` — immutable)
- [x] `ArticleDTO` + `NewsAPIResponseDTO` — JSON → object mapping via Gson
- [x] `NewsService` — real HTTP call + Gson parsing
- [x] `AppConfig` — environment variables, `.env` protected by `.gitignore`
- [x] `DataBaseConfig` — PostgreSQL connection via DriverManager
- [x] `NoticiaRepository` — JDBC: `salvar()` + `jaExiste()` with PreparedStatement
- [x] PostgreSQL 16 running in Docker with `init.sql` auto-executed on startup
- [x] `BotOrchestrator` — full pipeline: fetch → dedupe → translate → send → save
- [x] **Curation layer** — boolean query, domain whitelist, `sortBy=relevancy`, URL encoding
- [x] **DeepL API integration** — real EN → PT translation
- [x] **Telegram Bot API integration** — HTML formatting, live on a public channel
- [ ] `ScheduledExecutorService` — automated 2h cycle
- [ ] VPS deployment — the bot currently runs only while the local machine is on

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

The `UNIQUE` constraint on `url_original` is a second line of defense — even if the application logic fails, the database rejects repeated inserts.

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
TELEGRAM_CHAT_ID=your_channel_id_here
NEWS_API_KEY=your_newsapi_key_here
DEEPL_API_KEY=your_deepl_key_here
DB_URL=jdbc:postgresql://localhost:5432/bancoBotTelegram
DB_USER=your_db_user
DB_PASSWORD=your_db_password
```

> To post to a channel, the bot must be added as an **administrator** with *post messages* permission. Channel IDs are negative (e.g. `-100...`).

### Running

```bash
# Start PostgreSQL via Docker
docker-compose up -d

# Build and run
mvn compile exec:java -Dexec.mainClass="com.nicolas.botTelegram.Main"
```

---

## 🗺️ Roadmap

**V1 (current)** — autonomous broadcast to a single channel with a fixed curated query.

**V2** — multi-chat with per-user subscriptions: each chat defines its own regions and themes via Telegram commands, requiring `getUpdates` polling, a subscribers table, and per-user deduplication.

---

## 📌 Development Workflow

Each feature sprint is developed in a separate Git branch, simulating real team workflows (e.g. `DTOservice`, `Deepl-Integration-Test`).

---

*Developed as a consolidation project in Software Architecture and Java Backend — 4th semester, Software Engineering.*