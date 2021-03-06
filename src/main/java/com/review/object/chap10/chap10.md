#### 상속과 코드 재사용

##### 상속과 중복 코드

- DRY(Don't Repeat Yourself) 원칙
  - 요구사항이 변경됐을 때 두 코드를 함께 수정해야 하는 것이 중복코드이며 코드의 모양이 유사한 것은 중복의 징후일 뿐이다

- 중복과 변경
  - 예시: 한달에 한번씩 가입자별 전화 요금 계산 어플리케이션
    - chap10/a.dup 에 중복 코드를 삭제하기 위해 chap10/c.inherit을 사용했다.
    - 하지만 c.inherit 예제인 상속은 Phone 과 NightlyDiscountPhone 은 강하게 결합되어 있다.
  - 상속은 결합도를 높인다.
    - 결합도를 하나의 모듈이 다른 모듈에 대해 지식의 정도를 나타내는 데, 상속은 부모클래스, 자식클래스 간에 결합을 높이게 된다.

- 강하게 결합된 Phone, NightlyDiscountPhone
  - 추가 로직이 발생하면 Phone에 대한 수정이 NightlyDiscountPhone으로 이어진다.
  - 즉, 새로운 중복코드를 만들게 된다

##### 취약한 기반 클래스 문제

- 부모 클래스의 변경에 의해 자식 클래스가 영향을 받는 현상을 취약한 기반 클래스 문제라고 한다.
- 취약한 기반 클래스 문제는 캡슐화를 약화시키고 결합도를 높인다.

- 불필요한 인터페이스 상속 문제
  - vector와 stack
    - 자바 초창기에 상속 문제로 vector 를 상속한 stack 에서 찾을 수 있다.
    - vector 에 public interface
      - get, add, remove
    - stack 에 public interface
      - push, pop
    - stack 은 FIFO 자료구조이지만 vector를 상속받기 때문에 add를 사용할 수 있다.
    - 기본적인 stack 에 원칙을 무너트린다

- 메서드 오버라이딩의 오작용 문제
  - HashSet 과 강하게 결합된 InstrumentedHashSet
  - InstrumentedHashSet
    ```java
    public class InstrumentedHashSet<E> extends HashSet {
        private int addCount = 0;
        @Override
        public boolean add(E e) {
            this.addCount++;
            return super.add(e);
        }
    
        @Override
        public boolean addAll(Collection<? extends E> c) {
            this.addCount += c.size();
            return super.addAll(c);
        }
    }
    ```
    - InstrumentedHashSet 을 보았을 때에는 문제가 없어 보지만 실제 사용하면 문제가 발생한다.
      - HashSet 에 addAll 메서드 안에는 add를 호출하기 때문에 addCount 에 개수가 맞지 않게 된다.

- 부모클래스와 자식클래스 동시 수정 문제
  - 상속을 사용하면 자식클래스는 부모클래스에 대한 지식을 알도록 강요받기 때문에 함게 수정해야 하는 상황이 발생한다.

- 상속을 위한 경고
  - 자식클래스의 메서드 안에서 super 참조를 이용해 부모 클래스의 메서드를 직접 호출할 경우 두 클래스는 강하게 결합된다. super 호출을 제거할 수 있는 방법을 찾아 결합도를 제거하라
  - 상속받은 부모 클래스의 메서드가 자식 클래스의 내부 구조에 대한 규칙을 깨트릴 수 있다.
  - 자식클래스가 부모클래스의 메서드를 오버라이딩할 경우 부모클래스가 자신의 메서드를 사용하는 방법에 자식클래스가 결합될 수 있다.
  - 클래스를 상속하면 결합도로 인해 자식클래스와 부모클래스의 구현을 영원히 변경하지 않거나 자식클래스와 부모클래스를 동시에 변경하거나 둘 중 하나를 선택할 수 밖에 없다.

##### Phone 다시 살펴보기

- 추상화에 의존하자
  - 상속에서의 문제점은 부모클래스와 자식클래스가 강하게 결합되어 있는 것이다
  - 이는 부모클래스와 자식클래스 모두 추상화에 의존하도록 수정하는 것으로 해결할 수 있다.
  - 코드 중복 제거를 위한 상속 원칙
    - 두 메서드가 유사하게 보인다면 차이점을 메서드로 추출하라. 메서드 추출을 통해 두 메서드를 동일한 형태로 보이도록 만들 수 있다.
    - 부모클래스의 코드를 하위로 내리지 말고 자식클래스의 코드를 상위로 올려라. 부모 클래스의 구체적인 메서드를 자식클래스로 내리는 것보다 자식클래스의 추상적인 메서드를 부모클래스로 올리는 것이 재사용성, 응집도 측면에서 더 뛰어난 효과를 얻을 수 있다.

- 차이를 메서드로 추출하라
  - Phone, NightlyDiscountPhone 클래스에서의 차이는 Call 별로 요금을 계산하는 방식이다
    - calculateCallFee()로 새롭게 추출

- 중복코드를 부모클래스로 올려라
  - 모든 클래스가 추상화에 의존할 수 있도록 부모클래스를 추가하자(AbstractPhone)
    - calculateFee에 구조는 같다 -> List<Call> calls 를 반복하여 해당 요금 * 통화시간을 더하는 것
    - 차이는 call에 대한 요금 부과로 수정이 변하는 부분은 calculateCallFee 로 메서드를 분리하였다.
    - 부모클래스는 calculateFee를 계산할 때 calculateCallFee 를 protected + 추상화 메서드로 선언한 후 이를 상속받는 자식클래스가 이를 구현하도록 한다 

- 추상화가 핵심이다
  - 부모클래스는 이제 전체 통화목록에 대한 계산이 바뀔 때에만 변경된다
  - calculateCallFee 메서드에 대한 시그니처가 변경되는 경우가 아닌 이상 자식클래스를 수정할 필요가 없게 된다.
    - 부모클래스와 자식클래스에 낮은 결합도
  - 한 건에 대한 계산이 변경되는 경우에는 AbstractPhone을 상속받는 새로운 구현체를 추가하면 된다.
    - OCP 원칙을 준수한다.
  - 상속계층이 코드를 진화시키는 데 걸림돌이 된다면 추상화를 찾아내고 상속 계층 안의 클래스들이 그 추상화에 의존하도록 코드를 리팩토링하자
    - 차이점을 메서드로 추출하고 공통적인 부문은 부모클래스로 이동

- 인스턴스 변수의 추가
  - 클래스는 메서드 뿐만 아니라 인스턴스 변수도 함께 추가될 수 있다.
  - 따라서 상속은 부모클래스의 인스턴스 변수에 대해서도 결합하게 만든다.
  - 객체의 행동만 변화하게 된다면 추상화 의존 방식으로 유연하게 대처할 수 있지만 인스턴스 변수에 추가는 변경을 초래한다
    - 상속관계에서는 어쩔 수 없다.
  - 우리가 원하는 것은 행동을 변경하기 위해 인스턴스 변수를 추가하여도 상속 계층 전역에 걸친 부작용을 퍼지지 않게 막는 것이다.

#### 차이에 의한 프로그래밍

- 기존코드와 다른 부분만을 추가함으로써 애플리케이션의 기능을 확장하는 방법을 차이에 의한 프로그래밍(programming by difference)
- 차이에 의한 프로그래밍의 목표는 중복 코드를 제거하고 코드를 재사용하는 것이다.
- 중복이 아닌 재사용을 위한 프로그래밍은 단순한 타이핑의 문제를 넘어 버그에 대한 문제, 코드의 품질을 높이는 방법이다.
- 중복을 줄이는 방법으로 대표적인 방법은 상속
  - 하지만 상속은 자식클래스가 부모클래스에 대한 정보를 알게 되기 때문에 결합도가 높아진다.
  - 이를 막기 위해서 추상화 의존으로 해결할 수 있다
- 상속에 문제점을 해결하기 위한 다른 방법으로는 합성이 있다