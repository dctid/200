package aww.yeah

import com.jayway.restassured.RestAssured
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TwoHundredControllerIntegrationTest {
    @LocalServerPort
    private val port = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null
    private val random = Random()

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @Test
    @Throws(Exception::class)
    fun old_twoHundredApplication_shouldRespondToRandomGetRequests_withStatusOk() {
        for (i in 0..19) {
            val response = RestAssured.given()[genRandomString()]
                    .thenReturn()
            Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK.value())
            if (MediaType.APPLICATION_JSON_VALUE == response.contentType) {
                Assertions.assertThat(response.body.asString()).isEqualTo("{\"success\":\"Aww Yeah\"}")
            } else {
                Assertions.assertThat(response.body.asInputStream())
                        .hasSameContentAs(InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).inputStream)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun twoHundredApplication_shouldRespondToRandomGetRequests_withStatusOk() {
        for (i in 0..19) {
            val response = restTemplate!!.getForEntity("http://localhost:" + port + "/" + genRandomString(),
                    ByteArray::class.java)
            Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
            if (MediaType.APPLICATION_JSON == response.headers.contentType) {
                Assertions.assertThat(String(response.body!!, StandardCharsets.UTF_8)).isEqualTo("{\"success\":\"Aww Yeah\"}")
            } else {
                Assertions.assertThat(ByteArrayInputStream(response.body))
                        .hasSameContentAs(InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).inputStream)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun twoHundredApplication_shouldRespondToRandomGetRequestsWithUriVars_withStatusOk() {
        for (i in 0..19) {
            val response = RestAssured.given()
                    .param(genRandomString(), genRandomString())
                    .param(genRandomString(), genRandomString())[genRandomString()]
                    .thenReturn()
            Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK.value())
            if (MediaType.APPLICATION_JSON_VALUE == response.contentType) {
                Assertions.assertThat(response.body.asString()).isEqualTo("{\"success\":\"Aww Yeah\"}")
            } else {
                Assertions.assertThat(response.body.asInputStream())
                        .hasSameContentAs(InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).inputStream)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun twoHundredApplication_shouldRespondToRandomPostWithUriVarRequests_withStatusOk() {
        for (i in 0..19) {
            val response = RestAssured.given()
                    .queryParam(genRandomString(), genRandomString())
                    .queryParam(genRandomString(), genRandomString())
                    .post(genRandomString())
                    .thenReturn()
            Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK.value())
            if (MediaType.APPLICATION_JSON_VALUE == response.contentType) {
                Assertions.assertThat(response.body.asString()).isEqualTo("{\"success\":\"Aww Yeah\"}")
            } else {
                Assertions.assertThat(response.body.asInputStream())
                        .hasSameContentAs(InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).inputStream)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun twoHundredApplication_shouldRespondToRandomPostWithFormDataRequests_withStatusOk() {
        for (i in 0..19) {
            val response = RestAssured.given()
                    .formParam(genRandomString(), genRandomString())
                    .formParam(genRandomString(), genRandomString())
                    .post(genRandomString())
                    .thenReturn()
            Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK.value())
            if (MediaType.APPLICATION_JSON_VALUE == response.contentType) {
                Assertions.assertThat(response.body.asString()).isEqualTo("{\"success\":\"Aww Yeah\"}")
            } else {
                Assertions.assertThat(response.body.asInputStream())
                        .hasSameContentAs(InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).inputStream)
            }
        }
    }

    private fun genRandomString(): String {
        val builder = StringBuilder()
        for (i in 0 until random.nextInt(15) + 5) {
            builder.append(chars[random.nextInt(chars.size)])
        }
        return builder.toString()
    }


    private val chars = IntStream.rangeClosed('A'.toInt(), 'Z'.toInt())
            .mapToObj { c: Int -> "" + (c + 32).toChar() + c.toChar() }.collect(Collectors.joining()).toCharArray()
}
