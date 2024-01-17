package jpastudy.jpashop.domain.Item;

import jakarta.persistence.*;
import jpastudy.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    // 구현체 상속할 것이므로 추상 클래스

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity; // 재고 수량

    @ManyToMany // 다대다는 실무에서 안쓴다

    private List<Category> categories = new ArrayList<>();
}
