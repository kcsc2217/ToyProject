package board.boardservice.controller;

import board.boardservice.controller.form.PostForm;
import board.boardservice.domain.Member;
import board.boardservice.domain.Post;
import board.boardservice.service.MemberService;
import board.boardservice.service.PostService;
import board.boardservice.session.SessionConst;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;


    @GetMapping("/lists")
    public String lists(Model model) {
        List<Post> posts = postService.findAll();

        model.addAttribute("posts", posts);

        return "posts/list";

    }

    @GetMapping("/write")
    public String writeForm(@ModelAttribute PostForm postForm, Model model) {


        return "posts/write";

    }

    @PostMapping("/write")
    public String write(@Valid @ModelAttribute PostForm postForm, BindingResult bindingResult,
                        @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
    Member loginMember){

        if(loginMember == null){
            log.info("빈 객체");
            return "redirect:/members/login";
        }

        if (bindingResult.hasErrors()) {
            log.info("에러 발생");
            return "posts/write";
        }

         postService.savePost(loginMember.getId(), postForm);

        return "redirect:/posts/lists";


    }


}
