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
import javafx.scene.control.*;
import org.hibernate.engine.spi.Managed;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CreateDelivery {


    public TextField newDeliveryName;
    public TextField startLocation;
    public TextField destinationLocation;
    public DatePicker startDate;
    public DatePicker deadlineDate;
    public Button createBtn;
    public ComboBox lovNewVehicle;
    public ComboBox lovNewCargo;
    public ComboBox lovNewDriver;

    private User user;
    private EntityManagerFactory entityManagerFactory;
    private Delivery selectedDelivery;

    private DeliveryHib deliveryHib;
    private VehicleHib vehicleHib;

    private UserHib driverHib;

    private CargoHib cargoHib;

    public void setData(EntityManagerFactory entityManagerFactory, User user){
        this.user = user;
        this.entityManagerFactory = entityManagerFactory;
        this.deliveryHib = new DeliveryHib(entityManagerFactory);
        this.vehicleHib = new VehicleHib(entityManagerFactory);
        this.cargoHib = new CargoHib(entityManagerFactory);
        this.driverHib = new UserHib(entityManagerFactory);

        fillFields();
    }

    private void fillFields() {
        List<Vehicle> allVehicles = vehicleHib.getAllVehicles();
        allVehicles.forEach(m -> lovNewVehicle.getItems().add(m));

        List<Driver> allDrivers = driverHib.getAllDrivers();
        allDrivers.forEach(m -> lovNewDriver.getItems().add(m));

        List<Cargo> allCargo = cargoHib.getAllCargo();
        allCargo.forEach(m -> lovNewCargo.getItems().add(m));
    }

    public void createNewDelivery(ActionEvent actionEvent) {

        if (startDate.getValue().compareTo(deadlineDate.getValue()) > 0){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "datos skirtingos kekw", "datos skirtingos kekw");

        }

        if(!newDeliveryName.getText().isEmpty() || !startLocation.getText().isEmpty() || !destinationLocation.getText().isEmpty() || !startDate.getValue().isEqual(null)
                || !deadlineDate.getValue().isEqual(null)){

            Delivery newDelivery = new Delivery();
            newDelivery.setDeliveryStatus(DeliveryStatus.WAITING.toString());
            newDelivery.setResponsiblePerson((Manager) user);

            newDelivery.setName(newDeliveryName.getText());
            newDelivery.setCreationDate(startDate.getValue());
            newDelivery.setDeliveryDate(deadlineDate.getValue());
            newDelivery.setDeliveryStartLocation(startLocation.getText());
            newDelivery.setDeliveryEndLocation(destinationLocation.getText());

            //System.out.println((Driver)lovNewDriver.getSelectionModel().getSelectedItem());

            newDelivery.setDriver((Driver) lovNewDriver.getSelectionModel().getSelectedItem());
            newDelivery.setVehicle((Vehicle) lovNewVehicle.getSelectionModel().getSelectedItem());
            newDelivery.setCargo((Cargo) lovNewCargo.getSelectionModel().getSelectedItem());



            System.out.println(newDelivery);
            deliveryHib.createDelivery(newDelivery);
        }
        else{
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Blank fields!", "Please fill fields to create a Delivery.");
        }
    }
}
