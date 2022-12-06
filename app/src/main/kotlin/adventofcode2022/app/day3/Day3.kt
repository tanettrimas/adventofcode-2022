package adventofcode2022.app.day3

import java.io.File

fun main() {
    task1()
}

fun task1() {
    val file = File({}.javaClass.getResource("/day3/input.txt")?.file ?: throw IllegalStateException())
    val priorities = file.useLines { it.map(::Rucksack).sumOf(Rucksack::priorities) }
    println(priorities)
}

fun task2() {

}

class Rucksack(input: String) {
    companion object {
        private val wordsScoreMap: Map<Char, Int> = createWordScoreMap()

        private fun createWordScoreMap(): Map<Char, Int> {
            val map = mutableMapOf<Char, Int>()
            ('a'..'z').forEachIndexed { index, letter ->
                map[letter] = (index + 1)
            }
            ('A'..'Z').forEachIndexed { index, letter ->
                map[letter] = (index + 1) + 26
            }
            return map
        }
    }

    private val parts: List<String>

    init {
        require(sanityCheck(input))
        val middle = input.length / 2
        parts = listOf(input.substring(0, middle), input.substring(middle))
    }

    fun priorities(): Int {
        val commonWords = parts.component1().split("").toSet().intersect(parts.component2().split("").toSet())
            .filter { it.isNotEmpty() }
        return commonWords.sumOf { wordsScoreMap[it.first()] ?: 0 }
    }

    private fun sanityCheck(input: String) = input.isNotEmpty() && input.all { it.isLetter() } && input.length % 2 == 0
}