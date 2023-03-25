package Hello.Hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // JPA가 관리한다.
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //P.K. mapping | IDENTITY DB에서 알아서 생성해줌
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
