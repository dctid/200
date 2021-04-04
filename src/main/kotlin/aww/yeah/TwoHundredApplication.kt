package aww.yeah

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import java.util.Random

@SpringBootApplication
class TwoHundredApplication

fun main(args: Array<String>) {
    runApplication<TwoHundredApplication>(*args)
}

@Service
class ShowRandomGif(private val random: Random = Random()): () -> Boolean {
    override fun invoke(): Boolean =
            random.nextInt(5) == 0

}
