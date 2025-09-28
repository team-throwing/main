# 1. Iterable

<br>

Java Collection 프레임워크의 근간을 이루는 최상위 인터페이스입니다.

`Iterable` 인터페이스를 구현한 객체는 for-each(enhanced for) 문의 대상이 될 수 있습니다.

```java
for (Number n : numbers) {
		...
}
```

즉, 어떤 클래스가 이 인터페이스를 구현한다는 것은 “이 객체는 그 안의 요소들을 순차적으로 순회할 수 있습니다.” 라고 약속하는 것과 같습니다.

<img width="3000" height="2240" alt="111111" src="https://github.com/user-attachments/assets/2406ad8e-459d-440a-9952-ea79edcfae06" />


`Collection` 인터페이스는 `Iterable` 인터페이스에서 확장된 인터페이스입니다. 따라서 `Collection` 를 구현하는 `List` , `Set` , `Queue` 인터페이스의 구현체의 요소들은 for-each 문, forEach() 메서드, 그리고 Iterator 를 사용하여 순회할 수 있습니다.

그러나 자바 컬렉션 프레임워크 중 `Collection` 인터페이스를 확장하지 않는 `Map` 인터페이스의 구현체의 경우 `Iterable` 인터페이스가 제공하는 순회 기능을 활용할 수 없습니다.

`Iterator` 인터페이스는 다음의 세 가지 메서드를 가지고 있습니다.

<br>

### 1.1. `iterator()`

요소 순회에 사용할 수 있는 `Iterator` 객체를 반환합니다.

<br>

### 1.2. `forEach(Consumer<T> action)`

각 요소에 대해 주어진 동작(`action`)을 수행합니다.

다시 말해, `Iterable` 의 내부 요소 각각에 대한 `action` 콜백을 실행합니다. 모든 요소가 처리되거나 `action` 콜백이 예외를 던지면 종료됩니다.

- 순서가 존재하는 경우(ex. `ArrayList`), 그 순서를 따릅니다.
- `action` 콜백이 던진 예외는 `forEach` 메서드를 호출했던 위치로 전달됩니다.

`Iterable` 인터페이스의 `default forEach(...)` 메서드를 살펴보면 동작을 더 잘 이해할 수 있을 것입니다.

```java
default void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    for (T t : this) {
        action.accept(t);
    }
}
```

위 디폴트 메서드는 만약 `action` 이 side-effect 를 가질때 어떻게 처리할 지 결정하지 않습니다. 본 맥락에서 side-effect 는 순회 중 콜렉션의 구조를 변경하는 행위 즉, 콜렉션의 크기나 순서에 영향을 주는 작업을 말합니다. 예를 들어,

- 요소 추가(ex. `list.add(...)`)
- 요소 제거(ex. `list.remove(...)`)
- 비우기(ex. `list.clear()`)

이러한 동작에 대해서 어떻게 처리할 지 결정하지 않는다는 것은 **매우 중요한 경고**로 다음과 같은 의미를 내포합니다.

1. 대부분의 경우 `ConcurrentModificationException` 이 발생할 수 있습니다.
    - `ArrayList`, `LinkedList`, `HashSet` 와 같은 일반적인 컬렉션의 forEach 메서드나 for-each 문 내부에서 side-effect 가 발생하는 경우 `ConcurrentModificationException` 예외가 발생합니다. 이는 구현체의 fail-fast 동작 방식 때문입니다.
        
        forEach 메서드 (또는 for-each 문) 가 내부적으로 사용하는 `Iterator` 는 생성될 때의 컬렉션 상태(`modCount`) 를 기억하고 있는데, 순회 도중 이 값에 대한 `Iterator` 를 통하지 않은 변경이 감지되면 즉시 예외를 던져 더 큰 문제를 방지합니다.
        
    
2. 그러나 예외 없이 오동작 할 수도 있습니다. 예를 들어
    - 특정 요소를 건너뜀
    - 이미 처리한 요소를 다시 처리
    - 무한 루프에 빠짐
    - …
    
    등의 예측 불가능한 상황이 발생할 수 있습니다. 따라서 예외가 발생하지 않았다고 해서 코드가 안전하다고 단정해서는 안 됩니다.
    
    앞서 말했던 fail-fast 동작은 잠재적인 오류를 피하기 위한 최선의 노력(best-effort)일 뿐 코드의 정확성을 보장해주지는 않습니다.
    

그러나 하위 클래스에서 변경에 대한 동시성 정책(concurrent modification policy)을 지정한 경우에는 예외입니다. 이 경우에는 동시 수정이 가능합니다. 예를 들어, `java.util.concurrent` 패키지에 있는 동시성 컬렉션이 이에 해당합니다.

<br>

### 1.3. `spliterator()`

병렬 순회가 가능한 `Spliterator` 객체를 반환합니다.

<br>

# 2. Iterator

<br>

컬렉션의 요소들을 순회하기 위한 ‘커서’ 객체입니다.

기본적인 순회 방식인 단방향 순회를 지원합니다. 컬렉션의 요소를 처음부터 끝까지 한 방향으로만 순회할 수 있습니다.

과거에 사용하던 `Enumeration` 을 대체하기 위해 만들어졌으며, 아래 두 가지 주요한 개선 사항이 있습니다.

- `Iterator` 는 underlying collection 의 요소를 순회 중에 삭제할 수 있습니다.
- 더 직관적인 메서드 이름을 사용합니다.

<br>

### 2.1. `hasNext()`

순회할 요소가 남은 경우 `true` 를 반환합니다.

<br>

### 2.2. `next()`

다음 요소를 반환합니다. 요소가 없으면 `NoSuchElementException` 을 던지므로 호출 전 `hasNext()` 메서드를 통한 검사가 필요합니다.

<br>

### 2.3. `remove()`

`next()` 메서드를 통해 가장 최근에 반환된 요소를 컬렉션에서 제거합니다.

<br>

### 2.4. `forEachRemaining(Consumer<? super T> action)`

`hasNext()` 가 `true` 인 동안 남아있는 모든 요소에 대해 주어진 동작(`action` 콜백)을 수행합니다.

<br>

### 2.5. 예시

`List<String>` 의 요소들을 순회하며 출력하고, 특정 요소를 안전하게 제거

```java
List<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");

System.out.println("초기 list = " + list);

Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    String element = iterator.next();
    System.out.println(element);

    // next()로 반환된 "B"를 안전하게 제거
    if (element.equals("B")) {
        System.out.println("요소 \"B\" 제거!");
        iterator.remove();
    }
}

// list의 최종 상태: ["A", "C"]
System.out.println("최종 list = " + list);
```

- 실행 결과
    
    > 초기 list = [A, B, C]  
    > A  
    > B  
    > 요소 "B" 제거!  
    > C  
    > 최종 list = [A, C]  

<br>

# 3. ListIterator

<br>

`List` 타입의 컬렉션에서만 사용 가능한 `Iterator` 를 확장한 인터페이스입니다.

`Iterator` 의 기능에 더해 여러 강력한 기능을 제공합니다.

- 양방향 순회
- 요소 수정
- 요소 삽입
- 현재 위치(인덱스) 확인

`List` 인터페이스의 `listIterator(initIndex)` 메서드를 사용하여 사용할 수 있습니다. `initIndex` 는 생성 직후 `next()` 메서드를 호출했을 때 반환될 요소의 인덱스입니다.

```java
ListIterator<Integer> listIter ;
listIter = list.listIterator(initIndex);
```

`ListIterator` 의 동작을 이해하려면 커서의 위치에 유의해야 합니다.

<img width="1160" height="920" alt="2222222" src="https://github.com/user-attachments/assets/0b410f7d-5251-47bf-a0a5-723a20b8a8b6" />


- 정방향으로 순회하려면 `list.listIterator(0)` 를 사용합니다. 이 경우 커서는 가장 앞(왼쪽 끝)에 위치합니다.
- 역방향으로 순회하려면 `list.listIterator(list.size())` 를 사용합니다. 이 경우 커서는 가장 뒤(오른쪽 끝)에 위치합니다.
- 생성자의 파라미터 값을 조절하며 중간에서 시작할 수도 있습니다.

지금부터는 `ListIterator` 의 주요 메서드를 살펴봅시다.

<img width="3240" height="2960" alt="33333333" src="https://github.com/user-attachments/assets/cd04b813-418c-4aad-a89b-0c772f755265" />


<br>

### 3.1. `hasNext()`

현재 커서의 뒤(오른쪽)에 요소가 있으면 true 를 반환합니다.

<br>

### 3.2. `next()`

현재 커서의 뒤(오른쪽)에 있는 요소를 반환합니다. 만약 커서가 가장 뒤에 있었다면 `NoSuchElementException` 예외를 던집니다.

<br>

### 3.3. `nextIndex()`

커서의 뒤(오른쪽)에 있는 요소(당장 `next()` 를 호출했을 때 반환될 요소)의 인덱스를 반환합니다.

만약 커서가 가장 뒤에 있다면 `list.size()` 를 반환합니다.

<br>

### 3.4. `hasPrevious()`

현재 커서의 앞(왼쪽)에 요소가 있으면 `true` 를 반환합니다.

만약 커서가 가장 앞에 있다면 `-1` 을 반환합니다.

<br>

### 3.5. `previous()`

현재 커서의 앞(왼쪽)에 요소가 있으면 그 요소를 반환합니다. 만약 커서가 가장 앞에 있었다면 `NoSuchElementException` 예외를 던집니다.

<br>

### 3.6. `previousIndex()`

커서의 앞(왼쪽)에 있는 요소(당장 `previous()` 를 호출했을 때 반환될 요소)의 인덱스를 반환합니다.

<br>

### 3.7. `add(E e)`

현재 커서 위치에 요소를 삽입합니다. 삽입한 후에 커서의 위치는 순회 중인 방향과 무관하게 항상 삽입된 요소의 뒤(오른쪽)에 위치합니다.

다시 말해, 연속된 `add(e)` 메서드의 호출은 순회 방향과 무관하게 항상 뒤(오른쪽으)로 이어지며, `add(e)` 직후 `previous()` 를 호출하면 방금 삽입했던 e가 조회됩니다.

<br>

### 3.8. `remove()`

가장 최근에 조회했던 요소를 제거합니다.

따라서, `remove()` 메서드는 연속해서 호출이 불가능합니다. 반드시 `next()` 또는 `previous()` 로 조회한 뒤에 `remove()` 를 호출할 수 있습니다. 만약 조회하지 않고 이 메서드를 호출한 경우 `IllegalStateException` 예외가 발생합니다.

또한 `add(e)` 메서드를 호출한 직후에 이 메서드를 호출할 수 없습니다. 예를 들어 `add` 메서드를 호출한 경우, 반드시 `next()` 또는 `previous()` 메서드를 호출하고 나서 호출해야합니다. 만약 이를 어긴경우 마찬가지로 `IllegalStateException` 예외가 발생합니다.

<br>

### 3.9. `set(E e)`

가장 최근에 조회했던 요소를 주어진 객체로 변경합니다.

`remove()` 메서드와 비슷하게 연속 호출이 불가하며, `remove()` 그리고 `add(e)` 메서드 호출 직후에 사용할 수 없습니다. 반드시 `next()` 또는 `previous()` 메서드를 사용하여 조회한 직후에만 호출할 수 있습니다. 만약 이를 어기는 경우 마찬가지로 `IllegalStateException` 예외가 발생합니다.

<br>

### 3.10. 삭제 시 주의사항

개인적으로 `remove()` 는 `ListIterator` 에서 가장 혼동하기 쉬운 기능이라고 생각합니다. `remove()` 메서드는 순회 방향에 따라 삭제 대상이 변하기 떄문입니다.

1. 정방향 순회 시에는 현재 커서의 앞(왼쪽)을 삭제한다.
2. 역방향 순회 시에는 현재 커서의 뒤(오른쪽)을 삭제한다.

몇 가지 코드 예시를 살펴보는 것이 기능을 확실하게 파악하는 데 도움이 될 수 있습니다.

1. 현재 커서 방향의 앞(왼쪽)에 있는 요소를 삭제하는 것은 항상 동일한 코드로 수행할 수 있다.
    
    ```sql
    ...
    if (lit.hasPrevious()) {
    		lit.previous();
    		lit.remove();
    }
    ```
    
2. `remove()` 메서드는 연속 호출 할 수 없다.
    1. 정방향으로 연속해서 삭제하려는 경우
        
        ```java
        lit.next();  // 이 원소부터 정방향 연속 삭제를 원할 때,
        for (...) {
        		if (lit.hasNext()) {
        				lit.next();
        				lit.remove();
        		} else {
        				// 더 이상 삭제할 원소가 없음
        		}
        }
        ```
        
    2. 역방향으로 연속해서 삭제하려는 경우
        
        ```java
        lit.next();  // 이 원소부터 역방향 연속 삭제를 원할 때,
        for (...) {
        		if (lit.hasPrevious()) {
        				lit.previous();
        				lit.remove();
        		} else {
        				// 더 이상 삭제할 원소가 없음
        		}
        }
        ```
        
    
3. 삽입 직후 삭제가 불가능하다.
    1. 삽입한 대상을 삭제하기를 원한다면,
        
        ```java
        ...
        lit.add(e);
        lit.previous();  // 별도의 검증이 필요 없음. 항상 방금 삽입한 e가 반환됨
        lit.remove();
        ```
        
    2. 삽입한 대상의 오른쪽에 있는 요소를 삭제하기 바란다면,
        
        ```java
        ...
        lit.add(e);
        if (e.hasNext()) {
        		lit.next();
        		lit.remove();
        } else {
        		// 오른쪽 끝이라 삭제할 수 없음
        }
        ```
        
    3. 삽입한 대상의 왼쪽에 있는 요소를 삭제하기 바란다면,
        
        ```java
        ...
        lit.add(e);
        lit.previouse(e);  // 별도의 검증이 필요 없음. 항상 방금 삽입한 e가 반환됨
        if (e.hasPrevious()) {
        		lit.previous();
        		lit.remove();
        } else {
        		// 왼쪽 끝이라 삭제할 수 없음
        }
        ```
        

<br>

### 3.11. 예시

```java
// 예시: ListIterator를 사용한 양방향 순회 및 수정
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        ListIterator<String> listIterator = list.listIterator();

        System.out.println("[정방향 순회]");
        while (listIterator.hasNext()) {
            System.out.print("listIterator.nextIndex() = " + listIterator.nextIndex());
            String element = listIterator.next();
            System.out.println(" => element = " + element);

            // "B"를 "X"로 교체
            if ("B".equals(element)) {
                listIterator.set("X");
            }
        }
        // listIterator.nextIndex() == list.size()
        System.out.println("* listIterator.nextIndex() = " + listIterator.nextIndex());

        System.out.println("\n[역방향 순회]");
        while (listIterator.hasPrevious()) {
            System.out.print("listIterator.previousIndex() = " + listIterator.previousIndex());
            String element = listIterator.previous();
            System.out.println(" => element = " + element);
            
            // A를 제거
            if ("A".equals(element)) {
                listIterator.remove();
            }
        }
        // listIterator.previousIndex() == -1
        System.out.println("* listIterator.previousIndex() = " + listIterator.previousIndex());

        // list의 최종 상태: ["X", "C"]
        System.out.println("\n최종 list = " + list);
```

- 실행 결과
    
    > [정방향 순회]  
    > listIterator.nextIndex() = 0 => element = A  
    > listIterator.nextIndex() = 1 => element = B  
    > listIterator.nextIndex() = 2 => element = C  
    > listIterator.nextIndex() = 3  
    >   <br>
    > [역방향 순회]  
    > listIterator.previousIndex() = 2 => element = C  
    > listIterator.previousIndex() = 1 => element = X  
    > listIterator.previousIndex() = 0 => element = A  
    > listIterator.previousIndex() = -1  
    >   <br>
    > 최종 list = [X, C]  

<br>

# 4. **Iterable과 Iterator 사이의 관계**

<br>

`Iterable` 과 `Iterator` 는 역할이 명확히 분리된 **협력 관계**를 갖습니다.

`Iterable` 은 순회 가능한 데이터의 집합 그 자체를 나타냅니다. `Iterable` 의 역할은 `iterator()` 메서드를 통해 자신을 순회할 수 있는 `Iterator` 객체를 제공해야 함을 명시하는 것입니다.

반면, `Iterator` 은 실제로 순회가 이루어지는 방식을 추상화합니다.

이처럼 순회 책임을 분리하면 결합도를 낮추고, 컬렉션의 내부 구조를 감추면서, 항상 동일한 방식으로 안전하게 순회할 수 있습니다.

1. 동일한 코드 패턴을 다양한 컬렉션에 대해 적용할 수 있으므로 코드를 작성하고 이해하기 쉬워집니다. ⇒ **생산성 및 가독성 개선**
2. 내부 구조가 외부에 노출되지 않으므로 데이터를 보호하고 안정성을 확보할 수 있습니다. ⇒ **캡슐화에 따른 이점 활용**
3. 내부 구조가 변하더라도 외부 순회 코드를 변경할 필요가 없으며, 다양한 순회 방식을 동일한 컬렉션에 적용해 볼 수 있다. ⇒ **유연성 및 확장성**

<br>

### 4.1. `Iterator` 와 `ConcurrentModificationException`

일부 `Iterable` 객체를 `Iterator` 를 통해서 순회할 때, `Iterator` 를 통하지 않고 컬렉션의 구조를 변경하는 경우 `ConcurrentModificationException` 이 발생할 수 있습니다.

- 요소 추가(ex. `list.add(...)`)
- 요소 제거(ex. `list.remove(...)`)
- 비우기(ex. `list.clear()`)

`Iterator` 를 통해 순회하던 중 `Iterator` 외부에서 변경이 일어나면(동기화되지 않은 변경), `Iterator` 에서 추적하고 있던 `Iterable` 의 내부 상태가 실제 상태와 달라지게 됩니다. 이는 예기치 못한 오류를 유발할 수 있으므로 많은 컬렉션 구현체에서 이와 같은 동작을 금지하고 있습니다. 동기화 되지 않은 변경의 예를 들어 보겠습니다.

- `Iterator` 를 이용하여 순회하면서, `Iterator` 를 이용하지 않고 별개로 컬렉션을 변경하는 경우(ex. 순회 중에 `iter.remove()` 말고 `list.remove()` 를 사용하는 경우)
- 한 스레드에서 `Iterator` 를 이용하여 순회하던 중에, 다른 스레드가 컬렉션을 변경하는 경우

동기화 되지 않은 변경이 감지되면 설령 그 변경이 문제를 불러오지 않을 수 있더라도 fail-fast 하게 예외를 발생시킵니다.

  > [!Tip]
  > 물론 이와 같은 동작이 `Iterable` 인터페이스에 정해져 있는 사항은 아닙니다. `Iterable` 은 이러한 경우에 대해 어떻게 처리할 지 결정하고 있지 않습니다. 예를 들어, `java.util.concurrent` 패키지에 있는 동시성 컬렉션의 경우 스레드 안전한 방식으로 동시에 변경할 수 있으므로 이와 같은 예외가 발생하지 않습니다.

따라서 `Iterator` 로 순회하는 동시에 `Iterator` 외부에서 컬렉션을 변경하지 않도록 주의해야 합니다.

<br>

### 4.2. 암묵적 `Iterator` 순회(for-each & `forEach`)

`Iterator` 를 통한 순회는 다음과 같은 경우에 일어납니다.

1. 명시적으로 `Iterator` 객체를 사용
2. for-each 문을 통해 순회
    
    `Iterable` 객체에 대한 for-each 문은 내부적으로 `Iterator` 를 사용하여 동작하도록  컴파일러에 의해 변환됩니다.
    
    (배열의 경우에는 예외적으로 전통적인 인덱스 기반의 for 루프로 변환되어 처리됩니다.)
    
3. forEach 메서드
    
    `Iterable` 의 `forEach` 메서드는 내부적으로 `Iterator` 를 직접 사용하거나 for-each 문을 통해 사용하도록 구현될 수 있습니다.
    
    결과적으로 for-each 구문을 사용하든지 `forEach` 메서드를 사용하든지, 모두 내부적으로는 `Iterator` 를 통해 순회됩니다. (순수 배열을 제외하고)
    

for-each 문이나 `forEach` 메서드를 사용하는 경우 `Iterator` 의 사용이 눈에 잘 띄지 않으므로 `ConcurrentModificationException` 을 유발하기 쉬우므로 더 조심해야 합니다.

<br>

### 4.3. `Collection.removeIf(Predicate<? super E> filter)`

이러한 실수를 예방하기 위해 `Collection` 인터페이스의 `removeIf` 메서드를 사용하는 것을 고려해 볼 수 있습니다.

이 메서드는 `filter` 콜백을 적용했을 때 `true` 가 반환되는 모든 요소를 삭제합니다. 내부적으로 `Iterator` 를 이용하므로 안전하게 요소를 삭제할 수 있으며 훨씬 간결합니다.

<br>

# 5. 마치면서…

`Iterable` 과 `Iterator` 의 단순 사용법이 아닌 그 안에 담긴 설계 원칙을 이해하는 것이 중요합니다. 마지막으로 이 부분을 짚고 마치도록 하겠습니다.

`Iterable` 과 `Iterator` 는 역할이 명확히 분리된 **협력 관계**를 갖습니다.

`Iterable` 은 순회 가능한 데이터의 집합 그 자체를 나타냅니다. `Iterable` 의 역할은 `iterator()` 메서드를 통해 자신을 순회할 수 있는 `Iterator` 객체를 제공해야 함을 명시하는 것입니다.

반면, `Iterator` 은 실제로 순회가 이루어지는 방식을 추상화합니다.

이처럼 순회 책임을 분리하면 결합도를 낮추고, 컬렉션의 내부 구조를 감추면서, 항상 동일한 방식으로 안전하게 순회할 수 있습니다.

1. 동일한 코드 패턴을 다양한 컬렉션에 대해 적용할 수 있으므로 코드를 작성하고 이해하기 쉬워집니다. ⇒ **생산성 및 가독성 개선**
2. 내부 구조가 외부에 노출되지 않으므로 데이터를 보호하고 안정성을 확보할 수 있습니다. ⇒ **캡슐화에 따른 이점 활용**
3. 내부 구조가 변하더라도 외부 순회 코드를 변경할 필요가 없으며, 다양한 순회 방식을 동일한 컬렉션에 적용해 볼 수 있다. ⇒ **유연성 및 확장성**

<br>
