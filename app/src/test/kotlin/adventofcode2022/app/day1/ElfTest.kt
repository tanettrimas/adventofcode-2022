package adventofcode2022.app.day1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class ElfTest {
    @Test
    internal fun `cannot have empty calories`() {
        assertThrows<IllegalArgumentException> {
            Elf(emptyList())
        }
    }

    @Test
    internal fun `can get total calories`() {
        assertEquals(6000, Elf(1000, 2000, 3000).totalCalories())
        assertEquals(4000, Elf(4000).totalCalories())
        assertEquals(11000, Elf(5000, 6000).totalCalories())
        assertEquals(24000, Elf(7000, 8000, 9000).totalCalories())
        assertEquals(10000, Elf(10000).totalCalories())
    }
}