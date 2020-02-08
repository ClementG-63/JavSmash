package model.animation;

import javafx.scene.image.Image;
import model.Interface.Spritable;
import model.manager.SkinManager;

/**
 * @author Clement GUYON
 * Sprite is use to change the skin of the character while he is walking to do an walk-model.animation
 * This class is used by the class Displacement
 */
public class Sprite implements Spritable {
    private static final int MAX_ITERATOR_CHARACTER = 14;
    private static final int MAX_ITERATOR_FIREBALL = 5;
    private static final int SWITCH_SKIN_SPEED = 5;
    private static final int ZERO = 0;
    private static final int incrementer = 1;
    private static int counter = 1, iterator = 1;

    private SkinManager skinManager;
    private String typeOfObject;


    /***
     * Constructor of Sprite
     * @param skinManager skin
     * @param typeOfObject type of the object
     */
    public Sprite(SkinManager skinManager, String typeOfObject) {
        this.skinManager = skinManager;
        this.typeOfObject = typeOfObject;
    }

    /**
     * @param typeOfMovement tells if the character is walking, jumping, dying...
     *                       spriteAnimation is changing the image of the skin in function of the type of displacement and the character.
     */
    @Override
    public void spriteAnimation(String typeOfMovement) {
        counter++;
        if (counter % SWITCH_SKIN_SPEED == ZERO) {
            if (typeOfMovement.equals("Forward")) {
                if (typeOfObject.equals("Character")) {
                    if (iterator + incrementer > MAX_ITERATOR_CHARACTER) {
                        iterator = incrementer;
                    }
                    iterator++;
                    Image imgChange = new Image(skinManager.getRepertory() + iterator + ".png");
                    skinManager.setSkinImage(imgChange);

                } else {
                    if (typeOfObject.equals("Entity")) {
                        if (iterator + incrementer > MAX_ITERATOR_FIREBALL) {
                            iterator = incrementer;
                        }
                        iterator++;
                        Image imgChange = new Image(skinManager.getRepertory() + iterator + ".png");
                        skinManager.setSkinImage(imgChange);
                    }
                }
            }
        }
    }

    /**
     * @author Clement
     * spriteReset permits to set default-position of the skin when any key is pressed.
     */
    public void spriteReset() {
        if (typeOfObject.equals("Character")) {
            skinManager.setSkinImage(new Image(skinManager.getRepertory() + "1.png"));
        } else {
            if (typeOfObject.equals("Entity")) {
                skinManager.setSkinImage(new Image(skinManager.getRepertory() + "1.png"));
            }
        }
    }
}
