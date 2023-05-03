package Model;

/**
 * Az ágens virológusra gyakorolt hatás idejét tárolja, valmaint kifejti a hatását a virológusra.
 */
public class Untouchable extends Effects implements Steppable {
	private long timeUntouchable;

	/**
	 * Az érinthetetlen hatás konstruktora
	 */
	public Untouchable(){
		timeUntouchable = 0;
	}

	/**
	 * Lépteti az időt. Addig hívja a függvényt, amíg nem lesz a timeUntouchable = 0 és
	 * a timeUntouchable-t 1-gyel csökkenti
	 */
	@Override
	public void Step() {
		if(timeUntouchable > 0) {
			timeUntouchable--;
			if(timeUntouchable == 0){
				virologist.setUntouchbale(false);
				virologist.removeEffect(this);
			}
		}
	}

	/**
	 * Beállítja a hatásnak az idejét
	 * @param n Az idő mértéke
	 */
	public void setTimeEffected(long n) {
		timeUntouchable = n;
		virologist.setUntouchbale(true);
	}

	/**
	 * Visszaadja, hogy meddig gyakorol hatást a virológusra
	 * @return a hátralévő idő
	 */
	public long getTime() {
		return timeUntouchable;
	}
}
