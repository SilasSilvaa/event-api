# API de Gerenciamento de eventos

API RESTful para gerenciamento de eventos, construída com Spring Boot, JPA, PostgreSQL e Swagger.

## Dependências
- `spring-boot-starter-data-jpa`: Para integração com o banco de dados usando JPA.
- `spring-boot-starter-web`: Para criação de endpoints RESTful.
- `postgresql`: Banco de dados.
- `flyway-core`: Para gerenciamento de migrações do banco de dados.
- `spring-boot-starter-validation`: Para validação de entradas.
- `springdoc-openapi-starter-webmvc-ui`: Para geração de documentação Swagger.





## Instruções para rodar o projeto
A aplicação conta com um arquivo `docker-compose.yml` para configurar o banco de dados PostgreSQL.

1. Clone o repositório.
2. Navegue até o diretório do projeto.
3. Execute `docker-compose up` localizado na pasta `docker` na raiz do projeto para iniciar o PostgreSQL.
4. Execute a aplicação com `mvn spring-boot:run`.
5. Acesse a  API via Swagger em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Funcionalidades

### Endpoints da API

### `GET /api/event`
Retorna uma lista de eventos com os seguintes detalhes:
```json
[
  {
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "title": "string",
    "description": "string",
    "date": "2025-02-23T20:01:43.499Z",
    "city": "string",
    "state": "string",
    "remote": true,
    "eventUrl": "string",
    "imageUrl": "string"
  }
]
```

### `POST /api/event`
Cria um novo evento e retorna os dados do evento criado:
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "description": "string",
  "imageUrl": "string",
  "eventUrl": "string",
  "remote": true,
  "date": "2025-02-23T20:02:16.320Z",
  "address": {
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "city": "string",
    "uf": "string",
    "event": "string"
  }
}
```

## `GET /api/event/{id}`
Retorna detalhes de um evento específico:
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "description": "string",
  "date": "2025-02-23T20:02:54.679Z",
  "city": "string",
  "uf": "string",
  "imageUrl": "string",
  "eventUrl": "string",
  "coupons": [
    {
      "code": "string",
      "discount": 1073741824,
      "validUntil": "2025-02-23T20:02:54.679Z"
    }
  ]
}
```


## `DELETE /api/event/{id}`
Deleta um evento pelo seu ID e retorna os dados do evento excluído:
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "title": "string",
  "description": "string",
  "imageUrl": "string",
  "eventUrl": "string",
  "remote": true,
  "date": "2025-02-23T20:03:25.284Z",
  "address": {
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "city": "string",
    "uf": "string",
    "event": "string"
  }
}
```
## `GET /api/event/filter`
Filtra os eventos com base nos seguintes parâmetros:

- page (opcional, padrão 0): Número da página.
- size (opcional, padrão 10): Tamanho da página.
- title (opcional): Título do evento.
- city (opcional): Cidade do evento.
- uf (opcional): UF do evento.
- startDate (opcional): Data de início.
- endDate (opcional): Data de fim.

Retorna os enventos com os filtros aplicados.

## `POST /api/coupon`

Esta rota permite criar um novo cupom com um código de desconto.

```json
{
  "code": "string",
  "discount": 1073741824,
  "valid": 9007199254740991,
  "envent": {
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "title": "string",
    "description": "string",
    "imageUrl": "string",
    "eventUrl": "string",
    "remote": true,
    "date": "2025-02-23T20:03:25.284Z",
      "address": {
        "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "city": "string",
        "uf": "string",
        "event": "string"
      }
  }
}
```

Retorno:

```json
{
  "code": "string",
  "discount": 1073741824,
  "valid": 9007199254740991,
  "envent": {
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "title": "string",
    "description": "string",
    "imageUrl": "string",
    "eventUrl": "string",
    "remote": true,
    "date": "2025-02-23T20:03:25.284Z",
      "address": {
        "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "city": "string",
        "uf": "string",
        "event": "string"
      }
  }
}
```
