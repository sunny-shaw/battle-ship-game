package component

import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import model.Action
import org.junit.jupiter.api.Test


class BattleAreaTest {
    private val length = 2
    private val breadth = 2

    @Test
    fun `should return a battle area of size 1 X 2 with initial cell value null representing not occupied`() {
        val length = 1
        val breadth = 2

        val battleArea = BattleArea(length, breadth)

        battleArea[0, 0] shouldBe null
        battleArea[0, 1] shouldBe null
    }

    @Test
    fun `should return true on placing the given ship in unoccupied battle area`() {
        val ship = Ship(length, breadth)
        val battleArea = BattleArea(4, 4)

        val isPlaced = battleArea.place(ship)

        isPlaced shouldBe true
        battleArea[0, 0]!!.javaClass shouldBe Ship::class.java
        battleArea[0, 0]!!.javaClass shouldBe Ship::class.java
        battleArea[0, 0]!!.javaClass shouldBe Ship::class.java
        battleArea[0, 0]!!.javaClass shouldBe Ship::class.java
    }

    @Test
    fun `should return false when placement of a ship not possible`() {
        val ship = Ship(length, breadth)
        val battleArea = BattleArea(1, 1)

        val isPlaced = battleArea.place(ship)

        isPlaced shouldBe false
    }

    @Test
    fun `should return action HIT if ship is present in given position`() {
        val ship = Ship(length, breadth)
        val battleArea = BattleArea(2, 2)
        battleArea.place(ship)

        val action = battleArea.hitOrMiss("A1")

        action shouldBe Action.HIT
    }

    @Test
    fun `should return action MISS if ship is not in given position`() {
        val battleArea = BattleArea(2, 3)

        val action = battleArea.hitOrMiss("B3")

        action shouldBe Action.MISS
    }

    @Test
    fun `should call reduce health method of ship component if ship is present for given position`() {
        val battleArea = BattleArea(2, 2)
        val ship = mockk<Ship> {
            every { length } returns 1
            every { breadth } returns 1
        }
        every { ship.reduceHealth() } returns mockk()
        battleArea.place(ship)

        battleArea.hitOrMiss("A1")

        verify(exactly = 1) { ship.reduceHealth() }
    }

    @Test
    fun `should not call reduce health method of ship component if ship is present for given position`() {
        val battleArea = BattleArea(2, 2)
        val ship = mockk<Ship>()
        every { ship.reduceHealth() } returns mockk()

        battleArea.hitOrMiss("A1")

        verify(exactly = 0) { ship.reduceHealth() }
    }

    @Test
    fun `should return true when all ships are destroyed`() {
        val ship = mockk<Ship> {
            every { length } returns 1
            every { breadth } returns 1
        }
        val battleArea = BattleArea(2, 1)
        battleArea.place(ship)
        battleArea.place(ship)
        every { ship.reduceHealth() } returns mockk()
        battleArea.hitOrMiss("A1")
        battleArea.hitOrMiss("B1")

        val areAllShipsDestroyed = battleArea.areAllShipsDestroyed()

        areAllShipsDestroyed shouldBe true
    }
}