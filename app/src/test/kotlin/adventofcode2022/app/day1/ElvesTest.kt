package adventofcode2022.app.day1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ElvesTest {
    @Test
    internal fun `sorted elfes`() {
        val wantedElf = Elf(7000, 8000, 9000)
        val elves = Elves(listOf(
            Elf(1000, 2000, 3000),
            Elf(4000),
            Elf(5000, 6000),
            wantedElf,
            Elf(10000)
        ))
        assertEquals(wantedElf, elves.mostCalories())
    }
}