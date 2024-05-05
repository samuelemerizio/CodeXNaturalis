import java.util.ArrayList;
import java.util.List;

/**
 * Variabili comuni a tutto il programma
 */
public class Variabili {

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

}
