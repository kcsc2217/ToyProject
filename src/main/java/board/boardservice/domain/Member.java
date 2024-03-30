package board.boardservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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


    public void updateMember(Member member){
        this.username =member.getUsername();
        this.name = member.getName();
        this.password = member.getPassword();
        this.address = member.getAddress();
        this.gender = member.getGender();
        this.birthDay = member.getBirthDay();
        this.phoneNumber = member.phoneNumber;

    }


}