package console;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Variabili comuni a tutto il programma
 */
public final class Variabili {

    /**
     * Mazzo originale delle carte iniziali.
     * Non utilizzare direttamente ma copiare in un mazzo di gioco.
     */
    static List<Carta_Gioco> mazzoCarteInizialiOriginali = new ArrayList<Carta_Gioco>();

    /**
     * Mazzo originale delle carte risorsa.
     * Non utilizzare direttamente ma copiare in un mazzo di gioco.
     */
    static List<Carta_Gioco> mazzoCarteRisorsaOriginali = new ArrayList<Carta_Gioco>();

    /**
     * Mazzo originale delle carte oro.
     * Non utilizzare direttamente ma copiare in un mazzo di gioco.
     */
    static List<Carta_Gioco> mazzoCarteOroOriginali = new ArrayList<Carta_Gioco>();

    /**
     * Mazzo originale delle carte obiettivo.
     * Non utilizzare direttamente ma copiare in un mazzo di gioco.
     */
    static Carta_Obiettivo[] mazzoCarteObiettivo = new Carta_Obiettivo[16];

    public static boolean DEBUG_MAPPA_ANGOLI = false;
    

    public static Gioco partita = null;
    public static boolean mostraMercato = true;


    // Tecnologia doubleBuffering 

    public static Image immagineBack;
    public static Graphics2D immagineBackGraphics;
    
    public static Carta carta = new Carta();
    
    public static Giocatore giocatoreInCreazione = null;

    public Variabili(){
        
    }

}
