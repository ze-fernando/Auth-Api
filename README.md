# Documentação do Projeto: Sistema de Autenticação e Confirmação de Conta

## Visão Geral

Este projeto é uma API back-end desenvolvida em Java usando o framework Spring. O principal objetivo é fornecer funcionalidades de autenticação e confirmação de conta, incluindo o disparo de e-mails para a confirmação.

### Tecnologias Utilizadas

- Linguagem de Programação: Java
- Framework: Spring Boot
- Banco de Dados: MySQL (via Docker)
- Gerenciador de Dependências: Maven
- Persistência: JPA (Java Persistence API)
- Autenticação: JWT (JSON Web Tokens)

## Configuração do Ambiente

### Requisitos

- Java JDK 8 ou superior
- Maven
- Docker
- IDE de sua escolha (recomendado: IntelliJ, Eclipse)

### Configuração do Banco de Dados

O banco de dados MySQL pode ser iniciado via Docker usando o seguinte comando:

```bash
docker-compose up
```

Certifique-se de ajustar as vaiáveis de ambiente conforme necessário.


## Funcionalidades

### Autenticação

- **Endpoint:** `/auth/login`
- **Método:** POST
- **Descrição:** Autentica o usuário e gera um token JWT.

#### Parâmetros de Requisição

- `name`: Nome de usuário do usuário.
- `password`: Senha do usuário.

#### Exemplo de Requisição

```json
{
  "name": "usuario",
  "password": "senha"
}
```

#### Exemplo de Resposta

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Confirmação de Conta

- **Endpoint:** `/user/verify/{code}`
- **Método:** GET
- **Descrição:** Confirma a conta do usuário com base no token fornecido.

#### Parâmetros de Requisição

- `token`: Token de confirmação enviado por e-mail.

#### Exemplo de Requisição

```url
/user/verify/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

#### Exemplo de Resposta

```json
"user is active"
```

## Conclusão

Este documento fornece uma visão geral do projeto, configuração do ambiente, estrutura do projeto e detalhes das funcionalidades implementadas. Certifique-se de seguir as boas práticas de segurança ao implantar este sistema em um ambiente de produção.