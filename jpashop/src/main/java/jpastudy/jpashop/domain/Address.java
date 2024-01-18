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

    protected Address() {
    }
    // jpa가 생성할 때 reflection, proxy 기술을 써야하는 경우가 많은데 기본 생성자가 없으면 그런 걸 못함
    // 따라서 사람들이 쉽게 호출할 수 없도록 public이 아닌 protected로 기본 생성자 만들어 줌

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
