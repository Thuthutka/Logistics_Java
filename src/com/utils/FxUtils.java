package utils;

import javafx.scene.control.Alert;
//L.S
public class FxUtils {
    public static void generateAlert(Alert.AlertType alertType, String header, String contentText){
        Alert alert = new Alert(alertType);
        alert.setTitle("Attention!");
        alert.setHeaderText(header);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}