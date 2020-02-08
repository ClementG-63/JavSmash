package model.manager;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clement GUYON
 * CharacterSkinLoader manages images of skins
 */
public class SkinManager {
    private Map<String, Repertory> skinCollection;

    private ImageView skinImage;
    private String repertory;

    /**
     * Constructor of the function which defines which skin to defines at the character
     * @param entity types on int defines which character is selected
     */
    public SkinManager(String entity) {
        skinCollection = new HashMap<>();
        initializeMap();

        skinFinder(entity);
    }

    /**
     * skinManager set the skin depending of the characterNumber and set the repertory of the character
     */
    private void skinFinder(String entity) {
        for (Map.Entry<String, Repertory> entry : skinCollection.entrySet()) {
            if (entity.equals(entry.getKey())) {
                skinImage = entry.getValue().getImageView();
                setRepertory(entry.getValue().getPath());
            }
        }
    }

    /***
     * Initialize the skin Map
     */
    private void initializeMap() {
        skinCollection.put("Clement", new Repertory(new ImageView(new Image("img/BombMan/Walk/1.png")), "img/BombMan/Walk/"));
        skinCollection.put("Maxime", new Repertory(new ImageView(new Image("img/Cucumber/Walk/1.png")), "img/Cucumber/Walk/"));
        skinCollection.put("Fireball", new Repertory(new ImageView(new Image("img/Fireball/1.png")), "img/Fireball/"));
        skinCollection.put("Apple", new Repertory(new ImageView(new Image("img/Items/apple.png")), "img/Items/"));
    }

    /***
     * Internal class to set the repertory path and the Image of an skin
     */
    private static class Repertory {
        private ImageView imageView;
        private String path;

        /***
         * Constructor of Repertory
         * @param imageView Image given for the skin
         * @param path the path of an image
         */
        public Repertory(ImageView imageView, String path) {
            this.imageView = imageView;
            this.path = path;
        }

        /***
         * Getter of imageView
         * @return imageView
         */
        public ImageView getImageView() {
            return imageView;
        }

        /***
         * Getter of Path
         * @return path of Image into ImageView
         */
        public String getPath() {
            return path;
        }
    }

    /**
     * Getter of the repertory
     *
     * @return repertory type of String
     */
    public String getRepertory() {
        return repertory;
    }

    /**
     * Defines the directory of the character
     *
     * @param repertory is the name of the repertory
     */
    private void setRepertory(String repertory) {
        this.repertory = repertory;
    }

    /**
     * Getter of skinImage
     *
     * @return skinImage type of ImageView
     */
    public ImageView getSkinImage() {
        return skinImage;
    }

    /**
     * setSkinImage is the setter of imageView skinImage
     *
     * @param image types of Image
     */
    public void setSkinImage(Image image) {
        skinImage.setImage(image);
    }

}
