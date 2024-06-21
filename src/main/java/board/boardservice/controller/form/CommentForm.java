package board.boardservice.controller.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentForm {

    @NotEmpty(message = "내용을 입력하세요")
    private String content;

    private Long postId;
    private Long memberId;
}
