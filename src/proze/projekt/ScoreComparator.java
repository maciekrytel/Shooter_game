package proze.projekt;

import java.util.Comparator;

/**
 *Klasa dostarczająca metodę porównującą dwa wyniki
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */

public class ScoreComparator implements Comparator<Score> {
    /**
     * Predykat porównujący dwa wyniki punktowe
     * @param score1
     * @param score2
     * @return integer
     */
    public int compare(Score score1, Score score2) {

        int sc1 = score1.getScore();
        int sc2 = score2.getScore();

        if (sc1 > sc2){
            return -1;
        }
        else if (sc1 < sc2){
            return 1;
        }
        else{
            return 0;
        }
    }
}