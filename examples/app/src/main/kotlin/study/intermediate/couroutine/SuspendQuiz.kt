// study.intermediate.Coutine.kt

package study.intermediate.couroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.async 
import kotlin.system.measureTimeMillis
import kotlin.suspend

// 두 개의 suspend 함수를 만드세요:

// multiplySlowly: 두 숫자를 받아서 곱하는데 0.5초가 걸립니다
// addSlowly: 두 숫자를 받아서 더하는데 1초가 걸립니다

suspend fun multiplySlowly(a: Int, b: Int): Int {
    println("multiplySlowly($a, $b) start")
    delay(500)
    println("multiplySlowly($a, $b) end")
    return a * b
}

suspend fun addSlowly(a: Int, b: Int): Int {
    println("addSlowly($a, $b) start")
    delay(1000)
    println("addSlowly($a, $b) end")
    return a + b
}

fun testLaunch() = runBlocking {
    println("testCoroutine start")

    launch {
        val r1 = multiplySlowly(2, 3)
        println("multiplySlowly(2, 3) = ${r1}")
    }

    launch {
        val r2 = addSlowly(4, 5)
        println("addSlowly(4, 5) = ${r2}")
    }

    println("testCoroutine end")
}

fun testAsync() = runBlocking {
    val result1 = async { multiplySlowly(2, 3) }
    val result2 = async { addSlowly(4, 5) }
    println("Result 1: ${result1.await()}")
    println("Result 2: ${result2.await()}")
}

// calculateParallel이라는 suspend 함수를 만드세요. 이 함수는:

// 4개의 숫자(a, b, c, d)를 입력받습니다
// (a * b) + (c * d)를 계산해야 합니다
// 곱셈 연산들은 병렬로 수행되어야 합니다
// 마지막 덧셈은 두 곱셈이 완료된 후에 수행되어야 합니다

suspend fun calculateParallel(a: Int, b: Int, c: Int, d: Int) = coroutineScope {
    var multiply1: suspend (Int, Int) -> Int = { v1: Int, v2: Int -> v1 * v2}
    var multiply2: suspend (Int, Int) -> Int = { v1: Int, v2: Int -> v1 * v2}
    
    val result1 = async { multiply1(a, b) }
    val result2 = async { multiply2(c, d) }

    val r1 = result1.await()
    val r2 = result2.await()

    println("calculateParallel($a, $b, $c, $d) = ${r1 + r2}")
}

fun calculateSequential(a: Int, b: Int, c: Int, d: Int) = runBlocking {
    var multiply1 = { v1: Int, v2: Int -> v1 * v2}
    var multiply2 = { v1: Int, v2: Int -> v1 * v2}
    val r1 = multiply1(a, b)
    val r2 = multiply2(c, d)
    println("calculateSequential($a, $b, $c, $d) = ${r1 + r2}")
}

fun testCompareSyncAndAsync() = runBlocking {
    println("--------------------------------")
    val time = measureTimeMillis {
        calculateParallel(1, 2, 3, 4)
    }
    println("calculateParallel time: ${time}ms")
    println("--------------------------------")
    val time2 = measureTimeMillis {
        calculateSequential(1, 2, 3, 4)
    }
    println("calculateSequential time: ${time2}ms")

}

