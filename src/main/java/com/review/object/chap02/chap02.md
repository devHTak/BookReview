#### Chap 02 객체 지향 프로그래밍

- 영화 예매 시스템 요구사항
  - 영화
  - 할인 정책
  - 할인 조건
  - 상영
  - 예매

- 클래스 구현
  - 자율적인 객체
    - 객체가 상태와 행동을 함께 가지는 복합적인 존재
    - 객체가 스스로 판단하고 행동하는 자율적인 존재
    - 캡슐화
      - 객체 내부에 접근을 통제함으로써 객체의 자율적인 존재를 만든다
      - 접근제어 메커니즘 제공
  - 프로그래머의 자유
    - 클래스 작성자는 새로운 데이터 타입을 프로그램에 추가
    - 클라이언트 프로그래머는 클래스 작성자가 추가한 데이터 및 클래스를 엮어 애플리케이션을 빠르고 안정적으로 구축
    - 구현은닉: 클라이언트 프로그래머가 내부에 접근하지 못하도록 하므로써 영향도를 줄이기 위한 것
  - 협력하는 클래스
    - 클래스는 다른 클래스에게 요청할 수 있고, 요청받은 클래스는 로직을 통해 만든 응답을 전송한다.
    - 요청과 응답에서 주고 받는 것은 메시지이다(메시지 송신, 메시지 수신)

- 상속과 다형성
  - 컴파일 시간 의존성과 실행 시간 의존성
    - 의존성: 어떤 클래스가 다른 클래스에 접근할 수 있는 경로를 가지거나 해당 클래스의 객체의 메서드를 호출하는 경우
    - Movie 클래스는 DiscountPolicy 의존성을 갖는다.
    - 실행 시점 의존성: Movie 클래스 생성자에 DiscountPolicy 구현체인 AmountDiscountPolicy, PercentDiscountPolicy를 생성하여 넘겨주는 데 이때 의존성이 결정된다.
    - 동적바인딩: 추상 메서드가 어떤 메서드가 실행될 것인지는 메시지를 수신하는 객체의 클래스가 무엇이냐에 따라 달라지는데(다형성) 메서드를 실행 시점에 바인딩하는 것을 말한다.

- 추상화와 유연성
  - 추상화의 힘: DiscountPolicy, DiscountCondition 은 추상클래스, 인터페이스로 구현함으로써 유연성을 더했다.
    - 추가적인 DiscountPolicy, DiscountCondition이 추가되더라도 해당 로직을 수정하는 것이 아닌 새로운 구현체를 만들어 사용할 수 있다.
  - 추상 클래스와 인터페이스 트레이드오프
    - 인터페이스를 사용하여 추상화를 높이는 것이 실제로 더 좋은 설계가 될 것이다.
    - 하지만 중복 코드가 발생할 수 있다.
  - 코드 재사용
    - 코드를 재사용하기 위해서 다양한 방법이 존재한다.
      - 대표적으로 상속과 합성이 있다.
    - 상속에 문제점
      - 캡슐화 위배. 상속을 사용하기 위해서는 부모클래스의 구현이 자식클래스에게 노출되기 때문에 캡슈화가 약화된다.
      - 설계가 유연하지 않다. 부모클래스와 자식클래스의 관계를 컴파일 시점에 결정하기 때문에 실행 시점에 객체의 종류를 변경하는 것이 불가능하다.
        - 예를 들어, 실행 시점에 금액할인정책인 영화를 비율할인정책으로 변경한다면, 이미 생성된 객체의 클래스를 변경하는 기능을 지원하지 않기 때문에 최선의 방법은 비율할인정책을 생성하여 금액할인정책에 상태를 복사하는 것이다.
        - 이 것은 부모클래스와 자식클래스가 강하게 결합되어 있기 때문에 발생한다.
        - 인스턴스 변수로 연결한 기존방법을 사용하면 변경할 수 있다. 즉, 상속보다 인스턴스 변수로 관계를 연결한 원래의 설계가 더 유영하다는 것을 알 수 있다. 
      
        ```java
        public class Movie {
            // ...
            public void changeDiscountPolicy(DiscountPolicy discountPolicy) {
                this.discountPolicy = discountPolicy;
            }
        }
        ```
    - 합성
      - 합성은 객체가 다른 객체의 참조자를 얻는 방식으로 런타임시에 동적으로 이뤄지며 has-a 관계라고 한다 
      - 따라서 다른 객체의 참조자를 얻은 후 그 참조자를 이용해서 객체의 기능을 이용하기 때문에 해당 객체의 인터페이스만을 바라보게 됨으로써 캡슐화가 잘 이뤄질 수 있다.
      - 단점으로는 객체간의 관계가 수평관계를 이루기 때문에 객체나 메서드명이 명확하지 않으면 가독성이 떨어지고 이해하기 어려워진다.
