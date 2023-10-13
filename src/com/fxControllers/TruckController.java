package com.fxControllers;

import com.DeliveryService.Vehicle;
import com.hibernate.UserHib;
import com.hibernate.VehicleHib;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TruckController {
    public TextField truckModel;
    public Button quitButton;
    public Button saveAndQuitButton;
    public TextField lNumber;
    public TextField fuelType;
    public DatePicker truckYear;

    private Stage stage;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LogisticsComp");
    private VehicleHib vehicleHib = new VehicleHib(entityManagerFactory);

    public void quitButtonAction() {
        stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void saveButtonAction(ActionEvent actionEvent) {

        if(!truckModel.getText().isEmpty() && !(truckYear.getValue() == null) && !lNumber.getText().isEmpty() && !fuelType.getText().isEmpty()){
            Vehicle newVehicle = new Vehicle(truckModel.getText(), lNumber.getText(), truckYear.getValue(), fuelType.getText());
            vehicleHib.createVehicle(newVehicle);
            quitButtonAction();
        }
        else{
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Blank fields!", "Please fill all fields to create a Vehicle.");
        }

    }
}
