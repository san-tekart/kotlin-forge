# Kotlin 설치 및 환경 설정

## Java 설치

자바를 다운로드하려면 [Oracle의 공식 웹사이트](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)로 이동하여 JDK를 다운로드합니다.

### 우분투에서 JAVA_HOME 확인 및 추가
JAVA_HOME 환경 변수를 확인하고 설정하는 방법:

echo $JAVA_HOME
readlink -f $(which java)
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

이 설정을 영구적으로 유지하려면 ~/.bashrc 또는 ~/.zshrc 파일에 추가하세요.

### 윈도우즈에서 JAVA_HOME 확인 및 추가

## Gradle 설치
1. [Gradle 공식 사이트](https://gradle.org/releases/)에서 최신 버전 다운로드
2. 압축 해제 후 원하는 경로에 설치 (예: `C:\Gradle`)
3. 환경 변수 설정:
   - `GRADLE_HOME`: Gradle 설치 경로
   - `Path`에 `%GRADLE_HOME%\bin` 추가

## 2. VS Code 확장 프로그램 설치
VS Code 마켓플레이스에서 다음 확장 프로그램들을 설치:

1. **Extension Pack for Java**
2. **Kotlin Language**
3. **Kotlin Debug Adapter**

## 3. 새 코틀린 프로젝트 생성

1. 새 폴더 생성 후 VS Code로 열기
2. 터미널에서 다음 명령어 실행:
