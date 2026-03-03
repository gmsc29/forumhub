# 💬 FórumHub - Challenge Back-End Alura

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

Bem-vindo ao **FórumHub**! Este projeto é uma API RESTful desenvolvida como o desafio final da trilha de Back-End da Alura. O objetivo é replicar o funcionamento de um fórum de dúvidas, onde alunos e professores podem interagir, criar tópicos e solucionar problemas.

## 🎯 Funcionalidades

A API foca no gerenciamento de tópicos e conta com um sistema de segurança robusto:

- **Autenticação:** Login seguro devolvendo um token JWT.
- **Controle de Acesso:** Apenas usuários autenticados podem interagir com os endpoints protegidos.
- **CRUD de Tópicos:** Criação, listagem, detalhamento, atualização e exclusão.
- **Validações:** Prevenção de tópicos duplicados (mesmo título e mensagem).
- **Paginação:** Listagem de tópicos paginada e ordenada por data de criação.

## 🛣️ Endpoints da API

Abaixo estão as principais rotas disponíveis na aplicação:

| Método | Endpoint | Descrição | Autenticação |
|---|---|---|---|
| `POST` | `/login` | Autentica o usuário e retorna o token JWT | ❌ Não |
| `POST` | `/topicos` | Cria um novo tópico | ✅ Sim |
| `GET` | `/topicos` | Lista todos os tópicos (com paginação) | ✅ Sim |
| `GET` | `/topicos/{id}` | Retorna os detalhes de um tópico específico | ✅ Sim |
| `PUT` | `/topicos/{id}` | Atualiza o título e/ou mensagem de um tópico | ✅ Sim |
| `DELETE` | `/topicos/{id}` | Exclui um tópico do fórum | ✅ Sim |

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3** (Web, Data JPA, Security, Validation)
- **MySQL** (Banco de dados relacional)
- **Flyway** (Migrations para controle de versão do banco de dados)
- **Auth0 java-jwt** (Geração e validação de tokens JWT)
- **Lombok** (Redução de código boilerplate)
- **Maven** (Gerenciamento de dependências)

## 🚀 Como executar o projeto localmente

1. Clone este repositório:
   ```bash
   git clone [https://github.com/SEU-USUARIO/forumhub.git](https://github.com/SEU-USUARIO/forumhub.git)
