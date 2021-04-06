package aww.yeah

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.InputStreamSource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random


data class Success(val success: String = "Aww Yeah")

private val gif = ClassPathResource("aww_yeah.gif")

@Service
class ShowRandomGif(private val random: Random = Random) : () -> Boolean {
    override fun invoke(): Boolean =
            random.nextInt(5) == 0
}

@RestController
class TwoHundredController(private val showRandomGif: () -> Boolean) : ErrorController {

    @RequestMapping("/error")
    fun respondToAnything(): ResponseEntity<*> =
            when (showRandomGif()) {
                true -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_GIF)
                        .body<InputStreamSource?>(InputStreamResource(gif.inputStream))
                false -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Success())
            }
}

