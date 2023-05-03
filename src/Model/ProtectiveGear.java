package Model;

/**
 * A védőfelszerelések ősosztálya, ami azért felel, hogy az óvóhelyen tárolt védőfelszereléseket tudjuk
 * tárolni.
 */
public abstract class ProtectiveGear extends Item {
	protected Shelter shelter;

	/**
	 * Superrel beállítja a kapott nevet
	 * @param name a név
	 */
	public ProtectiveGear(String name) {
		super(name);
	}

	/**
	 * Kiveszi a viselt tárgyak közül a védőfelszerelést, ha benne
	 * van, valamint a virológus táskájából is kiveszi
	 * @param v akitől elveszi
	 */
	public abstract void takeAway(Virologist v);

	/**
	 * Az adott virológusra beállítja a védőfelszerelés hatását
	 * @param v akié a tárgy
	 */
	public abstract void setAttribute(Virologist v);

	/**
	 * A függvény hívása után nem csak a táskában lesz benne a
	 * védőfelszerelés, hanem viselni is fogja a virológus
	 */
	public abstract void Wear();

	/**
	 * Egy védőfelszerelés használatához használható függvény
	 * @param v virológus, akin használják
	 * @param a az ágens amit, a kesztyű esetében hasnál
	 */
	public void Use(Virologist v, Agent a) {}

	/**
	 * Egy védőfelszerelés elpusztításához használható függvény, meghívja a
	 * takeAway-t
	 */
	public abstract void Destroy();

}
