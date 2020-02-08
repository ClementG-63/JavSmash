package utils.alert;

import model.Interface.Error;

/***
 * @author Clement GUYON
 * Class Alert
 */
public class PopupError implements Error {
    private Error error;

    /***
     * Default Constructor
     * @param error given Error
     */
    public PopupError(Error error) {
        this.error = error;
        showError();
    }

    @Override
    public void showError() {
        error.showError();
    }
}
