# 익셉션 핸들링

시스템 동작 중 system call, Interrupt Handling, Fault 등 커널이 처리해야하는 이벤트가 발생하면 커널로 진입해야 한다.
head.S가 부팅 플세스에 의한 커널 진입점이라고 생각한다면, 익셉션 벡터는 다양한 이벤트에 의한 런타임에서 커널의 진입점이라고 볼수 있겠다.
커널은 익셉션을 유발한 이벤트의 핸들링을 마치게되면, 본래 실행 흐름으로 복귀하게 된다.

익셉션이 발생하게 되면, 프로세서는 VBAR_ELn 레지스터(Vector Based Address Register)에 저장된 익셉션 벡터로 점프하게 된다.
VBAR_EL1레지스터는 head.S에서 설정하는데, 0번 코어는 __map_swtiched 레이블에서 설정하고 SMP(Symmetric Multicore Processor)의 나머지 코어는 __secondary_switched 레이블에서 설정한다.

같은 익셉션 타입이라도 익셉션이 발생한 익셉션 레벨과 사용하는 스택 포인터, 실행 상태에 따라 벡터 테이블의 다른 엔트리로 분기하게 된다.

## 익셉션 벡터

익셉션이 발생했을 때, 수행되는 핸들러가 저장된 위치를 익셉션 벡터라고 하며, ARM은 테이블 형태로 정의되어져있다.
```
<arch/arm64/kernel/entry.S>
.macro ventry label
.align 7
b      \label
.endm
```

> 익셉션 벡터 테이블
벡터 테이블 엔트리는 각각 크기 128바이트인데, 커널은 각 엔트리에 직접 핸들러를 구현하는 대신,점프하는 매크로를 지정하게 된다.
