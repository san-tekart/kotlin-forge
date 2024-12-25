// study.intermediate.couroutine.Lecture1.kt

package study.intermediate.couroutine

import kotlinx.coroutines.*
import kotlin.concurrent.thread

fun testGlobalScope() {
    /*
    abstract class JobSupport : Job {
        // Job 인터페이스의 구현
    }

    class StandaloneCoroutine(
        parentContext: CoroutineContext,
        active: Boolean
    ) : AbstractCoroutine<Unit>(parentContext, active) {
        // AbstractCoroutine은 JobSupport를 상속받음
    }

    class LazyStandaloneCoroutine(
        parentContext: CoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) : StandaloneCoroutine(parentContext, active = false) {
        // StandaloneCoroutine을 상속
    }

    코루틴을 만들고 반환한다.
    public func CoroutineScope.launch(
        context: CoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) : Job {
        val newContext = newCoroutineContext(context)
        val coroutine = if (start.isLazy)
            LazyStandaloneCoroutine(newContext, block) else
            StandaloneCoroutine(newContext, active = true)
        coroutine.start(start, coroutine, block)
        return coroutine 
     }
    */      
    GlobalScope.launch { // launch는 scope가 있어야 실행될 수 있다
        delay(1000)
        println("Hello")
    }
    println("World")
    Thread.sleep(2000)
}   

fun testThread() {
    thread {
        println("Hello ${Thread.currentThread().name}")
    }
    println("World ${Thread.currentThread().name}")
    Thread.sleep(2000)
}


/*
delay는 suspend 함수이고 CoroutineScope나 다른 suspend 함수에서 호출할 수 있다.
그래서 아래쪽 delay는 runBlocking 안에서 호출한다
 */
fun testRunBlocking() {
    GlobalScope.launch { // launch는 scope가 있어야 실행될 수 있다
        delay(1000)
        println("Hello ${Thread.currentThread().name}")
    }
    println("World ${Thread.currentThread().name}")
    
    runBlocking {
        delay(2000)
    }
}


/*
동기 함수 전체를 blocking으로 만든다
Hello만 출력되고 끝난다. 3초 뒤에 World를 출력하므로.
 */
fun testWholeRunBlocking() = runBlocking {
    GlobalScope.launch { // launch는 scope가 있어야 실행될 수 있다
        delay(3000)
        println("World  ${Thread.currentThread().name}")
    }
    println("Hello ${Thread.currentThread().name}")    
    delay(2000)
}

/*
Job을 도입한다.
launch를 하면 Job 객체가 반환된다.
join으로 코루틴이 끝날 때까지 기다린다.
 */
fun testJobJoin() = runBlocking {
    val job = GlobalScope.launch { // launch는 scope가 있어야 실행될 수 있다
        delay(3000)
        println("World  ${Thread.currentThread().name}")
    }
    println("Hello ${Thread.currentThread().name}")    
    delay(2000)
    job.join()
}
