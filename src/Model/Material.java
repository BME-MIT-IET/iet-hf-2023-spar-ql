package Model;

import java.util.ArrayList;

/**
 * Az ágens elkészítéséhez szükséges anyagok ősosztálya
 */
public class Material extends Item {
	/**
	 * Az anyagok konstruktora. Superrel beállítja a kapott nevet
	 * @param name a neve
	 */
	public Material(String name) {
		super(name);
	}

	/**
	 * Csökkenti az anyag mennyiségét
	 * @param amount Az összeg amennyivel csökkenti
	 */
	public void lessMaterial(long amount) {
		ArrayList<Material> materials = virologist.getBag().getMaterials();
		for(Material material : materials){
			if(this.ItemEqual(material) && amount > 0){
				virologist.getBag().Discard(material);
				amount--;
			}
		}
	}
	/**
	 * Növeli az anyag mennyiségét
	 * @param amount Az összeg amennyivel növeli
	 */
	public void addMaterial(long amount) throws CloneNotSupportedException {
		for(int i = 0; i < amount; i++){
			Material mater = (Material) this.clone();
			virologist.getBag().Add(mater);
		}
	}
}
