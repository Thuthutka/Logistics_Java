package com.fxControllers;

import com.DeliveryService.Checkpoint;
import com.DeliveryService.Delivery;
import com.hibernate.CheckpointHib;
import com.hibernate.DeliveryHib;
import com.hibernate.UserHib;
import com.users.Driver;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;

public class CreateCheckpointController {
    public TextField locationText;
    public TextField timeSpentText;

    public Button quitButton;
    public Button saveAndQuitButton;

    private User currentUser;
    private EntityManagerFactory entityManagerFactory;
    private Delivery selectedDelivery;
    private UserHib userHib;

    private CheckpointHib checkpointHib;
    private DeliveryHib deliveryHib;

    public void setData(EntityManagerFactory entityManagerFactory, User currentUser, Delivery selectedDelivery) {
        this.currentUser = currentUser;
        this.entityManagerFactory = entityManagerFactory;
        this.selectedDelivery = selectedDelivery;
        this.userHib = new UserHib(entityManagerFactory);
        this.deliveryHib = new DeliveryHib(entityManagerFactory);
        this.checkpointHib = new CheckpointHib(entityManagerFactory);
    }

    public void saveButtonAction() {
        if(!locationText.getText().isEmpty() && !timeSpentText.getText().isEmpty()) {
            Checkpoint newCheckpoint = new Checkpoint(locationText.getText(), Integer.parseInt(timeSpentText.getText()), selectedDelivery);
            checkpointHib.createCheckpoint(newCheckpoint);
        }
            else{
                utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Blank Fields!", "Please fill all fields.");
            }
    }

    public void quitButtonAction() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}
