/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otto.process.minMaxAlphaBetaAlgo;

import java.util.ArrayList;

/**
 *
 * @author PIERRE
 */
public interface IMMABGame {
        public boolean isEnd();

        public ArrayList<IMMABMove> getPossibilities();

        public void play(IMMABMove coup);

        public void unPlay(IMMABMove coup);

        public int evaluateSituationFor(IMMABPlayer Player);
        
}
