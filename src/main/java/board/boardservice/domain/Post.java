package board.boardservice.domain;

import board.boardservice.domain.dto.PostDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {


    // 처음 글을 생성 할 때
    public Post(Member member, String title, String content) {
        this.title = title;
        this.content = content;
        addMember(member);
    }


    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();


    //연관 관계 메서드

    public void addMember(Member member) {
        this.member = member;
    }

    //글 수정 업데이트
    public void updatePost(PostDto postDto) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
    }


}
