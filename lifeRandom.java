import java.awt.*;
import javax.swing.*;
import java.util.*;
public class lifeRandom {
    public static void main(String[] args) {
        int[] rules = new int[]{0,1,0,1,0,1,0,0,0};
        int[] spawnrules = new int[]{0,0,1,0,0,0,0,0,0};
        //rules is based on the amount of surrounding cells
        
        for(int i=0;i<rules.length;i++) {
            //rules[i]=(int)(Math.random()+.5);
            rules[i]=0;
            spawnrules[i]=(int)(Math.random()+.5);
        }   
        spawnrules[2]=1;
        rules[1]=1;
        spawnrules[0]=0;
        spawnrules[1]=0;
        
        
        
        JFrame frame = new JFrame("Conway's Window");
        frame.setVisible(true);
        frame.setSize(1920,1080);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int width=100;
        int height=100;
        
        Display d = new Display(height,width,Color.BLACK);
        frame.add(d);
        d.setVisible(true);
        
        
        int frametime = 100;
        int[][] board = new int[height][width];
        //board[25][25]=1;
        //board[26][26]=1;
        //board[27][26]=1;
        //board[27][25]=1;
        //board[27][24]=1;
        /*board[25][25]=1;
        board[26][25]=1;
        board[25][26]=1;
        board[26][26]=1;
        board[25][35]=1;
        board[26][35]=1;
        board[27][35]=1;
        board[24][36]=1;
        board[28][36]=1;
        board[23][37]=1;
        board[23][38]=1;
        board[29][37]=1;
        board[29][38]=1;
        board[26][39]=1;
        board[24][40]=1;
        board[28][40]=1;
        board[25][41]=1;
        board[26][41]=1;
        board[27][41]=1;
        board[26][42]=1;
        board[23][45]=1;
        board[23][46]=1;
        board[24][45]=1;
        board[24][46]=1;
        board[25][45]=1;
        board[25][46]=1;
        board[22][47]=1;
        board[26][47]=1;
        board[21][49]=1;
        board[22][49]=1;
        board[26][49]=1;
        board[27][49]=1;
        board[23][59]=1;
        board[24][59]=1;
        board[23][60]=1;
        board[24][60]=1;*/
        
        for(int r=0;r<board.length;r++) {
            for(int c=0;c<board[0].length;c++) {
                if (Math.random()<.01) {
                    board[r][c]=1;
                }
            }
        }
        /*board[49][50]=1;
        board[50][50]=1;
        board[51][50]=1;
        board[50][49]=1;
        board[50][51]=1;
        */
        for (int i=0;i<10000;i++) {
            if (i%50==0) {
                for(int i2=0;i2<rules.length;i2++) {
                    //rules[i]=(int)(Math.random()+.5);
                    rules[i2]=0;
                    spawnrules[i2]=(int)(Math.random()+.5);
                }   
                spawnrules[2]=1;
                rules[1]=1;
                spawnrules[0]=0;
                spawnrules[1]=0;
                for(int r=0;r<board.length;r++) {
                    for(int c=0;c<board[0].length;c++) {
                        board[r][c]=0;
                    }
                }
                for(int r=0;r<board.length;r++) {
                    for(int c=0;c<board[0].length;c++) {
                        if (Math.random()<.01) {
                            board[r][c]=1;
                        }
                    }
                }
            }
            
            
            //board[(int)(Math.random()*board.length)][(int)(Math.random()*board[0].length)]=1;
            int[][] temp = new int[board.length][board[0].length];
            for (int r=0;r<board.length;r++) {
                for (int c=0;c<board[0].length;c++) {
                    temp[r][c]=board[r][c];
                }
            } //copy to temp board, temp board is used for neighbor computation
            for(int r=1;r<board.length-1;r++) {
                for(int c=1;c<board[0].length-1;c++) {
                    if (board[r][c] == 1) {
                        int total = 0;
                        for (int rr=r-1;rr<=r+1;rr++) {
                            for (int cc=c-1;cc<=c+1;cc++) {
                                total+=temp[rr][cc];
                            }
                        } //gets amount of neighbors
                        total--;
                        /*if (total<2) {
                            board[r][c]=0;
                        } //kill if too few neighbors
                        if (total==2||total==3) {
                            board[r][c]=1;
                        } //leave alive if good amt of neighbors
                        if (total>3) {
                            board[r][c]=0;
                        }*/ //kill if too many neighbors
                        board[r][c]=rules[total];
                    } else if(board[r][c]==0) {
                        int total = 0;
                        for (int rr=r-1;rr<=r+1;rr++) {
                            for (int cc=c-1 ;cc<=c+1;cc++) {
                                total+=temp[rr][cc];
                            }
                        } //amt of neigbors
                        /*if (total==3) {
                            board[r][c]=1;
                        }*/ //make alive if perfect amt of neighbors
                        board[r][c]=spawnrules[total];
                    }
                }
            }
            //System.out.println("FRAME: " + (i+1));
            //System.out.println("FRAME TIME: " + frametime);
            ArrayList<Organism> organisms = new ArrayList<Organism>();
            for(int r=0;r<board.length;r++) {
                for(int c=0;c<board[0].length;c++) {
                    if (board[r][c]==0) {
                        //System.out.print("  ");
                    } else {
                        //System.out.print("O ");
                        organisms.add(new Organism(r,c,new Color((int)(((double)r/(double)board.length)*200)+55,(int)(((double)c/(double)board[0].length)*200)+55,(int)((((double)r*(double)c)/((double)board.length*(double)board[0].length))*255))));
                    }
                }
                System.out.println();
            } //FRAME DRAW
            d.setOrganism(organisms);
            
            try {
                Thread.sleep(frametime);
            } catch (Exception e) {
                e.printStackTrace();
            } //WAIT
            //System.out.print('\u000C'); //CLEAR BLUEJ SCREEN
        }
    }
}