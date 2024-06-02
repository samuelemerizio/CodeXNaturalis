import java.util.Scanner;

/**
 * Classe principale del programma
 */
public class CodeX{
    
    public static void main(String[] args) throws Exception {
    	boolean sceltaNonValida = true;
    	
    	System.out.println("");
    	System.out.println("		CodeX Naturalis");
    	System.out.println("");
    	System.out.println("		programmato da:");
    	System.out.println("");
    	System.out.println("		Jacopo Marinelli");
    	System.out.println("		 Luca Zucchetti");
    	System.out.println("		Samuele  Merizio");
    	System.out.println("");
    	
    	Scanner sc;
    	String risposta = "";
    	
    	while (sceltaNonValida) {
    		System.out.println("");
	    	System.out.println(" ---------------------------------------------------------");
	    	System.out.println("Sono disponibili 2 modalità di gioco: da console o grafica");
	    	System.out.println("	Digitare 'C' per versione console");
	    	System.out.println("	Digitare 'G' per versione grafica");
	    	System.out.println("");
	    	System.out.println("	Digitare 'E' per uscire dal gioco");
	    	System.out.println("");
	    	sc = new Scanner(System.in);
	    	risposta = sc.nextLine().toUpperCase();
	    	switch (risposta) 
	    	{
		    	case "C":
		    		sceltaNonValida = false;
		    		new console.MainConsole();
		    		break;
		    	case "G":
		    		sceltaNonValida = false;
		    		new grafica.MainGrafica();
		    		break;
		    	case "E":
		    		sceltaNonValida = false;
		    		break;
		    	default:
		    		System.out.println("ATTENZIONE: hai digitato una scelta errata. Riprova");
		    		sc = new Scanner(System.in);
		    		break;
	    	} 	    	
    	}
    }
    
    
    

}
