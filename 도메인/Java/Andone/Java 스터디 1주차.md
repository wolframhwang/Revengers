# 1주차 JVM은 무엇이며 자바코드는 어떻게 실행하는가

## JVM이란?

Java Virtual Machine 약자로 자바가 돌아가는 가상 환경이라고 생각하면 된다. 자바의 가장 강력한 장점인 이식성을 가능하게 하는 가상머신이다.

Java는 JVM 위에서 실행되기 때문에 JVM만 각 OS에 맞춰서 설치해주면 OS에 맞춰서 Java를 새로 빌드해줄 필요가 없다.(C++은 OS에 따라 int형의 크기가 다르다.)

또한, JVM은 Garbage Collection을 하여 메모리 관리 또한 해준다.

한마디로 요약하면 __"JVM은 Java가 실행되는 가상머신 이다."__

 JVM의 동작 과정을 보기 전에 우선 Java가 컴파일 되는 과정을 알아보자.



## Java Compile 과정

1. Java 프로그램이 실행되면 우선 OS위에 JVM이 올라가기 된다.
2. Java 컴파일러(javac)가 자바 소스코드(.java)를 바이트코드(.class)로 번경한다.
3. Class Loader로 바이트코드를 JVM으로 로드시킨다.
4. 여기서 로딩된 바이트 코드를 Execution Engine으로 보내 해석한다.
5. Execution Engine에서 해석한 바이트 코드를 Runtime Data Area에 배치하여 실제로 프로그램 수행이 이루어 진다.

Java의 동작과정은 모두 JVM에서 이루어지며 이젠 JVM의 구조에데해서 알아보도록 하겠다.



## JVM 구성도

![JVM 구조](https://wikidocs.net/images/page/102803/jvm.png)

1. Class Loader : .java파일을 컴파일러(javac)로 해석하고 바이트코드를 .class 파일로 만들게 되고, 이 파일들을 엮어서 JVM이 운영체제로부터 받은 메모리 영역인 Runtime Data Area에 적재한다.
2. Execution Engine : 클래스를 실행시키는 역할을 한다. Class Loader가 Runtime Data Area에 적재한 바이트 코드들을 Execution Engine이 실행시킨다. Byte Code는 기계어는 아니고 인간이 읽기 쉬운 형태로 java 코드를 기계어로 해석하기 위한 중간단계 코드이다. 이제 중간 코드인 Byte code를 기계가 해석할 수 있는 기계어로 번역해줘야 한다.(0과 1로 이루어진 코드) 해석 방식은 두가지로 나뉘게된다.
   1. Interpreter : byte code를 명령어 단위로 읽어서 실행한다. 이 방식은 코드를 명령어 단위로 읽기 때문에 속도가 느리다는 인터프리터 언어의 단점을 그대로 가지고 있다.
   2. JIT(Just In Time) : 인터프리터 방식을 개선해준 방식이다. 기본적으로는 인터프리터 방식으로 바이트코드를 해석해주지만 적절한 시점에 바이트코드 전체를 컴파일하여 네이티브 코드로 변경해주고, 이후에는 인터프리팅을 하지 않고 네이티브 코드를 직접적으로 실행해준다. 네이티브 코드는 캐시에 보관되기 때문에 한 번 컴파일된 코드는 더 빠르게 수행한다. 하지만, 한번에 네이티브 코드로 컴파일 하는 과정은 인터프리팅 방식으로 한줄을 읽어드리는 것보다 느리기 때문에 한번 쓰고 더 이상 쓰지 않는 코드는 인터프리팅 방식으로 해석하는 것이 더 유리하다.
3. GC (Garbage Collector) : 메모리를 관리해주는 모듈로 작동 방식에 대해서는 다른 게시물에서 자세히 알아보도록 한다.

이제.. Class Loader에 의해 메모리에 적재한 정보들에 대해서 알아봅시다.

### Runtime Data Area

1. Heap : new 키워드로 생성된 인스턴스들을 보관하는 영역이다. Method 영역에 있는 정보들만 인스턴스화 가능하며 GC가 참조되지 않은 메모리를 관리하는 영역이다.
2. Method Area : 클래스 멤버 변수의 이름, 데이터 타입, 접근제어자와 같은 필드 정보와 메서드의 이름, 매개변수 타입, 리턴 타입 등을 저장하는 영역이다. Constant pool, static, final class와 같은 변수들을 이 부분에서 관리한다. 프로그램이 종료될 때 까지 유지된다.
3. Stack Area : 지역변수, 파라미터, 리턴값과 같은 연산에 사용되는 임시 값을 저장하는 영역이다.
4. PC Register : Thread가 생성될 때 마다 생성되는 영역이며 Program Counter이다. CPU에 있는 레지스터와는 다른다.
5. Native Stack Area : 자바외 언어로 작성된 네이티브 코드를 위한 메모리 영역이다.(Cpp로 작성된 네이티브 코드를 사용한는 경우가 있는데 이런 경우이다.)



# JRE VS JDK

JRE : Java Runtime Environment의 약자이며 자바를 동작시키기 위한 런타임 환경이며 동작시킬 때 필요한 라이브러리 파일들과 기타 파일들을 저장하고 있다. JVM의 실행환경을 구성한다고 생각할 수 있다.

JDK : Java Development Kit의 약자이며 자바를 개발하기 위한 도구들을 포함하고 있다. 기본적으로 JRE를 표함하고 있으며 그 외에도 javac, java등의 것들을 포함하고 있다.