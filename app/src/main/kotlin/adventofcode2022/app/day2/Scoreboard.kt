package adventofcode2022.app.day2

class Scoreboard {

    private val opponent = Player()
    private val player = Player()

    private inner class Player {

        var score: Int = 0
        fun increase(scoreToAdd: Int) {
            score += scoreToAdd
        }

        fun clear() {
            score = 0
        }
    }

    fun clear() {
        player.clear()
        opponent.clear()
    }

    fun play(opponentChosen: GameType, playerChosen: GameType) {
        val playergame = Game(playerChosen)
        val opponentGame = Game(opponentChosen)
        val score = playergame.score(opponentGame)
        player.increase(score.player1Score)
        opponent.increase(score.player2Score)
    }

    fun playerScore() = player.score
}

enum class ScoreResult(val score: Int) {
    Win(6), Draw(3), Lose(0)
}

enum class GameType(val score: Int) {
    Rock(1), Paper(2), Scissor(3);

    companion object {
        fun toGameType(char: Char) = when(char) {
            'A', 'X' -> Rock
            'B', 'Y' -> Paper
            'C', 'Z' -> Scissor
            else -> throw IllegalArgumentException("Invalid game type with char $char")
        }
    }
    fun beats(other: GameType) = when(this) {
        Rock -> other == Scissor
        Paper -> other == Rock
        Scissor -> other == Paper
    }
}

class Game(private val type: GameType) {
    private fun winner(other: Game) = Score(ScoreResult.Win.score + this.type.score, ScoreResult.Lose.score + other.type.score)
    private fun loser(other: Game) = Score(ScoreResult.Lose.score + this.type.score, ScoreResult.Lose.score + other.type.score)
    private fun draw(other: Game) =
        Score(ScoreResult.Draw.score + this.type.score, ScoreResult.Draw.score + other.type.score)

    fun score(other: Game): Score {
        if (this.type == other.type) {
            return draw(other)
        }
        if (this.type.beats(other.type)) {
            return winner(other)
        }
        return loser(other)
    }
}

data class Score(val player1Score: Int, val player2Score: Int)