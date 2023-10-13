package com.fxControllers;

import com.DeliveryService.Vehicle;
import com.hibernate.UserHib;
import com.hibernate.VehicleHib;
import com.users.Driver;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

public class UpdateTruckController {
    public TextField truckModel;
    public Button quitButton;
    public Button saveAndQuitButton;
    public TextField lNumber;
    public TextField fuelType;
    public DatePicker truckYear;
    private User currentUser;

    private EntityManagerFactory entityManagerFactory = null;
    private Vehicle selectedVehicle;

    private VehicleHib vehicleHib;

    public void setData(EntityManagerFactory entityManagerFactory, User currentUser, Vehicle selectedVehicle) {
        this.currentUser = currentUser;
        this.entityManagerFactory = entityManagerFactory;
        this.selectedVehicle = selectedVehicle;
        this.vehicleHib = new VehicleHib(entityManagerFactory);

        fillFields();
    }

    private void fillFields() {
        Vehicle vehicle = (Vehicle) vehicleHib.getVehicleById(selectedVehicle.getId());
        truckModel.setText(vehicle.getModel());
        truckYear.setValue(vehicle.getYear());
        fuelType.setText(vehicle.getFuelType());
        lNumber.setText(vehicle.getLicenceNumber());
        //like
        saveAndQuitButton.setOnAction(actionEvent -> {
            updateVehicle(vehicle);
        });
    }

    private void updateVehicle(Vehicle vehicle) {
        String model = this.truckModel.getText();
        LocalDate year = this.truckYear.getValue();
        String licenceNumber = this.lNumber.getText();
        String fuelType = this.fuelType.getText();

        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model is required");
        }

        if (year == null) {
            throw new IllegalArgumentException("Year is required");
        }

        if (year.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid year");
        }

        if (licenceNumber == null || licenceNumber.isEmpty()) {
            throw new IllegalArgumentException("Licence number is required");
        }

        if (fuelType == null || fuelType.isEmpty()) {
            throw new IllegalArgumentException("Fuel type is required");
        }

        vehicle.setModel(model);
        vehicle.setYear(year);
        vehicle.setLicenceNumber(licenceNumber);
        vehicle.setFuelType(fuelType);
        this.vehicleHib.updateVehicle(vehicle);
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

