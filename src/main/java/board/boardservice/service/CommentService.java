package board.boardservice.service;

import board.boardservice.domain.Comment;
import board.boardservice.domain.dto.CommentUpdateDto;
import board.boardservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 저장
    @Transactional
    public Long save(Comment comment){
        commentRepository.save(comment);

        return comment.getId();

    }


    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Transactional
    public void upDateComment(Long id, CommentUpdateDto commentUpdateDto){
        Comment findComment = commentRepository.findOne(id);

        findComment.updateContent(commentUpdateDto);

    }

    @Transactional
    public void deleteComment(Long id){
        commentRepository.deletePost(id);
    }





}
