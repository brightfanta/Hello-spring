package Hello.Hellospring.Repository;

import Hello.Hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByNmae(String name);
    List<Member> findAll();
}