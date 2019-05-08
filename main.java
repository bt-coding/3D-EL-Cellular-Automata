import java.awt.*;
import javax.swing.*;
public class main{
    public static void main(String[] args){
        JFrame frame = new JFrame("3D Cellular Automata");
        Display screen = new Display();
        frame.add(screen);
        int planeWidth = 20; 
        int planeLength = 20;
        int creaturesAtStart = 10;
        int environmentTemp = 60;
        int foodPerUpdate = 5;
        int foodWorth = 1;
        int foodAtStart = 20;
        
        CreatureManager creatureManager = new CreatureManager(creaturesAtStart,planeWidth,planeWidth);
        Environment environment = new Environment(environmentTemp,foodPerUpdate,foodWorth,planeWidth,planeLength,foodAtStart,creatureManager);
        
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}