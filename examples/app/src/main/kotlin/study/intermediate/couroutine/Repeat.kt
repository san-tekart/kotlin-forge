// study.intermediate.couroutine.Repeat.kt

package study.intermediate.couroutine

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/*
코루틴은 가볍다.
 */
fun testRepeat() = runBlocking {
    repeat(100000) {
        launch {
            delay(1000)
            print(".")  
        }
    }
}


fun testRepeatByThread() = runBlocking {
    repeat(100000) {
        thread {
            Thread.sleep(1000)
            print(".")  
        }
    }
}
