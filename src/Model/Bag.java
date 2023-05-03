package Model;

import java.util.ArrayList;

public class Bag {
	private int id;
	private long size;
	private Virologist virologist;
	private ArrayList<Agent> agents;
	private ArrayList<ProtectiveGear> protectiveGears;
	private ArrayList<Material> materials;

	/**
	 * A táska konstruktora. Beállítja a size-ot 30-ra, a virológust null-ra, a litákat pedig
	 * példányosítja
	 */
	public Bag(){
		size = 30;
		virologist = null;
		agents = new ArrayList<Agent>();
		protectiveGears = new ArrayList<ProtectiveGear>();
		materials = new ArrayList<Material>();
	}

	/**
	 * A virologist-et a paraméterként kapott értékre állítja
	 * @param v a virológus, amit beállít
	 */
	public void setVirologist(Virologist v){
		virologist = v;
	}

	/**
	 * A táska méretének a setterje
	 * @param n az amennyivel növeli a méretet
	 */
	public void setSize(long n){
		size = n;
	}

	public void setBonusSize(long n) {size += n;}

	/**
	 * Megmondja, hogy mennyi a táska mérete
	 * @return táska mérete
	 */
	public long getSize(){
		return size;
	}

	public long getUsedSize(){
		return agents.size() + protectiveGears.size() + materials.size();
	}

	/**
	 * Megmondja, hogy milyen ágensei vannak
	 * @return az ágensek
	 */
	public ArrayList<Agent> getAgents() {
		return agents;
	}

	/**
	 * Megmondja, hogy milyen anyagai vannak
	 * @return az anyagok
	 */
	public ArrayList<Material> getMaterials() {
		return materials;
	}

	/**
	 * Megmondja, hogy milyen védőfelszerelései vannak
	 * @return az védőfelszerelések
	 */
	public ArrayList<ProtectiveGear> getProtectiveGears() {
		return protectiveGears;
	}

	/**
	 * Kiveszi a listából az ágenst. Ezt nem helyezi sehova, a tárgy megsemmisül.
	 * @param a A kivenni kívánt ágens
	 */
	public void Discard(Agent a) {
		agents.remove(a);
	}
	/**
	 * Kiveszi a listából a védőfelszerelést. Ezt nem helyezi sehova, a tárgy megsemmisül.
	 * @param g A kivenni kívánt ágens
	 */
	public void Discard(ProtectiveGear g) {
		protectiveGears.remove(g);
		if(virologist.getWear().contains(g))
			g.takeAway(virologist);
	}
	/**
	 * Kiveszi a listából az anyagot. Ezt nem helyezi sehova, a tárgy megsemmisül.
	 * @param m A kivenni kívánt ágens
	 */
	public void Discard(Material m) {
		materials.remove(m);
	}

	/**
	 * Új ágenst ad a virológus raktárához.
	 * @param a A hozzáadott ágens
	 */
	public void Add(Agent a) {
		agents.add(a);
		a.setBag(this);
	}

	/**
	 * Új védőfelszerelést ad a virológus raktárához.
	 * @param g A hozzáadott védőfelszerelést
	 */
	public void Add(ProtectiveGear g) {
		protectiveGears.add(g);
	}

	/**
	 * Új anyagot ad a virológus raktárához.
	 * @param m A hozzáadott anyagot
	 */
	public void Add(Material m) {
		materials.add(m);
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

	/**
	 * Kiírja az osztály attribútumainak értékeit
	 */
	public void Print(char c){
		System.out.println("Bag:");
		if(c == 'a') {
			System.out.println("\tsize: " + size);
			System.out.println("\tvirologist: " + (virologist.getId() + 1) + ". virologist");
			System.out.print("\tagents: ");
			if(agents.size() != 0){
				for(int i = 0; i < agents.size(); i++)
					System.out.print((agents.get(i).getId() + 1) + ". Agent ");
				System.out.println("");
			}
			if(agents.size() == 0)
				System.out.println("null");
			System.out.print("\tprotectiveGears: ");
			if(protectiveGears.size() != 0){
				for(int i = 0; i < protectiveGears.size(); i++)
					System.out.print((protectiveGears.get(i).getId() + 1)+ ". protectiveGear ");
				System.out.println("");
			}
			if(protectiveGears.size() == 0)
				System.out.println("null");
			System.out.print("\tmaterials: ");
			if(materials.size() != 0){
				for(int i = 0; i < materials.size(); i++)
					System.out.print((materials.get(i).getId() + 1)+ ". material ");
				System.out.println("");
			}
			if(materials.size() == 0)
				System.out.println("null");
			System.out.println("");
		}
		if(c == 'b'){
			System.out.print("\tmaterials: ");
			if(materials.size() != 0){
				for(int i = 0; i < materials.size(); i++)
					System.out.print((materials.get(i).getId() + 1) + ". material ");
				System.out.println("");
			}
			if(materials.size() == 0)
				System.out.println("null");
			System.out.println("");
		}
		if(c == 'c') {
			System.out.print("\tagents: ");
			if (agents.size() != 0) {
				for (int i = 0; i < agents.size(); i++)
					System.out.print((agents.get(i).getId() + 1) + ". Agent ");
				System.out.println("");
			}
			if (agents.size() == 0)
				System.out.println("null");
		}
	}
}
