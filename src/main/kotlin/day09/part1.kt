package day09

import java.io.File

fun main() {
    val array = File("src/main/kotlin/day09/input.txt")
            .readLines()
            .map {
                it.toCharArray()
            }
    val lowPoints = mutableListOf<Int>()
    for (y in array.indices) {
        for (x in array[0].indices) {
            val location = array[y][x]
            if ((x == 0 || array[y][x - 1] > location)
                    && (x == array[y].size - 1 || array[y][x + 1] > location)
                    && (y == 0 || array[y - 1][x] > location)
                    && (y == array.size - 1 || array[y + 1][x] > location)) {
                lowPoints.add(location.code - 48 + 1)
            }
        }
    }
    println(lowPoints.sum())
}