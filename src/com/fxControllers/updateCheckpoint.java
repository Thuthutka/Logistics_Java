package com.fxControllers;

import com.DeliveryService.Checkpoint;
import com.DeliveryService.Delivery;
import com.DeliveryService.Vehicle;
import com.hibernate.CheckpointHib;
import com.hibernate.DeliveryHib;
import com.hibernate.UserHib;
import com.users.Driver;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;

public class updateCheckpoint {
    public TextField locationText;
    public Button quitButton;
    public Button saveAndQuitButton;
    public TextField timeSpentText;
    private User currentUser;
    private EntityManagerFactory entityManagerFactory;
    private UserHib userHib;
    private DeliveryHib deliveryHib;
    private CheckpointHib checkpointHib;
    private Checkpoint selectedCheckpoint;

    public void setData(EntityManagerFactory entityManagerFactory, User currentUser, Checkpoint selecteCheckpoint) {
        this.currentUser = currentUser;
        this.entityManagerFactory = entityManagerFactory;
        this.selectedCheckpoint = selecteCheckpoint;
        this.userHib = new UserHib(entityManagerFactory);
        this.deliveryHib = new DeliveryHib(entityManagerFactory);
        this.checkpointHib = new CheckpointHib(entityManagerFactory);

        fillFields();
    }

    private void fillFields() {
        Checkpoint checkpoint = (Checkpoint) checkpointHib.getCheckpointById(selectedCheckpoint.getId());
        locationText.setText(checkpoint.getLocation());
        timeSpentText.setText(Integer.toString(checkpoint.getStopedTime()));
        saveAndQuitButton.setOnAction(actionEvent ->  {
            updateCheckpoint(checkpoint);
        });
    }

    private void updateCheckpoint(Checkpoint checkpoint) {
        checkpoint.setLocation(locationText.getText());
        checkpoint.setStopedTime(Integer.parseInt(timeSpentText.getText()));
        checkpointHib.updateCheckpoint(checkpoint);
    }

    public void saveButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void quitButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}
