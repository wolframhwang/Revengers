# Item2 생성자에 매개변수가 많다면 빌더를 고려하라

### 정적 팩터리 메서드의 한계

정적팩터리 메서드도 마찬가지로 제약사항이 있다. 만약 선택적 매개변수가 많은 경우에는 적절히 대응하기 쉽지 않다. 예를들어, 매개변수가 20개가 있는데 그 중 10개가 필요없는 값일 때에 일부 필요한 변수만을 고르기가 쉽지 않다. 이전에는 점층적 생성자로 해당 사항을 해결했지만 매개변수가 앞선것 처럼 20개가 되면 그것을 알기 쉽지 않다. 이러한 패턴들의 단점을 해결하기 위해 자바 빈즈 패턴을 사용하여 해결했다.(setter를 이용한 방식)

하지만, 이 방식 또한 매개변수가 많을때에 setter가 엄청나게 많아진다는 단점과 객체가 완전히 생성되기 전까지는 일관성이 떨어진다는 단점이 발생한다. 이것은 동시성 제어에 대한 설정을 프로그래머스가 따로 추가로 해줘야 한다. 객체가 생성되어 리턴되기 전에 해당 객체를 freeze 하는 방식도 존재하지만 해당 방식은 선택적 매개변수와 자바 빈즈 패턴의 장점만을 취할 수 있다.

또한, 생성자에서 여러 매개변수에 대해서 불변식을 검사함으로서 사전에 잡아낼 수 있다.



### 계층적으로 설계된 클래스와 함께 쓰기 좋음

각 계층의 클래스에 관련 빌더를 멤버로 정의하자. 추상클래스는 추상 빌더를, 구체 클래스는 구체 빌더를 가지게 된다. 하위 클래스가 존재할 때 각 하위 클래스의 빌더가 정의한 build 메서드는 해당하는 구체 하위 클래스를 반환하도록 선언한다. 상위 클래스가 지정해놓은 반환타입이 아니라 따로 정의 한 구체 하위 클래스를 반환하도록 선언할 수 있다. 그 하위 타입을 반환하는 이런 방식을 공변 반환 타이핑이라고 한다. 이를 이용하면 클라이언트는 형 변환에 신경쓰지 않고 빌더를 사용할 수 있게 된다.

### 유연함

매개변수에 따라 매번 다른 객체를 생성해 줄 수 있다.



### 단점

1. 빌더를 객체에 반드시 만들어 줘야하기 때문에 성능에 매우 민감한 경우에는 미세한 차이가 발생 할 수있다. 하지만 API는 시간이 지날수록 매개변수가 늘어나기 때문에 빌더를 사용해야 하는 장점이 더 크다.