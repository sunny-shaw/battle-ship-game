class Ship(val length: Int, val breadth: Int) {
    private var healthMeter: Array<Array<Int>>

    init {
        healthMeter = Array(length) { Array(breadth) { INITIAL_HEALTH } }
    }

    fun reduceHealthFor(coordinateX: Int, coordinateY: Int) {
        healthMeter[coordinateX - 1][coordinateY - 1]--
    }

    fun isDestroyed(): Boolean = healthMeter.all {
        areAllPositionsAttacked(it)
    }

    fun healthFor(coordinateX: Int, coordinateY: Int): Int {
        return healthMeter[coordinateX - 1][coordinateY - 1]
    }

    private fun areAllPositionsAttacked(positionArray: Array<Int>) = positionArray.all { it == NO_HEALTH }

    companion object {
        private const val INITIAL_HEALTH = 1
        private const val NO_HEALTH = 0
    }
}
