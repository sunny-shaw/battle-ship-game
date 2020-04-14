class BattleArea(val length: Int, val breadth: Int, val array: Array<Array<Int>>) {

    companion object {
        private const val VACANT = 0
        private const val OCCUPIED = 1

        operator fun invoke(length: Int, breadth: Int) =
            BattleArea(length, breadth, Array(length) { arrayOfZeroes(breadth) })

        private fun arrayOfZeroes(size: Int): Array<Int> {
            return Array(size) { 0 }
        }
    }

    operator fun get(coordinateX: Int, coordinateY: Int): Int {
        return array[coordinateX - 1][coordinateY - 1]
    }

    fun place(ship: Ship): Boolean {
        val shipLength = ship.length
        val shipBreadth = ship.breadth

            for (startIndex in 0 until array.size - shipLength + 1) {
                if (spaceAvailable(startIndex, shipLength)) {
                    occupySpace(startIndex, shipLength, shipBreadth)
                    println("Ship is placed successfully in the Battle Area!! The war is on...")
                    return true
                }
            }
        return false
    }

    private fun spaceAvailable(startIndex: Int, shipLength: Int): Boolean {
        return array.sliceArray(startIndex until startIndex + shipLength)
            .all { row ->
                row.sliceArray(0 until breadth).all { it == VACANT }
            }
    }

    private fun occupySpace(startIndex: Int, length: Int, breadth: Int) {
        array.sliceArray(startIndex until startIndex + length ).forEach { row ->
            row.fill(OCCUPIED, 0, breadth)
        }
    }
}
