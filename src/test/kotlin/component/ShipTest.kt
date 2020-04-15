package component

import component.Ship
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class ShipTest {
    private val length = 1
    private val breadth = 1

    @Test
    fun `should return a ship of size 1 X 1`() {
        val ship = Ship(length, breadth)

        ship.length shouldBe 1
        ship.breadth shouldBe 1
    }

    @Test
    fun `should initialise health value when ship is created`() {
        val ship = Ship(2, 3)

        ship.health() shouldBe 6
    }

    @Test
    fun `should reduce health with value 1 for specific position`() {
        val ship = Ship(length, breadth)

        ship.reduceHealth()

        ship.health() shouldBe 0
    }

    @Test
    fun `should return true if health is zero`() {
        val ship = Ship(1, 1)
        ship.reduceHealth()

        val isDestroyed = ship.isDestroyed()

        isDestroyed shouldBe true
    }
}