import java.awt.*;
public class Creature{
    double energy;
    double aggression;
    double packMantality;
    double damage;
    double fear;
    double tempResistance;
    int[] loc;
    Color color;
    public Creature(double a, double pm, double d, double f,int[] l, double tr){
        energy = 20;
        aggression = a;
        packMantality = pm;
        damage = d;
        fear = f;
        loc = l;
        tempResistance = tr;
        color = calulateColor();
    }
    public Color calulateColor(){
        return new Color((int)(255*tempResistance),(int)(255*aggression),(int)(255*packMantality));
    }
    public void move(){
        
    }
}   