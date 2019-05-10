import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
public class Display extends JComponent{
    private ArrayList<Organism> organisms;
    private int xlen;
    private int ylen;
    private double rotation;
    private Color groundColor;
    private OtherPoint gp1;
    private OtherPoint gp2;
    private OtherPoint gp3;
    private OtherPoint gp4;
    private final int FOV = 90;
    private final int ASPECT = 1;
    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;
    private final int groundheight = 10;
    private double degrees = 0;
    private ArrayList<ZObject> screenobjects;
    private ArrayList<ZObject> grnd;
    public int framenum;
    public JFrame frame;
    public Display(int xl, int yl, Color gc, JFrame fr){
        organisms = new ArrayList<Organism>();
        xlen=xl;
        ylen=yl;
        rotation=0;
        groundColor=gc;
        gp1 = new OtherPoint(0,groundheight,0);
        gp2 = new OtherPoint(xlen,groundheight,0);
        gp3 = new OtherPoint(0,groundheight,ylen);
        gp4 = new OtherPoint(xlen,groundheight,ylen);
        screenobjects = new ArrayList<ZObject>();
        grnd = new ArrayList<ZObject>();
        ZObject ground = new ZObject(gp2,gp1,gp3,gp4,groundColor);
        grnd.add(ground);
        grnd = MoveCamera(grnd, 'z', (int)(ylen/3));
        grnd = MoveCamera(grnd, 'x', -(int)(xlen/2));
        grnd = MoveCamera(grnd, 'y', 5);
        framenum=0;
        frame=fr;
        
        (new Thread(new FrameThread(60,this))).start();
    }
    public void redraw(){
        super.repaint();
    }
    public void addRotate() {
        degrees+=.005;
        framenum++;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //degrees+=.005;
        //degrees+=.005;
        //framenum++;
        
        //ArrayList<ZObject> screenobjects = new ArrayList<ZObject>();
        
        
        //screenobjects.add(ground);
        
        
        //create cubes corresponding to each object
        //ArrayList<Cube> orgcubes = new ArrayList<Cube>();
        /*for(Organism o : organisms) {
            Cube c = Shapes.genCube(o.getX(), groundheight-1, o.getY(), o.getColor());
            for(ZObject z : c.getZObjects()) {
                screenobjects.add(z);
            }
            orgcubes.add(c);
        }*/
        //Organisms use X and Y, but the Y is actually considered to be Z when in 3D
        
        //create ground plane
        
        
        
        //rotate map and raise camera
        
        /*for(ZObject z : screenobjects) {
            if (z.getType().equals("Polygon")) {
                
            } else if (z.getType().equals("Quad")) {
                
            }
        }*/
        
        
        
        //project and actually display points
        
        ArrayList<ZObject> onscreen = spinCamera(degrees, screenobjects);
        onscreen = tiltCamera(-40, onscreen);
        onscreen = ZBuffer.sortZ(onscreen);
        
        /*ArrayList<ZObject> withground = new ArrayList<ZObject>();
        withground.add(ground);
        withground = MoveCamera(withground, 'z', (int)(ylen));
        withground = MoveCamera(withground, 'x', -10);
        withground = MoveCamera(withground, 'y', 5);
        withground = spinCamera(degrees, withground);
        for(ZObject z : screenobjects) {
            withground.add(z);
        }
        screenobjects=withground;
        */
        ArrayList<ZObject> grnarr = spinCamera(degrees, grnd);
        grnarr = tiltCamera(-40,grnarr);
        ZObject ground = grnarr.get(0);
        
        double[] oneprojg = Calculate.project2Ddouble(new double[]{ground.getQuad().getOne().getX(),ground.getQuad().getOne().getY(),ground.getQuad().getOne().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
        double[] twoprojg = Calculate.project2Ddouble(new double[]{ground.getQuad().getTwo().getX(),ground.getQuad().getTwo().getY(),ground.getQuad().getTwo().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
        double[] threeprojg = Calculate.project2Ddouble(new double[]{ground.getQuad().getThree().getX(),ground.getQuad().getThree().getY(),ground.getQuad().getThree().getSpecialZ(),1},FOV,ASPECT,5.0,100.0);     
        double[] fourprojg = Calculate.project2Ddouble(new double[]{ground.getQuad().getFour().getX(),ground.getQuad().getFour().getY(),ground.getQuad().getFour().getSpecialZ(),1},FOV,ASPECT,5.0,100.0);
        int[] xpg = new int[]{(int)(WIDTH*oneprojg[0]),(int)(WIDTH*twoprojg[0]),(int)(WIDTH*threeprojg[0]),(int)(WIDTH*fourprojg[0])};
        int[] ypg = new int[]{(int)(HEIGHT*oneprojg[1]),(int)(HEIGHT*twoprojg[1]),(int)(HEIGHT*threeprojg[1]),(int)(HEIGHT*fourprojg[1])};
        g.setColor(groundColor);
        g.fillPolygon(xpg,ypg,4);

        
        
        
        
        
        for(ZObject z : onscreen) {
            if (z.getType().equals("Polygon")) {
                double[] oneproj = Calculate.project2Ddouble(new double[]{z.getPolygon().getOne().getX(),z.getPolygon().getOne().getY(),z.getPolygon().getOne().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] twoproj = Calculate.project2Ddouble(new double[]{z.getPolygon().getTwo().getX(),z.getPolygon().getTwo().getY(),z.getPolygon().getTwo().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] threeproj = Calculate.project2Ddouble(new double[]{z.getPolygon().getThree().getX(),z.getPolygon().getThree().getY(),z.getPolygon().getThree().getSpecialZ(),1},FOV,ASPECT,5.0,100.0);
                int[] xp = new int[]{(int)(WIDTH*oneproj[0]),(int)(WIDTH*twoproj[0]),(int)(WIDTH*threeproj[0])};
                int[] yp = new int[]{(int)(HEIGHT*oneproj[1]),(int)(HEIGHT*twoproj[1]),(int)(HEIGHT*threeproj[1])};
                g.setColor(z.getPolygon().getColor());
                g.fillPolygon(xp,yp,3);
                g.setColor(Color.BLACK);
                g.drawPolygon(xp,yp,3);
            } else if (z.getType().equals("Quad")) {
                double[] oneproj = Calculate.project2Ddouble(new double[]{z.getQuad().getOne().getX(),z.getQuad().getOne().getY(),z.getQuad().getOne().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] twoproj = Calculate.project2Ddouble(new double[]{z.getQuad().getTwo().getX(),z.getQuad().getTwo().getY(),z.getQuad().getTwo().getSpecialZ(),1},FOV,ASPECT,0.0,100.0);
                double[] threeproj = Calculate.project2Ddouble(new double[]{z.getQuad().getThree().getX(),z.getQuad().getThree().getY(),z.getQuad().getThree().getSpecialZ(),1},FOV,ASPECT,5.0,100.0);     
                double[] fourproj = Calculate.project2Ddouble(new double[]{z.getQuad().getFour().getX(),z.getQuad().getFour().getY(),z.getQuad().getFour().getSpecialZ(),1},FOV,ASPECT,5.0,100.0);
                int[] xp = new int[]{(int)(WIDTH*oneproj[0]),(int)(WIDTH*twoproj[0]),(int)(WIDTH*threeproj[0]),(int)(WIDTH*fourproj[0])};
                int[] yp = new int[]{(int)(HEIGHT*oneproj[1]),(int)(HEIGHT*twoproj[1]),(int)(HEIGHT*threeproj[1]),(int)(HEIGHT*fourproj[1])};
                g.setColor(z.getQuad().getColor());
                g.fillPolygon(xp,yp,4);
                g.setColor(Color.BLACK);
                g.drawPolygon(xp,yp,4);
            }
        }
        
        //display variable and debug text on screen
        /*try {
            Point p = this.getLocationOnScreen();
        
            Robot robot = new Robot();
            Rectangle rect = new Rectangle(p.x,p.y,1920,1080);
            
            BufferedImage img = robot.createScreenCapture(rect);
            
            File imageFile = new File(frame+".png");
            
            ImageIO.write(img, "jpeg", imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
        /*try {
            BufferedImage img = new BufferedImage(frame.getWidth(),frame.getHeight(),BufferedImage.TYPE_INT_RGB);
            frame.paint(img.getGraphics());
            File outfile = new File(framenum+".png");
            ImageIO.write(img,"png",outfile);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
        
        
    }
    public void setOrganism(ArrayList<Organism> ao) {
        organisms=ao;
        ArrayList<ZObject> tempscreenobjects = new ArrayList<ZObject>();
        //screenobjects = new ArrayList<ZObject>();
        for(Organism o : ao) {
            ArrayList<ZObject> objs = Shapes.genCubeQuads(o.getX(), groundheight-1, o.getY(), o.getColor());
            for(ZObject z : objs) {
                tempscreenobjects.add(z);
            }
        }
        tempscreenobjects = MoveCamera(tempscreenobjects, 'z', (int)(ylen/3));
        tempscreenobjects = MoveCamera(tempscreenobjects, 'x', -(int)(xlen/2));
        tempscreenobjects = MoveCamera(tempscreenobjects, 'y', 5);
        screenobjects=tempscreenobjects;
    }
    public ArrayList<ZObject> MoveCamera(ArrayList<ZObject> objects, char dir, double dis) {
        ArrayList<ZObject> tempzobj = new ArrayList<ZObject>();
        double xdist = 0;
        double ydist = 0;
        double zdist = 0;
        if (dir == 'x') {
            xdist = dis;
        }
        if (dir == 'y') {
            ydist = dis;
        }
        if (dir == 'z') {
            zdist = dis;
        }
        for (ZObject zo : objects) {
            if (zo.getType().equals("Quad")) {
                double[] oreturned = Calculate.translateDouble(zo.getOneList(), xdist, ydist, zdist);            
                double[] twreturned = Calculate.translateDouble(zo.getTwoList(), xdist, ydist, zdist);
                double[] trreturned = Calculate.translateDouble(zo.getThreeList(), xdist, ydist, zdist);   
                double[] freturned = Calculate.translateDouble(zo.getFourList(), xdist, ydist, zdist);
                OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
                OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
                OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
                OtherPoint dpfour = new OtherPoint(freturned[0],freturned[1],freturned[2]);
                tempzobj.add(new ZObject(dpone,dptwo,dpthree,dpfour,zo.getColor(),zo.isTouched()));
            } else if (zo.getType().equals("Polygon")) {
                double[] oreturned = Calculate.translateDouble(zo.getOneList(), xdist, ydist, zdist);            
                double[] twreturned = Calculate.translateDouble(zo.getTwoList(), xdist, ydist, zdist);
                double[] trreturned = Calculate.translateDouble(zo.getThreeList(), xdist, ydist, zdist);
                OtherPoint dpone = new OtherPoint(oreturned[0],oreturned[1],oreturned[2]);
                OtherPoint dptwo = new OtherPoint(twreturned[0],twreturned[1],twreturned[2]);
                OtherPoint dpthree = new OtherPoint(trreturned[0],trreturned[1],trreturned[2]);
                tempzobj.add(new ZObject(dpone,dptwo,dpthree,zo.getColor()));
            }
        }
        return tempzobj;
    }
    public ArrayList<ZObject> spinCamera(double deg, ArrayList<ZObject> objects) {
        double ydist=deg;
        //uble xdist=(int)(xlen/2)-10;
        double xdist=0;
        double zdist=(int)(ylen/2)+ylen/3;
        ArrayList<ZObject> tempzobj = new ArrayList<ZObject>();
        for(ZObject zo : objects) {
            if (zo.getType().equals("Quad")) {
                double[] oreturned = Calculate.rotateDouble(new double[]{zo.getOne().getX()+xdist,zo.getOne().getY(),zo.getOne().getZ()-zdist,1}, 'y', deg);            
                double[] twreturned = Calculate.rotateDouble(new double[]{zo.getTwo().getX()+xdist,zo.getTwo().getY(),zo.getTwo().getZ()-zdist,1}, 'y', deg);
                double[] trreturned = Calculate.rotateDouble(new double[]{zo.getThree().getX()+xdist,zo.getThree().getY(),zo.getThree().getZ()-zdist,1}, 'y', deg);   
                double[] freturned = Calculate.rotateDouble(new double[]{zo.getFour().getX()+xdist,zo.getFour().getY(),zo.getFour().getZ()-zdist,1}, 'y', deg);
                OtherPoint dpone = new OtherPoint(oreturned[0]-xdist,oreturned[1],oreturned[2]+zdist);
                OtherPoint dptwo = new OtherPoint(twreturned[0]-xdist,twreturned[1],twreturned[2]+zdist);
                OtherPoint dpthree = new OtherPoint(trreturned[0]-xdist,trreturned[1],trreturned[2]+zdist);
                OtherPoint dpfour = new OtherPoint(freturned[0]-xdist,freturned[1],freturned[2]+zdist);
                tempzobj.add(new ZObject(dpone,dptwo,dpthree,dpfour,zo.getColor(),zo.isTouched()));
            } else if (zo.getType().equals("Polygon")) {
                double[] oreturned = Calculate.rotateDouble(new double[]{zo.getOne().getX()+xdist,zo.getOne().getY(),zo.getOne().getZ()-zdist,1}, 'y', deg);            
                double[] twreturned = Calculate.rotateDouble(new double[]{zo.getTwo().getX()+xdist,zo.getTwo().getY(),zo.getTwo().getZ()-zdist,1}, 'y', deg);
                double[] trreturned = Calculate.rotateDouble(new double[]{zo.getThree().getX()+xdist,zo.getThree().getY(),zo.getThree().getZ()-zdist,1}, 'y', deg);   
                OtherPoint dpone = new OtherPoint(oreturned[0]-xdist,oreturned[1],oreturned[2]+zdist);
                OtherPoint dptwo = new OtherPoint(twreturned[0]-xdist,twreturned[1],twreturned[2]+zdist);
                OtherPoint dpthree = new OtherPoint(trreturned[0]-xdist,trreturned[1],trreturned[2]+zdist);
                tempzobj.add(new ZObject(dpone,dptwo,dpthree,zo.getColor()));
            }
        }
        return tempzobj;
    }
    public ArrayList<ZObject> tiltCamera(double deg, ArrayList<ZObject> objects) {
        double ydist=deg;
        //uble xdist=(int)(xlen/2)-10;
        double xdist=0;
        double zdist=(int)(ylen/2)+ylen/3;
        ArrayList<ZObject> tempzobj = new ArrayList<ZObject>();
        for(ZObject zo : objects) {
            if (zo.getType().equals("Quad")) {
                double[] oreturned = Calculate.rotateDouble(new double[]{zo.getOne().getX()+xdist,zo.getOne().getY(),zo.getOne().getZ()-zdist,1}, 'x', deg);            
                double[] twreturned = Calculate.rotateDouble(new double[]{zo.getTwo().getX()+xdist,zo.getTwo().getY(),zo.getTwo().getZ()-zdist,1}, 'x', deg);
                double[] trreturned = Calculate.rotateDouble(new double[]{zo.getThree().getX()+xdist,zo.getThree().getY(),zo.getThree().getZ()-zdist,1}, 'x', deg);   
                double[] freturned = Calculate.rotateDouble(new double[]{zo.getFour().getX()+xdist,zo.getFour().getY(),zo.getFour().getZ()-zdist,1}, 'x', deg);
                OtherPoint dpone = new OtherPoint(oreturned[0]-xdist,oreturned[1],oreturned[2]+zdist);
                OtherPoint dptwo = new OtherPoint(twreturned[0]-xdist,twreturned[1],twreturned[2]+zdist);
                OtherPoint dpthree = new OtherPoint(trreturned[0]-xdist,trreturned[1],trreturned[2]+zdist);
                OtherPoint dpfour = new OtherPoint(freturned[0]-xdist,freturned[1],freturned[2]+zdist);
                tempzobj.add(new ZObject(dpone,dptwo,dpthree,dpfour,zo.getColor(),zo.isTouched()));
            } else if (zo.getType().equals("Polygon")) {
                double[] oreturned = Calculate.rotateDouble(new double[]{zo.getOne().getX()+xdist,zo.getOne().getY(),zo.getOne().getZ()-zdist,1}, 'x', deg);            
                double[] twreturned = Calculate.rotateDouble(new double[]{zo.getTwo().getX()+xdist,zo.getTwo().getY(),zo.getTwo().getZ()-zdist,1}, 'x', deg);
                double[] trreturned = Calculate.rotateDouble(new double[]{zo.getThree().getX()+xdist,zo.getThree().getY(),zo.getThree().getZ()-zdist,1}, 'x', deg);   
                OtherPoint dpone = new OtherPoint(oreturned[0]-xdist,oreturned[1],oreturned[2]+zdist);
                OtherPoint dptwo = new OtherPoint(twreturned[0]-xdist,twreturned[1],twreturned[2]+zdist);
                OtherPoint dpthree = new OtherPoint(trreturned[0]-xdist,trreturned[1],trreturned[2]+zdist);
                tempzobj.add(new ZObject(dpone,dptwo,dpthree,zo.getColor()));
            }
        }
        return tempzobj;
    }
}