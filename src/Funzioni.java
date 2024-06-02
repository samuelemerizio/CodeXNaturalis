import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


/**
 * Funzioni utili nel programma
 */
public class Funzioni {
	private static int CONTATORE0=0;
	private static int CONTATORE1=0;
	private static int CONTATORE2=0;
	static Carta_Obiettivo[] mazzoCarteObiettivo = new Carta_Obiettivo[16];
	static List<Carta_Gioco> mazzoCarteOro = new ArrayList<Carta_Gioco>();
	static List<Carta_Gioco> mazzoCarteRisorsa = new ArrayList<Carta_Gioco>();
	static List<Carta_Gioco> mazzoCarteIniziali = new ArrayList<Carta_Gioco>();
	
    public static void mostraMessaggioErrore(String messaggio){
        System.out.println(messaggio);
    }

    /**
     * Questo metodo scansiona un numero intero
     * @return il valore intero del numero
     */
    public int ScansionaNumero() {
    	Scanner sc=new Scanner(System.in);
		return Integer.decode(sc.nextLine());
    }
    public Carta_Obiettivo[] initArray() {
    	for(int i=0; i<16; i++) {
    		mazzoCarteObiettivo[i] = new Carta_Obiettivo(null);
    	}
		return mazzoCarteObiettivo;
    }
    public List<Carta_Gioco> initCarteIniziali() throws IOException{
    	
    	LetturaFile lettura=new LetturaFile("");
    	while(CONTATORE0 != 12) {
        	Carta_Gioco g0=new Carta_Gioco(lettura.scansioneRiga("assets/CarteIniziali.csv", CONTATORE0));
        	mazzoCarteIniziali.add(g0);
            CONTATORE0+=1;
         }
   	 //System.out.println("Carte INIZIALI inizializzate con successo");
    	return mazzoCarteIniziali;
    }
    
    public List<Carta_Gioco> initCarteOro() throws IOException{
    	LetturaFile lettura=new LetturaFile("");
    	while(CONTATORE1 != 40) {
        	Carta_Gioco g0=new Carta_Gioco(lettura.scansioneRiga("assets/CarteOro.csv", CONTATORE1));
            mazzoCarteOro.add(g0);
            if((CONTATORE1>=0)&&(CONTATORE1<=9)) {
           		g0.coloreCarta=Enums.eColoreCarta.ROSSO;
           	}else if((CONTATORE1>=10)&&(CONTATORE1<=19)) {
           		g0.coloreCarta=Enums.eColoreCarta.VERDE;
           	}else if((CONTATORE1>=20)&&(CONTATORE1<=29)) {
           		g0.coloreCarta=Enums.eColoreCarta.BLU;
           	}else {
           		g0.coloreCarta=Enums.eColoreCarta.VIOLA;
           	}
            CONTATORE1+=1;
         }
   	 	//System.out.println("Carte ORO inizializzate con successo");
    	return mazzoCarteOro;
    }
    public List<Carta_Gioco> initCarteRisorsa() throws IOException{
    	LetturaFile lettura=new LetturaFile("");
    	 while(CONTATORE2 != 40) {
           	Carta_Gioco g0=new Carta_Gioco(lettura.scansioneRiga("assets/CarteRisorse.csv", CONTATORE2));
           	mazzoCarteRisorsa.add(g0);
           	if((CONTATORE2>=0)&&(CONTATORE2<=9)) {
           		g0.coloreCarta=Enums.eColoreCarta.ROSSO;
           	}else if((CONTATORE2>=10)&&(CONTATORE2<=19)) {
           		g0.coloreCarta=Enums.eColoreCarta.VERDE;
           	}else if((CONTATORE2>=20)&&(CONTATORE2<=29)) {
           		g0.coloreCarta=Enums.eColoreCarta.BLU;
           	}else {
           		g0.coloreCarta=Enums.eColoreCarta.VIOLA;
           	}
               CONTATORE2+=1;
           }
    	 //System.out.println("Carte RISORSA inizializzate con successo");
    	 return mazzoCarteRisorsa;
    }
    
    

	 /** 
	  * Restituisce la posizione dell'angolo in alto a sinistra della carta data la riga e colonna del campo di gioco
	  * @param riga
	  * @param colonna
	  * @return
	  */
	public static Point coordinateCarta(int riga, int colonna)
	{
		Point punto = new Point();
		punto.x = (int)(colonna * Variabili.carta.stepX - Variabili.carta.dx / 2);
		punto.y = (int)(riga * Variabili.carta.stepY - Variabili.carta.dy / 2);
		return punto;
	}


	/**
	 * Classe per la registrazione dei risultati
	 */
	public static class risultati{
		int punti;
		int ID;
		int obiettiviRaggiunti;
		
		// Costruttore della classe risultati
		public risultati(int punti, int ID, int obiettiviRaggiunti){
			this.punti = punti;
			this.ID = ID;
			this.obiettiviRaggiunti = obiettiviRaggiunti;
		}
	}

	// Comparatore per la classe risultati
	public static class comparatoreRisultati implements Comparator<risultati>{
		@Override
		public int compare (risultati r1, risultati r2){
			if (r1.punti > r2.punti) return -1;
			if (r1.punti < r2.punti) return 1;

			if (r1.obiettiviRaggiunti > r2.obiettiviRaggiunti) return -1;
			if (r1.obiettiviRaggiunti < r2.obiettiviRaggiunti) return 1;

			return 0;
		}
	}
	

}
