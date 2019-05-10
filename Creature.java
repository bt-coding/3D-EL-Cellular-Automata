import java.awt.*;
public class Creature{
    double energy;
    double aggression;
    double packMantality;
    double damage;
    double fear;
    double foodDesire;
    int[] loc;
    Color color;
    String species;
    public Creature(double a, double pm, double d, double f,int[] l, double fd){
        energy = 20;
        aggression = a;
        packMantality = pm;
        damage = d;
        fear = f;
        loc = l;
        foodDesire = fd;
        color = calulateColor();
    }
    public Color calulateColor(){
        return new Color((int)(255*foodDesire),(int)(255*aggression),(int)(255*packMantality));
    }
    //-1 == no floor 0 == nothing, 1 == food, 2 == enemy, 3 == teammate
    public int[] move(int[] inputs){
        double[] decision = new double[inputs.length-2];
        double total = 0;
        for(int a = 1; a < inputs.length-1; a++){
            if(decision[a] != -1){
                if(inputs[a] == 1){
                    decision[a-1] += foodDesire;
                    total += foodDesire;
                }
                if(inputs[a+1] != 2 && inputs[a] != 2 && inputs[a-1] != 2){
                    decision[a-1] += fear;
                    total += fear;
                }
                if(inputs[a-1] == 3 || inputs[a+1] == 3){
                    decision[a-1] += packMantality;
                    total += packMantality;
                }
                if(inputs[a] == 2){
                    decision[a-1] += aggression;
                    total += aggression;
                }
            }
        }
        for(int a = 0; a < decision.length; a++){
            decision[a] = decision[a]/total;
        }
        double choice = Math.random();
        int[] direction = new int[2];
        for(int a = 0; a < decision.length; a++){
            choice -= decision[a];
            if(choice <= 0){
                direction = convertToDirection(a);
                break;
            }
        }
        return direction;
    }
    //top left corner and go clockwise around
    public int[] convertToDirection(int val){
        if(val == 0){
            return new int[]{-1,-1};
        }
        else if(val == 1){
            return new int[]{0,-1};
        }
        else if(val == 2){
            return new int[]{1,-1};
        }
        else if(val == 3){
            return new int[]{1,0};
        }
        else if(val == 4){
            return new int[]{1,1};
        }
        else if(val == 5){
            return new int[]{0,1};
        }
        else if(val == 6){
            return new int[]{-1,1};
        }
        return new int[]{-1,0};
    }
    public Creature clone(int[] pos){
        return new Creature(aggression,packMantality,damage,fear,pos,foodDesire);
    }
}   