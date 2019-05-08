import java.util.*;
public class CreatureManager{
    ArrayList<Creature> creatures;
    int creaturesAtStart;
    int width;
    int length;
    Display dis;
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
    public void resetCreatures(){
        for(int a = 0; a < creaturesAtStart; a++){
            int x = (int)(Math.random()*(width+1));
            int z = (int)(Math.random()*(length+1));
            while(contains(new int[]{x,z})){
                x = (int)(Math.random()*(width+1));
                z = (int)(Math.random()*(length+1));
            }
            creatures.add(new Creature(0.3,0.4,5,0.2,new int[]{x,z},0.3));
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
}