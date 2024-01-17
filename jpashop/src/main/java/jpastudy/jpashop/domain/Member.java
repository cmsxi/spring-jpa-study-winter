package jpastudy.jpashop.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    // Order 테이블에 있는 member field에 의해서 mapped 됐음을 명시
    // 직접 매핑하는 것이 아니라 mapped된 것을 보여주는 거울과 같은 역할

    private List<Order> orders = new ArrayList<>();
    // 클래스에 setter를 사용하지 않으면 경고 표시?가 생김
    // (아마도) 앞의 변수의 경우 한번 설정되면 변경되는 경우가 적음
    // 그러나 리스트의 경우 값이 변경될 가능성이 크므로 값을 다시 설정해주어야하는 경우가 많다
    // 따라서 스프링에서 자동으로 경고해주는 듯

}
