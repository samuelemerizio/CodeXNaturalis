

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class LetturaFile extends File {
	String[] array;

	public LetturaFile(String pathname) {
		super(pathname);
	}

	/**
	 * Metodo per leggere la riga del file scelta
	 * @param posizioneFile Percorso del file (mettere solo nome se si trova dentro al progetto)
	 * @param IDCartaDaLeggere numero della carta da leggere
	 */
	public String[] scansioneRiga(String posizioneFile,int IDCartaDaLeggere) {

		try (BufferedReader br = new BufferedReader(new FileReader(posizioneFile))) {
			String riga = "";
			int rigaCorrente=0;
			while(rigaCorrente != IDCartaDaLeggere){
				riga = br.readLine();
				rigaCorrente+=1;
			}
	        riga = br.readLine();
	        array = riga.split(";"); 
	        //System.out.println("Carta "+ IDCartaDaLeggere + "" + Arrays.toString(array));
		} catch (IOException e) {
	            e.printStackTrace();
	      	}
		return array;
	} 			
}