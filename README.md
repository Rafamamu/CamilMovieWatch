# 🎬 CamilMovieWatch

Projeto desenvolvido em **Spring Boot** para gerenciamento e registro de catálogo de filmes.  
O objetivo é permitir que usuários cadastrem, consultem e organizem filmes assistidos ou desejados, com suporte a autenticação e documentação da API.

---

## 🚀 Tecnologias utilizadas
- **Java 17**
- **Spring Boot** (Web, JPA, Security, Flyway)
- **PostgreSQL**
- **Docker Compose**
- **Swagger / OpenAPI**
- **Maven**

---

## ⚙️ Como rodar o projeto

### 🔹 Rodando localmente (aplicação fora do Docker, banco dentro)
1. Crie um arquivo `.env` com suas credenciais:
   `.env
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=<nome-do-banco>
   DB_USERNAME=<usuario-do-banco>
   DB_PASSWORD=<senha-do-banco>
   SECRET_KEY=<sua-chave-secreta>`

   ###  Suba o Banco com Docker
      ``docker-compose up -d``

   ### Rode a aplicação
      ``mvn spring-boot:run -Dspring.profiles.active=local``

   📂 Estrutura do projeto- src/main/java → código fonte da aplicação
- src/main/resources/application.yml → configuração principal
- src/main/resources/db/migration → migrations do Flyway
- .env → variáveis de ambiente (não versionado)
- docker-compose.yml → orquestração do banco via Docker
  
### 🗄️ Modelagem do Banco de DadosA modelagem segue uma estrutura simples de usuários, filmes e registros de acompanhamento:User (id, name, email, password)
   |
   |---< MovieWatch (id, user_id, movie_id, status, rating)
                |
                |--- Movie (id, title, genre, release_date)
- User: informações de login e perfil.
- Movie: catálogo de filmes disponíveis.
- MovieWatch: relacionamento entre usuários e filmes, com status (assistido, em andamento, desejado) e avaliação.
  
 ### 🔑 Segurança- Autenticação baseada em JWT.
- A chave secreta é definida pela variável SECRET_KEY no .env.
  
 ### 📖 Documentação da API- Swagger UI disponível em:
http://localhost:8080/swagger/index.html
- OpenAPI JSON em:
http://localhost:8080/api/api-docs

### 📝 Contribuição- Faça um fork do projeto
- Crie uma branch (git checkout -b feature/nova-feature)
- Commit suas alterações (git commit -m 'Adiciona nova feature')
- Push para a branch (git push origin feature/nova-feature)
- Abra um Pull Request
 ### 📌 LicençaEste projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.
   
   
   
