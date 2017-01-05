package klok;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class KlokFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private DigitaleKlok digitaleKlok;
	private AnalogeKlok analogeKlok;
	private KlokModel klokModel;
	private JPanel jPanel;

	Toolkit tk = Toolkit.getDefaultToolkit();
	final int inHalf = 2;
	final int xSize = ((int) tk.getScreenSize().getWidth()) / inHalf;
	final int ySize = ((int) tk.getScreenSize().getHeight()) / inHalf;
	final int border = 10;
	final int analogeKlokWidth = AnalogeKlok.KLOKDIAMETER;
	final int analogeKlokHeight = analogeKlokWidth;
	final int digitalKlokWidth = 210;
	final int digitalKlokHeight = 50;
	final int beginScreenX = 0;
	final int beginScreenY = 0;
	final int beginFrame = 50;

	public KlokFrame() {
		super("Klok");
		setBounds(beginScreenX, beginScreenY, xSize, ySize);
		jPanel = new JPanel();
		jPanel.setBorder(new EmptyBorder(border, border, border, border));
		setContentPane(jPanel);
		jPanel.setLayout(null);

		digitaleKlok = new DigitaleKlok();
		digitaleKlok.setBounds(beginFrame, beginFrame + analogeKlokWidth, digitalKlokWidth, digitalKlokHeight);
		jPanel.add(digitaleKlok);

		analogeKlok = new AnalogeKlok();
		analogeKlok.setBounds(beginFrame, beginFrame, analogeKlokWidth, analogeKlokHeight);
		jPanel.add(analogeKlok);

		klokModel = new KlokModel();
		klokModel.addObserver(digitaleKlok);
		klokModel.addObserver(analogeKlok);

		addWindowListener(new FrameAfsluiter());
		klokModel.start();
	}

	private class FrameAfsluiter extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			klokModel.stop();
		}
	}

	public static void main(String[] args) {
		KlokFrame klok = new KlokFrame();
		klok.setVisible(true);
		klok.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
