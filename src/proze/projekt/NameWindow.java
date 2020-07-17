package proze.projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Klasa reprezentująca okno, w którym gracz wpisuje swoje imię
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
*/

public class NameWindow extends JFrame {
    JButton buttonOK = new JButton("OK");
    JButton buttonCancel = new JButton("Anuluj");
    static JTextField textField = new JTextField(23);
    static String name = "";

    /**
     * Konstruktor klasy
     */
    public NameWindow(){
        setNameWindowProperties();
        setTextField();
        setButtons();
        setVisible(true);
    }

    /**
     * Metoda ustawiająca tytuł okna i jego rozmiar
     */
    public void setNameWindowProperties(){
        setTitle("Imię gracza");
        setSize(400,300);
        setLocation(800,400);
        setLayout(new FlowLayout(0,30,60));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Metoda umieszczająca pole tekstowe do wpisania imienia gracza
     */
    public void setTextField(){
        textField.setPreferredSize(new Dimension(300,40));
        textField.setFont(new Font("Courier New", Font.ITALIC, 24));
        add(textField);
    }

    /**
     * Metoda umieszczająca przyciski
     */
    public void setButtons(){
        buttonOK.setPreferredSize(new Dimension(150,60));
        buttonCancel.setPreferredSize(new Dimension(150,60));
        buttonOK.setFont(new Font("Courier New", Font.BOLD, 32));
        buttonCancel.setFont(new Font("Courier New", Font.BOLD, 32));
        add(buttonOK);
        add(buttonCancel);
    }

    /**
     * Metoda zwracająca tekst, który został wpisany w polu tekstowym
     * @return name
     */
    public static String returnName(){
        name = textField.getText();
        return name;
    }
    /**
     * Realizuje obsługę zdarzeń polegających na wciśnięciu któregoś z dwóch przycisków
     */
    public void buttonActions(){
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                MainFrame mainFrame = new MainFrame();
            }

        });

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField.getText().equals("")){
                    buttonOK.setEnabled(false);
                    buttonOK.setEnabled(true);
                }
                else{
                GameBoard gameBoard = new GameBoard();
                dispose();
                }
            }
        });

    }
}

