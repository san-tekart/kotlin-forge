// study.advanced.Contract.kt

package study.advanced

import kotlin.contracts.ExperimentalContracts  // 추가
import kotlin.contracts.contract              // 추가
import kotlin.contracts.InvocationKind       // 추가


import kotlin.contracts.contract


@ExperimentalContracts 
inline fun runOnce(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}

@ExperimentalContracts 
fun testContract() {
    var x: String
    runOnce { 
        x = "value"  // 컴파일러는 x가 반드시 초기화됨을 알 수 있음
    }
    println(x)  // Contract 덕분에 컴파일 에러가 발생하지 않음
}

@ExperimentalContracts 
fun <T> checkNotNull(value: T?): T {
    contract {
        returns() implies (value != null)  // value가 null이 아님을 보장
    }
    if (value == null) throw IllegalArgumentException()
    return value
}

@ExperimentalContracts 
fun testContract2() {
    fun example(str: String?) {
        checkNotNull(str)
        str.length  // contract가 없으면 컴파일 에러! (str이 null일 수 있다고 판단)
    }

    example("Hello")  // 컴파일 에러 없음
    //example(null)     // 컴파일 에러 발생
}