# MVC 패턴의 정의 및 의의

애플리케이션 개발에 사용하는 디자인패턴의 일종으로 ***관심사의 분리***를 통해서 애플리케이션의 설계를 단순하게 유지하고 유지보수성을 높이기 위해 사용합니다.

MVC 패턴은 애플리케이션을 상호연결된 세 부분으로 구분합니다.

## 1. MVC 패턴의 구성요소

### 1.1. Model

- 애플리케이션의 데이터와 비즈니스 로직을 관리합니다.
- MVC 패턴에서의 Model은 DB 연동을 통한 데이터 저장 및 조회도 포함한다고 봐야할 것 같습니다.
  - (그렇게 보지 않을 수도 있을 것 같습니다. 확실하지 않으니 의견 추가 부탁드립니다.)
- 이런 관점에서 Model 은 두 계층으로 나눠볼 수 있습니다.
  1. 도메인 모델 계층
  2. 데이터 액세스 계층

### 1.2. View

- 정보 표현을 담당합니다.

### 1.3. Controller

- 사용자의 요청을 Model 과 View 를 이용해서 처리합니다.

## 2. 요소 간 관계
![Image](https://github.com/user-attachments/assets/69e813c3-50c3-46af-a675-fbb1a65d6de4)

위 그림은 각 요소가 어떻게 상호작용하는 지 나타냅니다.
- 사용자는 컨트롤러를 통해 특정 자원이나 기능 수행을 요청합니다.
- 컨트롤러는 요청에 따라 모델의 상태를 변경하거나, 모델로부터 데이터를 가져와서 그 정보를 뷰에 전달합니다. (위 그림의 Generate Output)
- ***또는*** 옵저버 패턴 등을 통해 Model(Subject)의 변경을 View(Observer) 에 알리는 방식을 사용할 수도 있습니다. (위 그림의 Data Change)
- 뷰는 전달받은 정보를 적절한 형태로 사용자(또는 다른 시스템)에 보여줍니다.
- 사용자의 요청이 컨트롤러를 통해 수신되어 Model 과 View 를 통해 적절한 형태로 사용자에게 다시 반환되는 순환구조를 가집니다.

MVC 패턴의 효과를 극대화하기 위해서는 다음의 권장 사항을 준수하는 것이 좋습니다.

### 2.1. Model

- UI 와 무관해야 합니다.
- Model 은 View 와 Controller 에 관한 어떠한 로직도 포함해서는 안됩니다.

```java
public class Student {
    private String name;
    private int score;

    // 생성자 및 getter
    // 기타 비즈니스 로직
    // ...
}
```

```java
public Interface StudentRepository ... {
    
    Student findById(long id);
    ...
}
```

### 2.2. View

- 오직 데이터를 제시하는 것과 관련된 로직만 포함해야 합니다.
- 비즈니스 로직과 데이터 접근 로직은 포함해선 안됩니다.

```java
public class StudentView {
    // 학생 정보 뷰는 학생의 이름과 점수를 보여주지만,
    // 그 정보가 어떤 Model 에 속하는지, Controller 의 어떤 요청에 의한 것인지는 알아서는 안 된다.
    public void printStudentDetails(String name, int score) {
        System.out.println("학생 이름: " + name);
        System.out.println("학생 점수: " + score);
    }
}
```

뷰는 위 예시에서처럼 콘솔을 통해 정보를 제시할 수도 있지만,
- GUI 나 웹 페이지와 같은 형태로 제시될 수도 있고
- 객체를 JSON/XML 형식으로 직렬화하는 것에 국한될 수도 있습니다. 즉, API 서버에서는 데이터를 JSON 과 같은 형식으로 표현하며, 이 역할도 View 의 역할로 봐도 무방합니다.

### 2.3. Controller

- 컨트롤러는 얇아야 합니다. 오직 사용자의 요청을 Model 과 View 에 위임하는 동작만을 수행해야 합니다.

```java
// Controller는 Model과 View를 연결하고 프로그램의 흐름을 제어합니다.
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentView view;

    public void getStudentDetails() {
        // 학생 Model 의 정보를 학생 정보 View 에 전달한다.
        Student student = studentRepository.findById();
        view.printStudentDetails(student.getName(), student.getScore());
    }
}
```

## 3. 관련 패턴

다양한 관련 패턴에 대한 GPT 정리를 긁어 왔습니다. 제가 이 부분에 대해 잘 모르는 상태라 잘못된 내용이 있을 수 있습니다.
이런게 있다 정도만 짚고 넘어가면 될 것 같습니다.

📊 MVC와 관련 패턴 비교
| 패턴                                         | 구조적 특징                                                                          | 장점                              | 단점                                 | 사용 예시                           |
| ------------------------------------------ | ------------------------------------------------------------------------------- | ------------------------------- | ---------------------------------- | ------------------------------- |
| **MVC** (Model–View–Controller)            | Model: 데이터/비즈니스 로직<br>View: UI 표현<br>Controller: 입력 처리·흐름 제어                    | 관심사 분리, 테스트 용이                  | 대규모 프로젝트에서 복잡성↑, Controller 비대화 위험 | Spring MVC, Django (MTV 변형)     |
| **MVP** (Model–View–Presenter)             | Model: 데이터/로직<br>View: UI (수동적, 이벤트만 위임)<br>Presenter: View와 Model 연결, UI 갱신 책임 | View가 단순, 테스트 쉬움, Android 전통 패턴 | Presenter가 비대해질 수 있음               | 초기 Android 앱, WinForms          |
| **MVVM** (Model–View–ViewModel)            | Model: 데이터<br>View: UI (바인딩)<br>ViewModel: View 상태를 데이터로 표현, 양방향 데이터 바인딩        | UI와 로직의 완전한 분리, 단순한 화면 개발 용이    | 복잡한 양방향 바인딩 → 디버깅 어려움              | WPF, Angular, Vue, React + MobX |
| **HMVC** (Hierarchical MVC)                | MVC를 모듈 단위로 계층화<br>각 모듈이 자체 MVC 구조를 가짐                                          | 대형 프로젝트 확장성↑, 모듈 재사용성↑          | 구조가 더 복잡, 설계 비용↑                   | CodeIgniter HMVC 확장             |
| **PAC** (Presentation–Abstraction–Control) | Presentation: UI<br>Abstraction: 데이터/로직<br>Control: 모듈 간 상호작용 조정                | 병렬 개발 유리, 모듈 간 독립성↑             | Control 복잡도 높음                     | 임베디드 UI, 일부 연구용 아키텍처            |
| **MVU** (Model–View–Update, Elm 아키텍처)      | Model: 상태<br>View: 상태 → UI 렌더링<br>Update: 이벤트 처리 후 새 상태 반환                      | 불변성 강조, 상태 관리 단순화, 예측 가능한 UI    | 러닝 커브↑, boilerplate 코드 많음          | Elm, React+Redux 패턴             |
