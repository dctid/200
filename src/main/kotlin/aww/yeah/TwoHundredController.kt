package aww.yeah

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.InputStreamSource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

data class Success(val success: String = "Aww Yeah")

private val gif = ClassPathResource("aww_yeah.gif")

private val defaultResponse: ResponseEntity<Success> =
        ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Success())

private fun gifResponse(): ResponseEntity<InputStreamSource> =
        ResponseEntity.ok()
                .contentType(MediaType.IMAGE_GIF)
                .body(InputStreamResource(gif.inputStream))

@RestController
class TwoHundredController(private val random: Random) : ErrorController {

    @RequestMapping("/error")
    fun respondToAnything(): ResponseEntity<*> =
            when (random.nextInt(5)) {
                0 -> gifResponse()
                else -> defaultResponse
            }
}

