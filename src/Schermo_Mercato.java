import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;



public final class Schermo_Mercato {
    // Creo i pulsanti per lo Schermo_Mercato
    private Pulsante pb1 = new Pulsante(180, 10, 15, 15, "");
    private Pulsante pb2 = new Pulsante(205, 10, 15, 15, "");
    public double zoomCarte = 0.75;
    // Rettangoli che contengono le posizioni delle carte
    Rectangle[] areeCarte = new Rectangle[12];

	Point[] coordinatePunti = new Point[]{ new Point(947 - 840 , 745), new Point(1039 - 840, 745), new Point(1132 - 840, 745),
			new Point(1179 - 840 , 662), new Point(1087 - 840, 662), new Point(992 - 840, 662), new Point(900 - 840, 662),
			new Point(900 - 840 , 580), new Point(992 - 840, 580), new Point(1087 - 840, 580), new Point(1179 - 840, 580),
			new Point(1179 - 840 , 496), new Point(1087 - 840, 496), new Point(992 - 840, 496), new Point(900 - 840, 496),
			new Point(900 - 840 , 418), new Point(992 - 840, 418), new Point(1087 - 840, 418), new Point(1179 - 840, 418),
			new Point(1179 - 840 , 335), new Point(1040 - 840, 291), new Point(898 - 840, 335),
			
			new Point(898 - 840 , 249), new Point(898 - 840, 169), new Point(952 - 840, 101),
			new Point(1040 - 840 , 84), new Point(1125 - 840, 99), new Point(1179 - 840, 168),
			new Point(1180 - 840 , 248), new Point(1040 - 840, 186)};

    enum eCarteMercato{
		MAZZORISORSE,
		MAZZOORO,
		RISORSA1,
		RISORSA2,
		ORO1,
		ORO2,
		OBIETTIVO1,
		OBIETTIVO2,
		GIOCO1,
		GIOCO2,
		GIOCO3,
		OBIETTIVO
	}


    public Schermo_Mercato(){

    }

    public void paint(Graphics2D g2d){
		// Attivo giocatore attuale
		if (Variabili.partita == null) return;

		if (Variabili.schermoAttivo == Enums.eElencoSchermi.PUNTEGGIO) Variabili.mostraMercato = false;



		Giocatore giocatore = new Giocatore();
		if (Variabili.partita.giocatori.size() > 0){
			giocatore = Variabili.partita.giocatori.get(Variabili.partita.giocatoreAttuale);
		}
        // Dichiaro le variabili per spostare il sistema di riferimento delle coordinate per la grafica
        AffineTransform originaleAt = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        // Modifico il sistema di riferimento
        at.translate(Variabili.dxTavolo, 0);
        g2d.setTransform(at);

        // Condizione per stabilire quale immagine visualizzare
        if (Variabili.mostraMercato){     	
            //Disegno il mercato e le carte del giocatore
	    	int largCarta = (int)(Variabili.carta.dx * zoomCarte);
	    	int altCarta = (int)(Variabili.carta.dy * zoomCarte);
	    	areeCarte[eCarteMercato.MAZZORISORSE.ordinal()] = new Rectangle(45, 60, largCarta, altCarta);
	    	areeCarte[eCarteMercato.MAZZOORO.ordinal()] = new Rectangle(400 - 40 - largCarta, areeCarte[eCarteMercato.MAZZORISORSE.ordinal()].y, largCarta, altCarta);
	    	areeCarte[eCarteMercato.RISORSA1.ordinal()] = new Rectangle(45, 170, largCarta, altCarta);
	    	areeCarte[eCarteMercato.RISORSA2.ordinal()] = new Rectangle(45, 170 + 105, largCarta, altCarta);
	    	areeCarte[eCarteMercato.ORO1.ordinal()] = new Rectangle(400 - 40 - largCarta, 170, largCarta, altCarta);
	    	areeCarte[eCarteMercato.ORO2.ordinal()] = new Rectangle(400 - 40 - largCarta, 170 + 105, largCarta, altCarta);
	    	areeCarte[eCarteMercato.OBIETTIVO1.ordinal()] = new Rectangle(45, 405, largCarta, altCarta);
	    	areeCarte[eCarteMercato.OBIETTIVO2.ordinal()] = new Rectangle(400 - 40 - largCarta, 405, largCarta, altCarta);
	    	areeCarte[eCarteMercato.GIOCO1.ordinal()] = new Rectangle(45, 535, largCarta, altCarta);
	    	areeCarte[eCarteMercato.GIOCO2.ordinal()] = new Rectangle(45, 535 + 105, largCarta, altCarta);
	    	areeCarte[eCarteMercato.GIOCO3.ordinal()] = new Rectangle(400 - 40 - largCarta, 535, largCarta, altCarta);
	    	areeCarte[eCarteMercato.OBIETTIVO.ordinal()] = new Rectangle(400 - 40 - largCarta, 535 + 105, largCarta, altCarta);

            g2d.drawImage(Variabili.imgSfondoMercato, 0, 0, 400, Variabili.dyMonitor, null);
            
            
            
        	//Titolo area
		    Grafica.disegnaTesto(g2d, "Risorse - Oro", 200, areeCarte[eCarteMercato.MAZZORISORSE.ordinal()].y - 5, Color.LIGHT_GRAY, Color.BLACK, 1, 2, 20, false);
		    //Mazzo risorse
		    Grafica.drawMazzo(g2d, areeCarte[eCarteMercato.MAZZORISORSE.ordinal()]);
		    if (Variabili.partita.indiceCartaMercatoRimpiazzo == 0 && Variabili.partita.faseDelGiocatore == 1)
                Grafica.evidenziaCarta(g2d, areeCarte[eCarteMercato.MAZZORISORSE.ordinal()], Color.GREEN, 7);		    		    
		    Grafica.drawCarta(g2d, retroCartaDaID(Variabili.partita.mercato.mazzoCarteRisorse.get(0)), areeCarte[eCarteMercato.MAZZORISORSE.ordinal()]);

		    //Mazzo oro
		    Grafica.drawMazzo(g2d, areeCarte[eCarteMercato.MAZZOORO.ordinal()]);
		    if (Variabili.partita.indiceCartaMercatoRimpiazzo == 1 && Variabili.partita.faseDelGiocatore == 1)
                Grafica.evidenziaCarta(g2d, areeCarte[eCarteMercato.MAZZOORO.ordinal()], Color.green, 7);		    
            Grafica.drawCarta(g2d,  retroCartaDaID(Variabili.partita.mercato.mazzoCarteOro.get(0)), areeCarte[eCarteMercato.MAZZOORO.ordinal()]);

		    //Disegno carte risorsa
		    if (Variabili.partita.indiceCartaMercatoRimpiazzo == 2 && Variabili.partita.faseDelGiocatore == 1)
                Grafica.evidenziaCarta(g2d, areeCarte[eCarteMercato.RISORSA1.ordinal()], Color.green, 7);	
            Grafica.drawCartaGioco(g2d, Variabili.partita.mercato.carteRisorsa[0], areeCarte[eCarteMercato.RISORSA1.ordinal()]);
		    if (Variabili.partita.indiceCartaMercatoRimpiazzo == 4 && Variabili.partita.faseDelGiocatore == 1)
                Grafica.evidenziaCarta(g2d, areeCarte[eCarteMercato.RISORSA2.ordinal()], Color.green, 7);	
		    Grafica.drawCartaGioco(g2d, Variabili.partita.mercato.carteRisorsa[1], areeCarte[eCarteMercato.RISORSA2.ordinal()]);

		    //Disegno carte oro
		    if (Variabili.partita.indiceCartaMercatoRimpiazzo == 3 && Variabili.partita.faseDelGiocatore == 1)
                Grafica.evidenziaCarta(g2d, areeCarte[eCarteMercato.ORO1.ordinal()], Color.green, 7);	
		    Grafica.drawCartaGioco(g2d, Variabili.partita.mercato.carteOro[0], areeCarte[eCarteMercato.ORO1.ordinal()]);
		    if (Variabili.partita.indiceCartaMercatoRimpiazzo == 5 && Variabili.partita.faseDelGiocatore == 1)
                Grafica.evidenziaCarta(g2d, areeCarte[eCarteMercato.ORO2.ordinal()], Color.green, 7);	
		    Grafica.drawCartaGioco(g2d, Variabili.partita.mercato.carteOro[1], areeCarte[eCarteMercato.ORO2.ordinal()]);

			//Titolo 
			Grafica.disegnaTesto(g2d, "Obiettivi comuni", 200, areeCarte[eCarteMercato.OBIETTIVO1.ordinal()].y - 5, Color.LIGHT_GRAY, Color.BLACK, 1, 2, 20, false);
			//Disegno le carte obiettivi comuni
			Grafica.drawCartaObiettivo(g2d, Variabili.mazzoCarteObiettivo [Variabili.partita.obiettiviComuni[0]], areeCarte[eCarteMercato.OBIETTIVO1.ordinal()], new Color(0,0,0,0), new Color(0,0,0,0));
			Grafica.drawCartaObiettivo(g2d, Variabili.mazzoCarteObiettivo [Variabili.partita.obiettiviComuni[1]], areeCarte[eCarteMercato.OBIETTIVO2.ordinal()], new Color(0,0,0,0), new Color(0,0,0,0));

			// Disegno le carte gioco del giocatore attuale
			if (Variabili.partita.giocoInCorso){
				Grafica.drawCartaGioco(g2d, giocatore.carteInMano[0], areeCarte[eCarteMercato.GIOCO1.ordinal()],
				 						Variabili.partita.indiceCartaGiocoScelta == 0 && Variabili.partita.giocoInCorso, 8);
				Grafica.drawCartaGioco(g2d, giocatore.carteInMano[1], areeCarte[eCarteMercato.GIOCO2.ordinal()],
										 Variabili.partita.indiceCartaGiocoScelta == 1 && Variabili.partita.giocoInCorso, 8);
				Grafica.drawCartaGioco(g2d, giocatore.carteInMano[2], areeCarte[eCarteMercato.GIOCO3.ordinal()],
										 Variabili.partita.indiceCartaGiocoScelta == 2 && Variabili.partita.giocoInCorso, 8);
				// Titolo area
				Grafica.disegnaTesto(g2d, "Carte giocatore", 200, areeCarte[eCarteMercato.GIOCO1.ordinal()].y -5, Color.LIGHT_GRAY, Color.BLACK, 1, 2, 20, false);

				// Disegno carta obiettivo
				Grafica.drawCartaObiettivo(g2d, giocatore.cartaObiettivo, areeCarte[eCarteMercato.OBIETTIVO.ordinal()], new Color(0, 0, 0, 0), new Color(0, 0, 0, 0));
			}
			
			if (Variabili.partita.giocoInCorso && Variabili.schermoAttivo != Enums.eElencoSchermi.PUNTEGGIO)
				Grafica.disegnaTesto(g2d, String.format("%s : punti %d", giocatore.nome, giocatore.punteggio + giocatore.puntiObiettivoComune1 + giocatore.puntiObiettivoComune2 + giocatore.puntiObiettivoSegreto),
														 200, Variabili.dyTavolo - 5, Color.BLACK, Color.YELLOW, 1, 2, 22, false);
	       
        }
        else
        {        	
            g2d.drawImage(Variabili.imgTracciatoSegnapunti, 0, 0, 400, Variabili.dyMonitor, null);

			if (Variabili.partita != null){
				for (Giocatore gio : Variabili.partita.giocatori){
					int punti = gio.punteggio + gio.puntiObiettivoComune1 + gio.puntiObiettivoComune2 + gio.puntiObiettivoSegreto;
					int xP, yP;
					if (punti > 29) punti = 29;
					xP = coordinatePunti[punti].x + (gio.ID < 2 ? 0 : -28);
					yP = coordinatePunti[punti].y + (gio.ID == 1 || gio.ID == 2 ? 0 : -28);
					
					
					g2d.drawImage(Variabili.imgPedine[gio.colore.ordinal()], xP, yP, null);
					if (gio.ID == 0)
					{
						g2d.setColor(Color.black);
						g2d.setStroke(new BasicStroke(6.0f));
						g2d.drawOval(xP, yP, 28, 28);
						g2d.setStroke(new BasicStroke(1.0f));
					}
				}
			}
        }
        
		 // Pitturo i pulsanti 
        pb1.drawCerchio(g2d);
        pb2.drawCerchio(g2d);
        
        g2d.setColor(Color.green);
        if (Variabili.mostraMercato) 
        	//Evidenzio pulsante mappa o mercato
        	g2d.fillOval(182, 12, 11, 11);
        else
            //Evidenzio tabellone punti
        	g2d.fillOval(207, 12, 11, 11);
        
        

        // Ripristino le coordinate originali
        g2d.setTransform(originaleAt);
    }

    public void mousePressed(MouseEvent e){
        // Variabili per le coordinate dei pulsanti
        int xM = e.getX() - Variabili.dxTavolo;
        int yM = e.getY();
        // Condizione per verificare se il click è avvenuto sul pulsante
        if (pb1.area.contains(xM, yM)) 
            // Cambia il valore della variabile booleana che stabilisce quale delle due schermate visualizzare
            Variabili.mostraMercato = !Variabili.mostraMercato;
        // Condizione per verificare se il click è avvenuto sul pulsante
        if (pb2.area.contains(xM, yM))
            // Cambia il valore della variabile booleana che stabilisce quale delle due schermate visualizzare
            Variabili.mostraMercato = !Variabili.mostraMercato;

		// Verifico se ho clicato su una carta gioco
		if (Variabili.mostraMercato){
			if (Variabili.partita.faseDelGiocatore == 0 && Variabili.partita.giocoInCorso)
			{
				if (areeCarte[eCarteMercato.GIOCO1.ordinal()].contains(xM, yM))
					Variabili.partita.indiceCartaGiocoScelta = 0;
				if (areeCarte[eCarteMercato.GIOCO2.ordinal()].contains(xM, yM))
					Variabili.partita.indiceCartaGiocoScelta = 1;
				if (areeCarte[eCarteMercato.GIOCO3.ordinal()].contains(xM, yM))
					Variabili.partita.indiceCartaGiocoScelta = 2;

				if (Variabili.partita.giocoInCorso)
					Variabili.partita.valutaCarteScelte();
			}

			if (Variabili.partita.faseDelGiocatore == 1)
			{
				if (areeCarte[eCarteMercato.MAZZORISORSE.ordinal()].contains(xM, yM)) verificaCartaRimpiazzo(eCarteMercato.MAZZORISORSE);
				if (areeCarte[eCarteMercato.MAZZOORO.ordinal()].contains(xM, yM)) verificaCartaRimpiazzo(eCarteMercato.MAZZOORO);
				if (areeCarte[eCarteMercato.RISORSA1.ordinal()].contains(xM, yM)) verificaCartaRimpiazzo(eCarteMercato.RISORSA1);
				if (areeCarte[eCarteMercato.ORO1.ordinal()].contains(xM, yM)) verificaCartaRimpiazzo(eCarteMercato.ORO1);
				if (areeCarte[eCarteMercato.RISORSA2.ordinal()].contains(xM, yM)) verificaCartaRimpiazzo(eCarteMercato.RISORSA2);
				if (areeCarte[eCarteMercato.ORO2.ordinal()].contains(xM, yM)) verificaCartaRimpiazzo(eCarteMercato.ORO2);

			}

		}

    }

	private void verificaCartaRimpiazzo(eCarteMercato cM){
		// Determino quale carta rimpiazzare
		int qualeCartaRimpiazzare = -1;
		if (Variabili.partita.giocatori.get(Variabili.partita.giocatoreAttuale).carteInMano[0] == null)
			qualeCartaRimpiazzare = 0;
		if (Variabili.partita.giocatori.get(Variabili.partita.giocatoreAttuale).carteInMano[1] == null)
			qualeCartaRimpiazzare = 1;
		if (Variabili.partita.giocatori.get(Variabili.partita.giocatoreAttuale).carteInMano[2] == null)
			qualeCartaRimpiazzare = 2;

		// Imposto la carta selezionata come valida
		Variabili.partita.selezioneCartaRimpiazzoValida = true;

		int qualeCartaRimpiazzo = -1;

		switch (cM) {
			case MAZZORISORSE:
				qualeCartaRimpiazzo = 0;
				break;
			case MAZZOORO:
				qualeCartaRimpiazzo = 1;
				break;
			case RISORSA1:
				qualeCartaRimpiazzo = 2;
				break;
			case ORO1:
				qualeCartaRimpiazzo = 3;
				break;
			case RISORSA2:
				qualeCartaRimpiazzo = 4;
				break;
			case ORO2:
				qualeCartaRimpiazzo = 5;
				break;
		
			default:
				break;
		}

		if (Variabili.partita.selezioneCartaRimpiazzoValida)
			Variabili.partita.indiceCartaMercatoRimpiazzo = qualeCartaRimpiazzo;
		else 
			Variabili.partita.indiceCartaMercatoRimpiazzo = -1;

	}

    private BufferedImage retroCartaDaID(Carta_Gioco carta)
	{
		switch (carta.simboloRetro[0]) {
			case FUNGO:
				if (carta.tipo == Enums.eTipoCarta.RISORSA) {
					return Variabili.imgCartaRisorsaRossaRetro;
				} else {
					return Variabili.imgCartaOroRossaRetro;
				}
			case FOGLIA:
				if (carta.tipo == Enums.eTipoCarta.RISORSA) {
					return Variabili.imgCartaRisorsaVerdeRetro;
				} else {
					return Variabili.imgCartaOroVerdeRetro;
				}
			case LUPO:
				if (carta.tipo == Enums.eTipoCarta.RISORSA) {
					return Variabili.imgCartaRisorsaBluRetro;
				} else {
					return Variabili.imgCartaOroBluRetro;
				}
			case FARFALLA:
				if (carta.tipo == Enums.eTipoCarta.RISORSA) {
					return Variabili.imgCartaRisorsaViolaRetro;
				} else {
					return Variabili.imgCartaOroViolaRetro;
				}
			default:
				return null;
		}
	}


}
