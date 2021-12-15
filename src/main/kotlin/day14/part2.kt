package day14

import java.io.File

fun main() {
    val file = File("src/main/kotlin/day14/input.txt").readLines()
    val line = file[0]
    val rules = file.subList(2, file.size).associate { Pair(it.substring(0, 2), it[6]) }
    var map = mutableMapOf<String, Long>()
    for (i in 0..line.length - 2) {
        val stringPair = line.substring(i, i + 2)
        map[stringPair] = map.getOrDefault(stringPair, 0) + 1
    }

    for (i in 0 until 40) {
        val newMap = mutableMapOf<String, Long>()
        map.forEach {
            if (rules.keys.contains(it.key)) {
                val firstKey = it.key[0].toString() + rules[it.key].toString()
                val secondKey = rules[it.key].toString() + it.key[1].toString()
                newMap[firstKey] = newMap.getOrDefault(firstKey, 0) + it.value
                newMap[secondKey] = newMap.getOrDefault(secondKey, 0) + it.value
            }
        }
        map = newMap.toMutableMap()
    }
    var min = -1L
    var max = -1L
    val countMap = mutableMapOf<Char, Long>()
    map.forEach { (key, value) ->
        countMap[key[0]] = countMap.getOrDefault(key[0], 0) + value
        countMap[key[1]] = countMap.getOrDefault(key[1], 0) + value
    }

    countMap.forEach { key, value ->
        var count = value
        if (line.first() == key) count++
        if (line.last() == key) count++
        count /= 2
        if (min == -1L || min > count) min = count
        if (max == -1L || max < count) max = count
    }

    println(max - min)
}