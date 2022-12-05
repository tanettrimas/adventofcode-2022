package adventofcode2022.app.day2

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ScoreboardTest {

    private lateinit var scoreboard: Scoreboard

    @BeforeEach
    fun setUp() {
        scoreboard = Scoreboard()
    }

    @Test
    fun `oppgave testcase`() {
        scoreboard.play(GameType.Rock, GameType.Paper)
        scoreboard.play(GameType.Paper, GameType.Rock)
        scoreboard.play(GameType.Scissor, GameType.Scissor)
        assertEquals(15, scoreboard.playerScore())
    }

    @Test
    fun `wins game`() {
        scoreboard.play(GameType.Rock, GameType.Paper)
        assertEquals(8, scoreboard.playerScore())
        scoreboard.clear()

        scoreboard.play(GameType.Scissor, GameType.Rock)
        assertEquals(7, scoreboard.playerScore())
        scoreboard.clear()

        scoreboard.play(GameType.Paper, GameType.Scissor)
        assertEquals(9, scoreboard.playerScore())
    }
}