package klok;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class DigitaleKlok extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private int uur;
	private int minuut;
	private int seconde;
	private int blockSide = 20;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawDigitaleKlok(g);
	}

	private void drawDigitaleKlok(Graphics g) {
        int widthBetweenBlock = 25;
        int blockX = 70;
        int blockY = 10;
		g.setColor(Color.black);
		for (int i = 0; i < 3; i++) {
			g.drawRect(blockX + (i * widthBetweenBlock), blockY, blockSide, blockSide);
			if (i == 0)
				g.drawString(checkIntUnderTen(uur) + "" + uur, blockX + (i * widthBetweenBlock) + blockSide /6, widthBetweenBlock);
			else if (i == 1)
				g.drawString(checkIntUnderTen(minuut) + "" + minuut, blockX + (i * widthBetweenBlock) + blockSide /6, widthBetweenBlock);
			else
				g.drawString(checkIntUnderTen(seconde) + "" + seconde, blockX + (i * widthBetweenBlock) + blockSide /6, widthBetweenBlock);
		}
	}
	
	private String checkIntUnderTen(int value) {
		if (value < 10) {
			return "0";
		} else {
			return "";
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		uur = ((KlokModel) o).getUur();
		minuut = ((KlokModel) o).getMinuut();
		seconde = ((KlokModel) o).getSeconde();
		repaint();
	}
}
