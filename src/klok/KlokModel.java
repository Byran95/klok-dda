package klok;

import java.util.Calendar;
import java.util.Observable;

public class KlokModel extends Observable implements Runnable {

	private final int sleepTime = 1000;
	private Thread thread;
	private boolean draait;
	private int seconde;
	private int minuut;
	private int uur;

	@Override
	public void run() {
		while (draait) {
			try {
				Thread.sleep(sleepTime);
				updateTime();
				notifyObservers();
			} catch (InterruptedException e) {
				
			}
		}
	}

	private void updateTime() {
		Calendar rightNow = Calendar.getInstance();
		seconde = rightNow.get(Calendar.SECOND);
		minuut = rightNow.get(Calendar.MINUTE);
		uur = rightNow.get(Calendar.HOUR_OF_DAY);
		setChanged();
	}

	public void start() {
		thread = new Thread(this);
		thread.setDaemon(true);
		draait = true;
		thread.start();
	}

	public void stop() {
		draait = false;
	}

	public int getUur() {
		return uur;
	}

	public int getMinuut() {
		return minuut;
	}

	public int getSeconde() {
		return seconde;
	}
}
