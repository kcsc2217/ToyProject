package board.boardservice.service;

import board.boardservice.domain.Address;
import board.boardservice.domain.Gender;
import board.boardservice.domain.Member;
import board.boardservice.domain.MemberDTO;
import board.boardservice.exception.InvalidCredentialsException;
import board.boardservice.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = testMember();

        //when

        Long memberId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(memberId));
    }
    
    @Test
    public void 중복회원() throws Exception {
       //given
        Member member1 = testMember();
        Member member2 = testMember();

        //when

        memberService.join(member1);



        //then
        assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
    }

    @Test
    public void 로그인() throws Exception {
       //given
        Member member = testMember();

        //when
        memberService.join(member);

        Member loginMember = memberService.login("john123", "password123");

        //then
        assertEquals(member, loginMember);
    }

    @Test
    public void 로그인실패() throws Exception {
       //given
        Member member = testMember();

        //when
        memberService.join(member);

       //then
        assertThrows(InvalidCredentialsException.class, () -> memberService.login("john123", "123"));
    }

    @Test
    public void 회원정보찾기() throws Exception {
       //given
        Member member = testMember();

        //when
        memberService.join(member);
        Member findMember = memberService.findMember("john123", "010-xxxx-xxxx", "john@example.com");

        //then
        assertEquals(member, findMember);
    }

    @Test
    public void 회원정보불일치() throws Exception {
        //given
        Member member = testMember();

        //when
        memberService.join(member);
        Member findMember = memberService.findMember("lee", "010-xxxx-xxxx", "john@example.com");

        //then
       assertNull(findMember);
    }
    
    @Test
    public void 회원수정() throws Exception {
       //given
        Member member = testMember();

        MemberDTO memberDTO = new MemberDTO("이성원", "k12002@nate.com", new Address("서울", "충무로", "ㄹㅇ"),
                Gender.MALE, LocalDate.of(1990, 5, 15), "010-7119-xxxx");


        //when
        memberService.join(member);
        memberService.updateMember(member.getId(), memberDTO);

        em.flush();
        em.clear();

        Member findMember = memberRepository.findOne(member.getId());


        //then
       assertEquals(findMember.getName(), "이성원");

    }
    
    @Test
    public void 회원탈퇴() throws Exception {
       //given
        Member member = testMember();

        //when
        memberService.join(member);

        memberService.delete(member.getId());

        em.flush();
        em.clear();

        Member findMember = memberRepository.findOne(member.getId());


        //then
        assertNull(findMember);


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