package adventofcode2022.app.day4


class ElfPair(val start: Int, val end: Int) {
    private val range: IntRange;

    init {
        require(end >= start) {
            "Failed because $end is not greater than $start"
        }
        range = start..end
    }

    fun containsAll(other: ElfPair): Boolean {
        val rangeSet = range.toSet()
        val otherRangeset = other.range.toSet()
        return if (rangeSet.size >= otherRangeset.size) {
            rangeSet.containsAll(otherRangeset)
        } else {
            otherRangeset.containsAll(rangeSet)
        }
    }
}