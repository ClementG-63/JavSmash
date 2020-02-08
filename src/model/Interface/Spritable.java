package model.Interface;

/***
 * @author Clement GUYON
 * Sprite Interface to create different type of Sprite (idle for exemple)
 */
public interface Spritable {

    /***
     * Methods to create the Sprite Animation swapping skin's Image
     * @param typeOfMovement given type of movement (Walk, Run, Attack...)
     */
    void spriteAnimation(String typeOfMovement);

    /***
     * Methods to reset the image of the skin when he stop moving
     */
    void spriteReset();
}
