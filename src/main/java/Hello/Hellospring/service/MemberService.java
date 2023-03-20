package Hello.Hellospring.service;

import Hello.Hellospring.Repository.MemberRepository;
import Hello.Hellospring.Repository.MemoryMemberRepository;
import Hello.Hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //MemberRepository 외부에서 넣어주도록 설정
    }

    /**
     * 회원가입
     */


    public Long join(Member member) {
        //동명 중복회원 가입 불가
//        Optional<Member> result = memberRepository.findByName(member.getId());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.")
//        });

        validateDuplicateMember(member); // 중복회원 검증


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll(); // 반환 타입(List) 확인하고 return 까지 입력
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);

    }
} //
