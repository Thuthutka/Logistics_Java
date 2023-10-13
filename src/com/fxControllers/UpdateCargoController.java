package com.fxControllers;

import com.DeliveryService.Cargo;
import com.DeliveryService.Vehicle;
import com.hibernate.CargoHib;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;

public class UpdateCargoController {
    public TextField cargoName;
    public Button quitButton;
    public Button saveAndQuitButton;
    public TextField clientName;
    public TextField cargoWeight;
    public TextField cargoType;
    public TextField cargoSize;

    private EntityManagerFactory entityManagerFactory = null;
    private Vehicle selectedVehicle;

    private User currentUser;
    private Cargo selectedCargo;

    private CargoHib cargoHib;

    public void setData(EntityManagerFactory entityManagerFactory, User currentUser, Cargo selectedCargo) {
        this.currentUser = currentUser;
        this.entityManagerFactory = entityManagerFactory;
        this.selectedCargo = selectedCargo;
        this.cargoHib = new CargoHib(entityManagerFactory);
        fillFields();
    }

    private void fillFields() {
        Cargo cargo = (Cargo) cargoHib.getCargoById(selectedCargo.getId());
        cargoName.setText(cargo.getName());
        clientName.setText(cargo.getClientName());
        cargoWeight.setText(cargo.getWeight().toString());
        //like
        saveAndQuitButton.setOnAction(actionEvent -> {
            updateCargo(cargo);
        });
    }

    private void updateCargo(Cargo cargo) {
        cargo.setName(cargoName.getText());
        cargo.setClientName(clientName.getText());
        cargo.setWeight(Double.parseDouble(cargoWeight.getText()));
        cargoHib.updateCargo(cargo);
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
