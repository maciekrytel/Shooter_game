package proze.projekt;

import java.awt.*;

/**
 *Klasa reprezentująca cel w postaci prostokąta, do którego należy strzelić
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */

public class TargetRectangle extends Target {
    /**
     * Metoda rysująca prostokąt
     * @param g
     */
    @Override
    public void drawTarget(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect((int)x, (int)y, super.width, super.height);
        tick();
    }
}
