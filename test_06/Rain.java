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
   private int[] velDex;
   private Point2D vel = new Point2D(0,0);
   private Node[] drops = new Node[1000];
   Physics phys;
   public Rain(Pane groot)
   {
       for(int i = 0; i < drops.length; i++)
       {
           posX = Math.random()*720;
           posY = Math.random()*1000;
           velDex[i] = (int)(Math.random()*6+4);
           width = (int)(Math.random()*2+2);
           drops[i] = Game.createEntity((int)posX, (int)posY, width,10,Color.ALICEBLUE, groot);
       }
        phys = new Physics(10,groot,vel);
   }
   
   public void fall()
   {
       for(int i = 0; i < drops.length; i++){
       if(vel.getY() < 10)
            vel = vel.add(0, velDex[i]); //x does not increase in velocity
       phys.moveY((int)vel.getY(), drops[i]);
    }
   }
   public Point2D getVel()
   {
       return vel;
   }

}
