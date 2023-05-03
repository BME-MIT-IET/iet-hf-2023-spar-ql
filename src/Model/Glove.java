package Model;

/**
 * Ha a virológus viseli, akkor a feladata az, hogy az ágens, amit a virológusra akarnak kenni azt az
 * ágenst használó virológusra fog hatni
 */
public class Glove extends ProtectiveGear {
	int remainingUses;

	/**
	 * A kesztyű konstruktora. Superrel beállítja a kapott nevet
	 * @param name a neve
	 */
	public Glove(String name) {
		super(name);
		remainingUses = 3;
	}

	/**
	 * Elveszi a Glove-ot a virológustól
	 * @param v A virológus akitől elveszi a Glove-ot
	 */
	public void takeAway(Virologist v) {
		v.setThrowBackAvailable(false);
		v.Unwear(this);
	}
	/**
	 * Visszadobja az ágenst
	 * @param v A virológus akinek visszadobja az ágenst
	 * @param a Az ágens amit visszadob
	 */
	public void throwBack(Virologist v, Agent a) {
		if(v.getUntouchable() == false){
			v.HitByAgent(a);
		}
		remainingUses--;
	}
	/**
	 * Hozzáadja a Glove-ot a virológushoz
	 * @param v A virológus akinek hozzáadja a Glove-ot
	 */
	public void setAttribute(Virologist v) {
		v.setThrowBackAvailable(true);
	}

	/**
	 * A függvény hívása után nem csak a táskában lesz benne a védőfelszerelés,
	 * hanem viselni is fogja a virológus és meghívja a setAttribute-ot
	 */
	@Override
	public void Wear() {
		if(!virologist.getWear().contains(this))
			setAttribute(virologist);
	}

	/**
	 * Visszaadja, hogy mennyi használata maradt a kesztyűnek
	 * @return a még meglévő haszálatok száma
	 */
	public int getRemainingUses() {
		return remainingUses;
	}

	/**
	 * A kesztyű használata, meghivja a throwBack függvényt
	 * @param v virológus, akin használják
	 * @param a az ágens amit, a kesztyű esetében hasnál
	 */
	public void Use(Virologist v, Agent a) {
		throwBack(v, a);
		if(remainingUses == 0){
			virologist.setThrowBackAvailable(false);
			virologist.getWear().remove(this);
			virologist.getBag().Discard(this);
		}
	}

	/**
	 * Elpusztítja a kesztyűt, ami meghívja a takeAway metódust
	 */
	public void Destroy(){
		takeAway(virologist);
	}

	public void Print()
	{
		System.out.println("Glove:");
		System.out.println("\tremainingUses: " + remainingUses);
	}
}
