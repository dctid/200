package aww.yeah

import com.jayway.restassured.RestAssured
import com.jayway.restassured.http.ContentType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Primary
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.*

@Service
@Primary
class TestShowRandomGif(var result: Boolean = false) : () -> Boolean {
    override fun invoke(): Boolean = result.also { println("****Two***") }
}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TwoHundredControllerIntegrationTest {
    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var showRandomGif: TestShowRandomGif

    private val randomStringGenerator: Random = Random()

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    private val awwYeahGif = ClassLoader.getSystemResourceAsStream("aww_yeah.gif")

    private val responseBody = """{"success":"Aww Yeah"}"""

    @Test
    fun `two hundred application should respond to random GET requests with status 200 Ok`() {
        showRandomGif.result = false

        val response = RestAssured.given()[genRandomString()]
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .contentType(ContentType.JSON)
                .and()
                .extract().response().asString()


        assertThat(response).isEqualTo(responseBody)
    }

    @Test
    fun `two hundred application should respond to random GET requests with status 200 Ok and GIF if random is 0`() {
        showRandomGif.result = true

        val response = RestAssured.given()[genRandomString()]
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .contentType("image/gif")
                .and()
                .extract().response().asInputStream()

        assertThat(response)
                .hasSameContentAs(InputStreamResource(awwYeahGif).inputStream)

    }

    @Test
    fun `two hundred application should respond to random GET requests that have random query params with status 200 Ok`() {
        showRandomGif.result = false

        val response = RestAssured.given()
                .param(genRandomString(), genRandomString())
                .param(genRandomString(), genRandomString())[genRandomString()]
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .contentType(ContentType.JSON)
                .and()
                .extract().response().asString()


        assertThat(response).isEqualTo(responseBody)
    }


    @Test
    fun `two hundred application should respond to random GET requests that have random query params with status 200 Ok and GIF if random is 0`() {
        showRandomGif.result = true

        val response = RestAssured.given()
                .queryParam(genRandomString(), genRandomString())
                .queryParam(genRandomString(), genRandomString())
                .post(genRandomString())
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .contentType("image/gif")
                .and()
                .extract().response().asInputStream()

        assertThat(response)
                .hasSameContentAs(InputStreamResource(awwYeahGif).inputStream)

    }

    @Test
    fun `two hundred application should respond to random POST requests that have random form params with status 200 Ok`() {
        showRandomGif.result = false

        val response = RestAssured.given()
                .formParam(genRandomString(), genRandomString())
                .formParam(genRandomString(), genRandomString())
                .post(genRandomString())
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .contentType(ContentType.JSON)
                .and()
                .extract().response().asString()

        assertThat(response).isEqualTo(responseBody)
    }

    @Test
    fun `two hundred application should respond to random POST requests that have random form params with status 200 Ok and GIF if random is 0`() {
        showRandomGif.result = true

        val response = RestAssured.given()
                .formParam(genRandomString(), genRandomString())
                .formParam(genRandomString(), genRandomString())
                .post(genRandomString())
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .contentType("image/gif")
                .and()
                .extract().response().asInputStream()

        assertThat(response)
                .hasSameContentAs(InputStreamResource(awwYeahGif).inputStream)
    }

    private fun genRandomString(): String =
            IntRange(0, randomStringGenerator.nextInt(15) + 5)
                    .map { chars[randomStringGenerator.nextInt(chars.size)] }
                    .joinToString(separator = "")
                    .let { URLEncoder.encode(it, StandardCharsets.UTF_8.toString()) }


    private val chars =
            CharRange('!', '~')
                    .joinToString(separator = "")
                    .toCharArray()
}
