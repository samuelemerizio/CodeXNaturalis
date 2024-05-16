import java.util.Scanner;

/**
 * Funzioni utili nel programma
 */
public class Funzioni {

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
    
    



}
