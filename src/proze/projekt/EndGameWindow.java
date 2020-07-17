package proze.projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Klasa reprezentująca okno pojawiające się po zakończeniu gry
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */


public class EndGameWindow extends JFrame {
    JButton buttonPlayAgain = new JButton("Zagraj ponownie");
    JButton buttonCancel = new JButton("Wyjdź do menu głównego");
    JLabel scoreText = new JLabel();

    /**
     * Konstruktor klasy
     */
    public EndGameWindow(){
        setNameWindowProperties();
        setButtons();
        setVisible(true);
    }

    /**
     * Ustawia wymiary okna i jego tytuł
     */
    public void setNameWindowProperties(){
        setTitle("Koniec gry!");
        setSize(400,300);
        setLocation(800,400);
        setLayout(new FlowLayout());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Ustawia właściwości przycisków umożliwiających wyjście do menu głównego, lub ponowną rozgrywkę
     */
    public void setButtons(){
        buttonPlayAgain.setPreferredSize(new Dimension(400,100));
        buttonCancel.setPreferredSize(new Dimension(400,100));
        buttonPlayAgain.setFont(new Font("Courier New", Font.BOLD, 24));
        buttonCancel.setFont(new Font("Courier New", Font.BOLD, 24));
        add(scoreText);
        add(buttonPlayAgain);
        add(buttonCancel);
    }

    /**
     * Umieszcza w oknie informację o liczbie zdobytych punktów
     * @param points
     */
    public void setText(int points){
        scoreText.setText("Zdobyłeś " + points + " punktów");
        scoreText.setFont(new Font("Courier New", Font.BOLD, 32));
    }

    /**
     * Realizuje obsługę zdarzeń polegających na wciśnięciu któregoś z dwóch przycisków
     */
    public void buttonActions(){
        buttonPlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                GameBoard gameBoard = new GameBoard();
            }

        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    MainFrame mainFrame = new MainFrame();
                    dispose();
            }
        });

    }
}