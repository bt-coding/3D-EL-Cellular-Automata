import java.util.*;
import java.awt.*;
public class Cube {
    ArrayList<OtherPoint> points;
    ArrayList<Vector> vectors;
    ArrayList<Polygon> polygons;
    ArrayList<ZObject> zobjects;
    Color color;
    public Cube(ArrayList<OtherPoint> p, ArrayList<Vector> v, ArrayList<Polygon> po, Color c) {
        points = p;
        vectors = v;
        polygons = po;
        zobjects = new ArrayList<ZObject>();
        /*for (Vector ve : vectors) {
            //zobjects.add(new ZObject(ve.getOne(), ve.getTwo()));
        }*/
        for (Polygon pol : polygons) {
            zobjects.add(new ZObject(pol.getOne(),pol.getTwo(),pol.getThree(),pol.getColor()));
        }
        color=c;
    }
    public void setPoints(ArrayList<OtherPoint> p) {
        points = p;
    }
    public void setVectors(ArrayList<Vector> v) {
        vectors = v;
    }
    public void setPolygons(ArrayList<Polygon> p) {
        polygons = p;
    }
    public ArrayList<OtherPoint> getPoints() {
        return points;
    }
    public ArrayList<Vector> getVectors() {
        return vectors;
    }
    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }
    public ArrayList<ZObject> getZObjects() {
        return zobjects;
    }
    public Color getColor() {
        return color;
    }
}