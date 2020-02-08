package model.Interface;


/**
 * @author Clement GUYON
 * IPosition is an Interface to be the mother of an PositionManager
 */
public interface Positionable {
    /**
     * Setter of the position X
     *
     * @param pos type of double
     */
    void setPosX(double pos);

    /**
     * Setter of the position Y
     *
     * @param pos type of double
     */
    void setPosY(double pos);
}
