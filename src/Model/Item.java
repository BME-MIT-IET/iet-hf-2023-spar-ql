package Model;

/**
 * A raktárban tárolandó anyagok és védőfelszerelések ősosztálya. A virológus raktárában
 * szereplő dolgokat ennek a segítségével tároljuk egy listában.
 */
public abstract class Item {
	protected String name;
	protected int id;
	protected Virologist virologist;

	/**
	 * Egy tárgy konstruktora, ami beállítja a nevét
	 * @param name a neve
	 */
	public Item(String name) {
		this.name = name;
		virologist = null;
	}

	/**
	 * Megadja hogy egyenlő e az egyik tárgy a másikkal
	 * @param i A tárgy amit összehasonlítunk
	 */
	public boolean ItemEqual(Item i) {
		if(this.name.equalsIgnoreCase(i.name)){
			return true;
		}
		return false;
	}

	/**
	 * Visszaadja a tárgy nevét
 	 * @return a tárgy neve
	 */
	public String getName() {
		return name;
	}

	/**
	 * Beállítja, hogy melyik virológus birtokában van a tárgy
	 * @param v A virológus, akinél van
	 */
	public void setVirologist(Virologist v) {
		this.virologist = v;
	}

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
