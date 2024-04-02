package board.boardservice.service;

import board.boardservice.domain.Address;
import board.boardservice.domain.Comment;
import board.boardservice.domain.Member;
import board.boardservice.domain.Post;
import board.boardservice.domain.dto.CommentUpdateDto;
import board.boardservice.controller.form.CommentForm;
import board.boardservice.repository.CommentRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired CommentService commentService;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 댓글작성() throws Exception {
       //given
        CommentForm commentForm = new CommentForm("댓글1");
        Member member = testMember();
        Post post = new Post("zz"," zz");

        em.persist(post);
        em.persist(member);

        em.flush();
        em.clear();


        //when
        Long commentId = commentService.save(commentForm, member.getId(), post.getId());

        Comment findComment = commentRepository.findOne(commentId);


        //then
        assertEquals(commentId, findComment.getId());
    }

    @Test
    public void 전체조회() throws Exception {
       //given
        Comment comment1 = getComment();
        Comment comment2 = getComment();
        //when
        em.persist(comment1);
        em.persist(comment2);

        List<Comment> comments = commentService.findAll();

        //then

        assertEquals(comments.size(), 2);



    }



    @Test
    public void 업데이트() throws Exception {
       //given
        Comment comment = getComment();
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("야야");

        //when
        em.persist(comment);
        commentService.upDateComment(comment.getId(), commentUpdateDto);
       
       //then
        assertEquals(comment.getContent(), "야야");
    }

    @Test
    public void 댓글삭제() throws Exception {
       //given
        Comment comment = getComment();

        //when
        em.persist(comment);

        em.flush();
        em.clear();

        commentService.deleteComment(comment.getId());


        Comment findComment = em.find(Comment.class, comment.getId());

       //then

        assertNull(findComment);


    }

    private static Comment getComment() {

        return new Comment("ZZ");
    }

    private static Member testMember() {
        Address address = new Address("광양", "광장로", "454");
        //given
        Member member = Member.builder()
                .name("John")
                .username("john123")
                .address(address)
                .phoneNumber("010-xxxx-xxxx")
                .password("password123")
                .email("john@example.com")
                .birthDay(LocalDate.of(1990, 5, 15)) // 생일 필드 설정
                // 나머지 필드에 대한 설정
                .build();


        return member;
    }

}