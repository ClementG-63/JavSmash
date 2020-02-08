package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.statistic.Result;
import model.statistic.Statistic;
import utils.alert.FileNullPopAlert;
import utils.alert.PopupError;

import java.time.LocalDate;

/***
 * @author Maxime DACISAC
 */
public class ResultController {

    @FXML
    private TextField PlayerOneF = new TextField();
    @FXML
    private TextField PlayerTwoF = new TextField();
    @FXML
    private CheckBox PlayerOneCB = new CheckBox();
    @FXML
    private CheckBox PlayerTwoCB = new CheckBox();
    @FXML
    private Button addButton = new Button();
    @FXML
    private Button backButton = new Button();

    private Statistic stats;

    /***
     * Constructor
     * @param stats given Statistics (result list)
     */
    public ResultController(Statistic stats) {
        this.stats = stats;
    }

    @FXML
    private void initialize() {
        initializeButtons();
    }

    private void initializeButtons() {
        addButton.setOnAction(actionEvent -> {
            try {
                if (!PlayerOneF.getText().isEmpty() && !PlayerTwoF.getText().isEmpty()) {
                    if (PlayerOneCB.isSelected() && !PlayerTwoCB.isSelected()) {
                        addResultat(PlayerOneF.getText(), PlayerTwoF.getText(), PlayerOneF.getText());
                        close(actionEvent);
                    }
                    if (!PlayerOneCB.isSelected() && PlayerTwoCB.isSelected()) {
                        addResultat(PlayerOneF.getText(), PlayerTwoF.getText(), PlayerTwoF.getText());
                        close(actionEvent);
                    }
                }
            } catch (NullPointerException e) {
                new PopupError(new FileNullPopAlert("Two names are no selected"));
            }
        });
        backButton.setOnAction(this::close);
    }

    /***
     * Add result into TableView
     * @param playerOne given player one
     * @param playerTwo given player two
     * @param winner given winner
     */
    private void addResultat(String playerOne, String playerTwo, String winner) {
        Result result = new Result(playerOne, playerTwo, winner, LocalDate.now());
        stats.addStatistic(result);
    }

    /***
     * Close actual window
     * @param actionEvent click event
     */
    private void close(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
