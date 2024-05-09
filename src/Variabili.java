import java.awt.Graphics2D;
import java.awt.Image;
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
    List<Carta_Gioco> mazzoCarteInizialiOriginali = new ArrayList<Carta_Gioco>();

    /**
     * Mazzo originale delle carte risorsa.
     * Non utilizzare direttamente ma copiare in un mazzo di gioco.
     */
    List<Carta_Gioco> mazzoCarteRisorsaOriginali = new ArrayList<Carta_Gioco>();

    /**
     * Mazzo originale delle carte oro.
     * Non utilizzare direttamente ma copiare in un mazzo di gioco.
     */
    List<Carta_Gioco> mazzoCarteOroOriginali = new ArrayList<Carta_Gioco>();

    /**
     * Mazzo originale delle carte obiettivo.
     * Non utilizzare direttamente ma copiare in un mazzo di gioco.
     */
    Carta_Obiettivo[] mazzoCarteObiettivo = new Carta_Obiettivo[16];




    // Tecnologia doubleBuffering 

    public static Image immagineBack;
    public static Graphics2D immagineBackGraphics;
    

    public static int dxMonitor = 1240;
    public static int dyMonitor = 800;
    public static Enums.eElencoSchermi schermoAttivo = Enums.eElencoSchermi.SPLASHSCREEN;


    // Variabili utilizzate per le classi grafiche
    public static Schermo_SplashScreen schermoSplashScreen;
    public static Schermo_Iniziale schermoIniziale;




    public Variabili(){
        
    }

}
