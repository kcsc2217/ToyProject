package board.boardservice.domain;

import board.boardservice.domain.dto.comment.CommentUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    public Comment(String content) {
        this.content = content;
    }



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


    //게시글에 멤버 추가 메서드
    public void addMember(Member member) {
        this.member = member;
    }


    //게시글과 댓글 연관 관계 메서드
    public void addPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }


    // 업데이트 메서드
    public void updateContent(CommentUpdateDto commentUpdateDto){
        this.content = commentUpdateDto.getContent();
    }

    //
    public static Comment createContent(String content, Member member, Post post){
        Comment comment = new Comment(content);
        comment.addMember(member);
        comment.addPost(post);

        return comment;

    }


}

