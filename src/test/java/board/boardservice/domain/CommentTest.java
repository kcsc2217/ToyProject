package board.boardservice.domain;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class CommentTest {

    @Autowired
    private EntityManager em;


    @Test
    public void 연관관계() throws Exception {
       //given
        Member member = testMember2();

        em.persist(member);

        Post post = new Post(member, "test1", "test2");

        em.persist(post);

        Comment comment = Comment.createContent("ㄷㄷ", member, post);

        em.persist(comment);


        //then
        Assertions.assertThat(comment.getPost()).isEqualTo(post);


    }



    private static Member testMember2() {
        Address address = new Address("지우", "전주", "454");
        //given
        Member member = Member.builder()
                .name("지우")
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