package adventofcode2022.app.day5

import java.util.Stack



class Cargo(crates: Map<Int, List<Char>>) {
    fun move(amount: Int, from: Int, to: Int) = crateHolder.move(amount, from, to)
    fun top(): String {
        return crateHolder.top()
    }

    private val crateHolder = CrateHolder(crates)
}

private class CrateHolder(crates: Map<Int, List<Char>>) {
    private val database: Map<Int, Crate> = initialize(crates)

    private fun initialize(crates: Map<Int, List<Char>>) = crates.toSortedMap().mapValues { (_, value) ->
        val stack: Stack<Char> = Stack()
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


private class Crate(private val stack: Stack<Char>) {
    fun remove(amount: Int): Crate {
        val times = amount.coerceAtMost(stack.size)
        val charStack = Stack<Char>()
        repeat(times) {
            charStack.add(stack.pop())
        }
        return Crate(charStack)
    }

    fun add(cargoCratesToBeAdded: Crate) {
        cargoCratesToBeAdded.stack.forEach { stack.push(it) }
    }
    fun top(): Char? {
        return stack.peek()
    }
}
