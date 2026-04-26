import greenfoot.*;
import java.util.*;
public class Hero extends methods
{
    private int hearts;
    public Hero()
    {
        hearts = 3;
    }
    public static Platform rightClosestPlatform;
    public static Platform leftClosestPlatform;
    public static Platform closestPlatform;
    private GreenfootImage damage;
    private GreenfootImage DL;
    private GreenfootImage DR;
    private String lastAtkKey;
    private String atk;
    private boolean fallAtk = false;
    private boolean mainAtk = false;
    private boolean hitEnemy = true;
    private int invinciblilityTimer = 0;
    public int hurtFallTimer = 0;
    private int atkInTmr = 20;
    private int atkKeyTmr = 5;
    public int atkTmr = 0;

    public void act()
    {
        if(keyIs("j"))
        {
            System.out.println("y: "+getY());
        }
        if(getWorld()!=null)
        {
            fall();

            closestPlatforms();

            takeDamage();

            attack();
        }
    }

    public void fall()
    {
        Mover m = getWorld().getObjects(Mover.class).get(0);
        if(getOneObjectAtOffset(getImage().getWidth()/2, getImage().getHeight()/2+1, Platform.class)!=null
        ||
        getOneObjectAtOffset(-getImage().getWidth()/2, getImage().getHeight()/2+1, Platform.class)!=null)
        {
            m.fall = false;
        }
        else
        {
            if(m!=null)
            {
                m.fall = true;
            }
        }

    }

    public void takeDamage()
    {
        Enemy e = (Enemy)getOneIntersectingObject(Enemy.class);
        Mover m = getWorld().getObjects(Mover.class).get(0);
        if(hurtFallTimer>0)
        {
            hurtFallTimer--;
        }
        if(isTouching(Enemy.class)&&e.deathTmr==0)
        {
            if(atkTmr==0&&invinciblilityTimer==0)
            {
                GreenfootImage DL = new GreenfootImage("A damage.png");
                GreenfootImage DR = new GreenfootImage("D damage.png");
                m.moveLeft = false;
                m.moveRight = false;
                invinciblilityTimer=150;
                hurtFallTimer = 70;
                m.jumpAcc=-7;
                hearts--;
                if(getOneObjectAtOffset(-getImage().getWidth()/2, 8, Enemy.class)!=null)
                {
                    m.hurtAcc=25;
                    damage = DL;
                    damage.scale(45 , 55);
                }
                if(getOneObjectAtOffset(getImage().getWidth()/2, 8, Enemy.class)!=null)
                {
                    m.hurtAcc=-25;
                    damage = DR;
                    damage.scale(45, 55);
                }
                setImage(damage);
            }
        }
        if(invinciblilityTimer>0)
        {
            invinciblilityTimer--;
        }
        if(hurtFallTimer==0)
        {
            m.moveLeft = true;
            m.moveRight = true;
        }     
    }

    public void closestPlatforms()
    {
        Mover m = getWorld().getObjects(Mover.class).get(0);
        closestPlatform = (Platform)getOneObjectAtOffset(0, m.jumpAcc+getImage().getHeight()/2, Platform.class);
        closestPlatform = (Platform)getOneObjectAtOffset(-getImage().getWidth()/2, m.jumpAcc+getImage().getHeight()/2, Platform.class);
        closestPlatform = (Platform)getOneObjectAtOffset(getImage().getWidth()/2, m.jumpAcc+getImage().getHeight()/2, Platform.class);
        if(m.moveAcc<0&&m.lastKey=="a")
        {
            leftClosestPlatform = (Platform)getOneObjectAtOffset(m.moveAcc-getImage().getWidth()/2, getImage().getHeight()/2, Platform.class);
            leftClosestPlatform = (Platform)getOneObjectAtOffset(m.moveAcc-getImage().getWidth()/2, -getImage().getHeight()/2, Platform.class);
            leftClosestPlatform = (Platform)getOneObjectAtOffset(m.moveAcc-getImage().getWidth()/2, 0, Platform.class);
        }
        else
        {
            leftClosestPlatform = null;
        }
        if(m.moveAcc>0&&m.lastKey=="d")
        {
            rightClosestPlatform = (Platform)getOneObjectAtOffset(m.moveAcc+getImage().getWidth()/2, getImage().getHeight()/2, Platform.class);
            rightClosestPlatform = (Platform)getOneObjectAtOffset(m.moveAcc+getImage().getWidth()/2, -getImage().getHeight()/2, Platform.class);
            rightClosestPlatform = (Platform)getOneObjectAtOffset(m.moveAcc+getImage().getWidth()/2, 0, Platform.class);
        }
        else
        {
            rightClosestPlatform = null;
        }

        if(rightClosestPlatform!=null)
        {
            m.setLocation(m.getX()+m.moveAcc, m.getY());
            m.setLocation(m.getX()+getX()-rightClosestPlatform.getX()+getImage().getWidth()+rightClosestPlatform.getImage().getWidth()/2-15, m.getY());
        }
        if(leftClosestPlatform!=null)
        {
            m.setLocation(m.getX()+m.moveAcc, m.getY());
            m.setLocation(m.getX()+getX()-leftClosestPlatform.getX()-getImage().getWidth()/2-leftClosestPlatform.getImage().getWidth()/2-5, m.getY());
        }

        if(m.hurtAcc<0)
        {
            if(getOneObjectAtOffset(m.hurtAcc-getImage().getWidth()/2, 0, Platform.class)!=null)
            {
                m.setLocation(m.getX()+m.hurtAcc-3, m.getY());
            }
        }
        if(m.hurtAcc>0)
        {
            if(getOneObjectAtOffset(m.hurtAcc+getImage().getWidth()/2, 0, Platform.class)!=null)
            {
                m.setLocation(m.getX()+m.hurtAcc+3, m.getY());
            }
        }    
    }

    public void attack()
    {
        Mover m = getWorld().getObjects(Mover.class).get(0);
        atk = m.lastKey.toUpperCase();

        if(atkTmr>0)
        {
            atkTmr--;
        }        
        if(hurtFallTimer == 0)
        {
            GreenfootImage FA = new GreenfootImage("Fall Main.png");
            FA.scale(35, 50);
            GreenfootImage A1 = new GreenfootImage(atk + " attack1.png");
            A1.scale(35, 50);
            GreenfootImage A2 = new GreenfootImage(atk + " attack2.png");
            A2.scale(40, 50);
            GreenfootImage A3 = new GreenfootImage(atk + " attack3.png");
            A3.scale(43, 50);
            GreenfootImage A4 = new GreenfootImage(atk + " attack4.png");
            A4.scale(47, 50);

            if(atkInTmr>0)
            { 
                atkInTmr--;
            }
            if(atkInTmr==0)
            {
                if((keyIs("e")||keyIs("shift"))&&atkKeyTmr>0)
                {
                    atkTmr = 7;
                    atkKeyTmr--;
                    lastAtkKey = "e";
                }
            }
            if(isKey("e")||isKey("shift"))
            {
                atkKeyTmr=5;
                atkInTmr=15;
            }
            if((keyIs("s")||keyIs("down"))&&Mover.fall&&hitEnemy == false)
            {
                atkTmr = 7;
                fallAtk = true;
                lastAtkKey = "s";
            }  
            if(lastAtkKey == "e")
            {
                if(atkTmr==7)
                {
                    setImage(A1);
                }
                if(atkTmr==6)
                {
                    setImage(A2);
                }
                if(atkTmr==5)
                {
                    setImage(A3);
                }
                if(atkTmr==4)
                {
                    setImage(A4);
                }
            }
            if(lastAtkKey == "s"&&getOneObjectAtOffset(0, getImage().getHeight()/2+2, Platform.class)==null&&fallAtk&&hitEnemy==false)
            {
                setImage(FA);
                atkTmr = 2;
                atkInTmr = 5;
                m.jumpAcc+=2;
            }
            else
            {
                fallAtk = false;
            }
            if(getOneObjectAtOffset(getImage().getWidth()/2, getImage().getHeight()/2+1, Platform.class)!=null&&hitEnemy==true
            ||
            getOneObjectAtOffset(-getImage().getWidth()/2, getImage().getHeight()/2+1, Platform.class)!=null&&hitEnemy==true)
            {
                hitEnemy=false;
            }
            if(isTouching(Enemy.class)&&lastAtkKey == "s"&&atkTmr>0)
            {
                m.jumpAcc=-20;
                fallAtk=false;
                hitEnemy=true;
            }
        }
    }
}
