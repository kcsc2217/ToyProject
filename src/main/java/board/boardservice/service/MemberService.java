package board.boardservice.service;

import board.boardservice.domain.Member;
import board.boardservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member){
        memberRepository.save(member);
        validateMember(member);
        memberRepository.save(member);

    }

    public Member login(String username,String password){
        List<Member> findMemberList = memberRepository.findByName(username);

        for (Member member : findMemberList) {

            if(member.getPassword().equals(password)){
                return member;
            }
        }

        return null;


    }
    //아이디와 비밀번호 찾기
    public Member findMember(String username, String phoneNumber, String email){
        return memberRepository.findMember(username, phoneNumber, email);
    }

    @Transactional
    public void update(Long id, Member member){
        Member findmember = memberRepository.findOne(id);

        findmember.updateMember(member);
    }





    private void validateMember(Member member){
        List<Member> findByMember = memberRepository.findByName(member.getUsername());

        if(!findByMember.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }


    }





}
