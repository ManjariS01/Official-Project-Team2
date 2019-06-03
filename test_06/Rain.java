import javafx.scene.shape.Line;
import javafx.scene.paint.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.geometry.Point2D;
/**
 * Write a description of class Drop here.
 *
 * @author Manjari
 * @version 06/2/2019
 */
public class Rain 
{
   //position
   private int posX = 10;
   private int posY = -200;
   private Point2D vel = new Point2D(0,1);
   
   Physics phys;
   public Rain(Pane groot)
   {
       Node drop = Game.createEntity(posX, posY, 2,10,Color.ALICEBLUE, groot);
       phys = new Physics(10,groot,vel);
   }
   
   public void fall()
   {
       //posY += vel.get;
   }
   public void render()
   {
       
    }
}
