import java.awt.*;

public class Circle
{
    private int x;
    private int y;
    private int size;
    private Color color;
    private int points;
    
    public Circle(int x, int y, int size, Color color, int points)
    {
        this.x=x;
        this.y=y;
        this.size=size;
        this.color=color;
        this.points=points;
    }
    
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getSize()
    {
        return size;
    }
    public Color getColor()
    {
        return color;
    }
    public int getPoints()
    {
        return points;
    }
}