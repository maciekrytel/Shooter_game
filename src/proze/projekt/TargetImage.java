package proze.projekt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *Klasa reprezentująca cel w postaci obrazka, do którego należy strzelić
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */

public class TargetImage extends Target {
    static double velocity = 0.3;

    public void tick(){
        y = y + velocity;
    }


    public Image getTargetImage(){
        ImageIcon i = new ImageIcon(getClass().getResource(Parametry.zwrocPlikObiektuGry()));
        return i.getImage();
    }

    /**
     * Metoda rysująca cel będący obrazkiem
     * @param g
     * @throws IOException
     */
    @Override
    public void drawTarget(Graphics g) throws IOException {
        Image resizedImage = getTargetImage().getScaledInstance(
                (int)Parametry.zwrocPoczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy() *
                        Parametry.zwrocSzerokosc()
                        / 100,
                (int)Parametry.zwrocPoczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy() *
                        Parametry.zwrocSzerokosc()
                        / 100,
                    Image.SCALE_DEFAULT);

        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);

        g.drawImage(resizedImageIcon.getImage(),(int)x, (int)y,null);
        tick();


    }
}

