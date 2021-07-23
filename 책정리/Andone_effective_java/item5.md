# Item 5 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라

많은 클레스가 하나 이상의 자원에 의존한다. 이러한 클래스를 정적 유틸리티 클래스로 구현하는 경우가 종종 있다. 

```java
public class SpellChecker {
  private static final Lexicon dictionary = ...; //사전에 의존
  
  private SpellChecker() {} //객체 생성 방지
  
  public static boolean isValid(String word) {...}
  public static List<String> suggestions(String typo) {...}
}
```

또는 싱글턴으로 구현하는 경우도 많다.

```java
public class SpellChecker {
  private static final Lexicon dictionary = ...; //사전에 의존
  
  private SpellChecker() {} //객체 생성 방지
  public static SpellChecker INSTANCE = new SpellChecker(...);
  
  public static boolean isValid(String word) {...}
  public static List<String> suggestions(String typo) {...}
}
```

하지만 사전이 언어별로 따로 있고 특수 어휘용 사전을 따로 사용할 수도 있다. 따라서 이 사전 하나로 사용하기에 무리가 있다.

final 한정자를 제거하고 다른 사전으로 교체하는 메서드를 교체하는 메서드를 추가할 수 있지만, 오류를 내기 쉬우며 멀티스레드에서는 사용할 수 없다.

따라서 인스턴스가 필요할때 외부에서 생성자를 주입해주는 방식을 통해서 유연함을 확보한다.

```java
public class SpellChecker {
  private final Lexicon dictionary = ...; //사전에 의존

  private SpellChecker(Lexicon dictionary) {
    this.dictionary = Objects.requireNonNull(dictionary);
  } //객체 생성 방지
  public static SpellChecker INSTANCE = new SpellChecker(...);

  public static boolean isValid(String word) {...}
  public static List<String> suggestions(String typo) {...}
}
```



## 팩터리 메서드 패턴

이 방식을 변형하여 생성자에 자원 팩터리를 넘겨주는 방식이 있다. 팩터리는 호출할 때 마다 특정 타입의 인스턴스를 반복하여 생성해주는 객체를 말한다. 즉, 팩터리 메서드 패턴을 구현한 것이며, Java8에서 새로 지원하는 Supplier<T> Interface가 팩터리를 완벽하게 표현하였다

Supplier는 받는 제네릭을 반드시 extends를 통해서 받을 수 있는 객체를 제한해 줘야한다.



## 단점

하지만 이러한 생성자 주입은 유연성과 테스트 용이성을 개선해주나, 의존성이 수천개가 되는 경우는 프로그래밍을 힘들게 한다. 하지만, Spring과 같은 프레임워크를 통해서 더 용이하게 관리 할 수 있다.