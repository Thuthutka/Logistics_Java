package com.fxControllers;

import com.DeliveryService.Forum;
import com.hibernate.ForumHib;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;

public class ForumCreator {

    public TextField forumTitleText;
    public TextArea forumText;
    public Button createBtn;
    private EntityManagerFactory entityManagerFactory;
    private ForumHib forumHib;

    public void setData(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.forumHib = new ForumHib(entityManagerFactory);
    }

    public void createForumAction(ActionEvent actionEvent) {
        if (forumText.getText().isEmpty() || forumTitleText.getText().isEmpty()){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Create forum", "All fields must be filled.");
        }else {
            Forum forum= new Forum();
            forum.setForumName(forumTitleText.getText());
            forum.setForumDescription(forumText.getText());

            forumHib.createForum(forum);

            Stage stage = (Stage) createBtn.getScene().getWindow();
            stage.close();
        }
    }

}
