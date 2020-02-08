package model.Interface;

import javafx.scene.layout.Pane;
import model.hero.Character;
import model.manager.SkinManager;

/***
 * @author Clement GUYON
 * Entity for range attack
 */
public interface AttackEntity {
    SkinManager skin = null;
    Character character = null;
    Pane root = null;

    /***
     * Initialize the entity
     */
    void initialize();

    /***
     * Destroy the entity
     */
    void destruction();
}
