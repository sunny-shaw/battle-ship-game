package component

import model.Action
import java.io.BufferedReader

class Player(
    val id: Int,
    private val battleArea: BattleArea,
    private val inputReader: BufferedReader
) {
    fun fireMissile(): String {
        return inputReader.readLine()
    }

    fun confirmAttack(position: String): Action {
        return battleArea.hitOrMiss(position)
    }

}
