package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Egy mező, amin a virológus védőfelszerelést tud majd szerezni. A feladata az, hogy ha a
 * virológus megkísérli felvenni a védőfelszerelést, akkor megmondja, hogy ott melyik védőfelszerelés
 * található.
 */
public class Shelter extends Tile {
	private ProtectiveGear pG;

	/**
	 * Az óvóhely konstruktora
	 * @param pG a mezőn található védőfelszerelés
	 */
	public Shelter(ProtectiveGear pG, int[] _pointsX, int[] _pointsY, int n){
		super(_pointsX, _pointsY, n);
		this.pG = pG;
		this.color = new Color(249, 251, 250);
	}

	/**
	 * Megmondja, hogy melyik védőfelszerelés található a
	 * @return
	 */
	public ProtectiveGear Collect() {
		return pG;
	}

	/**
	 * Mi a mezőn található gyüjthető tárgy
	 * @return Visszaadja, hogy mit lehet felvenni
	 */
	public ProtectiveGear GetCollectable() {
		return Collect();
	}

	/**
	 * Beállítja azt, hogy milyen védőfelszerelés találhatő a mezőn
	 * @param o a védőfelszerelés
	 */
	public void setCollectable(Object o){
		pG = (ProtectiveGear) o;
	}

	/**
	 * Kiírja az osztály attribútumainak értékeit
	 */
	public void Print(){
		System.out.println("Shelter:");
		System.out.println("\tcapacity: " + capacity);
		System.out.print("\tadjacentTiles: ");
		if(adjacentTiles.size() != 0){
			for(int i = 0; i < adjacentTiles.size(); i++)
				System.out.print((adjacentTiles.get(i).getId() + 1) + ". tile ");
			System.out.println("");
		}
		if(adjacentTiles.size() == 0)
			System.out.println("null");
		System.out.print("\tvirologist: ");
		if(virologists.size() != 0){
			for(int i = 0; i < virologists.size(); i++)
				System.out.print((virologists.get(i).getId() + 1)  + ". virologist ");
			System.out.println("");
		}
		if(virologists.size() == 0)
			System.out.println("null");
		if(pG == null)
			System.out.println("\tp_g: null");
		if(pG != null)
			System.out.println("\tp_g: " + (pG.getId() + 1) + ". protective gear");
		System.out.println("");
	}
}