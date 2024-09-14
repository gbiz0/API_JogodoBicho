# Jogo do Bicho API

Este projeto é uma API desenvolvida em **Java Spring Boot** para o jogo do bicho, com o objetivo de praticar e aplicar conhecimentos técnicos adquiridos na área de desenvolvimento. **Atenção:** este projeto é de uso estritamente educacional e **não deve ser utilizado de forma ilegal**.

## Funcionalidades

- **Autenticação JWT**: Apenas contraventores autenticados como Admin podem cadastrar clientes e criar apostas vinculadas a esses clientes.
- **CRUD Completo**:
  - Gerenciamento de contraventores.
  - Gerenciamento de clientes.
  - Gerenciamento de apostas.
- **Documentação**: Swagger UI integrada para visualizar e testar os endpoints da API.
- **Banco de Dados**: PostgreSQL como banco de dados relacional.
- **Testes**: Testes unitários implementados com Mockito.

## Tecnologias Utilizadas

- **Java 21**
- **Maven**
- **Spring Boot 3.3.2**
  - Spring Security para autenticação e autorização.
  - Spring Data JPA para interação com o banco de dados.
- **JWT** (JSON Web Token) para controle de autenticação.
- **PostgreSQL** como banco de dados.
- **Mockito** para testes unitários.
- **Swagger** para documentação da API.

## Como Rodar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/gbiz0/API_JogodoBicho.git
   cd API_JogodoBicho
   
2. **Configuração do Banco de Dados**:

    - Certifique-se de que o PostgreSQL está instalado e rodando.
    - Crie um banco de dados chamado bicho_db.
    - Atualize as configurações de conexão em application.properties ou application.yml com suas credenciais do PostgreSQL.
    - Pegue o script sql localizado em util/script.sql
      
3. **Execute a aplicação**:

4. **Acesse a documentação Swagger**:
        Acesse http://localhost:8080/swagger-ui.html para visualizar e testar os endpoints da API.

5. **Autenticação JWT**

Os endpoints são protegidos por JWT. Para acessar os endpoints que exigem autenticação:
  - Faça login usando as credenciais de um contraventor Admin.
  - Obtenha o token JWT e adicione-o ao cabeçalho Authorization de suas requisições como Bearer {token}.

## Contribuições

Este projeto está aberto a contribuições de qualquer pessoa que deseje colaborar. Sinta-se à vontade para abrir issues, sugerir melhorias ou enviar pull requests. Toda ajuda é bem-vinda para aprimorar ainda mais o projeto!

## Próximos Passos
  - **Frontend**: Futuramente, será desenvolvido um frontend em React, consumindo esta API via Axios.
  - **Deploy**: Planejo fazer o deploy da API em um servidor para acesso público.

## Nota importante
  - Este projeto é uma ideia para fins educativos, focando em conceitos de autenticação, CRUD, APIs RESTful e boas práticas no desenvolvimento de software. Em hipótese alguma deve ser utilizado para atividades ilegais.
