package board.boardservice.domain.dto.member;

import board.boardservice.domain.Member;
import lombok.Data;

@Data
public class LoginMemberDto {

    private Long id;

    public LoginMemberDto(Member member) {
        this.id = member.getId();
    }
}
