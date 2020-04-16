import component.BattleArea
import component.Player
import component.Ship
import controller.GameController
import model.Action
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val inputReader = BufferedReader(InputStreamReader(System.`in`))
    val game = GameController(inputReader)

    val battleArea1 = game.initializeBattleArea(1)
    val battleArea2 = game.initializeBattleArea(2)

    val player1 = game.initializePlayer(1, battleArea1)
    val player2 = game.initializePlayer(2, battleArea2)

    val shipsForPlayer1 = game.initializeShip(player1.id)
    placeShip(shipsForPlayer1, battleArea1)

    val shipsForPlayer2 = game.initializeShip(player2.id)
    placeShip(shipsForPlayer2, battleArea2)

    var previousPlayer: Player?
    var currentPlayer = player1
    var nextPlayer = player2

    while (!battleArea1.areAllShipsDestroyed() && !battleArea2.areAllShipsDestroyed()) {
        val targetPosition = currentPlayer.fireMissile()

        if (nextPlayer.confirmAttack(targetPosition) == Action.MISS) {
            previousPlayer = currentPlayer
            currentPlayer = nextPlayer
            nextPlayer = previousPlayer
        }
    }
    println("HURRAY.... Player${currentPlayer.id} wins the game !! ")
}

private fun placeShip(shipsForPlayer1: List<Ship>, battleArea1: BattleArea) {
    shipsForPlayer1.forEach {
        battleArea1.place(it)
    }
    println("Ship is placed successfully in the Battle Area!! The war is on...")
}