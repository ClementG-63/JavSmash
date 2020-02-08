package model.manager;

import javafx.scene.input.KeyEvent;
import model.hero.Displacement;

/***
 * @author Clement GUYON
 * Key manager to seperate attack and Displacement
 */
public class KeyManager {
    private Displacement displacement;
    private AttackManager attackManager;

    /***
     * Constructor
     * @param displacement the displacement class of an character
     * @param attackManager the attackManager of the actual game
     */
    public KeyManager(Displacement displacement, AttackManager attackManager) {
        this.displacement = displacement;
        this.attackManager = attackManager;
    }

    /***
     * Separate displacement & attack
     * @param keyEvent the event of pressure
     */
    public void separatorOnPress(KeyEvent keyEvent) {
        displacement.CharacterEventOnKeyPressed(keyEvent);
        attackManager.onAttackKeyPressed(keyEvent);
    }

    /***
     * Separate displacement...
     * @param keyEvent the event of pressure
     */
    public void separatorOnRelease(KeyEvent keyEvent) {
        displacement.CharacterEventOnKeyReleased(keyEvent);
    }
}