package com.fxControllers;

import com.DeliveryService.Delivery;
import com.DeliveryService.Enums.DeliveryStatus;
import com.hibernate.DeliveryHib;
import com.hibernate.VehicleHib;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;

public class UpdateStatusWindow {

    public Text deliveryNameText;
    public ComboBox statusChoserComboBox;
    public Button updateButton;
    public Text currentStatusText;
    private EntityManagerFactory entityManagerFactory;
    private Delivery selectedDelivery;

    private DeliveryHib deliveryHib;
    private VehicleHib vehicleHib;

    public void setData(EntityManagerFactory entityManagerFactory, Delivery selectedDelivery) {
        this.entityManagerFactory = entityManagerFactory;
        this.selectedDelivery = selectedDelivery;
        this.deliveryHib = new DeliveryHib(entityManagerFactory);
        this.vehicleHib = new VehicleHib(entityManagerFactory);

        fillFields();
    }

    private void fillFields() {
        deliveryNameText.setText(selectedDelivery.getName());
        currentStatusText.setText(selectedDelivery.getDeliveryStatus());
        statusChoserComboBox.getItems().addAll(DeliveryStatus.CANCELED, DeliveryStatus.WAITING, DeliveryStatus.DELIVERING, DeliveryStatus.COMPLETED);

    }

    public void UpdateStatusAction(ActionEvent actionEvent) {
        Delivery delivery = deliveryHib.getDeliveryById(selectedDelivery.getId());

        delivery.setDeliveryStatus(statusChoserComboBox.getSelectionModel().getSelectedItem().toString());

        deliveryHib.updateDelivery(delivery);

        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }
}
