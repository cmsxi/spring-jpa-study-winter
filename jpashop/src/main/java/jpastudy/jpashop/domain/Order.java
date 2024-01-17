package jpastudy.jpashop.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders" )
// 테이블 명을 설정안하면 클래스 명이 테이블 명이 되는데,
// 예약어인 order by 때문에 관례적으로 orders라 이름을 설정해준다
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    //Order와 Member는 다대일 관계.
    @JoinColumn(name = "member_id")
    //foreign key가 member_id
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    private LocalDateTime orderDate; // 주문시간
    // 자바 8 이후로는 따로 어노테이션 없이 hibernate가 알아서 시간 구해줌
    @Enumerated(EnumType.STRING)
    private OrderStatus status ; // 주문상태 Enum 타입

}
