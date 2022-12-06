package adventofcode2022.app.day3

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

internal class SingleRucksackTest {
    @Test
    fun `fails when input is not dividable by two`() {
        assertThrows<IllegalArgumentException> {
            SingleRucksack("")
        }
        assertThrows<IllegalArgumentException> {
            SingleRucksack("a")
        }
        assertThrows<IllegalArgumentException> {
            SingleRucksack("aAb")
        }
    }

    @Test
    fun `priorities`() {
        assertEquals(16, SingleRucksack("vJrwpWtwJgWrhcsFMMfFFhFp").priorities())
        assertEquals(38, SingleRucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL").priorities())
        assertEquals(42, SingleRucksack("PmmdzqPrVvPwwTWBwg").priorities())
        assertEquals(22, SingleRucksack("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn").priorities())
        assertEquals(20, SingleRucksack("ttgJtRGJQctTZtZT").priorities())
        assertEquals(19, SingleRucksack("CrZsJsPPZsGzwwsLwLmpwMDw").priorities())
    }
}