import java.awt.*;
import javax.swing.*;
public class Display extends JComponent{
    public Display(){
        
    }
    public void redraw(){
        super.repaint();
    }   
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}