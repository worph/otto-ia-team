/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otto.process.engines;

import otto.process.minMaxAlphaBetaAlgo.MMABGame;
import otto.process.minMaxAlphaBetaAlgo.MMABMove;
import otto.process.minMaxAlphaBetaAlgo.MMABPlayer;
import java.util.ArrayList;

/**
 *
 * @author PIERRE
 */
public class TicTacToeGame implements MMABGame{

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public enum Player implements MMABPlayer {X,O};
        
    public class Move implements MMABMove{
        public int row;
        public int column;

        public Move(int row, int column) {
            this.row = row;
            this.column = column;
        }
        
    }

    public boolean isEnd() {
        return currentResult()!=BoardGlobalState.UNCLEAR;
    }

    public ArrayList<MMABMove> getPossibilities() {
        ArrayList<MMABMove> ret = new ArrayList<MMABMove>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(canPlace(i, j)){
                    ret.add(new Move(i, j));
                }
            }
        }
        return ret;
    }

    public void play(MMABMove coup) {
        Move amove = (Move) coup;
        if(canPlace(amove)){
            board[amove.row][amove.column] = caseOfThisPlayer(currentPlayer);
            if(currentPlayer==Player.O){
                currentPlayer=Player.X;
            }else{
                currentPlayer=Player.O;
            }
        }
    }

    public void unPlay(MMABMove coup) {
        Move amove = (Move) coup;
        board[amove.row][amove.column] = BoardCase.Empty;
        if(currentPlayer==Player.O){
            currentPlayer=Player.X;
        }else{
            currentPlayer=Player.O;
        }
    }

    public int evaluateSituationFor(MMABPlayer player) {
        Player p = (Player) player;
        if(isAWin(p)){
            return 1000;
        }
        if(boardIsFull()){
            return 0;
        }
        return -1000;
    }

    
    public enum BoardGlobalState {X_WIN,O_WIN,DRAW,UNCLEAR};
    public enum BoardCase {X,O,Empty};
    private BoardCase[][] board;
    Player currentPlayer;
    
    public TicTacToeGame(Player whoStarts,TicTacToeGame old) {
        currentPlayer = whoStarts;
        board = new BoardCase[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = old.board[i][j];
            }
        }
    }

    public TicTacToeGame(Player whoStarts) {
        currentPlayer = whoStarts;
        board = new BoardCase[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = BoardCase.Empty;
            }
        }
    }

    private TicTacToeGame(BoardCase[][] board, Player currentPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
    }
   
    
    public String print(){
        String ret="player : "+currentPlayer.toString()+" : \n";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j]==BoardCase.X){
                    ret+="X";
                }else if(board[i][j]==BoardCase.O){
                    ret+="O";
                }else{
                    ret+="_";
                }
            }
            ret+="\n";
        }
        return ret;
    }
    
    // Simple supporting routines
    public final void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[ i][ j] = BoardCase.Empty;
            }
        }
    }

    public boolean boardIsFull() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] == BoardCase.Empty) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Compute static value of current position (win, draw, etc.)
    public BoardGlobalState currentResult() {
        return isAWin(Player.O) ? BoardGlobalState.O_WIN
                : isAWin(Player.X) ? BoardGlobalState.X_WIN
                : boardIsFull() ? BoardGlobalState.DRAW : BoardGlobalState.UNCLEAR;
    }

    private boolean isAWin(Player player) {
        BoardCase side = caseOfThisPlayer(player);
        int row, column;

        /* Look for all in a row */
        for (row = 0; row < 3; row++) {
            for (column = 0; column < 3; column++) {
                if (board[ row][ column] != side) {
                    break;
                }
            }
            if (column >= 3) {
                return true;
            }
        }

        /* Look for all in a column */
        for (column = 0; column < 3; column++) {
            for (row = 0; row < 3; row++) {
                if (board[ row][ column] != side) {
                    break;
                }
            }
            if (row >= 3) {
                return true;
            }
        }

        /* Look on diagonals */
        if (board[ 1][ 1] == side && board[ 2][ 2] == side
                && board[ 0][ 0] == side) {
            return true;
        }

        if (board[ 0][ 2] == side && board[ 1][ 1] == side
                && board[ 2][ 0] == side) {
            return true;
        }

        return false;
    }
    
    /* fonction utiles */
    private BoardCase caseOfThisPlayer(Player player){
        BoardCase side;
        if(Player.O == player){
            side = BoardCase.O;
        }else{
            side = BoardCase.X;
        }
        return side;
    }
    
    private boolean canPlace(Move amove) {
        return canPlace(amove.row,amove.column);
    }

    private boolean canPlace(int row, int column) {
        return board[row][column] == BoardCase.Empty;
    }

}
