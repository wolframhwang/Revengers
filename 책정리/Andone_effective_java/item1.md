# Item 1 생성자 대신 정적 팩터리 메서드를 고려하라

## 정적 팩터리 메서드

정적 팩터리 메서드란 그 클래스의 인스턴스를 반환하는 단순한 정적인 메서드를 의미한다.

아래는 그 예인 Boolean의 valueOf 코드이다.

```java
public static Boolean valueOf(boolean b) {
  return b? Boolean.TRUE : Boolean.FALSE;
}
```

일반적으로 public 생성자로 인스턴스를 얻으나 class는 이 방법 말고도 "정적 팩터리 메서드"를 이용할 수 있다.

※ 주의할 점은 디자인 패턴에서의 팩터리 메서드와는 다르다는 점이다.

이 방식에는 장점과 단점이 함께 존재한다.

### 장점

#### 1. 이름을 가질 수 있다.

생성자에 넘기는 매개변수와 생성자 자체만으로는 반환될 객체의 특성을 하기 힘들다. 예를들어 BigInteger(int, int, Random)이라는 생성자가 있고 BigInteger.probablePrime중 어떤값이 소수인 BigInteger를 반환하는지를 보았을때 후자가 명확함을 알 수 있다. 또한, 하나의 시그니처로는 하나의 생성자만 만들 수 있다. 입력 매개변수 순서를 바꿈으로서 이를 회피할 수는 있지만 코드를 읽는데 매우 좋지 않다. 이를 정적 팩토리 메서드로 해결 할 수 있다.

#### 2. 호출될 때 마다 인스턴스를 새로 만들지 않아도 된다.

불변 클래스는 인스턴스를 미리 만들어 놓거나 새로 생성한 인스턴스를 캐싱하여 재활용 하는 식으로 불필요한 객체 생성을 막을 수 있다. 특히, 생성비용이 큰 객체의 경우 성능을 상당히 끌어올릴 수 있다. 플라이 웨이트 패턴도 이와 유사하다.

정적 팩터리 방식을 통해 인스턴스가 살아있는 기간을 철저히 통재할 수 있으며 

#### 3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있으며 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.

반환할 객체를 마음대로 선택할 수 있다. API를 만들때 이를 이용해 API를 작게 유지할 수 있다. interface에서도 정적 메서드를 선언 할 수 있어서 그것으로 처리한다. 예를들어 Basic, Intermediate, Advanced 클래스가 Level이라는 상위 타입을 상속받고 있는 구조를 가정해보자.

```java
public class Level {
  public static Level of(int score) {
    if(score < 50) {
      return new Basic();
    } else if(score<80) {
      return new Intermediate();
    } else {
      return new Advanced();
    }
  }
}
```

이 경우에 생성자 역할을 하는 정적 팩토리 메서드를 통해 분기처리를 할 수 있다. 또한 컬랙션 프레임워크는 핵심 인터페이스들에 수정 불가나 동기화 등의 기능을 덧붙인 총 45개의 유틸리티 구현체를 제공하는데, 이 구현체 대부분을 단 하나의 인스턴스화 불가 클래스인 java.util.Collections에서 정적 팩터리 메서드를 통해 얻는다.

#### 4. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.

이 유연함은 서비스 제공자 프레임워크를 만드는 근간이며 대표적인 서비스 제공자 프레임워크로는 JDBC가 있다.

```java
public static void main(String[] args) {
	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/test";
	String user = "root";
	String password = "soyeon";
    
	try {
		Class.forName(driverName);

		// 서비스 접근 API인 DriverManager.getConnection가 서비스 구현체(서비스 인터페이스)인 Connection 반환
		Connection connection = DriverManager.getConnection(url, user, password);

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

출처 : https://github.com/2021BookChallenge/Effective-Java/issues/1
```

위 코드는 jdbc의 드라이버를 설정 후 커넥션을 맺는 과정을 보여주고 있다. 

코드를 보면 DriverManager를 통해 커넥션을 얻어오는데 그 이전에 Class.forName을 이용한 리플렉션에 의해 mysql.jdbc.Driver를 설정한것을 알 수 있다. 그 아래 드라이버 매니저 코드에선 따로 드라이버를 설정하는 내용이 없는데 이것을 Class.forName의 리플렉션을 통해 설정을 해준다. 이것을 가능하게 하는것이 반환할 객체의 클래스가 존재하지 않아도 상관이 없다.



### 단점

1. 상속을 하려면 public이나 protected생성자가 필요하니 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다. 
   앞서 말한것 처럼 컬랙션 프레임워크의 유틸리티 구현 클래스를 상속할 수 없다. 다만 이 제약은 상속보다 컴포지션을 유도하게 되어 장점이 될 수도 있다.

2. 정적 팩터리 메서드는 프로그래머가 찾기 힘들다. 생성자 처럼 API 설명에 바로 드러나지 않으며 사용자는 정적 메서드 방식 클래스를 인스턴스화 할 방법을 찾아야한다. 따라서 naming을 최대한 표준에 맞도록 정해주는 것이 중요하다. 네이밍 규칙은 다음과 같다.

   from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드이다.

   of : 여러개의 매개변수를 받아서 적합한 타입의 인스턴스를 반환하는 집계 메서드이다.

   valueOf: from과 of의 더 자세한 버전이다.

   instance 혹은 getInstance : (매개변수를 받는다면) 매개변수로 명시한 인스턴스를 반환하지만, 같은 인스턴스임을 보장하진 않는다.

   created 혹인 newInstance : instance 혹은 getInstance와 같지만, 매번 새로운 인스턴스를 생성해 반환함을 보장한다.

   getType : getInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다. Type은 팩터리 메서드가 반환할 객체의 타입이다.

   type : getType과 newType의 간결한 버전.

