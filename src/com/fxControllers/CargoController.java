package com.fxControllers;

import com.DeliveryService.Cargo;
import com.DeliveryService.Vehicle;
import com.hibernate.CargoHib;
import com.hibernate.VehicleHib;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CargoController {
    public TextField cargoName;
    public Button quitButton;
    public Button saveAndQuitButton;
    public TextField clientName;
    public TextField cargoWeight;
    public TextField cargoType;
    public TextField cargoSize;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LogisticsComp");
    private CargoHib cargoHib = new CargoHib(entityManagerFactory);

    public void quitButtonAction() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void saveButtonAction() {
        if(!cargoName.getText().isEmpty() && !clientName.getText().isEmpty() && !cargoWeight.getText().isEmpty()){
            Cargo newCargo = new Cargo(cargoName.getText(), clientName.getText(), Double.parseDouble(cargoWeight.getText()));
            cargoHib.createCargo(newCargo);
            quitButtonAction();
        }
        else{
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Blank fields!", "Please fill all fields to create a Vehicle.");
        }
    }
}
