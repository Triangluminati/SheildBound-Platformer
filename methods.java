import greenfoot.*;
public class methods extends Actor
{
    public boolean keyIs(String key)
    {
        if(Greenfoot.isKeyDown(key))
        {
            return true;
        }
        return false;
    }

    public boolean isKey(String key)
    {
        String Key = Greenfoot.getKey();
        if(key.equals(Key)){
            return true;
        }
        return false;
    }
}
