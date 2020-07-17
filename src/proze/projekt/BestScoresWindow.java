package proze.projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Klasa reprezentująca okno z listą najlepszych wyników
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */

public class BestScoresWindow extends JFrame {

    JTextArea textField= new JTextArea();
    private JButton buttonOK = new JButton("OK");

    /**
     * Konstruktor klasy
     */
    public BestScoresWindow(){
        setBestScoresWindowProperties();
        setButtons();
        showBestScores();
        setVisible(true);
    }

    /**
     * Ustawia wymiary okna i jego tytuł
     */
    public void setBestScoresWindowProperties(){
        setTitle("Lista najlepszych wyników");
        setSize(400,300);
        setLocation(800,400);
        setLayout(new FlowLayout(0, 80, 30));
        setResizable(false);
    }

    /**
     * Ustawia właściwości przycisku wyłączającego okno, oraz pola zawierającego listę najlepszych wyników
     */
    public void setButtons(){
        buttonOK.setPreferredSize(new Dimension(150,60));
        buttonOK.setFont(new Font("Courier New", Font.BOLD, 36));
        textField.setBounds(50,100 ,100,100);
        //buttonOK.setBounds(300,300,100,100);
        textField.setFont(new Font("Courier New", Font.BOLD, 24));
        textField.setEditable(false);
        add(textField);
        add(buttonOK);
    }

    /**
     * Umieszcza listę najlepszych wyników w przeznaczonym do tego polu
     */
    public void showBestScores(){
        HighScoreManager hm = new HighScoreManager();
        textField.setText(hm.getHighscoreString());
    }

    /**
     * Realizuje obsługę zdarzenia polegającego na wciśnięciu przycisku zamykającego okno
     */
    public void buttonActions() {
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
