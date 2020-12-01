@file:Suppress("PackageName")

package com.cfstout.advent.`2020`

import java.io.File

object Day1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input: List<Long> = File("src/main/resources/day_1_input.txt").readLines().map { it.toLong() }
        val setOfInput: Set<Long> = input.toHashSet()
        println("Part 1")
        for (l in input) {
            val other = 2020 - l
            if (other > 0 && setOfInput.contains(other)) {
                println("Found part 1 answer! $l x $other = ${l * other}")
                break;
            }
        }

        println("Part 2")
        for (l in input) {
            val newSum = 2020 - l
            for (m in input) {
                val other = newSum - m
                if (other > 0 && setOfInput.contains(other)) {
                    println("Found 2 answer! $l x $m x $other = ${l * other * m}")
                    return;
                }
            }
        }
    }
}
