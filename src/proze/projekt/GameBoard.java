package proze.projekt;

import javax.swing.*;

/**
 *Klasa reprezentująca okno zawierające planszę gry
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */


public class GameBoard extends JFrame {
    /**
     * Konstruktor klasy
     */
    public GameBoard(){
        setGameBoard();
        setVisible(true);
    }

    /**
     * Ustawia wymiary okna i jego tytuł, umieszcza w nim planszę gry
     */
    public void setGameBoard(){
        setTitle(Parametry.zwrocNazweGry());
        setSize(Parametry.zwrocSzerokosc(),Parametry.zwrocWysokosc());
        setLocation(600,200);
        GamePanel gamepanel = new GamePanel();
        add(gamepanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}


