package adventofcode2022.app.day1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ElfTest {
    @Test
    internal fun `cannot have empty calories`() {
        assertThrows<IllegalArgumentException> {
            Elf(emptyList())
        }
    }
}