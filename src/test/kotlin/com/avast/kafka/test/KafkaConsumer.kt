package com.avast.kafka.test

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch


@Component
class KafkaConsumer {

    private val logger = LoggerFactory.getLogger(KafkaConsumer::class.java)

    val latch = CountDownLatch(1)

    @KafkaListener(topics = ["test-topic"])
    fun receive(consumerRecord: ConsumerRecord<String, String>) {
        logger.info("received payload='{}'", consumerRecord)
        latch.countDown()
    }
}