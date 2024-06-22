package board.boardservice.repository;

import board.boardservice.domain.Address;
import board.boardservice.domain.Comment;
import board.boardservice.domain.Member;
import board.boardservice.domain.Post;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void fetch컬렉션으로() throws Exception {
       //given
        Member member = testMember2();

        memberRepository.save(member);

        Post testPost = new Post(member, "안녕", "ㅋㅋ");

        postRepository.save(testPost);

        Comment comment = new Comment("commnet1");

        comment.addPost(testPost);

        commentRepository.save(comment);


        em.flush();
        em.clear();

        postRepository.findByAllPost();



        //when

       //then
    }

    @Test
    public void 싱글쿼리로fetchjoin() throws Exception {
       //given

        //given
        Member member = testMember2();

        memberRepository.save(member);

        Post testPost = new Post(member, "안녕", "ㅋㅋ");

        postRepository.save(testPost);

        Comment comment = new Comment("commnet1");

        comment.addPost(testPost);

        commentRepository.save(comment);


        em.flush();
        em.clear();

        postRepository.findBySinglePost(testPost.getId())


        //when

       //then
    }


    private static Member testMember2() {
        Address address = new Address("지우", "전주", "454");
        //given
        Member member = Member.builder()
                .name("지우")
                .username("kcsc2217")
                .address(address)
                .phoneNumber("010-xxxfdx-xxxx")
                .password("password")
                .email("john@examplfde.com")
                .birthDay(LocalDate.of(1990, 5, 15)) // 생일 필드 설정
                // 나머지 필드에 대한 설정
                .build();

        return member;
    }





}