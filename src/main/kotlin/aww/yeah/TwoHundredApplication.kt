package aww.yeah

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Random

@SpringBootApplication
class TwoHundredApplication

fun main(args: Array<String>) {
    runApplication<TwoHundredApplication>(*args)
}


@Configuration
class TwoHundredConfig {
    @Bean
    fun random(): Random
        = Random()
}
