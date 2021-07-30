# 익셉션 핸들링

시스템 동작 중 system call, Interrupt Handling, Fault 등 커널이 처리해야하는 이벤트가 발생하면 커널로 진입해야 한다.
head.S가 부팅 플세스에 의한 커널 진입점이라고 생각한다면, 익셉션 벡터는 다양한 이벤트에 의한 런타임에서 커널의 진입점이라고 볼수 있겠다.
커널은 익셉션을 유발한 이벤트의 핸들링을 마치게되면, 본래 실행 흐름으로 복귀하게 된다.

익셉션이 발생하게 되면, 프로세서는 VBAR_ELn 레지스터(Vector Based Address Register)에 저장된 익셉션 벡터로 점프하게 된다.
VBAR_EL1레지스터는 head.S에서 설정하는데, 0번 코어는 __map_swtiched 레이블에서 설정하고 SMP(Symmetric Multicore Processor)의 나머지 코어는 __secondary_switched 레이블에서 설정한다.

같은 익셉션 타입이라도 익셉션이 발생한 익셉션 레벨과 사용하는 스택 포인터, 실행 상태에 따라 벡터 테이블의 다른 엔트리로 분기하게 된다.

## 익셉션 벡터

익셉션이 발생했을 때, 수행되는 핸들러가 저장된 위치를 익셉션 벡터라고 하며, ARM은 테이블 형태로 정의되어져있다.

> 익셉션 벡터 테이블 : 벡터 테이블 엔트리는 각각 크기 128바이트인데, 커널은 각 엔트리에 직접 핸들러를 구현하는 대신,점프하는 매크로를 지정하게 된다.

```
<arch/arm64/kernel/entry.S>
.macro ventry label // 
.align 7            // 12byte 정렬
b      \label       // 핸들러로 분기
.endm
```
vectors 엔트리는 AArch64 익셉션 벡터 테이블에 명시된 대로 구현하게 된다.
익셉션 벡터 테이블은 EL1, EL2, EL3 모두 공통 포맷이며, 여기서 커널이 동작하는 EL1을 기준으로 보자.

```
arch/arm64/kernel/entry.S>

	.align	11 // - 벡터 테이블 주소가 2KB단위로 정렬되어야함. align 11명령은, 2^11단위로 벡터 테이블의 시작 주소를 정렬한다.
SYM_CODE_START(vectors)
	kernel_ventry	1, t, 64, sync		// Synchronous EL1t // 동기 EL1t - 처음 4개 엔트리는 현재 익셉션 레벨에서 익셉션이 발생했고, SP_EL0을 사용하도록 설정했을때 진입한다.커널은 EL1 진입 시, SP_EL0을 사용하도록 설정하지 않았으므로, 실행 될 일이 없다,
	kernel_ventry	1, t, 64, irq		// IRQ EL1t
	kernel_ventry	1, t, 64, fiq		// FIQ EL1h
	kernel_ventry	1, t, 64, error		// Error EL1t

	kernel_ventry	1, h, 64, sync		// Synchronous EL1h // 동기 EL1h - 다음 4개의 엔트리는 현재 익셉션 레벨에서 익셉션이 발생했고, SP_ELn을 사용하도록 설정했을 때 진입한다. 커널 동작중 발생한 익셉션일 경우, 이쪽으로 분기하게 된다. 동기 익셉션과 IRQ 익셉션을 핸들링하고, 사용하지않는 FIQ와, 처리하지 못하는 SError익셉션은 무효 매크로로 처리한다,
	kernel_ventry	1, h, 64, irq		// IRQ EL1h
	kernel_ventry	1, h, 64, fiq		// FIQ EL1h
	kernel_ventry	1, h, 64, error		// Error EL1h

	kernel_ventry	0, t, 64, sync		// Synchronous 64-bit EL0 // 다음 4개의 엔트리는 낮은 익셉션 레벨 EL0에서 익셉션이 발생했고 당시의 실행 상태가 AArch64일때 진입한다.
	kernel_ventry	0, t, 64, irq		// IRQ 64-bit EL0
	kernel_ventry	0, t, 64, fiq		// FIQ 64-bit EL0
	kernel_ventry	0, t, 64, error		// Error 64-bit EL0

	kernel_ventry	0, t, 32, sync		// Synchronous 32-bit EL0 //4개의 엔트리는 낮은 익셉션 레벨(EL0)에서 익셉션이 발생했고, 당시의 실행 상태가 AArch32일때 진입한다.
	kernel_ventry	0, t, 32, irq		// IRQ 32-bit EL0
	kernel_ventry	0, t, 32, fiq		// FIQ 32-bit EL0
	kernel_ventry	0, t, 32, error		
```

### 컨텍스트 저장과 복원
익셉션은 핸들링 하기에 앞서서 가장 먼저 하는 일은 익셉션이 발생했을 때, 컨텍스트를 저장하는 것이다.
익셉션을 마치고 이전 코드로 돌아가기 실행 흐름을 이어나가기 위해 범용 레지스터와 프로세서의 상태, 스택 위치등을 저장한다.

커널모드와 유저모드 구분없이 동일한 레이아웃을 갖게된다. pt_regs의 크기는 스택 정렬 조건에 의해 16바이트의 배수가 된다.

> 흐름 : 익셉션 발생 -> 현재 실행중인 프로세스 컨텍스트 저장 -> 익셉션 핸들링 -> 현재 실행중인 프로세스 컨텍스트 재 진입 -> 실행

#### Kernel_entry 매크로 : 익셉션 발생 시 컨텍스트 저장하기
kernel_entry 매크로는 각 엔트리에서 가장 먼저 호출되는 매크로다.
커널 스택에 이전에 컨텍스트를 저장하고, 익셉션에서 복귀할 때 컨텍스트 복원에서 사용된다.
매크로 호출 시, 익셉션 레벨과 레지스터 크기를 저장하는데, 어떤 익셉션 레벨을 위한 컨텍스트 저장인지 사용하던 레지스터 크기는 몇 비트인지를 표시해준다.

```
<arch/arm64/kernel/entry.S>

.macro	kernel_entry, el, regsize = 64
  sub sp, sp, $S_FRAME_SIZE // 현재 익셉션의 스택에 컨텍스트를 저장하기 위한 공간을 확보하고 pt_regs의 구조체 형태로 저장한다.
	.if	\regsize == 32
	mov	w0, w0				// zero upper 32 bits of x0
	.endif
	stp	x0, x1, [sp, #16 * 0]
	stp	x2, x3, [sp, #16 * 1]
	stp	x4, x5, [sp, #16 * 2]
	stp	x6, x7, [sp, #16 * 3]
	stp	x8, x9, [sp, #16 * 4]
	stp	x10, x11, [sp, #16 * 5]
	stp	x12, x13, [sp, #16 * 6]
	stp	x14, x15, [sp, #16 * 7]
	stp	x16, x17, [sp, #16 * 8]
	stp	x18, x19, [sp, #16 * 9]
	stp	x20, x21, [sp, #16 * 10]
	stp	x22, x23, [sp, #16 * 11]
	stp	x24, x25, [sp, #16 * 12]
	stp	x26, x27, [sp, #16 * 13]
	stp	x28, x29, [sp, #16 * 14] // x0에서 x20까지의 레지스터를 스택에 저장한다.
  
	.if	\el == 0 // 진입한 익셉션 레벨이 0이라면, el0스택 포인터를 x21에 임시 저장하고, 0이 아니라면 EL1 스택포인터를 x21에 임시저장한다.
	mrs	x21, sp_el0
  mov tsk, sp
  and tsk, tsk, # ~(THREAD_SIZE - 1)
  ....
  mov x29, xzr
  .else
  add x21, sp, #S_FRAME_SIZE
  .endif
  
  mrs x22, elr_el1 // 프로세서에 의해 자동 복사된 익셉션 복귀 주소와 익셉션 발생 당시의 pstate를 포함하여 lrfh tkdydehlsms x30과 sp를 저장한다.
  mrs x23,spsr_el1 
  stp lr, x21, [sp, #S_LR]
  st x22, x23, [sp, #S_PC]
  
  .if \el == 0 // el0에서 진입했을 경우, syscallno 에 -1을 저장한다. 시스템 콜로 진입했을 경우 el0_svc에서 원본 x0과 시스템 콜 번호를 채운다. 저장할때 pt_regs 구조체의 모든 필드를 저장하게 됨
  mvn x21, xzr
  str x21, [sp, #S_SYSALLNO]
  .endif
  
  .if \el == 0 // EL0에 진입했을 경우, sp_el0에 thread_info 구조체 주소를 저장한다. head.S에서 설정한 sp_el0가 유저모드를 실행하는 동안 변경되기 때문임
  msr sp_el0, tsk
  .endif
  
  .endm
```

매크로 호출 이후의 레지스터 값들은 다음과 같다.
- x21은 익셉션 발생시 SP레지스터
- x22는 익셉션 발생시 PC레지스터
- x23은 익셉션 발생시 PSTATE레지스터

#### kernel_exit매크로: 익셉션 종료시 컨텍스트 복원

kernel_exit Macro는 익셉션 핸들링을 마치고 이전 컨텍스트로 돌아가기 위해 마지막에 호출하는 매크로다.
커널 스택에 저장했던 이전 컨텍스트를 복원하고, 익셉션 복귀 인스트럭션으로 익셉션 이전으로 돌아가 실행흐름을 재개하게 된다.
```
<arch/arm64/kernel/entry.S>

	.macro	kernel_exit, el // 스택에 저장한 pt_reg에서 pc와 pstate를 읽는다. el0로 복귀하게 되는 경우에는 sp를 복원한다.
	ldp	x21, x22, [sp, #S_PC]		// load ELR, SPSR
	.if	\el == 0
	ct_user_enter
	ldr	x23, [sp, #S_SP]		// load return stack pointer
	msr	sp_el0, x23
#ifdef CONFIG_ARM64_ERRATUM_845719
alternative_if_not ARM64_WORKAROUND_845719
	nop
	nop
#ifdef CONFIG_PID_IN_CONTEXTIDR
	nop
#endif
alternative_else
	tbz	x22, #4, 1f
#ifdef CONFIG_PID_IN_CONTEXTIDR
	mrs	x29, contextidr_el1
	msr	contextidr_el1, x29
#else
	msr contextidr_el1, xzr
#endif
1:
alternative_endif
#endif
	.endif
	msr	elr_el1, x21			// set up the return data // 앞서 읽어낸 pc와 pstate를 elr_el1과 spsr_el1에 복원함. 코드 마지막에 있는 eret인스트럭션을 실행하면 프로세서에 의해 elr_el1과 spsr_el1에 설정된 값을 pc,pstate로 사용하기 때문이다.
	msr	spsr_el1, x22
	ldp	x0, x1, [sp, #16 * 0] // 스택에 저장한 x0~x29, lr(x30)을 복원시킨다.
	ldp	x2, x3, [sp, #16 * 1]
	ldp	x4, x5, [sp, #16 * 2]
	ldp	x6, x7, [sp, #16 * 3]
	ldp	x8, x9, [sp, #16 * 4]
	ldp	x10, x11, [sp, #16 * 5]
	ldp	x12, x13, [sp, #16 * 6]
	ldp	x14, x15, [sp, #16 * 7]
	ldp	x16, x17, [sp, #16 * 8]
	ldp	x18, x19, [sp, #16 * 9]
	ldp	x20, x21, [sp, #16 * 10]
	ldp	x22, x23, [sp, #16 * 11]
	ldp	x24, x25, [sp, #16 * 12]
	ldp	x26, x27, [sp, #16 * 13]
	ldp	x28, x29, [sp, #16 * 14]
	ldr	lr, [sp, #S_LR] 
	add	sp, sp, #S_FRAME_SIZE		// restore sp // 스택포인터를 복원하고 익셉션 복귀 인스트럭션을 사용하여 복귀한다.
	eret					// return to kernel
	.endm
```
