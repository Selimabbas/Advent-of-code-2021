package day10

import java.io.File

fun main() {
    val totalList = mutableListOf<Long>()
    File("src/main/kotlin/day10/input.txt")
            .readLines()
            .forEach loop@{ line ->
                val items = mutableListOf<Char>()
                line.forEach {
                    when (it) {
                        '(', '[', '{', '<' -> items.add(it)
                        ')' -> if (items.removeLast() != '(') {
                            return@loop
                        }
                        ']' -> if (items.removeLast() != '[') {
                            return@loop
                        }
                        '}' -> if (items.removeLast() != '{') {
                            return@loop
                        }
                        '>' -> if (items.removeLast() != '<') {
                            return@loop
                        }
                    }
                }
                var total = 0L
                for (i in items.size - 1 downTo 0) {
                    when (items[i]) {
                        '(' -> total = total * 5 + 1
                        '[' -> total = total * 5 + 2
                        '{' -> total = total * 5 + 3
                        '<' -> total = total * 5 + 4
                    }
                }
                totalList.add(total)
            }
    totalList.sort()
    println(totalList[(totalList.size - 1) / 2])
}