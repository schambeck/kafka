# kafka
[![build](https://github.com/schambeck/kafka/actions/workflows/maven.yml/badge.svg)](https://github.com/schambeck/kafka/actions/workflows/maven.yml)

### Build artifact

    ./mvnw clean package

Executable file generated: target/kafka-0.0.1-SNAPSHOT.jar

### Execute application

    java -jar kafka-0.0.1-SNAPSHOT.jar

## REST API

The REST API to the Kafka app is described below.

### Publish Invoice created event

#### Request

`POST /invoices`

    curl -d '{"issued": "2022-04-02", "total": 1500.00}' -H "Content-Type: application/json" -X POST http://localhost:8090/invoices

#### Response

    Status: 202 Accepted

```json
{"id": null, "issued": "2022-04-02", "total": 1500.00}
```
