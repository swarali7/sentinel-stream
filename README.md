# SentinelStream 
**High-Throughput Data Resilience Engine**

A modern backend microservice built with **Java 21** and **Spring Boot 3.x**, designed to intercept, validate, and sanitize high-velocity data streams using **Virtual Threads**.

## Tech Stack
* **Language:** Java 21 (Virtual Threads / Project Loom)
* **Framework:** Spring Boot 3.2
* **Infrastructure:** GitHub Actions (CI/CD)
* **Architecture:** Layered (API -> Core Engine -> Service -> Model)

## Privacy & Security Features
* **Immutability:** Uses Java Records to prevent data corruption.
* **PII Masking:** Built-in utility to redact sensitive data in logs.
* **Defensive Validation:** "Fail-fast" logic to reject malformed packets at the entry point.

## Getting Started
1. Clone the repo.
2. Run `mvn spring-boot:run`.
3. Test ingestion: 
   `curl -X POST http://localhost:8080/api/v1/stream/ingest -d "Your data here"`