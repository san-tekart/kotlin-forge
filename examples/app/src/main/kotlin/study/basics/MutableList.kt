// study.basics.MutableList.kt

package study.basics

fun mutableListExamples() {
    val list: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5)
    println("list: ${list.joinToString(", ")}")

    var strings: MutableList<String> = mutableListOf("a", "b", "c", "d", "e")
    println("strings: ${strings.joinToString(", ")}")

    strings.add("f")
    println("strings: ${strings.joinToString(", ")}")

    strings.remove("a")
    println("strings: ${strings.joinToString(", ")}")

    strings.removeAt(0)
    println("strings: ${strings.joinToString(", ")}")
}
