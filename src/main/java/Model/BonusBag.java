package Model;

/**
 * Megnöveli a virológus raktárának a méretét +20 hellyel, ha a virológus viseli. Feladata a
 * raktár növelése, illetve csökkentése.
 */
public class BonusBag extends ProtectiveGear {
	private long bonusAttribute;

	/**
	 * A zsák konstruktora. Superrel beállítja a kapott nevet, a
	 * bonusAttribute-ot 20-ra állítja
	 * @param name a neve
	 */
	public BonusBag(String name) {
		super(name);
		bonusAttribute = 20;
	}

	/**
	 * Hozzáadja a BonusBag-et a virológushoz
	 * @param v A virológus akinek hozzáadja a BonusBag-et
	 */
	public void setAttribute(Virologist v) {
		v.getBag().setBonusSize(bonusAttribute);
	}
	/**
	 * Elveszi a BonusBag-et a virológustól
	 * @param v A virológus akitől elveszi a BonusBag-et
	 */
	public void takeAway(Virologist v) {
		v.getBag().setSize(-bonusAttribute);
		v.Unwear(this);
	}

	/**
	 * Visszaadja azt, hogy mennyivel képes növelni a Bag méretét
	 * @return a növelés mértéke
	 */
	public long getBonus(){
		return bonusAttribute;
	}

	/**
	 * A függvény hívása után nem csak a táskában lesz benne a védőfelszerelés,
	 * hanem viselni is fogja a virológus és meghívja a setAttribute-ot
	 */
	public void Wear(){
		if(!virologist.getWear().contains(this))
			setAttribute(virologist);
	}

	/**
	 * Egy védőfelszerelés elpusztításához használható függvény, meghívja a
	 * takeAway-t
	 */
	@Override
	public void Destroy() {
		takeAway(virologist);
	}
}