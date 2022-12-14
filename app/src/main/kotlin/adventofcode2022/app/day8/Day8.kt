package adventofcode2022.app.day8

fun main() {
}

data class Position(val rowIndex: Int, val columnIndex: Int)

class Tree(val height: Int, val position: Position, private val gridLength: Int) {
    fun isOnEdge() =
        (position.rowIndex == 0 || position.rowIndex == gridLength - 1) || (position.columnIndex == 0 || position.columnIndex == gridLength - 1)

    fun left() = 0 until position.columnIndex
    fun right() = position.columnIndex + 1 until gridLength
    fun top() = 0 until position.rowIndex
    fun bottom() = position.rowIndex + 1 until gridLength
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

    private fun row(position: Position) = trees[position.rowIndex]
    private fun column(position: Position): List<Tree> {
        val columnList = mutableListOf<Tree>()
        trees.forEach {
            val singleTree = it[position.columnIndex]
            columnList.add(singleTree)
        }
        return columnList.toList()
    }

    fun visibleInterior(): Int {
        val interiorTrees = trees.flatten()
        return interiorTrees.count { tree ->
            if (tree.isOnEdge()) {
                return@count false
            }
            isVisible(tree)
        }
    }

    private fun isVisible(tree: Tree): Boolean {
        val row = row(tree.position)
        val column = column(tree.position)

        val left = row.slice(tree.left())
        val right = row.slice(tree.right())
        val top = column.slice(tree.top())
        val bottom = column.slice(tree.bottom())
        return (left.none { isTreeHigher(it, tree) }
                || right.none { isTreeHigher(it, tree) }
                || top.none { isTreeHigher(it, tree) }
                || bottom.none { isTreeHigher(it, tree) })
    }

    private fun isTreeHigher(rowTree: Tree, comparingTree: Tree) = rowTree.height >= comparingTree.height
}