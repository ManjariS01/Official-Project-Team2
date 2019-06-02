import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
/**
 * Write a description of class Entity here.
 *
 * @author Manjari
 * @version 06/02/2019
 */
public abstract class Matter
{
    public Node create(double posX, double posY, double width, double height, Color fill, Pane root);

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
