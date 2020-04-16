package controller

import component.BattleArea
import component.Player
import component.Ship
import java.io.BufferedReader

class GameController(private val inputReader: BufferedReader) {

    fun initializeBattleArea(playerId: Int): BattleArea {
        println("Enter size of the battle area MxN for Player$playerId")
        val (length, breadth) = inputReader.readLine().split(" ")

        return BattleArea(breadth.toInt(), length.toInt())
    }

    fun initializeShip(playerId: Int): List<Ship> {
        val ships = mutableListOf<Ship>()
        println("Enter number of ships and their size in AxB for Player$playerId")
        val numberOfShips = inputReader.readLine().toInt()
        for (each in 1..numberOfShips) {
            val (length, breadth) = inputReader.readLine().split(" ")

            ships.add(Ship(length.toInt(), breadth.toInt()))
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