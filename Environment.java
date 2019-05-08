import java.util.*;
public class Environment{
    double temp;
    int foodSpawnPerUpdate;
    double foodValue;
    // X value
    double width;
    // Z value
    double length;
    int foodAtStart;
    ArrayList<int[]> foodLocs;
    CreatureManager creatureManager;
    public Environment(double t, int fspu, double fv, double w,double l,int fas, CreatureManager cm){
        temp = t;
        foodSpawnPerUpdate = fspu;
        foodValue = fv;
        width = w;
        length = l;
        foodAtStart = fas;
        creatureManager = cm;
        resetBoard();
    }
    public boolean foodLocContains(int[] check){
        for(int[] e: foodLocs){
            if(e[0] == check[0] && e[1] == check[1]){
                return true;
            }
        }
        return false;
    }
    public void resetBoard(){
        for(int a = 0; a < foodAtStart; a++){
            int x = (int)(Math.random()*(width+1));
            int z = (int)(Math.random()*(length+1));
            while(foodLocContains(new int[]{x,z}) || creatureManager.contains(new int[]{x,z})){
                x = (int)(Math.random()*(width+1));
                z = (int)(Math.random()*(length+1));
            }
            foodLocs.add(new int[]{});
        }
    }
    public void updateFood(){
        for(int a = 0; a < foodSpawnPerUpdate; a++){
            int x = (int)(Math.random()*(width+1));
            int z = (int)(Math.random()*(length+1));
            while(foodLocContains(new int[]{x,z})){
                x = (int)(Math.random()*(width+1));
                z = (int)(Math.random()*(length+1));
            }
            foodLocs.add(new int[]{});
        }
    }
}