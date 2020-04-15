class BattleArea(val length: Int, val breadth: Int, val array: Array<Array<Ship?>>) {

    companion object {
        private val VACANT = null

        operator fun invoke(length: Int, breadth: Int) =
            BattleArea(length, breadth, Array(length) { arrayOfNulls<Ship>(breadth) })
    }

    operator fun get(coordinateX: Int, coordinateY: Int): Ship? {
        return array[coordinateX - 1][coordinateY - 1]
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

    private fun spaceAvailable(startIndex: Int, ship: Ship): Boolean {
        return array.sliceArray(startIndex until startIndex + ship.length)
            .all { row ->
                row.sliceArray(0 until breadth).all { it == VACANT }
            }
    }

    private fun occupySpace(startIndex: Int, ship: Ship) {
        array.sliceArray(startIndex until startIndex + ship.length ).forEach { row ->
            row.fill(ship, 0, ship.breadth)
        }
    }
}
