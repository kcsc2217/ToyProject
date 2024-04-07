package board.boardservice.controller.form;

import board.boardservice.domain.Address;
import board.boardservice.domain.Gender;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "id를 입력하세요..")
    // 사용할 id
    private String username;

    @NotNull(message = "이름을 입력하세요..")
    //본인 이름
    private String name;

    @NotEmpty(message = "패스워드를 입력하세요..")
    private String password;

    @NotNull(message = "이메일을 입력하세요..")
    private String email;

    @NotNull(message = "도시를 입력하세요..")
    private String city;
    @NotNull(message = "거리를 입력하세요..")
    private String street;
    @NotNull(message = "주소를 입력하세요..")
    private String zipcode;

    @NotNull(message = "성별을 입력하세요")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "생년월일를 입력하세요..")
    private LocalDate birthDay;

    @NotEmpty(message = "핸드폰 번호를 입력하세요..")
    private String phoneNumber;

}
