package board.boardservice.repository;

import board.boardservice.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;


    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);

    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //이름 찾기
    public List<Member> findByName(String username) {
        return em.createQuery("select * from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    //비밀 번화 반환

    public Member findMember(String username, String phoneNumber, String email) {
        try {
            return em.createQuery("select m from Member m" +
                            " where m.username = :username" +
                            "and m.phoneNumber = :phoneNumber" +
                            "and m.email = email", Member.class)
                    .setParameter("username", username)
                    .setParameter("phoneNumber", phoneNumber)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }


}
