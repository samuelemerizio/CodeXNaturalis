import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Funzioni utili nel programma
 */
public class Funzioni {
	//Contatore per forzare il termine della sequenza di aggiunta delle carte all'arrayList
	private static int CONTATORE1=0;
	private static int CONTATORE2=0;
	private List<Carta_Gioco> mazzoCarteOro=new ArrayList<Carta_Gioco>();
	private List<Carta_Gioco> mazzoCarteRisorsa=new ArrayList<Carta_Gioco>();
	private List<Carta_Gioco> mazzoCarteIniziali=new ArrayList<Carta_Gioco>();
	private Carta_Obiettivo[] mazzoCarteObiettivo = new Carta_Obiettivo[16];
	
	
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
    
    /**
     * Questo metodo inizializza tutte le carte del gioco nelle rispettive ArrayList
     * @throws IOException 
     */
    public void initTutteCarte() throws IOException {
    	LetturaFile lettura=new LetturaFile("");
    	while(CONTATORE1 != 40) {
        	Carta_Gioco g0=new Carta_Gioco(lettura.scansioneRiga("/assets/CarteOro.csv", CONTATORE1));
            mazzoCarteOro.add(g0);
            CONTATORE1+=1;
         }
    	 
    	 
    	 while(CONTATORE2 != 40) {
          	Carta_Gioco g0=new Carta_Gioco(lettura.scansioneRiga("/assets/CarteRisorsa.csv", CONTATORE2));
          	mazzoCarteRisorsa.add(g0);
              CONTATORE2+=1;
          }
    	 System.out.println("Carte inizializzate con successo");
     	 
        
    	
    	
    	
    }
    

}
