package controller

import component.BattleArea
import component.Player
import component.Ship
import java.io.BufferedReader

class GameController(private val inputReader: BufferedReader) {
    private var battleAreaLength = 0
    private var battleAreaBreadth = 0

    fun askBattleAreaSize() {
        println("Enter size of the battle area MxN?")
        battleAreaLength = inputReader.readInt()
        battleAreaBreadth = inputReader.readInt()
        println("Battle Area created of size $battleAreaLength X $battleAreaBreadth")
    }

    fun askShipInfo(): List<Ship> {
        val ships = mutableListOf<Ship>()
        println("Enter number of ships and their size in AxB")
        val numberOfShips = inputReader.readInt()
        for (each in 1..numberOfShips) {
            val shipLength = inputReader.readInt()
            val shipBreadth = inputReader.readInt()
            ships.add(Ship(shipLength, shipBreadth))
        }
        return ships
    }

    fun askPlayerToFireMissile(playerId: Int): String {
        println("Player$playerId - fire the missile")

        return inputReader.readLine()
    }

    fun initializeBattleArea(length: Int, breadth: Int): BattleArea {
        return BattleArea(length, breadth)
    }

    fun initializePlayer(id: Int, battleArea: BattleArea): Player {
        return Player(id, battleArea, inputReader)
    }
}

fun BufferedReader.readInt(): Int {
    return this.readLine().toInt()
}
