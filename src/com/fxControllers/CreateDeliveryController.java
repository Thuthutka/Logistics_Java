package com.fxControllers;

import com.DeliveryService.Cargo;
import com.DeliveryService.Delivery;
import com.DeliveryService.Vehicle;
import com.hibernate.*;
import com.users.Driver;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateDeliveryController {

    public TextField deliveryName;
    public Button quitButton;
    public Button saveAndQuitButton;
    public TextField clientName;
    public TextField pickLocation;
    public TextField cargoId;
    public DatePicker pickDate;
    public DatePicker deliveryDate;
    public TextField delLocation;
    public TextField vehicleId;
    public TextField driverUsername;

    private Stage stage;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LogisticsComp");
    private DeliveryHib delHib = new DeliveryHib(entityManagerFactory);
    private VehicleHib vehHib = new VehicleHib(entityManagerFactory);
    private UserHib userHib = new UserHib(entityManagerFactory);
    private CargoHib cargoHib = new CargoHib(entityManagerFactory);

    public void quitButtonAction() {
        stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void saveButtonAction() {
        if(!deliveryName.getText().isEmpty() && !pickLocation.getText().isEmpty()&& !delLocation.getText().isEmpty() && !vehicleId.getText().isEmpty() && !driverUsername.getText().isEmpty() &&  !cargoId.getText().isEmpty()){

            Vehicle vehicle = vehHib.getVehicleById(Integer.parseInt(vehicleId.getText()));
            Driver driver = userHib.getDriverByUsername(driverUsername.getText());
            Cargo cargo = cargoHib.getCargoById(Integer.parseInt(cargoId.getText()));

            if(driver != null) {
                Delivery newDelivery = new Delivery(deliveryName.getText(), driver);
                delHib.createDelivery(newDelivery);
                utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Created!", "");
            }
            /*else if(cargo == null){
                utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Wrong Information!", "No such vehicle found.");
            }
            else if(driver == null){
                utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Wrong Information!", "No such driver found.");
            }
            else if(vehicle == null){
                utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Wrong Information!", "No such cargo found.");
            }*/
        }
        else{
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Blank fields!", "Please fill all fields to create a Delivery.");
        }
    }
}
