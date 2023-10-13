package com.fxControllers;

import com.DeliveryService.Delivery;
import com.DeliveryService.Vehicle;
import com.hibernate.DeliveryHib;
import com.hibernate.UserHib;
import com.hibernate.VehicleHib;
import com.users.Driver;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AssignDeliveryWindow {

    public ComboBox lovVehicles;
    public Text deliverynameText;
    public Button assignButton;
    private User user;
    private EntityManagerFactory entityManagerFactory;
    private Delivery selectedDelivery;

    private DeliveryHib deliveryHib;
    private VehicleHib vehicleHib;

    public void setData(EntityManagerFactory entityManagerFactory, User user, Delivery selectedDelivery) {
        this.user = user;
        this.entityManagerFactory = entityManagerFactory;
        this.selectedDelivery = selectedDelivery;
        this.deliveryHib = new DeliveryHib(entityManagerFactory);
        this.vehicleHib = new VehicleHib(entityManagerFactory);

        fillFields();
    }

    private void fillFields() {
        List<Vehicle> allVehicles = vehicleHib.getAllVehicles();
        allVehicles.forEach(m -> lovVehicles.getItems().add(m));
        deliverynameText.setText(selectedDelivery.getName());
    }

    public void AssignToMeAction(ActionEvent actionEvent) {
        if(!lovVehicles.getSelectionModel().getSelectedItem().equals(null)){
            Delivery delivery = deliveryHib.getDeliveryById(selectedDelivery.getId());
            delivery.setDriver((Driver) user);
            delivery.setVehicle((Vehicle) lovVehicles.getSelectionModel().getSelectedItem());

            deliveryHib.updateDelivery(delivery);

            Stage stage = (Stage) assignButton.getScene().getWindow();
            stage.close();
        }
        else{
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "No vehicle slelected!", "Please select a vehicle to continue.");
        }


    }
}
