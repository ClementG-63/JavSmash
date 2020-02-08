package model.statistic;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/***
 * @author Maxime DACISAC
 * One line of Statistics Tab
 */
public class Result {

    private final StringProperty player1 = new SimpleStringProperty();
    private final StringProperty player2 = new SimpleStringProperty();
    private final StringProperty winner = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();

    /***
     * Constructor of an Result
     * @param player1 the player number one
     * @param player2 the player number two
     * @param winner the winner of the match
     * @param date the date of the match
     */
    public Result(String player1, String player2, String winner, LocalDate date) {
        this.player1.set(player1);
        this.player2.set(player2);
        this.winner.set(winner);
        this.date.set(date);

    }

    /***
     * Getter of player one
     * @return player number one
     */
    public String getPlayer1() {
        return player1.get();
    }

    /***
     * Getter of player two
     * @return player number two
     */
    public String getPlayer2() {
        return player2.get();
    }

    /***
     * Getter of the Winner
     * @return the winner of the match
     */
    public String getWinner() {
        return winner.get();
    }

    /***
     * Getter of date
     * @return the date of the match
     */
    public LocalDate getDate() {
        return date.get();
    }
}
