package com.example.producer.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

/** Thread pool for task's scheduler or for spring boot spring.task.scheduling.pool.size=
@EnableScheduling
@EnableAsync
@Configuration
class SchedulerConfig : SchedulingConfigurer {
override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
val threadPoolTaskScheduler = ThreadPoolTaskScheduler()

threadPoolTaskScheduler.poolSize = 10
threadPoolTaskScheduler.setThreadNamePrefix("my-scheduler-task-pool-")
threadPoolTaskScheduler.initialize()

taskRegistrar.setTaskScheduler(threadPoolTaskScheduler)
}
}*/

@EnableScheduling
@EnableAsync
@Configuration
class SchedulerConfig {

    @Bean(name = ["threadPoolTaskExecutor"])
    fun threadPoolTaskExecutor(): Executor {                                    //Thread pool for schedulers
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 4
        executor.maxPoolSize = 100
        executor.queueCapacity = 100
        executor.setThreadNamePrefix("threadPoolTaskExecutor-")
        executor.initialize()
        return executor
    }
}
