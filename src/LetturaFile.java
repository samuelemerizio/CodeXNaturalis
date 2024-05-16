import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class LetturaFile extends File {
	public LetturaFile(String pathname) {
		super(pathname);
	}
	
	public boolean prossimaRiga(String posizioneFile) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(posizioneFile));
		if(br.readLine().equals("FINE")) {
			System.out.println("Non ci sono altre righe");
			return false;
		}else {
			System.out.println("C'è un'altra riga");
			return true;
		}
		
	}
	public String ScansioneColonna(int posizioneDato, String posizioneFile, int IDCartaDaLeggere) {
		String[] array = scansioneRiga(posizioneFile, IDCartaDaLeggere);
		System.out.println("Parametro scelto: "+ array[posizioneDato]);
		return array[posizioneDato];
		
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
	        String[] array = riga.split(";"); 
	        System.out.println("Carta "+ IDCartaDaLeggere + "" + Arrays.toString(array));
	        return array;
		} catch (IOException e) {	
	        e.printStackTrace();
	        return null;
	      	}
	} 			
}
