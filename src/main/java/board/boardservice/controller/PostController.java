package board.boardservice.controller;

import board.boardservice.controller.form.CommentForm;
import board.boardservice.controller.form.MemberLoginForm;
import board.boardservice.controller.form.PostForm;
import board.boardservice.domain.Member;
import board.boardservice.domain.Post;
import board.boardservice.domain.dto.comment.CommentCreateDto;
import board.boardservice.domain.dto.member.LoginMemberDto;
import board.boardservice.domain.dto.post.PostlistDto;
import board.boardservice.repository.PostRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    // 작성한 글 리스트들

    @GetMapping("/lists")
    public String lists(Model model) {
        List<Post> posts = postService.findAll();

        model.addAttribute("posts", posts);

        return "posts/lists";

    }

    // 글 작성 폼
    @GetMapping("/write")
    public String writeForm(@ModelAttribute PostForm postForm, Model model) {
        return "posts/write";

    }

    // 글 작성 로직
    @PostMapping("/write")
    public String write(@Valid @ModelAttribute PostForm postForm, BindingResult bindingResult,
                        @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                        Member loginMember) {

        if (loginMember == null) {
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

    // 글 상세 보기
    @GetMapping("/list/{postId}")
    public String list(@PathVariable Long postId, Model model, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {

        postService.upCount(postId);
        Post findPost = postRepository.findBySinglePost(postId);

        PostlistDto postlistDto = new PostlistDto(findPost);
        LoginMemberDto loginMemberDto = new LoginMemberDto(loginMember);

        List<CommentCreateDto> comments = findPost.getComments().stream().map(comment -> new CommentCreateDto(comment)).collect(Collectors.toList());


        model.addAttribute("comments", comments);

        model.addAttribute("post", postlistDto);

        model.addAttribute("loginMember", loginMemberDto);

        CommentForm commentForm = new CommentForm();

        model.addAttribute("commentForm", commentForm);


        log.info("게시물 회원 아이디 {}", findPost.getMember().getId());
        log.info("로그인 회원 세션 아이디{}", loginMember.getId());


        return "posts/list";
    }

    // 글 수정

    @GetMapping("/edit")
    public String editForm(@RequestParam("param") Long param,
                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                           RedirectAttributes redirectAttributes,
                           Model model) {

        log.info("{}", param);

        Post findPost = postService.findOne(param);

        if (!Objects.equals(findPost.getMember().getId(), loginMember.getId())) {

            log.info("권한이 없음");
            // 수정 권한이 없는 경우 경고 메시지를 추가하고 게시글 목록 페이지로 리다이렉트
            redirectAttributes.addFlashAttribute("error", "수정할 수 없는 회원입니다.");
            return "redirect:/posts/list/" + findPost.getId(); // 게시글 목록 페이지로 리다이렉트
        }

        PostForm postForm = PostForm.PostCreate(findPost.getTitle(), findPost.getContent());

        // 수정 권한이 있는 경우 수정 폼으로 이동
        model.addAttribute("postForm", postForm);
        return "posts/edit"; // 수정 폼 뷰로 이동
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute PostForm postForm, BindingResult bindingResult, @RequestParam("param") Long param) {
        if (bindingResult.hasErrors()) {
            log.info("에러발생");
            return "posts/edit";
        }

        postService.updatePost(param, postForm);

        return "redirect:/posts/list/" + param;

    }

    @PostMapping("/delete")
    public String delete(@RequestParam("postId") Long postId, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        Post findPost = postService.findOne(postId);

        log.info("{}", findPost);

        if (!Objects.equals(findPost.getMember().getId(), loginMember.getId())) {

            log.info("권한이 없음");
            // 수정 권한이 없는 경우 경고 메시지를 추가하고 게시글 목록 페이지로 리다이렉트
            redirectAttributes.addFlashAttribute("error", "삭제 할 수 없는 회원입니다");
            return "redirect:/posts/list/" + findPost.getId(); // 게시글 목록 페이지로 리다이렉트
        }


        postService.deletePost(postId);

        if(postService.findOne(postId) == null){
            redirectAttributes.addFlashAttribute("success", "게시물이 성공적으로 삭제되었습니다");
        }



        log.info("삭제 성공 !!");

        return "redirect:/posts/lists";


    }


}
