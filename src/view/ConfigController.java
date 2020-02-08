package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import model.entity.Fire;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/***
 * Controller of the configuration page
 * @author Maxime DACISAC
 */
public class ConfigController implements Initializable {

    @FXML
    private TextField textFieldAvecBindingFXML;
    @FXML
    private Button PlayButton;
    @FXML
    private Button backButton;


    @FXML
    private void handlePlayButton(ActionEvent e) {
        e.consume();
        try {
            startPlaying();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    @FXML
    private void handleBackButton(ActionEvent e) {
        e.consume();
        try {
            final Node source = (Node) e.getSource();
            close(source);
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    /**
     * @throws IOException throwable by loader.load()
     *                     This function is call by the button 'Play'. It creates new window for the HeroSelection
     * @author Clément GUYON
     */
    private void startPlaying() throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/HeroSelection.fxml"));

        loader.setController(new HeroSelectionController());

        Pane root = loader.load();

        Scene selectionScene = new Scene(root, 1500, 600.0D);

        primaryStage.setScene(selectionScene);
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFieldAvecBindingFXML.textProperty().bindBidirectional(Fire.damageProperty(), new NumberStringConverter());
    }

    private void close(Node source){
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
