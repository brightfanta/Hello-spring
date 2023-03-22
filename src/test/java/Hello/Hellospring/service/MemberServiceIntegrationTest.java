package Hello.Hellospring.service;

import Hello.Hellospring.Repository.MemberRepository;
import Hello.Hellospring.Repository.MemoryMemberRepository;
import Hello.Hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // Test 시, rollback 기능을 사용하기 위해 추가하는 annotation! each annotationd이 들어간 method 별도 작성 필요 없음
// DB는 Transaction이라는 개념이 있음. 기본적으로 insertquery후 commit을 해야 DB에 반영됨 원래는. AutoCommmit모드이거sk
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

//    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // 두개를 쓸 이우갸 없지 new로 다른 인스턴스를 만든다는 것 부담됨.
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {

        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
        //given


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
