package board.boardservice.controller.form;

import board.boardservice.domain.Address;
import board.boardservice.domain.Gender;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "id를 입력하세요..")
    // 사용할 id
    private String username;

    @NotEmpty(message = "이름을 입력하세요..")
    //본인 이름
    private String name;

    @NotEmpty(message = "패스워드를 입력하세요..")
    private String password;

    private String email;

    private String city;
    private String street;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDay;

    private String phoneNumber;

}
