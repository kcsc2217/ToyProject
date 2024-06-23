package board.boardservice.domain.dto.comment;

import board.boardservice.domain.Comment;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Data


public class CommentUpdateDto {

    private Long id;
    private String content;

}
