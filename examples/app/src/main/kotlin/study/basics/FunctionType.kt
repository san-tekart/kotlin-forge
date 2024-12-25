// study.basics.FunctionType.kt

package study.basics

fun functionType1() {
    val a: (Int) -> Int = { it + 1 }
    val b = a(1)
    println(b)
}

fun functionType2() {
    fun example(callbak: ()->Unit) {
        println("example")
        callbak()
    }

    example {
        println("callback")
    }

    fun testCallback(): Unit {
        println("testCallback")     
    }

    example(::testCallback)

    class Person {
        // 프로퍼티
        var name: String = "Kim"
        
        // 메서드
        fun name(): String = "Kim"

        fun unit(): Unit {
            println("unit")
        }
    }    

    val person = Person()
    person.name    // 프로퍼티 접근
    person.name()  // 메서드 호출

    val methodRef: (Person) -> String = { p -> p.name() }    // 람다식으로 해결

    person.unit()

    example(person::unit)
}
