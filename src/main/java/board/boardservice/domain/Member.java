package board.boardservice.domain;

import board.boardservice.controller.form.MemberForm;
import board.boardservice.domain.dto.member.MemberUpdateDTO;
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


    // update 로직
    public void updateMember(MemberUpdateDTO memberDTO) {
        this.name = memberDTO.getName();
        Address adress = new Address(memberDTO.getCity(), memberDTO.getStreet(), memberDTO.getZipcode());
        this.address = adress;

        this.email = memberDTO.getEmail();
        this.gender = memberDTO.getGender();
        this.birthDay = memberDTO.getBirthDay();
        this.phoneNumber = memberDTO.getPhoneNumber();

    }

    // 멤버를 도메인 안에서 생성
    public static Member createMember(MemberForm memberForm) {
        Member member = new Member(null, memberForm.getUsername(), memberForm.getName(), memberForm.getPassword(), memberForm.getEmail(),
                new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode()), memberForm.getGender(), memberForm.getBirthDay(),
                memberForm.getPhoneNumber());

        return member;
    }


}