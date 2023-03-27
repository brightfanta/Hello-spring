package Hello.Hellospring.service;

import Hello.Hellospring.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import Hello.Hellospring.service.MemberService;



@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired//생성자 1개일 경우, 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // injection 받음, SpringDataJpa가 구현체를 만들어오는 과정이 등록이 됨.

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); // new (구현체) 가능, new (인터페이스) 불가!
//        return new JdbcTemplateMemberRepository(dataSource); //config만 손댐
//        return new JpaMemberRepository(em);
//    }
}
