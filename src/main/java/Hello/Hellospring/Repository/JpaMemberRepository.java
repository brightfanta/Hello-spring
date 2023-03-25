package Hello.Hellospring.Repository;

import Hello.Hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; //jpa는 EntityManager로 모든 것이 동작함 build.gradle에서 data-jpa에 의해 springboot가 생성한
    //EntityManager를 injection받으면 된다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);//persist: 영속하다. 영구 저장하다. 이렇게 하면 jpa가 insert query 만들어서 집어넣 setId 까지 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // jpql | 기존 table 대상으로 query를 날리는 것이
                // 아닌, 객체를 대상으로 query를 날린다. sql로 변역이 된다.
                .getResultList();
    }
}
