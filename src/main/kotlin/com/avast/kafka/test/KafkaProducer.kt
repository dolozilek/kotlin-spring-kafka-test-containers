package com.avast.kafka.test

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    private val logger = LoggerFactory.getLogger(KafkaProducer::class.java)

    fun send(topic: String, payload: String) {
        logger.info("sending payload='{}' to topic='{}'", payload, topic)
        kafkaTemplate.send(topic, payload)
    }


}