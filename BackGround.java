import greenfoot.*;
import java.util.*;
public class Background extends methods
{
    private String image;
    public Background(String img)
    {
        setImage(img);
        image = img;
    }
    public Background(String img, GreenfootImage vImg)
    {
        setImage(vImg);
        image = img;
    }
    //ArrayList<Boolean> spawns = new ArrayList<Boolean>();
    private int cloudTmr = 50;
    private int invls = 1;
    public int initialX;
    public void act()
    {
        if(invls == 1)
        {
            initialX = getX();
            invls = 0;
        }
        Mover m = getWorld().getObjects(Mover.class).get(0);
        int mx = m.getX()/500;
        int mxm = mx+initialX;
        setLocation(mxm-30, getY());
        if(image == "CloudLeft.png"||image == "CloudRight.png")
        {
            if(cloudTmr > 0)
            {
                cloudTmr--;
            }
            else
            {
                initialX+=2;
                cloudTmr = 50;
            }
        }
    }
}
