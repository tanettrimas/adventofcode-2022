package adventofcode2022.app.day1

class Elves(private val elfs: List<Elf>) {
    fun mostCalories() = elfs.maxBy { it.totalCalories() }
    fun topThreeSum() = elfs
        .sortedByDescending(Elf::totalCalories)
        .slice(0..2)
        .sumOf(Elf::totalCalories)
}