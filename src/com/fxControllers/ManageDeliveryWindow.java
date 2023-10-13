package com.fxControllers;

import com.DeliveryService.Cargo;
import com.DeliveryService.Delivery;
import com.DeliveryService.Enums.DeliveryStatus;
import com.DeliveryService.Vehicle;
import com.hibernate.CargoHib;
import com.hibernate.DeliveryHib;
import com.hibernate.UserHib;
import com.hibernate.VehicleHib;
import com.users.Driver;
import com.users.Manager;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ManageDeliveryWindow {

    public Text deliveryName;
    public Text driverName;
    public Text managerName;
    public Text statusText;
    public Text cargoText;
    public Text vehicleText;
    public ComboBox lovDrivers;
    public ComboBox lovManagers;
    public ComboBox lovStatus;
    public ComboBox lovCargo;
    public ComboBox lovVehicle;
    public Button quitBtn;

    private User user;

    private EntityManagerFactory entityManagerFactory;
    private Delivery selectedDelivery;

    private DeliveryHib deliveryHib;
    private VehicleHib vehicleHib;

    private UserHib driverHib;

    private CargoHib cargoHib;

    public void chengeDriver(ActionEvent actionEvent) {
        Delivery delivery = selectedDelivery;

        delivery.setDriver((Driver) lovDrivers.getSelectionModel().getSelectedItem());

        deliveryHib.updateDelivery(delivery);

        if(delivery.getDriver() != null){
            driverName.setText(delivery.getDriver().getUsername());
        }
        else{
            driverName.setText("Unassigned");
        }

    }

    public void chencgeManager(ActionEvent actionEvent) {
        Delivery delivery = selectedDelivery;

        delivery.setResponsiblePerson((Manager) lovManagers.getSelectionModel().getSelectedItem());

        deliveryHib.updateDelivery(delivery);

        if(delivery.getResponsiblePerson() != null){
            managerName.setText(delivery.getResponsiblePerson().getUsername());
        }
        else{
            managerName.setText("Unassigned");
        }
    }

    public void chengeStatus(ActionEvent actionEvent) {
        Delivery delivery = selectedDelivery;

        delivery.setDeliveryStatus(lovStatus.getSelectionModel().getSelectedItem().toString());

        deliveryHib.updateDelivery(delivery);

        if(delivery.getDeliveryStatus() != null){
            statusText.setText(delivery.getDeliveryStatus());
        }
        else{
            statusText.setText("Unassigned");
        }
    }

    public void chengeCargo(ActionEvent actionEvent) {
        Delivery delivery = selectedDelivery;

        delivery.setCargo((Cargo) lovCargo.getSelectionModel().getSelectedItem());

        deliveryHib.updateDelivery(delivery);

        if(delivery.getCargo() != null){
            cargoText.setText(delivery.getCargo().getName());
        }
        else{
            cargoText.setText("Unassigned");
        }
    }

    public void chengeVehicle(ActionEvent actionEvent) {
        Delivery delivery = selectedDelivery;

        delivery.setVehicle((Vehicle) lovVehicle.getSelectionModel().getSelectedItem());

        deliveryHib.updateDelivery(delivery);


        if(delivery.getVehicle() != null){
            vehicleText.setText(delivery.getVehicle().getModel());
        }
        else{
            vehicleText.setText("Unassigned");
        }
    }

    public void quitAction(ActionEvent actionEvent) {
        Stage stage = (Stage) quitBtn.getScene().getWindow();
        stage.close();
    }

    public void setData(EntityManagerFactory entityManagerFactory, User user, Delivery selectedDelivery) {
        this.user = user;
        this.entityManagerFactory = entityManagerFactory;
        this.deliveryHib = new DeliveryHib(entityManagerFactory);
        this.vehicleHib = new VehicleHib(entityManagerFactory);
        this.cargoHib = new CargoHib(entityManagerFactory);
        this.driverHib = new UserHib(entityManagerFactory);
        this.selectedDelivery = selectedDelivery;

        fillFields();
    }

    private void fillFields() {

        List<Vehicle> allVehicles = vehicleHib.getAllVehicles();
        allVehicles.forEach(m -> lovVehicle.getItems().add(m));

        List<Driver> allDrivers = driverHib.getAllDrivers();
        allDrivers.forEach(m -> lovDrivers.getItems().add(m));

        List<Cargo> allCargo = cargoHib.getAllCargo();
        allCargo.forEach(m -> lovCargo.getItems().add(m));

        List<Manager> allManagers = driverHib.getAllManagers();
        allManagers.forEach(m -> lovManagers.getItems().add(m));

        lovStatus.getItems().addAll(DeliveryStatus.CANCELED, DeliveryStatus.WAITING, DeliveryStatus.DELIVERING, DeliveryStatus.COMPLETED);

        deliveryName.setText(selectedDelivery.getName());

        setTextfields();



    }

    private void setTextfields(){
        if(selectedDelivery.getDriver() != null){
            driverName.setText(selectedDelivery.getDriver().getUsername());
        }
        else{
            driverName.setText("Unassigned");
        }
        if(selectedDelivery.getResponsiblePerson() != null){
            managerName.setText(selectedDelivery.getResponsiblePerson().getUsername());
        }
        else{
            managerName.setText("Unassigned");
        }
        if(selectedDelivery.getDeliveryStatus() != null){
            statusText.setText(selectedDelivery.getDeliveryStatus());
        }
        else{
            statusText.setText("Unassigned");
        }
        if(selectedDelivery.getCargo() != null){
            cargoText.setText(selectedDelivery.getCargo().getName());
        }
        else{
            cargoText.setText("Unassigned");
        }
        if(selectedDelivery.getVehicle() != null){
            vehicleText.setText(selectedDelivery.getVehicle().getModel());
        }
        else{
            vehicleText.setText("Unassigned");
        }
    }
}
