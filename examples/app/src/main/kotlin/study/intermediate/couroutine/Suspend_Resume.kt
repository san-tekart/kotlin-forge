// study.intermediate.couroutine.Repeat.kt

package study.intermediate.couroutine

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/*
VM 옵션에 -Dkotlinx.coroutines.debug 옵션을 주면 코루틴의 상태를 출력해준다.
 */

fun <T>println(msg: T) {
    kotlin.io.println("$msg [${Thread.currentThread().name}]")
}

fun testSuspendResume() = runBlocking {
    launch {
        repeat(5) { i->
            println("Couroutine A, $i ...")
            delay(10) // delay에 의해 코루틴 b가 실행되는 시간을 준다.
        }
    }

    launch {
        repeat(5) { i->
            println("Couroutine B, $i ...")
            delay(10) // delay에 의해 코루틴 a가 실행되는 시간을 준다.
        }
    }

    println("Couroutine Outer")
}
