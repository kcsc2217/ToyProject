package board.boardservice.domain.dto.comment;

import board.boardservice.domain.Comment;
import board.boardservice.domain.Member;
import lombok.Data;

@Data
public class CommentCreateDto {


    private Long id;
    private String content;
    private String name;

    public CommentCreateDto(Comment comment) {
        id = comment.getId();
        this.content = comment.getContent();
        this.name = comment.getMember().getName();
    }
}
