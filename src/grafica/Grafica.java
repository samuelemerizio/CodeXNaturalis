package grafica;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public final class Grafica {
	public static final void disegnaTesto(Graphics2D g, String testo, int x, int y, Color bkColor, Color foColor, int align, int lineAlign, int fontSize, boolean bold) {
		g.setColor(bkColor);
		// Condizioni per stabilire se applicare il "grassetto" al testo
		if (bold) g.setFont(new Font("Arial", Font.BOLD , fontSize));
		else g.setFont(new Font("Arial", Font.PLAIN , fontSize));
		
		// Variabili per memorizzare le dimensioni del testo
		int sLen = (int)g.getFontMetrics().getStringBounds(testo, g).getWidth();
		int sHe = (int)g.getFontMetrics().getStringBounds(testo, g).getHeight();
		FontMetrics fm = g.getFontMetrics();
		sHe = fm.getAscent() - 1;
		// switch per stabilire che tipo di allineamento applicare al testo contenuto nel pulsante
		switch (align)
		{
			case 1:
				x -= sLen / 2;
				break;
			case 2:
				x -= sLen;
				break;
		}

		switch (lineAlign)
		{
			case 0:
				y += sHe - 1;
				break;
			case 1:
				y += sHe / 2 - 1;
				break;
		}
		// Pitturo le stringhe necessarie alla visualizzazione del testo
		g.drawString(testo, x - 1, y - 1);
		g.drawString(testo, x - 1, y + 1);
		g.drawString(testo, x + 1, y - 1);
		g.drawString(testo, x + 1, y + 1);
		g.setColor(foColor);
		g.drawString(testo, x, y);
	}

	public static final void disegnaTesto(Graphics2D g, String testo, int x, int y, Color bkColor, Color foColor, int align, int lineAlign, double angolo) {

		g.setFont(new Font("Arial", Font.BOLD , 40));

		int sLen = (int)g.getFontMetrics().getStringBounds(testo, g).getWidth();
		int sHe = (int)g.getFontMetrics().getStringBounds(testo, g).getHeight();
		FontMetrics fm = g.getFontMetrics();
		sHe = fm.getAscent() - 1;

		int xc = 0;
		int yc = 0;

		switch (align)
		{
			case 1:
				xc -= sLen / 2;
				break;
			case 2:
				xc -= sLen;
				break;
		}

		switch (lineAlign)
		{
			case 0:
				yc += sHe - 1;
				break;
			case 1:
				yc += sHe / 2 - 1;
				break;
		}

		g.setColor(bkColor);
		drawRotate(g, x - 1, y - 1, xc, yc, angolo, testo);
		drawRotate(g, x - 1, y + 1, xc, yc, angolo, testo);
		drawRotate(g, x + 1, y - 1, xc, yc, angolo, testo);
		drawRotate(g, x + 1, y + 1, xc, yc, angolo, testo);
		g.setColor(foColor);
		drawRotate(g, (x), (y), xc, yc, angolo, testo);
	}

	public static void drawRotate(Graphics2D g2d, double x, double y, int xc, int yc, double angle, String testo)
	{
	    g2d.translate((float)x,(float)y);
	    g2d.rotate(Math.toRadians(angle));
	    g2d.drawString(testo,xc, yc);

	    g2d.rotate(-Math.toRadians(angle));
	    g2d.translate(-(float)x,-(float)y);
	}

	public static void drawMazzo(Graphics2D g2d, Rectangle area){
		for (int i = 0; i < 5; i++){
			g2d.setColor(Color.GRAY);
			g2d.fillRoundRect(area.x +5, area.y +5, area.width, area.height, 5, 5);
			g2d.setColor(Color.BLACK);
			g2d.drawRoundRect(area.x +5, area.y +5, area.width, area.height, 5, 5);
		}
	}

	public static void drawCarta(Graphics2D g2d, BufferedImage img, Rectangle area){
		if (img == null) return;
			g2d.drawImage(img, area.x, area.y, area.width, area.height, null);
	}

	public static void evidenziaCarta(Graphics2D g2d, Rectangle area, Color fillColor, int spessore){
		g2d.setColor(fillColor);
		g2d.fillRoundRect(area.x - spessore, area.y - spessore, area.width + 2 * spessore, area.height+ 2 * spessore, spessore / 4, spessore / 4);
	}

	public static void drawCartaGioco(Graphics2D g2d, Carta_Gioco cG, Rectangle area){
		if (cG == null) return ;
		switch (cG.tipo) {
			case RISORSA:
				if (cG.fronte) g2d.drawImage(Variabili.imgCarteRisorse[cG.ID], area.x, area.y, area.width, area.height, null);
				else g2d.drawImage(ottieniImmagineCartaGioco(cG), area.x, area.y, area.width, area.height, null);
				break;
			case ORO:
				if (cG.fronte) g2d.drawImage(Variabili.imgCarteOro[cG.ID], area.x, area.y, area.width, area.height, null);
				else g2d.drawImage(ottieniImmagineCartaGioco(cG), area.x, area.y, area.width, area.height, null);
				break;
			case INIZIALE:
				if (cG.fronte) g2d.drawImage(Variabili.imgCarteIniziali[cG.ID], area.x, area.y, area.width, area.height, null);
				else g2d.drawImage(ottieniImmagineCartaGioco(cG), area.x, area.y, area.width, area.height, null);
				break;
			default:
				break;
		}
	}

	public static void drawCartaGioco(Graphics2D g2d, Carta_Gioco cG, Rectangle area, boolean evidenzia, int spessore){
		if (cG == null) return ;
		if (evidenzia) evidenziaCarta(g2d, area, Color.GREEN, spessore);
		drawCartaGioco(g2d, cG, area);
	}


	public static void evidenziaCarta(Graphics2D g2d, Rectangle area, Color fillColor)
	{
		g2d.setColor(fillColor);
		g2d.fillRoundRect(area.x - 30, area.y - 30, area.width + 60, area.height + 60, 15, 15);
	}
	
	
	public static void evidenziaAngoloCarta(Graphics2D g2d, Rectangle area, Enums.eAngolo angolo, Color colore)
	{
		if (angolo == null) return;
		
		g2d.setColor(colore);
		Rectangle r = new Rectangle(area.x, area.y, area.width, area.height);
		r.width = r.width / 2;
		r.height = r.height / 2;
		switch (angolo)
		{
			case NO:
				break;
			case NE:
				r.x += r.width;
				break;
			case SE:
				r.x += r.width;
				r.y += r.height;
				break;
			case SO:
				r.y += r.height;
				break;
		}
		g2d.fillRect(r.x, r.y, r.width, r.height);
	}

	/**
	 * Restituisce l'immagine della carta gioco RETRO
	 * @param cG
	 * @return
	 */
	private static BufferedImage ottieniImmagineCartaGioco(Carta_Gioco cG)
	{
		
		switch (cG.simboloRetro[0]) {
			case FUNGO: 
				if (cG.tipo == Enums.eTipoCarta.RISORSA) return Variabili.imgCartaRisorsaRossaRetro;
				else return Variabili.imgCartaOroRossaRetro;
			case FOGLIA: 
				if (cG.tipo == Enums.eTipoCarta.RISORSA) return Variabili.imgCartaRisorsaVerdeRetro;
				else return Variabili.imgCartaOroVerdeRetro;
			case LUPO: 
				if (cG.tipo == Enums.eTipoCarta.RISORSA) return Variabili.imgCartaRisorsaBluRetro;
				else return Variabili.imgCartaOroBluRetro;
			case FARFALLA: 
				if (cG.tipo == Enums.eTipoCarta.RISORSA) return Variabili.imgCartaRisorsaViolaRetro;
				else return Variabili.imgCartaOroViolaRetro;
			default : return null;
		}
	}

	public static void drawCartaObiettivo(Graphics2D g2d, Carta_Obiettivo cO, Rectangle area, Color bkColor, Color fillColor){
		if (cO == null) return;

		g2d.setColor(fillColor);
		g2d.fillRect(area.x -8, area.y -8, area.width + 16, area.height +16);

		g2d.setColor(bkColor);
		g2d.fillRect(area.x -6, area.y -6, area.width + 12, area.height +12);

		g2d.drawImage(Variabili.imgCarteObiettivi[cO.ID], area.x, area.y, area.width, area.height, null);

	}


	


}
