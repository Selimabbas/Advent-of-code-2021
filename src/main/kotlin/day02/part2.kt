package day02

import java.io.File

fun main() {
    var position = 0
    var depth = 0
    var aim = 0

    File("src/main/kotlin/day02/input.txt")
        .readLines()
        .map { "([a-z]+) ([0-9])".toRegex().matchEntire(it)!!.destructured }
        .forEach {
            val value = it.component2().toInt()
            when (it.component1()) {
                "forward" -> {
                    position += value
                    depth += aim * value
                }
                "up" -> aim -= value
                "down" -> aim += value
            }
        }
    println(depth * position)
}
