APP = kafka
VERSION = 0.0.1-SNAPSHOT
JAR = target/${APP}-${VERSION}.jar
KAFKA_CONF = C:/kafka/config
AB = ab
AB_TIME = 1
AB_CONCURRENCY = 5
API_BASE_URL = http://localhost:8090
API_PAYLOAD = ${AB}/invoice-payload.json

# Spring

clean:
	./mvnw clean

all: clean
	./mvnw compile

install: clean
	./mvnw install

check: clean
	./mvnw verify

check-unit: clean
	./mvnw test

check-integration: clean
	./mvnw integration-test

dist: clean
	./mvnw package -Dmaven.test.skip=true

dist-run: dist run

run:
	java -jar ${JAR}

# Server

start-zookeeper:
	zookeeper-server-start.bat ${KAFKA_CONF}/zookeeper.properties

start-kafka:
	kafka-server-start.bat ${KAFKA_CONF}/server.properties

# Topic

create-topic:
	kafka-topics.bat \
		--create \
		--topic invoice-created \
		--bootstrap-server localhost:9092

describe-topic:
	kafka-topics.bat \
		--describe \
		--topic invoice-created \
		--bootstrap-server localhost:9092

delete-topic:
	kafka-topics.bat \
		--delete \
		--topic invoice-created \
		--bootstrap-server localhost:9092

list-topics:
	kafka-topics.bat \
		--list \
		--bootstrap-server localhost:9092

produce-topic:
	kafka-console-producer.bat \
		--topic invoice-created \
		--bootstrap-server localhost:9092

# Consumer Groups

describe-consumer-groups:
	kafka-consumer-groups.bat \
		--describe \
		--group invoice-created-group \
		--bootstrap-server localhost:9092

delete-consumer-groups:
	kafka-consumer-groups.bat \
		--delete \
		--group invoice-created-group \
		--bootstrap-server localhost:9092

list-consumer-groups:
	kafka-consumer-groups.bat \
		--list \
		--bootstrap-server localhost:9092

consume-topic:
	kafka-console-consumer.bat \
		--topic invoice-created \
		--from-beginning \
		--bootstrap-server localhost:9092

get-offsets:
	kafka-get-offsets.bat \
		--exclude-internal-topics \
		--bootstrap-server localhost:9092

# HTTP

publish-invoice:
	http --style=pie-dark -vv POST ${API_BASE_URL}/invoices \
		issued='2022-04-03' \
		total=1500

# AB

ab-publish-invoice:
	ab.exe -T application/json \
		-p ${API_PAYLOAD} \
		-t ${AB_TIME} \
		-c ${AB_CONCURRENCY} \
		${API_BASE_URL}/invoices > ${AB}/invoice-result-c${AB_CONCURRENCY}.txt

# Postgres

create-database:
	psql.exe -U postgres -c "CREATE DATABASE kafka"
