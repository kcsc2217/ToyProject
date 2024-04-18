package board.boardservice.domain.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {

    @Email
    @NotEmpty(message = "이메일을 입력하세요")
    private String emailAddress;
}
