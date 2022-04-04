package com.schambeck.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(topics = "${invoice-created.topic}", bootstrapServersProperty = "kafka.bootstrap-servers")
class KafkaApplicationTests {

    @Test
    void contextLoads() {
    }

}
