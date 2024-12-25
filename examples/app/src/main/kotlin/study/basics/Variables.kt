// study.basics.Variables.kt

package study.basics

fun valExamples() {
    val a: Int = 10
    println("a: $a")

    // a = 30 // Error: Val cannot be reassigned
}

fun variableExamples() {
    val a: Int = 10
    val b: Int = 20
    val c: Int = a + b
    println(c)

    val d: String = "Hello, World!"
    println("d: $d")

    val f: Float = 1.0f
    println("f: $f")

    val g: Double = 1.0
    println("g: $g")

    val h: Boolean = true
    println("h: $h")

    val i: Char = 'A'
    println("i: $i")

    var array: Array<Int> = arrayOf(1, 2, 3, 4, 5)
    println("array: $array")
}


