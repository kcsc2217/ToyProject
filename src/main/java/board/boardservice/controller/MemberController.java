package board.boardservice.controller;

import board.boardservice.controller.form.MemberLoginForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("memberLoginForm", new MemberLoginForm());

        return "members/login";
    }




}
