package Hello.Hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 주소! GetMapping 이 선순위, index.html 후순위
    public String home() {
        return "home";
    }
}
