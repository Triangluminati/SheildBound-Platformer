import greenfoot.*;
public class Mover extends methods
{
    String lastKey="d";
    public static boolean moveRight = true;
    public static boolean moveLeft = true;
    public static boolean fall = false;
    public boolean canMoveDmg = true;
    public boolean hurtFall = false;
    public boolean canFall = false;
    private int spriteTmr = 20;
    private int jumpTmr = 10;
    public int jumpAcc = 0;
    public int moveAcc = 0;
    public int hurtAcc = 0;
    public int initialX;
    public int initialY;

    public void act()
    {
        getImage().setTransparency(0);
        move();
    }

    public void move(){
        Hero h = getWorld().getObjects(Hero.class).get(0);

        sprites();

        vMovement();

        hMovement();
    }

    public void sprites(){
        Hero h = getWorld().getObjects(Hero.class).get(0);

        GreenfootImage D1 = new GreenfootImage("D1.png");
        D1.scale(35, 50);
        GreenfootImage D2 = new GreenfootImage("D2.png");
        D2.scale(35, 50);
        GreenfootImage D3 = new GreenfootImage("D3.png");
        D3.scale(35, 50);
        GreenfootImage D4 = new GreenfootImage("D4.png");
        D4.scale(35, 50);

        GreenfootImage A1 = new GreenfootImage("A1.png");
        A1.scale(35, 50);
        GreenfootImage A2 = new GreenfootImage("A2.png");
        A2.scale(35, 50);
        GreenfootImage A3 = new GreenfootImage("A3.png");
        A3.scale(35, 50);
        GreenfootImage A4 = new GreenfootImage("A4.png");
        A4.scale(35, 50);

        GreenfootImage FA = new GreenfootImage("Fall A.png");
        FA.scale(35, 50);

        GreenfootImage FD = new GreenfootImage("Fall D.png");
        FD.scale(35, 50);

        GreenfootImage IA = new GreenfootImage("Idle A.png");
        IA.scale(35, 50);
        GreenfootImage IA1 = new GreenfootImage("Idle A1.png");
        IA1.scale(35, 50);

        GreenfootImage ID = new GreenfootImage("Idle D.png");
        ID.scale(35, 50);
        GreenfootImage ID1 = new GreenfootImage("Idle D1.png");
        ID1.scale(35, 50);

        GreenfootImage JD1 = new GreenfootImage("Jump D1.png");
        JD1.scale(35, 50);

        GreenfootImage JA1 = new GreenfootImage("Jump A1.png");
        JA1.scale(35, 50);

        if(moveLeft){
            if(keyIs("a")||keyIs("left")){
                if(moveAcc>-10){
                    moveAcc--;
                }
            }
            else if(moveAcc<0){
                moveAcc++;
            }
        }
        else {
            moveAcc = 0;
        }
        if(moveRight){
            if(keyIs("d")||keyIs("right")){
                if(moveAcc<10){
                    moveAcc++;
                }
            }
            else if(moveAcc>0){
                moveAcc--;
            }
        }
        else {
            moveAcc = 0;
        }
        if(keyIs("a")){ 
            lastKey = "a";
        }
        if(keyIs("d")){ 
            lastKey = "d";
        }
        if(keyIs("a")&&keyIs("d")){
            if(moveAcc>0){
                lastKey = "d";
            }
            if(moveAcc<0){
                lastKey = "a";
            }
        }

        if(spriteTmr>0){
            spriteTmr--;
        }
        else {
            spriteTmr = 20;
        }
        if(h.atkTmr==0){
            if(!fall){
                if(moveAcc>0&&moveRight){
                    if(spriteTmr == 5){                 
                        h.setImage(D1);
                    }
                    if(spriteTmr == 10){
                        h.setImage(D2);
                    }
                    if(spriteTmr == 15){
                        h.setImage(D3);
                    }
                    if(spriteTmr == 20){
                        h.setImage(D4);
                    }
                }
                else if(moveAcc<0&&moveLeft){
                    if(spriteTmr == 5){
                        h.setImage(A1);
                    }
                    if(spriteTmr == 10){
                        h.setImage(A2);
                    }
                    if(spriteTmr == 15){
                        h.setImage(A3);
                    }
                    if(spriteTmr == 20){
                        h.setImage(A4);
                    }
                }
                else{
                    if(lastKey == "a"&&h.hurtFallTimer==0){
                        if(spriteTmr == 10){
                            h.setImage(IA);
                        }
                        if(spriteTmr == 20){
                            h.setImage(IA1);
                        }
                    }
                    if(lastKey == "d"&&h.hurtFallTimer==0){
                        if(spriteTmr == 10){
                            h.setImage(ID);
                        }
                        if(spriteTmr == 20){
                            h.setImage(ID1);
                        }
                    }
                }
            }
            else{
                if(jumpAcc>0&&h.hurtFallTimer==0){
                    if(keyIs("a")&&keyIs("d")){
                    }
                    else if(keyIs("a")){
                        h.setImage(FA);
                    }
                    else if(keyIs("d")){
                        h.setImage(FD);
                    }
                }
                else{
                    if(lastKey == "a"&&h.hurtFallTimer==0){
                        h.setImage(JA1);
                    }
                    else if(lastKey == "d"&&h.hurtFallTimer==0){
                        h.setImage(JD1);
                    }
                }
            }
        }
    }

    public void vMovement(){
        Hero h = getWorld().getObjects(Hero.class).get(0);
        GreenfootImage JA = new GreenfootImage("Jump A.png");
        JA.scale(35, 50);
        GreenfootImage JD = new GreenfootImage("Jump D.png");
        JD.scale(35, 50);
        if(fall){
            jumpAcc++;
        }
        else {
            if(jumpTmr>0){
                jumpTmr--;
            }
            else{
                if(h.atkTmr==0){
                    if(keyIs("space")&&h.hurtFallTimer==0){
                        jumpTmr = 10;
                        jumpAcc=-20;
                        if(lastKey == "a"){
                            h.setImage(JA);
                        }
                        if(lastKey == "d"){
                            h.setImage(JD);
                        }
                    }             
                }
            }
            if(jumpAcc>0){
                jumpAcc = 0;
            }
        }
        if(hurtAcc>0){
            hurtAcc--;
        }
        if(hurtAcc<0){
            hurtAcc++;
        }
        if(h.closestPlatform!=null&&jumpAcc>0){
            h.setLocation(h.getX(), h.closestPlatform.getY()-h.closestPlatform.getImage().getHeight()/2-h.getImage().getHeight()/2-1);
        }
        else{
            h.setLocation(h.getX(), h.getY()+jumpAcc);
        }
    }

    public void hMovement(){
        Hero h = getWorld().getObjects(Hero.class).get(0);
        if(getX()<=600){
            setLocation(getX()-moveAcc, getY());
            setLocation(getX()-hurtAcc, getY());
        }
        else if(getX()>=600){
            if(h.getX()+moveAcc<601){
                h.setLocation(h.getX()+moveAcc, h.getY());
                if(h.isAtEdge()){
                    h.setLocation(h.getX()-moveAcc, h.getY());
                }
            }
            else{
                setLocation(getX()-moveAcc, getY());
                if(canMoveDmg){
                    setLocation(getX()-hurtAcc, getY());
                }
            }
        }
    }

    public void scrollMove(){
        Mover m = getWorld().getObjects(Mover.class).get(0);
        setLocation(m.getX()-initialX,m.getY()-initialY);
    }
}

