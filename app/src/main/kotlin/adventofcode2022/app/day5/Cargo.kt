package adventofcode2022.app.day5

enum class CargoType {
    Stack, KeepOrder
}


typealias Transformer = (list: List<Char>) -> List<Char>

class Cargo(crates: Map<Int, List<Char>>, cargoType: CargoType) {
    fun move(amount: Int, from: Int, to: Int) = crateHolder.move(amount, from, to)
    fun top(): String {
        return crateHolder.top()
    }

    private fun toTransformer(type: CargoType): Transformer = when(type) {
        CargoType.Stack ->  { list -> list.reversed() }
        CargoType.KeepOrder -> { list -> list }
    }

    private val crateHolder = CrateHolder(crates, toTransformer(cargoType))
}

private class CrateHolder(crates: Map<Int, List<Char>>, private val transformer: Transformer) {

    private inner class Crate(private val stack: MutableList<Char>) {
        fun remove(amount: Int): List<Char> {
            val times = amount.coerceAtMost(stack.size)
            val toBe = stack.takeLast(times)
            repeat(times) {
                stack.removeLastOrNull()
            }
            return toBe
        }

        fun add(cargoCratesToBeAdded: List<Char>) {
            stack.addAll(transformer(cargoCratesToBeAdded))
        }
        fun top(): Char? {
            return stack.lastOrNull()
        }
    }

    private val database: Map<Int, Crate> = initialize(crates)

    private fun initialize(crates: Map<Int, List<Char>>) = crates.toSortedMap().mapValues { (_, value) ->
        val stack = mutableListOf<Char>()
        value.reversed().forEach { stack.add(it) }
        Crate(stack)
    }

    fun move(amount: Int, from: Int, to: Int) {
        val sourceCrate =
            database[from] ?:
            throw IllegalArgumentException("Fant ingen crates å hente fra på reol $from")
        val destionationCrate =
            database[to] ?: throw IllegalArgumentException("Fant ingen crates å sende til på reol $to")
        val cargoCratesToBeAdded = sourceCrate.remove(amount)
        return destionationCrate.add(cargoCratesToBeAdded)
    }

    fun top() = database.values.mapNotNull { it.top() }.joinToString("")
}



