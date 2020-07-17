package proze.projekt;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *Klasa reprezentująca planszę gry
 * @author Maciej Rytel, Marcin Skinder
 * @version 13.06.2019
 */

public class GamePanel extends JPanel implements ActionListener, MouseListener, KeyListener {

    Timer timer = new Timer(5, this);
    int points = 0; //Zmienna przechowująca ilość aktualnie zdobytych punktów
    int level = 1; //Zmienna przechowująca numer poziomu, który osiągnął gracz

    /** Lista przechowująca cele będące kwadratami */
    public ArrayList<TargetSquare> targetSquareList = new ArrayList<TargetSquare>();

    /** Lista przechowująca cele będące prostokątami */
    public ArrayList<TargetRectangle> targetRectangleList = new ArrayList<TargetRectangle>();

    /** Lista przechowująca cele będące kółkami */
    public ArrayList<TargetCircle> targetCircleList = new ArrayList<TargetCircle>();

    /** Lista przechowująca cele będące obrazkami */
    public ArrayList<TargetImage> targetImageList = new ArrayList<TargetImage>();

    JLabel pointsLabel = new JLabel(); //napis na planszy informujący o liczbie zdobytych punktów
    JLabel levelLabel = new JLabel(); //napis na planszy informujący o aktualnym poziomie
    JLabel pauseLabel = new JLabel(); //napis na planszy informujący o włączonej pauzie

    HighScoreManager hm = new HighScoreManager(); //obiekt klasy obsługującej listę najlepszych wyników
    boolean game_paused = false; //flaga informująca o włączonej/wyłączonej pauzie

    private Point mouseCoordinates = new Point(0, 0); //zmienna przechowująca położenie kursora na planszy

    /**
     * Konstruktor klasy
     */
    public GamePanel() {
        addMouseListener(this);
        addKeyListener(this);
        this.setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    /**
     * Metoda obsługuje włączenie/wyłączenie pauzy po wciśnięciu przycisku 'P'
     * @param event
     */
    @Override
    public void keyPressed(KeyEvent event) {

        char ch = event.getKeyChar(); //przypisanie klawisza, który został wciśnięty

        double currentVelocityForShapes = 0.1 * level; //zmienna służąca do przywrócenia prędkości obiektu
                                                       // po wyłączeniu pauzy

        double currentVelocityForImage = 0.3 * level; //zmienna służąca do przywrócenia prędkości obiektu
                                                      // po wyłączeniu pauzy

        if (Parametry.zwrocObiektyGry().equals("figuryGeometryczne")) {
            /**
             * Jeśli gra nie jest aktualnie zapauzowana, a został wciśnięty przycisk "P",
             * to umieszczamy napis "PAUZA" i zatrzymujemy obiekt
             */
            if (ch == 'p' && !game_paused) {
                addPauseLabel();
                switch (Parametry.zwrocFigureObiektuGry()) {
                    case "kwadraty":
                        targetSquareList.get(0).velocity = 0;
                        break;

                    case "prostokąty":
                        targetRectangleList.get(0).velocity = 0;
                        break;

                    case "kółka":
                        targetCircleList.get(0).velocity = 0;
                        break;
                }
                game_paused = true;
            }
            /**
             * Jeśli gra jest aktualnie zapauzowana, a został wciśnięty przycisk "P",
             * to usuwamy napis "PAUZA" i przywracamy obiektowi jego aktualną prędkość
             */
            else if (ch == 'p' && game_paused) {
                removePauseLabel();
                switch (Parametry.zwrocFigureObiektuGry()) {
                    case "kwadraty":
                        targetSquareList.get(0).velocity = currentVelocityForShapes;
                        break;

                    case "prostokąty":
                        targetRectangleList.get(0).velocity = currentVelocityForShapes;
                        break;

                    case "kółka":
                        targetCircleList.get(0).velocity = currentVelocityForShapes;
                        break;
                }

                game_paused = false;

            }
        }
        else
        /**
         * Jeśli gra nie jest aktualnie zapauzowana, a został wciśnięty przycisk "P",
         * to umieszczamy napis "PAUZA" i zatrzymujemy obiekt
         */
            if(ch == 'p' && !game_paused){
                addPauseLabel();
                targetImageList.get(0).velocity = 0;
                game_paused = true;
            }
            /**
             * Jeśli gra jest aktualnie zapauzowana, a został wciśnięty przycisk "P",
             * to usuwamy napis "PAUZA" i przywracamy obiektowi jego aktualną prędkość
             */
            else if(ch == 'p' && game_paused){
                removePauseLabel();
                targetImageList.get(0).velocity = currentVelocityForImage;
                game_paused = false;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Metoda służąca do pobrania pliku graficznego stanowiącego tło planszy
     * @return Image
     */
    public Image getBackgroundImage(){
        ImageIcon i = new ImageIcon(getClass().getResource(Parametry.zwrocPlikTla()).getFile());
        return i.getImage();
    }

    /**
     * Metoda służąca do odtworzenia dźwięku strzału
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public void playShootSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File audioFile = new File("D:/Program files/IdeaProjects/proze-iii-etap/src/proze/projekt/shoot.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStream);
        audioClip.start();
    }

    /**
     * Metoda służąca do odtworzenia dźwięku informującego o przejściu na wyższy poziom trudności
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public void playLevelUpSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File audioFile = new File("D:/Program files/IdeaProjects/proze-iii-etap/src/proze/projekt/levelUp.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStream);
        audioClip.start();
    }

    /**
     * Metoda umieszczająca napis informujący o liczbie zdobytych punktów
     */
    public void addPointsLabel(){
        pointsLabel.setText("Punkty: " + Integer.toString(points));
        pointsLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        pointsLabel.setForeground(Color.RED);
        this.add(pointsLabel);
    }
    /**
     * Metoda umieszczająca napis informujący o aktualnym poziomie
     */
    public void addLevelLabel(){
        levelLabel.setText("Poziom: " + Integer.toString(level));
        levelLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        levelLabel.setForeground(Color.RED);
        this.add(levelLabel);
    }

    /**
     * Metoda umieszczająca napis "PAUZA" na planszy
     */
    public void addPauseLabel(){
        pauseLabel.setText("PAUZA");
        pauseLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        pauseLabel.setForeground(Color.RED);
        this.add(pauseLabel);
    }

    /**
     * Metoda usuwająca napis "PAUZA" z planszy
     */
    public void removePauseLabel(){
        this.remove(pauseLabel);
    }

    /**
     * Metoda rysująca tło na planszy
     * @param g
     */
    public void paintBackground(Graphics g){
        if(Parametry.zwrocRodzajTla().equals("plikGraficzny")){
            g.drawImage(getBackgroundImage(),0,0,this);
        }
        else if (Parametry.zwrocRodzajTla().equals("jednolite")) {
            setBackground(new Color(Parametry.zwrocTabliceKolorow()[0], Parametry.zwrocTabliceKolorow()[1],
                    Parametry.zwrocTabliceKolorow()[2]));
        }
    }

    /**
     * Metoda rysująca na planszy obiekt, w zależności od jego rodzaju
     * @param g
     */
    public void drawTarget(Graphics g){
        if (Parametry.zwrocFigureObiektuGry().equals("kwadraty")){
            targetSquareList.add(new TargetSquare());
            targetSquareList.get(0).drawTarget(g);
        }

        else if (Parametry.zwrocFigureObiektuGry().equals("prostokąty")){
            targetRectangleList.add(new TargetRectangle());
            targetRectangleList.get(0).drawTarget(g);
        }

        else if (Parametry.zwrocFigureObiektuGry().equals("kółka")) {
            targetCircleList.add(new TargetCircle());
            targetCircleList.get(0).drawTarget(g);
        }

        else if (Parametry.zwrocObiektyGry().equals("plikGraficzny")){
            targetImageList.add(new TargetImage());
            try {
                targetImageList.get(0).drawTarget(g);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Metoda wyświetlająca okno końcowe, informujące o ilości zdobytych punktów i umożliwiające ponowną rozgrywkę
     */
    public void displayEndGameWindow(){
        EndGameWindow endGameWindow = new EndGameWindow();
        endGameWindow.setText(points);
        endGameWindow.setVisible(true);
        endGameWindow.buttonActions();
        hm.addScore(NameWindow.returnName(), points);
        JFrame parent = (JFrame) this.getTopLevelAncestor();
        parent.dispose();
    }

    /**
     * Metoda sprawdzająca, czy obiekt nie znalazł się poza obszarem gry
     */
    public void checkIfTargetIsInGameField(){
        if(Parametry.zwrocObiektyGry().equals("figuryGeometryczne")) {
            switch (Parametry.zwrocFigureObiektuGry()) {
                case "kółka": {
                    if (targetCircleList.get(0).y + targetCircleList.get(0).width > Parametry.zwrocWysokosc()) {
                        this.setVisible(false);
                        displayEndGameWindow();
                    }
                    break;
                }

                case "kwadraty": {
                    if (targetSquareList.get(0).y + targetSquareList.get(0).width > Parametry.zwrocWysokosc()) {
                        this.setVisible(false);
                        displayEndGameWindow();
                    }
                    break;
                }

                case "prostokąty": {
                    if (targetRectangleList.get(0).y + targetRectangleList.get(0).height > Parametry.zwrocWysokosc()) {
                        this.setVisible(false);
                        displayEndGameWindow();
                    }
                    break;
                }
            }
        }
        else
        if (targetImageList.get(0).y + targetImageList.get(0).width > Parametry.zwrocWysokosc()) {
            this.setVisible(false);
            displayEndGameWindow();
        }
    }

    /**
     * Metoda rysująca zawartość planszy
     * @param g
     */
    public void paintComponent(Graphics g){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.paintComponent(g);
        paintBackground(g);
        Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
        setCursor(cursor);
        addPointsLabel();
        addLevelLabel();
        drawTarget(g);
        repaint();
        checkIfTargetIsInGameField();
        timer.start();
    }

    /**
     * Metoda sprawdzająca, czy w momencie wciśnięcia przycisku strzału kursor znajduje się na celu
     */
    public void checkIfTargetIsHitted(){
        if (Parametry.zwrocObiektyGry().equals("figuryGeometryczne")) {
            if (!game_paused) {
                switch (Parametry.zwrocFigureObiektuGry()) {

                    case "kółka": {
                        if (mouseCoordinates.x > targetCircleList.get(0).x &&
                                mouseCoordinates.x < targetCircleList.get(0).width + targetCircleList.get(0).x &&
                                mouseCoordinates.y > targetCircleList.get(0).y &&
                                mouseCoordinates.y < (targetCircleList.get(0).width + targetCircleList.get(0).y)) {
                            targetCircleList.remove(0);
                            points++;
                            if (points % 10 == 0) {
                                try {
                                    playLevelUpSound();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                } catch (UnsupportedAudioFileException e1) {
                                    e1.printStackTrace();
                                } catch (LineUnavailableException e1) {
                                    e1.printStackTrace();
                                }
                                targetCircleList.get(0).velocity += 0.1;
                                level++;
                            }
                        } else {
                            displayEndGameWindow();
                        }
                        break;
                    }

                    case "kwadraty": {
                        if (mouseCoordinates.x > targetSquareList.get(0).x &&
                                mouseCoordinates.x < targetSquareList.get(0).width + targetSquareList.get(0).x &&
                                mouseCoordinates.y > targetSquareList.get(0).y &&
                                mouseCoordinates.y < (targetSquareList.get(0).width + targetSquareList.get(0).y)) {
                            targetSquareList.remove(0);
                            points++;
                            if (points % 10 == 0) {
                                try {
                                    playLevelUpSound();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                } catch (UnsupportedAudioFileException e1) {
                                    e1.printStackTrace();
                                } catch (LineUnavailableException e1) {
                                    e1.printStackTrace();
                                }
                                targetSquareList.get(0).velocity += 0.1;
                                level++;
                            }
                        } else {
                            displayEndGameWindow();
                        }
                        break;
                    }

                    case "prostokąty": {
                        if (mouseCoordinates.x > targetRectangleList.get(0).x &&
                                mouseCoordinates.x < targetRectangleList.get(0).width + targetRectangleList.get(0).x &&
                                mouseCoordinates.y > targetRectangleList.get(0).y &&
                                mouseCoordinates.y < (targetRectangleList.get(0).height + targetRectangleList.get(0).y)) {
                            //System.out.println("Trafiony!");
                            targetRectangleList.remove(0);
                            points++;
                            if (points % 10 == 0) {
                                try {
                                    playLevelUpSound();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                } catch (UnsupportedAudioFileException e1) {
                                    e1.printStackTrace();
                                } catch (LineUnavailableException e1) {
                                    e1.printStackTrace();
                                }
                                targetRectangleList.get(0).velocity += 0.1;
                                level++;
                            }
                        } else {
                            displayEndGameWindow();
                        }
                        break;
                    }
                }
            }
        }
        else if (!game_paused) {
            if (mouseCoordinates.x > targetImageList.get(0).x &&
                    mouseCoordinates.x < targetImageList.get(0).width + targetImageList.get(0).x &&
                    mouseCoordinates.y > targetImageList.get(0).y &&
                    mouseCoordinates.y < (targetImageList.get(0).width + targetImageList.get(0).y)) {
                //System.out.println("Trafiony!");
                targetImageList.remove(0);
                points++;
                if (points % 10 == 0) {
                    try {
                        playLevelUpSound();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                    //targetImageList.add(new TargetImage());
                    targetImageList.get(0).velocity += 0.3;
                    level++;
                }
            } else {
                displayEndGameWindow();
            }


        }
    }


    public void actionPerformed(ActionEvent e){

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Metoda uruchamia dźwięk strzału w momencie wciśnięcia przycisku, i wywołuje metodę sprawdzającą,
     * czy kursor znajdował się na celu
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mouseCoordinates.setLocation(e.getPoint());
        if(!game_paused) {
            try {
                playShootSound();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
        }
        checkIfTargetIsHitted();
    }

        @Override
        public void mouseReleased (MouseEvent e){

        }

        @Override
        public void mouseEntered (MouseEvent e){

        }

        @Override
        public void mouseExited (MouseEvent e){

        }

}

