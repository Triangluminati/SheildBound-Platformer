import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.util.ArrayList;

/**
 * Write a description of class refPlr here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class refPlr extends Actor
{
    public refPlr(){

    }

    public void act()
    {
        checkForPoints();
        if(Greenfoot.isKeyDown("w"))
        {
            move(3);
        }
        if(Greenfoot.isKeyDown("s"))
        {
            move(-3);
        }
        if(Greenfoot.isKeyDown("d"))
        {
            setRotation(getRotation()-3);
        }
        if(Greenfoot.isKeyDown("a"))
        {
            setRotation(getRotation()+3);
        }
    }

    //unused stuff I think
    ArrayList<Object> DISTANCEOBJ = new ArrayList<Object>();
    ArrayList<Integer> HEIGHTS = new ArrayList<Integer>();
    int SCREEN = (int) 500 / 60;

    //Used arraylists and values (some may be unused cause I didn't check thoroughly)
    private boolean rotationCycle = true;
    private boolean check = true;

    ArrayList<Integer> DISTANCEX = new ArrayList<Integer>();
    ArrayList<Integer> DISTANCEY = new ArrayList<Integer>();
    ArrayList<Integer> ROTATIONPOINT = new ArrayList<Integer>();
    ArrayList<Integer> HITX = new ArrayList<Integer>();
    ArrayList<Integer> HITY = new ArrayList<Integer>();

    //gonna be important later so im leaving this is used stuff
    ArrayList<Integer> COLOR = new ArrayList<Integer>();

    
    int x;
    int y;
    int CHECKEDROTATION;
    int ORIGINALROTATION;

    //FOV value (how much will be seen) dont change cause other values have to be changed in correspondance. Also full view is FOV*2
    final int FOV = 80;
    public void checkForPoints()
    {
        //Gets rotation point from 1-120 being seen by actor
        //this value changes and is used in later loops
        CHECKEDROTATION = getRotation()-FOV;
        //this value stays the same and only changes when player rotates
        ORIGINALROTATION = getRotation()-FOV;

        //re-runs line chec until all of FOV (160) points
        while(rotationCycle)
        {
            //checks line untl it hits a refObj (Object)
            while(check)
            {
                //checks new point in a straight line until it is ontop of an object
                if(getWorld().getObjectsAt(getX()+x, getY()+y, refObj.class).size() == 0 && x < 500 && y < 500 && x > -500 && y > -500)
                {
                    double radians = Math.toRadians(CHECKEDROTATION);
                    int dx = (int) Math.round(Math.cos(radians) * 5);
                    int dy = (int) Math.round(Math.sin(radians) * 5);
                    x += dx;
                    y += dy;
                }
                //stores values used later and sets the loop dependance to false if an object is found
                else if(getWorld().getObjectsAt(getX()+x, getY()+y, refObj.class).size() > 0)
                {
                    //print can be uncommented if needed, shows X and Y coordinate that the line is hitting 
                    //System.out.println("HIT OBJECT AT X"+x+"/Y"+y);

                    refObj r = getWorld().getObjectsAt(getX()+x, getY()+y, refObj.class).get(0);

                    //adds the distance from the player to the object when a line finds an object to an arraylist
                    DISTANCEX.add(x);
                    DISTANCEY.add(y);

                    HITX.add(getX()+x);
                    HITY.add(getY()+y);

                    //color purposes will add later B)
                    COLOR.add(r.color);

                    //finds the fov point hit out of 160
                    ROTATIONPOINT.add(CHECKEDROTATION-ORIGINALROTATION);

                    ////DISTANCEOBJ.add(getOneObjectAtOffset(getX()+x, getY()+y, refObj.class));
                    x = 0;
                    y = 0;
                    check = false;
                }
                //breaks out of loop if an object isn't hit and when the point checked is less than (500, 500) and lgreater than (-500, -500)
                else
                {   
                    x = 0;
                    y = 0;
                    break;
                }
            }

            //stops cycle once full FOV check has been completed
            if(CHECKEDROTATION >= getRotation() + FOV)
            //stops current loop
                rotationCycle = false;
            //adds value to variable to check next rotation point
            CHECKEDROTATION++;
            //allows for line loop to be ran again
            check = true;
        }

        //checks arraylist I picked to see if we even need to draw anything on the screen (block has been detected)
        if(DISTANCEX.size() > 0)
        {
            //makes as much of the sceen go blank as I can (some code will remove rest later)
            for(int REMOVED = 166; REMOVED >= 0; REMOVED--)
            {
                pasteImg PASTEDIMAGE = getWorld().getObjects(pasteImg.class).get(REMOVED);
                GreenfootImage img = PASTEDIMAGE.getImage();

                img.setColor(Color.WHITE);
                img.fillRect(0, 0, 8, 500);
                PASTEDIMAGE.setImage(img);

                ////PASTEDIMAGE.clearImage();
                ////getWorld().removeObject(PASTEDIMAGE);
            }
            int ROTATIONREFERENCE = ROTATIONPOINT.size();
            //code here changes objects on the newly blank screen to update object position
            for(int ADDED = ROTATIONPOINT.size()-1; ADDED >= 0; ADDED--)
            {
                ////System.out.println("WORKING");

                //chooses object based off of the fov point hit out of 160
                //basically I put around 160 of these 3d plane (pasteImg.class) and get whichever line hits the object (out of 160 aka FOV*2) VVnext lineVV
                //I chose which line that was and it cooresponds to one of these objects (Some of the are not used since I made world odd nu
                pasteImg p = getWorld().getObjects(pasteImg.class).get(ROTATIONPOINT.get(ADDED));
                ////ROTATIONPOINT.get(ADDED)*((int)500/60), 250, 

                // I used (and had to tweak alot cause it wouldn't work D: ) algorithim from the posts I sent you
                double across = (8 / (double)3 - 0.5);
                double distX = HITX.get(ADDED) - getX();
                double distY = HITY.get(ADDED) - getY();
                ////double dist = Math.sqrt(distX * distX + distY * distY);
                double rayLength = Math.sqrt(distX * distX + distY * distY);
                double planeDist = Math.sqrt(0.5 * 0.5 + across * across);

                int height = (int)(((0.5 * 3) / rayLength) * 3500);

                //plan to hopefully use this for the thing I was asking for help w/
                HEIGHTS.add(height);

                ////GreenfootImage img = new GreenfootImage(8, 100);

                ////int height = (int)((planeDist / dist) * img.getHeight()/2);
                ////System.out.println(height);
                GreenfootImage img = new GreenfootImage(3, height);

                if(COLOR.get(ROTATIONPOINT.size()-ADDED-1) == 0)
                    img.setColor(Color.GREEN);
                else
                {
                    img.setColor(Color.BLACK);
                }
                img.fillRect(0, 0, 3, height);

                p.setImage(img);
                ////getWorld().addObject(new pasteImg(HITX.get(ADDED), HITY.get(ADDED), getX(), getY(), getImage().getHeight()), (ROTATIONPOINT.get(ADDED)*8)+4, MyWorld.Y/2);

            }
        }
        //this is what I was saying will delete the rest of the unremoved objects on screen (for the edges of screen)
        if(DISTANCEX.size() == 0 && getWorld().getObjects(pasteImg.class).size() > 0)
        {
            for(int REMOVED = getWorld().getObjects(pasteImg.class).size()-1; REMOVED >= 0; REMOVED--)
            {
                pasteImg PASTEDIMAGE = getWorld().getObjects(pasteImg.class).get(REMOVED);
                GreenfootImage img = PASTEDIMAGE.getImage();
                img.setColor(Color.WHITE);
                img.fillRect(0, 0, 3, 500);
                PASTEDIMAGE.setImage(img);
                ////PASTEDIMAGE.clearImage();
                ////getWorld().removeObject(PASTEDIMAGE);
            }
            pasteImg PASTEDIMAGE = getWorld().getObjects(pasteImg.class).get(0);
            ////getWorld().removeObject(PASTEDIMAGE);
        }

        //this print can be uncommented out but shows how many points are hitting objects
        //System.out.println("SIZE/"+ROTATIONPOINT.size());

        //clears arraylist and sets booleans to true for when everything is re-run again in the act method
        DISTANCEX.clear();
        DISTANCEY.clear();
        HITX.clear();
        HITY.clear();
        COLOR.clear();
        ROTATIONPOINT.clear();
        rotationCycle = true;
        check = true;
    }
}