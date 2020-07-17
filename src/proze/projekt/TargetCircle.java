package proze.projekt;

import java.awt.*;

/**
 *Klasa reprezentująca cel w postaci kółka, do którego należy strzelić
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */


public class TargetCircle extends Target {
    /**
     * Metoda rysująca kółko
     * @param g
     */
    @Override
    public void drawTarget(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval((int)x, (int)y, super.width, super.width);
        tick();
    }
}
