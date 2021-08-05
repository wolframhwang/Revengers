# item 18 상속보다는 컴포지션을 사용하라

프로그래머가 컨트롤 할 수 있는 범위내에서는 상속이 좋은 방법이 될 수 있으나 다른 패키지의 구체 클래스를 상속하는 것은 위험하다. 

   ※ 이 장에서 이야기 하는 상속은 구현 상속이지 interface 상속과는 무관함.

메서드 호출과 달리 상속은 캡슐화를 깨트린다. 상위클래스의 변경으로 하위클래스의 코드가 변경되지 않았음에도 불구하고 하위 클래스에 문제가 발생할 수도 있다. HashSet을 상속받아 구현한 다음과 같은 클래스가 있다고 가정하자.

```java
public class InstrumentedHashSet<E> extends HashSet<E> {
  private int addCount = 0;
  public InstrumentedHashSet() {
    
  }
  
  public InstrumentedHashSet(int initCap, float loadFactor) {
    super(initCap, loadFator);
  }
  
  @Overried public boolean add(E e) {
    addCount++;
    return super.add(e);
  }
  
  @Overried public boolean addAll(Collection<? extends E> c) {
    addCount += c.size();
    return super.addAll(c);
  }
  
  public int getAddCount() {
    return addCount;
  }
}
```

위 함수만 봤을때는 별 문제가 없어보인다. 하지만 다음과 같은 상황을 한번 살펴보자.

```java
InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
s.addAll(List.of("틱", "탁탁", "펑"));
System.out.println(s.getAddCount());
```

우리가 예상하고 원했던 결과는 콘솔에 3이라는 결과가 뜨는 것이다. 하지만 실제로 실행시켜보면 6이 출력되는데 그 이유는 무엇일까?

바로 HashSet에 addAll 메서드는 add 메서드를 통해서 구현하기 때문이다! 하지만 당연히 이런 내용은 문서에는 쓰여져 있지 않기 때문에 우리가 예측할 수 없어서 이러한 문제가 발생했다. 상속으로 인해 생기는 문제를 요약하자면 두가지와 같다.

1. 이런 캡슐화를 깨트리는 방식이며 애초에 private 필드를 사용해야 하는 상황에서는 이것을 사용할 수 없다.

2. 하위 클래스가 깨지기 쉬운 이유는 릴리즈별로 상위 클래스에 스팩이 바뀔 수 있기 때문이다. 상위 클래스에 이전에 생각하지 않던 내용의 기능이 들어가게 되면 보안상에 문제가 생길 수 있으며 여러가지 문제가 생길 수 있다.

위 두가지 문제 모두 다 메서드 재정의가 원인이었다. 만약에 재정의가 아닌 새로운 메서드를 추가했다면 생기지 않을 문제이다. 하지만 만약 내가 새로 만든 메서드가 상위 클래스가 새로 릴리즈 되어 추가된 메서드와 것과 리턴타입, 시그니처, 매개변수 등이 모두 같다면 컴파일 조차 되지 않을 것이다. 

이런 문제를 모두 피해가는 방법이 있는데 바로 컴포지션을 사용하는 방법니다.

## 컴포지션?

이 방법은 새로운 클래스를 만들어 private 필드로 기존 클래스의 인스턴스를 참조하도록 설계하는 방식이다. 이런 방식을 포워딩이라고 하고 새 클래스의 메서드들을 전달 메서드(forwarding method)라고 한다. 그 결과 새로운 메서드는 기존 클래스의 내부 구현 방식에서 벗어나며 위에서 생긴 문제들을 모두 해소 할 수있다. 위에서 봤던 InstrumentedHashSet함수를 다시 구현해보겠다. has-a is-a

```java
public class InstrumentedSet<E> extends ForwardingSet<E> {
  private int addCount = 0;
  
  public InstrumentedSet(Set<E> s) {
    super(s);
  }
  
  @Overried public boolean add(E e) {
    addCount++;
    return super.add(e);
  }
  
  @Overried public boolean addAll(Collection<? extends E> c) {
    addCount += c.size();
    return super.addAll(c);
  }
  
  public int getAddCount() {
    return addCount;
  }
}
```

```java
public class ForwardingSet<E> implements Set<E> {
  private final Set<E> s;
  public ForwrodingSet(Set<E> s) {this.s = s;}
  
  public boolean contains(Object o) {return s.contains(o);}
  ..이사 행략
  세부 메서드들 구현
}
```

위 방식을 사용하면 HashSet의 모든 기능을 정의한 Set 인터페이스를 활용해 설계되어 견고하고 아주 유연하다. 

임의의 Set에 계측 기능을 덧씌워 새로운 Set으로 만드는 것이 이 방식의 핵심이다. 이 컴포지션 방식을 이용하여 어떠한 Set 구현체라도 계측 할 수 있으며, 기존의 생성자와 함꼐 사용 가능하다.

```java
Set<Instant> times = new InstrumentedSet<>(new TreeSet<>(cmp));
Set<E> s = new InstrumentedSet<>(new HashSet<>(INIT_CAPACITY));
```

다른 Set 인스턴스를 감싸고(wrap) 있다는 뜻에서 InstrumentedSet 같은 클래스를 래퍼 클래스라 하며, 다른 Set에 계측 기능을 덧씌운다는. 뜻에서 데코레이터 패턴이라고 한다. 컴포지션과 전달의 조합은 럾은 의미로 위임이라고 부른다.(엄밀히 따지면 래퍼 객체가 내부 객체에 자기 자신의 참조를 넘기는 경우만 위임에 해당한다.)

래퍼 클래스는 단점이 거의 없지만 콜백 프레임워크와는 어울리지 않는다. 콜백 프레임워크에서는 자기 자신의 참조를 다른 객체에 넘겨서 다음 호출 때 사용하도록 한다. 내부 객체는 자신을 감싸고 있는 래퍼의 존재를 모르니 대신 자신의 참조를 넘기고, 콜백 때는 래퍼가 아닌 내부 객체를 넘기게 된다. 이를 __Self 문제라 한다.__ 다음 예시를 보자.

```java
// 우리가 랩핑할 basic 클래스
public class Model{ 
    Controller controller;

    Model(Controller controller){
        this.controller = controller; 
        controller.register(this); //Pass SELF reference
    }

    public void makeChange(){
        ... 
    }
}

public class Controller{
    private final Model model;

    public void register(Model model){
        this.model = model;
    }

    // 여기서 래퍼는 래핑된 개체에 대해 모르기 때문에 변경 내용을 세지 못합니다.
    // references leaked
    public void doChanges(){
        model.makeChange(); 
    }
}

// wrapper class
public class ModelChangesCounter{
    private final Model model; 
    private int changesMade;

    ModelChangesCounter(Model model){
        this.model = model;
    }

    // 래퍼는 변경 내용을 카운트하기 위한 것이지만 컨트롤러에서 호출된 변경 사항은 건너뜁니다.
    public void makeChange(){
        model.makeChange(); 
        changesMade++;
    } 
}
https://stackoverflow.com/questions/28254116/wrapper-classes-are-not-suited-for-callback-frameworks
```

Model 클래스에서 생성자에서 Controller에 자기 자신을 등록 해놨다. 그러다보니 model.makeChange는 컨트롤러 부분을 무시하게 된다.



### 성능문제?

전달 메서드가 성능에 주는 영향이나 래퍼 객체가 메모리에 주는 사용량에 주는 영향을 걱정하는 사람도 많았지만 실제로는 큰 영향을 끼치지 않는다고 알려져 있다. 전달 메서드를 작성하는게 귀찮겠지만 재사용 가능한 전달 클래스를 한개씩만 만들어 두면 원하는 기능을 덧씌우는 전달 클래스들을 아주 손쉽게 구현 가능하다. 좋은 예로, 구아바는 모든 컬랙션 인터페이스용 전달 메서드를 전부 구현해 놨다.



### 상속을 쓰는 상황

상속은 반드시 하위 클래스가 상위 클래스의 '진짜' 하위 타입인 상황에서만 쓰여야만 한다. 클래스 B가 클래스 A와 is-a 관계일 때만 클래스 A를 상속해야한다. 클래스 A를 상속하는 클래스 B를 작성하려면 "B가 정말로 A인가?"를 고민해보고 확신할 수 있는 경우에만 사용하자. (예를들어 권진우는 사람을 상속 받는데 권진우는 사람이기 때문에 사용할 수 있다.) 아닌 경우는 그냥 private로 클래스의 일부로 두고 사용하는 것이 적합하다.



### 마지막으로

컴포지션을 사용하지 않고 상속을 끝까지 사용하겠다면 그 상위 클래스의 결함이 하위 클래스까지 전파될 수 있으며 이 영향도에 대해서 꼭 잘 생각해보고 사용해야한다.