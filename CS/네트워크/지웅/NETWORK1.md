# 웹과 HTTP
웹의 어플리케이션 계층의 프로토콜인 HTTP(Hyper Text Transfer Protocol)는 웹의 중심이다.
HTTP는 두가지 프로글매으로 구현이 된다.

서로 다른 호스트에서 수행되는 클라이언트 프로그램과 서버 프로그램은 서로 HTTP 메세지를 교환하여 통신하게 된다.
HTTP는 메세지의 구조 및 클라이언트와 서버가 메세지를 어떻게 교환하는가에 대해서 정의하고 있다.

먼저, 알아 볼것이
웹 페이지는 객체들로 구성이 된다.
객체는 단순하게 단일 URL로 지정할수 있는 하나의 파일 이다.
대부분의 웹 페이지는 기본 HTML 파일과 여러 참조 객체를 통해 구성이 된다.
예를들어, HTML 텍스트와 5개의 JPEG이미지로 구성이 되어 있으면, 이 웹페이지는 6개의 객체를 가지고 있는것이다.
기본 HTML파일은 페이지 내부의 다른 객체를 그 객체의 URL로 참조한다.
각 URL은 2개의 요소,
1. 객체를 갖고 있는 서버의 호스트 네임
2. 객체의 경로 이름
을 가지게 된다.
한가지 예를 보자.
http://www.someSchool.edu/someDepartment/picture.gif
에서 www.someSchool.edu는 호스트의 이름이고, /someDepartment/picture.gif 는 gif의 경로 이름이다.
웹 브라우저는 HTTP의 클라이언트를 구현하기 때문에 웹의 관점에서 브라우저와 클라이언트라는 용어를 혼용하여 사용하게 된다.
HTTP의 서버 측을 구현하는 웹 서버는 URL로 각각을 지정 할 수 있는 웹 객체를 갖고있다.

HTTP는 웹 클라이언트가 웹 서버에게 웹 페이지를 어떻게 요청하는지와 서버가 클라이언트로 어떻게 웹 페이지를 전송하는지를 정의한다.
사용자가 웹 페이지를 요청할 때, 브라우저는 페이지 내부의 객체에 대한 HTTP 요청 메세지를 서버에게 보낸다.
서버는 요청(request)를 수신하고, 객체를 포함하는 HTTP응답 메세지를 통해 응답(response)한다.

HTTP는 TCP 전송 프로토콜을 사용한다.
HTTP 클라이언트는 먼저 서버에 TCP 연결을 시작한다. 일단, 연결이 이루어지면, 브라우저와 서버 프로세스는 그들의 소켓 인터페이스를 통해 TCP로 접속한다.
클라이언트 측에서 보면 소켓 인터페이스는 클라이언트 프로세스와 TCP 연결 사이의 출입구이다.
반면 서버 측에서 보면 소켓 인터페이스는 서버 프로세스와 TCP 연결 사이에서 출입구이다.
클라이언트는 HTTP 요청 메세지를 소켓 인터페이스로 보내고, 소켓 인터페이스로 부터 HTTP 응답 메세지를 받는다.

여기서 알아둘 것은, 클라이언트가 메세지를 소켓 인터페이스로 보내게 되면, 메세지는 "클라이언트의 손을 떠난" 것이 된다.
TCP의 손에 주어 졌다는 뜻이다.

TCP는 HTTP에게 신뢰적인 데이터 전송 서비스를 제공한다는것을 한번 더 상기하자.
이러한 사실은 클라이언트의 프로세스가 발생시킨 모든 HTTP 요청 메세지가 최종적으로 서버에 잘 도착했다는 것을 알려주게 된다.
반대 입장인 서버에서 생각해보면 서버 프로세스가 발생시킨 HTTP 응답 메세지도 클라이언트에 잘 도착한다는 것을 의미하게 된다.

여기서 이러한 프로토콜 계층 구조에 장점이 드러나는데,
HTTP는 데이터의 손실 또는 TCP가 어떻게 손실 데이터를 복구하고 네트워크 내부에서 데이터를 올바른 순서로 배열하는가에 대해 신경쓸 필요가 없어진다는점이다.
> 도착하는것을 확인하는 신뢰적인 통신 방식이기 때문이다.

서버가 클라이언트에 요청 파일을 보낼때, 서버는 클라이언트에 대한 어떠한 상태 정보도 저장하지 않는다는 사실을 알아두자(이를 비 상태 프로토콜(stateless protocol)이라 하는것을 알아두자)
결론은 HTTP 방식을 통해, 웹서비스는 서버가 항상 켜져있고 고정 IP주소를 가지며 다른 잠재적인 N의 브라우저로부터 들어온 요청을 수행할수 있다는 것이다.

