package grafica;
import java.awt.Color;
/**
 * Enums utili per lo sviluppo del software
 */
public class Enums {

    /**
     * Identifica il tipo di carta tra Risorsa, Oro ed Iniziale
     */
    public enum eTipoCarta{
    	/** Carta risorsa */
        RISORSA, 
    	/** Carta oro */
        ORO,
    	/** Carta iniziale */
        INIZIALE;

        /**
         * Restituisce l'oggetto di tipo eTipoCarta in funzione del parametro intero valore
         * @param valore Valore da restituire in formato eTipoCarta
         * @return Valore restituito dalla funzione
         */
        public static eTipoCarta getTipoCarta(int valore){
            if (valore == RISORSA.ordinal()){ return RISORSA;
            } else if (valore == ORO.ordinal()){ return ORO;
            } else if (valore == INIZIALE.ordinal()){ return INIZIALE;
            } else return null;
        }
    }

    /**
     * Rappresenta in modo univoco i simboli disponibili sulle carte
     */
    public enum eSimbolo{
    	/** Simbolo nullo*/
        NULLO,
    	/** Simbolo vuoto*/
        VUOTO,
    	/** Simbolo fungo*/
        FUNGO,
    	/** Simbolo foglia*/
        FOGLIA,
    	/** Simbolo lupo*/
        LUPO,
    	/** Simbolo farfalla*/
        FARFALLA,
    	/** Simbolo piuma*/
        PIUMA,
    	/** Simbolo vasetto*/
        VASETTO,
    	/** Simbolo pergamena*/
        PERGAMENA;

        /**
         * Restituisce l'oggetto di tipo eSimbolo in funzione del parametro intero valore
         * @param valore Valore da restituire in formato eSimbolo
         * @return Valore restituito dalla funzione
         */
        public static eSimbolo getSimbolo(int valore){
            if (valore == NULLO.ordinal()){ return NULLO;
            } else if (valore == VUOTO.ordinal()) { return VUOTO;
            } else if (valore == FUNGO.ordinal()) { return FUNGO;
            } else if (valore == FOGLIA.ordinal()) { return FOGLIA;
            } else if (valore == LUPO.ordinal()) { return LUPO;
            } else if (valore == FARFALLA.ordinal()) { return FARFALLA;
            } else if (valore == PIUMA.ordinal()) { return PIUMA;
            } else if (valore == VASETTO.ordinal()) { return VASETTO;
            } else if (valore == PERGAMENA.ordinal()) { return PERGAMENA;
            } else return null;
        } 
    }
    /**
     * Condizione per il moltiplicatorePunti delle carte gioco
     */
    public enum eMoltiplicatorePunti{
    	/** Moltiplicatore nullo */
        NULLO,
    	/** Moltiplicatore piuma */
        PIUMA,
    	/** Moltiplicatore vasetto */
        VASETTO,
    	/** Moltiplicatore pergamena */
        PERGAMENA,
    	/** Moltiplicatore angolo */
        ANGOLO;

    	/**
    	 * Ottiene l'Enum eMoltiplicatore punti della carta obiettivo in base al valore inserito 
    	 * nell'inizializzazione delle carte obiettivo
    	 * @param valore valore inserito nel'inizializzazione
    	 * @return Enums eMoltiplicatorePunti
    	 */
        public static eMoltiplicatorePunti getMoltiplicatorePunti(int valore){
            if (valore == NULLO.ordinal()){ return NULLO;
            } else if (valore == PIUMA.ordinal()){ return PIUMA;
            } else if (valore == VASETTO.ordinal()){ return VASETTO;
            } else if (valore == PERGAMENA.ordinal()){ return PERGAMENA;
            } else if (valore == ANGOLO.ordinal()){ return ANGOLO;
            } else return null;
        }
    }

    /**
     * Identifica le possibili condizioni delle carte obiettivi
     */
    public enum eCondizioneObiettivi{
    	/** Almeno due piume sul manoscritto*/
        ORO_2_PIUME,
    	/** Almeno due vasetti sul manoscritto*/
        ORO_2_VASETTI,
    	/** Almeno due pergamene sul manoscritto*/
        ORO_2_PERGAMENE,
    	/** Almeno tre simboli sul manoscritto*/
        ORO_3_SIMBOLI,
    	/** Almeno tre funghi sul manoscritto*/
        ROSSO_FUNGHI,
    	/** Almeno tre carte rosse in diagonale sul manoscritto*/
        ROSSO_DIAGONALE,
    	/** Almeno due carte rosse in verticale e una del colore richiesto in diagonale sul manoscritto*/
        ROSSO_CAVALLO,
    	/** Almeno tre foglie sul manoscritto*/
        VERDE_FOGLIE,
    	/** Almeno tre carte verdi in diagonale sul manoscritto*/
        VERDE_DIAGONALE,
        /** Almeno due carte verdi in verticale e una del colore richiesto in diagonale sul manoscritto*/
        VERDE_CAVALLO,
    	/** Almeno tre lupi sul manoscritto*/
        BLU_LUPO,
    	/** Almeno tre carte blu in diagonale sul manoscritto*/
        BLU_DIAGONALE,
        /** Almeno due carte blu in verticale e una del colore richiesto in diagonale sul manoscritto*/
        BLU_CAVALLO,
    	/** Almeno tre farfalle sul manoscritto*/
        VIOLA_FARFALLA,
    	/** Almeno tre carte viola in diagonale sul manoscritto*/
        VIOLA_DIAGONALE,
        /** Almeno due carte viola in verticale e una del colore richiesto in diagonale sul manoscritto*/
        VIOLA_CAVALLO;
    }

    /**
     * Identifica il colore della pedina del giocatore
     */
    public enum eColoreGiocatore{
    	/** Giocatore rosso*/
        ROSSO,
        /** Giocatore verde*/
        VERDE,
        /** Giocatore blu*/
        BLU,
        /** Giocatore giallo*/
        GIALLO;

    	/**
    	 * Estrae il colore tramite l'Enum eColoregiocatore
    	 * @param colore Colore ricercato
    	 * @return Colore
    	 */
        public static Color colorFromEnum(Enums.eColoreGiocatore colore){
            switch (colore) {
                case ROSSO: return Color.RED;
                case VERDE: return Color.GREEN;
                case BLU: return Color.BLUE;
                case GIALLO: return Color.YELLOW;
            }
            return Color.BLACK;
        }
    }

    /**
     * Identifica i quattro angoli in senso orario
     */
    public enum eAngolo{
    	/** Direzione NordOvest */
        NO,	
        /** Direzione NordEst */
        NE,
        /** Direzione SudEst */
        SE,
        /** Direzione SudOvest */
        SO;
    }

    /**
     * Identifica la pagina visualizzata
     */
    public enum eElencoSchermi{
    	/** Schermo_SplashScreen*/
        SPLASHSCREEN,
        /** Schermo_Iniziale*/
        INIZIALE,
        /** Schermo_NuovaPartita*/
        NUOVAPARTITA,
        /** Schermo_NuovoGiocatore*/
        NUOVOGIOCATORE,
        /** Schermo_SceltaCartaIniziale*/
        SCELTACARTAINIZIALE,
        /** Schermo_SceltaCartaObiettivo*/
        SCELTACARTAOBIETTIVO,
        /** Schermo_Gioco*/
        GIOCO,
        /** Schermo_Punteggio*/
        PUNTEGGIO;
    }
}

