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
   private double posX;
   private double posY;
   private int width;
   private int height;
   private int dVel;
   private Point2D[] vel = new Point2D[300];
   private Node[] drops = new Node[300];
   Physics phys;
   public Rain(Pane groot)
   {
       for(int i = 0; i < drops.length; i++)
       {
           posX = Math.random()*720;
           posY = Math.random()*1000-200;
           vel[i] = new Point2D(0,(int)(Math.random()*2));
           width = (int)(Math.random()*2+2);
           height = (int)(Math.random()*5+10 );
           drops[i] = Game.createEntity((int)posX, (int)posY, width,height,Color.ALICEBLUE, groot);
       }
        phys = new Physics(10,groot,new Point2D(0,0));
   }
   
   public void move()
   {
       for(int i = 0; i < drops.length; i++){
       
            fall(i); //x does not increase in velocity
       phys.moveY((int)vel[i].getY(), drops[i]);
    }
   }
   private void fall(int i)
   {
       vel[i] = vel[i].add(0, dVel);
       //if(posY > 3000)
         //  posY = Math.random()*100-200;
    }
   /*
    public Point2D getVel()
   {
       return vel;
   }
   */

}
