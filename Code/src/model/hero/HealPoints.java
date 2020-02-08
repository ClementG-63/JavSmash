package model.hero;

/**
 * @author Clement GUYON and Maxime DACISAC
 * HealPoints is an components of the Character which defines number of HealPoints at the spawn, remain
 */
public class HealPoints {
    public static final int MAX_HP = 200;
    private double HP;
    private static double MIN_HP = 0;

    /***
     * Constructor of heal points
     */
    public HealPoints() {
        this.HP = MAX_HP;
    }

    /***
     * Getter of heal points
     * @return number of HP
     */
    public double getHP() {
        return Math.max(HP, MIN_HP);
    }

    /**
     * Setter of heal points
     *
     * @param HP healpoints value to update Character's HP
     */
    void setHP(double HP) {
        this.HP = HP;
    }
}
