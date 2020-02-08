package model.hero;

import javafx.scene.image.ImageView;
import model.Interface.Positionable;
import model.Interface.SkinPositionable;
import model.manager.SkinManager;

/**
 * @author Clement GUYON
 * Class used to manage the position of the skin
 */
public class CharacterSkinPosition implements Positionable, SkinPositionable {
    private ImageView skin;

    /**
     * Constructor
     *
     * @param skin types of CharacterSkinLoader
     * @see SkinManager
     */
    CharacterSkinPosition(SkinManager skin) {
        this.skin = skin.getSkinImage();
    }

    /**
     * updatePosSkinY updates the position of the skin in Y
     *
     * @param pos position in Y
     */
    public void updatePosSkinY(double pos) {
        setPosY(pos);
    }

    /**
     * updatePosSkinY updates the position of the skin in X
     *
     * @param pos X position
     */
    public void updatePosSkinX(double pos) {
        setPosX(pos);
    }

    /**
     * setPosX sets the position of the skin in Y
     *
     * @param pos types of double
     */
    @Override
    public void setPosX(double pos) {
        skin.setX(pos);
    }

    /**
     * setPosX sets the position of the skin in X
     * @param pos types of double
     */
    @Override
    public void setPosY(double pos) {
        skin.setY(pos);
    }


}
