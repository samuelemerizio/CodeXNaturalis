import java.awt.Graphics2D;
import java.awt.Image;
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

    

    public static Gioco partita = null;
    public static boolean mostraMercato = true;


    // Tecnologia doubleBuffering 

    public static Image immagineBack;
    public static Graphics2D immagineBackGraphics;
    
    public static Carta carta = new Carta();
    public static int dxMonitor = 1240;
    public static int dyMonitor = 800;
    public static int dxTavolo = 840;
    public static int dyTavolo = dyMonitor;
    public static Enums.eElencoSchermi schermoAttivo = Enums.eElencoSchermi.SPLASHSCREEN;
    public static double zoom = 1;


    // Variabili utilizzate per le classi grafiche
    public static Schermo_SplashScreen schermoSplashScreen;
    public static Schermo_Iniziale schermoIniziale;
    public static Schermo_NuovaPartita schermoNuovaPartita;
    public static Schermo_Mercato schermoMercato;
    public static Schermo_NuovoGiocatore schermoNuovoGiocatore;
    public static Schermo_SceltaCartaIniziale schermoSceltaCartaIniziale;
    public static Schermo_SceltaCartaObiettivo schermoSceltaCartaObiettivo;
    public static Schermo_Gioco schermoGioco;
    public static Schermo_Punteggio schermoPunteggio;

    public static boolean immaginiCaricate = false;


    //Variabili immagini utilizzate nel programma
    public static BufferedImage bkIniziale = null;
    public static BufferedImage bkMain = null;
	public static BufferedImage imgTavolo = null;
	public static BufferedImage imgTavolo2 = null;
	public static BufferedImage imgCartaObiettivoRetro = null;
	public static BufferedImage imgSfondoMercato = null;
	public static BufferedImage imgTracciatoSegnapunti = null;
	public static BufferedImage imgCartaOroRossaRetro = null;
	public static BufferedImage imgCartaRisorsaRossaRetro = null;
	public static BufferedImage imgCartaOroBluRetro = null;
	public static BufferedImage imgCartaRisorsaBluRetro = null;
	public static BufferedImage imgCartaOroVerdeRetro = null;
	public static BufferedImage imgCartaRisorsaVerdeRetro = null;
	public static BufferedImage imgCartaOroViolaRetro = null;
	public static BufferedImage imgCartaRisorsaViolaRetro = null;
	public static BufferedImage[] imgCarteRisorse = new BufferedImage[40];
	public static BufferedImage[] imgCarteOro = new BufferedImage[40];
	public static BufferedImage[] imgCarteObiettivi = new BufferedImage[16];
	public static BufferedImage[] imgCarteIniziali = new BufferedImage[12];
	public static BufferedImage[] imgPedine = new BufferedImage[4];
	public static BufferedImage imgZoom0 = null;
	public static BufferedImage imgZoom1 = null;
	public static BufferedImage imgZoom2 = null;


    public static Giocatore giocatoreInCreazione = null;


}
