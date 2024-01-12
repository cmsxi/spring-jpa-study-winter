# spring-jpa-study
Spring JPA 공부

실전 스프링부트와 JPA 활용 인프런 강의 기반

#### Dependencies
###### Thymeleaf: 웹, 앱 등의 환경에서 작용할 수 있는 자바 탬플릿 엔진
- natural, 마크업
(https://www.thymeleaf.org/)

###### H2: 간단하게 DB 내장에서 실행할때 사용, MySQL등보다 간단함.
- 웹 콘솔 환경을 제공함
- http://(개인 ip 주소):8082/login.jsp?jsessionid=a5872ba1f18e3c46b94f3ae605220c0a
- http://localhost:8082/login.jsp?jsessionid=a5872ba1f18e3c46b94f3ae605220c0a
2번째 처럼 localhost로 바꿔주기
- JDBC URL: jdbc:h2:~/jpashop(처음 파일 설정할때만. C:\Users\UserId\에 .mv.db파일 존재하는지 확인)
- 이후로는 네트워크 모드?로 jdbc:h2:tcp://localhost/~/jpashop
- 웹콘솔 나가면 db안됨

###### Lombok: 보일러플레이트 코드 줄여줌

##### Spring Boot에서 HTML

resources/templates 에 있는 html문서
즉,  "resources:templates/ +{ViewName}+ .html"로 경로를 자동으로 설정하여 html 파일 매핑해줌.

설정 바꾸려면 application.yml에서 prefix, surfix 수정해주면 됨.



#### 조금 더 공부할 것
##### 랜더링?
공부해서 설명 적기
##### 라이브러리중 devtools 조금 더 공부하기

##### 엔티티 매니저 - 탭에 쓴 거 옮겨서 정리하기

##### 트랜젝션 어노테이션도 공부하기


#### 다시 정리
##### 실행 실습 중 테스트 코드 
Test code 생성 단축키 :  ctrl + shift + T
https://prayme.tistory.com/17

이외 라이브 탬플릿에서 단축키 설정 가능
tdd + tab
```
@Test
public void $Name$ throws Exception {
    // given
    $END$  // 자동 완성 후 커서 위치
    // when

    // then
}
```


##### application.yml에서
```
show_sql: true # 로그가  system out상에 나오게 함.

logging.level:  
  org.hibernate.SQL: debug # logger상에 로그가 나오게 함...
  org.hibernate.orm.jdbc.bind: trace
```
- https://github.com/gavlyukovskiy/spring-boot-data-source-decorator 
	- 데이터베이스 connection을 래핑해서 SQL statement를 이해해서 로그 출력해주는 라이브러리 중 하나 
  - 운영 단계에서는 성능을 저하시킬 수 있으므로, 필요한 경우에만 사용

##### cmd상에서 jar파일 확인하기. 윈도우 기준 (jar 파일의 경우 배포같은 거 할 때 활용)
```
// 실행하고자하는 파일로 경로 이동
gradlew clean build

cd build

cd libs

java -jar jpashop-0.0.1-SNAPSHOT.jar //실행시키면 인텔리제이에서가 아니라 자체적으로 자바 파일 실행됨

```


