- 스프링쫌 다시 파기전에 객체지향이랑 자바 기본좀 보고 갑네다잉
- 코드는 내꺼 로컬에만 올린다

# 객체지향 프로그래밍

## 객체 지향 입문

### 01.객체와 객체지향 프로그래밍
- 객체는 의사나 행위가 미치는 대상
- 절차지향 프로그래밍은 시간이나 사건의 순서대로 프로그래밍함
- 객체지향 프로그래밍은 객체의 상관관계에 따라 프로그래밍함
- 객체지향 프로그래밍 언어에는 c++, c#, python, javascript 등이 있음
- 객체 지향 프로그래밍은 객체를 정의하고, 기능을 구현하고 각 객체간의 소통을 통해 협력을 구현

### 02. 생활 속에서 객체 찾아 클래스로 구현해보기
- 클래스는 객체의 청사진임
- 객체의 속성은 클래스의 맴버변수로 선언(ex. 학생 클래스 내의 학번, 이름, 학년 등)
- 객체를 정의하고 속성을 메서드로 구현하고, 객체간의 협력을 구현
- 클래스는 대문자로 시작하는 것이 좋고 java 클래스는 여러개 있을 수 있지만 public 클래스는 하나이고 public 클래스와 java 파일의 이름은 동일함

### 03. 함수와 메서드
- 함수는 하나의 기능을 수행하는 일련의 코드
- 함수는 호출해서 사용하고 수행이 끝나면 제어가 반환됨
- 함수로 구현된 기능은 다른 함수 등 여러곳에서 동일한 방식으로 호출되어 사용 가능
- 함수의 이름은 그 함수의 동작을 한눈에 알아볼 수 있도록 지어야 좋음
- 함수를 호출 시에 지역 변수들이 스택에 저장되고 함수를 호출하며, 함수가 끝나면 다시 스택에 저장된 지역변수들이 반환된다
- 메서드는 클래스 내부에 구현되는 함수로 멤버 함수라고도 함

### 04. 객체의 속성은 멤버 변수로, 객체의 기능은 메서드로 구현한다
- 예제 : 객체지향 프로그래밍 - ch4

### 05. 인스턴스 생성과 힙 메모리
- 객체를 new 해서 생성한 것이 인스턴스
- 인스턴스는 힙이라는 메모리에 들어가게 됨
- 인스턴스
  - 클래스는 객체의 속성을 정의하고, 기능을 구현해 놓은 코드 상태
  - 실제 클래스 기반으로 생성된 객체(인스턴스)는 각각 다른 멤버 변수 값을 가짐
  - new 키워드를 통해 생성
- 힙 메모리
  - 인스턴스는 동적 메모리인 힙에 할당
  - c나 c++은 사용한 동적 메모리를 프로그래머가 해제 시켜야함
  - 자바는 GC(Gabage Collector)에 의해서 자동으로 메모리가 할당 해제됨
  - 하나의 클래스에서 여러개의 인스턴스를 생성할 수 있으며 각각 다른 힙메모리 공간에 할당됨

### 06. 생성자에 대해 알아봅시다.
- 생성자의 기본 문법은 <class_name>([<argument_list]){[<statements]}의 형태
- 객체를 생성할 때에는 new 키워드를 함께 사용
- 생성자는 일반 함수처럼 기능을 호출하는 것이 아니고 객체를 생성하기 위해 new와 함께 생성되는 것
- 객체가 생성될 때 변수나 상수를 초기화하거나 다른 초기화 기능을 수행하는 메서드가 호출됨
- 생성자는 반환값이 없음
- private로 선언하면 외부에서 접근할 수 없음
- 기본 생성자
  - 클래스에는 반드시 하나 이상의 생성자 존재
  - 클래스에 생성자를 구현하지 않아도 new 키워드와 함께 생성자 호출 가능
  - 클래스에 생성자가 없으면 컴파일러가 생성자 코드 넣어줌
  - 매개변수 없고, 구현부도 없음
  - 기본 생성자는 클래스와 이름이 같음
- 생성자로 구현된 예시 - 객체지향프로그래밍/ch6

### 07. 여러가지 생성자를 정의하는 생성자 오버로딩(overloading)
- 생성자를 여러개 정의할 수 있는데 이것을 생성자 오버로딩이라고 함
- 오버로딩을 제공하는 이유는 사용하기 편하게 하기 위함(매개변수가 필요한 갯수가 다를 수 있음)
- 생성자에 this를 써야 변수명이 같을 때 구분 가능
- 무조건 생성자 오버로딩을 많이 제공하는게 좋은것은 아니지만 적정한만큼 지원해주면 좋음

### 08. 복습해봅시다(객체 구현하기)
- 객체지향프로그래밍 - ch8

### 09. 참조 자료형 변수
- 자료형에는 여러개지가 있음
  - 변수의 자료형(기본 자료형(int, double 등), 참조 자료형(String, Date))
- 참조 자료형은 클래스를 통해 선언
- 기본 자료형은 크기가 고정이지만 참조 자료형은 클래스에 따라서 다름
- 참조 자료형을 사용할 때에는 해당 변수에 대한 생성을 먼저 해줘야함
- 참조 자료형을 사용할 때에 적당히 분리해서 사용해야함(Student 클래스에 모든게 다 들어있는게 아닌 성적같은 경우 Subject 클래스를 따로 만들어서 사용)
- 객체지향 프로그래밍 - ch9

### 10. 접근 제어 지시자(access modifier)와 정보은닉(information hiding)
- 접근 제어 지시자는 클래스 외부에서 클래스 내부의 변수나 생성자 등에 접근할 수 있는지 없는지를 지정해주는 것을 말함
- private, protected, public, 아무것도 없음(default)이 있음
- private는 같은 클래스 내부에서만 접근 가능
- protected는 같은 패키지나 상속관계인 경우에만 접근가능
- 아무것도 없으면 같은 패키지 내에서만 접근 가능
- public은 아무나 접근 가능
- get(), set() 메서드는 private로 선언된 멤버 변수에 접근하거나 수정할 때 사용
- get()만 선언되면 read-only인 필드임
- 이클립스에서는 get()/set()메서드를 자동으로 생성해줌
  - 소스에서 우클릭 - source - generate getters and setters 클릭
- private를 사용하여 정보 은닉을 할 수 있는데 public으로 된 메서드를 사용하여 접근, 수정할 수 있다
- 객체지향프로그래밍 - ch10

### 11. 캡슐화 (encapsulation)
- 캡슐화는 외부에서 꼭 필요한 정보만 오픈하고 나머지는 숨김
- 대부분의 멤버 변수와 메서드는 감추고 외부에 인터페이스만 제공하여 일관된 기능을 구현하게 함
- 오류를 최소화할 수 있음

### 12. 객체 자신을 가리키는 this
- this는 인스턴스 자신의 메모리를 가리킴
- 생성자에서 또 다른 생성자를 호출 할때 사용
- 자신의 주소값을 반환함
- this.year 하면 내 인스턴스에 선언된 year 변수를 말하는 것
- 물론 다른 생성자를 호출할 때에는 this(변수1, 변수2); 이런식으로 호출해 줄 수 있음

### 13. 객체 간의 협력(collaboration)
- 객체가 다른 객체랑 협력이 이루어짐
- 협력을 위해서는 메시지를 전송하고 이를 처리하는 기능 필요
- 매개 변수로 객체가 전달되는 경우 발생
- 학생 객체가 버스 객체와 상호작용하거나 지하철 객체랑 상호작용 등

### 14. 버스 타고 학교 가는 학생의 과정을 객체 지향 프로그래밍으로 구현해보기
- 객체지향프로그래밍 - ch14

### 15. 복습해봅시다 (객체 협력)
- 그냥 복습임

### 16. 여러 인스턴스에서 공통으로 사용하는 변수를 선언하자
- 한 클래스로 선언한 여러 인스턴스가 공유하는 기준값이 필요한 경우가 있음
- 이런경우 static 변수를 사용하면 됨
- 예를들어서 사번같은것 만들면 중복되지않고 순차적으로 만들어야 하는데 이럴 때 사용
- 타입 앞에 static을 써서 선언
- 사용할 때에는 클래스명으로 호출(인스턴스명이 아니라)
- 객체지향 프로그래밍 - ch16

### 17. static 메서드의 구현과 활용, 변수의 유효 범위
- static 변수는 굉장히 중요한 변수이기때문에 public보다는 private로 선언해주는게 좋음(외부에서 못바꾸게)
- 스태틱 메서드 안에서는 일반 맴버변수를 사용할 수 없음(이 함수를 호출하는 시기에 선언이 되어있지 않을 수 있기 때문에)
- 반대로 일반 메서드 안에서는 스태틱 변수를 사용할 수 있음
- 변수는 크게 지역변수, 멤버 변수, static 변수가 있다고 보면 된다
- 객체지향 프로그래밍 - ch17

### 18. static 응용 - 싱글톤 패턴
- 싱글톤 패턴이란 프로그램에서 인스턴스가 단 한개만 생성되어야 하는 경우 사용하는 디자인 패턴임(ex. 회사는 유일하게 하나만 있어야한다)
- 패턴의 경우 이렇게 하면 좋다라는 권고사항이지 필수사항은 아님
- 생성자는 private로 선언하고 클래스 내부에 유일한 private 인스턴스 생성, 외부에서 유일한 인스턴스를 참조할 수 있도록 public 메서드를 제공
- 객체지향 프로그래밍 - ch18

### 19. 복습해봅시다(static과 싱글톤 패턴)
- 그냥 복습임

### 20. 자료를 순차적으로 한꺼번에 관리하는 방법 - 배열(array)
- 배열은 동일한 자료형의 순차적 자료구조
- 인덱스 연산자를 통해 빠르게 참조 가능
- 물리적 위치와 논리적 위치가 동일
- 배열의 순서는 0부터 시작
- 자바에서는 객체 배열을 구현한 ArrayList를 많이 활용
- 자바에서 배열 선언은 int arr1[] = new int[10]; 이런식으로 선언
- 초기화가 필요할 경우 바로 초기화 가능 int[] numbers = {10,20,30}; // new는 생략
- 배열의 길이와 요소의 갯수는 다를 수 있음(배열 공간만 잡아놓고 값 할당을 안해놓은 경우)
- 배열의 갯수만큼 반복이 필요할 때에는 for(변수 : 배열){} 이런식으로도 사용 가능(ex. for(char a : arrs){})
