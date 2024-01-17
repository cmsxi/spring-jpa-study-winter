package jpastudy.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
// 임베디드 타입: 복합값 타입
// ex' 집주소 ~ 도시, 거리, 주소 번지
// 객체 지향적으로 응집력을 높일 수 있다는 장점을 지님
// @Embeddable: 값 타입 정의하는 곳에 표시
// @Embedded: 값 타입 사용하는 곳에 표시
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
