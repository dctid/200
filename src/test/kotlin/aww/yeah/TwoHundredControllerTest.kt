package aww.yeah

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpStatus
import java.util.*

@ExtendWith(MockitoExtension::class)
class TwoHundredControllerTest {

    @InjectMocks
    private lateinit var twoHundredController: TwoHundredController

    @Mock
    private lateinit var random: Random

    @Test
    fun `respondToAnything() should return json when random is from 1 to 4`() {
        for (i in 1..4) {
            `when`(random.nextInt(ArgumentMatchers.anyInt())).thenReturn(i)

            val responseEntity = twoHundredController.respondToAnything()

            assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
            assertThat(responseEntity.body.toString()).isEqualTo(Success().toString())
        }
    }

    @Test
    fun `respondToAnything() should return gif when random is 0`() {
        `when`(random.nextInt(ArgumentMatchers.anyInt())).thenReturn(0)

        val responseEntity = twoHundredController.respondToAnything()

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat((responseEntity.body as InputStreamResource?)!!.inputStream)
                .hasSameContentAs(InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")!!).inputStream)
    }
}
