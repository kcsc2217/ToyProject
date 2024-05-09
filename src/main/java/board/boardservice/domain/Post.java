package board.boardservice.domain;

import board.boardservice.controller.form.PostForm;
import board.boardservice.domain.dto.PostDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        this.createDate = LocalDate.now();
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

    private LocalDate createDate;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();




    //연관 관계 메서드

    public void addMember(Member member) {
        this.member = member;
    }

    //글 수정 업데이트
    public void updatePost(PostForm postForm) {
        this.title = postForm.getTitle();
        this.content = postForm.getContent();
    }




    public static Post createPost(Member member, String title, String content){
        return new Post(member, title, content);
    }


}
