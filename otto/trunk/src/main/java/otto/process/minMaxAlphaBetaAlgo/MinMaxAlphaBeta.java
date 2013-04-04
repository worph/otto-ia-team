/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otto.process.minMaxAlphaBetaAlgo;

import otto.process.engines.TicTacToeGame;

/**
 *
 * @author PIERRE
 */
public class MinMaxAlphaBeta {

    public static MMABMove calcIA(MMABGame jeu, MMABPlayer player, int prof) {
        int tmp;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        MMABMove bestCoup = null;
        //Si la profondeur est nulle ou la partie est finie, 
        //on ne fait pas le calcul
        if ((prof != 0) || (!jeu.isEnd())) {
            //On parcourt les cases du morpion
            for (MMABMove coup : jeu.getPossibilities()) {
                //Si la case est vide
                //On simule qu'on joue cette case
                jeu.play(coup);
                //On appelle la fonction calcMin pour lancer l'IA
                tmp = calcMin(jeu,player, prof - 1, alpha, beta);

                //Si ce score est plus grand
                if (alpha < tmp) {
                    //On le choisit
                    alpha = tmp;
                    bestCoup = coup;
                }

                //On annule le coup
                jeu.unPlay(coup);
            }
        }
        return bestCoup;
    }

    private static int calcMin(MMABGame jeu, MMABPlayer player, int prof, int alpha, int beta) {
        int tmp;

        //Si on est à la profondeur voulue, on retourne l'évaluation
        //Si la partie est finie, on retourne l'évaluation de jeu
        if (prof == 0 || jeu.isEnd()) {
            return jeu.evaluateSituationFor(player);
        }

        //On parcourt le plateau de jeu et on le joue si la case est vide
        for (MMABMove coup : jeu.getPossibilities()){
            //On joue le coup
            jeu.play(coup);
            tmp = calcMax(jeu,player, prof - 1, alpha, beta);
            //On annule le coup
            jeu.unPlay(coup);
            //Mis a jour de beta
            if (beta > tmp) {
                beta = tmp;
            }
            if (beta <= alpha) {
                return beta;
            }
        }
        return beta;
    }

    private static int calcMax(MMABGame jeu,MMABPlayer player, int prof, int alpha, int beta) {
        int tmp;

        //Si on est à la profondeur voulue, on retourne l'évaluation
        //Si la partie est finie, on retourne l'évaluation de jeu
        if (prof == 0 || jeu.isEnd()) {
            return jeu.evaluateSituationFor(player);
        }

        //On parcourt le plateau de jeu et on le joue si la case est vide
        for (MMABMove coup : jeu.getPossibilities()) {
            //On joue le coup
            jeu.play(coup);
            tmp = calcMin(jeu,player, prof - 1, alpha, beta);
            jeu.unPlay(coup);

            //Mis a jour de la valeur de alpha
            if (alpha < tmp) {
                alpha = tmp;
            }
            if (beta <= alpha) {
                return alpha;
            }
        }
        return alpha;
    }
}
