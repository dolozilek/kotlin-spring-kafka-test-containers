package com.avast.kafka.test

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory


@Configuration
class KafkaTestContainersConfiguration {

    @Bean
    fun producerFactory() = DefaultKafkaProducerFactory<Any, Any>(
        mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to KafkaIntegrationTest.kafkaContainer.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java
        )
    )

    @Bean
    fun consumerFactory() = DefaultKafkaConsumerFactory<Any, Any>(
        mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to KafkaIntegrationTest.kafkaContainer.bootstrapServers,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
            ConsumerConfig.GROUP_ID_CONFIG to "test",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
        )
    )
}