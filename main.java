import java.awt.*;
import javax.swing.*;
public class main{
    public static void main(String[] args){
        JFrame frame = new JFrame("3D Cellular Automata");
        
        int planeWidth = 100; 
        int planeLength = 100;
        int creaturesAtStart = 500;
        int environmentTemp = 60;
        int foodPerUpdate = 5;
        int foodWorth = 1;
        int foodAtStart = 20;
        
        Display screen = new Display(planeWidth,planeLength,Color.GREEN);
        frame.add(screen);
        
        CreatureManager creatureManager = new CreatureManager(creaturesAtStart,planeWidth,planeWidth,screen);
        Environment environment = new Environment(environmentTemp,foodPerUpdate,foodWorth,planeWidth,planeLength,foodAtStart,creatureManager);
        
        frame.setSize(1920,1080);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}