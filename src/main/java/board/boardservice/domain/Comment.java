package board.boardservice.domain;

import board.boardservice.domain.dto.CommentUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Lazy;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    //게시글 추가 메서드
    public void addMember(Member member) {
        this.member = member;
    }

    public void addPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public void updateContent(CommentUpdateDto commentUpdateDto){
        this.content = commentUpdateDto.getContent();
    }


}

