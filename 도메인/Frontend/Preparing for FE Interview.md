# Preparing for Interview : JavaScript & TS

-----

> 
>
> [toc]

<br/>





## 이벤트 위임 Event Delegation



<br/>



## 실행 컨텍스트 Execution Context



<br/>

## 스코프 Scope



<br/>

### ref

[테코톡 엘라의 Scope & Closure](https://youtu.be/PVYjfrgZhtU)

<br/>

## 호이스팅 Hoisting



<br/>

## 클로저 Closure



<br/>

### ref

[테코톡 엘라의 Scope & Closure](https://youtu.be/PVYjfrgZhtU)

<br/>

## 네이티브 객체 vs 호스트 객체

### 네이티브 객체 (Native Object)

ECMAScript 명세에서 의미론적인 부분을 완전히 정의해놓은 객체들로, 다음과 같은 것들이 있다.

- `Object`
- `Function`
- `Date`
- `Math`
- `parseInt`
- `eval` ...



### 호스트 객체 (Host Object)

자바스크립트를 실행하는 환경에 종속된 객체로 그 환경에서만 찾아볼 수 있는 객체이다. 만약 브라우저 환경이라면 다음과 같은 것들이 있다.

- `window`
- `document`
- `location`
- `XMLHttpRequest`
- `querySelectorAll` ...



### ref

- [Must-know-about-fe](https://github.com/baeharam/Must-Know-About-Frontend/blob/main/Notes/javascript/native-host.md)

- [Stackoverflow, What is the difference between native objects and host objects?](https://stackoverflow.com/questions/7614317/what-is-the-difference-between-native-objects-and-host-objects)
- [MDN, Standard built-in objects](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects)
- [Poiemaweb, 빌트인 객체](https://poiemaweb.com/js-built-in-object)

<br/>

## this 의 바인딩

> JavaScript 에서의 this

### JS 의 this

- JS으 ㅣ함수는 객체, 그 중에서도 일급 객체이다. 1. 변수나 데이터에 저장 2. 함수의 인수로 전달 3. 함수의 반환 값으로 사용

- 어떤 객체에 의해 호출되느냐에 따라 , this 의 의미가 많이 달라진다
- JS의 모든 함수는 this를 가지고 있으며 , 함수 호출 시 , 그떄 상황에 따라 this가 가리키는 객체가 결정된다. 즉, 함수가 호출될 때마다 this가 동적으로 결정되는 것을 This가 SomeObject (그 객체)에 ₩바인딩₩ 된다고 표현한다.
- 자바스크립트 엔진 -> 실행 가능한 코드 (전역 코드, 함수 코드, eval 코드) -> 실행 문맥 (렉시컬 환경 컴포넌트, 디스 바인딩 컴포넌트)



### This Binding Rules 

> 함수가 호출되는 상황에 따른 규칙, 우선순위 존재

- 기본 바인딩 : 단독 실행
- 암시적 바인딩
- new 바인딩
- 명시적 바인딩





EC(Execution Context)가 생성될 때마다 this의 바인딩이 일어나며 우선순위 순으로 나열해보면 다음과 같다.

1. `new` 를 사용했을 때 해당 객체로 바인딩된다.

```
var name = "global";
function Func() {
  this.name = "Func";
  this.print = function f() { console.log(this.name); };
}
var a = new Func();
a.print(); // Func
```

1. `call`, `apply`, `bind` 와 같은 명시적 바인딩을 사용했을 때 인자로 전달된 객체에 바인딩된다.

```
function func() {
  console.log(this.name);
}

var obj = { name: 'obj name' };
func.call(obj); // obj name
func.apply(obj); // obj name
(func.bind(obj))(); // obj name
```

1. 객체의 메소드로 호출할 경우 해당 객체에 바인딩된다.

```
var obj = {
  name: 'obj name',
  print: function p() { console.log(this.name); }
};
obj.print(); // obj name
```

1. 그 외의 경우

- strict mode: `undefined` 로 초기화된다.
- 일반: 브라우저라면 `window` 객체에 바인딩 된다.



### ref

- [Must-know-about-fe](https://github.com/baeharam/Must-Know-About-Frontend/blob/main/Notes/javascript/this.md)

- [김정환 블로그, 자바스크립트 this 바인딩 우선순위](http://jeonghwan-kim.github.io/2017/10/22/js-context-binding.html#암시적-바인딩과-new-바인딩의-우선순위)
- [How does the “this” keyword work?](https://stackoverflow.com/questions/3127429/how-does-the-this-keyword-work)

- [테코톡 브콜의 this](https://youtu.be/7RiMu2DQrb4)

<br/>

## var vs let vs const

>  모두 변수를 선언하는 키워드라는 것은 동일하다. 하지만, let과 const는 ES2015(ES6)에서 등장했고 여러가지 다른 특성을 갖는다.
>
> 스코프 규칙, 호이스팅, 

### 스코프 규칙

> 스코프의 개념을 모른다면 [스코프](https://github.com/baeharam/Must-Know-About-Frontend/blob/master/Notes/Javascript/scope.md) 를 보고 오자.

- var는 함수 스코프를 갖는다.
- let과 const는 블록 스코프를 갖는다.

```
function run() {
  var foo = "Foo";
  let bar = "Bar";

  console.log(foo, bar);

  {
    let baz = "Bazz";
    console.log(baz);
  }

  console.log(baz); // ReferenceError
}

run();
```

따라서, 위 코드를 실행했을 때 블록 안에 있는 `baz` 를 출력하게 되면 ReferenceError가 발생하는 것이다.

<br/>

### 호이스팅

> 호이스팅의 개념을 모른다면 [호이스팅](https://github.com/baeharam/Must-Know-About-Frontend/blob/master/Notes/Javascript/Hoisting.md) 을 보고 오자.

- var는 **함수 스코프의 최상단으로 호이스팅** 되고 선언과 동시에 `undefined` 로 초기화 된다.
- let과 const는 **블록 스코프의 최상단으로 호이스팅** 되고 선언만 되고 값이 할당되기 전까지 어떤 값으로도 초기화되지 않는다.

```
function run() {
  console.log(foo); // undefined
  var foo = "Foo";
  console.log(foo); // Foo
}

run();
```

따라서, var의 경우 위와 같이 선언 전에 출력하면 `undefined` 가 출력된다.

```
function checkHoisting() {
  console.log(foo); // ReferenceError
  let foo = "Foo";
  console.log(foo); // Foo
}

checkHoisting();
```

반면에, let의 경우는 선언 전에 호이스팅 되긴 하지만 어떤 값도 가지지 않기 때문에 ReferenceError가 발생한다. 이런 현상을 **TDZ(Temporal Dead Zone)** 라고 한다. 즉, 선언은 되었지만 참조는 할 수 없는 사각지대를 갖는 것이다.

<br/>

### 글로벌 객체로의 바인딩

**strict mode가 아니라는 가정 하에,**

- var는 글로벌 스코프에서 선언되었을 경우 글로벌 객체에 바인딩된다.
- let과 const는 글로벌 스코프에서 선언되었을 경우 글로벌 객체에 바인딩되지 않는다.

```
var foo = "Foo";  // globally scoped
let bar = "Bar"; // globally scoped

console.log(window.foo); // Foo
console.log(window.bar); // undefined
```

브라우저 환경에서 글로벌 객체는 `window` 인데, var의 경우 바인딩이 되었고 let의 경우는 되지 않았다는 걸 볼 수 있다.

<br/>

### 재선언 (Redeclaration)

- var는 재선언이 가능하다.
- let과 const는 재선언이 불가능하다.

```
var foo = "foo1";
var foo = "foo2"; // 문제없음

let bar = "bar1";
let bar = "bar2"; // SyntaxError: Identifier 'bar' has already been declared
```

<br/>

### let vs const

- var와 let은 재할당이 가능하다.
- const는 선언과 초기화가 반드시 동시에 일어나야 하며 재할당이 불가능하다. 즉, 상수와 같은 고정값을 선언할 때 사용하는 키워드이다.

<br/>

### ES2015+의 등장

기존의 자바스크립트 문법에 다른 언어의 장점들을 더한 편리한 기능들이 많이 추가되었다. 이 중에 활용도가 높은 부분에 대해서 알아보자.



### 1. const, let

------

자바스크립트에서 변수를 선언할 때 var를 이용해왔다. 하지만 이제 var는 `const`와 `let`으로 대체할 것이다.

const와 let은 함수 스코프를 가지는 var와는 달리 **블록 스코프**를 갖는다.

블록 스코프는 `if, while, for, function` 등에서 사용하는 중괄호에 속하는 범위를 뜻한다. 따라서 const와 let을 이 중괄호 안에서 사용하게 된다면, 그 스코프 범위 안에서만 접근이 가능하다. 이를 통해 호이스팅에 관련된 문제는 자연스럽게 해결할 수 있다.



#### *그렇다면 const와 let은 무슨 차이일까?*

간단히 말해서 let은 대입한 값을 계속 수정할 수 있지만, const는 한번 대입하면 다른 값 대입을 할 수 없고 초기화 시 값이 필요다.

```
const a = 0;
a = 1; // error


let b = 0;
b = 1; // 1

const c; // error 
```





### 

### ref

- [must-know-abot-fe](https://github.com/baeharam/Must-Know-About-Frontend/blob/main/Notes/javascript/var-let-const.md)

- [gyoogle](https://github.com/gyoogle/tech-interview-for-developer/blob/master/Language/%5BJavascript%5D%20ES2015%2B%20%EC%9A%94%EC%95%BD%20%EC%A0%95%EB%A6%AC.md)

- [Stackoverflow, What's the difference between using “let” and “var”?](https://stackoverflow.com/questions/762011/whats-the-difference-between-using-let-and-var)
- [Stackoverflow, Are variables declared with let or const not hoisted in ES6?](https://stackoverflow.com/questions/31219420/are-variables-declared-with-let-or-const-not-hoisted-in-es6)
- [PoiemaWeb, let, const](https://poiemaweb.com/es6-block-scope)







<br/>

## IIFE (Immediately-Invoked Function Expression)

모두 변수를 선언하는 키워드라는 것은 동일하다. 하지만, let과 const는 ES2015(ES6)에서 등장했고 여러가지 다른 특성을 갖는다.

## 스코프 규칙

> 스코프의 개념을 모른다면 [스코프](https://github.com/baeharam/Must-Know-About-Frontend/blob/master/Notes/Javascript/scope.md) 를 보고 오자.

- var는 함수 스코프를 갖는다.
- let과 const는 블록 스코프를 갖는다.

```
function run() {
  var foo = "Foo";
  let bar = "Bar";

  console.log(foo, bar);

  {
    let baz = "Bazz";
    console.log(baz);
  }

  console.log(baz); // ReferenceError
}

run();
```

따라서, 위 코드를 실행했을 때 블록 안에 있는 `baz` 를 출력하게 되면 ReferenceError가 발생하는 것이다.



## 호이스팅

> 호이스팅의 개념을 모른다면 [호이스팅](https://github.com/baeharam/Must-Know-About-Frontend/blob/master/Notes/Javascript/Hoisting.md) 을 보고 오자.

- var는 **함수 스코프의 최상단으로 호이스팅** 되고 선언과 동시에 `undefined` 로 초기화 된다.
- let과 const는 **블록 스코프의 최상단으로 호이스팅** 되고 선언만 되고 값이 할당되기 전까지 어떤 값으로도 초기화되지 않는다.

```
function run() {
  console.log(foo); // undefined
  var foo = "Foo";
  console.log(foo); // Foo
}

run();
```

따라서, var의 경우 위와 같이 선언 전에 출력하면 `undefined` 가 출력된다.

```
function checkHoisting() {
  console.log(foo); // ReferenceError
  let foo = "Foo";
  console.log(foo); // Foo
}

checkHoisting();
```

반면에, let의 경우는 선언 전에 호이스팅 되긴 하지만 어떤 값도 가지지 않기 때문에 ReferenceError가 발생한다. 이런 현상을 **TDZ(Temporal Dead Zone)** 라고 한다. 즉, 선언은 되었지만 참조는 할 수 없는 사각지대를 갖는 것이다.



## 글로벌 객체로의 바인딩

**strict mode가 아니라는 가정 하에,**

- var는 글로벌 스코프에서 선언되었을 경우 글로벌 객체에 바인딩된다.
- let과 const는 글로벌 스코프에서 선언되었을 경우 글로벌 객체에 바인딩되지 않는다.

```
var foo = "Foo";  // globally scoped
let bar = "Bar"; // globally scoped

console.log(window.foo); // Foo
console.log(window.bar); // undefined
```

브라우저 환경에서 글로벌 객체는 `window` 인데, var의 경우 바인딩이 되었고 let의 경우는 되지 않았다는 걸 볼 수 있다.



## 재선언 (Redeclaration)

- var는 재선언이 가능하다.
- let과 const는 재선언이 불가능하다.

```
var foo = "foo1";
var foo = "foo2"; // 문제없음

let bar = "bar1";
let bar = "bar2"; // SyntaxError: Identifier 'bar' has already been declared
```



## let vs const

- var와 let은 재할당이 가능하다.
- const는 선언과 초기화가 반드시 동시에 일어나야 하며 재할당이 불가능하다. 즉, 상수와 같은 고정값을 선언할 때 사용하는 키워드이다.



## 참고

- [Stackoverflow, What's the difference between using “let” and “var”?](https://stackoverflow.com/questions/762011/whats-the-difference-between-using-let-and-var)
- [Stackoverflow, Are variables declared with let or const not hoisted in ES6?](https://stackoverflow.com/questions/31219420/are-variables-declared-with-let-or-const-not-hoisted-in-es6)
- [PoiemaWeb, let, const](https://poiemaweb.com/es6-block-scope)

<br/>

## 모듈 시스템 : CommonJS, AMD, UMD, ES6



<br/>

## 콜 스택과 힙  Call Stack , Heap



<br/>

## 이벤트 루프 Event Loop



<br/>

## 프로토타입 Prototype



<br/>

### ref

[테코톡 크리스의 Prototype](https://youtu.be/RYxgNZW3wl0)

<br/>

## 엄격모드 Strict Mode



<br/>

## new의 동작방식



<br/>

## ES6 (2015) 의 특징들



<br/>

## ES7 (ES2016) ~ ES8 (ES2017) 의 특징들



<br/>

## ES9 (ES2018) ~ ES10 (ES2019) 의 특징들





<br/>

## ES11 (ES2020) 의 특징들









## ref

- [JavaScript & TypeScript & React Frontend Interview Questions Trello](https://trello.com/b/pS6ECW7v/frontend-interview-questions)

<br/>

- [Educative.io-Ace the JavaScript Coding Questions](https://www.educative.io/path/ace-javascript-coding-interview)

- [Educative.io - Ace the Frontend Interview](https://www.educative.io/path/ace-front-end-interview)

- [The JavaScript Interview Handbook: 100+ Interview Questions](https://www.educative.io/courses/javascript-interview-handbook)

- [Web Development Interview Handbook](https://www.educative.io/courses/web-development-interview-handbook)

- [gyoogle](https://github.com/gyoogle/tech-interview-for-developer/blob/master/Language/%5BJavascript%5D%20ES2015%2B%20%EC%9A%94%EC%95%BD%20%EC%A0%95%EB%A6%AC.md)

- [Interview-question-for-beginners-frontend](https://github.com/CS-box/Interview_Question_for_Beginner/tree/master/FrontEnd)

- [Must-Know-about-Frontend](https://github.com/CS-box/Must-Know-About-Frontend)
- [Front-end-Developer-Interview-Questions](https://github.com/h5bp/Front-end-Developer-Interview-Questions)
- [123-Essential-JavaScript-Interview-Questions](https://github.com/CS-box/123-Essential-JavaScript-Interview-Questions)

<br/>

<br/>

- [프론트엔드 인터뷰 핸드북](https://github.com/CS-box/front-end-interview-handbook/blob/master/contents/kr/README.md)

- [WeareSoft-tech-interview-javascript](https://github.com/CS-box/tech-interview/blob/master/contents/javascript.md)

- [awesome-interview-questions](https://github.com/CS-box/awesome-interview-questions)

  JavaScript

  Front-end build tools

  ReactJS

  TypeScript

<br/>

<br/>

[토스-프론트엔드개발자에게 가장 중요한 역량은?](https://blog.toss.im/article/toss-frontend-chapter)

