package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Egy mező, amin a virológus genetikai kódot tud majd szerezni. A feladata az, hogy ha a
 * virológus megkísérli letapogatni a falát a laboratóriumnak, akkor megmondja, hogy ott melyik
 * genetikai kód található.
 */
public class Laboratory extends Tile {
	private GeneticCode geneticCode;
	private BearDanceAgent bearDance;
	/**
	 * A laboratórium konstruktora. Beállítja, hogy melyik
	 * genetikai kód található itt, beardance-t nullra állítja
	 * @param code a rajta található kód
	 */
	public Laboratory(GeneticCode code,  int[] _pointsX, int[] _pointsY, int n) {
		super(_pointsX, _pointsY, n);
		geneticCode = code;
		bearDance = null;
		color = new Color(183, 219, 243);
	}

	/**
	 * Beállítja az attributum értékeket
	 * a paraméterben kapott értékekre
	 * @param code a genetikai kód
	 * @param bearDanceAgent a medvetánc ágens
	 */
	public Laboratory(GeneticCode code, BearDanceAgent bearDanceAgent, int[] _pointsX, int[] _pointsY, int n) {
		super(_pointsX, _pointsY, n);
		geneticCode = code;
		bearDance = bearDanceAgent;
		color = new Color(183, 219, 243);
	}

	/**
	 * Beállítja a medvetáncot a mezőre
	 * @param bearDance
	 */
	public void setBearDance(BearDanceAgent bearDance){
		this.bearDance = bearDance;
	}

	/**
	 * FOgadja a virológust és ha van medvetánca, akkor megfertőzi vele (ha tudja)
	 * @param v Az a virológus, aki odalép
	 */
	@Override
	public void Accept(Virologist v) {
		super.Accept(v);
		if(bearDance != null){
			LaboratoryInfect(v);
		}
	}

	public void setCollectable(Object o) { geneticCode = (GeneticCode) o;}

	/**
	 * Megmondja, hogy a mezőn melyik genetikai kód található
	 * @return A mezőn található genetikai kód
	 */
	public GeneticCode Palpate() {
		return geneticCode;
	}

	/**
	 * Mi a mezőn található gyüjthető tárgy
	 * @return Visszaadja, hogy mit lehet felvenni
	 */
	@Override
	public GeneticCode GetCollectable() {
		return this.Palpate();
	}

	/**
	 * A laboratórium használja a beardance-t egy virológuson
	 * @param v a virológus, akin használja
	 */
	public void LaboratoryInfect(Virologist v){
		Random rand = new Random();
		double random = (double)rand.nextInt(1000) / 10.0;
		if(v.getUntouchable() == false && random > v.getAgentResistance()){
			BearDanceAgent bearDance=new BearDanceAgent(null,"bear dance");
			v.HitByAgent(bearDance);
		}
	}

	/**
	 * Megmondja, hogy fertőz-e a mező. Akkor fertőz, ha nem null a
	 * beardance attribútum
	 * @return ferőz-e a mező
	 */
	public boolean isInfects(){
		if(bearDance != null){return true;}
		return false;
	}

	/**
	 * Kiírja az osztály attribútumainak értékeit
	 */
	public void Print(){
		System.out.println("Laboratory:");
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
				System.out.print((virologists.get(i).getId() + 1) + ". virologist ");
			System.out.println("");
		}
		if(virologists.size() == 0)
			System.out.println("null");
		System.out.print("\tgeneticCode: ");
		if(geneticCode == null)
			System.out.println("null");
		if(geneticCode != null) {
			System.out.println((geneticCode.getId() + 1) + ". geneticCode");
			System.out.println("");
		}
		System.out.print("\tbearDance: ");
		if(bearDance == null)
			System.out.println("null");
		if(bearDance != null){
			System.out.println((bearDance.getId() + 1) + ". agent");
			System.out.println("");
		}
		System.out.println("");
	}
}
