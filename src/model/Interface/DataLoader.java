package model.Interface;

/***
 * @author Clement GUYON
 * DataLoader Interface, allow to create different way to load.
 */
public interface DataLoader {
    /***
     * default loader
     * @return null
     */
    static Object loadResultat() {
        return null;
    }
}
