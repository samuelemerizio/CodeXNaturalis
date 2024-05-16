public class Enums {

    /**
     * Identifica il tipo di carta tra Risorsa, Oro ed Iniziale
     */
    public enum eTipoCarta{
        RISORSA, 
        ORO,
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
        NULLO,
        VUOTO,
        FUNGO,
        FOGLIA,
        LUPO,
        FARFALLA,
        PIUMA,
        VASETTO,
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
        NULLO,
        PIUMA,
        VASETTO,
        PERGAMENA,
        ANGOLO;

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
        ORO_2_PIUME,
        ORO_2_VASETTI,
        ORO_2_PERGAMENE,
        ORO_3_SIMBOLI,
        ROSSO_FUNGHI,
        ROSSO_DIAGONALE,
        ROSSO_CAVALLO,
        VERDE_FOGLIE,
        VERDE_DIAGONALE,
        VERDE_CAVALLO,
        BLU_LUPO,
        BLU_DIAGONALE,
        BLU_CAVALLO,
        VIOLA_FARFALLA,
        VIOLA_DIAGONALE,
        VIOLA_CAVALLO;
    }

    /**
     * Identifica il colore della pedina del giocatore
     */
    public enum eColoreGiocatore{
        ROSSO,
        VERDE,
        BLU,
        GIALLO;
    }

    /**
     * Identifica i quattro angoli in senso orario
     */
    public enum eAngolo{
        NO,
        NE,
        SE,
        SO;
    }

    /**
     * Identifica la pagina visualizzata
     */
    public enum eElencoSchermi{
        SPLASHSCREEN,
        INIZIALE,
        NUOVAPARTITA,
        NUOVOGIOCATORE,
        SCELTACARTAINIZIALE,
        SCELTACARTAOBIETTIVO,
        GIOCO,
        PUNTEGGIO;
    }
}

