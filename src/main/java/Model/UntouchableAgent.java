package Model;

import java.util.ArrayList;

/**
 * Az ágens virológusra gyakorolt hatás idejét tárolja, valmaint kifejti a hatását a virológusra.
 */
public class UntouchableAgent extends Agent {
	private long duration;

	/**
	 * Az érinthetetlenséget előidéző ágens
	 * konstruktora.Superrel beállítja a kapott nevet
	 * @param m a szüksgéges anyagok
	 * @param name a neve
	 */
	public UntouchableAgent(ArrayList<Material> m, String name) {
		super(m, name);
		duration = 1;
	}

	/**
	 * Beállítja a virológusnak a státuszát
	 * @param v A virológus akinek beállítja a státuszát
	 * @param n Az idő mértéke
	 */
	public void setStatus(long n, Virologist v) {
		Untouchable untouchable = new Untouchable();
		untouchable.setVirologist(v);
		untouchable.setTimeEffected(n);
		v.addEffect(untouchable);
	}

	/**
	 * Beállítja, hogy meddig lehet felhasználni
	 * @param duration meddig hatásos
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * Visszaadja, hogy meddig lehet felhasználni
	 * @return a hátralévő idő
	 */
	public long getDuration() {
		return duration;
	}
}
