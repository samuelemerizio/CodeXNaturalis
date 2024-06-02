package grafica;
/**
 * Classe contenente le dimensioni in pixel di una carta ed il passo per il posizionamento nel manoscritto
 */
public class Carta {
	/** 
	 * Indica la larghezza in pixel della carta
	 */
    public int dx = 200;
    /**
     * Indica l'altezza in pixel della carta
     */
    public int dy = 132;
    /**
     * Indica il passo in direzione X tra 2 carte successive
     */
    public int stepX = 159;
    /**
     * Indica il passo in direzione Y tra 2 carte consecutive   
     */
    public int stepY = 80;
}
