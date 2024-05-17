import java.awt.Color;
import java.awt.Graphics2D;
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






        }else{
            g2d.drawImage(Variabili.imgTracciatoSegnapunti, 0, 0, 400, Variabili.dyMonitor, null);
        }
        
        // Pitturo i pulsanti 
        pb1.drawCerchio(g2d);
        pb2.drawCerchio(g2d);

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
