package jpastudy.jpashop.domain;

import jakarta.persistence.*;
import jpastudy.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"), // 중간 테이블에 있는 category id
            inverseJoinColumns = @JoinColumn(name = "item_id")) // 중간 테이블의 item id
    // 중간 테이블 mapping 관계형 DB는 collection 관계를 양쪽에 가질 수 없으므로,
    // 일대다 다대일 중간 테이블이 있어야 된다.
    // 등록 날짜 등 추가적인 필드를 더할 수 없으므로 실무에서는 사용하지 않는다.
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // == 연관관계 편의 메서드 ==//
    public  void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
