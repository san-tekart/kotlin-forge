// study.intermediate.CrossInline.kt

package study.intermediate

inline fun performAction(action: () -> Unit) {
    action() // 인라인으로 호출
}

inline fun performSafeAction(crossinline action: () -> Unit) {
    val runnable = Runnable {
        action() // crossinline 때문에 return이 제한됨
    }
    runnable.run()
}

fun testNonLocalReturn() {
    performAction {
        println("Hello")
        return // performAction 함수 전체에서 반환
    }
    println("This will never be printed") // 실행되지 않음
}

fun testCrossInlineReturn() {
    performSafeAction {
        println("Hello")     
        // or return@performSafeAction // 명시적 라벨로 반환        
    }
    println("This should be printed")
}


fun testCrossInline() {
    testNonLocalReturn()
    testCrossInlineReturn()
}
