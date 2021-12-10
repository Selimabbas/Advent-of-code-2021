package day10

import java.io.File

fun main() {
    var total = 0
    File("src/main/kotlin/day10/input.txt")
            .readLines()
            .forEach  { line ->
                val items = mutableListOf<Char>()
                line.forEach loop@ {
                    when (it) {
                        '(', '[', '{', '<' -> items.add(it)
                        ')' -> if (items.removeLast() != '(') {
                            total += 3
                            return@loop
                        }
                        ']' -> if (items.removeLast() != '[') {
                            total += 57
                            return@loop
                        }
                        '}' -> if (items.removeLast() != '{') {
                            total += 1197
                            return@loop
                        }
                        '>' -> if (items.removeLast() != '<') {
                            total += 25137
                            return@loop
                        }
                    }
                }
            }
    println(total)


}