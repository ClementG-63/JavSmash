package model.entity;

import model.Interface.Positionable;

/***
 * @author Clement GUYON
 * This class allows to manipulate the different positions of the fireball
 */
public class FirePosition implements Positionable {
    private static final int FIREBALL_CHARACTER_DISTANCE = 20;
    public static final int CHARACTER_FIREBALL_SPAWN_RANGE = 80;
    private Fire fireBall;

    /***
     * Constructor of the position of the current fireball
     * @param fireBall the current fireball
     */
    FirePosition(Fire fireBall) {
        this.fireBall = fireBall;
        spawnInitPosition();
    }

    /***
     * Initialize the position of the fireball at the spawn
     */
    private void spawnInitPosition() {
        setPosXY(fireBall.getCharacter().getHero().getX() + CHARACTER_FIREBALL_SPAWN_RANGE, fireBall.getCharacter().getHero().getY() + FIREBALL_CHARACTER_DISTANCE);
    }

    //<editor-fold desc="POSITION SETTER X & Y and XY">

    /***
     * Set the position in X & Y
     * @param X given X position
     * @param Y given Y position
     */
    public void setPosXY(double X, double Y) {
        setPosX(X);
        setPosY(Y);
    }

    //<editor-fold desc="POSITION X & Y GETTER">

    /***
     * Getter of the Y position
     *
     * @return the actual position of the Fireball
     */
    public double getPosY() {
        return fireBall.getFireBallCircle().getCenterY();
    }

    /***
     * Set the Y position
     * @param pos type of double
     */
    @Override
    public void setPosY(double pos) {
        fireBall.getFireBallCircle().setCenterY(pos);
        fireBall.getFireSkinPosition().updatePosSkinY(this.getPosY());
    }
    //</editor-fold>

    /***
     * Getter of position X
     * @return the X position
     */
    public double getPosX() {
        return fireBall.getFireBallCircle().getCenterX();
    }

    /***
     * Set the position in X
     * @param pos type of double
     */
    @Override
    public void setPosX(double pos) {
        fireBall.getFireBallCircle().setCenterX(pos);
        fireBall.getFireSkinPosition().updatePosSkinX(this.getPosX());
    }
    //</editor-fold>
}
