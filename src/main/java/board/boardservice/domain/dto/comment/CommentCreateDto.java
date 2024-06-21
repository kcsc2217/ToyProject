package board.boardservice.domain.dto.comment;

import board.boardservice.domain.Comment;
import board.boardservice.domain.Member;
import lombok.Data;

@Data
public class CommentCreateDto {

    private String content;
    private String name;

    public CommentCreateDto(Comment comment) {
        this.content = comment.getContent();
        this.name = comment.getMember().getName();
    }
}
