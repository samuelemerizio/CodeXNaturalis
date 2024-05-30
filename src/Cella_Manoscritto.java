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
    
    
    public Cella_Manoscritto(Carta_Gioco cGG, int riga2, int colonna2) {
    	m.griglia[riga2][colonna2]=cGG.ID;
    }
    public Cella_Manoscritto(Carta_Gioco cGG, Manoscritto manoscritto, int riga2, int colonna2) {
    	this.carta=cGG;
    	this.riga=riga2;
    	this.colonna=colonna2;
    	manoscritto.griglia[riga2][colonna2]=cGG.ID;
    }
    public Cella_Manoscritto(Manoscritto manoscritto, int riga2, int colonna2, int posizioneCartaIniziale) {
    	manoscritto.griglia[riga2][colonna2]=manoscritto.carte.get(posizioneCartaIniziale).carta.ID;
    	
    }
    
    public Cella_Manoscritto cercaCarta(List<Cella_Manoscritto> listaCarte, String iD) {
    	int iDIntero=Integer.parseInt(iD);
    	for(int i=0;i<listaCarte.size();i++) {
    		if(listaCarte.get(i).carta.ID==iDIntero) {
    			return listaCarte.get(i);
    		}
    	}
		return null;
    	
    }
    public Cella_Manoscritto cercaCarta(List<Cella_Manoscritto> listaCarte, String iD, Enums.eTipoCarta tipo) {
    	int iDIntero=Integer.parseInt(iD);
    	for(int i=0;i<listaCarte.size();i++) {
    		if((listaCarte.get(i).carta.ID==iDIntero)&&(listaCarte.get(i).carta.tipo==tipo)) {
    			return listaCarte.get(i);
    		}
    	}
		return null;
    	
    }
    public int getIndex(List<Cella_Manoscritto> listaCarte, String iD) {
    	int iDIntero=Integer.parseInt(iD);
    	for(int i=0;i<listaCarte.size();i++) {
    		if(listaCarte.get(i).carta.ID==iDIntero) {
    			return i;
    		}
    	}
		return -1;
    }
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
    public int getCoordinataCartaInizialeX() {
    	int x=20;
    	return x; 
    }
    
    public int getCoordinataCartaInizialeY() {
    	int y=20;
    	return y;
    }
    
    
	
    
}

