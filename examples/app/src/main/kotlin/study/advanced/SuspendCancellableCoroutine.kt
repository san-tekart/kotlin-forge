// study.advanced.Async.kt

package study.advanced

import kotlinx.coroutines.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

/**
 * 코루틴에서 사용 가능한 "커스텀 delay" 메커니즘을 제공한다.
 * Executors.newSingleThreadScheduledExecutor()를 사용하여
 * 특정 시간 이후에 코루틴을 재개(resume)하거나,
 * 취소 시에는 예약된 작업을 취소한다.
 */
class CustomDelayDispatcher {
    private val scheduler = Executors.newSingleThreadScheduledExecutor()
    private val tasks = ConcurrentHashMap<Long, ScheduledFuture<*>>()
    private val taskIdCounter = AtomicLong()

    /**
     * [timeMillis]가 지나면 이어서 코루틴 실행을 재개한다.
     * 코루틴이 취소되면 예약된 스케줄도 자동으로 취소한다.
     */
    suspend fun customDelay(timeMillis: Long) = suspendCancellableCoroutine<Unit> { continuation ->
        // 새 작업 ID 생성
        val taskId = taskIdCounter.incrementAndGet()

        // timeMillis 후 continuation.resume(Unit) 호출
        val future = scheduler.schedule({
            tasks.remove(taskId)
            continuation.resume(Unit)
        }, timeMillis, TimeUnit.MILLISECONDS)

        // 작업을 ConcurrentHashMap에 기록
        tasks[taskId] = future

        // 코루틴이 취소됐을 때 예약 작업을 취소하는 콜백
        continuation.invokeOnCancellation {
            val scheduled = tasks.remove(taskId)
            scheduled?.cancel(false)
        }
    }

    /**
     * 스케줄러 종료. 더 이상 예약 작업을 실행할 수 없다.
     */
    fun shutdown() {
        scheduler.shutdown()
    }
}


/**
 * 커스텀 delay 기능이 정상 작동하는지 테스트하기 위한 함수.
 * 여러 코루틴을 만들고, 중간에 특정 코루틴을 취소해본다.
 */
suspend fun testCustomDelay() = coroutineScope {
    val dispatcher = CustomDelayDispatcher()

    println("시작: ${System.currentTimeMillis()}")

    // 3개의 코루틴 생성: 각각 1초 뒤에 재개
    val jobs = List(3) { index ->
        launch {
            println("코루틴 $index 시작: ${System.currentTimeMillis()}")
            dispatcher.customDelay(1000L)  // 커스텀 delay 사용
            println("코루틴 $index 종료: ${System.currentTimeMillis()}")
        }
    }

    // 2초 뒤 재개될 예정이지만 0.5초 뒤 취소해볼 코루틴
    val cancelJob = launch {
        println("취소될 코루틴 시작: ${System.currentTimeMillis()}")
        try {
            dispatcher.customDelay(2000L)
            println("이 메시지는 출력되지 않아야 함")
        } catch (e: CancellationException) {
            println("코루틴이 취소됨: ${System.currentTimeMillis()}")
        }
    }

    // 0.5초 뒤에 위 cancelJob을 취소
    delay(500)
    cancelJob.cancel()

    // 모든 코루틴이 종료될 때까지 대기
    jobs.forEach { it.join() }

    dispatcher.shutdown()
}
