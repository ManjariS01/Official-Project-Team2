import javafx.scene.layout.Pane;
/**
 * Write a description of class Rain02 here.
 *
 * @author Manjari Senthilkumar
 * @version 6/3/2019
 */
public class Rain02 

{
    private Drop d;
    public Rain02(Pane root)
    {
        d = new Drop(360,1000,2,10,root);
    }
    public void fall(){d.fall();}
}
