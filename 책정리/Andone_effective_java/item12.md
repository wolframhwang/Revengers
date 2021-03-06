# item 12 toString을 항상 재정의 하라

Object의 toString메서드는 *__class이름+16진수 hashcode__*를 리턴한다. 이는 원하는 결과가 아니기 때문에 toString을 재정의 해야한다.

재정의를 함으로서 디버깅을 하기가 더 쉽게 된다.

toString 메서드는 객체를 println, printf, 문자열 연결 연산자(+), assert 구문에 넘길 때, 혹은 디버거가 객체를 출력할 때 자동으로 불린다. 즉, 우리가 직접적으로 해당 메서드를 호출하지 않아도 어딘가에서 쓰인다. 우리가 작성할 때 객체를 참조하는 컴포넌트가 오류 메세지를 로깅할 때 자동으로 호출할 수 있다. toString을 제대로 재정의 하지 않으면 쓸모없는 로그만 남게된다.

*__실전에서 toString은 그 객체가 가진 주요 정보 모두를 반환하는 게 좋다.__*

객체가 만약에 너무 거대하거나 객체가 많다면 요약정보를 띄워주도록 재정의 한다. 만약에 객체 정보를 toString으로 재정의 해준다면 해당 포맷을 문서화를 항상 해줘야 한다. 하지만 포맷을 지정할 경우 차후에 그 클래스는 평생 포멧에 얽매이게 된다. 따라서 포맷을 명시하지 않는다면 향후 릴리즈에서 정보를 더 집어 넣거나 포맷을 개선할 수 있는 유연성을 얻게 된다.

하지만 *__포멧을 명시하든 아니든 여러분의 의도는 명확히 밝혀야 한다.__*

포맷의 명시 여부와 상관없이 *__toString이 반환한 값에 포함된 정보를 얻어올 수 있는 API를 제공하자.__* 예를들어 Point 클래스는 x,y 에 대한 접근자를 제공해야한다. 그렇지 않으면 프로그래머는 toString의 반환값을 파싱할 수밖에 없다. 이런것은 성능도 나빠지고 불필요한 작업이다. 게다가 향후 포맷을 바꾸면 시스템이 망가지는 결과를 초래할 수 있으며 접근자를 제공하지 않으면 toString의 포맷이 준-표준 API가 되어버린다.

정적 유틸리티 클래스는 toString을 제공할 필요가 없다. 또한 대부분의 열거타입와 완벽한 toString 함수를 지원한다. 하지만 하위 클래스들이 공유해야 할 문자열 표현이 있는 추상 클래스라면 tosTring을 재정의 해줘야한다. 대다수의 컬랙션 구현체는 추상 컬렉션 클래스들의 toString 메서드를 상속해 쓴다.



--찾아보기 : google AutoValue 프레임워크--