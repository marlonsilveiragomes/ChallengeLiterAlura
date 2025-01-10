# Literalura Consulta Livros

![Projeto Cover](src/main/resources/imagens/Literalura.png)

**Assista o vídeo do projeto!**

![Miniatura do vídeo](https://img.youtube.com/vi/6tE5Q_omB7w/0.jpg)

---

## 📚 **ÍNDICE**

1. [Tecnologias Utilizadas](#-tecnologias-utilizadas)
2. [Configuração do Projeto com Spring Initializr](#-configuração-do-projeto-com-spring-initializr)
3. [Consumo da API Gutendex](#-consumo-da-api-gutendex)
4. [Conversão de Dados](#-conversão-de-dados)
5. [Funcionalidades](#-funcionalidades)
6. [Banco de Dados e Persistência](#-banco-de-dados-e-persistência)
7. [Passo a Passo para Rodar o Projeto no IntelliJ IDEA](#-passo-a-passo-para-rodar-o-projeto-no-intellij-idea)
8. [Estrutura de Pastas](#-estrutura-de-pastas)

---

## ✨ Tecnologias Utilizadas

- **Java JDK:** Versão 17 ou superior  
  [Baixe a versão mais recente do Java LTS](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven:** Versão 4 ou superior
- **Spring Boot:** Versão 3.2.3  
  [Spring Initializr](https://start.spring.io/)
- **PostgreSQL:** Versão 16 ou superior  
  [Baixe o PostgreSQL](https://www.postgresql.org/download/)
- **IDE IntelliJ IDEA (opcional):**  
  [Baixe o IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

---

## 🚀 Configuração do Projeto com Spring Initializr

1. **Configurações Básicas**:
   - Linguagem: **Java (17 ou superior)**
   - Ferramenta de Build: **Maven**
   - Spring Boot: **3.2.3**
   - Tipo de Projeto: **JAR**

2. **Dependências a Adicionar**:
   - **Spring Data JPA**
   - **Postgres Driver**

3. **Estrutura de Pastas no IntelliJ IDEA**:
   - `src/main/java`: Código-fonte principal
   - `src/main/resources`: Arquivos de configuração, como `application.properties`
   - `src/test/java`: Testes unitários e de integração

   > **Nota:** Para criar o projeto no IntelliJ, selecione a opção "New Project" e configure conforme acima.

---

## 📡 Consumo da API Gutendex

A API **Gutendex** é um catálogo com informações sobre mais de 70 mil livros do Project Gutenberg. Utilizamos as classes `HttpClient`, `HttpRequest` e `HttpResponse` para interagir com essa API.

### 1️⃣ Construindo o Cliente para Requisições
Utilizamos a classe `HttpClient` para facilitar a conexão com a API.

### 2️⃣ Configurando a Solicitação
A classe `HttpRequest` nos permite personalizar as requisições para obter os dados desejados da API.

### 3️⃣ Gerenciando a Resposta
Com a interface `HttpResponse`, acessamos e analisamos elementos como status, cabeçalhos e corpo da resposta (JSON).

---

## 🔄 Conversão de Dados

Ao receber os dados da API, convertemos o JSON para objetos Java utilizando as anotações `@JsonIgnoreProperties` e `@JsonAlias` para mapear os atributos necessários.

### Métodos Recomendados:
- getters, setters, e `toString()` para manipulação de dados.

---

## 🎯 Funcionalidades

- **Buscar Livros por Título**
- **Listar Livros no Banco de Dados**
- **Listar Autores no Banco de Dados**
- **Buscar Autores Vivos em Um Determinado Ano**
- **Listar Livros por Idioma**

---

## 📚 Banco de Dados e Persistência

### Utilizando PostgreSQL
- Crie tabelas para **Livro** e **Autor**.
- Configure as entidades utilizando `Spring Data JPA` e anotações como `@Entity` e `@Id`.
- Use `JpaRepository` para manipular as entidades.

### Funcionalidades:
1. **Salvar Livros e Autores**
2. **Listar Livros por Idioma**
3. **Consultar Autores Vivos em Determinado Ano**

---

## 🛠️ Passo a Passo para Rodar o Projeto no IntelliJ IDEA

1. **Clonar o Repositório**:
    ```bash
    git clone https://github.com/seu-usuario/literalura-consulta-livros.git
    ```

2. **Abrir o Projeto no IntelliJ**:
   - Vá em **File > Open** e selecione a pasta do projeto.

3. **Configurar o PostgreSQL**:
   - Crie um banco de dados no PostgreSQL.
   - Configure o arquivo `application.properties` com as credenciais do banco:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
     spring.datasource.username=seu_usuario
     spring.datasource.password=sua_senha
     spring.jpa.hibernate.ddl-auto=update
     ```

4. **Rodar o Projeto**:
   - Clique com o botão direito no arquivo `LiteraluraApplication.java` e selecione **Run**.

5. **Testar Funcionalidades**:
   - Use o terminal para interagir com o menu.

---

## 🧰 Estrutura de Pastas

```plaintext
project-root
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.literalura
│   │   │       ├── LiteraluraApplication.java
│   │   │       ├── model
│   │   │       ├── principal
│   │   │       ├── repository
│   │   │       └── service
│   │   └── resources
│   │       ├── application.properties
│   │       └── imagens
│   └── test
│       └── java
│
├── pom.xml
└── README.md