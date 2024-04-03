package board.boardservice.controller.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class MemberLoginForm {


    @NotEmpty(message = "id는 필수 입니다")
    //아이디
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입니다")
    //비밀번호
    private String password;

}
