package board.boardservice.controller;

import board.boardservice.controller.form.CommentForm;
import board.boardservice.domain.Comment;
import board.boardservice.domain.Member;
import board.boardservice.domain.Post;
import board.boardservice.domain.dto.comment.CommentCreateDto;
import board.boardservice.service.CommentService;
import board.boardservice.service.MemberService;
import board.boardservice.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comments")

public class CommentController {


    private final CommentService commentService;
    private final PostService postService;
    private final MemberService memberService;

    @PostMapping("/create")
    public String createComments(@Valid @RequestBody CommentForm commentForm,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

        if(commentForm == null){
            log.info("노바인딩");
        }

        log.info("content ={}", commentForm.getContent());

        if (bindingResult.hasErrors()) {
            log.info("에러 발생");
            return "posts/lists :: #comment-list";
        }

        log.info("바인딩 완료");

        Post findPost = postService.findOne(commentForm.getPostId());
        commentService.save(commentForm, commentForm.getMemberId(), commentForm.getPostId());

        List<Comment> findComments = findPost.getComments();
        List<CommentCreateDto> comments = findComments.stream()
                .map(CommentCreateDto::new)
                .collect(Collectors.toList());

        model.addAttribute("comments", comments);

        return "posts/list :: #comment-list";
    }


}
