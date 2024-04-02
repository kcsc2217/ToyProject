package board.boardservice.controller.form;

import board.boardservice.domain.Address;
import board.boardservice.domain.Gender;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberForm {

    // 사용할 id
    private String username;

    //본인 이름
    private String name;

    private String password;

    private String email;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDay;

    private String phoneNumber;

}
