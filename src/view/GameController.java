package view;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.entity.Fire;
import model.hero.Character;
import model.hero.CharacterPosition;
import model.hero.Displacement;
import model.manager.AttackManager;
import model.manager.KeyManager;
import model.world.Collision;

import java.util.ArrayList;

/***
 * Controller of the game
 * @author Clément GUYON
 */
public class GameController {

    private static final int MAX_WIDTH = 1500;
    private static final double MAX_HEIGHT = 600.0D;
    private static Scene sc1;

    @FXML
    private Pane root;
    private ArrayList<Character> characterCollection;
    private Stage heroSelectionStage;
    private String firstCharacterSelected, secondCharacterSelected;

    @FXML
    private Rectangle healthBarPlayerA;
    @FXML
    private Rectangle healthBarPlayerB;
    @FXML
    private Text textDamage;


    private Character firstCharacter;
    private Character secondCharacter;

    /**
     * @param heroSelectionStage Stage of the window
     *                           Initialize the window
     */
    GameController(String firstCharacter, String secondCharacter, Stage heroSelectionStage) {
        this.heroSelectionStage = heroSelectionStage;
        this.firstCharacterSelected = firstCharacter;
        this.secondCharacterSelected = secondCharacter;
    }

    /***
     * Getter of max width
     * @return max width of the current stage
     */
    public static int getMaxWidth() {
        return MAX_WIDTH;
    }

    /***
     * Getter of max height
     * @return max height of the current stage
     */
    public static double getMaxHeight() {
        return MAX_HEIGHT;
    }

    /***
     * @author Maxime DACISAC
     */
    @FXML
    private void initialize() {
        textDamage.setText(String.valueOf(Fire.getDamage()));
        initializePlayers();
        initializeWindow();
    }

    /***
     * Character Initialisation
     */
    private void initializePlayers() {

        firstCharacter = new Character(firstCharacterSelected, true);
        secondCharacter = new Character(secondCharacterSelected, false);

        characterCollection = new ArrayList<>();
        characterCollection.add(firstCharacter);
        characterCollection.add(secondCharacter);
    }

    /**
     * Initialize game-useful Objects, size of Window, character, key-event.
     */
    private void initializeWindow() {

        sc1 = new Scene(root, MAX_WIDTH, MAX_HEIGHT);
        sc1.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

        Displacement characterDisplacement = new Displacement(new CharacterPosition(firstCharacter), new CharacterPosition(secondCharacter));
        AttackManager attackManager = new AttackManager(firstCharacter, secondCharacter, root);
        KeyManager keyManager = new KeyManager(characterDisplacement, attackManager);

        Collision collision = new Collision(firstCharacter, secondCharacter);

        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long l) {


                characterDisplacement.moving(l);

                System.out.println("First Character Hero Scale" + firstCharacter.getHero().getScaleX());
                System.out.println("First Character Skin Scale" + firstCharacter.getSkin().getScaleX());

                System.out.println("Second Character Hero Scale" + secondCharacter.getHero().getScaleX());
                System.out.println("Second Character Skin Scale" + secondCharacter.getSkin().getScaleX());

                sc1.setOnKeyPressed(keyManager::separatorOnPress);
                sc1.setOnKeyReleased(keyManager::separatorOnRelease);

                try {
                    attackManager.getFireDisplacement().setTime(l);
                    attackManager.getFireDisplacement().hasAttacked();
                } catch (NullPointerException ignored) {
                }

                collision.checkCollision((Fire) attackManager.getFireBall(), attackManager.getCharacterWhoAttacked());

                healthActualization(firstCharacter, secondCharacter);


                //LIFE DOWNGRADING ACTUALISATION
                //firstCharacter.setLife(firstCharacter.getLifeStatus().getHP()-1);
                //System.out.println(firstCharacter.getLifeStatus().getHP());
            }
        };

        gameLoop.start();
        addToCurrentParentGroup(firstCharacter, secondCharacter);
        stageInitializer();
    }

    /***
     * Update health of each player
     * @param firstCharacter given first character
     * @param secondCharacter given second character
     */
    private void healthActualization(Character firstCharacter, Character secondCharacter) {
        healthBarPlayerA.setWidth(firstCharacter.getLifeStatus().getHP());
        healthBarPlayerB.setWidth(secondCharacter.getLifeStatus().getHP());
    }

    /***
     * Add the hitbox of each character
     * @param firstCharacter given first character
     * @param secondCharacter given second character
     */
    private void addToCurrentParentGroup(Character firstCharacter, Character secondCharacter) {
        for (Character character : characterCollection) {
            for (Circle circle : character.getHitbox().getCircleArrayList()) {
                root.getChildren().addAll(circle);
            }
        }

        root.getChildren().addAll(firstCharacter.getHero(),
                firstCharacter.getSkin(),
                secondCharacter.getHero(),
                secondCharacter.getSkin()
        );
    }

    /***
     * Initialize stage
     */
    private void stageInitializer() {
        heroSelectionStage.setTitle("JavSmash - GAME STARTED");
        heroSelectionStage.setFullScreen(false);
        heroSelectionStage.setResizable(false);
        heroSelectionStage.setScene(sc1);
        heroSelectionStage.show();
    }


}
