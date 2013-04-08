/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otto.process.engines;

import java.util.ArrayList;
import otto.process.minMaxAlphaBetaAlgo.IMMABGame;
import otto.process.minMaxAlphaBetaAlgo.IMMABMove;
import otto.process.minMaxAlphaBetaAlgo.IMMABPlayer;

/**
 *
 * @author PIERRE
 */
public class TicTacToeGame implements IMMABGame{
    
    public enum BoardGlobalState {X_WIN,O_WIN,DRAW,UNCLEAR};
    public enum BoardCase {X,O,Empty};
    private BoardCase[][] board;
    private Player currentPlayer;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public enum Player implements IMMABPlayer {X,O};
        
    public class Move implements IMMABMove{
        public int row;
        public int column;

        public Move(int row, int column) {
            this.row = row;
            this.column = column;
        }
        
        char toBoardNumber(){
            switch(row*10+column){
                case 00: return '1';
                case 10: return '2';
                case 20: return '3';
                case 01: return '4';
                case 11: return '5';
                case 21: return '6';
                case 02: return '7';
                case 12: return '8';
                case 22: return '9';
            }
            return '1';
        }
    }
    
    public TicTacToeGame(Player whoPlay,TicTacToeGame old) {
        currentPlayer = whoPlay;
        board = new BoardCase[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = old.board[i][j];
            }
        }
    }
    
    public TicTacToeGame(Player whoPlay,String boarddata) {
        if(boarddata.length()==9){
           board[0][0] = charToEnum(boarddata.charAt(0));
           board[1][0] = charToEnum(boarddata.charAt(1));
           board[2][0] = charToEnum(boarddata.charAt(2));
           board[0][1] = charToEnum(boarddata.charAt(3));
           board[1][1] = charToEnum(boarddata.charAt(4));
           board[2][1] = charToEnum(boarddata.charAt(5));
           board[0][2] = charToEnum(boarddata.charAt(6));
           board[1][2] = charToEnum(boarddata.charAt(7));
           board[2][2] = charToEnum(boarddata.charAt(8));
        }
    }
    
    private BoardCase charToEnum(char c){
        if(c=='0'){
            return BoardCase.Empty;
        }else if(c=='1'){
            return BoardCase.O;
        }else if(c=='2'){
            return BoardCase.X;
        }
        return BoardCase.Empty;
    }

    public TicTacToeGame(Player whoPlay) {
        currentPlayer = whoPlay;
        board = new BoardCase[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = BoardCase.Empty;
            }
        }
    }

    private TicTacToeGame(BoardCase[][] board, Player whoPlay) {
        this.board = board;
        this.currentPlayer = whoPlay;
    }

    @Override
    public boolean isEnd() {
        return currentResult()!=BoardGlobalState.UNCLEAR;
    }

    @Override
    public ArrayList<IMMABMove> getPossibilities() {
        ArrayList<IMMABMove> ret = new ArrayList<IMMABMove>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(canPlace(i, j)){
                    ret.add(new Move(i, j));
                }
            }
        }
        return ret;
    }

    @Override
    public void play(IMMABMove coup) {
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

    @Override
    public void unPlay(IMMABMove coup) {
        Move amove = (Move) coup;
        board[amove.row][amove.column] = BoardCase.Empty;
        if(currentPlayer==Player.O){
            currentPlayer=Player.X;
        }else{
            currentPlayer=Player.O;
        }
    }

    @Override
    public int evaluateSituationFor(IMMABPlayer player) {
        Player p = (Player) player;
        if(isAWin(p)){
            return 1000;
        }
        if(boardIsFull()){
            return 0;
        }
        return -1000;
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
