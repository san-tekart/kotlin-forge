
# Kotlin 개발 환경 설정 및 Gradle 문제 해결

## 1. 오류 발생
Gradle을 실행할 때 다음과 같은 오류가 발생했습니다:

san@San:~/dev/kotlin-forge/basics/hello$ ./gradlew run

FAILURE: Build failed with an exception.

* What went wrong:
Could not determine java version from '11.0.25'.

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

## 2. 우분투에서 JAVA_HOME 확인 및 추가
JAVA_HOME 환경 변수를 확인하고 설정하는 방법:

echo $JAVA_HOME
readlink -f $(which java)
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

이 설정을 영구적으로 유지하려면 ~/.bashrc 또는 ~/.zshrc 파일에 추가하세요.

## 3. gradle-wrapper.properties 수정
gradle-wrapper.properties 파일을 열고 distributionUrl을 다음과 같이 수정하세요:

distributionUrl=https\://services.gradle.org/distributions/gradle-7.5-bin.zip

## 4. Gradle 명령어 실행
Gradle 캐시를 정리하고 버전을 확인합니다:

./gradlew clean
./gradlew --version --stacktrace

## 5. build.gradle 수정
build.gradle 파일을 다음과 같이 수정합니다:

plugins {
    id 'org.jetbrains.kotlin.jvm' version '1.8.0'
    id 'application'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib"
}

application {
    mainClass = 'MainKt'
}

## 6. 프로젝트 구조
Main.kt 파일은 project/src/main/kotlin 아래에 있어야 합니다.
이는 Gradle의 기본 규칙(convention)으로, 특별한 설정이 없으면 Gradle은 자동으로 이 경로에서 소스 코드를 찾습니다.

프로젝트 생성 순서:
1. gradle init으로 프로젝트 초기화
2. src/main/kotlin 디렉토리 생성
3. Main.kt 파일을 해당 위치에 생성

이렇게 하면 Gradle의 표준 디렉토리 구조를 따르게 되어, 별도의 sourceSets 설정 없이도 Gradle이 자동으로 소스 코드를 인식할 수 있습니다.
