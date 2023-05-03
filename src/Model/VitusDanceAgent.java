package Model;

import java.util.ArrayList;

/**
 * Az ágens virológusra gyakorolt hatás idejét tárolja, valmaint kifejti a hatását a virológusra.
 */
public class VitusDanceAgent extends Agent {
	private long duration;

	/**
	 * A vitustáncot előidéző ágens konstruktora. Superrel
	 * beállítja a kapott nevet
	 * @param m a szükséges anyagok
	 * @param name a neve
	 */
	public VitusDanceAgent(ArrayList<Material> m, String name) {
		super(m, name);
		duration = 1;
	}

	/**
	 * Beállítja a virológusnak a státuszát
	 * @param v A virológus akinek beállítja a státuszát
	 * @param n Az idő mértéke
	 */
	public void setStatus(long n, Virologist v) {
		VitusDance vitusDance = new VitusDance();
		vitusDance.setVirologist(v);
		vitusDance.setTimeEffected(n);
		v.addEffect(vitusDance);
	}

	/**
	 * Visszaadja, hogy meddig lehet felhasználni
	 * @return a felhaszálási idő
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * Beállítja, hogy meddig lehet felhasználni
	 * @param duration az új időtartam, amíg fel lehet használni
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}
}
