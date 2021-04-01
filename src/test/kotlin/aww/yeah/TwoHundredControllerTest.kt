package aww.yeah

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpStatus
import java.util.*

@ExtendWith(MockitoExtension::class)
class TwoHundredControllerTest {
    private var twoHundredController: TwoHundredController? = null

    @Mock
    private val random: Random? = null
    @BeforeEach
    fun setUp() {
        twoHundredController = TwoHundredController(random!!)
    }

    @Test
    fun respondToAnything_shouldReturnJson_whenRandomIsNotZero() {
        Mockito.`when`(random!!.nextInt(ArgumentMatchers.anyInt())).thenReturn(1)
        val responseEntity = twoHundredController!!.respondToAnything()
        Assertions.assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(responseEntity.body.toString()).isEqualTo(Success().toString())
    }

    @Test
    @Throws(Exception::class)
    fun respondToAnything_shouldReturnGif_whenRandomIsZero() {
        Mockito.`when`(random!!.nextInt(ArgumentMatchers.anyInt())).thenReturn(0)
        val responseEntity = twoHundredController!!.respondToAnything()
        Assertions.assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat((responseEntity.body as InputStreamResource?)!!.inputStream)
                .hasSameContentAs(InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).inputStream)
    }
}
