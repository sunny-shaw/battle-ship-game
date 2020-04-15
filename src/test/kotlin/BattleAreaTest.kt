import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test


class BattleAreaTest {
    @Test
    fun `should return a battle area of size 1 X 2 with initial cell value null representing not occupied`() {
        val length = 1
        val breadth = 2

        val battleArea = BattleArea(length, breadth)

        battleArea[1,1] shouldBe null
        battleArea[1,2] shouldBe null
    }

    @Test
    fun `should return true on placing the given ship in unoccupied battle area`() {
        val length = 2
        val breadth  = 2
        val ship = Ship(length, breadth)
        val battleArea = BattleArea(4, 4)

        val isPlaced = battleArea.place(ship)

        isPlaced shouldBe true
        battleArea[1,1]!!.javaClass shouldBe Ship::class.java
        battleArea[1,2]!!.javaClass shouldBe Ship::class.java
        battleArea[2,1]!!.javaClass shouldBe Ship::class.java
        battleArea[2,2]!!.javaClass shouldBe Ship::class.java
    }

    @Test
    fun `should return false when placement of a ship not possible`() {
        val length = 2
        val breadth  = 2
        val ship = Ship(length, breadth)
        val battleArea = BattleArea(1, 1)

        val isPlaced = battleArea.place(ship)

        isPlaced shouldBe false
    }
}