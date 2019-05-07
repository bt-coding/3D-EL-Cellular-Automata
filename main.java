import java.awt.*;
import javax.swing.*;
public class main{
    public static void main(String[] args){
        JFrame frame = new JFrame("3D Cellular Automata");
        Display screen = new Display();
        frame.add(screen);
        
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}