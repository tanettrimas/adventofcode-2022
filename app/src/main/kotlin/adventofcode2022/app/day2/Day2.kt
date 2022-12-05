package adventofcode2022.app.day2

import java.io.File

fun main() {
    task1()
    task2()
}

fun task1() {
    val scoreboard = Scoreboard();
    val file = File({}.javaClass.getResource("/day2/input.txt")?.file ?: throw IllegalStateException())
    file.forEachLine {
        val (opponent, player) = it.split(" ").map { s ->
            GameType.toGameType(s.first())
        }
        scoreboard.play(opponent, player)
    }
    println(scoreboard.playerScore())
}

fun task2() {

}
