package com.cardlay.techinterview.cardmanagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlin.jvm.JvmStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CardmanagementApplication

fun main(args: Array<String>) {
    SpringApplication.run(CardmanagementApplication::class.java, *args)
}
