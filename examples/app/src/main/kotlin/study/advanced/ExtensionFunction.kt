//  study.advanced.ExtensionFunction.kt
package study.advanced

// 확장 함수
fun String.countUppercase(): Int {
    // 모두 동일
    //return count { it.isUpperCase() }
    //return count { it -> it.isUpperCase() }
    return count { ch -> ch.isUpperCase() } 
}

fun testExtensionFunction() {
    val str = "Hello, World!"
    println("Number of uppercase letters: ${str.countUppercase()}")
}



