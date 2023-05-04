package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Egy mező, amin a virológus anyagot tud majd szerezni. A feladata az, hogy ha a virológus
 * megkísérli felvenni az anyagot, akkor megmondja, hogy ott melyik anyag található.
 */
public class Storage extends Tile {
	private ArrayList<Material> materials;

	/**
	 * A raktár konstruktora
	 * @param m a raktárban található anyagok
	 */
	public Storage(ArrayList<Material> m, int[] _pointsX, int[] _pointsY, int n){
		super(_pointsX, _pointsY, n);
		this.materials = m;
		this.color = new Color(210, 216, 216);
	}

	public void setCollectable(Object o){
		if(materials == null){
			materials = new ArrayList<>();
		}
		this.materials.add( (Material) o);
	}

	/**
	 * Megmondja, hogy melyik anyag található a mezőn.
	 * @return az anyag, amit a mrzőn fel lehet venni
	 */
	public Material Collect() {
		if(materials.size() > 0){
			Material material = materials.get(materials.size() - 1);
			materials.remove(material);
			return material;
		}
		return null;
	}

	/**
	 * Mi a mezőn található gyüjthető tárgy
	 * @return Visszaadja, hogy mit lehet felvenni
	 */
	@Override
	public Material GetCollectable() {
		return Collect();
	}

	/**
	 * Elpusztulnak a raktárban található anyagok (kiürül a lista, amibe
	 * tároljuk őket)
	 */
	public void DestroyMaterial(){
		materials.clear();
	}

	/**
	 * Kiírja az osztály attribútumainak értékeit
	 */
	public void Print(char c) {
		if (c == 'a') {
			System.out.println("Storage:");
			System.out.println("\tcapacity: " + capacity);
			System.out.print("\tadjacentTiles: ");
			if (adjacentTiles.size() != 0) {
				for (int i = 0; i < adjacentTiles.size(); i++)
					System.out.print((adjacentTiles.get(i).getId() + 1) + ". tile ");
				System.out.println("");
			}
			if (adjacentTiles.size() == 0)
				System.out.println("null");
			System.out.print("\tvirologist: ");
			if (virologists.size() != 0) {
				for (int i = 0; i < virologists.size(); i++)
					System.out.print((virologists.get(i).getId() + 1) + ". virologist ");
				System.out.println("");
			}
			if (virologists.size() == 0)
				System.out.println("null");
			System.out.print("\tmaterials: ");
			if (materials != null && materials.size() > 0) {
				for (int i = 0; i < materials.size(); i++)
					System.out.print((materials.get(i).getId() + 1) + ". material ");
				System.out.println("");
			} else {
				System.out.println("null");
			}
			System.out.println("");
		}
		if (c == 'm') {
			System.out.println("Storage:");
			System.out.print("\tmaterials: ");
			if (materials != null && materials.size() > 0) {
				for (int i = 0; i < materials.size(); i++)
					System.out.print((materials.get(i).getId() + 1) + ". material ");
				System.out.println("");
			} else {
				System.out.println("materials:" + 0);
			}
		}
	}
}
