package proze.projekt;

import java.util.*;
import java.io.*;

/**
 *Klasa obsługująca listę najlepszych wyników
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */

public class HighScoreManager {
    // An arraylist of the type "score" we will use to work with the scores inside the class
    private ArrayList<Score> scores;

    // The name of the file where the highscores will be saved
    private static final String HIGHSCORE_FILE = "high_scores.dat";

    //Initialising an in and outputStream for working with the file
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    /**
     * Konstruktor klasy
     */
    public HighScoreManager() {
        //initialising the scores-arraylist
        scores = new ArrayList<Score>();
    }

    /**
     * Metoda wczytująca plik z wynikami
     */
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IO Error: " + e.getMessage());
            }
        }
    }

    /**
     * Metoda sortująca wyniki
     */
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }

    /**
     * Metoda uaktualniająca listę najlepszych wyników, jeśli pojawił się wynik lepszy od aktualnie trzeciego na liście
     */
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }

    /**
     * Metoda dodająca wynik uzyskany przez gracza do listy wyników
     * @param name
     * @param score
     */
    public void addScore(String name, int score) {
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
    }

    /**
     * Metoda zwracająca posortowaną listę wyników
     * @return scores
     */
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }

    /**
     * Metoda wybierająca 3 najlepsze wyniki, i zwracająca je w postaci:
     * "numer na liście" "imię" "wynik"
     * @return highscoreString
     */
    public String getHighscoreString() {
        String highscoreString = "";
        int max = 3;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += (i + 1) + ". " + scores.get(i).getName() + " " + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }
}