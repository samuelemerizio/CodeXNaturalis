import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Funzioni utili nel programma
 */
public class Funzioni {
	//Contatore per forzare il termine della sequenza di aggiunta delle carte all'arrayList
	private static int CONTATORE0=0;
	private static int CONTATORE1=0;
	private static int CONTATORE2=0;
	private List<Carta_Gioco> mazzoCarteOro=new ArrayList<Carta_Gioco>();
	private List<Carta_Gioco> mazzoCarteRisorsa=new ArrayList<Carta_Gioco>();
	private List<Carta_Gioco> mazzoCarteIniziali=new ArrayList<Carta_Gioco>();
	private Carta_Obiettivo[] mazzoCarteObiettivo = new Carta_Obiettivo[16];
	
	
    public static void mostraMessaggioErrore(String messaggio){
        System.out.println(messaggio);
    }



    //NON ABBIAMO LA TASTIERA, SIAMO IN MODALITA' GRAFICA
    
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
        	Carta_Gioco g0=new Carta_Gioco(lettura.scansioneRiga("CarteIniziali.csv", CONTATORE0));
        	mazzoCarteIniziali.add(g0);
            CONTATORE0+=1;
         }
   	 System.out.println("Carte INIZIALI inizializzate con successo");
    	return mazzoCarteIniziali;
    }
    public Carta_Obiettivo[] initCarteObiettivo() throws IOException{
    	LetturaFile lettura=new LetturaFile("");
    	initArray(); //inizializza array carte obiettivo
    	for(int i=0; i<16; i++) {
    		mazzoCarteObiettivo[i]=new Carta_Obiettivo(lettura.scansioneRiga("CarteObiettivo.csv", i));
    	}
       	System.out.println("Carte OBIETTIVO inizializzate con successo");
    	return mazzoCarteObiettivo;
    	
    }
    public List<Carta_Gioco> initCarteOro() throws IOException{
    	LetturaFile lettura=new LetturaFile("");
    	while(CONTATORE1 != 40) {
        	Carta_Gioco g0=new Carta_Gioco(lettura.scansioneRiga("CarteOro.csv", CONTATORE1));
            mazzoCarteOro.add(g0);
            CONTATORE1+=1;
         }
   	 	System.out.println("Carte ORO inizializzate con successo");
    	return mazzoCarteOro;
    }
    public List<Carta_Gioco> initCarteRisorsa() throws IOException{
    	LetturaFile lettura=new LetturaFile("");
    	 while(CONTATORE2 != 40) {
           	Carta_Gioco g0=new Carta_Gioco(lettura.scansioneRiga("CarteRisorsa.csv", CONTATORE2));
           	mazzoCarteRisorsa.add(g0);
               CONTATORE2+=1;
           }
    	 System.out.println("Carte RISORSA inizializzate con successo");
    	 return mazzoCarteRisorsa;
    }
    



}
