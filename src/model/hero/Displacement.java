package model.hero;

import javafx.scene.input.KeyEvent;
import model.Interface.Displaceable;
import model.animation.Sprite;

/**
 * @author Maxime DACISAC & Clement GUYON
 * Displacement is managing the displacement of the character on x,y,z
 */
public class Displacement implements Displaceable {
    private static final long ONE_SECOND = 1000000000;
    private static double coefficientOfJump = 10;

    private boolean leftA, rightA, jumpA;
    private boolean leftB, rightB, jumpB;

    private long timeLastDisplacementA;
    private long timeLastDisplacementB;


    private CharacterPosition firstCp, secondCp;
    private Sprite spriteA;
    private Sprite spriteB;


    /**
     * GameLoop who manages displacement on x,y,z depending of keys pressed, released
     * Constructor of Displacement
     *
     * @param firstCp  defines the actual position of the first character
     * @param secondCp defines the actual position of the second character
     *                 Instantiates the Sprite Class
     * @see Sprite, CharacterPosition
     */
    public Displacement(CharacterPosition firstCp, CharacterPosition secondCp) {
        this.firstCp = firstCp;
        this.secondCp = secondCp;

        spriteA = new Sprite(firstCp.getPersonnage().getSkinManager(), "Character");
        spriteB = new Sprite(secondCp.getPersonnage().getSkinManager(), "Character");
    }

    /***
     * Switch scale when the character goes to right or left
     *
     * @param characterPosition defines the position of the character
     */
    @Override
    public void swapScale(CharacterPosition characterPosition) {
        if (characterPosition.getPersonnage().getSkin().getScaleX() == 1 && leftA || rightB) {
            setScale(characterPosition, -1);
        } else {
            if (characterPosition.getPersonnage().getSkin().getScaleX() == -1 && rightA || leftB)
                setScale(characterPosition, 1);
        }
    }

    /***
     * Defines the orientation of the Character
     *
     * @param characterPosition defines the position of the character
     * @param orientation Defines the orientation of the character
     */
    private void setScale(CharacterPosition characterPosition, double orientation) {
        characterPosition.getPersonnage().getSkin().setScaleX(orientation);
        characterPosition.getPersonnage().getHero().setScaleX(orientation);
    }

    /**
     * Move the character to the right or to the left in function of the time
     *
     * @param cp                        characterPosition
     * @param isMovingL                 if the character wants to move to the left
     * @param isMovingR                 is the character wants to move to the right
     * @param TimeSinceLastDisplacement time since the last displacement of the player had been effectuated
     * @return true when the character is moving or false when he is not moving
     */
    @Override
    public boolean movingLR(CharacterPosition cp, boolean isMovingL, boolean isMovingR, long TimeSinceLastDisplacement) {
        double dx = 3;

        if (isMovingL)
            dx = dx * -1;

        if (isMovingL || isMovingR) {

            if (TimeSinceLastDisplacement > ONE_SECOND / 100) {
                cp.setPosX(dx);
                return true;
            }
        }
        return false;
    }

    /**
     * Call the function jump for each character all the frame
     * Call the function moving for each character when the can move
     *
     * @param l time in nanosecond
     */
    public void moving(long l) {

        long timeSinceMovingA = l - timeLastDisplacementA;
        if (movingLR(firstCp, leftA, rightA, timeSinceMovingA)) {
            timeLastDisplacementA = l;
        }

        long timeSinceMovingB = l - timeLastDisplacementB;
        if (movingLR(secondCp, leftB, rightB, timeSinceMovingB)) {
            timeLastDisplacementB = l;
        }

        jump(firstCp, jumpA, l);
        jump(secondCp, jumpB, l);

        testJump(firstCp);
        testJump(secondCp);

        verifY(firstCp);
        verifY(secondCp);

    }


    /**
     * Do the jump of the character
     * Called all the frame
     *
     * @param cp   characterPosition
     * @param jump is the character wants to jump
     * @param l    time in nanosecond
     */
    private void jump(CharacterPosition cp, boolean jump, long l) {
        if (jump && !cp.isJumping()) {
            cp.setJumping(true);
            cp.setNbJump(cp.getNbJump() + 1);
            cp.setTimeInitOfJump(l);
        }

        cp.setTimeOfTheJumpInstant_i(l - cp.getTimeInitOfJump());
        cp.setTimeOfTheJumpInstant_i_float((float) cp.getTimeOfTheJumpInstant_i() / 1000000000);

        if (cp.isJumping()) {
            if (0.5 < cp.getTimeOfTheJumpInstant_i_float() && cp.getTimeOfTheJumpInstant_i_float() > 0.6 && cp.getNbJump() < 2 && jump) {
                cp.setJumping(true);
                cp.setNbJump(cp.getNbJump() + 1);
                cp.setTimeInitOfJump(l);

            } else {
                cp.setPosY(-coefficientOfJump * Math.cos(Math.PI * cp.getTimeOfTheJumpInstant_i_float()));

            }

            if (cp.getNbJump() < 2 && (l - cp.getTimeInitOfJump()) > ONE_SECOND) {
                cp.setJumping(false);
                cp.setNbJump(0);

            }

            if (cp.getNbJump() == 2 && (l - cp.getTimeInitOfJump()) > (1.5 * ONE_SECOND)) {
                cp.setJumping(false);
                cp.setNbJump(0);
            }
        }
    }

    /**
     * If (the first jump) is to hight, coefficient is divided by 10.
     *
     * @param cp CharacterPosition of the player
     */
    private void testJump(CharacterPosition cp){
        if(cp.getHeroPosY()<0){
            coefficientOfJump = coefficientOfJump /10;
            cp.setPosY(CharacterPosition.getFirstcharacterPosYAtSpawn());
            firstCp.setJumping(false);
            firstCp.setNbJump(0);

        }
    }

    /**
     * If the player is no longer jumping and if his posY is to hight, the player go down
     *
     * @param cp CharacterPosition of the player
     */
    private void verifY(CharacterPosition cp){
        double dy=5;
        if (cp.getNbJump()==0 && cp.getHeroPosY() < CharacterPosition.getFirstcharacterPosYAtSpawn()) {
            cp.setPosY(dy);
        }
    }

    /**
     * CharacterEventOnKeyPressed is called when an keys is press during the AnimationTimer and sets
     * boolean to know in which direction move and when jumpA.
     *
     * @param keyEvent KeyEvent, used to get which keys is pressed
     */
    public void CharacterEventOnKeyPressed(KeyEvent keyEvent) {
        spriteA.spriteAnimation("Forward");
        spriteB.spriteAnimation("Forward");

        switch (keyEvent.getCode()) {
            case Q:
                leftA = true;
                swapScale(firstCp);
                break;
            case D:
                rightA = true;
                swapScale(firstCp);
                break;
            case SPACE:
                jumpA = true;
                break;

            case LEFT:
                leftB = true;
                swapScale(secondCp);
                break;
            case RIGHT:
                rightB = true;
                swapScale(secondCp);
                break;
            case UP:
                jumpB = true;
                break;
        }
    }

    /**
     * CharacterEventOnKeyReleased is used to know when key is released to stop the model.animation and stop any movement
     *
     * @param keyEvent KeyEvent, used to get which key is released.
     * @see Sprite
     */
    public void CharacterEventOnKeyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case Q:
                leftA = false;
                spriteA.spriteReset();
                break;
            case D:
                rightA = false;
                spriteA.spriteReset();
                break;
            case SPACE:
                jumpA = false;
                break;

            case LEFT:
                leftB = false;
                spriteB.spriteReset();
                break;
            case RIGHT:
                rightB = false;
                spriteB.spriteReset();
                break;
            case UP:
                jumpB = false;
                break;
        }
    }
}
