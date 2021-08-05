# Item 10 equals는 일반 규약을 지켜 재정의하라

equals는 기본적으로 재정의 하지 않는것이 가장 좋다.

1. 각 인스턴스는 본질적으로 고유하다
2. 인스턴스의 논리적 동치성을 검사할 일이 없다.
3. 상위 클래스에서 재정의한 equals가 하위 클래스에더 딱 들어맞는다.
4. 클래스가 private이거나 package-private이고 equals 메서드를 호출할 일이 없다. 만약 equals를 호출할 수 없도록 만드려면 아래와 같이 override하면 된다.

```java
@Override
public boolean equals(Object o) {
  throw new AssertionError();
}
```



기본적으로는 equals는 재정의 하지 않는 것이 원칙이지만 특정 상황에서는 재정의 해야한다.

객체의 식별성(두 객체가 물리적으로 같은 것인지)를 비교하는 것이 아니라 논리적인 동치성을 비교해야하는 경우인데, 만약 상위의 equals 함수가 논리적 동치성을 검사하도록 재정의 해야한다. 주로 값 클래스(String, Integer 등)이 여기에 해당된다.

하지만, 값 클래스여도 Enum과 같이 하나의 인스턴스만 생성된다는 것이 보장되는 경우는 재정의 하지 않아도 된다.

논리적인 동치를 보여야할때 아래와 같은 규약을 지켜야 한다.

1. 반사성(reflexivity) : null이 아닌 모든 참조 값 x에 대해, x.equlas(x)는 true이다.
2. 대칭성(symmetry) : null이 아닌 모든 참조 값 x,y에 대해, x.equals(y)가 true면 y.equals(x)도 true이다.
3. 추이성(transitivity) : null이 아닌 모든 참조 값 x,y,z에 대해, x.equals(y)가 true이고 y.equals(z)가 true이면 z.equals(x)도 true이다.
4. 일관성(consistency) : null이 아닌 모든 참조값 x,y에 대해 x.equals(y)가 true이면 여러번 실행해도 true가 나와야 한다.
5. Null-아님 : null이 아닌 모든 참조값 x에 대해 x.equals(null)은 false이다.

이러한 내용들은 기본적으로 수학에서 나온 개념들이랑 동일히다.

모든 객체 지향 언어의 동치관계에서 나타나는 근본적인 문제인데 

*__구체클래스를 확장해 새로운 값을 추가하면  객체 지향적 추상화의 이점을 포기하지 않는 한 equals 규약을 만족시킬 방법은 존재하지 않는다.__*

```java
@Override
public boolean equals(Object o) {
  if (o == null || o.getClass() != getClasS())
    return false;
  Point p = (Point)o;
  return p.x == x && p.y == y;
}
```

위 코드를 보면 equals로 Point class에 대해서 논리적인 동치성을 알 수 있다. 

하지만 사용할 수는 없다. 그 이유는 Point 객체를 상속받는 하위 class에서는 해당 equals 메서드를 사용할 수 없다. 구체클래스에 새로운 값이 추가되어서 Point가 Point로 활용될 수 없기 떄문이다.

리스코프 치환 원칙에 따르면, 어떤 타입에 있어 중요한 속성이라면 그 하위 타입에서도 마찬가지로 중요하다. 따라서, 그 타입의 모든 메서드가 하위 타입에서도 똑같이 잘 동작해야 한다. 이는 Point의 하위 클래스도 정의상으론 여전히 Point이기 때문에 어디서든 Point로서 활용될 수 있어야 한다는 의미이다.

*__클래스가 불변이든 가변이든 equals의 판단에 신뢰할 수 없는 자원이 끼어들게 해서는 안 된다.__*

java.net.URL의 경우 equals는 주어진 URL과 매핑된 호스트의 IP주소로 비교한다. 호스트 이름을 IP주소로 바꾸려면 네트워크를 통과해야하는데 이것이 항상 같다고 말할 수 없어 이렇게 정의 해서는 안된다.

위와 같은 모든 조건들을 만족 시키기 위해서는 아래와 같은 단계로 순서로 구성해야한다.

1.  == 연산자를 활용하여 입력이 자기 자신의 참조인지 확인한다.
2. instanceof 연산자로 입력이 올바른 타입인지 확인한다.
3. 입력을 올바른 타입으로 형변환한다.
4. 입력 객체와 자기 자신의 대응되는 "핵심"필드들이 모두 일치하는지 하나씩 검사한다.