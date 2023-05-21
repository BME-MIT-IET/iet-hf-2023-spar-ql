package Model;

/**
 * Az ágens virológusra gyakorolt hatás idejét tárolja, valmaint kifejti a hatását a virológusra.
 */
public class VitusDance extends Effects {
	private long timeDancing;

	/**
	 * A vitustánc konstruktora
	 */
	public VitusDance(){
		timeDancing = 0;
	}

	/**
	 * Lépteti az időt. Addig hívja a függvényt, amíg nem lesz a timeDancing= 0 és
	 * timeDancing-et 1-gyel csökkenti
	 */
	@Override
	public void Step() {
		if(timeDancing > 0) {
			timeDancing--;
			if(timeDancing == 0){
				virologist.removeEffect(this);
			}
		}
	}

	/**
	 * Beállítja a hatásnak az idejét
	 * @param n Az idő mértéke
	 */
	public void setTimeEffected(long n) {
		timeDancing = n;
	}

	/**
	 * Visszaadja, hogy meddig gyakorol hatást a virológusra
	 * @return a hétralévő idő
	 */
	public long getTime() {
		return timeDancing;
	}
}
