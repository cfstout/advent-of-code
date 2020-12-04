package com.cfstout.advent.`2020`

import java.io.File

object Day3 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input: List<List<Boolean>> = File("src/main/resources/day_3_input.txt").readLines()
                .map { mapToBooleanArray(it) }
        val size = input[0].size
        var first = 0
        var value1: Long = 0
        var second = 0
        var value2: Long = 0
        var third = 0
        var value3: Long = 0
        var forth = 0
        var value4: Long = 0
        input.forEach {
            value1 += if (it[first % size]) 0 else 1
            value2 += if (it[second % size]) 0 else 1
            value3 += if (it[third % size]) 0 else 1
            value4 += if (it[forth % size]) 0 else 1
            first += 1
            second += 3
            third += 5
            forth += 7
        }

        var fifth = 0
        var value5: Long = 0
        var loop = 0
        input.forEach {
            if (loop % 2 == 0) {
                value5 += if(it[fifth%size]) 0 else 1
            } else {
                fifth +=1
            }
            loop+=1
        }

        println("1: $value1, 2: $value2, 3: $value3, 4: $value4, 5: $value5, ${value1*value2*value3*value4*value5}")
    }

    // True = open
    // False = tree
    private fun mapToBooleanArray(input: String): List<Boolean> {
        return input.map { it == '.' }
    }
}
