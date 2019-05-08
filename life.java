public class life {
    public static void main(String[] args) {
        int frametime = 100;
        int[][] board = new int[55][100];
        //board[25][25]=1;
        //board[26][26]=1;
        //board[27][26]=1;
        //board[27][25]=1;
        //board[27][24]=1;
        board[25][25]=1;
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
        board[24][60]=1;
        for (int i=0;i<10000;i++) {
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
                        if (total<2) {
                            board[r][c]=0;
                        } //kill if too few neighbors
                        if (total==2||total==3) {
                            board[r][c]=1;
                        } //leave alive if good amt of neighbors
                        if (total>3) {
                            board[r][c]=0;
                        } //kill if too many neighbors
                    } else if(board[r][c]==0) {
                        int total = 0;
                        for (int rr=r-1;rr<=r+1;rr++) {
                            for (int cc=c-1 ;cc<=c+1;cc++) {
                                total+=temp[rr][cc];
                            }
                        } //amt of neigbors
                        if (total==3) {
                            board[r][c]=1;
                        } //make alive if perfect amt of neighbors
                    }
                }
            }
            System.out.println("FRAME: " + (i+1));
            System.out.println("FRAME TIME: " + frametime);
            for(int r=0;r<board.length;r++) {
                for(int c=0;c<board[0].length;c++) {
                    if (board[r][c]==0) {
                        System.out.print("  ");
                    } else {
                        System.out.print("O ");
                    }
                }
                System.out.println();
            } //FRAME DRAW
            try {
                Thread.sleep(frametime);
            } catch (Exception e) {
                e.printStackTrace();
            } //WAIT
            System.out.print('\u000C'); //CLEAR BLUEJ SCREEN
        }
    }
}