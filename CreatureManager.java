import java.util.*;
public class CreatureManager{
    ArrayList<Creature> creatures;
    int creaturesAtStart;
    int width;
    int length;
    Display dis;
    ArrayList<int[]> food;
    public CreatureManager(int cas,int w, int l, Display d){
        dis=d;
        creatures = new ArrayList<Creature>();
        creaturesAtStart = cas;
        width = w;
        length = l;
        resetCreatures();
        ArrayList<Organism> temporgs = new ArrayList<Organism>();
        for(Creature c : creatures) {
            temporgs.add(new Organism(c.loc[0],c.loc[1],c.color));
        }
        dis.setOrganism(temporgs);
        dis.redraw();
    }
    public void setFood(ArrayList<int[]> f){
        food = f;
    }
    public void resetCreatures(){
        for(int a = 0; a < creaturesAtStart; a++){
            int x = (int)(Math.random()*(width));
            int z = (int)(Math.random()*(length));
            while(contains(new int[]{x,z})){
                x = (int)(Math.random()*(width));
                z = (int)(Math.random()*(length));
            }
            creatures.add(new Creature(Math.random(),Math.random(),5,Math.random(),new int[]{x,z},Math.random()));
        }
    }
    public boolean contains(int[] check){
        for(Creature c: creatures){
            if(c.loc[0] == check[0] && c.loc[1] == check[1]){
                return true;
            }
        }   
        return false;
    }
    /*
    public int[] getInputs(Creature c){
        int[] vals = new int[8];
        for(int a = 1; a < vals.length-1; a++){
            if(){
                
            }
        }
        return vals;
    }
    */
    public void update(){
        
    }
}