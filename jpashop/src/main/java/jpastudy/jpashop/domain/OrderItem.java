package jpastudy.jpashop.domain;

import jakarta.persistence.*;
import jpastudy.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    // 단방향이므로 Item 클래스에서 OneToMany 안해줘도 됨
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 당시 가격, 상품 가격은 바뀔 수 있으므로
    private int count; // 주문 수량
}
