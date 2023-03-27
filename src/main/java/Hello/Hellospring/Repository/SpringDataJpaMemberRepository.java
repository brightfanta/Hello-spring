package Hello.Hellospring.Repository;

import Hello.Hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {//interface가 interface를 받을 떄는 implements말고
    // extends 라고 함 | ID는 Entity에서 식별자 PK, 이 강의에서의 경우 Long(Member의 ID type이 Long 임)

    @Override
    Optional<Member> findByName(String name); // 구현할 게 없습니다. 다 만든 겁니다.
}

//SpringDataJpa가 자동으로 구현체를 만듦
