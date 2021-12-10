package day09

import day09.Direction.*
import java.io.File

fun main() {
    val array = File("src/main/kotlin/day09/input.txt")
            .readLines()
            .map {
                it.toCharArray()
            }.toMutableList()
    val lowPoints = mutableListOf<Coord>()
    for (y in array.indices) {
        for (x in array[0].indices) {
            val location = array[y][x]
            if ((x == 0 || array[y][x - 1] > location)
                    && (x == array[y].size - 1 || array[y][x + 1] > location)
                    && (y == 0 || array[y - 1][x] > location)
                    && (y == array.size - 1 || array[y + 1][x] > location)) {
                lowPoints.add(Coord(y, x))
            }
        }
    }

    val basins = List(array.size) { Array(array[0].size) { -1 } }

    lowPoints.forEachIndexed { index, lowPoint ->
        basins[lowPoint.y][lowPoint.x] = index
        runThrough(lowPoint, LEFT, true, array, basins, index)
        runThrough(lowPoint, RIGHT, true, array, basins, index)
        runThrough(lowPoint, UP, true, array, basins, index)
        runThrough(lowPoint, DOWN, true, array, basins, index)
    }

    val total = MutableList(lowPoints.size) { 0 }
    for (y in basins.indices) {
        for (x in basins[0].indices) {
            basins[y][x].takeIf { it != -1 }?.let {
                total[it] += 1
            }
            print(basins[y][x] + 1)
        }
        println()
    }
    total.sort()
    println(total[total.size - 1] * total[total.size - 2] * total[total.size - 3])
}

private fun runThrough(
        start: Coord, direction: Direction, turn: Boolean, array: MutableList<CharArray>,
        basins: List<Array<Int>>,
        index: Int,
) {
    when (direction) {
        RIGHT -> {
            val position = start.copy(x = start.x + 1)
            while (position.x < array[0].size && array[position.y][position.x] != '9') {
                basins[position.y][position.x] = index
                if (turn) {
                    runThrough(position, UP, false, array, basins, index)
                    runThrough(position, DOWN, false, array, basins, index)
                }
                basins[position.y][position.x] = index
                position.x++
            }
        }
        DOWN -> {
            val position = start.copy(y = start.y + 1)
            while (position.y < array.size && array[position.y][position.x] != '9') {
                basins[position.y][position.x] = index
                if (turn) {
                    runThrough(position, LEFT, false, array, basins, index)
                    runThrough(position, RIGHT, false, array, basins, index)
                }
                basins[position.y][position.x] = index
                position.y++
            }
        }
        LEFT -> {
            val position = start.copy(x = start.x - 1)
            while (position.x >= 0 && array[position.y][position.x] != '9') {
                basins[position.y][position.x] = index
                if (turn) {
                    runThrough(position, UP, false, array, basins, index)
                    runThrough(position, DOWN, false, array, basins, index)
                }
                basins[position.y][position.x] = index
                position.x--
            }
        }
        UP -> {
            val position = start.copy(y = start.y - 1)
            while (position.y >= 0 && array[position.y][position.x] != '9') {
                basins[position.y][position.x] = index
                if (turn) {
                    runThrough(position, LEFT, false, array, basins, index)
                    runThrough(position, RIGHT, false, array, basins, index)
                }
                basins[position.y][position.x] = index
                position.y--
            }
        }
    }
}

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
}

private data class Coord(var y: Int, var x: Int)

