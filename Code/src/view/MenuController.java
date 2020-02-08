package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entity.Fire;

import java.io.IOException;

/***
 * Controller of the main page / welcoming page
 * @author Clément GUYON
 */
public class MenuController {

    @FXML
    private Button PlayButton;
    @FXML
    private Button ConfigButton;
    @FXML
    private Button ExitButton;
    @FXML
    private Button StatisticButton;


    /**
     * Entry point for this project.
     *
     * @author Clément GUYON
     * This event is call by the button 'Play' in the main menu and call the function 'startPlaying'
     */
    @FXML
    private void handlePlayButton(ActionEvent e) {
        e.consume();
        try {
            if(Fire.getDamage()==0){
                Fire.setDamage(0.1);

            }
            startPlaying();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    /**
     * @author Clément GUYON
     * This event is call by the button 'Exit' in the main menu and call the function 'exit'
     */

    @FXML
    public void handleExitButton(ActionEvent e) {
        e.consume();
        exit();
    }

    /***
     * Event of Config button
     * @author Maxime DACISAC
     * @param e given event
     */
    @FXML
    private void handleConfigButton(ActionEvent e) {
        e.consume();
        try {
            startConfig();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void handleStatisticButton(ActionEvent e) {
        e.consume();
        try {
            startStatistic();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }



    /**
     * This event is consumed by the button 'Exit'. Permitt user to leave properly the application
     */
    private void exit() {
        try {
            System.exit(0);
        } catch (Exception exc) {
            exc.printStackTrace();
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

    /***
     * Button to start the configuration
     * @throws IOException
     * @author Maxime DACISAC
     */
    private void startConfig() throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Config.fxml"));

        loader.setController(new ConfigController());

        Pane root = loader.load();

        Scene selectionScene = new Scene(root, 300.0, 300.0);
        primaryStage.setTitle("Paramètre");

        primaryStage.setScene(selectionScene);
        primaryStage.show();
    }

    /***
     * Button to start the Statistics
     * @author Maxime DACISAC
     * @throws IOException
     */
    private void startStatistic() throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Statistic.fxml"));

        loader.setController(new StatisticController());

        Pane root = loader.load();

        Scene selectionScene = new Scene(root, 290, 300);
        primaryStage.setTitle("Statistique");

        primaryStage.setScene(selectionScene);
        primaryStage.show();
    }
}
