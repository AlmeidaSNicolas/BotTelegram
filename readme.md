🌍 Geopolitics Telegram Bot (WIP)

An autonomous Telegram bot focused on monitoring, translating, and automatically broadcasting news about global geopolitics and international tensions.

An autonomous microservice developed in Vanilla Java, designed to monitor international news APIs and track updates on global geopolitical tensions — focusing on strategic regions such as the Arctic, Taiwan, and China. The system features an internal translation engine to Portuguese, duplication validation via PostgreSQL, and scheduled alert broadcasting directly to Telegram, operating with low memory consumption and high resilience.

🚀 Project Objective

The system fetches data from international APIs focused on specific keywords (e.g., tensions in Asia, the Arctic, US foreign policy), translates titles and summaries from English to Portuguese, and sends direct alerts on Telegram, maintaining strict control against spam and news repetition.

🛠️ Technologies Used

Language: Java (Vanilla) - Built without robust frameworks like Spring to maximize performance and maintain low memory consumption.

Database: PostgreSQL

Infrastructure: Docker (DB Containerization)

Integrations:

Telegram Bot API

News REST API

Translation REST API

⚙️ Architecture and Business Rules (MVP)

The system was designed to run autonomously 24/7 with a focus on resilience:

Internal Cron Job: Fetching routines executed natively every 2 hours using ScheduledExecutorService.

Absolute Duplication Prevention: State validation performed directly at the data layer (UNIQUE constraints in PostgreSQL) to prevent resending already registered URLs.

Rate Limit Control: Strict limit of 10 messages processed per day to ensure high feed curation.

Exception Handling: System prepared to withstand network drops and third-party API timeouts without interrupting the application's lifecycle.

🗄️ Structure and Installation

Setup instructions, environment variables, and deployment guides will be added soon as development progresses.

Developed as a consolidation project in Software Architecture and Java Backend.
