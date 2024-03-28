package board.boardservice.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    //연관 관계 메서드
    public void setMember(Member member){
        this.member = member;
        member.getPost().add(this);
    }


}
