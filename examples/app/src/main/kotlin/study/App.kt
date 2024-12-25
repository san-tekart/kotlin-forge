// study.App.kt

/*
 * This source file was generated by the Gradle 'init' task
 */
package study

import study.basics.variableExamples
import study.basics.arrayExamples
import study.basics.mutableListExamples
import study.basics.classExamples
import study.basics.controlFlowExamples
import study.basics.lambdaExamples
import study.basics.functionType1
import study.basics.functionType2
import study.reflection.reflectionExamples
import study.advanced.testCustomDelay
import study.advanced.testExtensionFunction
import study.advanced.testContract
import study.advanced.testContract2
import study.intermediate.testCrossInline
import kotlinx.coroutines.runBlocking  // 추가
import kotlinx.coroutines.*
import kotlin.contracts.ExperimentalContracts
import study.intermediate.couroutine.testLaunch
import study.intermediate.couroutine.testAsync
import study.intermediate.couroutine.testCompareSyncAndAsync
import study.intermediate.couroutine.testWholeRunBlocking
import study.intermediate.couroutine.testThread
import study.intermediate.couroutine.testJobJoin
import study.intermediate.couroutine.testStructuredConcurrency
import study.intermediate.couroutine.testRepeat
import study.intermediate.couroutine.testRepeatByThread
import study.intermediate.couroutine.testGlobalCouroutine
class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

// fun main() {
//     //println(App().greeting)
//     //variableExamples()
//     //arrayExamples()
//     //mutableListExamples()
//     //classExamples()
//     //controlFlowExamples()
//     //lambdaExamples()
//     //reflectionExamples()
// }

// fun main() = runBlocking {
//     testCustomDelay()
// }

// fun main() {
//     //functionType1()
//     //functionType2()
//     //testExtensionFunction()    
// }

// @OptIn(ExperimentalContracts::class)  
// fun main() {
//     //testContract()
//     testContract2()
// }

// fun main() {
//     testCrossInline()
// }

// fun main() {
//     //testCoroutineLaunch()
//     //testCoroutineAsync()
// }

// fun main() {
//     testCalculate()
// }

fun main() {
    //testRunBlocking()
    //testThread()
    //testJobJoin()
    //testStructuredConcurrency()
    //testRepeat()  
    //  testRepeatByThread()
    testGlobalCouroutine()
}
