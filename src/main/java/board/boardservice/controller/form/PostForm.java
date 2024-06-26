package board.boardservice.controller.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {

    @NotEmpty(message = "제목을 입력하세요")
    private String title;

    @NotEmpty(message = "내용을 입력하세요")
    private String content;

    public static PostForm PostCreate(String title, String content){
        PostForm postForm = new PostForm();

        postForm.title = title;
        postForm.content = content;

        return postForm;
    }



}
