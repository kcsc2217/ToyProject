package board.boardservice.domain.dto.comment;

import board.boardservice.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CommentUpdateDto {

    private String content;


}
