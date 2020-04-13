import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ShipTest {
    private val length = 1
    private val breadth = 1

    @Test
    fun `should return a ship of size 1 X 1`() {
        val ship = Ship(length, breadth)

        assertEquals(1, ship.length)
        assertEquals(1, ship.breadth)
    }

    @Test
    fun `should initialise health value for all positions when ship is created`() {
        val ship = Ship(2, 3)

        assertEquals(1, ship.healthFor(1,1))
        assertEquals(1, ship.healthFor(1,2))
        assertEquals(1, ship.healthFor(1,3))
        assertEquals(1, ship.healthFor(2,1))
        assertEquals(1, ship.healthFor(2,2))
        assertEquals(1, ship.healthFor(2,3))
    }

    @Test
    fun `should return health value for given position coordinates`() {
        val ship = Ship(length, breadth)

        assertEquals(1, ship.healthFor(1,1))
    }

    @Test
    fun `should reduce health with value 1 for specific position`() {
        val ship = Ship(length, breadth)

        ship.reduceHealthFor(1,1)

        assertEquals(0, ship.healthFor(1,1))
    }

    @Test
    fun `should return true if all positions health is zero`() {
        val ship = Ship(1, 1)
        ship.reduceHealthFor(1,1)

        val isDestroyed = ship.isDestroyed()

        assertEquals(isDestroyed, true)
    }
}