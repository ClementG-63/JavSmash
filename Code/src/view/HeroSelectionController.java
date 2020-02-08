package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.alert.CharacterSelectionPopAlert;
import utils.alert.PopupError;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/***
 * Controller of the character selection page
 * @author Clément GUYON
 */
public class HeroSelectionController implements Initializable {
    @FXML
    private Button Clement;
    @FXML
    private Button Maxime;

    private boolean isFirstCharacterSelected = false, isSecondCharacterSelected = false;
    private boolean finalState;
    private Button firstCharacterSelected, secondCharacterSelected;
    private String nameOfFirstHeroSelected, nameOfSecondHeroSelected;

    /**
     * @param e is an ActionEvent. Can gives the source and the target.
     * @throws Exception throws by the 'start' function.
     *                   <p>
     *                   This event is consumed by different buttons from the HeroSelection.
     *                   It avoid different errors : If same Hero is selected two times, if only one hero is selected the game will not start.
     * @author Clément GUYON
     */
    @FXML
    private void characterSelected(ActionEvent e) throws Exception {
        if (!isFirstCharacterSelected) {
            isFirstCharacterSelected = true;
            firstCharacterSelected = (Button) e.getSource();
            nameOfFirstHeroSelected = firstCharacterSelected.getId();

        } else {
            if (isFirstCharacterSelected && !isSecondCharacterSelected) {
                if (e.getTarget() != firstCharacterSelected) {
                    isSecondCharacterSelected = true;
                    secondCharacterSelected = (Button) e.getSource();
                    nameOfSecondHeroSelected = secondCharacterSelected.getId();

                    if (updateFinalState()) {
                        start();
                    }

                } else {
                    new PopupError(new CharacterSelectionPopAlert(firstCharacterSelected.getId()));
                }
            }
        }
    }

    /**
     * @return finalState boolean to check if two different character are selected
     * @author Clément GUYON
     */
    private boolean updateFinalState() {
        finalState = isFirstCharacterSelected && isSecondCharacterSelected;
        return finalState;
    }

    /**
     * @throws IOException throwable by loader.load()
     *                     This function is call if two character are selected. It creates new window of the Game.
     * @author Clément GUYON
     */
    private void start() throws IOException {
        Stage heroSelectionStage = new Stage();
        heroSelectionStage.initModality(Modality.APPLICATION_MODAL);
        heroSelectionStage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/InGame.fxml"));
        loader.setController(new GameController(nameOfFirstHeroSelected, nameOfSecondHeroSelected, heroSelectionStage));
        loader.load();
    }

    /***
     * Initialize button
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Clement.setStyle("-fx-background-image: url('img/BombMan/Walk/1.png');-fx-background-repeat: no-repeat no-repeat");
        Maxime.setStyle("-fx-background-image: url('img/Cucumber/Walk/1.png');-fx-background-repeat: no-repeat no-repeat");
    }
}
