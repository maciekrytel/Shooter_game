package proze.projekt;

import java.io.FileReader;
import java.util.Properties;

/**
 * Klasa przechowująca główny parametr gry. Singleton. Nie wolno samodzielnie modyfikować
 * tej klasy poza zmianą:
 * <ol>
 * <li>kodowania,</li>
 * <li>zdania pakietyzacji,</li>
 * <li>komentarzy,</li>
 * <li>ścieżki do pliku {@code ./pin.txt}.</li>
 * </ol>
 *
 * @author kmi
 * @version 1.0.0  6 marca 2019 04:11
 */
public enum PIN {
    /**
     * Singleton tej klasy.
     */
    PIN;

    /**
     * Pin gry.
     */
    final public int pin;

    /**
     * Autorzy całego projektu.
     */
    final public String autorzy;

    /**
     * Własności wczytane z pliku pin.txt.
     */
    private Properties wlasnosci = null;

    /**
     * Konstruktor wczytujący pin. Od razu uruchamia leniwą inicjację.
     */
    private PIN() {
        initializeLazy();
        String pin$ = null;
        try {
            pin$ = wlasnosci.getProperty("pin").trim();
            Integer.parseInt(pin$);
        } catch (Throwable thr) {
            wypiszKomunikatyIZakoncz("Błąd parsowania pinu", "pin=" + pin$, thr);
        }
        pin = Integer.parseInt(pin$);
        if (pin < 1000 | pin > 9999) { // dlaczego tu jest | a nie || ?!
            wypiszKomunikatyIZakoncz("Pin poza dopuszczalnym zakresem", pin);
        }
        autorzy = wlasnosci.getProperty("autorzy").trim();
    }

    /**
     * Leniwe ładowanie pliku własności. Wzorzec leniwej inicjacji. Metoda powinna
     * być użyta dopiero w momencie potrzeby pierwszego skorzystania z własności
     * tu przechowywanych, ponieważ jednak projekt nie może działać bez pinu - metoda
     * jest wywoływana od razu w konstruktorze. Naśladuj leniwą inicjację - inicjuj
     * obiekty dopiero wtedy, gdy to jest potrzebne. Inicjacja rozumiana jest tu jako
     * odwołanie się do głębszych warstw np. systemu operacyjnego, systemu plików, bazy
     * danych itp. W tym przypadku jest to odczyt pliku.
     */
    private void initializeLazy() {
        if (wlasnosci == null) {
            try (FileReader fr = new FileReader("D:/Program files/IdeaProjects/proze-iii-etap/src/proze/projekt/pin.txt")) {
                (wlasnosci = new Properties()).load(fr);
            } catch (Throwable thr) {
                wypiszKomunikatyIZakoncz("Nie udało się wczytać pinu gry.", thr);
            }
        }
    }

    /**
     * Wypisuje komunikaty i kończy działanie programu przez {@link java.lang.System#exit}.
     * Dodatkowo, jeśli komunikat jest wyjątkiem zostanie wywołana metoda
     * {@link java.lang.Throwable#printStackTrace} tego wyjątku.
     *
     * @param tablicaKomunikatow
     */
    void wypiszKomunikatyIZakoncz(Object... tablicaKomunikatow) {
        for (int i = 0; i < tablicaKomunikatow.length; i++) {
            System.err.print((i + 1) + ": ");
            if (tablicaKomunikatow[i] instanceof Throwable) {
                ((Throwable) tablicaKomunikatow[i]).printStackTrace();
            } else {
                System.err.println(tablicaKomunikatow[i].toString());
            }
        }
        System.exit(-1);
    }

    public String toString() {
        return "pin: " + pin + ", autorzy: " + autorzy;
    }

    /**
     * Test klasy. Argumenty nieużywane.
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}