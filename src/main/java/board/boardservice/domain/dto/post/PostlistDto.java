package board.boardservice.domain.dto.post;

import board.boardservice.domain.Post;
import board.boardservice.domain.dto.comment.CommentCreateDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostlistDto {

    private Long id;

    private String name;

    private String title;

    private String content;



    public PostlistDto(Post post) {
        this.id = post.getId();
        this.name = post.getMember().getName();
        this.title = post.getTitle();
        this.content = post.getContent();


    }
}

