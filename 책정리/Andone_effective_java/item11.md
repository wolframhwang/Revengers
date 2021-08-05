# Item 11 equals를 재정의하려거든 hashCode도 재정의하라

해당 원칙을 지키지 않으면 hashCode의 일반 규약을 어기게되어 해당 클래스의 인스턴스를 HashMap이나 HashSet 같은 컬렉션의 원소로 사영할 때 문제를 일으킬 것이다. 

*__equals가 두 객체를 같다고 판단했다면, 두 객체의 hashCode는 똑같은 값을 반환해야 한다. __*

논리적으로 같은 객체는 같은 hashCode를 반환해야 한다. equals는 물리적으로 다른 두 객체가 논리적으로 갑ㅌ다고 판단하지만 Object의 기본 hashCode Method는 이 둘이 전혀 다르다고 판단하여, 규약과 달리 (무작위처럼 보이는) 서로 다른 값을 반환한다.

HashMap에서 예를들어 m.put(new Point(1,3), "진우"); 라는 코드가 있고 m.get(new Point(1,3))을 하게 되면 Null이 나오게 된다.

두 인스턴스는 논리적으로는 동치이나 두개의 해시코드는 다르기 때문에 엉뚱한 해시 버킷에가서 객체를 찾으로 하게 된다.

이러한 문제는 Point의 hashCode를 메서드를 재정의 하면 끝나는 일이다.

```java
@Override
public int hashCode() {
  return 42;
}
```

위처럼 정의하면 동치인 모든 객체에서 똑같은 해시코드를 반환하니 적법하다. 하지만 모든 객체에서 똑같은 해시코드를 반환해주기 때문에 계속 같은 버킷을 찾게 되고 원래는 O(1)로 동작해야하는 HashMap이 O(N)으로 수행하게 되어 성능적인 손해를 보게 된다.

이상적인 해시함수는 서로 다른 인스턴스들에 대해 32비트 정수 범위에 균일하게 분배해야 한다. 다음은 좋은 hashCode를 작성하는 간단한 요령이다.

1. int 변수 result를 선언한 후 값 c로 초기화 한다. 이때 c는 해당 객체의 첫번째 핵심 필드를 단계2.a 방식으로 계산한 해시코드다(여기서 핵심 필드란 equals에서 비교에 사용되는 필드를 말한다.)
2. 해당 객체의 나머지 핵심 필드 f 각각에 대해 다음 작업을 수행한다.
   1. 해당 필드의 해시코드 c를 계산한다.
      1. 기본 타입 필드라면, Type.hashCode(f)를 수행한다. 여기서 Type은 해당 기본 타입의 박싱 클래스이다.
      2. 참조 타입 필드면서 이 클래스의 equals 메서드가 이 필드의 equals를 재귀적으로 호출해 비교한다면, 이 필드의 hashCode를 재귀적으로 호출한다. 계산이 더 복잡해 질 것 같으면, 이 필드의 표준형을 만들어 그 표준형의 hashCode를 호출한다.
      3. 필드가 배열이라면, 핵심 원소 각각을 별도 필드처럼 다룬다. 이상의 규칙을 재귀적으로 적용해 각 핵심 원소의 해시코드를 계산한 다음, 단계 2.b 방식으로 갱신한다. 배열에 핵심 원소가 없다면 단순히 상수(0을 추천)를 사용한다. 모든 원소가 핵심 원소라면 Arrays.hashCode를 사용한다.
   2. 단계 2.a에서 계산한 해시코드 c로 result를 갱신한다. 코드로는 다음과 같다.
      result = 31 * result + c;
   3. result를 반환한다.

자.. 이제 위에서 배운 것을 가지고 Point의 hashCode를 재정의 해보자.

```java
@Override
public int hashCode() {
  int result = 1;
  result = 31 * result + Short.hashCode(x);
  result = 31 * result + Short.hashCode(y);
  return result;
}
```

다음과 같이 정의하면 31은 홀수이고 소수이므로 해당 해시값은 논리적인 동치라면 같은 값을 반환하게 된다.

해시충돌이 가장 작게 하고 싶다면 구아바의 com.google.common.hash.Hashing을 참고하자.

Object도 hash함수를 제공하여 한줄로 코드 구현이 가능하다. 다만 속도는 조금 느리다.

```java
@Override
public int hashCode() {
  return Object.hash(x,y);
}
```

클래스가 불변이고 해시코드를 계산하는 비용이 크다면, 매번 새로 계산을 하기 보다는 캐싱을 하는 전략도 고려해야한다.

hashCode가 처음 불릴 때만 계산하는 지연초기화 방식을 사용하면 된다. 다만 클래스가 스레드 안전하게 만들도록 신경써야한다.

```java
private int hashCode;// 자동으로 0으로 초기화

@Override
public int hashCode() {
  int result = hashCode;
  if(hashCode == 0) {
    result = 31 * result + Short.hashCode(x);
  	result = 31 * result + Short.hashCode(y);
    hashCode = result;
  }
  return result;
}
```

*__hashCode가 반환하는 값의 생성 규칙을 API 사용자에게 자세히 공표하지 말자. 그래야 클라이언트가 이 값에 의지하지 않게 되고, 추후에 계산 방식을 바꿀수도 있다. __* 이미 자바에서는 해당 규칙을 정확히 알려주는 경우가 있었으나 해당 방식은 좋지 않다.