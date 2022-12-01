package adventofcode2022.app.day1

class Elf(private val calories: List<Int>) {
    constructor(vararg calory: Int) : this(calory.toList())

    init {
        require(calories.isNotEmpty())
        require(calories.all { it > 0.0 })
    }

    fun totalCalories() = calories.sum()
}