package adventofcode2022.app.day8

fun main() {
}

data class Position(val rowIndex: Int, val columnIndex: Int)

class Tree(private val height: Int, private val position: Position, private val gridLength: Int) {
    fun isOnEdge() =
        (position.rowIndex == 0 || position.rowIndex == gridLength - 1) || (position.columnIndex == 0 || position.columnIndex == gridLength - 1)
}

class TreeStructure(input: String) {
    companion object {
        fun parseTreeStructure(input: String, lengthOfGrid: Int) = input
            .split("\n")
            .mapIndexed { rowIndex, line
                ->
                line
                    .split("")
                    .filter { it.isNotBlank() }
                    .mapIndexed { columnIndex, s ->
                        Tree(
                            s.toInt(),
                            Position(rowIndex, columnIndex),
                            gridLength = lengthOfGrid
                        )
                    }
            }
    }

    private val lengthOfGrid = input.split("\n").first().trim().length
    private val trees: List<List<Tree>> = parseTreeStructure(input, lengthOfGrid)

    fun visibleEdges() = trees.flatten().count(Tree::isOnEdge)
}