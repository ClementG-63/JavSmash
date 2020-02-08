package model.world;

import javafx.scene.shape.Circle;
import model.entity.Fire;
import model.hero.Character;

/***
 * @author Clement GUYON
 * Permit collision between Fire ball and character
 */
public class Collision {
    private Character one, two;

    /***
     * Constructor of Collision
     * @param one player one
     * @param two player two
     */
    public Collision(Character one, Character two) {
        this.one = one;
        this.two = two;
    }

    /***
     * Method to check collision between player and fireball
     * @param fireball fireball thrown
     * @param characterWhoAttack the character who thrown the fireball
     */
    public void checkCollision(Fire fireball, Character characterWhoAttack) {

        for (Circle circleA : one.getHitbox().getCircleArrayList()) {
            for (Circle circleB : two.getHitbox().getCircleArrayList()) {
                try {
                    if (characterWhoAttack == one) {
                        if (circleB.intersects(fireball.getFireBallCircle().getBoundsInLocal())) {
                            two.setLife(-Fire.getDamage());
                        }
                    } else {
                        if (characterWhoAttack == two) {
                            if (circleA.intersects(fireball.getFireBallCircle().getBoundsInLocal())) {
                                one.setLife(-Fire.getDamage());
                            }
                        }
                    }
                } catch (NullPointerException ignored) {
                }
            }
        }
    }
}


