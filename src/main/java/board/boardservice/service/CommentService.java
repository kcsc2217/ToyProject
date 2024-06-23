package board.boardservice.service;

import board.boardservice.controller.form.CommentUpdateForm;
import board.boardservice.domain.Comment;
import board.boardservice.domain.Member;
import board.boardservice.domain.Post;
import board.boardservice.domain.dto.comment.CommentUpdateDto;
import board.boardservice.controller.form.CommentForm;
import board.boardservice.repository.CommentRepository;
import board.boardservice.repository.MemberRepository;
import board.boardservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    // 댓글 저장
    @Transactional
    public Long save(CommentForm commentForm, Long memberId, Long postId){
        Member findMember = memberRepository.findOne(memberId);
        Post findPost = postRepository.findOne(postId);

        Comment comment = Comment.createContent(commentForm.getContent(), findMember, findPost);


        commentRepository.save(comment);

        return comment.getId();

    }

    public Comment findById(Long id){
        return commentRepository.findOne(id);
    }


    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Transactional
    public Long upDateComment(CommentUpdateForm commentUpdateForm){
        Comment findComment = commentRepository.findOne(commentUpdateForm.getId());

        findComment.updateContent(commentUpdateForm.getContent());

        return findComment.getId();
    }

    @Transactional
    public void deleteComment(Long id){
        commentRepository.deletePost(id);
    }





}
