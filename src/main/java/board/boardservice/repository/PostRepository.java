package board.boardservice.repository;

import board.boardservice.domain.Member;
import board.boardservice.domain.Post;
import board.boardservice.domain.dto.PostDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;


    public void save(Post post) {
        em.persist(post);
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);

    }

    public List<Post> findAll(){
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public Post find(PostDto postDto){
        try {
            return em.createQuery("select p from Post p" +
                            " where p.title = :title" +
                            " and p.content = :content",
                             Post.class)
                    .setParameter("title", postDto.getTitle())
                    .setParameter("content", postDto.getContent())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void deletePost(Long id){
        Post post = em.find(Post.class, id);

        if(post != null){
            em.remove(post);
        }

    }

}
