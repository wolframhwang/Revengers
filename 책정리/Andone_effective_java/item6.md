# Item 6 불빌요한 객체 생성을 피해라

똑같은 기능을 하는 객체를 매번 재 생성하기 보다는 객체 하나를 재사용 하는것이 낫다.

```java
String a = new String("a");
```

이 문장이 실행 될 때마다 String 인스턴스를 생성하게 된다. 하지만

```java
String b = "Test";
```

를 하게 된다면 JVM 안에서 이와 독같은 문자열 리터럴을 사용하는 경우(같은 문자열일 경우) 이것을 재사용 하게 된다.



생성자의 생성 비용이 아주 비싼 객체도 있다. 만약 반복되어서 사용되는데 객체 생성 비용이 비싸다면 "캐싱"하여 재사용해야 한다.

예를들어 정규식을 사용하는

```java
static boolean isNumbers(String s) {
  return s.matches(["0-9"]);
}
```

과 같은 코드가 있다고 가정하자.

Pattern intance는 함수가 불릴때 마다 한번 쓰고 버려지게 된다. 따라서,

```java
public class isNumbers {
  private static final Pattern ROMAN = Pattern.complie("0-9");
  
  static boolean isNumber(String s) {
    return ROMAN.matcher(s).matches();
  }
}
```

이런 방식으로 사용하게 되면 Pattern을 매번 생성하지 않고 사용할 수 있게 된다.

이 개선된 방식을 통해서 인스턴스를 재사용 함으로 인해서 성능을 개선 할 수 있다.



불필요한 객체를 만든는 것에 Auto Boxing이 있다. 

__오토박싱은 기본 타입과 그에 대응하는 박싱된 기본 타입의 구분을 흐려주지만, 완전히 없애주는 것은 아니다.__

모든 양의 Integer 형식의 값을 저하는 경우를 가정할 때에

```java
private static long sum() {
	Long sum = 0L;
  for(long i=0; i<=Integer.MAX_VALUE; i++) {
    sum += i;
  }
  
  return sum;
}
```

이렇게 만든다면 sum변수를 long이 아닌 Long으로 선언하여 Long 인스턴스가 불필요하게 많이 생성되게 된다. 이런 박싱에 의해서 속도 저하가 발생한다.

하지만 굉장히 무거운 객체가 아닌 이상 단순히 객체 생성을 피하기 위해 객체 풀(pool)을 만들지는 말자.

하지만 DB Connection의 경우 생성 비용이 너무 비싸기 때문에 재사용을 위해 Connection pool을 이용해서 미리 생성해 놓고 재사용 한다.