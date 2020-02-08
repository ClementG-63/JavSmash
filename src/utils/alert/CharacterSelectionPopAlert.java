package utils.alert;

import javafx.scene.control.Alert;
import model.Interface.Error;

/***
 * @author Clement GUYON
 * Class Alert when the same player is selected into the Character Selection
 */
public class CharacterSelectionPopAlert implements Error {
    Alert errorAlert;

    /***
     * Constructor of the alert
     * @param message given message
     */
    public CharacterSelectionPopAlert(String message) {
        errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("You can't chose the same character.");
        errorAlert.setHeaderText("Character Selection Error.");
        errorAlert.setContentText("The first player has taken :" + message);
    }

    /***
     * Override method to show alert
     */
    @Override
    public void showError() {
        errorAlert.show();
    }
}
