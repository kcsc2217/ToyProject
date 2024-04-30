package board.boardservice.testData;

import board.boardservice.domain.Address;
import board.boardservice.domain.Member;
import board.boardservice.domain.Post;
import board.boardservice.repository.MemberRepository;
import board.boardservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class testInit implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Member member = Member.builder()
                .name("지우")
                .username("john123")
                .phoneNumber("010-xxxx-xxxx")
                .password("123")
                .email("john@example.com")
                .address(new Address("ZZ", "ZZ", "ZZ"))
                .birthDay(LocalDate.of(1990, 5, 15))
                .build();

        memberRepository.save(member);

        Post post = new Post(member, "안녕", "ㅋㅋㅋ");

        postRepository.save(post);


    }
}
