# item 13 clone 재정의는 주의해서 진행해라

Cloneable은 복제해도 되는 클래스임을 명시하는 용도의 믹스인 인터페이스지만, 아쉽게도 제대로된 목적을 이루지 못했다. 하지만 가장 큰 문제는 clone 메서드가 Object에 정의되어 있고 protected여서 Cloneable을 구현하는 것만으로는 외부 객체에서 clone 메서드를 호출할 수 없다. 다만 리플랙션을 사용하면 가능하지만 100% 성공하지는 않는다. 이럼에도 불구하고 Cloneable을 구현하는 방식은 여전히 쓰이고 있다.



### Cloneable

Cloneable은 메서드가 하나도 없는데 어떤 일을 할까? 이 인터페이스는 Object의 protected 메서드인 clone 메서드의 동작 방식을 정해준다.

Cloneable을 구현한 클래스의 인스턴스에서 clone을 호출하면 그 객체의 필드를 하나하나 복사한 객체를 반환하며, 그렇지 않은 클래스의 인스턴스에서 호출하면 CloneNotSupportedException을 던진다.  이 방식은 인터페이스를 굉장히 예외적으로 사용하는 경우여서 따라해선 안된다.

interface를 구현한다는 것은 그 인터페이스에서 정의한 기능을 제공한다고 선언하는 행동이다. 그런데 Cloneable의 경우는 상위 클래스에 정의된 clone의 행동 방식을 바꾸는 것이기 때문에 그 의미가 조금 다르다.

*__실무에서 Cloneable을 구현한 클래스는 clone 메서드를 publice으로 제공하며, 사용자는 당연히 복제가 제대로 이루어 질 것이라고 기대한다.__* 하지만 이 매커니즘은 생성자를 호출하지 않고 객체를 생성하기 때문에 깨지기 쉽고 모순적이며 위험한 메커니즘이 탄생할 수 있다.

또한, object의 clone메서드는 일반 규약은 허술하다.

```
어떤 객체 x에 대해 다음 식은 참이다.
x.clone() != x
x.clone().getClass() == x.getClass
아래 식은 일반적으로 참이지만, 역시 필수는 아니다.
x.clone().equals(x)
관례상, 이 메서드가 반환하는 객체는 super.clone을 호출해야 한다. 이 클래스와 (Object를 제외한)모든 상위 클래스가 이 관례를 따른다면 다음 식은 참이다.
x.clone().getClass() == x.getClass()
관례상 반환된 객체와 원본 객체는 독립적이여야 한다. 이를 만족시키려면 super.clone으로 얻은 객체의 필드 중 하나 이상을 반환 전에 수정해야 할 수도 있다.
```

강제성이 없다는 점만 빼면 생성자 연쇄와 살짝 비슷한 메커니즘이다. 

clone 메서드가 하위클래스에서 super.clone메서드를 호출하게 된다면 컴파일 중에는 문제가 발생하지 않지만 하위 클래스에서는 우리가 원하는 결과를 만들어 내지 않는다. 하지만 clone을 재정이한 클래스가 final이라면 걱정해야 할 하위 클래스가 없기 때문에 관례를 무시하여도 되지만 final 클래스의 clone메서드가 super.clone을 호출하지 않는다면 Cloneable을 구현할 이유도 없다. Object의 구현 동작 방식에 기댈 필요가 없기 때문이다.

배열을 가지고 있는 class를 복사해보자.

```java
public class Stack {
  private Object[] elements;
  private int size = 0;
  
	public Stack() {
    this.elements = new Object[10];
  }
}
```

위 class가 super.clone 메서드를 받아서 그 결과를 그대로 반환한다면 반환된 stack의 인스턴스의 size 필드는 올바르게 사이즈가 지정되겠지만 elements 배열은 복사된 인스턴스여도 같은 배열을 참조할 것이다. 이것은 우리가 원하는 결과가 아니다.

*__clone 메서드는 사실상 생성자와 같은 효과를 낸다. 즉, clone은 원본 객체에 아무런 해를 끼치지 않는 동시에 복제된 객체의 불변식을 보장해야한다.__*

따라서, clone을 재 정의 해야한다.

```java
@Override
public Stack clone() {
  try {
    Stack result = (Stack) super.clone();
    result.elements = elements.clone();
    return result;
  } catch (CloneNotSupportedException e) {
    throw new AssertionError();
  }
}
```

배열 또한 clone 메서드를 통해 복사를 한다. 배열의 clone 메서드는 형도 원본 배열과 똑같이 복사해주며 clone메서드를 온전하게 사용하기에 적합한 경우라고 할 수 있다.

만약, element 필드가 final이었다면 앞서의 방식은 작동하지 않는다. final 값에는 새로운 값을 할당 할 수 없기 떄문이다. *__Cloneable 아키텍처는 '가변 객체를 참조하는 필드는 final로 선언하라'는 일반 용법과 충돌한다.__* 단, 원본과 복제된 객체가 그 가변 객체를 공유해도 안전하다면 괜찮다. 따라서 clone을 사용하기 위해 final 키워드를 삭제해줘야 할 수도 있다.

clone을 재귀적으로 호출하는 것만으로는 충분하지 않을 때도 있다. 

이걸을 해결하기 위해 다음과 같이 해결한다.

```java
public class HashTable implements Cloneable {
  private Entry[] buckets = ...;
  
  private static class Entry {
    final Object key;
    Object value;
    Entry next;
    
    Entry(Object key, Object value, Entry next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
    
    // 이 엔트리가 가리키는 연결 리스트를 재귀적으로 복사
    Entry deepcopy() {
      return new Entry(key, value, next == null ? null : next.deepCopy);
    }
  }
  
  @Override
  public HashTable clone() {
    try {
      HashTable result = (HashTable) super.clone();
      result.buckets = new Entry[buckets.length];
      for (int i=0; i<buckets.length;i++) {
        if(buckets[i] != null)
          result.buckets[i] = buckets[i].deepcopy();
      } catch (CloneNotSupportedException e) {
        throw new AssertionError();
      }
    }
  }
}
```

private 클래스인 HashTable.Entry 는 깊은복사(deep copy)를 지원하도록 보강되었다. 엔트리에 있는 deepcopy 메서드는 엔트리가 가르키는 연결리스트를 재귀적으로 순회하며 복사를 해온다. 하지만 이것도 연결리스트의 길이가 너무 길게되면 stack에 쌓이게 되어 오버플로우가 날 수 있다. 이것을 반복자를 이용하여 복사하도록 다음과 같이 개선한다.

```java
Entry deepCopy() {
  Entry result = new Entry(key, value, next);
  for (Entry p = result; p.next != null; p = p.next)
    p.next = new Entry(p.next.key, p.next.value, p.next.p.next);
  return result;
}
```



### 생성자에서는 재정의 될 수 있는 메서드를 호출하지 않아야 한다.

이것은 clone에서도 마찬가지로 이루어 지는데 만약 clone이 하위 클래스에서 재정의한 메서드를 호출하면, 하위클래스는 복제과정에서 자신의 상태를 교정할 기회를 잃어버리게 되어 원본과 복제본의 상태가 달라지는 상황이 올 수 있다. 따라서 map에서 있는 put과 같은 매서드는 final이나 private여야 한다. Object의 clone 메서드는 CloneNotSupportedException을 던진다고 선언했지만 재정의한 메서드는 그렇지 않다. public인 clone 메서드에서는 throws 절을 없어애 한다. 검사 예외를 던지지 않아야 사용하기 편하기 떄문이다.



### 상속해서 쓰기 위한 클래스 설계 방식 두 가지중 어느쪽에서든, 상속용 클래스는 Cloneable을 구현해서는 안된다.

Object의 방식을 모방할 수 있다. 

1. 제대로 작동하는 clone 메서드를 구현해 protected로 두고 CloneNotSupportedException도 던질 수 있다고 선언하는 것이다. Object를 바로 상속할 때처럼 Cloneable 구현 여부를 하위 클래스에서 선택하도록 해준다.
2. clone을 동작하지 않게 구현해놓고 하위 클래스에서 재정의하지 못하게 할 수도 있다. 다음과 같이 clone을 퇴화시켜놓으면 된다.

```java
@Override
protected final Object clone() throws CloneNotSupportedException {
  throw new CloneNotSupportedException();
}
```



### Cloneable을 구현한 스레드 안전 클래스를 작성할 때는 clone 메서드 역시 적절히 동기화해줘야 한다.

Object의 clone은 동기화를 고려하지 않는다. 그러므로 super.clone 호출 외에도 다른 할 일이 없더라도 clone을 재정의하고 동기화해줘야한다.

한마디로 Cloneable을 구현하는 모든 클래스는 clone을 재정의 해야하며 public으로 설정하며 반환타임은 자신의 클래스이다.

Cloneable을 이미 구현한 클래스를 확장한다면 어쩔 수 없이 clone이 잘 작동하도록 구현해야 한다. 그렇지 않은 상황에서는 __복사 생성자와 복사 팩터리라는 더 나은 객체 복사 방식을 제공할 수 있다.__

복사 생성자란? -> 단순히 자신과 같은 클래스의 인스턴스를 인수로 받는 생성자이다.

```java
//복사 생성자
public Yun(Yum yum) {...};

//복사 팩터리
public static Yum newInstance(Yum yum) {...};
```

복사 생성자와 그 변형인 복사 팩터리는 Cloneable/clone 방식보다 나은 면이 많다.

1. 언어 모순적이고 위험천만한 객체 생성 매커니즘(생성자를 쓰지않는 방식)을 사용하지 않는다.
2. 엉성하게 문서화된 규약에 의지하지 않는다.
3. 불필요한 검사 예외를 던지지 않는다.
4. 형변환도 필요하지 않는다.
5. 해당 클래스가 구현한 '인터페이스' 타입의 인스턴스를 인수로 받을 수 있다.
   - 모든 범용 컬랙션 구현체는 Collection이나 Map 타입을 받는 생성자를 제공한다.
   - 인터페이스 기반 복사 생성자와 복사 팩터리의 더 정확한 이름은 '변환 생성자'와 '변환 팩터리'이다. 이들을 이용하면 클라이언트는 원본의 구현 타입에 얽매이지 않고 복제본의 타입을 직접 선택할 수 있다
   - 예를들어 HashSet 객체 s를 TreeSet xkdlqdmfh qhrwpgkf tn dlTek. clone으로는 불가능한 이 기능을 변환 생성자로는 간단히 new TreeSet<>(s)로 처리할 수 있다.