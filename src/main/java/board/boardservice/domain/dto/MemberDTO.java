package board.boardservice.domain.dto;

import board.boardservice.domain.Address;
import board.boardservice.domain.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MemberDTO {

    @NotNull(message = "이름을 입력하세요")
    private String name;
    @NotNull(message = "이메일을 입력하세요")
    private String email;

    @NotNull(message = "도시를 입력하세요")
    private String city;
    @NotNull(message = "거리를 입력하세요")
    private String street;

    @NotNull(message = "도시를 입력하세요")
    private String zipcode;

    @NotNull(message = "성별을 입력하세요")
    private Gender gender;

    private LocalDate birthDay;

    private String phoneNumber;
}
