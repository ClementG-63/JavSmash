package data.stub;

import model.Interface.DataLoader;
import model.statistic.Result;
import model.statistic.Statistic;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/***
 * @author Maxime DACISAC
 * Testing Data Loader
 */
public class StubDataLoader implements DataLoader {
    private static List<Result> listOfResult;

    /***
     * @return list of Resultat in Statistic Object
     */
    public static Statistic loadResultat() {
        Statistic stats = new Statistic();
        listOfResult = new ArrayList<>();

        initializeList();

        for (Result result : listOfResult) {
            stats.addStatistic(result);
        }
        return stats;
    }

    /***
     * Initializer of the list of Result
     */
    private static void initializeList() {
        listOfResult.add(new Result("Adrien63", "Alain63", "Adrien63", LocalDate.of(2019, Month.DECEMBER, 25)));
        listOfResult.add(new Result("Mario37", "Gamer27", "Mario37", LocalDate.of(2019, Month.JANUARY, 12)));
        listOfResult.add(new Result("Luigi", "Wario", "Luigi", LocalDate.of(2019, Month.MARCH, 5)));
        listOfResult.add(new Result("Dragon25", "Gamer27", "Gamer27", LocalDate.of(2020, Month.JULY, 9)));
    }
}
