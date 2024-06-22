package board.boardservice.repository;

import board.boardservice.domain.Post;
import board.boardservice.domain.dto.post.PostDto;
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

    public List<Post> findByAllPost(){
        return em.createQuery("select distinct p from Post p" +
                                            " join fetch p.member m" +
                                            " join fetch p.comments cm", Post.class).getResultList();
    }

    public Post findBySinglePost(Long id){
        try{
            return em.createQuery("select distinct p from Post p" +
                    " left join fetch p.member m" +
                    " left join fetch p.comments cm" +
                    " where p.id = :id", Post.class).setParameter("id",id).getSingleResult();
        }catch (NoResultException e){
            System.out.println("No Post Not found with id = " + id);
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
