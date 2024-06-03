package console;
import java.awt.Rectangle;
import java.util.List;

/**
 * Rappresenta una carta gioco nel manoscritto includendo la riga e la colonna su cui è posizionata
 */

public class Cella_Manoscritto {

	Carta_Gioco carta;
    int riga;
    int colonna;
    Rectangle rectCarta = new Rectangle(0, 0, 10, 10);
    Manoscritto m;
    
    /**
     * Costruttore della Cella_Manoscritto
     * @param cGG	carta_Gioco
     * @param riga2	riga in cui sarà posizionata la carta
     * @param colonna2 colonna in cui sarà posizionata la carta
     */
    public Cella_Manoscritto(Carta_Gioco cGG, int riga2, int colonna2) {
    	m.griglia[riga2][colonna2]=cGG.ID;
    }
    /**
     * Costruttore Cella_Manoscritto
     * @param cGG carta gioco
     * @param manoscritto manoscritto associato al giocatore
     * @param riga2 riga in cui sarà posizionata la carta
     * @param colonna2 colonna in cui sarà posizionata la carta
     */
    public Cella_Manoscritto(Carta_Gioco cGG, Manoscritto manoscritto, int riga2, int colonna2) {
    	this.carta=cGG;
    	this.riga=riga2;
    	this.colonna=colonna2;
    	manoscritto.griglia[riga2][colonna2]=cGG.ID;
    }
    /**
     * costruttore della cella_Manoscritto
     * @param manoscritto manoscritto associato al giocatore
     * @param riga2 riga in cui sarà posizionata la carta
     * @param colonna2 colonna in cui sarà posizionata la carta
     * @param posizioneCartaIniziale 
     */
    public Cella_Manoscritto(Manoscritto manoscritto, int riga2, int colonna2, int posizioneCartaIniziale) {
    	manoscritto.griglia[riga2][colonna2]=manoscritto.carte.get(posizioneCartaIniziale).carta.ID;
    	
    }
    
    /**
     * Metodo per cercare una carta all'interno della lista
     * @param listaCarte lista dove sono contenute le carte con le relative righe e colonne di posizionamento
     * @param iD id della carta
     * @return oggetto di tipo Cella_Manoscritto contenente la carta cercata
     */
    public Cella_Manoscritto cercaCarta(List<Cella_Manoscritto> listaCarte, String iD) {
    	int iDIntero=Integer.parseInt(iD);
    	for(int i=0;i<listaCarte.size();i++) {
    		if(listaCarte.get(i).carta.ID==iDIntero) {
    			return listaCarte.get(i);
    		}
    	}
		return null;
    	
    }
    /**
     * metodo per cercare la carta all'interno della lista carte
     * @param listaCarte lista delle carte piazzate
     * @param iD id della carta
     * @param tipo tipo della carta (iniziale, oro o risorsa)
     * @return
     */
    public Cella_Manoscritto cercaCarta(List<Cella_Manoscritto> listaCarte, String iD, Enums.eTipoCarta tipo) {
    	int iDIntero=Integer.parseInt(iD);
    	for(int i=0;i<listaCarte.size();i++) {
    		if((listaCarte.get(i).carta.ID==iDIntero)&&(listaCarte.get(i).carta.tipo==tipo)) {
    			return listaCarte.get(i);
    		}
    	}
		return null;
    	
    }
    /**
     * metodo per ottenere l'indice della relativa posizione della carta all'interno della lista
     * @param listaCarte lista delle carte piazzate
     * @param iD id della carta che si vuole trovare
     * @return la posizione nella lista dove è salvata la carta
     */
    public int getIndex(List<Cella_Manoscritto> listaCarte, String iD) {
    	int iDIntero=Integer.parseInt(iD);
    	for(int i=0;i<listaCarte.size();i++) {
    		if(listaCarte.get(i).carta.ID==iDIntero) {
    			return i;
    		}
    	}
		return -1;
    }
    /*
    //faccio scorrere la lista carte finche non trovo una carta con righe e colonne uguali a quella cercata
    public Boolean angoloOccupatoNO(List<Cella_Manoscritto> lista,Cella_Manoscritto cartaPosizionata) {
    	for(int i=0;i<lista.size();i++) {
    		if((cartaPosizionata.riga-1==lista.get(i).riga)&&(cartaPosizionata.colonna-1==lista.get(i).colonna)){
        		return true; //angolo occupato
        	}
        	return false; //angolo libero
    	}
		return null;
    	
    }
    public Boolean angoloOccupatoNE(List<Cella_Manoscritto> lista,Cella_Manoscritto cartaPosizionata) {
    	for(int i=0;i<lista.size();i++) {
    		if((cartaPosizionata.riga+1==lista.get(i).riga)&&(cartaPosizionata.colonna-1==lista.get(i).colonna)){
        		return true; //angolo occupato
        	}
        	return false; //angolo libero
    	}
		return null;
    	
    }
    public Boolean angoloOccupatoSE(List<Cella_Manoscritto> lista,Cella_Manoscritto cartaPosizionata) {
    	for(int i=0;i<lista.size();i++) {
    		if((cartaPosizionata.riga+1==lista.get(i).riga)&&(cartaPosizionata.colonna+1==lista.get(i).colonna)){
        		return true; //angolo occupato
        	}
        	return false; //angolo libero
    	}
		return null;
    	
    }
    public Boolean angoloOccupatoSO(List<Cella_Manoscritto> lista,Cella_Manoscritto cartaPosizionata) {
    	for(int i=0;i<lista.size();i++) {
    		if((cartaPosizionata.riga-1==lista.get(i).riga)&&(cartaPosizionata.colonna+1==lista.get(i).colonna)){
        		return true; //angolo occupato
        	}
        	return false; //angolo libero
    	}
		return null;
    	
    }
    */
    /**
     * metodo per ottenere la coordinata della della colonna carta iniziale
     * @return il numero della colonna in cui si trova la carta iniziale
     */
    public int getCoordinataCartaInizialeX() {
    	int x=20;
    	return x; 
    }
    
    /**
     * metodo per ottenere la coordinata della riga della carta iniziale
     * @return il numero della riga in cui si trova la carta iniziale
     */
    public int getCoordinataCartaInizialeY() {
    	int y=20;
    	return y;
    }
    
    
	
    
}

