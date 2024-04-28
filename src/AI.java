import java.io.IOException;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.LinkedList;


//
// Hello! This is Evan! March/27/2024
//

class AI {
    Hashtable<String, matchbox>  memory ;
    int side;

    void printBoard(int[][] board) {
            for(int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    System.out.print(board[r][c] + " ");
                }
                System.out.println();
            }
    }

    public void Testing() {

        int[][] b = Main.Setup();
        matchbox m = new matchbox(b, this.side);
        for (int i = 0; i < m.beads.size(); i++ ) {
            printBoard(m.beads.get(i).board);
        }

        System.out.println("-----");
        int[][] mv = move(b).board;
        printBoard(mv);
    }

    public static void main(String[] args) throws IOException {
        AI ai = new AI(1);
        ai.Testing();
    }

    public static class boardAndMove {
        int[][] board;
        int x1;
        int y1;
        int x2;
        int y2;

        boardAndMove(int[][] b, int x1, int y1, int x2, int y2) {
            this.board = b;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    public class matchbox {

        private String Label;
        private ArrayList<boardAndMove> beads = new ArrayList<>(9);
        private ArrayList<boardAndMove> beadsOriginal;

        private int currentBead = -1;
        private int[][] copyBoard(int[][] in)
        {
            int[][] out = new int[3][3];
            for(int x = 0; x < 3; x++)
            {
                for (int y = 0; y < 3; y++)
                {
                    out[y][x] = in[y][x];
                }
            }
            return out;
        }

        private Boolean boardsequal(int[][] left, int[][]right)
        {
            for(int x = 0; x < 3; x++)
            {
                for (int y = 0; y < 3; y++)
                {
                    if (left[y][x] != right[y][x])
                    {
                        return false;
                    }
                }
            }
            return true;
        }

        public matchbox(int[][] board, int side)
        {
            int direction = 1;
            if (side == 2) {
                direction = -1;
            }

            for (int x = 0; x < 3; x++)
            {
                for(int y = 0; y < 3; y++) // loop through  until you find a piece that belongs to the AI's side
                {
                    if(board[y][x] == side)
                    {
                        for (int i = -1; i <=1; i++) {
                            int x2 = x+i;
                            int y2 = y+direction;
                            if(x2 < 0|| x2 > 2|| y2 < 0 || y2 > 2)
                            {
                                continue;
                            }
                            boardAndMove newBead = new boardAndMove( copyBoard(board), x, y, x+ i, y + direction);
                            move_check(newBead.board, newBead.x1, newBead.y1, newBead.x2, newBead.y2);
                            if(!boardsequal(newBead.board, board))
                            {
                                beads.add(newBead);
                            }

                        }

                    }
                }
            }

            // For restoring content if the matchbox gets empty
            beadsOriginal = new ArrayList<>(beads);
        }

        public boardAndMove pickMove()
        {
            if (beads.isEmpty())  // for safety, if the box runs out of beads
            {
                beads = new ArrayList<>(beadsOriginal);
            }
            currentBead = (int)(Math.random()*beads.size());
            return beads.get(currentBead);
        }

        public void onWon() {
            if (currentBead == -1) {
                return;
            }
            beads.add(beads.get(currentBead));
            currentBead = -1;
        }


        public void onLost() {
            if (currentBead == -1) {
                return;
            }
            beads.remove(currentBead);
            currentBead = -1;
        }
    }

    public AI(int side) {
        memory = new Hashtable<String, matchbox>();
        //memory.put("yeet", new matchbox());
        this.side = side;



    }
    public boardAndMove move(int[][] currentBoard)
    {
        String name = convertBoardToName(currentBoard);
        matchbox currentBox = memory.get(name);
        if(currentBox == null)
        {
            currentBox = new matchbox(currentBoard, side);
            memory.put(name, currentBox);
        }
        return currentBox.pickMove();
    }
    public void onWin() //triggers doubling of successful beads, thus rewarding the ai
    {
        memory.forEach((k,v) -> {
            v.onWon();
                }

        );
    }
    public void onLoss() // triggers removal of failiure beads, thus punishing the ai
    {
        memory.forEach((k,v) -> {
                    v.onLost();
                }

        );
    }

    /*

    Boards are formatted from board to name as thus

    123
    456 --> 123456789
    789
     */

    public String convertBoardToName(int[][] in)
    {
        StringBuilder out = new StringBuilder();
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                out.append(in[y][x]) ;
            }
        }
        return out.toString();
    }
    static void move1(int[][] game,int x,int y){
        game[y][x] = 1;
    }
    static void move2(int[][] game,int x,int y){
        game[y][x] = 2;
    }
    static void replace(int[][] game,int x, int y){
        game[y][x] = 0;
    }
    // Copied from main to make silent and ignore last
    public static boolean move_check(int[][] board, int X1, int Y1, int X2, int Y2){

        if(board[Y1][X1] == board[Y2][X2]){

            return true;
        }
        if((board[Y1][X1] == 2 && Y2 == 2) || (board[Y1][X1] == 1 && Y2 == 0)){
            return true;
        }
        if(X1 == X2 && Y1 == Y2){
            return true;
        }

        // Change from original to skip testing last
        if(board[Y1][X1] == 0){
            return true;
        }

        //second 2 checks

        //checks to make sure you aren't moving more than 1 space at a time
        if(Y2 == (Y1 + 2) || Y2 == (Y1 - 2)){
            return true;
        }

        //sideways check

        //stops the invalid move of going sideways Rhett Ward 02/26/2024
        if(Y1 == Y2){
            return true;
        }

        //checks to make sure you aren't moving diagonally unless you are taking.
        if(board[Y2][X2] == 0){
            if( X2 != X1 ){
                return true;
            }
        }

        //rest of the checks

        //02/27/2024 Rhett Ward work was done to fix IOB errors //fixed and simplified on 02/29/2024 Rhett Ward
        if(board[Y2][X2] == 2 && board[Y1][X1] == 1) { //checks to make sure a piece 1 taking a piece 2 is valid Rhett Ward 02/26/2024
            if(((Y2 == (Y1 + 1))) && (X2 == (X1 + 1)) || ((Y2 == (Y1 + 1))) && (X2 == (X1 - 1))) { // checks for the SOP below 02/29/2024 Rhett Ward
                //do nothing 02/29/2024 Rhett Ward
            }
            else {
                return true;
            }
            try { //catches IOB's without the program stopping 02/29/2024 Rhett Ward
                if (board[Y2][X2] == board[Y1 + 1][X1 - 1]) { // Checks the NW diagonal of selected piece 02/29/2024 Rhett Ward
                    move1(board, X2, Y2);
                    replace(board,X1,Y1);
                    return true;
                }
                else if (board[Y2][X2] == board[Y1 + 1][X1 + 1]) {
                    // checks the NE diagonal of selected piece 02/29/2024 Rhett Ward
                    move1(board, X2, Y2);
                    replace(board,X1,Y1);
                    return true;
                    //this gets repeated inside the catch to make sure that it still gets checked if the check before throws an IOB error 02/29/2024 Rhett Ward
                }
                else { // in the case that you are trying to take a 2 piece using a 1 piece (the initial condition to access these ifs) and neither of the above are true, your move is invalid as you cant take forward only diagonal 02/29/2024 Rhett Ward
                    return true;
                }
            } catch (Exception e) { // catches if the is or else if throws an IOB error (the only error that can happen here, or at least that happened in testing) 02/29/2024 Rhett Ward
                try { //this is to run the second check in the case that the first check threw the IOB error 02/29/2024 Rhett Ward
                    if (board[Y2][X2] == board[Y1 + 1][X1 + 1]) {
                        move1(board, X2, Y2);
                        replace(board,X1,Y1);
                    }
                    else { //repeated as a measure of guaranteeing you cant take forward 02/29/2024 Rhett Ward
                    }
                    return true;
                }
                catch (Exception y){ // empty so that program continues even if IOB's got thrown on both checks 02/29/2024 Rhett Ward
                }
            }
        }


        //modified on 02/27/2024 by Rhett Ward, work was done to fix IOB errors //fixed and simplified on 02/29/2024 Rhett Ward
        if(board[Y2][X2] == 1 && board[Y1][X1] == 2){ // checks to make sure a piece 2 taking a piece 1 is valid Rhett Ward 02/26/2024
            if(((Y2 == (Y1 - 1))) && (X2 == (X1 + 1)) || ((Y2 == (Y1 - 1))) && (X2 == (X1 - 1))) {
                //do nothing 02/29/2024 Rhett Ward
            }
            else {
                return true;
            }
            try { // catches IOB errors 02/29/2024 Rhett Ward
                if (board[Y2][X2] == board[Y1 - 1][X1 - 1]) { // Checks the SW diagonal of the selected piece 02/29/2024 Rhett Ward
                    move2(board, X2, Y2);
                    replace(board,X1,Y1);
                    return true;
                }
                else if (board[Y2][X2] == board[Y1 - 1][X1 + 1]) { // checks the SE diagonal of the selected piece 02/29/2024 Rhett Ward
                    move2(board, X2, Y2);
                    replace(board,X1,Y1);
                    return true;
                    // repeated in catch for same reason as in the check for 1 taking 2 02/29/2024 Rhett Ward
                }
                else { // makes sure you can't take forward 02/29/2024 Rhett Ward
                    return true;
                }
            }
            catch(Exception e){
                try{ // makes sure the second check happens in the case the first check throws the IOB error 02/29/2024 Rhett Ward
                    if (board[Y2][X2] == board[Y1 - 1][X1 + 1]) { // checks SE diagonal of selected piece 02/29/2024 Rhett Ward
                        move2(board, X2, Y2);
                        replace(board,X1,Y1);

                    }
                    else { // repeated to guarantee that you can't take forward 02/29/2024 Rhett Ward
                    }
                    return true;
                }
                catch(Exception y){ // empty so that program continues even if IOB's got thrown on both checks 02/29/2024 Rhett Ward
                }
            }
        }


        //checks deciding which piece gets moved
        if(board[Y1][X1] == 2) {
            move2(board, X2, Y2);
            replace(board,X1,Y1);
        }
        else{
            move1(board, X2, Y2);
            replace(board,X1,Y1);
        }
        return false;
    }

}