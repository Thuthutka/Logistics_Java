package com.fxControllers;

import com.hibernate.UserHib;
import com.users.Driver;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;

public class UserInfoController {
    public TextField firstNameText;
    public Button quitButton;
    public PasswordField passwordText;
    public TextField lastNameText;
    public TextField usernameText;
    public TextField emailText;
    public DatePicker birthdayDate;
    public Button saveAndQuitButton;
    private EntityManagerFactory entityManagerFactory;
    private User currentUser;
    private UserHib userHib;
    private Driver selectedDriver;

    //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FlowerShop");
    //UserHib userHib = new UserHib(entityManagerFactory);


    public void setData(EntityManagerFactory entityManagerFactory, User currentUser, Driver selectedDriver) {
        this.currentUser = currentUser;
        this.entityManagerFactory = entityManagerFactory;
        this.selectedDriver = selectedDriver;
        this.userHib = new UserHib(entityManagerFactory);

        fillFields();
    }

    private void fillFields() {
        Driver driver = (Driver) userHib.getDriverById(selectedDriver.getId());
        usernameText.setText(driver.getUsername());
        passwordText.setText(driver.getPassword());
        firstNameText.setText(driver.getName());
        lastNameText.setText(driver.getSurname());
        emailText.setText(driver.getEmail());
        birthdayDate = new DatePicker(driver.getBirthday());
        //like
        saveAndQuitButton.setOnAction(actionEvent ->  {
            updateUser(driver);
        });
    }

    private void updateUser(Driver driver) {
        driver.setUsername(usernameText.getText());
        driver.setPassword(passwordText.getText());
        driver.setName(firstNameText.getText());
        driver.setSurname(lastNameText.getText());
        driver.setBirthday(birthdayDate.getValue());
        driver.setEmail(emailText.getText());
        userHib.updateUser(driver);
    }

    public void quitButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void saveButtonAction(ActionEvent actionEvent) {
    }
}
