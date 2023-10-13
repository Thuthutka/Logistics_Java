package com.fxControllers;

import com.DeliveryService.Comment;
import com.DeliveryService.Forum;
import com.hibernate.CommentHib;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;

public class CommentCreation {

    public TextArea commentText;
    public Button createBtn;

    private Forum forum;
    private EntityManagerFactory entityManagerFactory;
    private CommentHib commentHib;
    private User user;

    public void setData(EntityManagerFactory entityManagerFactory, User user, Forum forum) {
        this.entityManagerFactory = entityManagerFactory;
        this.forum = forum;
        this.commentHib = new CommentHib(entityManagerFactory);
        this.user = user;
    }

    public void createComment(ActionEvent actionEvent) {
        if(commentText.getText().isEmpty()){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "No text!", "Please add text to comment.");
        }else {
            Comment comment;
            comment = new Comment(user.getUsername(), commentText.getText(), forum);

            commentHib.createComment(comment);

            Stage stage = (Stage) createBtn.getScene().getWindow();
            stage.close();
        }
    }
}
