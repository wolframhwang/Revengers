# Item 14 Comparable을 구현할지 고려하라

## Comparable 인터페이스

Comparable 인터페이스의 유일무이한 메서드는 compareTo이며 Object의 메서드가 아니다.

성격은 두가지만 빼면 Object의 equals와 같다. 다른점은 무엇일까?

1. compareTo는 단순 동치성 비교에 더해 순서까지 비교할 수 있으며 제네릭하다.
2. Comparable을 구현했다는 것은 그 클래스의 인스턴스들에는 자연젃인 순서(natural order)

그래서 Comparable을 구현한 객체들의 배열은 다음처럼 손쉽게 정렬할 수 있다.

Arrays.sort(a)

또한, 컬랙션의 경우에도 쉽게 정렬할 수 있는데, String이 Comparable을 알파뱃 순서로 구현했기에 가능한 일이다.(아래 예는 TreeSet이 자동으로 정렬되는 코드이다.)

```java
public class WordList {
  public static void main() {
    Set<String> s = new TreeSet<>();
    Collections.addAll(s, args);
    System.out.println(s);
  }
}
```

Comparable의 원형은 다음과 같다.

```java
public interface Comparable<T> {
  int compareTo(T t);
}
```

compareTo 메서드의 일반 규약은 equals와 비슷한데 다음과 같다. 다음에서 설명하는 sgn(표현식) 표기는 수학에서 말하는 부호함수를 의미하며 표현식의 값이 음수, 0, 양수일 때 -1, 0 ,1을 반환하도록 정의했다.

1. 이 객체와 주어진 객체의 순서를 비교한다. 이 객체가 주어진 객체보다 작으면 음의 정수, 같으면 0, 크면 양의 정수를 반환한다. 만약 비교할 수 없다면 ClassCastException을 던진다.
2. Comparable을 구현한 클래스는 모든 x,y에 대해 sgn(x.comparaTo(y)) == -sgn(y,compareTo(x))여야 한다.
3. Comparable을 구현한 클래스는 추이성을 보장해야 한다. 즉, (x.compareTo(y) > 0 && y.compareTo(z) > 0)이면 x.compareTo(z) > 0 이다.
4. Comparable을 구현한 클래스는 모든 z에 대해 x.compareTo(y) == 0이면 sgn(x.compareTo(z)) == sgn(y.compareTo(z)) 다.
5. 이 권고는 필수는 아니지만 만족시키면 좋다. (x.compareTo(y) == 0) == (x.equals(y))여야 한다. 만약 지키지 않는다면 반드시 명시해야한다.

1, 2, 3번은 equals와 같은 이야기이고 주의사항도 똑같다.

5번 사항은 지켜지지 않아도 동작은 잘 하지만 이 객체를 정렬된 컬랙션에 넣으면 해당 컬렉션이 구현한 인터페이스(Collection, Set, Map)에 정의된 동작과 엇박자를 낼 것이다. 이 인터페이스들은 equals의 메서드 규약을 따르기 떄문이다. 하지만 놀랍게도 정렬된 컬랙션들은 동치성을 비교할 때에는 compareTo를 이용하기 때	[] 

Comparble을 만족하면 다른 객체는 신경쓰지 않아도 되며 만약 다른 객체가 들어오면 예외를 던지면 된다. 

검색과 정렬 알고리즘을 활용하는 Collections와 Arrays가 있다.



## compareTo 메서드 작성 요령

equals와 유사하다. 몇가지 차이점만 주의하면 된다.

1. Comparable을 타입을 인수로 받는 제네릭 인터페이스이므로 compareTo 메서드의 인수 타입은 컴파일 타임에 정해진다. 따라서 입력인수의 타입을 확인하거나 형변환할 필요가 없다는 뜻이다. 인수타입이 잘못되었다면 컴파일 자체가 되지 않는다.
2. compareTo 메서드는 각 필드가 동치인지를 비교하는게 아니라 그 순서를 비교한다. 객체 참조 필드를 비교하려면 compareTo 메서드를 재귀적으로 호출한다.
3. Comparable을 구현하지 않은 클래스의 경우 Comparator를 사용하면 된다.

```java
public final class CaseInsensitiveString implements Comparable<CaseInsensitiveString> {
  public int compareTo(CaseInsensitiveString cis) {
    return String.CASE_InSENSITIVE_ORDER.comapre(s, cis.s);
  }
}
```

위 코드는 객체 참조 필드가 하나뿐인 비교자이며 CaseInsensitiveString이 Comparable\<CaseInsensitiveString\>을 구현한 것에 주목해야 한다.

즉, CaseInsensitiveString의 참조는 CaseInsensitiveString참조와만 비교할 수 있다는 뜻이며, Comparable을 구현할 때 일반적으로 따르는 패턴이다.

옛날에는 기본 타입을 비교할 때 <,>와 같은걸 쓰고 정적메서드인 Double.compare와 같은 것을 사용할 것을 권장하였으나 java7부터 상황이 바뀌었다. 박싱된 기본 타입 클래스에 새로 추가된 정적 메서드인 comparefmf dldydgkaus ehlsek. *__compareTo 메서드에서 관계 연산자 <와>를 이용하는 이전 방식은 거추장스럽고 오류를 유발하니 이제는 추천하지 않는다.__*

값을 여러개 가진 객체의 경우 중요도 순서로 정렬을 하도록 짜면 된다. 아래는 10장의 여러 값을 가진 객체인 PhoneNumber 클래스용 compareTo 메서드를이 방식으로 구현한 예제이다.

```java
public int comapreTo(PhoneNumber pn) {
  int result = Short.compare(areaCode, pn,areaCode);
  if(result == 0) {
    result = Short.compare(prefix, pn.prefix);
    if(result == 0)
      result = Short.compare(lineNum, pn.lineNum);
  }
}
```

java8 부터는 Comparator 인터페이스가 일련의 비교자 생성 메서드와 팀을 꾸려 메서드 연쇄 방식으로 비교자를 생성할 수 있게 되었다. 짧고 간결하지만 다만 속도는 조금 느려질 수 있다.

```java
private static final Comparator<PhoneNumber> COMPARATOR =
  comaparingInt((PhoneNumber pn)->pn.areaCode)
  .thenComapringInt(pn->pn.prefix)
  .thenComapringInt(pn->pn.lineNum);

public int compareTo(PhoneNumber pn) {
  return COMPARATOR.compare(this. pn);
}
```

이 코드는 클래스를 초기화할 때 비교자 생성 메서드 2개를 이용해 비교자를 생성한다.



가끔 값의 차를 기준으로 비교를 하는 예가 있다.

```java
static Comparator<Object> hashCodeOrder = new Comparator<>() {
  public int comapre(Object o1, Object o2) {
    return o1.hashCode() - o2.hashCode();
  }
}
```

참고로 이 방식은 사용해서는 안된다. 왜냐하면 정수 오버플로우를 일으킬 수 있고(너무 큰값을 뺀다면) 부동소수점 오류를 발생시킬 수 있기 때문이다. 따라서 두가지 방식 중 하나를 사용하자.

1. 정적 compare 메서드를 활용한 비교자.

```java
static Comparator<Object> hashCodeOrder = new Comparator<>() {
  public int compare(Object o1, Object o2) {
    return Integer.compare(o1.hashCode(), o2.hashCode());
  }
}
```

2. 비교자 생성 메서드를 활용한 비교자

```java
static Comparator<Object> hashCodeOrder =
  Comparator.comparingInt(o->o.hashCode());
```



