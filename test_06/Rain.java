import javafx.scene.shape.Line;
import javafx.scene.paint.*;
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
   private double vel;
   
   public Rain()
   {
    }
   
   public void fall()
   {
       posY += vel;
   }
   public void render()
   {
       Line raindrop = new Line(posX, posY, posX + 10, posY + 10);
       raindrop.setStroke(Color.ALICEBLUE);
       raindrop.setStrokeWidth(2);
    }
}
