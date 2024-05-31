import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// import java.util.Scanner;

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

	public void stampaMercato(Carta_Gioco[] carteRisorsa, Carta_Gioco[] carteOro) {
		for(int i=0;i<carteRisorsa.length;i++) {
			System.out.println(i+", Risorsa, "+"fronte");
			carteRisorsa[i].toString(carteRisorsa[i], true);
			System.out.println(i+", Risorsa, "+"retro");
			carteRisorsa[i].toString(carteRisorsa[i], true);
			System.out.println("\n");
		}
		for(int i=0;i<carteOro.length;i++) {
			System.out.println(i+", Oro, "+"fronte");
			carteRisorsa[i].toString(carteRisorsa[i], true);
			System.out.println(i+", Oro, "+"retro");
			carteRisorsa[i].toString(carteRisorsa[i], true);
			
		}
	}
	
	public Carta_Gioco PescaCarta(Mercato mercato) {
		System.out.println("Da che parte del mercato vuoi pescare? "+
				"Input richiesto: mazzoRisorsa oppure mazzoOro; risorsa0 oppure oro2");
		Scanner sc=new Scanner(System.in);
		String inserimento=sc.nextLine();
		while((!inserimento.equals("mazzoRisorsa"))&&(!inserimento.equals("mazzoOro"))&&(!inserimento.equals("risorsa0"))
				&&(!inserimento.equals("risorsa1"))&&(!inserimento.equals("oro0"))&&(!inserimento.equals("oro1"))){
			//se inserimento non coincide con nessuna di queste opzioni, faccio reinserire l'input
			System.out.println("Hai inserito una parola sbagliata, riprova");
			inserimento=sc.nextLine();
		}
			
		
			if(inserimento.equals("mazzoRisorsa")) {
				//prendo la prima che capita perche sono gia state mescolate
				Carta_Gioco pescata=mazzoCarteRisorse.get(0);
				mazzoCarteRisorse.remove(0);
				return pescata;
			}else if(inserimento.equals("mazzoOro")) {
				Carta_Gioco pescata=mazzoCarteOro.get(0);
				mazzoCarteRisorse.remove(0);
				return pescata;
			}else if(inserimento.equals("risorsa0")) {
				Carta_Gioco pescata=carteRisorsa[0];
				//posiziono al posto della carta appena pescata una carta dal mazzo
				carteRisorsa[0]=mazzoCarteRisorse.get(0);
				mazzoCarteRisorse.remove(0);
				return pescata;
			}else if(inserimento.equals("risorsa1")) {
				Carta_Gioco pescata=carteRisorsa[1];
				//posiziono al posto della carta appena pescata una carta dal mazzo
				carteRisorsa[1]=mazzoCarteRisorse.get(0);
				mazzoCarteRisorse.remove(0);
				return pescata;
			}else if(inserimento.equals("oro0")) {
				Carta_Gioco pescata=carteRisorsa[0];
				//posiziono al posto della carta appena pescata una carta dal mazzo
				carteOro[0]=mazzoCarteOro.get(0);
				mazzoCarteOro.remove(0);
				return pescata;
			}else if(inserimento.equals("oro1")) {
				Carta_Gioco pescata=carteRisorsa[1];
				//posiziono al posto della carta appena pescata una carta dal mazzo
				carteRisorsa[1]=mazzoCarteRisorse.get(0);
				mazzoCarteRisorse.remove(0);
				return pescata;
			}else {
				return null; //scelta non valida
			}
			
		
		
		
	}


	/* 
	public void pescaMercato(Carta_Gioco[] carteRisorsa, Carta_Gioco[] carteOro, Carta_Gioco[] carteInMano){
		System.out.println("Vuoi pescare dal mercato (1) o dal mazzo(2)?");
		Scanner scanner=new Scanner();
		Integer pesca = scanner.nextInt();
		if(pesca.equals(1)){
			System.out.println("1 per pescare da carte Risorsa, 2 per pescare da carte Oro");
			Integer scelta1=scanner.nextInt();
			for(int i=0;i<3;i++){
				if(carteInMano[i].equals(null)){
					if(scelta1.equals(1)) {
						carteInMano[i]=carteRisorsa[scelta];
					} else if(scelta1.equals(2)){
						carteInMano[i]=carteOro[scelta];
					} else {
						System.out.println("Scelta non valida");
					}
				}
			}
	    } else if(pesca.equals(2)) {
			System.out.println("1 per pescare da carte Risorsa, 2 per pescare da carte Oro");
			Integer scelta2=scanner.nextInt();
			for(int i=0;i<3;i++){
				if(carteInMano[i].equals(null)) {
					if(scelta2.equals(1)){
						int pescata1=(int)(Math.random()*mazzoCarteRisorse.size());
						carteInMano[i]=mazzoCarteRisorse.get(pescata1);
					} else if(scelta2.equals(2)) {
						int pescata2=(int)(Math.random()*mazzoCarteOro.size());
						carteInMano[i]=mazzoCarteOro.get(pescata2);
					} else {
						System.out.println("Scelta non valida");
					}
				}
			}
		} else {
			System.out.println("Scelta non valida");
		}


		
		
		

	}
	*/
}
