package Hello.Hellospring.repository;

import Hello.Hellospring.Repository.MemberRepository;
import Hello.Hellospring.Repository.MemoryMemberRepository;
import Hello.Hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { //method 하나가 실행 종료될 때마다 실행되는 method!!(call-back method)
        repository.clearStore(); //테스트 종료마다 자료를 비워줌! - method 간 의존관계를 없애기 위해!
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //Optional에서 꺼낼 때 get() 사용.. 문제 소지 있지만 test 에서는 상관 없음
//        System.out.println(result);
//        System.out.println(result.getName());
//        System.out.println(member);
//        System.out.println(member.getName());

        Assertions.assertEquals(member, result);//junit안에 있는 method
//        assertThat(member).isEqualTo(result);
//        System.out.println("result = " + (result == member));
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring2").get();

        assertThat(result).isEqualTo(member2);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
