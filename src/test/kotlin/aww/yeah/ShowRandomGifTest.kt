package aww.yeah

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.random.Random

internal class ShowRandomGifTest{

    @Test
    internal fun `ShowRandomGif() should return true when random is 0`() {
        val mockRandom = mock<Random> { on { nextInt(5)} doReturn 0 }

        val result = ShowRandomGif(mockRandom)()

        assertThat(result).isTrue
    }

    @Test
    internal fun `ShowRandomGif() should return false when random is 1 to 4`() {
        for(i in 1..4) {
            val mockRandom = mock<Random> { on { nextInt(5) } doReturn i }

            val result = ShowRandomGif(mockRandom)()

            assertThat(result).isFalse
        }
    }
}
