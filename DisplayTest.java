import java.awt.*;
import javax.swing.*;
import java.util.*;
public class DisplayTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Display Test");
        frame.setVisible(true);
        frame.setSize(1920,1080);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Display d = new Display(20,20,Color.GREEN);
        frame.add(d);
        d.setVisible(true);
        ArrayList<Organism> organismsample = new ArrayList<Organism>();
        for(int i=0;i<20;i++) {
            for(int i2=0;i2<20;i2++) {
                organismsample.add(new Organism(i,i2,new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()))));
            }
        }
        d.setOrganism(organismsample);
        d.redraw();
    }   
}