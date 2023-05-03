package Model;

/**
 * Az ágens virológusra gyakorolt hatás idejét tárolja, valmaint kifejti a hatását a virológusra
 */
public class Paralyzed extends Effects{
	private long timeParalyzed;

	/**
	 * A bénult hatás konstruktora
	 */
	public Paralyzed(){
		timeParalyzed = 0;
	}

	/**
	 * Lépteti az időt. Addig hívja a függvényt, amíg nem lesz a timeParalyzed =0 és a
	 * timeParalyzed-et 1-gyel csökkenti
	 */
	@Override
	public void Step() {
		if(timeParalyzed > 0) {
			timeParalyzed--;
			if(timeParalyzed == 0){
				virologist.removeEffect(this);
			}
		}
	}

	/**
	 * Beállítja a hatásnak az idejét
	 * @param n Az idő mértéke
	 */
	public void setTimeEffected(long n) {
		timeParalyzed = n;
	}

	/**
	 * Visszaadja, hogy meddig gyakorol hatást a virológusra
	 * @return a hátralévő idő
	 */
	@Override
	public long getTime() {
		return timeParalyzed;
	}
}
