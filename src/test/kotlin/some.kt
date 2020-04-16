import io.kotlintest.shouldBe
//import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.util.Scanner

class SomeTest {

    @Test
    @Disabled
    fun `should ask user input for battle area size`() {
        val inputReader = mockk<Scanner>()
        every { inputReader.nextInt() } returns 2 andThen 3

        inputReader.nextInt() shouldBe 2
        inputReader.nextInt() shouldBe 3

        verify(exactly = 2) { inputReader.nextInt() }

        //confirmVerified(inputReader)
    }
}