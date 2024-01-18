package jpastudy.jpashop.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
// 테이블 명을 설정안하면 클래스 명이 테이블 명이 되는데,
// 예약어인 order by 때문에 관례적으로 orders라 이름을 설정해준다
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //Order와 Member는 다대일 관계.
    @JoinColumn(name = "member_id")
    //foreign key가 member_id
    private Member member;

    // JPQL select o From order o; -> SQL select * from order => n+1(order)  문제


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    private LocalDateTime orderDate; // 주문시간
    // 자바 8 이후로는 따로 어노테이션 없이 hibernate가 알아서 시간 구해줌
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 Enum 타입

    // ==연관 관계 매서드==// 주도권이 있는 쪽에 두면 됨. 양쪽 세팅을 원자적으로 할 수 있게 묶어버리는 것
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

//    public static void main(String[] args){
//        Member member = new Member();
//        Order order = new Order();
//
//        member.getOrders().add(order);    // 위에서 원자적으로 묶어버렸으므로 해당 line 없어도 됨
//        order.setMember(member);

//    }

}
