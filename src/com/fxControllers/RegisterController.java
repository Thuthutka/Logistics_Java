package com.fxControllers;

import com.hibernate.UserHib;
import com.users.Driver;
import com.users.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    public TextField firstNameRegText;
    @FXML
    public TextField lastNameRegText;
    @FXML
    public TextField usernameRegText;
    @FXML
    public PasswordField passwordRegText;
    public CheckBox managerChk;
    public CheckBox adminCheck;
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

    public void quitButtonAction(){
        stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
    public void registerButtonAction(ActionEvent event)throws IOException {
        System.out.println("Registruoja:");

        if(managerChk.isSelected())
        {
            if(firstNameRegText.getText().isEmpty() || lastNameRegText.getText().isEmpty() || usernameRegText.getText().isEmpty() || passwordRegText.getText().isEmpty()) {
                utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Manager can't be created!", "Please fill all of the fields.");
            }else{
                Manager newManager = new Manager(firstNameRegText.getText(), lastNameRegText.getText(), usernameRegText.getText(), passwordRegText.getText(), true);
                if(adminCheck.isSelected()){
                    newManager.setAdmin(true);
                    userHib.createUser(newManager);
                }else{
                    newManager.setAdmin(false);
                    userHib.createUser(newManager);
                }
            }
        }
        else
        {
            if(adminCheck.isSelected()){
                utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Driver can't be admin!", "Please unselect admin to create driver.");
            }
            else{
                if(firstNameRegText.getText().isEmpty() || lastNameRegText.getText().isEmpty() || usernameRegText.getText().isEmpty() || passwordRegText.getText().isEmpty()) {
                    utils.FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Driver can't be created!", "Please fill all of the fields.");
                }else{
                    Driver newDriver = new Driver(firstNameRegText.getText(), lastNameRegText.getText(), usernameRegText.getText(), passwordRegText.getText(), false);
                    userHib.createUser(newDriver);
                }

            }

        }
        //System.out.println(newUser);

    }
    public void backButtonAction(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
