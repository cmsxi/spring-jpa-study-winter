package jpastudy.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository //컴포넌트 스캔의 대상이 되어 자동으로 스프링 빈에 등록
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
        // member가 아닌 id를 반환하는 이유: command와 query 분리. 저장을 하고 나면 side effect를 일으킬 수 있는 커맨드성이므로 return값 지양. 조회 가능한 id 정도만 반환
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

}
