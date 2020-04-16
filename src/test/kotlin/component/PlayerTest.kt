package component

import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import model.Action
import org.junit.jupiter.api.Test
import java.io.BufferedReader

class PlayerTest {
    private val inputReader = mockk<BufferedReader>()
    private val battleArea = mockk<BattleArea>()

    @Test
    fun `should create a player with provided id`() {
        val player = Player(1, mockk(), mockk())

        player.id shouldBe 1
    }

    @Test
    fun `should fire missile pointing any position`() {
        val player = Player(1, battleArea, inputReader)
        every { inputReader.readLine() } returns "A1"

        val position = player.fireMissile()

        position shouldBe "A1"
    }

    @Test
    fun `should confirm the missile attack as a HIT to target`() {
        val player = Player(1, battleArea, inputReader)
        every { battleArea.hitOrMiss("A1") } returns Action.HIT

        val action = player.confirmAttack("A1")

        action shouldBe Action.HIT
    }

    @Test
    fun `should confirm the missile attack as a MISS to target`() {
        val player = Player(1, battleArea, inputReader)
        every { battleArea.hitOrMiss("A2") } returns Action.MISS

        val action = player.confirmAttack("A2")

        action shouldBe Action.MISS
    }
}