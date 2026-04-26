import greenfoot.*;
import java.util.*;
public class Platform extends Mover
{
    private String Img;
    private int Height;
    public Platform(int height)
    {
        Height = height;
        Img = "Ground.png";
    }
    public Platform(int height, String img)
    {
        Img = img;
        setImage(img);
        Height = height;
    }
    private String Image;
    private int invls = 1;
    private int listSpot;
    public void act()
    {
        if(invls == 1)
        {
            initialX = 600-getX();
            initialY = 400-getY();
            Image = Img.substring(0, Img.length() - 4);
            invls = 0;
            Hero h = getWorld().getObjects(Hero.class).get(0);   
        }
        if(Height>0)
        {
            getWorld().addObject(new Platform(0, Image+" Under.png"), getX(), getY()+(50*Height));
            Height--;
        }
        scrollMove();
    }
}
