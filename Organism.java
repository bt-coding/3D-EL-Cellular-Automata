import java.awt.*;
public class Organism {
    private int x;
    private int y;
    private Color color;
    public Organism() {
        x=0;
        y=0;
        color=Color.BLACK;
    }
    public Organism(int xx, int yy, Color col) {
        x=xx;
        y=yy;
        color=col;
    }
    public void setX(int xx) {
        x=xx;
    }
    public void setY(int yy) {
        y=yy;
    }
    public void setColor(Color col) {
        color=col;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Color getColor() {
        return color;
    }
}