package utils.persistence;

import model.statistic.Result;

/***
 * @author Clement GUYON
 * FileNameCreator allow to create an UNIQUE name for the file
 */
public class FileNameCreator {
    private static String uniqueName = null;

    /***
     * Unique file name method creator
     * @param object given Object
     * @return unique name
     */
    public static String resultatFileName(Object object) {
        if (object instanceof Result) {
            uniqueName = ((Result) object).getWinner() + ((Result) object).getDate();
        }
        return uniqueName;
    }
}
