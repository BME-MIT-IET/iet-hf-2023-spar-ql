package Model;

/**
 * Tárol egy ágenst. Feladata a genetikai kód virológushoz adása és egy ágens létrehozása.
 */
public class GeneticCode {
	protected int id;
	private Agent agent;


	/**
	 * A genetikai kód kontruktora
	 * @param a Az ágens, amit tartalmaz a genetikai kód
	 */
	public GeneticCode(Agent a){
		agent = a;
	}

	/**
	 * Létrehozza az ágenst és visszatér az eredményével,
	 * ami lehet sikeres létrehozatal vagy sikertelen.
	 * @param v A virológus, aki végzi a tevékenységet
	 * @return sikeres vagy sikretelen volt az ágens létrehozása
	 */
	public boolean CreateAgent(Virologist v) {
		if(v.useMaterials(agent.getNeededMaterials())){
			v.addAgent(agent);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Visszaadja azt, hogy melyik ágenst tartalmazza a genetikai kódot
	 * @return az ágens, amit tartalmaz
	 */
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent a) { agent = a;	}

	/**
	 * Visszaadja a az osztály azonosítóját
	 * @return id: azonosító szám
	 */
	public int getId(){
		return id;
	}

	/**
	 * Beállítja, hogy mennyi az id-je
	 * @param id a a kapott id
	 */
	public void setId(int id) {
		this.id = id;
	}
}
