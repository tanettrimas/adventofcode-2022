package adventofcode2022.app.day3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GroupedRucksackTest {
    @Test
    fun `grouped rucksack test`() {
        assertEquals(
            18, GroupedRucksack(
                """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
        """.trimIndent()
            ).priorities()
        )
        assertEquals(
            52, GroupedRucksack(
                """
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                """.trimIndent()
            ).priorities()
        )
    }
}