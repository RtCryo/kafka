package com.example.consumer.service

import com.example.consumer.config.EXAMPLE_TOPIC_NAME
import com.example.consumer.config.EXAMPLE_TOPIC_NAME_THREE
import com.example.consumer.config.EXAMPLE_TOPIC_NAME_TWO
import com.example.consumer.config.GROUP_ID
import com.example.consumer.model.EasyDto
import com.example.consumer.model.UserDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {

    private val log: Logger = LoggerFactory.getLogger(KafkaConsumer::class.java)

    @KafkaListener(topics = [EXAMPLE_TOPIC_NAME], groupId = GROUP_ID)
    fun listenerTask(msg: String) {
        log.info(msg)
    }

    @KafkaListener(topics = [EXAMPLE_TOPIC_NAME_TWO], groupId = GROUP_ID)
    fun listenerTask(msg: EasyDto) {
        log.info(msg.toString())
    }

    @KafkaListener(topics = [EXAMPLE_TOPIC_NAME_THREE], groupId = GROUP_ID)
    fun listenerTask(usr: UserDto) {
        log.info(usr.toString())
    }

}