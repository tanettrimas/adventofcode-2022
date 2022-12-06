package adventofcode2022.app.day4

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ElfPairTest {

    @Test
    fun containsAll() {
        assertTrue(ElfPair(2, 8).containsAll(ElfPair(3, 7)))
        assertFalse(ElfPair(2, 8).containsAll(ElfPair(1, 7)))
        assertTrue(ElfPair(6,6).containsAll(ElfPair(4,6)))
    }
}