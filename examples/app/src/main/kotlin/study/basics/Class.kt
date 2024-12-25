// study.basics.Class.kt

package study.basics

fun basic() {
    class Simple(val name: String)

    var simple = Simple("John")
    println(simple.name)

    class Person(val name: String, val age: Int = 10)       
    println(Person("John").age)


    class Person2(val name: String, aaa: Int = 30) {
        var age: Int = aaa
    }

    println(Person2("John").age)
}

fun inheritance() {
    open class Animal(val name: String) {
        private var age: Int = 0

        init {
            println("Animal initialized with name: $name")
        }

        open fun bark() {
            println("Hello, I am $name")
        }
    }

    class Dog(name: String) : Animal(name) {
        override fun bark() {
            println("Woof Woof")
        }
    }

    Dog("Rex").bark()
}

fun avoidDuplicatedGetter() {
    class Foo {
        private var internalAge: Int = 0

        constructor() {
        }

        constructor(age: Int) {
            this.internalAge = age
        }

        fun getAge(): Int = internalAge
    }

    val foo = Foo(10)
    println(foo.getAge())
}

fun abstractClass() {
    abstract class Animal(val name: String) {
        abstract fun bark()
        abstract var age: Int
    }

    class Dog(name: String) : Animal(name) {
        override var age: Int = 0
        override fun bark() {
            println("Woof Woof")
        }
    }

    val dog = Dog("Rex")
    dog.bark()
    println(dog.age)
}


 fun functionDelegation() {
    
    fun sum(a: Int, b: Int): Int {
        return a + b
    }
    
    //val s: (Int, Int) -> Int = sum // Error: 코틀린에서 지역 함수(local function)를 함수 참조로 사용할 수 없습니다. 이는 코틀린의 설계 제한사항입니다.     
    println(sum(1,2))

    class Foo {
        fun sum(a: Int, b: Int): Int {
            return a + b
        }
    }

    val sumFunc = Foo::sum
    val foo = Foo()
    println(sumFunc(foo, 1, 2))
 }

fun classExamples() {
    //val dog = Dog("Rex")
    // val duck = Duck("Duck")
    //dog.bark()
    // duck.bark()

    //basic()    
    //avoidDuplicatedGetter()
    //abstractClass()
    //basic()
    functionDelegation()
}
