// study.basics.Array.kt

package study.basics

fun arrayExamples() {
    val numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5)
    println("array: ${numbers.joinToString(", ")}")

    var strings: Array<String> = arrayOf("a", "b", "c", "d", "e")
    println("strings: ${strings.joinToString(", ")}")

    strings = Array(3){"aaa"}
    println("strings: ${strings.joinToString(", ")}")

    for (number in numbers) {
        println("number: $number")
    }

    for ((index , number) in numbers.withIndex()) {
        println("index: $index, number: $number")
    }

    var filtered = numbers.filter { it % 2 == 0 }   
    println("filtered: ${filtered.joinToString(", ")}")
}
