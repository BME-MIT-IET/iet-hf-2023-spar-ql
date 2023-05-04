package Model;

import View.TileView;

import java.awt.*;
import java.util.ArrayList;

/**
 * A mező ősosztály, ami a tárolja, hogy melyik virológusok vannak rajta és hogy mennyi
 * virológus lehet a mezőn. A feladata a szomszédos mezők tárolása és a virológusok mezőn
 * való tartózkodásának kezelése.
 */
public class Tile {
	protected int id;
	protected long capacity;
	protected ArrayList<Tile> adjacentTiles;
	protected ArrayList<Virologist> virologists;
	protected int pointsX[];
	protected int pointsY[];
	protected int n;
	protected Color color;

	protected TileView view;

	/**
	 * Egy mező konstruktora
	 */
	public Tile(int _pointsX[], int _pointsY[], int _n) {
		capacity = 2;
		adjacentTiles = new ArrayList<Tile>();
		virologists = new ArrayList<Virologist>();
		this.pointsX = _pointsX;
		this.pointsY = _pointsY;
		this.n = _n;
		this.color = new Color(181, 238, 205);
	}

	/**Kirajzoláshoz fognak kelleni az alábbi getterek:
	 * Ezzel lekérdezhetők az X koordináták sorban:*/
	public int[] getPointsX() {
		return pointsX;
	}

	/**Ezzel az Y koordináták: 	*/
	public int[] getPointsY() {
		return pointsY;
	}

	/**Ezzel, hogy mennyi csúcsa van a mezőnek összesen:	*/
	public int getN() {return n;}

	/**Ezzel pedig, hogy milyen színe van a mezőnek:	*/
	public Color getColor() {return color;}

	/**
	 * Eltávolítja az adott mezőről a virológust.
	 *
	 * @param v Az a virológus, aki ellép
	 */
	public void Remove(Virologist v) {
		virologists.remove(v);
	}

	/**
	 * Beteszi az adott mezőre a virológust.
	 *
	 * @param v Az a virológus, aki odalép
	 */
	public void Accept(Virologist v) {
		if (virologists.size() < capacity) {
			virologists.add(v);
		}
	}

	/**
	 * Megadja az n-edik szomszédos mezőt.
	 *
	 * @param n a mező száma
	 * @return A szomszédos mező
	 */
	public Tile GetNeighbor(long n) {
		for (int i = 0; i < adjacentTiles.size(); i++) {
			if (adjacentTiles.get(i).getId() == (int) n) {
				return adjacentTiles.get(i);
			}
		}
		return null;
	}

	/**
	 * Beállítja a szomszédos mezőt, a hatékonyság kedvéért oda-vissza hat
	 *
	 * @param tile a szomszédos mező
	 */
	public void setNeighbor(Tile tile) {
		adjacentTiles.add(tile);
		tile.setTheOtherNeighbor(this);
	}

	/**Erre azért van szükség, mert ha csak előbbi létezne,
	 * akkor a végtelenségig adogatnák egymást szomszédnak
	 * Ha meg csak utóbbi létezik, akkor kétszer annyi munka lenne Map-ben*/
	public void setTheOtherNeighbor(Tile tile) {
		adjacentTiles.add(tile);
	}


	/**
	 * Visszaadja a másik virológust a mezőről
	 *
	 * @param v a mi virológusunk
	 * @return a másik virológus
	 */
	public Virologist GetOtherVirologist(Virologist v) {
		if (virologists.size() > 1) {
			for (Virologist virologist : virologists) {
				if (virologist != v) {
					return virologist;
				}
			}
		}
		return null;
	}

	/**
	 * Beállítja a felvehető tárgyat/kódot
	 *
	 * @param o
	 */
	public void setCollectable(Object o) {
	}

	/**
	 * Megmondja, hogy milyen gyűjthető objektum van a mezőn
	 *
	 * @return a felvehető tárgy/kód
	 */
	public Object GetCollectable() {
		return null;
	}

	/**
	 * Visszaadja a az osztály azonosítóját
	 *
	 * @return id: azonosító szám
	 */
	public int getId() {
		return id;
	}

	/**
	 * Visszaadja a szomszédos mezőket
	 *
	 *
	 */

	public ArrayList<Tile> getAdjacentTiles() {
		return this.adjacentTiles;
	}
	/**
	 * Beállítja, hogy mennyi az id-je
	 *
	 * @param id a a kapott id
	 */

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Kiírja az osztály attribútumainak értékeit
	 */
	public void Print(char c) {
		if (c == 'a') {
			System.out.println("Tile:");
			System.out.print("\tadjacentTiles: ");
			if (virologists.size() != 0) {
				for (int i = 0; i < virologists.size(); i++)
					System.out.print(virologists.get(i).getId() + ". virologist ");
				System.out.println("");
			}
			if (virologists.size() == 0)
				System.out.println("null");
			System.out.println("");
		}
		if (c == 't') {
			System.out.println("Tile:");
			System.out.print("\tvirologist: ");
			if (virologists.size() != 0) {
				for (int i = 0; i < virologists.size(); i++)
					System.out.print((virologists.get(i).getId() + 1) + ". virologist ");
				System.out.println("");
			}
		}
	}
}
