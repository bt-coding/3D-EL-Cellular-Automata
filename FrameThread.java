import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
public class FrameThread implements Runnable {
    int fps;
    Display dis;
    long msdelay;
    public FrameThread(int f, Display d) {
        fps=f;
        dis=d;
    }
    public FrameThread(Display d) {
        fps=60;
        dis=d;
    }
    public void run() {
        msdelay=1000/fps;
        while(true) {
            try {
                Thread.sleep(msdelay);
            } catch (Exception e) {
                System.out.println("Error occured sleeping FPS thread");
            }
            dis.redraw();
            /*try {
                BufferedImage img = new BufferedImage(dis.frame.getWidth(),dis.frame.getHeight(),BufferedImage.TYPE_INT_RGB);
                dis.frame.paint(img.getGraphics());
                File outfile = new File(dis.framenum+".png");
                ImageIO.write(img,"png",outfile);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }
    }
}