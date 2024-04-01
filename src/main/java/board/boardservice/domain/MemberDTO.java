package board.boardservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MemberDTO {
    private String name;

    private String email;

    private Address address;

    private Gender gender;

    private LocalDate birthDay;

    private String phoneNumber;
}
