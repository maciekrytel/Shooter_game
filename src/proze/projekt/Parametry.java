package proze.projekt;

//import com.sun.java.swing.plaf.windows.TMSchema;
import javafx.fxml.FXML;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 *Klasa parsująca plik konfiguracyjny
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */

public class Parametry {

    private final PIN pin = PIN.PIN;
    private static Properties props = new Properties();
    private static int numeracjaPoziomow = 0;
    private static int poczatkowaWysokoscPlanszy = 0;
    private static String tlo = "";
    private static String plikTla = "";
    private static int poczatkowaSzerokoscPlanszy = 0;
    private static String kolory;
    private static int tablicaKolorow[] = {0, 0, 0};
    private static String obiektyGry = "";
    private static String plikObiektuGry = "";
    private static double poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy = 0;
    private static String rozszerzeniePlikuZOpisemPoziomu = "";
    private static int liczbaPoziomow = 0;
    private static String nazwaGry = "";
    private static int liczbaStopniTrudnosci = 0;
    private static int zmianaStopniaTrudnosci = 0;
    private static String figuraObiektuGry = "";


    /**
     * Metoda wczytująca parametry z pliku parametrycznego
     * @param nazwaPliku
     * @return props
     */
    public Properties wczytajParametry(String nazwaPliku){
        try (FileReader r = new FileReader(new File(nazwaPliku))) {
            FileInputStream input = new FileInputStream(new File("D:/Program files/IdeaProjects/proze-iii-etap/par.txt"));
            (props = new Properties()).load(new InputStreamReader(input, Charset.forName("UTF-8")));
        } catch (FileNotFoundException fnfe) {
            pin.wypiszKomunikatyIZakoncz("Nie znaleziono pliku parametrycznego",
                    nazwaPliku, fnfe);
        } catch (IOException ioe) {
            pin.wypiszKomunikatyIZakoncz("Wystąpil błąd odczytu pliku parametrycznego",
                    nazwaPliku, ioe);
        }
        return props;
    }

    /**
     * Metoda wypisująca wartości poszczególnych parametrów
     */
    public void wypiszParametry(){
        props.forEach((nazwaParametru, wartoscParametru) -> {
        });
    }

    /**
     * Metoda parsująca plik parametryczny
     */
    public void parsowaniePliku(){
        numeracjaPoziomow = Integer.parseInt(props.getProperty("numeracjaPoziomówZaczynaSięOd"));
        poczatkowaWysokoscPlanszy = Integer.parseInt(props.getProperty("początkowaWysokośćPlanszy"));
        poczatkowaSzerokoscPlanszy = Integer.parseInt(props.getProperty("początkowaSzerokośćPlanszy"));
        tlo = props.getProperty("tło");
        if(tlo.equals("plikGraficzny")){
            plikTla = props.getProperty("plikTła");
        }
        else if(tlo.equals("jednolite")){
            kolory = props.getProperty("klorTła");
            tablicaKolorow[0] = Integer.parseInt(kolory.split(" ")[0]);
            tablicaKolorow[1] = Integer.parseInt(kolory.split(" ")[1]);
            tablicaKolorow[2] = Integer.parseInt(kolory.split(" ")[2]);
        }
        obiektyGry = props.getProperty("obiektyGry");
        if(obiektyGry.equals("plikGraficzny")){
            plikObiektuGry = props.getProperty("plikObiektu");
        }
        else{
            figuraObiektuGry = props.getProperty("figuraObiektuGry");
        }
        poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy = Double.parseDouble(props.getProperty("początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy"));
        rozszerzeniePlikuZOpisemPoziomu = props.getProperty("rozszerzeniePlikuZOpisemPoziomu");
        liczbaPoziomow = Integer.parseInt(props.getProperty("liczbaPoziomów"));
        nazwaGry = props.getProperty("nazwaGry");
        liczbaStopniTrudnosci = Integer.parseInt(props.getProperty("liczbaStopniTrudności"));
        zmianaStopniaTrudnosci = Integer.parseInt(props.getProperty("zmianaStopniaTrudności"));
    }

    public static int zwrocWysokosc(){
        return poczatkowaWysokoscPlanszy;
    }

    public static int zwrocSzerokosc(){
        return poczatkowaSzerokoscPlanszy;
    }

    public static int zwrocNumeracjePoziomow(){
        return numeracjaPoziomow;
    }

    public static String zwrocNazweGry(){
        return nazwaGry;
    }

    public static int[] zwrocTabliceKolorow(){
        return tablicaKolorow;
    }

    public static String zwrocRodzajTla(){
        return tlo;
    }

    public static String zwrocPlikTla(){ return plikTla; }

    public static String zwrocObiektyGry(){ return obiektyGry; }

    public static String zwrocPlikObiektuGry(){ return plikObiektuGry; }

    public static String zwrocFigureObiektuGry(){return figuraObiektuGry; }

    public static double zwrocPoczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy(){
        return poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy;
    }

    public static String zwrocRozszerzeniePlikuZOpisemPoziomu(){return rozszerzeniePlikuZOpisemPoziomu; }

    public static int zwrocLiczbePoziomow(){return liczbaPoziomow; }

    public static int zwrocLiczbeStopniTrudnosci(){return liczbaStopniTrudnosci;}

    public static int zwrocZmianeStopniaTrudnosci(){return zmianaStopniaTrudnosci; }

    }


