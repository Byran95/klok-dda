package klok;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class AnalogeKlok extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	public static final int KLOKSTRAAL = 200;
    public static final int KLOKDIAMETER = KLOKSTRAAL * 2;
    private final int lengthSecondPointer = (int) (KLOKSTRAAL  * 0.95);
    private final int lengthMinutePointer = (int) (KLOKSTRAAL  * 0.80);
	private final int lengthSmallPointer = (int) (KLOKSTRAAL  * 0.60);
	private final double degreesForMinutesAndSeconds = 30;
	private final double degreesForHours = (degreesForMinutesAndSeconds * 12);
	private int uur;
	private int minuut;
	private int seconde;
    private final int valueHour = 12 * uur + minuut;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawAnalogeKlok(g);
	}

	private void drawAnalogeKlok(Graphics g) {
		drawAnalogeKlokBackground(g);
		drawClockLines(g);
	}

	private void drawAnalogeKlokBackground(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillOval(0, 0, KLOKDIAMETER, KLOKDIAMETER);
		g.setColor(Color.black);
		g.drawOval(0, 0, KLOKDIAMETER, KLOKDIAMETER);
	}

	private void drawClockLines(Graphics g) {
		g.setColor(Color.black);
		drawClockLineSecond(g, seconde);
		g.setColor(Color.BLUE);
		drawClockLineMinute(g, minuut);
		g.setColor(Color.white);
		drawClockLineHour(g, valueHour);
	}

	private void drawClockLineSecond(Graphics g, int value) {
		double mathCall = (value / degreesForMinutesAndSeconds) * Math.PI;
		g.drawLine(KLOKSTRAAL, KLOKSTRAAL,
				(int) (KLOKSTRAAL + lengthSecondPointer * Math.sin(mathCall)),
				(int) (KLOKSTRAAL - lengthSecondPointer * Math.cos(mathCall)));
	}

    private void drawClockLineMinute(Graphics g, int value) {
        double mathCall = (value / degreesForMinutesAndSeconds) * Math.PI;
        g.drawLine(KLOKSTRAAL, KLOKSTRAAL,
                (int) (KLOKSTRAAL + lengthMinutePointer * Math.sin(mathCall)),
                (int) (KLOKSTRAAL - lengthMinutePointer * Math.cos(mathCall)));
    }

	private void drawClockLineHour(Graphics g, int value) {
		double mathCall = value / (degreesForHours) * Math.PI;
		g.drawLine(KLOKSTRAAL, KLOKSTRAAL,
				(int) (KLOKSTRAAL + lengthSmallPointer * Math.sin(mathCall)),
				(int) (KLOKSTRAAL - lengthSmallPointer * Math.cos(mathCall)));
	}

	@Override
	public void update(Observable o, Object arg) {
		uur = ((KlokModel) o).getUur();
		minuut = ((KlokModel) o).getMinuut();
		seconde = ((KlokModel) o).getSeconde();
		repaint();

	}
}