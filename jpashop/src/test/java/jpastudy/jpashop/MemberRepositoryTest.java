package jpastudy.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class) // Junit에 스프링으로 test한다는 사실 알려줌. Junit 5 기준
@SpringBootTest // 스프링 부트 기반 테스트 코드
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // 말 그대로 해당 메서드가 작동하는 동안 트랜젝션을 보장해줌
    @Rollback(false) // 테스트 케이스 이므로 기본적으로 db에서 테스트 이전으로 rollback해줌. false하면 테스트한 데이터 db상에 반영
    public void testMember() {
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
        // 3번째 Assertions : 저장한 것(member)과 조회한 것(findMember)이 같을까?
        // 같은 영속성 컨텍스트를 가지므로 id값이 같으면 같은 엔티티로 식별
        // 따라서 select query없이 insert만 찍히는 걸 볼 수 있는데, 영속성 컨텍스트에 있으므로 1차 캐시에서 가져온 것
        System.out.println("findMember == member: " + (findMember==member));

    }
}