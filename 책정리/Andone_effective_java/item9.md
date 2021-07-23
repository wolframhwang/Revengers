# Item9 try-finally 보다는 try-with-resourse를 사용하라.

## try-finally

우리가 일반적으로 백준에서 input을 받을 때에 사용하는 BufferedReader를 확인해보자.

일반적인 자원 닫침 확인 방식(try catch를 이용한 방식)

```java
static String firstLineOfFile(String path) throws IOException {
  BufferdReader br = new BufferedReader(new FileReader(path));
  try {
    return br.readLine();
  } finally {
    br.close();
  }
}
```

하지만 자원을 한가지 더 사용한다면?

```java
static void copy(String src, String dst) throws IOException {
  InputStream in = new FileInputStream(src);
  try {
    OutputStream out - new FileOutputStream(dst);
    try {
      byte[] buf = new byte[BUFFER_SIZE];
      int n;
      while((n = in.read(buf))>=0)
        out.write(buf,0,n);
    } finally {
      out.close();
    }
  } finally {
    in.close();
  }
}
```

후.. 보면 알겠지만 잘못 짠것을 알 수 있다. try블록과 finally 블록 모두에서 예외가 발생 할 수 있는데 두가지 모두 예외가 발생할 경우 *__두번째 예외가 첫번째 예외를 집어 삼켜 stacktrace로 잡아내지 못할 가능성이 생겼다.__* 두번째 예외 대신 첫번째 예외를 기록 하도록 코드를 바꿀 수도 있지만 그렇게 되면 코드가 가독성이 안좋게 되어 그렇게 잘 하지 않을 것이고 이미 코드는 읽기가 굉장히 불편해져 있다.(depth가 깊어지게 되어 코드 복잡도가 증가한다.)

따라서, java 7에서 등장한 try-with-resources를 사용해보자



## try-with-resources

복잡했던 밑에 코드를 이것을 이용해서 나타내보자.

```java
static void copy(String src, String dst) throws IOException {
  try(InputStream in = new FileInputStream(src);
     OutputStream out = new FileOutputStream(dst)) {
    byte[] buf = new byte[BUFFER_SIZE];
    int n;
    while ((n = in.read(buf))>=0)
      out.write(buf,0,n);
  }
}
```

위 코드보다 훨씬 읽기 쉬워졌으며 문제를 읽기에도 쉽다. 이 덕분에 try-finally를 사용할 때 처럼 예외 사항이 숨겨져버리지 않고 나타나게 될 수 있다.

Try-with-resources에서도 catch 문을 사용할 수 있으며 덕분에 try문을 떡칠하지 않아도 예외사항을 처리할 수 있다. 그 예는 다음과 같다.

```java
static String firstLineOffFile(String path, String defaultVal) {
  try(BufferedRead br = new BufferedReader(new FileReader(path))) {
    return br.readLine();
  } catch (IOException e) {
    return defaultVal;
  }
}
```

