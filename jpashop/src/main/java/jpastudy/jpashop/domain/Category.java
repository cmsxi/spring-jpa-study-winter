package jpastudy.jpashop.domain;

import jakarta.persistence.*;
import jpastudy.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;


    @ManyToMany
    @JoinTable(name = "category_item")
    private List<Item> items = new ArrayList<>();

}
