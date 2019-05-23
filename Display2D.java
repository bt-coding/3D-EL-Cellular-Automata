import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
public class Display2D extends JComponent {
    private ArrayList<Organism> organisms;
    private int xlen;
    private int ylen;
    private int resolutionx;
    private int resolutiony;
    private double cellsizex;
    private double cellsizey;
    public Display2D(int xl, int yl, int rx, int ry) {
        organisms = new ArrayList<Organism>();
        xlen=xl;
        ylen=yl;
        resolutionx=rx;
        resolutiony=ry;
        cellsizex=((double)resolutionx/(double)xlen);
        cellsizey=((double)resolutiony/(double)ylen);
        (new Thread(new FrameThread2D(60,this))).start();
    }
    public void redraw() {
        this.repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Organism o : organisms) {
            g.setColor(o.getColor());
            g.fillRect((int)(o.getX()*cellsizex), (int)(o.getY()*cellsizey), (int)cellsizex, (int)cellsizey);
            g.setColor(Color.BLACK);
            g.drawRect((int)(o.getX()*cellsizex), (int)(o.getY()*cellsizey), (int)cellsizex, (int)cellsizey);
        }
    }
    public void setOrganism(ArrayList<Organism> ao) {
        organisms=ao;
    }    
}