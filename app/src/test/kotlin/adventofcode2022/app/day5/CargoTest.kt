package adventofcode2022.app.day5

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CargoTest {
    @Test
    fun move() {
        val cargo = Cargo(
            mapOf(
                1 to listOf('A', 'B', 'C'),
                2 to listOf('D', 'E', 'F')
            )
        )
        cargo.move(amount = 1, from = 2, to = 1)
        assertEquals("DE", cargo.top())
    }

    @Test
    fun `base test`() {
        val cargo = Cargo(
            mapOf(
                1 to listOf('N', 'Z'),
                2 to listOf('D', 'C', 'M'),
                3 to listOf('P')
            )
        )
        cargo.move(amount = 1, from = 2, to = 1)
        cargo.move(amount = 3, from = 1, to = 3)
        cargo.move(amount = 2, from = 2, to = 1)
        cargo.move(amount = 1, from = 1, to = 2)
        assertEquals("CMZ", cargo.top())
    }
}