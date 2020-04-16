package controller

import component.BattleArea
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.io.BufferedReader

class GameControllerTest {
    private val inputReader = mockk<BufferedReader>()
    private val game = GameController(inputReader)

    @Test
    fun `should initialize Battle Area`() {

        val battleArea = game.initializeBattleArea(1, 1)

        battleArea.javaClass shouldBe BattleArea::class.java
    }

    @Test
    fun `should initialize player`() {
        val battleArea = game.initializeBattleArea(1, 1)

        val player = game.initializePlayer(1, battleArea)

        player.id shouldBe 1
    }

    @Test
    fun `should ask user input for battle area size`() {
        every { inputReader.readLine() } returns "2" andThen "3"

        game.askBattleAreaSize()

        verify(exactly = 2) { inputReader.readLine() }
    }

    @Test
    fun `should ask user input for number of ships and their sizes`() {
        every { inputReader.readLine() } returns "2"
        every { inputReader.readLine() } returns "1" andThen "2"
        every { inputReader.readLine() } returns "2" andThen "2"

        game.askShipInfo()

        verify(exactly = 5) { inputReader.readLine() }
    }

    @Test
    fun `should return list of ship using ship info`() {
        every { inputReader.readLine() } returns "2"
        every { inputReader.readLine() } returns "1" andThen "2"
        every { inputReader.readLine() } returns "2" andThen "2"

        val ships = game.askShipInfo()

        ships.size shouldBe 2
        verify(exactly = 5) { inputReader.readLine() }
    }

    @Test
    fun `should ask player to fire missile`() {
        every { inputReader.readLine() } returns "A1"

        val positionToTarget = game.askPlayerToFireMissile(1)

        positionToTarget shouldBe "A1"
    }
}