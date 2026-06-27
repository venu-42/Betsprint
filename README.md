# 🚀 BetSprint — Fantasy Sports Backend API

BetSprint is a high-performance Spring Boot REST API engine designed for fantasy sports systems. It handles complex multi-tenant database transactions, match scheduling, user wallet balances, and real-time contest entry tracking.

---

## 🛠️ Current Project Status & Scope

This repository contains the completed baseline infrastructure and primary domain logic for the platform.

### ✅ Implemented Features
* **Database Architecture (DDL/DML):** Fully designed and normalized schema mappings covering `Matches`, `Teams`, `Tournaments`, `Contests`, and `ContestEntries` using PostgreSQL/MySQL.
* **Relationship Management:** Optimized table persistence configurations using dynamic data transfer abstractions (DTO Records) and high-performance repository indexing (`existsBy...`).
* **Endpoint Architecture:** Built out roughly **90% of business-critical REST endpoints** including:
    * Public Match Feed engines
    * Secure Contest Creation & User Join actions
    * Live Identity Context mapping using JWT access structures

### ⏳ Missing / Pending Implementations
* 🔴 **Update Player Scores:** The real-time metric tracking worker and corresponding controller endpoint (`PUT /api/v1/admin/players/scores`) are **not yet implemented**. Player point processing systems currently remain mock-bound until data provider pipelines are plugged in.


