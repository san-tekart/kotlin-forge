// study.intermediate.couroutine.Suspend.kt

package study.intermediate.couroutine

import kotlinx.coroutines.*

/*
delay는 suspend 함수이다.
suspend 함수는 코루틴 내부에서만 호출할 수 있다.
 */
suspend fun printWorld() {
    delay(1000)
    println("World  ${Thread.currentThread().name}")
}

fun testSuspend() = runBlocking {
    launch {
        printWorld()
    }
    println("Hello ${Thread.currentThread().name}")  
}
