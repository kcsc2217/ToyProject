package board.boardservice.service;

import board.boardservice.domain.Member;
import board.boardservice.domain.Post;
import board.boardservice.domain.dto.PostDto;
import board.boardservice.controller.form.PostForm;
import board.boardservice.repository.MemberRepository;
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
    private final MemberRepository memberRepository;


    // 글 작성
    @Transactional
    public Long savePost(Long memberId, PostForm postForm) {

        Member findMember = memberRepository.findOne(memberId);

        Post post = new Post(findMember, postForm.getContent(), postForm.getTitle());


        postRepository.save(post);

        return post.getId();
    }

    public Post findOne(Long postId){
        return postRepository.findOne(postId);
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
    public void updatePost(Long id, PostForm postForm) {
        Post findPost = postRepository.findOne(id);

        findPost.updatePost(postForm);

    }

    //게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        postRepository.deletePost(id);

    }

    @Transactional
    public void upCount(Long id){
        Post findPost = postRepository.findOne(id);

        findPost.upViewCount();
    }


}
