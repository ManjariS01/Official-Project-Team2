
import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.*;
import javafx.scene.Group;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
/**
 * @author Manjari Senthilkumar
 * @version 5/31/2019
 */
public class Game extends Application{
    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();

    private ArrayList<Node> platforms = new ArrayList<Node>();

    private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    private Pane uiRoot = new Pane();
    private Pane all = new Pane();
    //player node
    private Node player;
    private Point2D playervelocity = new Point2D(0,0);
    private boolean canJump = true;
    //player sprite
    Sprite sprite;
    ImageView spriteImg;
    
    private int levelWidth;
    private int levelHeight;
    @Override
    public void start(Stage stage) throws Exception
    {
        initContent();
        Scene scene = new Scene(appRoot);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        stage.setTitle("wazzup");
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer(){
                @Override
                public void handle(long now){update();}
            };
        timer.start();
    }

    private void initContent() throws Exception
    {
        Rectangle bg = new Rectangle(720,840);
        ImageView bgImg = convertImageView("C:\\Users\\Manjari\\Desktop\\platform game\\graphics\\background gradient.png");
        Image patt = convertImage("C:\\Users\\Manjari\\Desktop\\platform game\\graphics\\stone_texture4.jpg");
        Physics phys = new Physics(10)
        /*
        String line;
        levelWidth = LevelData.getLevel1()[0].length()*60;
        levelHeight = LevelData.getLevel1().length*60;
        for(int i = 0; i < LevelData.getLevel1().length; i++){
            line = LevelData.getLevel1()[i];
            for(int j = 0; j < line.length(); j++)
                switch(line.charAt(j)){
                    case '0':
                    break;
                    case '1':
                    Node platform = createEntity(j*60, i*60, 60, 60, Color.GRAY, gameRoot);
                    platforms.add(platform);
                    break;
                }
        }*/
        player = createEntity(0,1200,40,40,Color.TRANSPARENT);
        
        //sprite
        spriteImg = convertImageView("C:\\Users\\Manjari\\Desktop\\platform game\\graphics\\imageedit_1_9167375545.png");
        spriteImg.setViewport(new Rectangle2D(0,0,37,62));
        sprite = new Sprite(
            spriteImg,
            Duration.millis(700),
            21,7,0,0,37,62,0,0);
            //count, col, offsetx, offsety, widt, height, x, y
        sprite.setCycleCount(Animation.INDEFINITE);
        sprite.play();
        gameRoot.getChildren().add(spriteImg);
          
        //cam follow

        player.translateXProperty().addListener((obs, old, newValue) -> {
                int offset = newValue.intValue();
                if(offset > 640 && offset < levelWidth - 640){
                    gameRoot.setLayoutX(-(offset - 640));
                }
            });
        player.translateYProperty().addListener((obs, old, newValue) -> {
                int offset = newValue.intValue();
                if(offset > 60 && offset < levelHeight - 140){
                    gameRoot.setLayoutY(-(offset - 800));
                }
            });
        appRoot.getChildren().addAll(bgImg, gameRoot);
    }

    private void update()
    {
        //getTranslate computes layoutX - current X position and
        //sprite follows node
        sprite.setTranslateX(player.getTranslateX());
        sprite.setTranslateY(player.getTranslateY());
        
        if(isPressed(KeyCode.UP) && player.getTranslateY() >= 5)
            jumpPlayer();
        if(isPressed(KeyCode.LEFT) && player.getTranslateX() >= 5)
            movePlayerX(-5);
        if(isPressed(KeyCode.RIGHT) && player.getTranslateX() + 40 <= levelWidth - 5)
            movePlayerX(5);
        if(playervelocity.getY() < 10)
            playervelocity = playervelocity.add(0,1); //x does not increase in velocity
        movePlayerY((int)playervelocity.getY());
        
    }

    private boolean isPressed(KeyCode key)
    {
        //returns what key was pressed on the level1 table, if no key was pressed
        //return false by default
        return keys.getOrDefault(key, false);
    }

    private void movePlayerX(int value)
    {
        boolean movingRight = value > 0;

        for(int i = 0; i < Math.abs(value); i++)
        {
            for(Node platform : platforms)
                if(player.getBoundsInParent().intersects(platform.getBoundsInParent()))
                    if(movingRight){
                        if(player.getTranslateX() + 40 == platform.getTranslateX())
                            return;
                    }
                    else
                    if(player.getTranslateX() == platform.getTranslateX() + 60)
                        return;
            player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    private void movePlayerY(int value)
    {
        boolean movingDown = value > 0;

        for(int i = 0; i < Math.abs(value); i++){
            for(Node platform : platforms)
                if(player.getBoundsInParent().intersects(platform.getBoundsInParent()))
                    if(movingDown){
                        if(player.getTranslateY() +40 == platform.getTranslateY())
                        {
                            player.setTranslateY(player.getTranslateY()-1);
                            canJump = true;
                            return;
                        }
                    }
                    else
                    if(player.getTranslateY() == platform.getTranslateY() + 60)
                        return;
            //gravity
            player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : - 1));
        }
    }

    public void jumpPlayer()
    {
        if(canJump)
        {
            playervelocity = playervelocity.add(0,-30);
            canJump = false;
        }
    }

    public static Node createEntity(int x, int y, int w, int h, Paint fill, Pane root)
    {
        Rectangle entity = new Rectangle(w,h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(fill);

        root.getChildren().add(entity);
        return entity;
    }

    public static void main(String[] args){
        launch(args);}
        public static ImageView convertImageView(String file) throws Exception
    {
        FileInputStream input = new FileInputStream(file);
        Image image = new Image(input);
        return  new ImageView(image);
    }

    public static Image convertImage(String file) throws Exception
    {
        FileInputStream input = new FileInputStream(file);
        return new Image(input);
    }
}
