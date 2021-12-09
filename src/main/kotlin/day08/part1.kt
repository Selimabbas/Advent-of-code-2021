package day08

import java.io.File

fun main() {
    var total = 0
    File("src/main/kotlin/day08/input.txt")
            .readLines()
            .map { line ->
                line.split('|').map { it.split(' ') }
            }
            .forEach { line ->
                line[1].forEach {
                    if (it.length == 2 || it.length == 4 || it.length == 3 || it.length == 7) total++
                }
            }
    println(total)
}