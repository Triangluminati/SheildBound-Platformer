import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;

/**
 * Write a description of class rayCastPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class rayCastPoint extends Actor
{
    /**
 * NOTHING HERE DOES ANYTHING DON'T EVEN BOTHER LOOKING
 */
    
    int originx;
    int originy;
    public rayCastPoint()
    {
        originy = getImage().getHeight();
        originx = getImage().getWidth();
    }
    GreenfootImage CHANGEDIMAGE;
    public void act()
    {
        refPlr p = getWorld().getObjects(refPlr.class).get(0);
        //setRotation(p.getRotation());
        if(!isAtEdge())
            setLocation(p.getX()+getImage().getWidth()-originx, p.getY()+getImage().getHeight()-originy);
        //else
        //setLocation(getX()+1, getY());

        while(!isTouching(refObj.class)&&!isAtEdge())
        {
            CHANGEDIMAGE = getImage();
            double radians = Math.toRadians(getRotation());
            int dx = (int) Math.round(Math.cos(radians) * 3);
            int dy = (int) Math.round(Math.sin(radians) * 3);
            CHANGEDIMAGE.scale(getImage().getWidth()+3, getImage().getHeight());
            setLocation(getX()+dx, getY()+dy);
            setImage(CHANGEDIMAGE);
        }

        //setLocation(getX()+dx, getY(
        // }
        // else
        // {
        // while(!isTouching(refPlr.class))
        // move(-5);
        // }

    }
}
