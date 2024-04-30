package board.boardservice.controller;

import board.boardservice.domain.Post;
import board.boardservice.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards")
public class PostController {
    private final PostService postService;

    @GetMapping("/lists")
    public String lists(Model model){
        List<Post> posts = postService.findAll();

        model.addAttribute("posts",posts);

        return "posts/list";

    }


}
