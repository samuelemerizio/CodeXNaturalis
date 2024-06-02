package grafica;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;


public final class Schermo_Gioco {
    // Creo i pulsanti per lo Schermo_NuovoGiocatore
    private Pulsante pbEsci = new Pulsante(200, Variabili.dyTavolo - 60, 80, 50, "Esci");
    private Pulsante pbConfermaCartaGiocataFronte = new Pulsante(Variabili.dxTavolo - 60 - 200, 10, 250, 40, "Gioca fronte");
    private Pulsante pbConfermaCartaGiocataRetro = new Pulsante(Variabili.dxTavolo - 60 - 200, 60, 250, 40, "Gioca retro");
    private Pulsante pbConfermaCartaRimpiazzare = new Pulsante(Variabili.dxTavolo - 60 - 200, 10, 250, 40, "Prendi rimpiazzo");
    private Pulsante pbFineTurno = new Pulsante(Variabili.dxTavolo - 60 - 200, 10, 250, 40, "Fine turno");

    private Pulsante pbSi = new Pulsante((Variabili.dxTavolo / 2 - 80 - 40) , 400, 80, 40, "SI");
    private Pulsante pbNo = new Pulsante((Variabili.dxTavolo / 2 + 40) , 400, 80, 40, "NO");
    
    private Pulsante pbZoom0 = new Pulsante(20, 676, 28, 28, Variabili.imgZoom0); 
    private Pulsante pbZoom1 = new Pulsante(90, 676, 28, 28, Variabili.imgZoom1); 
    private Pulsante pbZoom2 = new Pulsante(150, 676, 28, 28, Variabili.imgZoom2); 
    
    double xZ, yZ, xC, yC;
    double[] valoriZoom = {1, 0.5, 0.25};
    int indiceZoom = 0;
    Point mouseStart = new Point(0, 0);                         // Coordinata del mouse in cui inizia il trascinamento
    boolean trascinamento = false;                              // Azione di trascinamento tavolo in corso
    boolean menuUscita = false;									// Con true visualizza il menù di uscita




    /**
     * Schermata che contiene il manoscritto del giocatore attuale e la mappa del tavolo
     */
    public Schermo_Gioco(){
       
    }


    public void init(){
        pbZoom0.img = Variabili.imgZoom0;
        pbZoom1.img = Variabili.imgZoom1;
        pbZoom2.img = Variabili.imgZoom2;

        xC = -(40 * Variabili.carta.stepX * Variabili.zoom - Variabili.dxTavolo) / 2;
        yC = -(40 * Variabili.carta.stepY * Variabili.zoom - Variabili.dyTavolo) / 2;
        xZ = xC;
        yZ = yC;
    }

    public void paint(Graphics2D g2d){
        // Ottengo il giocatore attuale
        Giocatore giocatore = Variabili.partita.giocatori.get(Variabili.partita.giocatoreAttuale);
        // Ripulisco lo sfondo
        g2d.drawImage(Variabili.imgTavolo2, 0, 0, null);

        // Memorizzo la trasformata affine attuale
        AffineTransform originaleAt = g2d.getTransform();
        // Creo la nuova trasformata affine 
        AffineTransform at = new AffineTransform();
        // Traslazione del tavolo di gioco rispetto a xZ e yZ
        at.translate(xZ, yZ);
        // Scalatura del campo di gioco
        at.scale(Variabili.zoom, Variabili.zoom);
        // Attivo la trasformazione
        g2d.setTransform(at);
        
        // Disegno griglia
        if (Variabili.DEBUG_MAPPA_ANGOLI)
        {
	        for (int i = 0; i < 40; i++)
	        {
	        	// Disegno linee verticali
	        	g2d.drawLine(i * Variabili.carta.stepX, 0, i * Variabili.carta.stepX, 40 * Variabili.carta.stepY);
	        	// Disegno linee orizzontali
	        	g2d.drawLine(0, i * Variabili.carta.stepY, 40 * Variabili.carta.stepX, i * Variabili.carta.stepY);
	        }
	    }

        // Disegno le carte del manoscritto
        for (int i = 0; i < giocatore.manoscritto.carte.size(); i++){
            // Ottengo la i-esima carta della lista
            Cella_Manoscritto cella = giocatore.manoscritto.carte.get(i);
            // Determino area di disegno
            Rectangle areaT = new Rectangle(0, 0, Variabili.carta.dx, Variabili.carta.dy);
            // Traslo la carta in funzione di riga e colonna 
            areaT.setLocation(Funzioni.coordinateCarta(cella.riga, cella.colonna));
            // Se la carta è stata selezionata dall'operatore la evidenzio
            if (Variabili.partita.indiceCartaManoscrittoScelta == i){
                Grafica.drawCartaGioco(g2d, cella.carta, areaT, true, 8);
                Rectangle r = new Rectangle(areaT.x, areaT.y, areaT.width, areaT.height);
                Grafica.evidenziaAngoloCarta(g2d, r, Variabili.partita.angoloCartaManoscrittoScelta, new Color(0, 255, 0, 170));
            }
            else Grafica.drawCartaGioco(g2d, cella.carta, areaT);

            cella.rectCarta = areaT;
        }
        
        //Se ho attivo modalità DEBUG_SIMBOLI_MAPPA visualizzo sulla carta i simboli acquisiti nella mappa angoli
        if (Variabili.DEBUG_MAPPA_ANGOLI) {
        	Point pt;
        	int offX, offY;
        	giocatore.manoscritto.generaAngoliDaCarte();
        	g2d.setColor(Color.black);
        	Enums.eSimbolo[][][] mas = giocatore.manoscritto.angoli;
        	for (int riga = 0; riga < 78; riga++) {
        		for (int colonna = 0; colonna < 78; colonna++) {
        			offX = (riga % 2 == 1) ? Variabili.carta.dx / 2 : 0;
        			offY = (colonna % 2 == 1) ? Variabili.carta.dy / 2: 0;
        			
        			pt = Funzioni.coordinateCarta(colonna / 2, riga / 2);
        				
        			if (mas[riga][colonna][0] != null) 
        				Grafica.disegnaTesto(g2d, String.format("%d",  mas[riga][colonna][0].ordinal()),
        									pt.x + offX , pt.y + offY, Color.black , Color.white, 0, 0, 20, false);
    				if (mas[riga][colonna][1] != null) 
        				Grafica.disegnaTesto(g2d, String.format("%d",  mas[riga][colonna][1].ordinal()),
        									pt.x + offX +20, pt.y + offY, Color.black , Color.white, 0, 0, 20, false);
    				if (mas[riga][colonna][2] != null) 
        				Grafica.disegnaTesto(g2d, String.format("%d",  mas[riga][colonna][2].ordinal()),
        									pt.x + offX + 40, pt.y + offY, Color.black , Color.white, 0, 0, 20, false);
        				
        			
        			
        		}
        			
        	}
        	
        }
        
        
        // Ripristino la trasformata affine originale per disegnare gli oggetti grafici che non traslano con la carta
        g2d.setTransform(originaleAt);

        // Disegno i pulsanti
        pbEsci.draw(g2d);
        pbConfermaCartaGiocataFronte.visibile = Variabili.partita.selezioneCarteGiocoRetroValida & Variabili.partita.selezioneCarteGiocoFronteValida &
                                            Variabili.partita.faseDelGiocatore == 0;
        pbConfermaCartaGiocataFronte.draw(g2d);

        pbConfermaCartaGiocataRetro.visibile = Variabili.partita.selezioneCarteGiocoRetroValida & Variabili.partita.faseDelGiocatore == 0;
        pbConfermaCartaGiocataRetro.draw(g2d);

        pbConfermaCartaRimpiazzare.visibile = Variabili.partita.selezioneCartaRimpiazzoValida & Variabili.partita.faseDelGiocatore == 1;
        pbConfermaCartaRimpiazzare.draw(g2d);

        pbFineTurno.visibile = Variabili.partita.faseDelGiocatore == 2;
        if (Variabili.partita.ultimoTurno && Variabili.partita.giocatoreAttuale == Variabili.partita.giocatori.size()-1)
            pbFineTurno.testo = "Fine partita";
        pbFineTurno.draw(g2d);

        // Sfondo per la mappa
        g2d.drawImage(Variabili.imgCartaObiettivoRetro, 0, Variabili.dyTavolo - Variabili.carta.dy, Variabili.carta.dx, Variabili.carta.dy, null);

        //Disegno il testo della fase attuale per il giocatore
        switch (Variabili.partita.faseDelGiocatore)
        {
            case 0:		//Fase di posizionamento carta gioco sul tavolo
                Grafica.disegnaTesto(g2d, giocatore.nome + " devi posizionare una carta gioco", 5, 5, Color.black, Enums.eColoreGiocatore.colorFromEnum(giocatore.colore), 0, 0, 30, false);
                break;
            case 1:		//Fase di rimpiazzo carta utilizzata
                Grafica.disegnaTesto(g2d, String.format("%s hai ottenuto %d %s", giocatore.nome, Variabili.partita.punteggioTurno, Variabili.partita.punteggioTurno == 1 ? "punto" : "punti") , 5, 5, Color.black, Enums.eColoreGiocatore.colorFromEnum(giocatore.colore), 0, 0, 30, false);
                Grafica.disegnaTesto(g2d, "Seleziona una carta dal mercato", 5, 45, Color.black, Enums.eColoreGiocatore.colorFromEnum(giocatore.colore), 0, 0, 30, false);
                break;
            case 2:		//Visualizzazione punti e fine turno
                Grafica.disegnaTesto(g2d, String.format("%s hai ottenuto %d punti", giocatore.nome, Variabili.partita.punteggioTurno), 5, 5, Color.black, Enums.eColoreGiocatore.colorFromEnum(giocatore.colore), 0, 0, 30, false);
                break;
        }

        //Disegno mappa del tavolo, creo una nuova trasformaione
		//Creo la nuova trasformazione affine 
		AffineTransform atM = new AffineTransform();
		//Traslo il tavolo di gioco
		atM.translate(20, (Variabili.dyTavolo - Variabili.mappaTavola.height * 0.025) - 15);
		
		//Scalo il tavolo di gioco
		atM.scale(0.025, 0.025);
		//Attivo la trasformazione
		g2d.setTransform(atM);
		
		
		g2d.setColor(new Color(0, 0, 0, 255));
		g2d.fillRect(0, 0, Variabili.mappaTavola.width, Variabili.mappaTavola.height);
		g2d.setColor(Color.yellow);
		
		// Disegno le carte del manoscritto
        for (int i = 0; i < giocatore.manoscritto.carte.size(); i++){
            // Ottengo la i-esima carta della lista
            Cella_Manoscritto cella = giocatore.manoscritto.carte.get(i);
            // Determino area di disegno
            Rectangle areaT = new Rectangle(0, 0, Variabili.carta.dx, Variabili.carta.dy);
            // Traslo la carta in funzione di riga e colonna 
            areaT.setLocation(Funzioni.coordinateCarta(cella.riga, cella.colonna));
            // Se la carta è stata selezionata dall'operatore la evidenzio
            if (Variabili.partita.indiceCartaManoscrittoScelta == i){
                Grafica.drawCartaGioco(g2d, cella.carta, areaT, true, 8);
                Rectangle r = new Rectangle(areaT.x, areaT.y, areaT.width, areaT.height);
                Grafica.evidenziaAngoloCarta(g2d, r, Variabili.partita.angoloCartaManoscrittoScelta, new Color(0, 255, 0, 170));
            }
            else Grafica.drawCartaGioco(g2d, cella.carta, areaT);

            cella.rectCarta = areaT;
        }
		
		
		g2d.setStroke(new BasicStroke(10));
		int mapX = (int)(Variabili.dxTavolo / Variabili.zoom);
		int mapY = (int)(Variabili.dyTavolo / Variabili.zoom);
			
		//System.out.println(String.format("Zoom: %.2f, mapX %d , mapY %d xZ %.1f, yZ %.1f",Variabili.zoom, mapX, mapY, xZ / Variabili.zoom, yZ / Variabili.zoom));
		g2d.setColor(Color.green);
		//g2d.drawRect(+Variabili.mappaTavola.width  +(int)(xZ / Variabili.zoom), Variabili.mappaTavola.height +(int)(yZ / Variabili.zoom), mapX, mapY);	
		g2d.drawRect(-(int)(xZ / Variabili.zoom), -(int)(yZ / Variabili.zoom), mapX, mapY);
		
		g2d.setTransform(originaleAt);







        pbZoom0.drawImg(g2d);
        pbZoom1.drawImg(g2d);
        pbZoom2.drawImg(g2d);
        g2d.setStroke(new BasicStroke(1));

        // Scritta ultimo turno
        if (Variabili.partita.ultimoTurno) 
            Grafica.disegnaTesto(g2d, "Ultimo turno di gioco", Variabili.dxTavolo, Variabili.dyTavolo - 40, Color.CYAN, Color.BLACK, 2, 0, 30, true);

        
        //Menu uscita
        if (menuUscita) {
        	g2d.setColor(new Color(0,0,0,196));
        	g2d.fillRect(0, 0, Variabili.dxMonitor, Variabili.dyMonitor);
        	g2d.setColor(new Color(0,0,0,100));
        	g2d.fillRect(50, 150, Variabili.dxTavolo - 2 * 50, 350);
        	g2d.setColor(new Color(0, 255, 255,196));
        	g2d.drawRect(50, 150, Variabili.dxTavolo - 2 * 50, 350);
        	pbSi.draw(g2d);
        	pbNo.draw(g2d);
        	Grafica.disegnaTesto(g2d, "Vuoi terminare la partita ?", Variabili.dxTavolo / 2, 200, Color.CYAN, Color.BLACK, 1, 0, 50, true);      	
        }
        
   }

    public void mousePressed(MouseEvent e){
        int xM = e.getX();
        int yM = e.getY();
        
        if (menuUscita) {
        	if (pbSi.area.contains(xM, yM)) {
        		Variabili.schermoAttivo = Enums.eElencoSchermi.INIZIALE;  
        		menuUscita = false;
                Variabili.partita = null;
        	}
        	if (pbNo.area.contains(xM, yM)) {
        		menuUscita = false;
        	}
        	return;
        }
        
        if (pbEsci.area.contains(e.getX(), e.getY()))
            if (pbEsci.visibile){
                menuUscita = true;
                return;
            }    

        // Pulsante zoom0
        if (pbZoom0.area.contains(xM, yM)){
            indiceZoom = 0;
            Variabili.zoom = valoriZoom[indiceZoom];
            init();
        }
        // Pulsante zoom1
        if (pbZoom1.area.contains(xM, yM)){
            indiceZoom = 1;
            Variabili.zoom = valoriZoom[indiceZoom];
            init();
        }
        // Pulsante zoom2
        if (pbZoom2.area.contains(xM, yM)){
            indiceZoom = 2;
            Variabili.zoom = valoriZoom[indiceZoom];
            init();
        }

        // Pulsante posiziona carta fronte
        if (pbConfermaCartaGiocataFronte.area.contains(xM, yM))
            if (pbConfermaCartaGiocataFronte.visibile)
                Variabili.partita.daGiocatoreAManoscritto(true);
            
        // Pulsante posiziona carta retro
        if (pbConfermaCartaGiocataRetro.area.contains(xM, yM))
            if (pbConfermaCartaGiocataRetro.visibile)
                Variabili.partita.daGiocatoreAManoscritto(false);

        // Pulsante rimpiazzaCarta
        if (pbConfermaCartaRimpiazzare.area.contains(xM, yM))
            if (pbConfermaCartaRimpiazzare.visibile)
                Variabili.partita.daMercatoAGiocatore();

        // Pulsante fine turno
        if (pbFineTurno.area.contains(xM, yM))
            if (pbFineTurno.visibile)
                Variabili.partita.prossimoGiocatore();

        // Verifico se ho cliccato su una carta del manoscritto
        if (e.getButton() == MouseEvent.BUTTON1){
            if (Variabili.partita.faseDelGiocatore == 0){
                Rectangle tavoloRect = new Rectangle(0, 0, Variabili.dxTavolo, Variabili.dyTavolo);
                if (tavoloRect.contains(xM, yM))
                    Variabili.partita.indiceCartaManoscrittoScelta = ottieniIndiceEdAngoloCartaCliccata(xM, yM);
                Variabili.partita.valutaCarteScelte();
            }
        }

        // Verifica trascinamento tavolo
        if (e.getButton() == MouseEvent.BUTTON3){
            mouseStart.x = e.getX();
            mouseStart.y = e.getY();
            trascinamento = true;

        }

    }

    public void mouseReleased(MouseEvent e){
        if (Variabili.schermoAttivo == Enums.eElencoSchermi.GIOCO){
            if (trascinamento){
                trascinamento = false;
                xZ -= (mouseStart.x - e.getX());
                yZ -= (mouseStart.y - e.getY());
                limitaTraslazione();
            }
        }
    }


    public void mouseDragged(MouseEvent e){
        if (Variabili.schermoAttivo == Enums.eElencoSchermi.GIOCO){
            if (trascinamento){
                xZ -= (mouseStart.x - e.getX());
                yZ -= (mouseStart.y - e.getY());
                limitaTraslazione();

                // Imposto le coordinate di start del trascinamento alle coordinate attuali
                mouseStart.x = e.getX();
                mouseStart.y = e.getY();
            }
        }
    }

    /**
     * Determina se ho cliccato su una carta del manoscritto
     * @param xM Coordinata x mouse
     * @param yM Coordinata y mouse
     * @return Restituisce l'indice della carta nel manoscritto su cui si è cliccato
     *          Restituisce -1 nel caso in cui non ho cliccato su una carta
     */
    private int ottieniIndiceEdAngoloCartaCliccata(int xM, int yM){
        // Determino la posizione del mouse in funzione dello zoom e della traslazione tavola
        double xR = (xM - xZ) / Variabili.zoom;
        double yR = (yM - yZ) / Variabili.zoom;
        
        Giocatore gio = Variabili.partita.giocatori.get(Variabili.partita.giocatoreAttuale);
        // Verifico le carte del manoscritto partendo dall'ultima carta posizionata
        for (int i = gio.manoscritto.carte.size() - 1; i >= 0; i--){
            Rectangle r = gio.manoscritto.carte.get(i).rectCarta;
            if (r.contains(xR, yR)){
                boolean sx = false;
                boolean up = false;
                // Verifico in quale quadrante della carta ho cliccato
                if (xR < r.x + r.width / 2) sx = true;
                if (yR < r.y + r.height / 2) up = true;
                // Ottengo l'angolo su cui ho cliccato dato il quadrante
                if (sx && up) Variabili.partita.angoloCartaManoscrittoScelta = Enums.eAngolo.NO;
                if (!sx && up) Variabili.partita.angoloCartaManoscrittoScelta = Enums.eAngolo.NE;
                if (!sx && !up) Variabili.partita.angoloCartaManoscrittoScelta = Enums.eAngolo.SE;
                if (sx && !up) Variabili.partita.angoloCartaManoscrittoScelta = Enums.eAngolo.SO;
                return i;

            }
        }

        return -1;
    }

    /**
     * Limita la traslazione della tavola nello spazio visibile richiesto considerando anche il fattore di zoom
     */
    private void limitaTraslazione(){
        double maxX = 40 * Variabili.carta.stepX * Variabili.zoom - Variabili.dxTavolo;
        double maxY = 40 * Variabili.carta.stepY * Variabili.zoom - Variabili.dyTavolo;

        if (xZ < -maxX) xZ = -maxX;
        else if (xZ > 0) xZ = 0;

        if (yZ < -maxY) yZ = -maxY;
        else if (yZ > 0) yZ = 0;

    }


}
