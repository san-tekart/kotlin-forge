// study.intermediate.couroutine.StructuredConcurrency.kt

package study.intermediate.couroutine

import kotlinx.coroutines.*

/*
이전 예제에서 실수로 join을 안한 경우 코루틴이 실행되지 않으면서 World가 출력되지 않는다.
이런 식으로 여러 코루틴 Job을 관리하기는 어렵다.
이런 문제를 해결하기 위해 구조적 동시성을 도입한다.

GlobalScope와 runBlocking은 서로 구조적으로 관련이 없다.
runBlocking과 launch에서 실행되는 코루틴의 구조적인 관계를 만들어 주면 서로 기다려 줄 수 있지 않나.

GlobalScope에서 launch하지 말고 runBlocking으로 들어 온 CouroutineScope에서 launch를 하자.
join을 하지 않아도 코루틴이 끝날 때까지 기다리게 된다.
*/
fun testStructuredConcurrency() = runBlocking {
    this.launch {
        delay(1000)
        println("World  ${Thread.currentThread().name}")
    }

    this.launch {
        delay(2000)
        println("World  ${Thread.currentThread().name}") 
    }

    println("Hello ${Thread.currentThread().name}")    
}

