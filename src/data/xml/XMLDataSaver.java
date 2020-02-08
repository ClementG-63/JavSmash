package data.xml;

import model.Interface.DataSaver;
import model.statistic.Result;
import model.statistic.SurrogateResult;
import utils.persistence.DataPath;
import utils.persistence.FileNameCreator;

import java.io.*;

/***
 * @author Clement GUYON
 * XML Data Saver, save given Serializable object into folder named "DataJavsmash" into Document Folder
 */
public class XMLDataSaver implements DataSaver {

    /***
     * Serialization method
     * @param object given object to serialize
     * @throws IOException is throws if there is an problem with FileOutputStream
     */
    public static void serialize(Object object) throws IOException {
        File repositoryPathToCreate = new File(DataPath.STATS_PATH_DOCUMENT);
        File filePath = new File(repositoryPathToCreate.getPath() + "\\" + FileNameCreator.resultatFileName(object) + ".dat");

        try {
            if (repositoryPathToCreate.exists()) {
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                objectOutputStream.writeObject(new SurrogateResult((Result) object));
                objectOutputStream.close();
            } else {
                if (repositoryPathToCreate.mkdir()) {
                    System.out.println("File created");
                }
                serialize(object);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(filePath);
    }


}
