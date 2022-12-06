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
    Win(6), Draw(3), Lose(0);

    companion object {
        fun from(char: Char) = when(char) {
            'X' -> Lose
            'Y' -> Draw
            'Z' -> Win
            else -> throw IllegalArgumentException("Cannot compute scoreresult from char $char")
        }
    }

    fun toGameOption(option: GameOption) = when(this) {
        Win -> GameOption.from(option.loses())
        Draw -> option
        Lose -> GameOption.from(option.beats())
    }
}

sealed class GameOption(val type: GameType) {
    companion object {
        fun from(gameType: GameType) = when(gameType) {
            GameType.Rock -> Rock()
            GameType.Paper -> Paper()
            GameType.Scissor -> Scissor()
        }
    }

    fun beats(other: GameType) = other == beats()
    abstract fun beats(): GameType
    abstract fun loses(): GameType
}

class Rock: GameOption(GameType.Rock) {
    override fun beats() = GameType.Scissor
    override fun loses() = GameType.Paper
}

class Paper: GameOption(GameType.Paper) {
    override fun beats() = GameType.Rock
    override fun loses() = GameType.Scissor
}

class Scissor: GameOption(GameType.Scissor) {
    override fun beats() = GameType.Paper
    override fun loses() = GameType.Rock
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

    fun toGameOption() = GameOption.from(this)
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
        if (this.type.toGameOption().beats(other.type)) {
            return winner(other)
        }
        return loser(other)
    }
}

data class Score(val player1Score: Int, val player2Score: Int)