package adventofcode2022.app.day3

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

internal class RucksackTest {
    @Test
    fun `fails when input is not dividable by two`() {
        assertThrows<IllegalArgumentException> {
            Rucksack("")
        }
        assertThrows<IllegalArgumentException> {
            Rucksack("a")
        }
        assertThrows<IllegalArgumentException> {
            Rucksack("aAb")
        }
    }

    @Test
    fun `priorities`() {
        assertEquals(16, Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp").priorities())
        assertEquals(38, Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL").priorities())
        assertEquals(42, Rucksack("PmmdzqPrVvPwwTWBwg").priorities())
        assertEquals(22, Rucksack("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn").priorities())
        assertEquals(20, Rucksack("ttgJtRGJQctTZtZT").priorities())
        assertEquals(19, Rucksack("CrZsJsPPZsGzwwsLwLmpwMDw").priorities())
    }
}