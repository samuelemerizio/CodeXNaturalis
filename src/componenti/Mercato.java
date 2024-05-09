package componenti;
import java.util.ArrayList;
import java.util.List;

/**
 * Identifica quanto chiamato mercato nel gioco.
 * Contiene il mazzo delle carte risorsa, il mazzo delle carte oro, le due carte risorsa e le due carte oro.
 */
public class Mercato {

    /**
     * Mazzo carte risorsa
     */
    List<Carta_Gioco> mazzoCarteRisorse = new ArrayList<Carta_Gioco>();
    /**
     * Mazzo carte oro
     */
    List<Carta_Gioco> mazzoCarteOro = new ArrayList<Carta_Gioco>();

    /**
     * Sono le 2 carte risorsa disponibili nel mercato
     */
    Carta_Gioco[] carteRisorsa = new Carta_Gioco[2];

    /**
     * Sono le 2 carte oro disponibili nel mercato
     */
    Carta_Gioco[] carteOro = new Carta_Gioco[2];

    public Mercato(){

    }


}
