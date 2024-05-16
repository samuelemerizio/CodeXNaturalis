import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;


public final class Schermo_NuovoGiocatore {
    // Creo i pulsanti per lo Schermo_NuovoGiocatore
    private Pulsante[] pbTasti = new Pulsante[26]; 
    private Pulsante pbEsci = new Pulsante(100, Variabili.dyTavolo - 50, 200, 40, "Indietro");
    private Pulsante pbSelezioneCartaIniziale = new Pulsante(Variabili.dxTavolo - 50 - 200, Variabili.dyTavolo - 50, 200, 40, "Avanti");
    private Pulsante pbNomeGiocatore = new Pulsante(120, 30, 595, 95, "");
    private Pulsante[] pbColori = new Pulsante[4];
    private Pulsante pbBackSpace = new Pulsante(570, 460, 195, 65, "Canc");
    private Enums.eColoreGiocatore coloreSelezionato = Enums.eColoreGiocatore.GIALLO;

    public Schermo_NuovoGiocatore(){
        // Istanzio i pulsanti 
        int x;
        int y;
        String testo = "";
        int stepX = 100;
        int stepY = 70;
        // Ciclo for per pitturare la tastiera
        for (int i = 0; i<pbTasti.length; i++){
            // Variabile che distanzia i pulsanti orizzontalmente
            x = (i % 7) * stepX + 70;
            // Variabile che stabilisce quando andare alla riga successiva (ogni 7 pulsanti)
            y = (i / 7) * stepY + 250;
            // Testo scritto nei pulsanti, avanza di lettera in lettera ogni volta che il ciclo è ripetuto
            testo = String.format("%s", (char)('A' + i));
            pbTasti[i] = new Pulsante(x, y, 95, 65, testo);
        }

        //Posiziono i oulsanti delle pedine
        pbColori[0] = new Pulsante(240, 150, 60, 60, "");
        pbColori[1] = new Pulsante(340, 150, 60, 60, "");
        pbColori[2] = new Pulsante(440, 150, 60, 60, "");
        pbColori[3] = new Pulsante(540, 150, 60, 60, "");
    }   

    public void caricaImmaginiPedine(){
        pbColori[0].img = Variabili.imgPedine[0];
        pbColori[1].img = Variabili.imgPedine[1];
        pbColori[2].img = Variabili.imgPedine[2];
        pbColori[3].img = Variabili.imgPedine[3];
        presetColore();
    }
    
    /**
     * Restituisce true se nessun giocatore ha selezionato il colore "colore"
     * @param colore Colore in formato Enums.eColoreGiocatore
     * @return true se il colore è disponibile
     */
    private boolean verificaColoreDisponibile(Enums.eColoreGiocatore colore){
        for (Giocatore giocatore : Variabili.partita.giocatori){
            if (giocatore.colore == colore) return false;
        }
        return true;
    }

    private void presetColore(){
        if (verificaColoreDisponibile(Enums.eColoreGiocatore.ROSSO)) coloreSelezionato = Enums.eColoreGiocatore.ROSSO;
        else if (verificaColoreDisponibile(Enums.eColoreGiocatore.VERDE)) coloreSelezionato = Enums.eColoreGiocatore.VERDE;
        else if (verificaColoreDisponibile(Enums.eColoreGiocatore.BLU)) coloreSelezionato = Enums.eColoreGiocatore.BLU;
        else if (verificaColoreDisponibile(Enums.eColoreGiocatore.GIALLO)) coloreSelezionato = Enums.eColoreGiocatore.GIALLO;
    }

    public void presetGiocatore(){
        Variabili.giocatoreInCreazione = new Giocatore();
        Variabili.giocatoreInCreazione.nome = "";
        Variabili.giocatoreInCreazione.ID = Variabili.partita.giocatori.size();
        Variabili.giocatoreInCreazione.cartaIniziale = Variabili.partita.estraiCartaIniziale(); 
        Variabili.giocatoreInCreazione.ID_carteObiettivoDaScegliere = Variabili.partita.obiettivoGenera();    
    }




    public void paint(Graphics2D g2d){
        // Pitturo immagine di sfondo 
        g2d.drawImage(Variabili.bkMain, 0, 0, Variabili.dxTavolo, Variabili.dyTavolo, null);
        g2d.setColor(new Color(0, 0, 0, 182));
        g2d.fillRect(0, 0, Variabili.dxTavolo, Variabili.dyTavolo);
        // Pitturo i pulsanti
        pbEsci.draw(g2d);
        
        if (Variabili.giocatoreInCreazione.nome.length() > 0){                           
            pbSelezioneCartaIniziale.draw(g2d);
        }
        pbBackSpace.draw(g2d);

        
        pbNomeGiocatore.testo = Variabili.giocatoreInCreazione.nome;
        pbNomeGiocatore.draw(g2d);

        // Ciclo per pitturare i pulsanti della tastiera
        for (Pulsante pb : pbTasti){
            pb.draw(g2d);
        }

        // Verifico la disponibilità del colore
        pbColori[0].visibile = verificaColoreDisponibile(Enums.eColoreGiocatore.ROSSO);
        pbColori[1].visibile = verificaColoreDisponibile(Enums.eColoreGiocatore.VERDE);
        pbColori[2].visibile = verificaColoreDisponibile(Enums.eColoreGiocatore.BLU);
        pbColori[3].visibile = verificaColoreDisponibile(Enums.eColoreGiocatore.GIALLO);

        // Disegno i quattro pulsanti di selezione colore giocatore
        pbColori[0].drawImg(g2d, coloreSelezionato == Enums.eColoreGiocatore.ROSSO);
        pbColori[1].drawImg(g2d, coloreSelezionato == Enums.eColoreGiocatore.VERDE);
        pbColori[2].drawImg(g2d, coloreSelezionato == Enums.eColoreGiocatore.BLU);
        pbColori[3].drawImg(g2d, coloreSelezionato == Enums.eColoreGiocatore.GIALLO);


    }

    public void mousePressed(MouseEvent e){
        if (pbEsci.area.contains(e.getX(), e.getY()))
            if (pbEsci.visibile)
                Variabili.schermoAttivo = Enums.eElencoSchermi.NUOVAPARTITA;   

        for (Pulsante pb : pbTasti){
            if (pb.area.contains(e.getX(), e.getY()))
                if (pb.visibile)
                    Variabili.giocatoreInCreazione.nome += pb.testo;
        }

        if (pbSelezioneCartaIniziale.area.contains(e.getX(), e.getY()))
            if (pbSelezioneCartaIniziale.visibile){
                Variabili.giocatoreInCreazione.colore = coloreSelezionato;
                Variabili.partita.stampaCartePresenti();
                Variabili.schermoAttivo = Enums.eElencoSchermi.SCELTACARTAINIZIALE;   
            }

        if (pbBackSpace.area.contains(e.getX(), e.getY()))
            if (pbBackSpace.visibile)
                Variabili.giocatoreInCreazione.nome = Variabili.giocatoreInCreazione.nome.substring(0, Variabili.giocatoreInCreazione.nome.length()-1);

        if (pbColori[0].area.contains(e.getX(), e.getY()) && pbColori[0].visibile)
            coloreSelezionato = Enums.eColoreGiocatore.ROSSO;

        if (pbColori[1].area.contains(e.getX(), e.getY()) && pbColori[1].visibile)
            coloreSelezionato = Enums.eColoreGiocatore.VERDE;

        if (pbColori[2].area.contains(e.getX(), e.getY()) && pbColori[2].visibile)
            coloreSelezionato = Enums.eColoreGiocatore.BLU;

        if (pbColori[3].area.contains(e.getX(), e.getY()) && pbColori[3].visibile)
            coloreSelezionato = Enums.eColoreGiocatore.GIALLO;
    }
}
