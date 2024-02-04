package jpastudy.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpastudy.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 스프링 빈으로 등록. JPA 예외를 스프링 기반 예외로 등록
//컴포넌트 스캔의 대상이 되어 자동으로 스프링 빈에 등록
public class MemberRepository {

    @PersistenceContext // JPA 제공 표준 어노테이션. 스프링이 엔티티 매니저 만들어서 주입해줌
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }
    // jpa가 저장하는 로직
    // persist를 하면 영속성 컨텍스트에 member를 올리게 되는데, KEY는 id값이 됨. db PK랑 매핑한게 key가 됨

//    public Long save(Member member){
//        em.persist(member);
//        return member.getId();
//        // member가 아닌 id를 반환하는 이유: command와 query 분리. 저장을 하고 나면 side effect를 일으킬 수 있는 커맨드성이므로 return값 지양. 조회 가능한 id 정도만 반환
//    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }
    // jpa find하는 로직


    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // member를 리스트로 만들어 줌
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) // 파라미터 바인딩
                .getResultList();
    }

}
