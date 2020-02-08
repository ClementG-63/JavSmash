package model.statistic;

import java.io.Serializable;
import java.time.LocalDate;

/***
 * @author Clement GUYON
 * Surrogate of Result to permit the Serialization | Design Pattern Proxy
 */
public class SurrogateResult implements Serializable {
    private String playerOne;
    private String playerTwo;
    private String winner;
    private LocalDate localDate;

    /***
     * Constructor of Surrogate
     * @param result given result to transform into Serializable Result
     */
    public SurrogateResult(Result result) {
        playerOne = result.getPlayer1();
        playerTwo = result.getPlayer2();
        winner = result.getWinner();
        localDate = result.getDate();
    }

    /***
     * Getter of player One
     * @return string of player One
     */
    public String getPlayerOne() {
        return playerOne;
    }

    /***
     * Getter of player Two
     * @return string of player Two
     */
    public String getPlayerTwo() {
        return playerTwo;
    }

    /***
     * Getter of winner
     * @return string of winner
     */
    public String getWinner() {
        return winner;
    }

    /***
     * Getter of the date
     * @return the date
     */
    public LocalDate getLocalDate() {
        return localDate;
    }
}
