package utils.persistence;

import javax.swing.*;

/***
 * @author Clement GUYON
 * path of the folder "Document"
 */
public class DataPath {
    public static String STATS_PATH_DOCUMENT = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\DataJavSmash";
}
