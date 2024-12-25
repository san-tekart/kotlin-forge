// study.basics.ControlFlow.kt

package study.basics

fun controlFlowExamples() {
    var a = 10
    if (a > 0) {
        println("a is greater than 0")
    } else {
        println("a is less than or equal to 0")
    }

    var b = if (a > 0) {
        "a is greater than 0"
    } else {
        "a is less than or equal to 0"
    }

    println(b)

    a = 30
    when (a) {
        10 -> println("a is 10")
        20 -> println("a is 20")
        else -> println("a is neither 10 nor 20")
    }
}

