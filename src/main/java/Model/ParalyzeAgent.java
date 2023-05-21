package Model;

import java.util.ArrayList;

/**
 * Az ágens virológusra gyakorolt hatás idejét tárolja, valmaint kifejti a hatását a virológusra.
 */
public class ParalyzeAgent extends Agent {
	private long duration;

	/**
	 * A bénító ágens konstruktora. Superrel beállítja a kapott nevet
	 * @param m a szükséges anyagok
	 * @param name a neve
	 */
	public ParalyzeAgent(ArrayList<Material> m, String name) {
		super(m, name);
		duration = 1;
	}

	/**
	 * Beállítja a virológusnak a státuszát
	 * @param v A virológus akinek beállítja a státuszát
	 * @param n Az időtartam ameddig tart
	 */
	public void setStatus(long n, Virologist v) {
		Paralyzed paralyzed = new Paralyzed();
		paralyzed.setTimeEffected(n);
		v.addEffect(paralyzed);
	}

	/**
	 * Visszaadja, hogy meddig lehet felhasználni
	 * @return a hátralévő idő, amig felhasználható
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * Beállítja, hogy meddig lehet felhasználni
	 * @param duration a hatás hossza
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}
}
