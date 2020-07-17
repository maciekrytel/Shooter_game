package proze.projekt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 *Klasa abstrakcyjna reprezentująca cel, do którego należy strzelić
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */

public abstract class Target{
    double x = 0, y = 0;
    int width = 0, height = 0;
    static double velocity = 0.1;
    Random rand = new Random();

    /**
     * Konstruktor klasy
     */
    public Target(){
        this.width = (int)(Parametry.zwrocSzerokosc()
                * Parametry.zwrocPoczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy() /100);
        this.height = 2 * (int)(Parametry.zwrocSzerokosc()
                * Parametry.zwrocPoczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy() /100);
        this.x = Math.abs(rand.nextInt(Parametry.zwrocSzerokosc()) - width);
        this.y = y;
    }

    /**
     * Metoda abstrakcyjna
     * @param g
     * @throws IOException
     */
    public abstract void drawTarget(Graphics g) throws IOException;



    public void tick(){
        y = y + velocity;
    }

}
