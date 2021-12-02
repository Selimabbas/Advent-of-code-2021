package day02

import java.io.File

fun main() {
    var position = 0
    var depth = 0

    File("src/main/kotlin/day02/input.txt")
        .readLines()
        .map { "([a-z]+) ([0-9])".toRegex().matchEntire(it)!!.destructured }
        .forEach {
            val (move, value) = it
            when (move) {
                "forward" -> position += value.toInt()
                "up" -> depth -= value.toInt()
                "down" -> depth += value.toInt()
            }
        }
    println(depth * position)
}
