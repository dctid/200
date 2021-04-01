package aww.yeah

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.util.*

@RestController
class TwoHundredController(private val random: Random) : ErrorController {
    @RequestMapping("/error")
    fun respondToAnything(): ResponseEntity<*> {
        if (random.nextInt(5) == 0) {
            try {
                val awwyeahGif: Resource = ClassPathResource("aww_yeah.gif")
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_GIF)
                        .body(InputStreamResource(awwyeahGif.inputStream))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Success())
    }

}

data class Success(val success : String = "Aww Yeah")
