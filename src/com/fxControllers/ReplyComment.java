package com.fxControllers;

import com.DeliveryService.Comment;
import com.hibernate.CommentHib;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;



public class ReplyComment {

    public TextArea replyText;
    public Button replyBtn;

    private EntityManagerFactory entityManagerFactory;

    private Comment parentComment;

    private CommentHib commentHib;

    private User user;

    public void setData(EntityManagerFactory entityManagerFactory, Comment parentComment, User user) {
        this.entityManagerFactory = entityManagerFactory;
        this.parentComment = parentComment;
        this.commentHib = new CommentHib(entityManagerFactory);
        this.user = user;
    }

    public void ReplyToComment(ActionEvent actionEvent) {
        if(replyText.getText().isEmpty()){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Can't Reply", "Add text to reply.");
        }else {
            Comment comment;

            comment = new Comment(user.getUsername(), replyText.getText(), parentComment);
            commentHib.createComment(comment);

            Stage stage = (Stage) replyBtn.getScene().getWindow();
            stage.close();
        }
    }
}
