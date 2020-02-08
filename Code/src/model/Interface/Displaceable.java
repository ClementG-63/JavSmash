package model.Interface;

import model.hero.CharacterPosition;

/**
 * @author Clement GUYON
 * Interface to allow to extend way to move
 */
public interface Displaceable {

    /***
     * Move Right & Left method
     * @param positionable is the position of an Character (@see CharacterPosition)
     * @param isMovingL boolean to know if he is moving left
     * @param isMovingR boolean to know if he is moving right
     * @param TimeSinceLastDisplacement given time since last displacement
     * @return boolean to know if the character is moving
     */
    boolean movingLR(CharacterPosition positionable, boolean isMovingL, boolean isMovingR, long TimeSinceLastDisplacement);

    /***
     * Method to allow the character to move
     * @param l time from AnimationTimer
     */
    void moving(long l);

    /***
     * Swap scale of an character when he is moving, depend of pressed-key
     * @param characterPosition is the actual position of an character
     */
    void swapScale(CharacterPosition characterPosition);
}
