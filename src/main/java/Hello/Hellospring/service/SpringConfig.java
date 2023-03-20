package Hello.Hellospring.service;

import Hello.Hellospring.Repository.MemberRepository;
import Hello.Hellospring.Repository.MemoryMemberRepository;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // new (구현체) 가능, new (인터페이스) 불가!
    }
}
