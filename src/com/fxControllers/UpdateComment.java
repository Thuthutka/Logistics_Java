package com.fxControllers;

import com.DeliveryService.Comment;
import com.hibernate.CommentHib;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;

public class UpdateComment {

    public TextArea commentText;
    public Button updateBtn;

    private Comment comment;

    private EntityManagerFactory entityManagerFactory;


    private CommentHib commentHib;

    public void setData(EntityManagerFactory entityManagerFactory, Comment comment) {
        this.entityManagerFactory = entityManagerFactory;
        this.commentHib = new CommentHib(entityManagerFactory);
        this.comment = comment;
        commentText.setText(comment.getCommentText());
    }
    public void UpdateComment() {
        if(commentText.getText() == null){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Cant Update!", "Please put text into comment field");
        }else {

            comment.setCommentText(commentText.getText());
            commentHib.updateComment(comment);


            Stage stage = (Stage) updateBtn.getScene().getWindow();
            stage.close();
        }
    }
}
