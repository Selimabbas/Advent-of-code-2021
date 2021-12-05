package day05

import java.io.File

fun main() {
    val array = Array(1000) { Array(1000) { 0 } }
    File("src/main/kotlin/day05/input.txt")
        .readLines()
        .forEach {
            val (tmpstartX, tmpstartY, tmpendX, tmpendY) = "([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)".toRegex()
                .matchEntire(it)!!.destructured
            var startY = tmpstartY.toInt()
            var startX = tmpstartX.toInt()
            val endY = tmpendY.toInt()
            val endX = tmpendX.toInt()
            if (startY == endY) {
                val range = if (startX < endX) startX..endX else endX..startX
                for (x in range) {
                    array[startY][x] += 1
                }
            } else {
                array[startY][startX] += 1
                while (startY != endY) {
                    if (startX != endX) {
                        if (startX > endX) startX-- else startX++
                    }
                    if (startY > endY) startY-- else startY++
                    array[startY][startX] += 1
                }
            }
        }
    var total = 0
    array.forEach {
        it.forEach { overlaps ->
            if (overlaps >= 2) total++
        }
        println()
    }

    println(total)
}