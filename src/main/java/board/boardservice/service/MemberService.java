package board.boardservice.service;

import board.boardservice.domain.Member;
import board.boardservice.domain.dto.member.MemberUpdateDTO;
import board.boardservice.service.exception.InvalidCredentialsException;
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

    //회원 가입
    @Transactional
    public Long join(Member member){
        validateMember(member);
        memberRepository.save(member);

        return member.getId();

    }

        // 로그인 기능
        public Member login(String username,String password)  {
            List<Member> findMemberList = memberRepository.findByName(username);

            for (Member member : findMemberList) {

                if(member.getPassword().equals(password)){
                    return member;
                }
            }

            throw new InvalidCredentialsException("존재하지 않는 회원입니다");

        }

    //아이디와 비밀번호 찾기
    public Member findMember(String username, String phoneNumber, String email){
        return memberRepository.findMember(username, phoneNumber, email);
    }


    // 회원 수정
    @Transactional
    public void updateMember(Long id, MemberUpdateDTO memberDTO){
        Member findMember = memberRepository.findOne(id);

        findMember.updateMember(memberDTO);
    }

    //회원 탈퇴
    @Transactional
    public void delete(Long id){
        memberRepository.deleteMember(id);
    }


    //회원의 비밀번호와 일치 유무

    public Boolean passwordExact(Member member, String password){   

        if(member.getPassword().equals(password)){
            return true;
        }
        return false;
    }




    // 회원 존재 유무

    private void validateMember(Member member){
        List<Member> findByMember = memberRepository.findByName(member.getUsername());

        if(!findByMember.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }


    }






}
