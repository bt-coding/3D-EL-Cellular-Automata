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
    private int[] liverules;
    private int[] spawnrules;
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
        g.setColor(Color.BLACK);
        g.fillRect(resolutionx/2-150, 20, 300, 30);
        g.setColor(Color.WHITE);
        Font f = new Font("Monospaced",Font.PLAIN,20);
        g.setFont(f);
        int startx=resolutionx/2-130;
        int starty=40;
        for(int ic : liverules) {
           g.drawString(ic+"",startx,starty);
           startx+=31;
        }
        
        g.setColor(Color.BLACK);
        g.fillRect(resolutionx/2-150, 60, 300, 30);
        g.setColor(Color.WHITE);
        g.setFont(f);
        startx=resolutionx/2-130;
        starty=80;
        for(int ic : spawnrules) {
           g.drawString(ic+"",startx,starty);
           startx+=31;
        }
        
        
    }
    public void setOrganism(ArrayList<Organism> ao, int[] lv, int[] sr) {
        organisms=ao;
        liverules=lv;
        spawnrules=sr;
    }    
}