package adventofcode2022.app.day8

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


internal class TreeStructureTest {
    @Test
    fun `gives correct edge trees`() {
        val input = """
            30373
            25512
            65332
            33549
            35390
        """.trimMargin()
        val treeStructure = TreeStructure(input)
        assertEquals(16, treeStructure.visibleEdges())
    }

    @Test
    fun `gives correct visible interior`() {
        val input = """
            30373
            25512
            65332
            33549
            35390
        """.trimMargin()
        val treeStructure = TreeStructure(input)
        assertEquals(5, treeStructure.visibleInterior())
    }
}