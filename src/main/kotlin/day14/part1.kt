package day14

import java.io.File

fun main() {
    val file = File("src/main/kotlin/day14/input.txt").readLines()
    var line = file[0]
    val rules = file.subList(2, file.size)
            .map { Pair(it.substring(0, 2), it[6]) }
    for (i in 0 until 10) {
        var position = 0
        while (position < line.length - 1) {
            val substring = line.substring(position, position + 2)
            rules.forEach {
                if (substring == it.first) {
                    line = line.substring(0, position + 1) + it.second + line.substring(position + 1)
                    position++
                    return@forEach
                }
            }
            position++
        }
    }
    var min = -1
    var max = -1
    line.forEach { char ->
        val count = line.count { it == char }
        if (min == -1 || min > count) min = count
        if (max == -1 || max < count) max = count
    }
    println(max - min)
}