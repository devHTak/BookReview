#### Appendix B. 타입 계층의 구현

- 타입을 구현하는 방법은 다양하며 타입계층은 다양한 방식으로 구현된 타입들을 하나의 타입 계층안에 조합할 수 있기 때문에 더 복잡하다
- 클래스를 이용한 타입 계층 구현
  - 클래스를 객체지향 언어에서 사용자 정의 타입이라고 부르는 이유
    - 타입은 객체의 퍼블릭 인터페이스를 가리키기 때문에 결과적으로 클래스는 객체의 타입과 구현을 동시에 정의하는 것과 같다
  - 클래스에서 정의한 퍼블릭 인터페이스는 동일하지만 다른 방식으로 구현해야 하는 객체가 필요하다면
    - 상속을 이용하면 자식 클래스가 부모 클래스의 구현뿐만 아니라 퍼블릭 인터페이스도 물려받을 수 있기 때문에 타입 계층을 쉽게 구현할 수 있다.
    - 하지만 상속은 자식 클래스를 부모 클래스의 구현에 강하게 결합시키기 때문에 구현 클래스를 상속받는 것은 피해야 한다
			
- 인터페이스를 이용한 타입 계층 구현
  - 상속으로 인한 결합도 문제를 피하고 다중 상속이라는 구현 제야도 해결할 수 있는 방법은 인터페이스를 사용하는 것이다
  - 특징 
    - 여러 클래스가 동일한 타입을 구현할 수 있다
      - 서로 다른 클래스가 동일한 타입(인터페이스)를 구현이 가능하며 클라이언트에게 동일한 메시지를 응답할 수 있다
    - 하나의 클래스가 여러 타입을 구현할 수 있다
      - 하나의 클래스에 여러 타입(인터페이스)를 구현할 수 있다
	
- 추상클래스를 이용한 타입 계층 구현
  - 클래스 상속을 이용하면서 결합도로 인한 부작용을 피하는 방법
  - 특징
    - 의존하는 대상의 추상화 정도가 다르다
      - 의존하는 대상의 추상적일수록 결합도는 낮아지고 결합도가 낮아질수록 변경으로 인한 영향도는 줄어든다
    - 상속을 사용하는 의도
      - 추상메서드를 제공함으로써 상속 계층을 쉽게 확장할 수 잇게 하고 결합도로 인한 부작용을 방지할 수 있는 안전망을 제공한다
- 추상클래스와 인터페이스 결합하기
  - 인터페이스의 단점으로 Java 8 버전 이전에는 인터페이스에 구현코드를 포함시킬 수 없기 때문에 인터페이스 만으로는 중복 코드를 제거하기 어렵다는 한계가 있다
  - 골격 구현 추상 클래스
    - 인터페이스를 이용해 타입을 정의하고 특정 상속 계층에 국한된 코드를 공유할 필요가 있을 경우에는 추상클래스를 이용해 코드 중복 방지
    - 인터페이스만 사용했을 때, 중복코드가 발생하면 추상클래스로 기본 구현을 제공하여 중복 코드 제거
  - 추상클래스와 인터페이스를 함께 사용하는 것이 추상클래스만 사용하는 것보다 좋다
    - 다양한 구현 방법이 필요한 경우 새로운 추상 클래스를 추가하여 쉽게 해결할 수 있다
    - 이미 부모 클래스가 존재하는 클래스라고 하더라도 인터페이스를 추가함으로써 새로운 타입으로 쉽게 확장할 수 있다