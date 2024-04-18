package board.boardservice.domain.dto.member;

import board.boardservice.domain.Gender;
import board.boardservice.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberUpdateDTO {

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

    @NotNull(message = "생일을 입력하세요")
    private LocalDate birthDay;

    @NotNull(message = "핸드폰 번호를 입력하세요")
    private String phoneNumber;

    public static MemberUpdateDTO createMemberDTO(Member member){
        MemberUpdateDTO memberDTO = new MemberUpdateDTO();

        memberDTO.name = member.getName();
        memberDTO.email = member.getEmail();
        memberDTO.city = member.getAddress().getCity();
        memberDTO.street = member.getAddress().getStreet();
        memberDTO.zipcode = member.getAddress().getZipCode();
        memberDTO.gender = member.getGender();
        memberDTO.birthDay = member.getBirthDay();
        memberDTO.phoneNumber = member.getPhoneNumber();

        return memberDTO;
    }

}
