package com.example.producer.service

import com.example.producer.config.EXAMPLE_TOPIC_NAME
import com.example.producer.config.EXAMPLE_TOPIC_NAME_THREE
import com.example.producer.config.EXAMPLE_TOPIC_NAME_TWO
import com.example.producer.model.EasyDto
import com.example.producer.model.UserDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class MyTaskScheduler(private val template: KafkaTemplate<String, String>,
                      private val easyTemplate: KafkaTemplate<String, EasyDto>,
                      private val userTemplate: KafkaTemplate<String, UserDto>) {

    private val log: Logger = LoggerFactory.getLogger(MyTaskScheduler::class.java)
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")

    @Async("threadPoolTaskExecutor")
    @Scheduled(fixedDelay = 500, initialDelay = 1000)
    fun task() {
        val msg: String = "task:%s: %s".format(Thread.currentThread().name, dateFormat.format(Date()))
        log.info(msg)
        template.send(EXAMPLE_TOPIC_NAME, msg)
        Thread.sleep(10000)
    }

    @Scheduled(fixedDelay = 200, initialDelay = 1000)
    fun anotherTask() {
        val msg: String = "easyTask:%s: %s".format(Thread.currentThread().name, dateFormat.format(Date()))
        log.info(msg)
        easyTemplate.send(EXAMPLE_TOPIC_NAME_TWO, EasyDto(msg))
        Thread.sleep(10000)
    }

    @Scheduled(fixedDelay = 10000)
    fun userTask() {
        val user = UserDto(UUID.randomUUID().toString(), "Freddy")
        log.info("user task: %s".format(Thread.currentThread().name))
        userTemplate.send(EXAMPLE_TOPIC_NAME_THREE, user)
    }

}