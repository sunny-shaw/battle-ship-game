package component

import model.Action

class BattleArea(private val length: Int, private val breadth: Int, private val array: Array<Array<Ship?>>) {

    companion object {
        private val VACANT = null

        operator fun invoke(length: Int, breadth: Int) =
            BattleArea(length, breadth, Array(length) { arrayOfNulls<Ship>(breadth) })
    }

    operator fun get(coordinateX: Int, coordinateY: Int): Ship? {
        return array[coordinateX][coordinateY]
    }

    fun place(ship: Ship): Boolean {
        val shipLength = ship.length

        for (startIndex in 0 until array.size - shipLength + 1) {
            if (spaceAvailable(startIndex, ship)) {
                occupySpace(startIndex, ship)
                println("Ship is placed successfully in the Battle Area!! The war is on...")
                return true
            }
        }
        return false
    }

    fun hitOrMiss(position: String): Action {
        val coordinates = coordinates(position)
        val ship = array[coordinates.first][coordinates.second]
        ship?.let { println("It's a HIT") } ?: println("It's a MISS")

        return ship?.let {
            ship.reduceHealth()
            removeShipFrom(coordinates)
            Action.HIT
        } ?: Action.MISS
    }

    fun areAllShipsDestroyed(): Boolean {
        return array.all { row ->
            row.all { it == VACANT }
        }
    }

    private fun spaceAvailable(startIndex: Int, ship: Ship): Boolean {
        return array.sliceArray(startIndex until startIndex + ship.length)
            .all { row ->
                row.sliceArray(0 until breadth).all { it == VACANT }
            }
    }

    private fun occupySpace(startIndex: Int, ship: Ship) {
        array.sliceArray(startIndex until startIndex + ship.length).forEach { row ->
            row.fill(ship, 0, ship.breadth)
        }
    }

    private fun removeShipFrom(coordinates: Pair<Int, Int>) {
        array[coordinates.first][coordinates.second] = null
    }

    private fun coordinates(position: String): Pair<Int, Int> {
        val positionList = position.toCharArray()
        require(positionList.size == 2) { "Enter a valid position !!" }

        return Pair(positionList.first() - 'A', positionList.last() - '1')
    }
}
