package Model;

/**
 * 82,3%-kal valószínűséggel távol tartja az ágens hatását, amennyiben a virológus viseli.
 * Feladata a virológus ágens ellenállásának beállítása
 */
public class Cape extends ProtectiveGear {
	private double bonusAttribute;

	/**
	 * A köpeny konstruktora. Superrel beállítja a kapott nevet
	 * @param name a neve
	 */
	public Cape(String name) {
		super(name);
		bonusAttribute = 82.3;
	}

	/**
	 * Hozzáadja a Cape-et a virológushoz
	 * @param v A virológus akinek hozzáadja a BonusBag-et
	 */
	public void setAttribute(Virologist v) {
		v.setAgentResistance(bonusAttribute);
	}
	/**
	 * Elveszi a Cape-et a virológustól
	 * @param v A virológus akitől elveszi a BonusBag-et
	 */
	public void takeAway(Virologist v) {
		v.setAgentResistance(-bonusAttribute);
		v.Unwear(this);
	}

	/**
	 * Visszaadja azt, hogy mennyivel képes növelni a virológus ágens
	 * ellenállását
	 * @return az ágens ellehárítási képessége
	 */
	public double getBonus() {
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
