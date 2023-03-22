package Hello.Hellospring.service;

import Hello.Hellospring.Repository.JdbcMemberRepository;
import Hello.Hellospring.Repository.JdbcTemplateMemberRepository;
import Hello.Hellospring.Repository.MemberRepository;
import Hello.Hellospring.Repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); // new (구현체) 가능, new (인터페이스) 불가!
        return new JdbcTemplateMemberRepository(dataSource); //config만 손댐
    }
}
