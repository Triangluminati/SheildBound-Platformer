import greenfoot.*;
public class Enemy extends Mover
{
    private int health = 1;
    private int sizeM = 1;
    public Enemy()
    {
    }
    public Enemy(int sizeMulti, int Health)
    {
        sizeM = sizeMulti;
        health = Health;
    }
    public String direction = "Right";
    private int invincWaves = 0;
    private int spriteTmr = 40;
    public int deathTmr = 0;
    private int hSpeed = 2;
    private int invls = 1;    
    public void act()
    {
        if(invls == 1)
        {
            initialX = 600-getX();
            initialY = 400-getY();
            invls = 0; 
        }

        eTakeDamage();

        spritesAndMovement();        
    }

    public void eTakeDamage()
    {
        Hero h = getWorld().getObjects(Hero.class).get(0);
        GreenfootImage DT1 = new GreenfootImage("death1.png");
        DT1.scale(36*sizeM, 36*sizeM);
        GreenfootImage DT2 = new GreenfootImage("death2.png");
        DT2.scale(36*sizeM, 36*sizeM);
        GreenfootImage DT3 = new GreenfootImage("death3.png");
        DT3.scale(36*sizeM, 36*sizeM);
        if(isTouching(Hero.class)&&h.atkTmr>0&&invincWaves == 0)
        {
            deathTmr=20;
            if(sizeM == 3)
            {
                invincWaves = Greenfoot.getRandomNumber(9)+1;
                hSpeed = 5;
            }
        }
        if(deathTmr>0)
        {
            deathTmr--;
            if(health>0)
            {
                if(deathTmr == 18)
                {
                    health--;
                }
            }
            else
            {
                if(deathTmr == 15)
                {
                    setImage(DT1);
                }
                if(deathTmr == 10)
                {
                    setImage(DT2);
                }
                if(deathTmr == 5)
                {
                    setImage(DT3);
                }
                if(deathTmr == 1)
                {
                    getWorld().removeObject(this);
                }
            }
        }
    }

    public void spritesAndMovement()
    {
        if(getWorld()!=null)
        {
            scrollMove();
            if(deathTmr==0)
            {
                GreenfootImage ER1 = new GreenfootImage("Enemy Right 1.png");
                ER1.scale(45*sizeM, 20*sizeM);
                GreenfootImage ER2 = new GreenfootImage("Enemy Right 2.png");
                ER2.scale(45*sizeM, 20*sizeM);
                GreenfootImage ER3 = new GreenfootImage("Enemy Right 3.png");
                ER3.scale(45*sizeM, 20*sizeM);

                GreenfootImage EL1 = new GreenfootImage("Enemy Left 1.png");
                EL1.scale(45*sizeM, 20*sizeM);
                GreenfootImage EL2 = new GreenfootImage("Enemy Left 2.png");
                EL2.scale(45*sizeM, 20*sizeM);
                GreenfootImage EL3 = new GreenfootImage("Enemy Left 3.png");
                EL3.scale(45*sizeM, 20*sizeM);

                getImage().scale(45*sizeM, 20*sizeM);

                if(isInGround())
                {
                    initialY++;
                }
                if(!isOnGround())
                {
                    initialY-=3;
                }

                if(direction == "Right")
                {
                    if(canPatrol())
                    {
                        direction = "Left";
                        if(invincWaves>0)
                        {
                            invincWaves--;
                        }
                        if(invincWaves == 0)
                        {
                            hSpeed = 2;
                        }
                    }
                    else
                    {
                        if(spriteTmr>5)
                        {
                            initialX-=hSpeed*sizeM;
                        }
                    }
                    if(spriteTmr>0)
                    {
                        spriteTmr--;
                    }
                    else
                    {
                        spriteTmr = 40;
                    }
                    if(spriteTmr == 40)
                    {
                        setImage(ER3);
                    }
                    else if(spriteTmr == 30)
                    {
                        setImage(ER1);
                    }
                    else if(spriteTmr == 20)
                    {
                        setImage(ER2);
                    }
                    else if(spriteTmr == 10)
                    {
                        setImage(ER1);
                    }
                }
                else if(direction == "Left")
                {
                    if(canPatrol())
                    {
                        direction = "Right";
                        if(invincWaves>0)
                        {
                            invincWaves--;
                        }
                        if(invincWaves == 0)
                        {
                            hSpeed = 2;
                        }
                    }
                    else
                    {
                        if(spriteTmr>5)
                        {
                            initialX+=hSpeed*sizeM;
                        }
                    }
                    if(spriteTmr>0)
                    {
                        spriteTmr--;
                    }
                    else
                    {
                        spriteTmr = 40;
                    }
                    if(spriteTmr == 40)
                    {
                        setImage(EL3);
                    }
                    else if(spriteTmr == 30)
                    {
                        setImage(EL1);
                    }
                    else if(spriteTmr == 20)
                    {
                        setImage(EL2);
                    }
                    else if(spriteTmr == 10)
                    {
                        setImage(EL1);
                    }
                }
            }
        }
    }

    public boolean isInGround()
    {
        if(getOneObjectAtOffset(0, getImage().getHeight()/2-1, Platform.class)!=null)
        {
            return true;
        }
        return false;
    }

    public boolean isOnGround()
    {
        if(getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class)!=null)
        {
            return true;
        }
        return false;
    }

    public boolean canPatrol()
    {
        if(direction == "Left")
        {
            if(getOneObjectAtOffset(-getImage().getWidth()/2-2, getImage().getHeight()/2+2, Platform.class)==null)
            {
                return true;
            }
            if(getOneObjectAtOffset(-getImage().getWidth()/2-2, 0, Platform.class)!=null)
            {
                return true;
            }
        }
        if(direction == "Right")
        {
            if(getOneObjectAtOffset(getImage().getWidth()/2+2, getImage().getHeight()/2+2, Platform.class)==null)
            {
                return true;
            }
            if(getOneObjectAtOffset(getImage().getWidth()/2+2, 0, Platform.class)!=null)
            {
                return true;
            }
        }
        return false;
    }
}
