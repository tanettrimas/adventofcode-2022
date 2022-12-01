package adventofcode2022.app.day1

class Elf (private val calories: List<Calory>) {
    init {
        require(calories.isNotEmpty())
    }
}

@JvmInline
value class Calory(private val value: Int)