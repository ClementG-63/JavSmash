package model.Interface;

/***
 * @author Clement GUYON
 * DataSaver Interface, allow to create different way to load.
 */
public interface DataSaver {
    /***
     * Default serializer
     * @param object given Object
     */
    static void serialize(Object object) {
    }
}
