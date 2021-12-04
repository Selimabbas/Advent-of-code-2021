package day04

import java.io.File

fun main() {
    val file = File("src/main/kotlin/day04/input.txt")
        .readLines()

    val numbers = file[0].split(',').map { it.toInt() }
    val boards = file.subList(2, file.size)
        .filter { it.isNotEmpty() }
        .map { line ->
            line.split(" ")
                .filter { it.isNotEmpty() }
                .map { it.toInt() }
        }
    for (i in 5..numbers.size) {
        val usedNumbers = numbers.subList(0, i)
        for (boardIndex in 0 until boards.size / 5) {
            val board = boards.subList(boardIndex * 5, boardIndex * 5 + 5)
            for (k in 0..4) {
                if (usedNumbers.containsAll(board[k])
                    || usedNumbers.containsAll(board.map { it[k] })) {
                    println(bingo(board, usedNumbers))
                    return
                }
            }
        }
    }
}

fun bingo(board: List<List<Int>>, usedNumbers: List<Int>): Int {
    var totalSum = 0
    board.forEach {
        it.forEach { value ->
            if (!usedNumbers.contains(value)) {
                totalSum += value
            }
        }
    }
    return totalSum * usedNumbers.last()

}