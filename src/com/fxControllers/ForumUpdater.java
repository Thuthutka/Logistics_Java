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



public class ForumUpdater {

    public TextField forumTitle;
    public TextArea forumText;
    public Button updateBtn;
    private EntityManagerFactory entityManagerFactory;

    private ForumHib forumHib;

    private Forum forum;


    public void setData(EntityManagerFactory entityManagerFactory, Forum forum) {
        this.entityManagerFactory = entityManagerFactory;
        this.forumHib = new ForumHib(entityManagerFactory);
        this.forum = forum;

        fillFields();
    }

    public void fillFields(){
        forumTitle.setText(forum.getForumName());
        forumText.setText(forum.getForumDescription());
    }


    public void updateForum(ActionEvent actionEvent) {
        if(forumText.getText().isEmpty() || forumTitle.getText().isEmpty()){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Can't Edit forum!", "Please fill all the text fields.");
        }else {
            forum.setForumName(forumTitle.getText());
            forum.setForumDescription(forumText.getText());

            forumHib.updateForum(forum);

            Stage stage = (Stage) updateBtn.getScene().getWindow();
            stage.close();
        }

    }
}
