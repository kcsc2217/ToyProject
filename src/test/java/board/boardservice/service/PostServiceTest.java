package board.boardservice.service;

import board.boardservice.controller.form.PostForm;
import board.boardservice.domain.Address;
import board.boardservice.domain.Member;
import board.boardservice.domain.Post;
import board.boardservice.repository.MemberRepository;
import board.boardservice.repository.PostRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired PostService postService;
    @Autowired
    PostRepository postRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 게시물수정() throws Exception {
       //given
        Member member = testMember();

        Post post = new Post(member, "안녕 1", "안녕 2");

        PostForm postForm = PostForm.PostCreate("안녕2", "안녕2");


        //when
        memberRepository.save(member);
        postRepository.save(post);
        postService.updatePost(post.getId(), postForm);

        em.flush();
        em.clear();;

       //then
        assertEquals(post.getTitle(), "안녕2");
    }

    private static Member testMember() {
        Address address = new Address("광양fsdaf", "광장로fd", "454");
        //given
        Member member = Member.builder()
                .name("John")
                .username("kcsc2217")
                .address(address)
                .phoneNumber("010-7119-8112")
                .password("password123")
                .email("k12002@example.com")
                .birthDay(LocalDate.of(1990, 5, 15)) // 생일 필드 설정
                // 나머지 필드에 대한 설정
                .build();

        return member;
    }
}
