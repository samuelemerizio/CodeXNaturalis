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
    public static Carta_Gioco[] carteRisorsa = new Carta_Gioco[2];

    /**
     * Sono le 2 carte oro disponibili nel mercato
     */
    Carta_Gioco[] carteOro = new Carta_Gioco[2];

    public Mercato(){

    }

    /**
     * Mediante questa funzione, viene mescolato il mazzo delle carte risorse ed il mazzo delle carte oro
     * Il procedimento consiste nel selezione 2 carte a caso e scambiando la loro posizione
     * Questo viene eseguito un numero elavato di volte 
     */
    public void mescolaMazziCarteRisorseOro(List<Carta_Gioco> mazzoRisorseOriginale , List<Carta_Gioco> mazzoOroOriginale) {
    	//Creo i mazzi della partita in funzione delle carte disponibili generate dal file
    	mazzoCarteRisorse = mazzoRisorseOriginale;
    	mazzoCarteOro = mazzoOroOriginale;
    	
    	//Itero n. volte scambiando tra di loro 2 carte
    	int indice1 = 0;
    	int indice2 = 0;
    	Carta_Gioco cGT;
    	for (int i=0 ; i < 400; i++) {
    		indice1 = (int)(Math.random() * mazzoCarteRisorse.size());
    		indice2 = (int)(Math.random() * mazzoCarteRisorse.size());
    		if (indice1 != indice2)
    		{
    			cGT = mazzoCarteRisorse.get(indice2);
    			mazzoCarteRisorse.set(indice2, mazzoCarteRisorse.get(indice1));
    			mazzoCarteRisorse.set(indice1,  cGT);
    		}
    	}
    	
    	for (int i=0 ; i < 400; i++) {
    		indice1 = (int)(Math.random() * mazzoCarteOro.size());
    		indice2 = (int)(Math.random() * mazzoCarteOro.size());
    		if (indice1 != indice2)
    		{
    			cGT = mazzoCarteOro.get(indice2);
    			mazzoCarteOro.set(indice2, mazzoCarteOro.get(indice1));
    			mazzoCarteOro.set(indice1,  cGT);
    		}
    	}
    }



}
