EventosTec

Backend em Java com Spring Boot para gerenciamento de tickets de eventos de tecnologia. A aplicação permite criar, gerenciar e consultar tickets, armazenando imagens de eventos no AWS S3 e controlando a validade dos tickets com base em um tempo de expiração configurado. Cada evento inclui detalhes como local, hora e data.
Objetivo do Projeto
O projeto foi desenvolvido como parte de um desafio técnico para:

Criar uma API RESTful para gerenciar eventos de tecnologia e seus tickets.
Armazenar imagens dos eventos no AWS S3.
Gerenciar a vida útil dos tickets com base em um tempo de expiração definido no código.
Persistir informações dos eventos (local, hora, data) e tickets em um banco de dados relacional.
Implementar boas práticas, como validação de dados, tratamento de erros e integração com serviços de nuvem.

Funcionalidades

Gerenciamento de Eventos:
Criar eventos com descrição, local, data, hora e imagem.
Consultar eventos por ID ou listar todos.
Atualizar detalhes de um evento.
Excluir eventos.


Gerenciamento de Tickets:
Criar tickets associados a um evento, com tempo de expiração configurável.
Consultar tickets válidos por evento ou usuário.
Validar tickets com base na data de expiração.
Excluir tickets (manualmente ou automaticamente ao expirar).


Armazenamento de Imagens:
Upload de imagens de eventos para o AWS S3.
Recuperação de URLs das imagens para exibição.


Validação de dados (ex.: data futura para eventos, formato de imagem válido).
Integração com banco de dados para persistência de eventos e tickets.

Tecnologias Utilizadas

Java 17: Linguagem principal.
Spring Boot 3.1.0: Framework para criação da API REST.
Spring Data JPA: Para persistência em banco de dados.
H2 Database: Banco de dados em memória para testes (configurável para PostgreSQL ou outro).
AWS SDK for Java: Para integração com o AWS S3.
Maven: Gerenciador de dependências.
Lombok: Para reduzir boilerplate no código.
SpringDoc OpenAPI: Para documentação da API com Swagger.

Pré-requisitos

Java 17 ou superior.
Maven (versão 3.8.0 ou superior).
Conta AWS com acesso ao S3 e credenciais configuradas (Access Key e Secret Key).
(Opcional) Banco de dados relacional, como PostgreSQL, se configurado.
(Opcional) Postman ou similar para testar a API.

Instalação e Configuração

Clonar o repositório:
git clone https://github.com/pedrohenriquebasilio/eventostec.git
cd eventostec


Compilar e instalar dependências:
mvn clean install


Configurar o AWS S3:

Crie um bucket no AWS S3 para armazenar as imagens.
Configure as credenciais AWS no arquivo src/main/resources/application.properties:cloud.aws.credentials.access-key=SEU_ACCESS_KEY
cloud.aws.credentials.secret-key=SEU_SECRET_KEY
cloud.aws.region.static=SEU_REGIAO
cloud.aws.s3.bucket=SEU_BUCKET_NAME




Configurar o banco de dados (opcional):

Por padrão, o projeto usa o banco H2 em memória.
Para usar outro banco (ex.: PostgreSQL), edite o application.properties:spring.datasource.url=jdbc:postgresql://localhost:5432/eventostec
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update




Executar a aplicação:
mvn spring-boot:run

A aplicação estará disponível em http://localhost:8080.

Acessar a documentação da API:

Abra o Swagger em: http://localhost:8080/swagger-ui.html.



Endpoints Principais
Eventos

POST /api/eventos: Criar um novo evento com imagem.
Exemplo de payload (multipart/form-data):{
  "titulo": "Tech Summit 2025",
  "local": "São Paulo, SP",
  "data": "2025-06-15",
  "hora": "09:00",
  "descricao": "Evento de tecnologia"
}
File: (selecionar imagem, ex.: evento.jpg)




GET /api/eventos: Listar todos os eventos.
GET /api/eventos/{id}: Consultar um evento por ID.
PUT /api/eventos/{id}: Atualizar um evento.
DELETE /api/eventos/{id}: Excluir um evento.

Tickets

POST /api/eventos/{id}/tickets: Criar um ticket para um evento.
Exemplo de payload:{
  "usuarioId": 1,
  "expiracao": "2025-06-16T23:59:59"
}




GET /api/eventos/{id}/tickets: Listar tickets válidos de um evento.
GET /api/tickets/{id}: Consultar um ticket por ID.
DELETE /api/tickets/{id}: Excluir um ticket.

Consulte a documentação no Swagger para detalhes completos.
Estrutura do Projeto
eventostec/
├── src/
│   ├── main/
│   │   ├── java/com/example/eventostec/
│   │   │   ├── controller/    # Controladores REST
│   │   │   ├── entity/        # Entidades JPA (Evento, Ticket)
│   │   │   ├── repository/    # Repositórios Spring Data
│   │   │   ├── service/       # Lógica de negócio (inclui AWS S3 e expiração)
│   │   │   └── EventosTecApplication.java  # Classe principal
│   │   └── resources/
│   │       └── application.properties  # Configurações
├── pom.xml                    # Dependências Maven
└── README.md                  # Este arquivo

Como Testar

Inicie a aplicação com mvn spring-boot:run.
Use o Postman ou cURL para testar os endpoints. Exemplo:curl -X POST http://localhost:8080/api/eventos \
-H "Content-Type: multipart/form-data" \
-F "titulo=Tech Summit 2025" \
-F "local=São Paulo, SP" \
-F "data=2025-06-15" \
-F "hora=09:00" \
-F "descricao=Evento de tecnologia" \
-F "imagem=@/caminho/para/evento.jpg"


Verifique os dados no banco H2 (acesso via http://localhost:8080/h2-console, se habilitado) e as imagens no bucket S3.

Contribuindo

Faça um fork do repositório.
Crie uma branch (git checkout -b feature/sua-funcionalidade).
Commit suas alterações (git commit -m "Adiciona funcionalidade X").
Envie a branch (git push origin feature/sua-funcionalidade).
Abra um Pull Request.

Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.
Observações

Este projeto foi desenvolvido como parte de um desafio técnico e não está afiliado a nenhuma empresa.
O banco H2 é usado para testes, mas o projeto suporta bancos como PostgreSQL para produção.
As credenciais AWS devem ser protegidas e nunca versionadas.
A lógica de expiração dos tickets é implementada no backend, mas pode ser ajustada para jobs agendados (ex.: Spring Scheduler) para remoção automática.

