package jpastudy.jpashop.service;

import jakarta.persistence.EntityManager;
import jpastudy.jpashop.domain.Member;
import jpastudy.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class) // 단위테스트를 만드는게 아니라 JPA가 실제로 DB까지 연동되는 것을  보이기 위해 메모리 모드로 엮으려고 사용. Junit 스프링과 함께 하겠다!라는 의미
// 완전히 Spring test 통합
@SpringBootTest // 스프링부트를 띄운 상태로 하기 위해서. 이거 없으면 Autowired 실패함
@Transactional // rollback을 위해서, 같은 트랜젝션 안에서 같은 영속성 컨택스트를 지니므로
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    //rollback이지만 DB에 쿼리 날리는게 보고 싶을 경우.
    @Autowired
    EntityManager em;

    // 회원가입, 가입이 정상적으로 되었는지
    @Test
//    @Rollback(false)
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        em.flush(); // 영속성 컨텍스트에 있는 변경을 DB에 반영하게 됨 -> insert문
        assertEquals(member, memberRepository.findOne(savedId));
    }

    // 중복 회원 예외
        @Test(expected = IllegalStateException.class) // 아래 코드 처럼 다소 지저분해보일 수 있는 내용을 해당 내용으로 간단히 작성할 수 있다.
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        fail("예외가 발생해야 한다.");
    }


//    @Test
//    public void 중복_회원_예외() throws Exception {
//        // given
//        Member member1 = new Member();
//        member1.setName("kim");
//
//        Member member2 = new Member();
//        member2.setName("kim");
//
//        // when
//        memberService.join(member1);
//        try {
//            memberService.join(member2); // 예외가 발생해야 함
//        } catch (IllegalStateException e) {
//            return;
//        }
//
//        // then
//        fail("예외가 발생해야 한다."); // Assert의 fail, 코드가 여기까지 도달하면 문제가 있는것. fail
//    }

}