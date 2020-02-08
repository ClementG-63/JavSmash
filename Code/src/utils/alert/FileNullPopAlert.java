package utils.alert;

import javafx.scene.control.Alert;
import model.Interface.Error;

/***
 * @author Clement GUYON
 * Class Alert when there isn't file selected in loading file page
 */
public class FileNullPopAlert implements Error {
    Alert errorAlert;

    /***
     * Constructor
     * @param message given message to show into the alert
     */
    public FileNullPopAlert(String message) {
        errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("None file selected");
        errorAlert.setHeaderText("File selection error");
        errorAlert.setContentText(message);
    }

    @Override
    public void showError() {
        errorAlert.show();
    }
}
