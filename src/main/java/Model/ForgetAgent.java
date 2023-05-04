package Model;

import java.util.ArrayList;

/**
 * Az ágens virológusra gyakorolt hatás idejét tárolja, valmaint kifejti a hatását a virológusra.
 */
public class ForgetAgent extends Agent {

	/**
	 * A genetikai kódokat elfelejtő ágens konstruktora. Superrel
	 * beállítja a kapott nevet
	 * @param m szükséges anyagok
	 * @param name a neve
	 */
	public ForgetAgent(ArrayList<Material> m, String name) {
		super(m, name);
	}

	/**
	 * Elveszi a virológustól a genetikai kódjait
	 * @param v A virológus akitől elveszi a kódokat
	 */
	public void takeAwayCode(Virologist v) {
		v.getGeneticCodes().clear();
		v.setCodeCount(0);
	}
	/**
	 * Beállítja a virológusnak a státuszát
	 * @param v A virológus akinek beállítja a státuszát
	 * @param n Az időtartam ameddig hat
	 */
	public void setStatus(long n, Virologist v) {
		takeAwayCode(v);
	}
}
