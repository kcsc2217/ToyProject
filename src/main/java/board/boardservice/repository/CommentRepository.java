package board.boardservice.repository;

import board.boardservice.domain.Comment;
import board.boardservice.domain.Member;
import board.boardservice.domain.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class CommentRepository {

    private final EntityManager em;


    public void save(Comment comment) {
        em.persist(comment);
    }

    public Comment findOne(Long id) {
        return em.find(Comment.class, id);

    }


    public List<Comment> findAll(){
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    public Comment findByCommentId(Long id){
        return em.createQuery("select c from Comment c" +
                                " join fetch c.member m" +
                                " join fetch c.post p"+
                                " where c.id = :id", Comment.class).setParameter("id", id).getSingleResult();
    }



    public void deletePost(Long id){
        Comment comment = em.find(Comment.class, id);

        if(comment != null){
            em.remove(comment);
        }

    }
}
