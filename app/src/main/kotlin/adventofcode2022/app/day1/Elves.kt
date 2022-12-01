package adventofcode2022.app.day1

class Elves(private val elfs: List<Elf>) {
    fun mostCalories() = elfs.maxBy { it.totalCalories() }
}