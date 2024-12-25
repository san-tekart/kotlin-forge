// study.basics.Lambda.kt

package study.basics

fun lambdaExamples() {
    val add = {v:Int -> v + 1}
    val b = add(100)
    println(b)
}
