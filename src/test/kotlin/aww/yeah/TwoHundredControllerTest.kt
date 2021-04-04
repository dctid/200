package aww.yeah

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.InputStreamSource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class TwoHundredControllerTest {

    @Test
    fun `respondToAnything() should return json when show gif is false`() {

        val responseEntity = TwoHundredController { false }.respondToAnything()

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.headers.contentType).isEqualTo(MediaType.APPLICATION_JSON)
        assertThat(responseEntity.body).isEqualTo(Success())
    }

    @Test
    fun `respondToAnything() should return gif when show gif is true`() {

        val responseEntity = TwoHundredController { true }.respondToAnything()

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.headers.contentType).isEqualTo(MediaType.IMAGE_GIF)
        assertThat((responseEntity.body as InputStreamSource).inputStream)
                .hasSameContentAs(
                        InputStreamResource(
                                ClassLoader.getSystemResourceAsStream("aww_yeah.gif")!!)
                                .inputStream)
    }

}
