## 4. Comments

- 잘 적힌 주석만큼 도움이 되는 것이 없다. 반대로, 잘 적히지 않은 주석만큼 우리를 혼란스럽게 만드는 것이 없기도 하다. 그러나, 만약 우리의 프로그래밍 언어가 충분히 표현력 있다면 주석은 사실 필요가 없다.

### 4-1. Comments Do Not Make Up for Bad Code

- 주석이 거의 없으면서도 명확하고 표현력이 있는 코드는 주석이 많은 코드보다 훨씬 우월하다. 주석을 작성하는데 시간을 너무 쓰는 것보다, 코드를 정리하는데 시간을 써보는 것도 좋다.
- 아래는 위에서 말하는 내용의 예시이다. 둘을 비교해보자.

```java
// Check to see if the employee is eligible for full benefits
if ((employee.flags & HOURLY_FLAG) && (employee.age > 65))
```

```java
if (employee.isEligibleForFullBenefits())
```

### 4-1. 좋은 주석

- 어떤 주석은 필수적이거나, 도움이 된다.
1. 법률적인 주석: 필수적인 주석의 예시이다. 가령, 저작권이나 저작자를 적는 것은 거의 필수적인 주석이라고 볼 수 있다.
2. 정보를 주는 주석: 기본적인 정보를 주는 주석은 괜찮다. 예를 들어, 아래와 같은 코드는 이해에 도움이 된다. 그러나, 가장 좋은 것은 이름을 바꿔서 주석이 없이도 이해할 수 있게 하는 것이다.

```java
// Returns an instance of the Responder being tested.
protected abstract Responder responderInstance();
```

1. 의도 설명: 어떤 경우에, 코드의 의도를 담은 주석은 단순히 정보를 주는 것에 벗어나서, 다음에 그 코드에 관련하여 수행할 작업에서의 의사 결정에 도움이 되기도 한다.

```java
public int compareTo(Object o) {
    if(o instanceof WikiPagePath) {
      WikiPagePath p = (WikiPagePath) o;
      String compressedName = StringUtil.join(names, "");
      String compressedArgumentName = StringUtil.join(p.names, "");
      return compressedName.compareTo(compressedArgumentName);
    }
    return 1; // we are greater because we are the right type.
}
```

1. 명확성을 위한 주석: 때때로 모호한 인수 또는 반환 값의 의미를 알 수 있게 주석을 다는 것이 좋다. 보통은 인수 또는 반환 값을 그 자체로 명확하게 만드는 방법을 찾는 것이 좋지만, 코드가 표준 라이브러리의 일부이거나 하는 식으로, 내가 변경할 수 없는 코드일 때 주석을 달아서 표현하는 것이 좋다.
2. 경고: 다른 프로그래머에게 어떠한 내용을 경고하기 위한 주석은 좋은 주석이다.
3. TODO: `//TODO`  문구를 포함하여, 앞으로 해야할 일을 적는 주석은 좋은 주석이다. TODO는 왜 이 함수가 완성되지 않은 구현을 갖는지를 설명해주며, 미래에 어떠한 역할을 최종적으로 해야할 지도 알려준다.
4. 강조 주석: 중요한 부분을 강조하는 주석은 좋은 주석이다. 예를 들어, 별거 아닌 것처럼 보이지만 치명적인 조작을 가하는 코드에는 주석으로 이러한 행동이 중요한 행동임을 명시해주어야 한다.
    - 강조는 프로그래머가 어떤 이유에서인지 현재로서는 작업할 수 없는 것들을 모은 것이다. 이는 사용하지 않는 기능을 삭제하거나, 문제를 해결하라는 것이 될 수도 있다. 또한, 변수의 더 나은 이름을 생각해달라는 요청이 될 수도 있다.
5. Javadocs: 잘 설명된 API만큼 유용한 것이 없다. Javadoc이 가장 대표적인 예시이다.

### 4-1. 나쁜 주석

- 아쉽게도, 대부분의 주석은 이러한 경우이다. 일반적으로, 주석은 부실한 코드에 대한 변명, 혹은 불충분한 기능에 대한 정당화이다.
1. 웅얼거림: 주석을 너무 막 달면 안된다. 일단, 주석을 달기로 했다면, 이게 최선의 주석인지 확인하는 데 일정 이상의 시간을 할애해야 한다.
2. 중복되는 주석: 중복되는 주석은 정말로 쓸모가 없다. 이 주석은 코드를 정당화하지도 않으며, 읽기도 어렵다. 이러한 주석은 독자가 코드에 대한 이해를 어렵게 만든다.
3. 오해를 사는 주석: 프로그래머는 가령, 좋은 표현만을 사용했음에도 불구하고, 엄밀하지 않은 주석을 작성한다. **[이 부분 더 작성하기]**
4. 주석의 의무화: 모든 함수가 javadoc 같은 주석을 가져야 한다는 건 멍청한 생각이다. 이런 주석은 코드를 어수선하게 만들고, 일반적으로 혼란을 불러일으킨다. 아래는 이러한 코드의 예시이다.

```java
/**
*
* @param title The title of the CD
* @param author The author of the CD
* @param tracks The number of tracks on the CD
* @param durationInMinutes The duration of the CD in minutes
*/
public void addCD(String title, String author, int tracks, int durationInMinutes) { /* OMIT INNERCODE */ }
```

1. 일기장: 때때로, 사람들은 코드를 수정할 때마다 주석을 다는 경우가 있다. 현대에는 git과 같은 버전 관리 시스템이 갖추어져 있기 때문에, 이런 건 필요가 없다.
2. noise comment: 아래와 같은 주석은 아무런 정보를 주지 않는다. javadoc은 사실 이런 카테고리에 속한다. 많은 경우에, 그들은 중복되는 주석을 갖는다.

```java
/**
* Default constructor.
*/
protected AnnualDateRule() { }

/** The day of the month. */
private int dayOfMonth;
```

1. 함수나, 변수명으로 대체할 수 있는 경우에는 주석을 사용하지 말아야 한다.
2. 위치 마킹: 아래와 같이, 위치를 구분하기 위한 주석은 코드를 어지럽게 만든다.

```java
// Actions //////////////////////////////////
```

1. 닫히는 중괄호: 중괄호를 닫는 위치에 주석을 넣는 것은 좋지 않다. 아래와 같은 예시를 보자. 우리는 이러한 코드들을 각각의 작은 함수들로 분리하여 처리할 수 있다. 이렇게 처리하는 것이 훨씬 깔끔하다.

```java
public class Main {
  public static void main(String[] args) {
    try {
      while ([REMOVE DETAILS]) {
	      [REMOVE DETAILS]
      } //while
      [REMOVE DETAILS]
    } // try
    catch (IOException e) {
      [REMOVE DETAILS]
    } //catch
  } //main
```

1.  작성자 이름 적기: 버전 관리 시스템이 이 문제를 대신 해결해줄 것이다.
2. 필요 없는 코드: 더 이상 필요 없는 코드는 주석 처리 하지 말고 그냥 지우자. 이것도 버전 관리 시스템이 관리해준다.
3. HTML 주석: HTML을 이용한 주석은 읽는 것이 가히 불가능하다.
4. 지역적이지 않은 정보: 주석을 단다면, 주석 근처에 그 주석이 설명하는 코드가 있는지 확인해야 한다. 주석에 시스템 전반에 걸친 내용을 소개하는 건 의미가 없다.
5. 과도한 정보: 과거의 논쟁거리나, 관련이 없는 설명을 상세하게 추가하지 않아야 한다.
6. 명확하지 않은 연결: 주석이랑 코드의 연결성이 명확해야 한다. 만약, 내가 주석을 다는데, 어려움이 있다면 그건 읽는 사람도 마찬가지이다.
7. 함수 헤더: 짧은 함수는 별다른 설명이 필요없다. 잘 정해진 이름 하나는 주석보다 낫다.
8. 비공개 코드에서의 javadocs: javadocs는 공개 API를 위한 것이다. 즉, 비공개 코드에서는 이러한 주석이 주의를 분산시킬 수 있다.

---

## 5. Formatting

- 코드 재정렬은 중요하다. 이것은 소통에 도움을 주며, 소통은 전문적인 개발자의 첫번째 덕목이다.

### 5-1. 수직 재정렬

- 이 개념은 코드에서 콘셉트를 분리하는 방법으로 구성되며, 아래는 이를 나타낸 예제이다. 이를 보면 알겠지만, 코드를 잘 재정렬하는 것은 정말로 중요하다.

```java
package fitnesse.wikitext.widgets;
import java.util.regex.*;
public class BoldWidget extends ParentWidget {
  public static final String REGEXP = "'''.+?'''";
  private static final Pattern pattern = Pattern.compile("'''(.+?)'''",
  Pattern.MULTILINE + Pattern.DOTALL);
  public BoldWidget(ParentWidget parent, String text) throws Exception {
    super(parent);
    Matcher match = pattern.matcher(text); match.find(); addChildWidgets(match.group(1));
  }
  public String render() throws Exception { StringBuffer html = new StringBuffer("<b>"); html.append(childHtml()).append("</b>"); return html.toString();
  }
}
```

### 5-1. Vertical Density

- 서로 밀접하게 관련된 코드 라인은 수직 방향으로 밀도가 높아야 한다. 아래는 그 예시를 보여준다. 2번째 예시가 더 눈에 보기 편하다.

```java
public class ReporterConfig {

	/**
	 * The class name of the reporter listener */
	private String m_className;

	/**
	 * The properties of the reporter listener */
	private List<Property> m_properties = new ArrayList<Property>();

	public void addProperty(Property property) { m_properties.add(property);
	}
```

```java
public class ReporterConfig {
  private String m_className;
  private List<Property> m_properties = new ArrayList<Property>();

  public void addProperty(Property property) {
    m_properties.add(property);
  }
}
```

### 5-1. Vertical Distance

- 변수는 가능한 한 사용되는 곳에 가깝게 선언해야 한다. 함수는 보통 짧기 때문에, 각 함수의 상단에 변수가 정의되어야 한다.
- 반면에, 인스턴스 변수는 클래스의 상단에 정의되어야 한다. 이러한 규칙은 누구나 선언을 보려면 어디로 이동해야 하는지 잘 알 수 있게 한다.
- 한 함수가 다른 함수를 호출하는 경우, 함수는 수직 방향으로 서로 가까워야 하며 가능하면 호출하는 함수가 더 위쪽에 있어야 한다. 이렇게 하면 프로그램에 자연스러운 흐름이 제공된다.

### 5-1. Vertical Ordering

- 보통 우리는 함수 호출의 종속성이 아래쪽 방향을 가리키면 좋은 코드라고 한다. 즉, 호출되는 함수는 호출을 수행하는 함수보다 아래에 있어야 한다. 이렇게 하면 코드 모듈에서 높은 수준에서 낮은 수준으로의 흐름이 만들어진다.

### 5-1. Horizontal Formatting

- 우리는 수평 공백을 이용해서 관련이 있는 것을 붙이고, 반면에 관련이 없는 것을 떨어뜨려놓는다. 변수 선언 및 할당에는 = 왼쪽과 오른쪽에 공백이 존재한다. 이는, 양변의 분리를 명확하게 보여준다.

```java
private void measureLine(String line) {
  lineCount++;
  int lineSize = line.length();
  totalChars += lineSize;
  lineWidthHistogram.addLine(lineSize, lineCount);
  recordWidestLine(lineSize);
}
```

### 5-1. Horizontal Alignment

```java
public class Example implements Base
{
  private   Socket      socket;
  private   inputStream input;
  protected long        requestProgress;

  public Expediter(Socket      s,
                   inputStream input) {
    this.socket =     s;
    this.input  =     input;
  }
}
```

- 현대의 언어에서는 위와 같은 정렬이 생각보다 쓸모가 있지는 않다. 이러한 정렬은 잘못된 것을 강조할 수 있다.

### 5-1. Indentation

- indentation은 계층 구조를 잘 보이게 하고, 잘 정의된 블럭을 쉽게 구분할 수 있게 하므로 중요하다.

### 5-1. 팀의 규칙

- 모든 프로그래머는 자신만의 코드 재정렬 규칙이 있다. 그러나, 팀이 함께 일한다면, 팀의 규칙을 따라야 한다.
- 팀의 프로그래머는 하나의 단일화된 재정렬 규칙을 만들고, 그것을 따라야 한다. 사람들은 소프트웨어가 균일한 스타일을 갖는 것을 원한다.

---

## 6. Objects and Data Structures

### 6-1. Data Abstraction

- 함수의 구현을 숨기는 것은, 추상화와 관련된 내용이다.
- 클래스는 단순히 getter, setter를 통해 변수를 받고 내보내는 존재가 아니라, 오히려 사용자가 그 구현을 모르더라도 데이터의 본질을 조작할 수 있도록 하는 추상적인 인터페이스를 노출시키는데 그 본질이 있다.

### 6-1. Data/Object Anti-Symmetry

- 객체는 추상화 뒤에 데이터를 숨기며, 메서드를 통해 데이터를 조작한다. 반면에, 데이터 구조는 데이터를 노출하지만 의미 있는 메서드를 가지고 있지는 않는다. 이러한 양면성은 객체와 데이터 구조 사이의 근본적인 이분법을 드러낸다.
- 데이터 구조를 사용하는 절차적 프로그래밍을 이용하면 기존 데이터 구조를 변경하지 않고도 새로운 기능을 추가할 수 있다. 반면에, 객체지향 프로그래밍은 기존 기능을 변경하지 않고도 새로운 클래스를 쉽게 추가할 수 있다.
- 때때로는 객체가 아니라 그냥 단순한 데이터 구조가 필요한 경우도 있다. 이러한 점을 명심하자.

### 6-1. The Law of Demeter

- 이 법칙은 굉장히 잘 알려진 휴리스틱으로, 모듈이 조작하는 물체의 내부를 알면 안 된다는 법칙이다.

### 6-1. Data Transfer Objects

- 데이터 구조는 전형적으로 공개 변수가 있고, 메서드는 없는 그런 클래스와 같은 형태를 띈다. 이를 데이터 전송 객체 혹은 DTO라고 하낟. DTO는 특히 데이터베이스와 통신하거나 소켓에서 메시지를 구문 분석하는 등에 이용되는 유용한 구조이다.

---

## 7. Error Handling

- 많은 코드는 에러 처리가 중요하다. 물론, 이것이 전부는 아니다. 에러 처리도 중요하지만 코드의 논리의 모호하게 만들면 그건 잘못된 것이다.

### 7-1. Use Exceptions Rather Than Return Codes

- 예전에는 exception이 없는 언어가 많았다. 이러한 언어에서는 오류를 처리하고 보고하는 기술이 제한적이었고, 오류 플래그를 설정하거나 확인할 수 있는 오류 코드를 반환하는 등의 방법을 이용하였다.

### 7-1. Write Your Try-Catch-Finally Statement First

- 어떤 면에서 try 블록은 트랜잭션과 비슷하다다. catch 블록은 try 안에서 무슨 일이 일어나든 프로그램을 일관된 상태로 유지한다. 이러한 이유로 예외를 발생시킬 수 있는 코드를 작성할 때는 처음부터 try-catch-finally 문을 사용하는 것이 좋은 습관이다. 이렇게 하면 try 안에서 실행되는 코드에서 무슨 일이 잘못되더라도 그 코드를 사용하는 사람이 무엇을 기대해야 하는지 명확히 정의할 수 있다.

### 7-1. Provide Context with Exceptions

- 각 예외는 오류의 원인과 위치를 파악할 수 있을 만큼의 충분한 정보를 제공해야 한다.
- 유용한 에러 메시지를 만들고 예외와 함께 전달하라. 실패한 작업과 실패 유형을 언급해야 한다. 앱에서 로그를 기록하는 경우, catch 블록에서 오류를 기록할 수 있도록 충분한 정보를 함께 전달해라.

### 7-1. Don't Return Null

- 메서드에서 null을 반환하고 싶어질 때는 예외를 던지거나 SPECIAL CASE 객체를 반환하는 방안을 고려하는 것이 좋다. 외부 API에서 null을 반환하는 메서드를 호출할 경우, 해당 메서드 자체를 예외를지거나 special case 객체를 반환하는 메서드로 감싸는 것을 고려해라.

### 7-1. Don't Pass Null

- 메서드에서 null을 반환하는 것은 좋지 않지만, 메서드에 null을 전달하는 것은 더 좋지 않다. API에서 null을 전달해야 하는 경우가 아니라면, 가능한 한 코드에서 null을 전달하는 것을 피해야한다.
