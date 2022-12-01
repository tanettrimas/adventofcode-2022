package adventofcode2022.app.day1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ElvesTest {
    @Test
    internal fun `most calories`() {
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

    @Test
    internal fun `top three elves`() {
        val one = Elf(7000, 8000, 9000)

        val two = Elf(5000, 6000)
        val three = Elf(10000)
        val elves = Elves(listOf(
            Elf(1000, 2000, 3000),
            Elf(4000),
            two,
            one,
            three
        ))
        assertEquals(45000, elves.topThreeSum())
    }
}