import java.util.ArrayList;

import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.paint.*;
import javafx.scene.layout.Pane;

import javafx.geometry.Point2D;
/**
 * Write a description of class Physics here.
 *
 * @author Manjari Senthilkumar
 * @version 06/02/2019
 */
public class Physics
{
    private double gForce;
    private ArrayList<Node> platforms = new ArrayList<Node>();
    private double levelWidth;
    private double levelHeight;
    private boolean canJump;
    private double vel;
    private Point2D playervelocity;
    public Physics(double g, Pane root, Point2D playervelocity)
    {
        gForce = g;
        vel = 0;
        this.playervelocity = playervelocity;
        levelWidth = LevelData.getLevel1()[0].length()*60;
        levelHeight = LevelData.getLevel1().length*60;
        for(int i = 0; i < LevelData.getLevel1().length; i++){
            String line = LevelData.getLevel1()[i];
            for(int j = 0; j < line.length(); j++)
                switch(line.charAt(j)){
                    case '0':
                    break;
                    case '1':
                    Node platform = Game.createEntity(j*60, i*60, 60, 60, Color.GRAY,root);
                    platforms.add(platform);
                    break;
                    case '2':
                    Node lift = Game.createEntity(j*60, i*60, 150, 40, Color.WHITE, root);
                    platforms.add(lift);
                    break;
                }
        }
    }
    
    public void moveX(int value, Node entity)
    {
        boolean movingRight = value > 0;

        for(int i = 0; i < Math.abs(value); i++)
        {
            for(Node platform : platforms)
                if(entity.getBoundsInParent().intersects(platform.getBoundsInParent()))
                    if(movingRight){
                        if(entity.getTranslateX() + 40 == platform.getTranslateX())
                            return;
                    }
                    else
                    if(entity.getTranslateX() == platform.getTranslateX() + 60)
                        return;
            entity.setTranslateX(entity.getTranslateX() + (movingRight ? 1 : -1));
        }
    }
    public void moveY(int value, Node entity)
    {
        boolean movingDown = value > 0;

        for(int i = 0; i < Math.abs(value); i++){
            for(Node platform : platforms)
                if(entity.getBoundsInParent().intersects(platform.getBoundsInParent()))
                    if(movingDown){
                        if(entity.getTranslateY() +40 == platform.getTranslateY())
                        {
                            entity.setTranslateY(entity.getTranslateY()-1);
                            Game.setCanJump(true);
                            return;
                        }
                    }
                    else
                    if(entity.getTranslateY() == platform.getTranslateY() + 60)
                        return;
            //gravity
            entity.setTranslateY(entity.getTranslateY() + (movingDown ? 1 : - 1));
        }
    }

    public void jumpPlayer()
    {
        if(canJump)
        {
            playervelocity = playervelocity.add(0,-30);
            Game.setCanJump(false);
        }
    }
}
