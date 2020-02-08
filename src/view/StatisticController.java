package view;

import data.stub.StubDataLoader;
import data.xml.XMLDataLoader;
import data.xml.XMLDataSaver;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.statistic.Result;
import model.statistic.Statistic;
import model.statistic.SurrogateResult;
import utils.alert.FileNullPopAlert;
import utils.alert.PopupError;
import utils.persistence.DataPath;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/***
 * @author Clément GUYON & Maxime DACISAC
 */
public class StatisticController {

    private final TableColumn<Result, String> player1Column = new TableColumn<>("Player 1");
    private final TableColumn<Result, String> player2Column = new TableColumn<>("Player 2");
    private final TableColumn<Result, String> winnerColumn = new TableColumn<>("Winner");
    private final TableColumn<Result, String> dateColumn = new TableColumn<>("Date");

    @FXML
    private TableView<Result> laListe = new TableView<>();
    @FXML
    private Button LoadButton = new Button();
    @FXML
    private Button SaverButton = new Button();
    @FXML
    private Button clearButton = new Button();
    @FXML
    private Button addButton = new Button();
    @FXML
    private Button deleteButton = new Button();

    private Statistic stats;

    private File selectedFile;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private StringConverter<LocalDate> converter = new StringConverter<>() {
        @Override
        public LocalDate fromString(String string) {
            if (string == null || string.isEmpty()) {
                return null;
            } else {
                return LocalDate.parse(string, formatter);
            }
        }

        @Override
        public String toString(LocalDate date) {
            if (date == null) {
                return null;
            } else {
                return formatter.format(date);
            }
        }
    };

    /***
     * Fill tab with existing data or from Stub
     * @author Clement GUYON
     */
    private void dataFilling() {
        File defaultFile = new File(DataPath.STATS_PATH_DOCUMENT);
        File[] files = defaultFile.listFiles();

        if (defaultFile.exists()) {
            stats = new Statistic();

            assert files != null;
            for (File file : files) {
                listFilling(file.getPath());
            }
        } else {
            stats = StubDataLoader.loadResultat();
        }
    }

    /***
     * Method to add an result to list from given file's path
     * @param path given file's path
     * @author Clement GUYON
     */
    private void listFilling(String path) {
        SurrogateResult surrogateResult = (SurrogateResult) XMLDataLoader.loadResultat(path);

        assert surrogateResult != null;
        stats.addStatistic(new Result(surrogateResult.getPlayerOne(), surrogateResult.getPlayerTwo(), surrogateResult.getWinner(), surrogateResult.getLocalDate()));
    }

    /***
     * Initialize components
     * @author Clement GUYON
     */
    @FXML
    public void initialize() {
        dataFilling();

        laListe.itemsProperty().bind(stats.statisticProperty());

        initializeButtons();
        initializeCells();
    }

    /***
     * Initialize Filechooser
     * @return configured fileChooser
     * @author Clement GUYON
     */
    private FileChooser initializeFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll();

        fileChooser.setInitialDirectory(new File(DataPath.STATS_PATH_DOCUMENT));

        return fileChooser;
    }

    /***
     * initialize different Buttons of the window
     * @author Clement GUYON & Maxime DACISAC
     */
    private void initializeButtons() {
        LoadButton.setOnAction(actionEvent ->
        {
            try {
                selectedFile = initializeFileChooser().showOpenDialog(new Stage());

                listFilling(selectedFile.getPath());

            } catch (NullPointerException e) {
                new PopupError(new FileNullPopAlert("Zero File Selected"));
            }
        });

        SaverButton.setOnAction(actionEvent -> {
            try {
                for (Result result : laListe.getItems()) {
                    XMLDataSaver.serialize(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        clearButton.setOnAction(actionEvent -> clearCells());

        addButton.setOnAction(actionEvent -> {
            try {
                Stage primaryStage = new Stage();
                primaryStage.initModality(Modality.APPLICATION_MODAL);

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AjoutResultat.fxml"));

                loader.setController(new ResultController(stats));

                Pane root = loader.load();

                Scene selectionScene = new Scene(root, 400, 400);
                primaryStage.setTitle("Ajout résultat");

                primaryStage.setScene(selectionScene);
                primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteButton.setOnAction(actionEvent -> {
            try {
                Result res = laListe.getSelectionModel().getSelectedItem();
                stats.removeResultat(res);
            } catch (NullPointerException e) {
                new PopupError(new FileNullPopAlert("No Resultat Selected"));
            }
        });
    }

    /***
     * Cells Initialization
     * @author Maxime DACISAC
     */
    private void initializeCells() {

        player1Column.setCellValueFactory(new PropertyValueFactory<>("Player 1"));
        player1Column.setCellValueFactory(param -> {
            final Result res = param.getValue();
            return new SimpleStringProperty(res.getPlayer1());
        });

        player2Column.setCellValueFactory(new PropertyValueFactory<>("Player 2"));
        player2Column.setCellValueFactory(param -> {
            final Result res = param.getValue();
            return new SimpleStringProperty(res.getPlayer2());
        });

        winnerColumn.setCellValueFactory(new PropertyValueFactory<>("Winner"));
        winnerColumn.setCellValueFactory(param -> {
            final Result res = param.getValue();
            return new SimpleStringProperty(res.getWinner());
        });

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        dateColumn.setCellValueFactory(param -> {
            final Result res = param.getValue();
            return new SimpleStringProperty(converter.toString(res.getDate()));
        });


        laListe.getColumns().setAll(player1Column, player2Column, winnerColumn, dateColumn);
    }

    /***
     * Cells cleaner
     */
    private void clearCells() {
        laListe.getItems().clear();
    }


}

