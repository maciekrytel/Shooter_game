package proze.projekt;
import java.io.Serializable;

/**
 *Klasa służąca do przechowania informacji o imieniu gracza i jego wyniku
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */

public class Score  implements Serializable {
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    /**
     * Konstruktor klasy
     * @param name
     * @param score
     */
    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }
}