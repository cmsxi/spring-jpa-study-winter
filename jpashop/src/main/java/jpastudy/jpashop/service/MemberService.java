package jpastudy.jpashop.service;

import jpastudy.jpashop.domain.Member;
import jpastudy.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 스프링이 제공하는 트랜젝션 쓰는게 쓸 수 있는게 많아서 사용 권장
// @RequiredArgsConstructor: @AllArgsConstructor와 비슷하지만 final 있는 필드로만 생성자 만들어 줌
// JPA 동작은 트랜젠션 안에서 동작되어야됨(데이터 변경 같은거)
public class MemberService {

    // 1. 필드로 바로 주입
//    @Autowired
//    private MemberRepository memberRepository;
//    // 많이 사용되지만 단점이 많음. 바꿀 수가 없다.  필드에 private니까.

    // 2. setter injection
//    private MemberRepository memberRepository;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//    // setter injection. spring이 바로 주입하는 것이 아님.
//    // test 코드에서 mock같은 걸 직접 주입할 수 있음
//    // 단점: (치명적) 한번 runtime에 누군가가 이걸 바꿀 수 있음. 애플리케이션 동작을 잘하고 있을 때는 바꿀 일이 없다.

    // 3. 생성자 injection
    //@AllArgsConstructor 로 (바깥에 적어야함) 만들어줄 수도 있음
    private final MemberRepository memberRepository; // 변경할 일이 없으므로 final로 설정! 컴파일 시점에 체크해줄 수 있으므로

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    // 생성자 injection에는 생성되는 시점에 조립이 끝나버리므로 중간에 set할 걱정은 없다.
    // 테스트 케이스 작성할 때도 직접 MOCK이든 뭐든 해줄 수 있다.
    // 생성자가 하나만 있으면 @Autowired 어노테이션이 없어도 알아서 주입 해줌

    //핵심 기능 : 회원 가입, 회원 전체 조회
    // 회원 가입
    @Transactional // 따로 설정한거는 따로 설정한게 우선권을 가짐 기본적으로는 readOnly가 false
    public Long join(Member member) {
        // 중복 이름을 지닌 회원을 없애는 로직 지정
        validateDuplicateMember(member); // 중복 회원 검증. 예외 처리?
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    // 정말 동시에 회원 A 2명이 가입을 진행하게 되면 해당 로직으로 안걸릴 수 있음.
    // 멀티스레드 상황을 고려하면 해당 로직으로도 부족할 수 있음. 따라서 실무에서는 데이터베이스에 unique 제약조건 등을 고려해야함


    // 회원 전체 조회
    //@Transactional(readOnly = true) : JPA가 조회하는 옵션에서 성능을 최적화 함. 데이터 변경에는 쓰면 안됨
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
