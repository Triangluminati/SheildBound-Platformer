import greenfoot.*;
public class MainLevel extends World
{
    public MainLevel()
    {    
        super(1200, 700, 1, false);
        setBackground("Background.png");
        for(int i = 164; i<92+368*10; i+=368){
            addObject(new Background("bgTrees.png"), i, 580);
        }
        
        addObject(new Hero(), 600, 550);

        addObject(new Mover(), 600, 400);
        for(int i = 0; i<950; i+=50){
            addObject(new Platform(2), i, 600);
        }

        for(int i = 1000; i<1500; i+=50){
            addObject(new Platform(8), i, 300);
        }
        addObject(new Platform(8,"Ground Left.png"), 950, 300);
        addObject(new Platform(8, "Ground Right.png"), 1500, 300);
        for(int i = 1550; i<2750; i+=50){
            addObject(new Platform(3), i, 550);
        }
        // for(int i = 1550; i<2450; i+=50){
        // addObject(new Platform(2), i, 600);
        // }
        addObject(new Platform(5), 2500, 450);
        addObject(new Platform(5,"Ground Left.png"), 2450, 450);

        addObject(new Platform(5), 2550, 450);
        addObject(new Platform(5), 2600, 450);
        addObject(new Platform(5), 2650, 450);
        addObject(new Platform(5, "Ground Right.png"), 2700, 450);
        addObject(new Platform(0, "Ground Small.png"), 700, 470);
        addObject(new Platform(0, "Ground Small Left.png"), 650, 470);
        addObject(new Platform(0, "Ground Small Right.png"), 750, 470);
        for(int i = 2750; i<2950; i+=50){
            addObject(new Platform(4), i, 500);
        }
        addObject(new Platform(5, "Ground Right.png"), 2950, 500);
        for(int i = 3000; i<4350; i+=50){
            addObject(new Platform(1), i, 650);
        }
        addObject(new Platform(0, "Ground Small Left.png"), 3200 ,350);
        addObject(new Platform(0, "Ground Small.png"), 3250 ,350);
        addObject(new Platform(0, "Ground Small.png"), 3300 ,350);
        addObject(new Platform(0, "Ground Small.png"), 3350 ,350);
        addObject(new Platform(0, "Ground Small Right.png"), 3400 ,350);

        addObject(new Platform(0, "Ground Small Left.png"), 3750 ,250);
        addObject(new Platform(0, "Ground Small.png"), 3800 ,250);
        addObject(new Platform(0, "Ground Small.png"), 3850 ,250);
        addObject(new Platform(0, "Ground Small.png"), 3900 ,250);
        addObject(new Platform(0, "Ground Small.png"), 3950 ,250);
        addObject(new Platform(0, "Ground Small.png"), 4000 ,250);
        addObject(new Platform(0, "Ground Small.png"), 4050 ,250);
        addObject(new Platform(0, "Ground Small Right.png"), 4100 ,250);

        addObject(new Platform(8,"Ground Left.png"), 4350, 300);
        for(int i = 4400; i<4600; i+=50){
            addObject(new Platform(8), i, 300);
        }
        addObject(new Platform(8, "Ground Right.png"), 4600, 300);

        for(int i = 4650; i<5500; i+=50){
            addObject(new Platform(5), i, 450);
        }
        addObject(new Platform(0, "Ground Small.png"), 5200, 300);
        addObject(new Platform(0, "Ground Small Right.png"), 5250, 300);
        addObject(new Platform(0, "Ground Small Left.png"), 5150, 300);
        addObject(new Platform(15, "Ground Left.png"), 5400, 130);
        addObject(new Platform(15), 5450, 130);
        addObject(new Platform(15, "Ground Right.png"), 5500, 130);

        for(int i = 5550; i<7050; i+=50){
            addObject(new Platform(2), i, 600);
        }

        addObject(new Enemy(), 2100, 515);
        addObject(new Enemy(), 1000, 240);
        addObject(new Enemy(), 3300, 310);
        addObject(new Enemy(), 5450, 80);
        addObject(new Enemy(3, 5), 5900, 500);
    }
}