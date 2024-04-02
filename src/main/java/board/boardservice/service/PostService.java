package board.boardservice.service;

import board.boardservice.domain.Post;
import board.boardservice.domain.dto.PostDto;
import board.boardservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;


    // 글 작성
    @Transactional
    public Long savePost(Post post) {
        postRepository.save(post);

        return post.getId();
    }

    // 글 전체 목록 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 특정 게시물 키워드로 검색
    public Post findKeyword(PostDto postDto) {
        return postRepository.find(postDto);

    }

    // 게시글 업데이트
    @Transactional
    public void updatePost(Long id, PostDto postDto) {
        Post findPost = postRepository.findOne(id);

        findPost.updatePost(postDto);

    }

    //게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        postRepository.deletePost(id);

    }


}