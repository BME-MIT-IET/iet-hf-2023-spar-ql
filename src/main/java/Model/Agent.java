package Model;

import java.util.ArrayList;

/**
 * A különböző vírusok és vakcinák őse. Tárolja azt az időt amíg fel lehet használni és hogy milyen
 * anyagok kellenek hozzá. Feladata az, hogy a felhasználásából hátralévő idő csökkentése, illetve hatás
 * beállítása.
 */
public abstract class Agent extends Item implements Steppable {
	protected long usable;
	protected ArrayList<Material> materials;
	protected Bag bag;

	/**
	 * Az ágensek konstruktora. Beállítja a
	 * usable-t az alapértelmezett értékre, materials-t a paraméterben kapott értékre és a bag-et null-ra.
	 * Superrel beállítja a kapott nevet,
	 * @param m A szügséges anyagok
	 * @param name az ágens neve
	 */
	public Agent(ArrayList<Material> m, String name){
		super(name);
		materials = m;
		usable = 5;
		bag = null;
	}

	/**
	 * Az ágens virológusra kifejtet hatását állítja be. A
	 * paraméterben kapott értékekre állítja be, ezt az ehhez tartozó osztály SetTimeEffected
	 * metódusával teszi meg
	 * @param n hatás hossza
	 * @param v virológus, akire hat
	 */
	public abstract void setStatus(long n, Virologist v);

	/**
	 * Lépteti az időt amíg még felhasználható a vírus
	 */
	public void Step() {
		if(usable > 0){
			usable--;
			if (usable == 0) {
				bag.getAgents().remove(this);
			}
		}
	}

	/**
	 * Beállítja a táskát, amihez az ágens tartozik
	 * @param bag a táska amit beállítunk
	 */
	public void setBag(Bag bag) {
		this.bag = bag;
	}

	/**
	 * Lekérdezi azt, hogy melyik táskában található az ágens
	 * @return a táska
	 */
	Bag getBag(){
		return bag;
	}

	/**
	 * Lekérdezi azt, hogy meddig használható az ágens
	 * @return ágens használati ideje
	 */
	long getUsable(){
		return usable;
	}

	/**
	 * Beállítja, hogy meddig lehet használni az ágenst
	 * @param n az idő amíg lehet használni
	 */
	void setUsable(long n){
		usable = n;
	}

	/**
	 * Visszaadja az ágenshez szükséges anyagokat
	 * @return szükséges anyagok
	 */
	public ArrayList<Material> getNeededMaterials() {
		return materials;
	}

	/**
	 * Beállítja az anyagokat, akit a létrehozásához szukségesek
	 * @param m
	 */
	public void setMaterial(Material m) {
		if(materials == null){
			materials = new ArrayList<>();
		}
		materials.add(m);
	}
}
