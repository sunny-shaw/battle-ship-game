package component

class Ship(val length: Int, val breadth: Int) {
    private var health: Int

    init {
        health = length * breadth * HEALTH_FACTOR
    }

    fun reduceHealth() = health--

    fun isDestroyed(): Boolean = health == 0

    fun health(): Int {
        return health
    }

    companion object {
        private const val HEALTH_FACTOR = 1
    }
}
