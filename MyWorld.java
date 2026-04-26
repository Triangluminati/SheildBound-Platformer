import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    static int X = 500;
    static int Y = 500;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(X, Y, 1); 
        ArrayList<Integer> GRIDX = new ArrayList<Integer>();
        ArrayList<Integer> GRIDY = new ArrayList<Integer>();
        //9X9 placement grid so I can place blocks easily
        for(int x = 25; x < X; x += 50)
        {
            GRIDX.add(x);            
        }
        for(int y = 25; y < Y; y += 50)
        {
            GRIDY.add(y);
        }
        addObject(new refObj(), GRIDX.get(5), GRIDY.get(0));
        addObject(new refObj(), GRIDX.get(5), GRIDY.get(1));
        addObject(new refObj(), GRIDX.get(5), GRIDY.get(2));
        ;
        // addObject(new refObj(), GRIDX.get(9), GRIDY.get(4));
        // addObject(new refObj(), GRIDX.get(9), GRIDY.get(5));
        // addObject(new refObj(), GRIDX.get(9), GRIDY.get(6));
        addObject(new refObj(), GRIDX.get(8), GRIDY.get(4));
        addObject(new refObj(), GRIDX.get(8), GRIDY.get(5));
        addObject(new refObj(), GRIDX.get(8), GRIDY.get(6));
        addObject(new refObj(), GRIDX.get(8), GRIDY.get(7));
        
        addObject(new refObj(), GRIDX.get(6), GRIDY.get(4));
        addObject(new refObj(), GRIDX.get(6), GRIDY.get(5));
        addObject(new refObj(), GRIDX.get(6), GRIDY.get(6));
        addObject(new refObj(), GRIDX.get(6), GRIDY.get(7));
        
        for(int dirx = 0; dirx<9 ; dirx++)
        addObject(new refObj(1), GRIDX.get(dirx), GRIDY.get(0));
        for(int dirx = 0; dirx<9 ; dirx++)
        addObject(new refObj(1), GRIDX.get(dirx), GRIDY.get(8));
        for(int diry = 1; diry<8 ; diry++)
        addObject(new refObj(1), GRIDX.get(0), GRIDY.get(diry));
        for(int diry = 1; diry<8 ; diry++)
        addObject(new refObj(1), GRIDX.get(8), GRIDY.get(diry));
        //adds background to screen so you can get rid of can delete to see stuff behind
        addObject(new BackGround(), X/2, Y/2);
        
        //adds all of the images to draw onto screen so I can change later
        for(int AMOUNT = 500; AMOUNT > 0; AMOUNT -= (int)X/160)
        {
            addObject(new pasteImg(), AMOUNT, Y/2);
        }
        
        addObject(new refPlr(), 100, 100);
        setPaintOrder(pasteImg.class, BackGround.class, refObj.class, refPlr.class);
    }
}
