package com.fxControllers;

import com.hibernate.UserHib;
import com.users.Driver;
import com.users.Manager;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;

public class EditManager {

    public TextField firstNameText;
    public TextField lastNameText;
    public TextField usernameText;
    public TextField passwordText;
    public TextField emailText;
    public TextField officeAdressText;
    public DatePicker birthdayView;
    public Button saveBtn;
    private EntityManagerFactory entityManagerFactory;
    private User currentUser;
    private UserHib userHib;
    private Manager selectedManager;

    public void setData(EntityManagerFactory entityManagerFactory, User currentUser, Manager selectedManager) {
        this.currentUser = currentUser;
        this.entityManagerFactory = entityManagerFactory;
        this.selectedManager = selectedManager;
        this.userHib = new UserHib(entityManagerFactory);

        fillFields();
    }

    private void fillFields() {
        firstNameText.setText(selectedManager.getName());
        lastNameText.setText(selectedManager.getSurname());
        usernameText.setText(selectedManager.getUsername());
        passwordText.setText(selectedManager.getPassword());
        emailText.setText(selectedManager.getEmail());
        officeAdressText.setText(selectedManager.getOfficeAdress());
        birthdayView.setValue(selectedManager.getBirthday());
    }

    public void updateManagerInfo(ActionEvent actionEvent) {

        if(usernameText.getText().isEmpty() || passwordText.getText().isEmpty() || firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || birthdayView.getValue().isEqual(null) || emailText.getText().isEmpty() ){
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Manager can't be updated!", "Please fill of the fields.");
        }else{
            selectedManager.setUsername(usernameText.getText());
            selectedManager.setPassword(passwordText.getText());
            selectedManager.setName(firstNameText.getText());
            selectedManager.setSurname(lastNameText.getText());
            selectedManager.setBirthday(birthdayView.getValue());
            selectedManager.setEmail(emailText.getText());
            userHib.updateUser(selectedManager);

            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        }

    }
}
