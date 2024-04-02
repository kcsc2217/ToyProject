package board.boardservice.domain;

import board.boardservice.domain.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"}),
                @UniqueConstraint(columnNames = {"email"}),
                @UniqueConstraint(columnNames = {"phoneNumber"})
        }
)
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;


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


    public void updateMember(MemberDTO memberDTO){
        this.name = memberDTO.getName();
        this.address = memberDTO.getAddress();
        this.email = memberDTO.getEmail();
        this.gender = memberDTO.getGender();
        this.birthDay = memberDTO.getBirthDay();
        this.phoneNumber = memberDTO.getPhoneNumber();

    }


}