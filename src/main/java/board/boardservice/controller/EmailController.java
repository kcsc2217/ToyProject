package board.boardservice.controller;

import board.boardservice.domain.dto.member.EmailDto;
import board.boardservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class EmailController {

   private EmailService emailService;

   @GetMapping("/findId")
    public String findEmail(Model model){
       model.addAttribute("email", new EmailDto());

       return "members/email";

   }

}
