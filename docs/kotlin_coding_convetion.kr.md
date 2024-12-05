# Kotlin 코딩 컨벤션

Kotlin에서 파일 이름, 변수, 함수는 모두 컨벤션에 따라 작성되며, Camel Case(낙타 표기법)와 Pascal Case(대문자로 시작하는 낙타 표기법)를 적절히 사용합니다. JetBrains와 Kotlin 커뮤니티가 제안하는 Kotlin 코딩 컨벤션에 따르면 다음과 같습니다:

## 1. 파일 이름
- 파일 이름은 보통 Pascal Case를 사용합니다.
  - 예: `Main.kt`, `UserManager.kt`, `DataProcessor.kt`
- 파일 이름 컨벤션
  - 파일 이름은 클래스나 주요 기능을 반영해야 합니다.
  - 하나의 파일에 여러 클래스나 함수가 있다면, 가장 주요한 요소의 이름을 따릅니다.
    - 예: 파일에 `User` 클래스와 `UserManager` 클래스가 있으면 파일 이름은 `UserManager.kt`.

## 2. 변수와 함수 이름
- 변수와 함수 이름은 Camel Case를 사용합니다.

  - 변수 이름 예:
    ```kotlin
    val userName = "John"
    var isLoggedIn = false
    val maxRetries = 3
    ```

  - 함수 이름 예:
    ```kotlin
    fun calculateSum(a: Int, b: Int): Int { ... }
    fun isValidUser(user: User): Boolean { ... }
    fun printMessage(message: String) { ... }
    ```

## 3. 클래스와 인터페이스 이름
- 클래스와 인터페이스 이름은 Pascal Case를 사용합니다.

  - 클래스와 인터페이스 예:
    ```kotlin
    class User
    class DataProcessor
    interface OnClickListener
    ```

## 4. 상수 이름
- 상수 이름은 UPPER_SNAKE_CASE를 사용합니다. Kotlin에서는 상수를 `const val`로 선언합니다.

  - 상수 예:
    ```kotlin
    const val MAX_CONNECTIONS = 5
    const val API_URL = "https://example.com"
    ```

## 5. 패키지 이름
- 패키지 이름은 소문자 스네이크 케이스를 사용하며, 일반적으로 프로젝트 구조를 반영합니다.

  - 패키지 예:
    ```
    com.example.myproject
    org.jetbrains.kotlin
    ```

## 6. 일반적인 코딩 스타일 요약

| 항목               | 컨벤션          | 예시                      |
|------------------|----------------|-------------------------|
| 파일 이름          | Pascal Case     | Main.kt, UserManager.kt |
| 변수 이름          | Camel Case      | userName, isLoggedIn    |
| 함수 이름          | Camel Case      | calculateSum, isValid   |
| 클래스 이름        | Pascal Case     | User, DataProcessor      |
| 상수 이름          | UPPER_SNAKE_CASE| MAX_CONNECTIONS, API_URL |
| 패키지 이름        | Lower Snake Case | com.example.myproject    |

Kotlin은 Java 생태계를 기반으로 하며, Java의 코딩 스타일을 일부 계승합니다. Camel Case와 Pascal Case는 가독성을 높이고 일관성을 유지하는 데 적합한 표기법이기 때문입니다.

- **Camel Case**: 변수와 함수 이름에 적합하며, 소문자로 시작하므로 명령어처럼 읽히는 느낌을 줍니다.
- **Pascal Case**: 클래스나 파일 이름에 적합하며, 대문자로 시작하므로 명사처럼 읽히는 느낌을 줍니다.

