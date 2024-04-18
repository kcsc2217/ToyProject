package board.boardservice.domain.dto.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDeleteDTO {

    @NotEmpty(message = "비밀번호를 입력하세요")
    private String password;


}
