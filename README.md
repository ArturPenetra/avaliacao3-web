# oficina_api — Avaliação 3 (PRW3)

API REST (Spring Boot 4.1.1, Java 21, Maven) para o cadastro de consertos de uma oficina.
Banco **H2** (arquivo em `./DATA`), **Lombok**, **Flyway**.

## Como executar
Abrir no IntelliJ e rodar `OficinaApiApplication`, ou `mvn spring-boot:run`.
Console do H2: <http://localhost:8080/h2-console> — JDBC URL `jdbc:h2:file:./DATA/oficina`, usuário `sa`, senha vazia.
Exemplos de requisições prontos em [`requests.http`](requests.http).

## Modelo
`Conserto` (dataEntrada, dataSaida, ativo) com `Mecanico` (nome, anosExperiencia) e
`Veiculo` (marca, modelo, ano, cor) como `@Embedded`. Datas são `String`.

## Migrations (Flyway)
| Versão | O que faz |
|---|---|
| V1 | cria a tabela `consertos` |
| V2 | adiciona `veiculo_cor` (opcional) |
| V3 | adiciona `ativo` (exclusão lógica; registros existentes ficam `true`) |

## Endpoints
| Método | URL | Descrição | Respostas |
|---|---|---|---|
| POST | `/consertos` | cadastra um conserto | 201 + `Location` + corpo · 400 |
| GET | `/consertos` | todos os dados, **paginado** (`?page=&size=&sort=`) | 200 |
| GET | `/consertos/algunsdados` | id, datas, nome do mecânico, marca e modelo; **sem paginação**; só ativos | 200 |
| GET | `/consertos/{id}` | dados de um conserto | 200 · 404 |
| PUT | `/consertos` | altera data de saída, nome e anos de experiência do mecânico (id no corpo) | 200 + corpo · 400 · 404 |
| DELETE | `/consertos/{id}` | exclusão lógica (`ativo = false`) | 204 · 404 |

## Validações (POST)
- `dataEntrada` / `dataSaida`: formato `xx/xx/xxxx` (regex simples)
- Obrigatórios: `mecanico.nome`, `veiculo.marca`, `veiculo.modelo`, `veiculo.ano` (formato `xxxx`)
- Opcionais: datas, `mecanico.anosExperiencia`, `veiculo.cor`
