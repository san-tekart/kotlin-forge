// study.intermediate.couroutine.Repeat.kt

package study.intermediate.couroutine

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/*
Global 코루틴은 데몬 스레드같은 것이다.
 */
fun testGlobalCouroutine() = runBlocking {
    // main 함수는 1초 뒤에 종료되는데 GlobalScope.launch는 어떻게 될까?
    GlobalScope.launch {
        repeat(1000) { i->
            println("I'm sleeping $i ...")
            delay(500)
            
        }
    }
    delay(1000)
}
