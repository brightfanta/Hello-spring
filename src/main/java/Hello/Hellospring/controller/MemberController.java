package Hello.Hellospring.controller;

import Hello.Hellospring.domain.Member;
import Hello.Hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLOutput;
import java.util.List;

@Controller

public class MemberController {

    private final MemberService memberService; // 생성자를 통해서 memberService가 MemberController에 주입된다.
    //필드주입도 있는데, 비추다. - 중간에 변경이 불가능하다.
    //세터 주입, setMemberService가 public으로 노출이 되어야 함. 굳이 그럴 필요가 없는데. 최초 세팅 후 변경필요 없음

    @Autowired
    public MemberController(MemberService memberService) { //MemberService는 컨트롤러에 등록되어 있지 않아 스프링에서 확인할 수 없다
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); // member를 전체 대상으로 불러올 수 있다.
        model.addAttribute("members",members); // 기억 나시죠? 기억 날 게 없는데요..??? ㅋㅋㅋ
        return "members/memberList";
    }
}
