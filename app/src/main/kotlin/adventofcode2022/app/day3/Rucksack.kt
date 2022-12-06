package adventofcode2022.app.day3

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

sealed class Rucksack {

    protected abstract fun getCommonWord(): Char
    fun priorities() = wordsScoreMap[getCommonWord()] ?: 0
}

class SingleRucksack(input: String) : Rucksack() {
    private val parts: List<String>

    init {
        require(sanityCheck(input))
        val middle = input.length / 2
        parts = listOf(input.substring(0, middle), input.substring(middle))
    }

    override fun getCommonWord() =
        parts.component1().split("").toSet().intersect(parts.component2().split("").toSet()).first { it.isNotEmpty() }
            .first()

    private fun sanityCheck(input: String) = input.isNotEmpty() && input.all { it.isLetter() } && input.length % 2 == 0
}

class GroupedRucksack(input: String) : Rucksack() {

    private val parts: List<String>

    init {
        val splittedInput = input.split("\n")
        require(splittedInput.size == 3 && splittedInput.all { it.isNotEmpty() })
        parts = splittedInput
    }

    override fun getCommonWord(): Char {
        val (one, two, three) = parts
        val result = one.split("") intersect two.split("").toSet() intersect three.split("").toSet()
        return result.first { it.isNotEmpty() }.first()
    }

}

