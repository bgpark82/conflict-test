package com.bgpark.kotlinspringmysqldemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringMysqlDemoApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringMysqlDemoApplication>(*args)
    println("3")
}
