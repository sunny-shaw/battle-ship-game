import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test


class BattleAreaTest {
    @Test
    fun `should return a battle area of size 1 X 2 with initial cell value 0 representing not occupied`() {
        val length = 1
        val breadth = 2

        val battleArea = BattleArea(length, breadth)

        battleArea[1,1] shouldBe 0
        battleArea[1,2] shouldBe 0
    }

    @Test
    fun `should return true on placing the given ship in unoccupied battle area`() {
        val length = 2
        val breadth  = 2
        val ship = Ship(length, breadth)
        val battleArea = BattleArea(4, 4)

        val isPlaced = battleArea.place(ship)

        isPlaced shouldBe true
        battleArea[1,1] shouldBe 1
        battleArea[1,2] shouldBe 1
        battleArea[2,1] shouldBe 1
        battleArea[2,2] shouldBe 1
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