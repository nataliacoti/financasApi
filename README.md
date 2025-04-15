# financasApi

`financasApi` é uma API REST desenvolvida com **Spring Boot** e estruturada segundo os princípios de **Domain-Driven Design (DDD)**. O projeto tem como objetivo o gerenciamento de contas e tipos financeiros, oferecendo endpoints para operações de **CRUD (Create, Read, Update, Delete)**.

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Data MongoDB**
- **PostgreSQL** (persistência relacional)
- **MongoDB** (persistência NoSQL para logs e auditoria)
- **RabbitMQ** (mensageria entre serviços)
- **JUnit 5** (testes de integração)
- **Docker** e **Docker Compose**

## 🧱 Arquitetura

O projeto segue os princípios do **Domain-Driven Design (DDD)**, com as seguintes camadas:

- `domain` → Entidades, agregados, repositórios e regras de negócio
- `application` → Casos de uso (services), interfaces de comunicação entre as camadas
- `infrastructure` → Implementações de persistência (JPA, Mongo), mensageria (RabbitMQ), controladores REST, configurações


## 📦 Endpoints da API

A API expõe endpoints RESTful para gerenciamento de **Tipos** e **Contas**.

### 📘 Tipos

- `POST /api/tipos` – Criar um novo tipo
- `GET /api/tipos` – Listar todos os tipos
- `GET /api/tipos/{id}` – Buscar um tipo por ID
- `PUT /api/tipos/{id}` – Atualizar um tipo existente
- `DELETE /api/tipos/{id}` – Deletar um tipo

### 💰 Contas

- `POST /api/contas` – Criar uma nova conta
- `GET /api/contas` – Listar todas as contas
- `GET /api/contas/{id}` – Buscar uma conta por ID
- `PUT /api/contas/{id}` – Atualizar uma conta existente
- `DELETE /api/contas/{id}` – Deletar uma conta

## 🧪 Testes

Os testes de integração são realizados com **JUnit 5**, focando na integração entre camadas, especialmente os casos de uso da aplicação.

## 🐳 Docker

O projeto pode ser executado via Docker com os seguintes serviços:

- **PostgreSQL**
- **MongoDB**
- **RabbitMQ**
- **financasApi (app)**


