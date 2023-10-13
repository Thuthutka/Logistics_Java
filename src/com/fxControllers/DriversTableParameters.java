package com.fxControllers;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

public class DriversTableParameters {

    private SimpleStringProperty driverId = new SimpleStringProperty();
    private SimpleStringProperty driverLogin = new SimpleStringProperty();
    private SimpleStringProperty driverPsw = new SimpleStringProperty();
    private SimpleStringProperty driverName = new SimpleStringProperty();
    private SimpleStringProperty driverSurn = new SimpleStringProperty();
    private SimpleStringProperty driverEmail = new SimpleStringProperty();



    public DriversTableParameters(SimpleStringProperty driverId, SimpleStringProperty driverLogin, SimpleStringProperty driverPsw, SimpleStringProperty driverName, SimpleStringProperty driverSurn, SimpleStringProperty driverEmail) {
        this.driverId = driverId;
        this.driverLogin = driverLogin;
        this.driverPsw = driverPsw;
        this.driverName = driverName;
        this.driverSurn = driverSurn;
        this.driverEmail = driverEmail;
    }

    public String getDriverId() {
        return driverId.get();
    }

    public SimpleStringProperty driverIdProperty() {
        return driverId;
    }

    public String getDriverLogin() {
        return driverLogin.get();
    }

    public SimpleStringProperty driverLoginProperty() {
        return driverLogin;
    }

    public String getDriverPsw() {
        return driverPsw.get();
    }

    public SimpleStringProperty driverPswProperty() {
        return driverPsw;
    }

    public String getDriverName() {
        return driverName.get();
    }

    public SimpleStringProperty driverNameProperty() {
        return driverName;
    }

    public String getDriverSurn() {
        return driverSurn.get();
    }

    public SimpleStringProperty driverSurnProperty() {
        return driverSurn;
    }

    public String getDriverEmail() {
        return driverEmail.get();
    }

    public SimpleStringProperty driverEmailProperty() {
        return driverEmail;
    }

    public void setDriverId(String driverId) {
        this.driverId.set(driverId);
    }

    public void setDriverLogin(String driverLogin) {
        this.driverLogin.set(driverLogin);
    }

    public void setDriverPsw(String driverPsw) {
        this.driverPsw.set(driverPsw);
    }

    public void setDriverName(String driverName) {
        this.driverName.set(driverName);
    }

    public void setDriverSurn(String driverSurn) {
        this.driverSurn.set(driverSurn);
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail.set(driverEmail);
    }
}
