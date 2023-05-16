package com.example.producer.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaAdminConfig

const val EXAMPLE_TOPIC_NAME = "someTopicOne"
const val EXAMPLE_TOPIC_NAME_TWO = "someTopicTwo"
const val EXAMPLE_TOPIC_NAME_THREE = "someTopicThree"

@Bean
fun topic() = NewTopic(EXAMPLE_TOPIC_NAME, 10, 1)

@Bean
fun topic2() = NewTopic(EXAMPLE_TOPIC_NAME_TWO, 4, 1)

@Bean
fun topic3() = NewTopic(EXAMPLE_TOPIC_NAME_THREE, 4, 1)