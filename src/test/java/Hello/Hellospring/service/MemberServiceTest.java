package Hello.Hellospring.service;

import Hello.Hellospring.Repository.MemoryMemberRepository;
import Hello.Hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

//    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // 두개를 쓸 이우갸 없지 new로 다른 인스턴스를 만든다는 것 부담됨.
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //Dependency Injection(=D.I.)
    } // TEST 실행 시마다 독립 실행된다.

    @AfterEach
    public void afterEach() { //method 하나가 실행 종료될 때마다 실행되는 method!!(call-back method)
        memberRepository.clearStore(); //테스트 종료마다 자료를 비워줌! - method 간 의존관계를 없애기 위해!
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("Spring");
        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            //정상성공
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

//        memberService.join(member1);
//        memberService.join(member2);

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}