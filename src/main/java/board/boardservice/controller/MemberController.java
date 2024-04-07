package board.boardservice.controller;

import board.boardservice.controller.form.MemberForm;
import board.boardservice.controller.form.MemberLoginForm;
import board.boardservice.domain.Member;
import board.boardservice.domain.dto.MemberDTO;
import board.boardservice.service.MemberService;
import board.boardservice.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 폼
    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());

        return "members/create";

    }

    // 회원가입

    @PostMapping("/new")
    public String create(@Valid MemberForm memberForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("에러 발생");
            return "members/create";
        }
        Member member = Member.createMember(memberForm);

        memberService.join(member);

        return "redirect:/members/login";


    }


    //로그인 폼
    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("memberLoginForm", new MemberLoginForm());

        return "members/login";
    }
    //로그인 구현 완료

    @PostMapping("/login")
    public String login(@Valid MemberLoginForm memberLoginForm, BindingResult bindingResult, HttpServletRequest request,
                        @RequestParam(defaultValue = "/") String redirectURL){

        if(bindingResult.hasErrors()){
            log.info("에러 발생");
            return "members/login";
        }

        Member loginMember = memberService.login(memberLoginForm.getUsername(), memberLoginForm.getPassword());

        log.info("login? {}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/login";
        }

        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);


        return "redirect:" + redirectURL;

    }

    //로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        return "redirect:/";
    }



    //   회원 정보수정
    @GetMapping("/update")

  public String update(@ModelAttribute MemberDTO memberDTO){
        return "members/update";
    }






}
