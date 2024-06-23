package board.boardservice.domain.dto.comment;

import board.boardservice.domain.Comment;
import lombok.Data;

@Data
public class UpdateCommentResponseDto {

    private Long postId;

    public UpdateCommentResponseDto(Comment comment) {
        this.postId = comment.getPost().getId();
    }
}
