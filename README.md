# Limpa Fossa Backend

API Backend para gestão de serviços de limpa fossa, desenvolvida com Spring Boot e Java 21.

## 🚀 Tecnologias Utilizadas

*   **Linguagem:** Java 21
*   **Framework:** Spring Boot 3.5.9
*   **Banco de Dados:** PostgreSQL 16 (Alpine)
*   **Gerenciamento de Banco:** Flyway Migration
*   **Containerização:** Docker & Docker Compose
*   **Mapeamento de Objetos:** MapStruct 1.6.3
*   **Segurança:** Spring Security + Auth0 Java JWT
*   **Utilitários:** Lombok

## 🛠️ Infraestrutura e Configuração

O ambiente de desenvolvimento utiliza Docker para o banco de dados e ferramentas de administração.

### Serviços Docker (docker-compose.yml)
*   **Database:** PostgreSQL rodando na porta `5432`.
    *   Database: `limpafossa_db`
    *   User/Pass: `admin` / `admin`
*   **PgAdmin:** Interface web para gestão do banco rodando na porta `5050`.
    *   Login: `admin@admin.com`
    *   Senha: `admin`

### Como Rodar o Ambiente

1.  Suba os containers do banco de dados:
    ```bash
    docker-compose up -d
    ```
2.  Execute a aplicação Spring Boot:
    ```bash
    mvn spring-boot:run
    ```

## 🏗️ Estrutura do Projeto (Implementado até o momento)

*   **Configuração (`application.properties`):**
    *   Conexão JDBC configurada para o container Docker.
    *   JPA/Hibernate configurado para validação de schema (`ddl-auto=validate`).
    *   Flyway habilitado para migrações em `classpath:db/migration`.
*   **Repositórios (JPA):**
    *   `CustomerRepository`: Métodos para busca e verificação por documento.
    *   `OrderRepository`: Busca de pedidos por ID do cliente.
*   **Mappers (MapStruct):**
    *   `OrderMapper`: Conversão entre Entidade `Order` e DTOs (`CreateOrderDTO`, `OrderResponseDTO`), incluindo mapeamento de dados do cliente aninhado.
*   **Dependências (`pom.xml`):**
    *   Inclusão de Spring Web, Data JPA, Validation, Security, DevTools e processadores de anotação (Lombok/MapStruct).
