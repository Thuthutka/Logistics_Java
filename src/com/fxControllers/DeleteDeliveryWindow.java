package com.fxControllers;

import com.DeliveryService.Delivery;
import com.DeliveryService.Enums.DeliveryStatus;
import com.hibernate.DeliveryHib;
import com.hibernate.VehicleHib;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeleteDeliveryWindow {

    public Text deliveryName;
    public Button deleteBtn;
    public Button cancelBtn;

    private EntityManagerFactory entityManagerFactory;
    private Delivery selectedDelivery;

    private DeliveryHib deliveryHib;

    public void setData(EntityManagerFactory entityManagerFactory, Delivery selectedDelivery) {
        this.entityManagerFactory = entityManagerFactory;
        this.selectedDelivery = selectedDelivery;
        this.deliveryHib = new DeliveryHib(entityManagerFactory);

        fillFields();
    }

    private void fillFields() {
        deliveryName.setText(selectedDelivery.getName());
    }

    public void deleteDeliveryAction(ActionEvent actionEvent) {

        System.out.println(selectedDelivery.getDeliveryStatus().toString());

        if(!selectedDelivery.getDeliveryStatus().toString().equals(DeliveryStatus.DELIVERING.toString())){
                deliveryHib.deleteDelivery(selectedDelivery);
                Stage stage = (Stage) deleteBtn.getScene().getWindow();
                stage.close();
        } else{
        utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Can't Delete Delivery!", "You can't Delete ongoing Delivery.");
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
