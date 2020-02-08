package data.xml;

import model.Interface.DataLoader;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/***
 * @author Clement GUYON
 * XML File loader
 */
public class XMLDataLoader implements DataLoader {

    /***
     * load an file and return them in Object
     * @param filePath path of given file
     * @return the file deserialized
     */
    public static Object loadResultat(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Object object = objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return object;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
