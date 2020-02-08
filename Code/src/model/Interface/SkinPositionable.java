package model.Interface;

/***
 * @author Clement GUYON
 * positioner of an skin
 */
public interface SkinPositionable {

    /***
     * Update the skin's position in X
     * @param pos number to update the actual position
     */
    void updatePosSkinX(double pos);

    /***
     * Update the skin's position in Y
     * @param pos number to update the actual position
     */
    void updatePosSkinY(double pos);

    /***
     * Setter of the X position
     * @param pos number to update the actual position
     */
    void setPosX(double pos);

    /***
     * Setter of the Y position
     * @param pos number to update the actual position
     */
    void setPosY(double pos);
}
