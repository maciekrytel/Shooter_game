package proze.projekt;
import javax.swing.SwingUtilities;

/**
*Główna klasa programu
* @author Maciej Rytel, Marcin Skinder
* @version 13.06.2019
*/


public class Gra{

    private static String nazwaPlikuParametrycznego = "par.txt";
    public static Parametry p = new Parametry();

    /**
     * Główna funkcja programu
     * @param args
     */
    public static void main(String[] args) {
        GeneratorPlikuParametrycznegoGry cg = new GeneratorPlikuParametrycznegoGry();
        HighScoreManager hm = new HighScoreManager();
        cg.zapiszPlikParametryczny(nazwaPlikuParametrycznego);
        p.wczytajParametry(nazwaPlikuParametrycznego);
        p.parsowaniePliku();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
