# Exception Model
ARMv8을 이해하기 위해서는 새로 도입된 Exception Model에 대한 이해가 필요하다.
프로세서를 바라보는 관점이 Exception Level 을 기준으로 변경되었기 때문이다.

하나씩 차근차근 알아보자.
### Exception이란 무엇일까?
시스템 흐름에서 벗어나서 익셉션 핸들러의 처리를 필요로 하는 State 또는, Event를 통칭하여 말한다.

예를들어, 유저모드에서 권한이나 여러 이유에 의해 처리할수 없는 interrupt Event가 발생했다고 가정하자.
이러한 interrupt는 본디 유저모드 레벨에서는 처리할수가 없다. 반드시 커널이상으로 올라가서 처리를 할 필요가 있고,
interrupt같은 현재 사용 상태에 대하여 예외 Event를 통칭하여 Exception이라 한다.

### Exception Model 이란 무엇일까?

익셉션의 타입과 상태, 프로세서의 익셉션 핸들링 루틴, 익셉션 복귀 등을 정의하는 모델을 말한다.
프로세서는 4개의 익셉션 레벨(EL0, EL1, EL2, EL3)중 한 가지의 레벨에서 동작을 하게 된다.
모든 익셉션 레벨에서 익셉션이 발생 가능하고, 익셉션의 레벨이 높을수록 자원에 접근할수 있는 권한 역시 높아지게 된다.

EL0은 User Application, EL1은 Kernel, EL2는 Hypervisor, EL3는 Secure Monitor가 동작하게 된다.

프로세서의 동작 상태는 시큐리티 상태에 따라서 Non - Secure, secure상태로 나누어 볼수 있다.
Non - Secure 상태에서는 Secure 상태의 자원(특히 물리 메모리)에 접근할수 없다.
이러한 차이는 EL3 가 담당하는데, EL3는 SMC 인스터럭션이나, 하드웨어 익셉션을 통하여 접근이 가능하다.

> EL2,EL3는 옵션이므로 프로세서에 포함하지 않을수 있다. 따라서, 가상화나 하드웨어 시큐리티 기능이 필요하다면, 프로세서 선정 시 EL2, EL3가 포함 되었는지 확인 해야 한다.

# Exception Level Change
익셉션 레벨이 변경되면 코드 흐름이나 권한 역시 변경된다.
그렇기에, 정확한 변경 조건이나 과정을 파악하는것이 중요하다.
먼저 특징을 살펴보자.

익셉션이 발생하면 동일 익셉션 레벨이나, 조금 더 상위의 익셉션 레벨로 진입하게 된다.
예를들어, EL0 -> EL1 으로 이동하는 것 같이, 높은 익셉션 레벨로 변경하게 되면, 실행하는 권한 역시 상승하게 된다.
의도하지 않은 권한 상승이 발생하면 안되기에, 익셉션이 발생한 레벨보다 낮은 레벨에서는 익셉션을 받을수 없다.(시점의 문제인가? 당장 익셉션 받은 레벨이 받아지면 그보다 낮은 익셉션은 받을수 없네... 높은건 되나?)
특히 EL0는, 익셉션 핸들링이 불가능 하므로, EL0에서 발생한 익셉션은 EL1 이상의 레벨에서 핸들링 해야만 한다.

Exception은 다음과 같은 상황에서 발생한다.
- IRQ, FIQ Interrupt Signal
- Memory System Abort
- Excute Undefined Instruction
- System call
- Secure Monitor or Hypervisor Trap

익셉션 핸들링이 종료되고, 이전 실행 코드로 복귀하기 위해서는 명시적으로 ERET 인스트럭션을 ㅏㅅ용해야 한다.
익셉션 복귀 시에는 익셉션과 발생과 반대로, 동일한 익셉션이나, 낮은 익셉션 레벨로 이동하게 된다.
또한, 보안 상태는 익셉션 레벨이 변경될 때 변경된다.

### 실행 상태
ARM v8 아키텍쳐에서는 AArch64와 AArch32라는 2개의 실행 상태가 정의되어 있다.

|           |    AArch64     |    AArch32     |
| ------ | ------ | ------ |
|    특징    |    64비트 실행 환경 </br> PC(Program Counter) </br> SP(Stack Pointer) </br> ELR(Exception Link Register)     |    32비트 실행환경     |
|  레지스터    |    64비트 크기 범용 레지스터 31개 </br> PC(Program Counter) </br> SP(Stack Pointer) </br> ELR(Exception Link Register)      |    32비트 크기 범용 레지스터 13개 </br> PC </br> SP </br> LR(Link Register)  |
|  인스트럭션 세트  |    A64(32비트 인코딩)     |    A32(ARM v7 지원), T32(Thumb)     |
|  주소방식  |   64비트 가상 주소     |    32비트 가상 주소   |
|  익셉션 모델  |   ARMv8 익셉션 모델(EL0~EL3)     |    ARMv7 익셉션 모델(ARMv8에 매핑됨)   |

AArch 32는 ARMv7과의 호환성을 지원하기 위해 제공된다.
ARMv7용으로 빌드된 바이너리는 거의 수정이 없이 ARMv8 AArch32에서 동작이 가능하다.
실행환경이 차이가 나므로, 바이너리는 AArch64 또는 AArch32를 타깃으로 빌드해야만 한다.

### 실행 상태 변경

실행 상태 변경은 현재 익셉션 레벨보다 높은 익셉션 레벨에 진입한 상태에서 원래의 익셉션 레벨로 복귀하는 과정에서 이루어진다.
단, EL3는 더 올라갈 익셉션 레벨이 없으므로, 리셋을 통해서 실행 상태를 변경시켜야만 한다.

실행 상태의 변경은 빈번하게 발생할수 있고, 또한 제약이 따른다.
AArch32로의 변경은 높은 익셉션 레벨에서 낮은 익셉션 레벨로 변경할 떄만 가능 하다.
AArch64로의 변경은 낮은 익셉션 레벨에서 높은 익셉션 레벨로 변경할 때만 가능하다.

AArch64에서 동작하는 커널은 AArch32에서 실행되는 32 비트 애플리케이션과 AArch64에서 실행되는 64비트 어플리케이션을 모두 실행할 수 있다.
익셉션 레벨 EL0 -> EL1 으로 높아질 경우에는 실행 상태를 AArch32에서 AArch64로 변경하는 것이 가능하기 때문이다.
반대로 AArch32에서 동작하는 커널은 AArch 64에서 동작하는 64비트 어플리케이션을 실핼할 수 없다.
익셉션 레벨이 EL1에서 EL0으로 낮아질 때는 실행 상태를 AArch32에서 AArch64로 변경하는것은 불가능하기 때문이다.


> reference : 코드로 알아보는 ARM 리눅스 커널(2판)
