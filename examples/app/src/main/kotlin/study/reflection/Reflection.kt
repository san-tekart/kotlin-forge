// study.reflection.Reflection.kt

package study.reflection

import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction

// 예제용 클래스
class Person(
    val name: String,
    var age: Int
) {
    private var address: String = ""
    
    fun introduce() {
        println("I am $name")
    }
    
    private fun secretMethod() {
        println("This is private")
    }
    
    fun setAddress(addr: String) {
        address = addr
    }
}

fun reflectionExample() {
    println("=== 리플렉션 예제 ===")
    
    val person = Person("John", 30)
    
    // 1. 클래스 정보 가져오기
    val kClass = person::class
    println("\n1. 클래스 정보:")
    println("클래스 이름: ${kClass.simpleName}")
    println("클래스 전체 이름: ${kClass.qualifiedName}")
    println("추상 클래스 여부: ${kClass.isAbstract}")
    println("데이터 클래스 여부: ${kClass.isData}")
    
    // 2. 생성자 정보
    println("\n2. 생성자 정보:")
    val constructor = kClass.primaryConstructor
    println("주 생성자 파라미터:")
    constructor?.parameters?.forEach { param ->
        println("- ${param.name}: ${param.type}")
    }
    
    // 3. 프로퍼티 정보
    println("\n3. 프로퍼티 정보:")
    kClass.declaredMemberProperties.forEach { prop ->
        println("프로퍼티: ${prop.name}")
        println("- 타입: ${prop.returnType}")
        println("- Visibility: ${prop.visibility}")
        println("- 값: ${
            try {
                when (prop) {
                    is KProperty1<*, *> -> (prop as KProperty1<Person, *>).get(person)
                    else -> "접근 불가"
                }
            } catch (e: Exception) {
                "접근 불가"
            }
        }")
    }
    
    // 4. 메서드 정보
    println("\n4. 메서드 정보:")
    kClass.declaredMemberFunctions.forEach { func ->
        println("메서드: ${func.name}")
        println("- 반환 타입: ${func.returnType}")
        println("- Visibility: ${func.visibility}")
        println("- 파라미터:")
        func.parameters.forEach { param ->
            if (param.name != null) {  // receiver 파라미터 제외
                println("  * ${param.name}: ${param.type}")
            }
        }
    }
    
    // // 5. 동적 메서드 호출
    // println("\n5. 동적 메서드 호출:")
    // val introduceMethod = kClass.declaredMemberFunctions.find { it.name == "introduce" }
    // introduceMethod?.call(person)
    
    // // 6. 동적 프로퍼티 접근
    // println("\n6. 동적 프로퍼티 접근:")
    // val nameProperty = kClass.declaredMemberProperties.find { it.name == "name" }
    // println("name 프로퍼티 값: ${nameProperty?.get(person)}")
    
    // // 7. 어노테이션 정보 (있는 경우)
    // println("\n7. 어노테이션 정보:")
    // kClass.annotations.forEach { annotation ->
    //     println("어노테이션: ${annotation.annotationClass.simpleName}")
    // }
}

fun reflectionExamples() {
    reflectionExample()
}
