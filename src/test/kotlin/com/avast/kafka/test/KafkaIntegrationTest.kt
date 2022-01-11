package com.avast.kafka.test

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.annotation.DirtiesContext
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.util.concurrent.TimeUnit


@SpringBootTest(classes = [KafkaProducerApplication::class])
@DirtiesContext
@Import(KafkaTestContainersConfiguration::class)
@Testcontainers
class KafkaIntegrationTest {

    companion object {
        @Container
        val kafkaContainer = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"))
    }

    @Autowired
    private lateinit var consumer: KafkaConsumer

    @Autowired
    private lateinit var producer: KafkaProducer

    @Test
    fun testKafka() {
        producer.send("test-topic", "Sending with own simple KafkaProducer")
        consumer.latch.await(10000, TimeUnit.MILLISECONDS)
        assertThat(consumer.latch.count, equalTo(0L))
    }
}