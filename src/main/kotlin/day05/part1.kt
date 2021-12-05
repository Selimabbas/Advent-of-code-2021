package day05

import java.io.File

fun main() {
    val array = Array(1000) { Array(1000) { 0 } }
    File("src/main/kotlin/day05/input.txt")
        .readLines()
        .forEach {
            val (tmpStartX, tmpStartY, tmpEndX, tmpEndY) = "([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)".toRegex()
                .matchEntire(it)!!.destructured
            val startX = tmpStartX.toInt()
            val startY = tmpStartY.toInt()
            val endX = tmpEndX.toInt()
            val endY = tmpEndY.toInt()
            if (startX == endX) {
                val range = if (startY < endY) startY..endY else endY..startY
                for (y in range) {
                    array[startX][y] += 1
                }
            } else if (startY == endY) {
                val range = if (startX < endX) startX..endX else endX..startX
                for (x in range) {
                    array[x][startY] += 1
                }
            }
        }
    var total = 0
    array.forEach {
        it.forEach { overlaps -> if (overlaps >= 2) total++  }
    }
    println(total)

}