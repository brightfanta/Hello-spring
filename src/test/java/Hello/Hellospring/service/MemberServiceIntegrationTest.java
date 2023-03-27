package Hello.Hellospring.service;

import Hello.Hellospring.Repository.MemberRepository;
import Hello.Hellospring.Repository.MemoryMemberRepository;
import Hello.Hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@RunWith(SpringRunner.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(properties = {"spring.config.name=myapp-test-h2","myapp.trx.datasource.url=jdbc:h2:mem:trxServiceStatus"})
@Transactional // Test 시, rollback 기능을 사용하기 위해 추가하는 annotation! each annotationd이 들어간 method 별도 작성 필요 없음
// DB는 Transaction이라는 개념이 있음. 기본적으로 insertquery후 commit을 해야 DB에 반영됨 원래는. AutoCommmit모드이거sk
class MemberServiceIntegrationTest {

//    @BeforeEach
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository); //Dependency Injection(=D.I.)
//    } // TEST 실행 시마다 독립 실행된다.

    @Autowired MemberService memberService;

//    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // 두개를 쓸 이우갸 없지 new로 다른 인스턴스를 만든다는 것 부담됨.
    @Autowired MemberRepository memberRepository;


    @Test
    @Commit
    void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("hwanhee's wife");
        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception{

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
