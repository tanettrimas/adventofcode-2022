package adventofcode2022.app.day8

class TreeStructure(input: String) {
    companion object {
        private fun parseTreeStructure(input: String, lengthOfGrid: Int) = input
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
        val directions = getDirections(tree)
        return (directions.left.none { isTreeHigher(it, tree) }
                || directions.right.none { isTreeHigher(it, tree) }
                || directions.top.none { isTreeHigher(it, tree) }
                || directions.bottom.none { isTreeHigher(it, tree) })
    }

    private fun isTreeHigher(rowTree: Tree, comparingTree: Tree) = rowTree.height >= comparingTree.height

    fun scienicScore(): Int {
        val flattenedTrees = trees.flatten()
        val max = flattenedTrees.maxOf { tree ->
            val directions = getDirections(tree)
            val viewingTop = viewingDistance(tree, directions.top.reversed())
            val viewingBottom = viewingDistance(tree, directions.bottom)
            val viewingRight = viewingDistance(tree, directions.right)
            val viewingLeft = viewingDistance(tree, directions.left.reversed())
            viewingTop * viewingBottom * viewingLeft * viewingRight
        }
        return max
    }

    private fun getDirections(tree: Tree) =
        Directions(tree = tree, row = row(tree.position), column = column(tree.position))

    private fun viewingDistance(tree: Tree, list: List<Tree>): Int {
        var distance = 0
        run breaking@{
            list.forEach {
                if (it.height >= tree.height) {
                    distance++
                    return@breaking
                }
                distance++
            }
        }

        return distance
    }

    fun visible() = visibleEdges() + visibleInterior()
}

private class Directions(tree: Tree, row: List<Tree>, column: List<Tree>) {
    val left = row.slice(tree.left())
    val right = row.slice(tree.right())
    val top = column.slice(tree.top())
    val bottom = column.slice(tree.bottom())
}

private class Tree(val height: Int, val position: Position, private val gridLength: Int) {
    fun isOnEdge() =
        (position.rowIndex == 0 || position.rowIndex == gridLength - 1) || (position.columnIndex == 0 || position.columnIndex == gridLength - 1)

    fun left() = 0 until position.columnIndex
    fun right() = position.columnIndex + 1 until gridLength
    fun top() = 0 until position.rowIndex
    fun bottom() = position.rowIndex + 1 until gridLength
}

private data class Position(val rowIndex: Int, val columnIndex: Int)