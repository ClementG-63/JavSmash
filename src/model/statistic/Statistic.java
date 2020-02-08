package model.statistic;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/***
 * @author Maxime DACISAC
 */
public class Statistic {
    private ObservableList<Result> statisticObs = FXCollections.observableArrayList();
    private final ListProperty<Result> statistics = new SimpleListProperty<>(statisticObs);

    public ListProperty<Result> statisticProperty() {
        return statistics;
    }

    /***
     * method to add an Result into Statistics (result list)
     * @param result given Result to add to list
     */
    public void addStatistic(Result result) {
        statisticObs.add(result);
    }

    /***
     * method to remove an Result from Statistics (result list)
     * @param res given Result to add to list
     */
    public void removeResultat(Result res) {
        statisticObs.remove(res);
    }
}
