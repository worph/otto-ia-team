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
public interface MMABGame {
        public boolean isEnd();

        public ArrayList<MMABMove> getPossibilities();

        public void play(MMABMove coup);

        public void unPlay(MMABMove coup);

        public int evaluateSituationFor(MMABPlayer Player);
        
}
