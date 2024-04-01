package board.boardservice.service;

import board.boardservice.domain.Address;
import board.boardservice.domain.Gender;
import board.boardservice.domain.Member;
import board.boardservice.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        Member member = testMember();

        //when

        Long memberId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(memberId));
    }

    private static Member testMember() {
        Address address = new Address("광양", "광장로", "454");
        //given
        Member member = Member.builder()
                .name("John")
                .username("john123")
                .address(address)
                .password("password123")
                .email("john@example.com")
                .birthDay(LocalDate.of(1990, 5, 15)) // 생일 필드 설정
                // 나머지 필드에 대한 설정
                .build();

        return member;
    }

}