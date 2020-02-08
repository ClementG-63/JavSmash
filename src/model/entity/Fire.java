package model.entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.Interface.AttackEntity;
import model.hero.Character;
import model.manager.SkinManager;

/***
 * @author Clement GUYON
 * This class is used to cast Fireball specific key is pressed
 */
public class Fire implements AttackEntity {
    private static final int RADIUS = 15;
    private static final int OPACITY = 0;

    private Character character;
    private Pane root;
    private Circle fireBallCircle;
    private double direction;

    private FirePosition fireballPosition;

    private SkinManager skin;
    private FireSkinPosition fireSkinPosition;

    private static DoubleProperty damage = new SimpleDoubleProperty();


    /**
     * @param character Character who casts the fireball
     * @param root      Actual parent-group ~~~~~~
     */
    public Fire(Character character, Pane root) {
        this.root = root;
        this.character = character;

        this.direction = character.getHero().getScaleX();

        initialize();

        skin = new SkinManager("Fireball");
        fireSkinPosition = new FireSkinPosition(skin);
        fireballPosition = new FirePosition(this);

        root.getChildren().addAll(fireBallCircle, skin.getSkinImage());
    }

    /**
     * Initialization of the fireball's base : Base Type, colors, size, opacity.
     */
    @Override
    public void initialize() {
        fireBallCircle = new Circle();

        fireBallCircle.setRadius(RADIUS);
        fireBallCircle.setOpacity(OPACITY);
    }

    /**
     * Destructs the current fireball
     */
    @Override
    public void destruction() {
        root.getChildren().removeAll(fireBallCircle, skin.getSkinImage());

        fireballPosition = null;
        fireSkinPosition = null;
        fireBallCircle = null;
        skin = null;
    }


    //<editor-fold desc="Getter of Character, fireSkinPosition & FireBall">

    /***
     * Damage property
     * @return damage
     */
    public static DoubleProperty damageProperty() {
        return damage;
    }

    /***
     * Getter of the character
     * @return the character who cast the fireball
     */
    public Character getCharacter() {
        return character;
    }

    /***
     * Getter of damage
     * @return damage
     */
    public static double getDamage() {
        return Fire.damage.get();
    }

    /***
     * Setter of damage
     * @param damage of the fireball
     */
    public static void setDamage(double damage) {
        Fire.damage.set(damage);
    }

    /***
     * Getter of the position of the fireball
     * @return the position of fireball
     */
    FireSkinPosition getFireSkinPosition() {
        return fireSkinPosition;
    }

    /***
     * Getter of skin Manager
     * @return the skin manager
     */
    public SkinManager getSkinManager() {
        return skin;
    }

    /***
     * Getter of the base / shape of an fireball
     * @return the base-shape of fireball
     */
    public Circle getFireBallCircle() {
        return fireBallCircle;
    }

    /***
     * Getter of the position of the fireball
     * @return the actual position of fireball
     */
    public FirePosition getFireballPosition() {
        return fireballPosition;
    }

    /**
     * Getter of the direction of the fireball
     * @return the direction of the fireball
     */
    public double getDirection() {
        return direction;
    }


    //</editor-fold>
}
