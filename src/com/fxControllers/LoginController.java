package com.fxControllers;

import com.hibernate.UserHib;
import com.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class LoginController {

    public PasswordField passwordText;
    public TextField usernameText;
    public CheckBox managerCheck;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Label loginMessageText;
    @FXML
    private Button loginButton, quitButton, registerButton, backToLoginButton;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LogisticsComp");
    private UserHib userHib = new UserHib(entityManagerFactory);

    public void quitButtonAction()throws IOException{
        stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
    public void registerButtonAction()throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("views/register.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) usernameText.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void loginButtonAction()throws IOException {
        User user = userHib.getUserByLoginData(usernameText.getText(), passwordText.getText(),  managerCheck.isSelected());
        if (user != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("views/homeScreen.fxml"));
            Parent parent = fxmlLoader.load();

            HomeScreenController hsCtrl = fxmlLoader.getController();
            hsCtrl.setData(entityManagerFactory, user);

            Scene scene = new Scene(parent);
            Stage stage = (Stage) usernameText.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "User login report", "No such user or wrong credentials");
        }
    }
}