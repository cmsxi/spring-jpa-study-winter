package jpastudy.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    // ORDINAL : 1,2,3,4, 숫자로 들어감. -> 중간에 다른 상태가 생기면 조회 안됨
    // STRING : 중간에 다른 값 생겨도 밀릴 걱정 X, 몇 글자 아끼지 말고 오류 방지하기 -> 웬만하면 STRING으로 사용하자
    private DeliveryStatus status; // enum타입, READY, COMP
}
