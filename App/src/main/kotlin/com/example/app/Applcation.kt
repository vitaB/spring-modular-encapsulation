package com.example.app

import com.example.moduleA.ConfigurationModuleA
import com.example.moduleB.ConfigurationModuleB
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(ConfigurationModuleA::class, ConfigurationModuleB::class)
class Applcation

fun main(args: Array<String>) {
    runApplication<Applcation>(*args)
}