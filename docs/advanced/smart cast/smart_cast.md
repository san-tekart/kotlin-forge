스마트 캐스트는 코틀린의 강력한 기능 중 하나입니다. 컴파일러가 타입 검사(type check)를 추적해서 명시적인 캐스팅 없이도 타입 변환을 자동으로 처리해주는 기능입니다.
기본적인 예제:
kotlinCopyfun demo(x: Any) {
    if (x is String) {
        // 여기서 x는 자동으로 String 타입으로 캐스트됨
        print(x.length) // x가 String으로 스마트 캐스트 되어서 length 사용 가능
    }
}
더 복잡한 예제들:
kotlinCopy// 1. null 체크와 함께 사용
fun nullCheckExample(str: String?) {
    if (str != null) {
        // 여기서 str은 자동으로 non-null String으로 스마트 캐스트
        println(str.length) // null 체크 이후라 안전하게 사용 가능
    }
}

// 2. when 식에서의 스마트 캐스트
fun whenExample(obj: Any) {
    when (obj) {
        is Int -> println(obj + 1)      // obj가 Int로 스마트 캐스트
        is String -> println(obj.length) // obj가 String으로 스마트 캐스트
        is List<*> -> println(obj.size) // obj가 List로 스마트 캐스트
    }
}

// 3. && 연산자와 함께 사용
fun andOperatorExample(obj: Any) {
    if (obj is String && obj.length > 0) {
        // obj가 String으로 스마트 캐스트되어 length 사용 가능
        println("첫 글자: ${obj[0]}")
    }
}

// 4. 변수 할당과 스마트 캐스트
fun assignmentExample() {
    var obj: Any = "Hello"
    if (obj is String) {
        // obj가 String으로 스마트 캐스트
        println(obj.uppercase())
    }
}
스마트 캐스트의 제한사항:
kotlinCopy// 1. 가변 속성에는 동작하지 않음
class Example {
    var mutableProperty: Any = "hello"
    
    fun test() {
        if (mutableProperty is String) {
            // 컴파일 에러! mutableProperty가 다른 스레드에서 
            // 변경될 수 있어서 스마트 캐스트 불가
            // println(mutableProperty.length)
        }
    }
}

// 2. 하지만 로컬 변수는 가변이어도 됨
fun localVarExample() {
    var local: Any = "hello"
    if (local is String) {
        println(local.length) // 정상 동작
    }
}
이런 스마트 캐스트 덕분에 코틀린 코드는 더 간결하고 안전해집니다. 자바에서 필요한 명시적 타입 캐스팅을 많이 줄일 수 있죠. CopyRetrySK그럼 스마트캐스트는 주로 any로 정의하고 이후에 타입 체크를 if하면 이후에는 알아서 해당 타입으로 인식된다는 건가?Edit네! Any 뿐만 아니라 모든 상위 타입(부모 클래스나 인터페이스)에서 하위 타입으로의 스마트 캐스트가 가능합니다.
예시를 보여드리겠습니다:
kotlinCopy// 1. Any 타입에서의 스마트 캐스트
fun anyExample(x: Any) {
    if (x is String) {
        println(x.length)      // x는 String으로 스마트 캐스트
    }
}

// 2. 인터페이스/클래스 상속에서의 스마트 캐스트
interface Animal {
    fun makeSound()
}

class Dog : Animal {
    override fun makeSound() = println("Woof!")
    fun wagTail() = println("Wagging tail")
}

fun animalExample(animal: Animal) {
    if (animal is Dog) {
        animal.wagTail()       // animal이 Dog로 스마트 캐스트
    }
}

// 3. Nullable 타입에서의 스마트 캐스트
fun nullableExample(str: String?) {
    if (str != null) {
        println(str.length)    // str이 non-null String으로 스마트 캐스트
    }
}

// 4. 여러 조건을 체이닝해도 동작
fun chainedExample(x: Any) {
    if (x is String && x.length > 0) {  // x가 String으로 스마트 캐스트되어 length 접근 가능
        println("First char is: ${x[0]}") // 여기서도 x는 String
    }
}
참고로, val(불변)인 경우에만 프로퍼티의 스마트 캐스트가 동작합니다:
kotlinCopyclass Container {
    val immutableProp: Any = "hello"
    var mutableProp: Any = "hello"
    
    fun test() {
        if (immutableProp is String) {
            println(immutableProp.length)  // 동작함
        }
        
        if (mutableProp is String) {
            // println(mutableProp.length)  // 컴파일 에러!
            println((mutableProp as String).length)  // 명시적 캐스팅 필요
        }
    }
} CopyRetrySK아애에서 dog = (Doc)animal처럼 c++ 스타일로 캐스팅할 순 없어? 아니면 dog = animal as Dog처럼fun animalExample(animal: Animal) { if (animal is Dog) { animal.wagTail() // animal이 Dog로 스마트 캐스트 } }Edit코틀린에서는 as 키워드를 사용하여 명시적 캐스팅을 수행합니다:
kotlinCopy// 1. 기본 as 캐스팅
fun animalExample(animal: Animal) {
    val dog = animal as Dog    // unsafe cast, 실패하면 예외 발생
    dog.wagTail()
}

// 2. 안전한 as? 캐스팅
fun safeCastExample(animal: Animal) {
    val dog = animal as? Dog   // safe cast, 실패하면 null 반환
    dog?.wagTail()            // null safe call
}
C++ 스타일의 괄호를 사용한 캐스팅은 지원하지 않습니다. 코틀린에서 제공하는 캐스팅 연산자들:
kotlinCopy// 1. as  - unsafe cast
// 2. as? - safe cast
// 3. is  - type check (스마트 캐스트 trigger)
// 4. !is - negative type check

fun castingExamples(any: Any) {
    // unsafe cast
    try {
        val str1 = any as String        // 실패하면 ClassCastException
    } catch(e: ClassCastException) {
        println("Casting failed")
    }
    
    // safe cast
    val str2 = any as? String          // 실패하면 null
    println(str2?.length)              // null이면 실행 안됨
    
    // type check with smart cast
    if (any is String) {
        println(any.length)            // 스마트 캐스트
    }
} CopyRetrySK그러면 아래처럼 하는 것과 if animal is Dog으로 하는 것이 무슨 차이지? 특별히 더 간결할 것이 없잖아.
val dog = animal as DogEdit두 방식의 주요 차이점은 스코프와 안전성입니다:
kotlinCopy// 1. as를 사용한 명시적 캐스팅
fun example1(animal: Animal) {
    val dog = animal as Dog    // 여기서 실패하면 즉시 예외 발생
    dog.wagTail()
    // dog는 이 함수 전체에서 Dog 타입으로 사용 가능
}

// 2. is와 스마트 캐스트 사용
fun example2(animal: Animal) {
    if (animal is Dog) {
        // 이 블록 안에서만 animal이 Dog 타입으로 사용 가능
        animal.wagTail()
    }
    // 여기서는 animal이 다시 Animal 타입
}
is를 사용하는 것이 더 나은 경우:

타입 체크가 필요한 조건문

kotlinCopyfun process(animal: Animal) {
    if (animal is Dog) {
        animal.wagTail()
    } else if (animal is Cat) {
        animal.purr()
    }
}
as를 사용하는 것이 더 나은 경우:

타입 변환이 확실할 때
변환된 타입을 더 넓은 스코프에서 사용해야 할 때

kotlinCopyfun processDog(animal: Animal) {
    // 이 함수는 Dog만 받아야 한다고 가정
    val dog = animal as Dog
    dog.wagTail()
    // dog를 함수 전체에서 사용
}
보통 is를 사용한 스마트 캐스트가 더 선호됩니다:

더 안전함 (실행 전 체크)
코틀린의 철학에 더 부합
조건문과 자연스럽게 통합