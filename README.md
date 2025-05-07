# 🎓 Sistema de Gerenciamento de Cursos Online (EAD)

Uma API RESTful para gerenciar uma plataforma de ensino a distância.  
Professores podem criar cursos e adicionar aulas. Alunos podem se cadastrar, se inscrever em cursos, assistir aulas e deixar comentários.

Este projeto foi criado para consolidar conhecimentos em desenvolvimento Back-end com **Java** e **Spring Boot**, aplicando boas práticas e arquitetura em camadas.

---

## 🚀 Funcionalidades

### 👩‍🏫 Professores
- **Cadastro/Login**
- **Criar Cursos**
- **Editar/Excluir Cursos**
- **Adicionar/Editar Aulas**

### 👨‍🎓 Alunos
- **Cadastro/Login**
- **Listar Cursos Disponíveis**
- **Inscrever-se em Cursos**
- **Visualizar Aulas Inscritas**
- **Comentar nas Aulas**

### 🔐 Autenticação & Segurança
- Proteção de rotas com JWT
- Regras de autorização baseadas em perfis: `STUDENT` e `TEACHER`

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot** (Web, Data JPA, Security)
- **PostgreSQL** (ou H2 para testes locais)
- **Lombok**
- **Swagger/OpenAPI**
- **JWT (JSON Web Token)**
- **Postman**
- **Maven**

---

## ⚙️ Como Configurar o Projeto

### 1. Pré-requisitos
- Java 17+
- Maven
- PostgreSQL

### 2. Clonar o Repositório
```bash
git clone https://github.com/thalesbensi/CoursesManagementAPI
````
### 3. Configurar o Banco de Dados

#### No arquivo src/main/resources/application.properties, configure as informações do banco:
````bash
spring.datasource.url=jdbc:postgresql://localhost:5432/ead
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
````

### 4. Executar a Aplicação
````bash
mvn spring-boot:run
````
A aplicação estará disponível em: http://localhost:8080

## 🔧 Endpoints Principais

### 📘 Cursos

- `POST /courses` – Criar novo curso (**PROFESSOR**)
- `GET /courses` – Listar cursos
- `PUT /courses/{id}` – Atualizar curso (**PROFESSOR**)
- `DELETE /courses/{id}` – Deletar curso (**PROFESSOR**)

---

### 🎥 Aulas

- `POST /lessons/{courseId}` – Adicionar aula a um curso
- `GET /lessons/{courseId}` – Listar aulas do curso
- `PUT /lessons/{id}` – Atualizar aula
- `DELETE /lessons/{id}` – Deletar aula

---

### 📝 Comentários

- `POST /comments/{lessonId}` – Comentar em aula (**ALUNO**)
- `GET /comments/{lessonId}` – Listar comentários da aula

---

### 📥 Inscrições

- `POST /enrollments/{courseId}` – Inscrever-se em um curso
- `GET /enrollments/my` – Ver cursos inscritos

---

### 🔐 Autenticação

- `POST /auth/register` – Registrar usuário (**ALUNO** ou **PROFESSOR**)
- `POST /auth/login` – Autenticar e receber token JWT

#### Payload de Registro
```json
{
  "login": "usuario",
  "password": "senha",
  "role": "STUDENT" // ou "TEACHER"
}
````
#### Payload de Login
````json
{
  "login": "usuario",
  "password": "senha"
}
````

## 📑 Documentação da API

A documentação dos endpoints é gerada automaticamente com **Swagger**.  
Acesse no navegador:

🔗 [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)

---

## 🔐 Autorização JWT

A API utiliza autenticação via **JWT (JSON Web Token)** para proteger rotas.  
Inclua o token JWT no cabeçalho das requisições autenticadas, no seguinte formato:

Authorization: Bearer {seu_token_jwt}
