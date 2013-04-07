/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otto.process.engines;

import otto.process.dto.EngineRequestDTO;
import otto.process.dto.EngineResultDTO;
import otto.process.engines.TicTacToeGame.Move;
import otto.process.minMaxAlphaBetaAlgo.MinMaxAlphaBeta;

/**
 *
 * @author PIERRE
 */
public class EngineTicTacToe implements Engine{

    @Override
    public EngineResultDTO process(EngineRequestDTO req) {
        //convention joueur 1 = X joueur 2 = O
        TicTacToeGame.Player iaplayer;
        if(req.getTurn()%2==0){//pair
            iaplayer = TicTacToeGame.Player.O;
        }else{//impaire
            iaplayer = TicTacToeGame.Player.X;
        }
        TicTacToeGame ticTacToeGame = new TicTacToeGame(iaplayer, req.getTray());
        Move coup = (Move) new MinMaxAlphaBeta().calcIA(ticTacToeGame,iaplayer, 100);
        return new EngineResultDTO(Character.toString(coup.toBoardNumber()));
    }
    
}
