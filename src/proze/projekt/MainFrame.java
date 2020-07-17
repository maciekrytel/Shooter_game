package proze.projekt;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Klasa reprezentująca okno z menu głównym
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */

public class MainFrame extends JFrame {

    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JButton buttonBeginGame = new JButton("Rozpocznij grę");
    private JButton buttonBestScores = new JButton("Najlepsze wyniki");
    private JButton buttonTurnOff = new JButton("Wyłącz grę");

    /**
     * Konstruktor klasy
     */
    public MainFrame(){
        setTitle(Parametry.zwrocNazweGry());
        setLayout(new GridLayout(3,1));
        prepareGUI();
        buttonActions();
        setVisible(true);
    }

    /**
     * Metoda budująca okno według metod, których używa
     */
    public void prepareGUI(){
        setMainFrameProperties();
        setButtons();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Metoda ustawiająca tytuł okna i jego rozmiary
     */
    public void setMainFrameProperties(){
        setTitle(Parametry.zwrocNazweGry());
        setLayout(new GridLayout(3,1));
        setBounds(dim.width/3, dim.height/4, Parametry.zwrocSzerokosc(), Parametry.zwrocWysokosc());
    }

    /**
     * Metoda umieszczająca przyciski na oknie
     */
    public void setButtons(){
        buttonBeginGame.setFont(new Font("Courier New", Font.BOLD, 36));
        buttonBestScores.setFont(new Font("Courier New", Font.BOLD, 36));
        buttonTurnOff.setFont(new Font("Courier New", Font.BOLD, 36));
        add(buttonBeginGame);
        add(buttonBestScores);
        add(buttonTurnOff);
        setVisible(true);
    }

    /**
     * Realizuje obsługę zdarzeń polegających na wciśnięciu któregoś z trzech przycisków
     */
    public void buttonActions(){
        buttonBeginGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NameWindow nameWindow = new NameWindow();
                nameWindow.buttonActions();
                dispose();
            }
        });

        buttonBestScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BestScoresWindow bestScoresWindow = new BestScoresWindow();
                bestScoresWindow.buttonActions();
            }
        });

        buttonTurnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }


}
