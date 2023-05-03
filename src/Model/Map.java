package Model;

import Control.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * A pályán található mezőket tárolja és megépíti a pályát.
 */
public class Map {
	private List<Tile> tiles;
	private List<Virologist> virologists;
	private int mapNumber = 1;
	private int virologistNumber = 4;

	Game game;

	/**
	 * A pálya konstruktora
	 */
	public Map(){
		tiles = new ArrayList<Tile>();
		virologists = new ArrayList<Virologist>();
	}

	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Megépíti a pályát
	 *
	 */
	public void Build() {
		if(tiles.size() != 0){
			tiles.clear();
		}
		if(virologists.size() != 0){
			virologists.clear();
		}
		/**
		 * Map1
		 */
		if(mapNumber == 1){
			createTiles1();
		}
		/**
		 * Map2
		 */
		else {
			createTiles2();
		}
		for(int i = 0; i < virologistNumber; i++){
			Bag bag = new Bag();
			Virologist virologist = new Virologist(null, bag);

			virologist.setMap(this);
			int rannum = (int) ((Math.random() * (tiles.size() - 0)) + 0);
			while (tiles.get(rannum).virologists.size() > 0 || rannum == 27){
				rannum = (int) ((Math.random() * (tiles.size() - 0)) + 0);
			}
			Tile ranTile = tiles.get(rannum);
			virologist.setTile(ranTile);
			virologist.setId(virologists.size());
			virologists.add(virologist);
		}
	}

	/**
	 * Ez a függvény a Tile létrehozásáért felel:
	 *  - a kapott type alapján eldönti, hogy pontosan milyen mezőt kell létrehoznia
	 *  - átadja az x-ek és y-ok tömbjét, azért ilyen formában, mert a kirajzolás is így fog menni
	 *  - átadja, hogy mennyi ponttal rendelkezik összesen
	 * */
	public void addPolygon(int type, int[] pointsX, int[] pointsY, int n) {
		switch (type) {
			case 1:
				Tile tmp = new Tile(pointsX, pointsY, n);
				tmp.setId(tiles.size());
				tiles.add(tmp);
				break;
			case 2:
				Laboratory tmp_L = new Laboratory(null, pointsX, pointsY, n);
				tmp_L.setId(tiles.size());
				tiles.add(tmp_L);
				break;
			case 3:
				Shelter tmp_Sh = new Shelter(null, pointsX, pointsY, n);
				tmp_Sh.setId(tiles.size());
				tiles.add(tmp_Sh);
				break;
			case 4:
				Storage tmp_St = new Storage(null, pointsX, pointsY, n);
				tmp_St.setId(tiles.size());
				tiles.add(tmp_St);
				break;
		}
	}

	/**
	 * Az alábbi függvények mind 1-1 polygon létrehozásáért felelnek:
	 *  - sorban kapnak pontokat, x és a hozzátartozó y koordinátákat
	 *  - ezeket ketté választja két tömbbe
	 *  - a két tömbböt tovább adja az addPolygon-nak, a kapott type-pal, illetve a pontok számával együtt
	 * */
	public void createPolygon(int type, int x1, int y1, int x2, int y2, int x3, int y3){
		int[] pointsX = {x1, x2, x3};
		int[] pointsY = {y1, y2, y3};
		addPolygon(type, pointsX, pointsY, 3);
	}
	public void createPolygon(int type, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
		int[] pointsX = {x1, x2, x3, x4};
		int[] pointsY = {y1, y2, y3, y4};
		addPolygon(type, pointsX, pointsY, 4);
	}
	public void createPolygon(int type, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5){
		int[] pointsX = {x1, x2, x3, x4, x5};
		int[] pointsY = {y1, y2, y3, y4, y5};
		addPolygon(type, pointsX, pointsY, 5);
	}
	public void createPolygon(int type, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5, int x6, int y6){
		int[] pointsX = {x1, x2, x3, x4, x5, x6};
		int[] pointsY = {y1, y2, y3, y4, y5, y6};
		addPolygon(type, pointsX, pointsY, 6);
	}
	public void createPolygon(int type, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5, int x6, int y6, int x7, int y7){
		int[] pointsX = {x1, x2, x3, x4, x5, x6, x7};
		int[] pointsY = {y1, y2, y3, y4, y5, y6, y7};
		addPolygon(type, pointsX, pointsY, 7);
	}

	/**
	 * Az alábbi függvény az egyes mezők létrehozásáért felel sorban
	 * Meghívja a megfelelő createPolygon függvényt, és átadja neki sorban a szükséges paramétereket
	 * A legvégén meghívja még a setNeighbors-t, hogy a mezők létrejötte után a szomszédok ismerjék is egymást
	 * */
	public void createTiles1() {
		//Sima mezők létrehozása, típus azonosítójuk: 1
		createPolygon(1, 0, 0, 134, 0, 230, 154, 138, 233, 0, 250);							//	A
		createPolygon(1, 370, 0, 508, 0, 585, 180, 367, 277, 230, 154, 387, 40);		//	B
		createPolygon(1, 508, 0, 673, 0, 814, 163, 585, 180);										//	C
		createPolygon(1, 138, 233, 230, 154, 367, 277);													//	D
		createPolygon(1, 0, 250, 138, 233, 276, 446, 238, 520, 0, 393);						//	E
		createPolygon(1, 138, 233, 367, 277, 276, 446);													//	F
		createPolygon(1, 585, 180, 814, 163, 649, 383);													//	G
		createPolygon(1, 814, 163, 1000, 353, 886, 363, 649, 383);									//	H
		createPolygon(1, 814, 163, 1000, 0, 1000, 353);													//	I
		createPolygon(1, 276, 446, 384, 485, 464, 700, 145, 700, 238, 520);					//	J
		createPolygon(1, 384, 485, 556, 548, 559, 700, 464, 700);									//	K
		createPolygon(1, 488, 420, 649, 383, 831, 530, 556, 548);									//	L
		createPolygon(1, 556, 548, 831, 530, 771, 700, 559, 700);									//	M
		createPolygon(1, 886, 363, 1000, 353, 1000, 590, 831, 530);								//	N

		//Laborok, típus azonosító: 2
		createPolygon(2, 134, 0, 370, 0, 387, 40, 230, 154);
		createPolygon(2, 831, 530, 1000, 590, 1000, 700, 771, 700);

		//Óvóhelyek, típus azonosító: 3
		createPolygon(3, 0, 393, 238, 520, 145, 700, 0, 700);
		createPolygon(3, 367, 277, 585, 180, 649, 383, 488, 420);

		//Raktárak, típus azonosító: 4
		createPolygon(4, 367, 277, 498, 323, 488, 420, 556, 548, 384, 485, 276, 446);
		createPolygon(4, 673, 0, 1000, 0, 814, 163);
		createPolygon(4, 649, 383, 886, 363, 831, 530);

		setNeighbors1();
		setCollectables1();
	}

	/**
	 * Sorban végigmegy minden mezőn és beállítja a megfelelő szomszédokat egymásnak
	 * Mivel ez oda-vissza hat a két mezőre, így ezt elég csak az egyik irányba elvégezni
	 * A függvény végére szép lassan elfogynak a beállítandó szomszédok
	 * */
	public void setNeighbors1() {
		//A
		tiles.get(0).setNeighbor(tiles.get(14));
		tiles.get(0).setNeighbor(tiles.get(3));
		tiles.get(0).setNeighbor(tiles.get(4));
		//B
		tiles.get(1).setNeighbor(tiles.get(2));
		tiles.get(1).setNeighbor(tiles.get(3));
		tiles.get(1).setNeighbor(tiles.get(14));
		tiles.get(1).setNeighbor(tiles.get(17));
		//C
		tiles.get(2).setNeighbor(tiles.get(6));
		tiles.get(2).setNeighbor(tiles.get(19));
		//D
		tiles.get(3).setNeighbor(tiles.get(5));
		//E
		tiles.get(4).setNeighbor(tiles.get(5));
		tiles.get(4).setNeighbor(tiles.get(8));
		tiles.get(4).setNeighbor(tiles.get(9));
		tiles.get(4).setNeighbor(tiles.get(16));
		//F
		tiles.get(5).setNeighbor(tiles.get(18));
		//G
		tiles.get(6).setNeighbor(tiles.get(7));
		tiles.get(6).setNeighbor(tiles.get(17));
		//H
		tiles.get(7).setNeighbor(tiles.get(8));
		tiles.get(7).setNeighbor(tiles.get(13));
		tiles.get(7).setNeighbor(tiles.get(20));
		//I
		tiles.get(8).setNeighbor(tiles.get(19));
		//J
		tiles.get(9).setNeighbor(tiles.get(10));
		tiles.get(9).setNeighbor(tiles.get(16));
		tiles.get(9).setNeighbor(tiles.get(18));
		//K
		tiles.get(10).setNeighbor(tiles.get(12));
		tiles.get(10).setNeighbor(tiles.get(18));
		//L
		tiles.get(11).setNeighbor(tiles.get(12));
		tiles.get(11).setNeighbor(tiles.get(17));
		tiles.get(11).setNeighbor(tiles.get(18));
		tiles.get(11).setNeighbor(tiles.get(20));
		//M
		tiles.get(12).setNeighbor(tiles.get(15));
		//N
		tiles.get(13).setNeighbor(tiles.get(15));
		tiles.get(13).setNeighbor(tiles.get(20));
		//L1 - már minden szomszédja megvan
		//L2 - neki is
		//Ó1 - neki is
		//Ó2
		tiles.get(17).setNeighbor(tiles.get(18));
		//R1 - ezen a ponton már ennek is megvan minden szomszédja
		//R2 - ennek is
		//R3 - és ennek is
	}

	/**
	 * Beállítja a gyűjthető dolgokat a mezőkre
	 * Raktárba: TDP, Lizin és CDP
	 * Laborba: Paralyzed agent és Forgat agent-et tartalmazó genetikai kódok
	 * Óvóhelyre: Kesztyű és köpeny
	 */
	public void setCollectables1(){
		Material tdp= new Material("TDP");
		tiles.get(18).setCollectable(tdp);
		for(int i = 0; i < 20; i++){
			tdp = new Material("TDP");
			tiles.get(18).setCollectable(tdp);
		}
		Material lizin = new Material("Lizin");
		tiles.get(19).setCollectable(lizin);
		for(int i = 0; i < 20; i++){
			lizin = new Material("Lizin");
			tiles.get(19).setCollectable(lizin);
		}
		Material cdp = new Material("CDP");
		tiles.get(20).setCollectable(cdp);
		for(int i = 0; i < 20; i++){
			cdp = new Material("CDP");
			tiles.get(20).setCollectable(cdp);
		}
		ArrayList<Material> agent1materials = new ArrayList<>();
		agent1materials.add(new Material("Lizin"));
		agent1materials.add(new Material("Lizin"));
		agent1materials.add(new Material("Lizin"));
		agent1materials.add(new Material("Lizin"));
		agent1materials.add(new Material("TDP"));
		VitusDanceAgent vitusDanceAgent = new VitusDanceAgent(agent1materials, "Vitusdance agent");
		GeneticCode geneticCode1 = new GeneticCode(vitusDanceAgent);
		tiles.get(14).setCollectable(geneticCode1);

		ArrayList<Material> agent2materials = new ArrayList<>();
		agent2materials.add(new Material("TDP"));
		agent2materials.add(new Material("TDP"));
		agent2materials.add(new Material("TDP"));
		agent2materials.add(new Material("Lizin"));
		agent2materials.add(new Material("Lizin"));
		agent2materials.add(new Material("CDP"));
		ParalyzeAgent paralyzeAgent = new ParalyzeAgent(agent2materials, "Paralyzed agent");
		GeneticCode geneticCode2 = new GeneticCode(paralyzeAgent);
		tiles.get(15).setCollectable(geneticCode2);

		Cape cape = new Cape("Cape");
		Glove glove = new Glove("Glove");
		tiles.get(16).setCollectable(cape);
		tiles.get(17).setCollectable(glove);
	}

	/**
	 * Ez a függvény ugyanazt végzi el, mint az 1-es változat, csak a 2-es térképpel
	 * */
	public void createTiles2() {
		//Sima mezők létrehozása, típus azonosítójuk: 1
		//A
		createPolygon(1,145, 0, 313, 0, 269, 56, 172, 63);
		//B, C, D, E, F
		createPolygon(1, 269, 56, 313, 0, 444, 0, 444, 106, 343, 133);
		createPolygon(1, 572, 0, 771, 0, 771, 123, 653, 54);
		createPolygon(1, 0, 110, 121, 140, 141, 290, 0, 230);
		createPolygon(1, 121, 140, 182, 207, 263, 210, 141, 290);
		createPolygon(1, 263, 210, 343, 133, 401, 240, 306, 360);
		//G, H, I, J, K
		createPolygon(1, 343, 133, 444, 106, 488, 190, 401, 240);
		createPolygon(1, 444, 106, 620, 103, 590, 200, 488, 190);
		createPolygon(1, 653, 54, 771, 123, 714, 170, 707, 253, 590, 200, 620, 103);
		createPolygon(1, 0, 230, 141, 290, 74, 423, 0, 377);
		createPolygon(1, 74, 423, 141, 290, 202, 397, 152, 493);
		//L, M, N, O, P
		createPolygon(1, 401, 240, 488, 190, 502, 336, 306, 360);
		createPolygon(1, 590, 200, 707, 253, 674, 317, 606, 300);
		createPolygon(1, 707, 253, 828, 287, 825, 357, 721, 330, 674, 317);
		createPolygon(1, 872, 167, 1000, 230, 1000, 397, 926, 401, 828, 287);
		createPolygon(1, 0, 377, 74, 423, 152, 493, 84, 553, 0, 627);
		//Q, R, S, T, U
		createPolygon(1, 152, 493, 202, 397, 306, 360, 296, 403, 269, 600);
		createPolygon(1, 404, 553, 424, 467, 529, 440, 579, 487, 539, 537, 535, 600, 461, 633);
		createPolygon(1, 502, 336, 606, 300, 623, 427, 579, 487, 529, 440);
		createPolygon(1, 606, 300, 674, 317, 721, 330, 680, 493, 623, 427);
		createPolygon(1, 539, 537, 579, 487, 623, 427, 680, 493, 650, 563, 535, 600);
		//V, W, X, Y, Z, AA
		createPolygon(1, 828, 287, 926, 401, 825, 440, 825, 357);
		createPolygon(1, 680, 493, 825, 440, 926, 401, 862, 483, 896, 547, 815, 647, 791, 567);
		createPolygon(1, 862, 483, 926, 401, 1000, 397, 1000, 563, 896, 547);
		createPolygon(1, 84, 553, 152, 493, 269, 600, 152, 640);
		createPolygon(1, 152, 640, 269, 600, 273, 700, 185, 700);
		createPolygon(1, 461, 633, 535, 600, 650, 563, 586, 700, 512, 700);

		//Laborok létrehozása, típus azonosítójuk: 1
		createPolygon(2, 0, 0, 145, 0, 172, 63, 121, 140, 0, 110);
		createPolygon(2, 771, 0, 1000, 0, 1000, 230, 872, 167, 771, 123);
		createPolygon(2, 0, 627, 84, 553, 152, 640, 185, 700, 0, 700);
		createPolygon(2, 896, 547, 1000, 563, 1000, 700, 789, 700, 815, 647);

		//Óvóhelyek, azonosító: 3
		createPolygon(3, 172, 63, 269, 56, 343, 133, 263, 210, 182, 207, 121, 140);
		createPolygon(3, 714, 170, 771, 123, 872, 167, 828, 287, 707, 253);
		createPolygon(3, 296, 403, 424, 467, 404, 553, 269, 600);
		createPolygon(3, 680, 493, 791, 567, 815, 647, 789, 700, 586, 700, 650, 563);

		//Végül a raktárak, azonosító: 4
		createPolygon(4, 141, 290, 263, 210, 306, 360, 202, 397);
		createPolygon(4, 444, 0, 572, 0, 653, 54, 620, 103, 444, 106);
		createPolygon(4, 488, 190, 590, 200, 606, 300, 502, 336);
		createPolygon(4, 306, 360, 502, 336, 529, 440, 424, 467, 296, 403);
		createPolygon(4, 721, 330, 825, 357, 825, 440, 680, 493);
		createPolygon(4, 269, 600, 404, 553, 461, 633, 512, 700, 273, 700);

		setNeighbors2();
		setCollectables2();
	}

	/**
	 * Akárcsak a fentebbi függvény, ez is a második térképhez tartozik,
	 * ezt leszámítva ugyanazt csinálja, mint a setNeighbors1
	 * */
	public void setNeighbors2() {
		//A
		tiles.get(0).setNeighbor(tiles.get(1));
		tiles.get(0).setNeighbor(tiles.get(27));
		tiles.get(0).setNeighbor(tiles.get(31));
		//B
		tiles.get(1).setNeighbor(tiles.get(6));
		tiles.get(1).setNeighbor(tiles.get(31));
		tiles.get(1).setNeighbor(tiles.get(36));
		//C
		tiles.get(2).setNeighbor(tiles.get(8));
		tiles.get(2).setNeighbor(tiles.get(28));
		tiles.get(2).setNeighbor(tiles.get(36));
		//D
		tiles.get(3).setNeighbor(tiles.get(4));
		tiles.get(3).setNeighbor(tiles.get(9));
		tiles.get(3).setNeighbor(tiles.get(27));
		//E
		tiles.get(4).setNeighbor(tiles.get(31));
		tiles.get(4).setNeighbor(tiles.get(35));
		//F
		tiles.get(5).setNeighbor(tiles.get(6));
		tiles.get(5).setNeighbor(tiles.get(11));
		tiles.get(5).setNeighbor(tiles.get(31));
		tiles.get(5).setNeighbor(tiles.get(35));
		//G
		tiles.get(6).setNeighbor(tiles.get(7));
		tiles.get(6).setNeighbor(tiles.get(11));
		//H
		tiles.get(7).setNeighbor(tiles.get(8));
		tiles.get(7).setNeighbor(tiles.get(36));
		tiles.get(7).setNeighbor(tiles.get(37));
		//I
		tiles.get(8).setNeighbor(tiles.get(12));
		tiles.get(8).setNeighbor(tiles.get(32));
		tiles.get(8).setNeighbor(tiles.get(36));
		//J
		tiles.get(9).setNeighbor(tiles.get(10));
		tiles.get(9).setNeighbor(tiles.get(15));
		//K
		tiles.get(10).setNeighbor(tiles.get(15));
		tiles.get(10).setNeighbor(tiles.get(16));
		tiles.get(10).setNeighbor(tiles.get(35));
		//L
		tiles.get(11).setNeighbor(tiles.get(37));
		tiles.get(11).setNeighbor(tiles.get(38));
		//M
		tiles.get(12).setNeighbor(tiles.get(13));
		tiles.get(12).setNeighbor(tiles.get(19));
		tiles.get(12).setNeighbor(tiles.get(37));
		//N
		tiles.get(13).setNeighbor(tiles.get(19));
		tiles.get(13).setNeighbor(tiles.get(21));
		tiles.get(13).setNeighbor(tiles.get(32));
		tiles.get(13).setNeighbor(tiles.get(39));
		//O
		tiles.get(14).setNeighbor(tiles.get(21));
		tiles.get(14).setNeighbor(tiles.get(23));
		tiles.get(14).setNeighbor(tiles.get(32));
		//P
		tiles.get(15).setNeighbor(tiles.get(24));
		tiles.get(15).setNeighbor(tiles.get(29));
		//Q
		tiles.get(16).setNeighbor(tiles.get(24));
		tiles.get(16).setNeighbor(tiles.get(33));
		tiles.get(16).setNeighbor(tiles.get(35));
		tiles.get(16).setNeighbor(tiles.get(38));
		//R
		tiles.get(17).setNeighbor(tiles.get(18));
		tiles.get(17).setNeighbor(tiles.get(20));
		tiles.get(17).setNeighbor(tiles.get(26));
		tiles.get(17).setNeighbor(tiles.get(33));
		tiles.get(17).setNeighbor(tiles.get(38));
		tiles.get(17).setNeighbor(tiles.get(40));
		//S
		tiles.get(18).setNeighbor(tiles.get(19));
		tiles.get(18).setNeighbor(tiles.get(20));
		tiles.get(18).setNeighbor(tiles.get(37));
		tiles.get(18).setNeighbor(tiles.get(38));
		//T
		tiles.get(19).setNeighbor(tiles.get(20));
		tiles.get(19).setNeighbor(tiles.get(37));
		tiles.get(19).setNeighbor(tiles.get(39));
		//U
		tiles.get(20).setNeighbor(tiles.get(26));
		tiles.get(20).setNeighbor(tiles.get(34));
		//V
		tiles.get(21).setNeighbor(tiles.get(22));
		tiles.get(21).setNeighbor(tiles.get(39));
		//W
		tiles.get(22).setNeighbor(tiles.get(23));
		tiles.get(22).setNeighbor(tiles.get(30));
		tiles.get(22).setNeighbor(tiles.get(34));
		tiles.get(22).setNeighbor(tiles.get(39));
		//X
		tiles.get(23).setNeighbor(tiles.get(30));
		//Y
		tiles.get(24).setNeighbor(tiles.get(25));
		tiles.get(24).setNeighbor(tiles.get(29));
		//Z
		tiles.get(25).setNeighbor(tiles.get(29));
		tiles.get(25).setNeighbor(tiles.get(40));
		//AA
		tiles.get(26).setNeighbor(tiles.get(34));
		tiles.get(26).setNeighbor(tiles.get(40));
		//L1
		tiles.get(27).setNeighbor(tiles.get(31));
		//L2
		tiles.get(28).setNeighbor(tiles.get(32));
		tiles.get(28).setNeighbor(tiles.get(14));
		//L3
		//ezen a ponton már minden szomszédja megvan
		//L4
		tiles.get(30).setNeighbor(tiles.get(34));
		//Ó1 - Ó2
		//nekik is
		//Ó3
		tiles.get(33).setNeighbor(tiles.get(38));
		tiles.get(33).setNeighbor(tiles.get(40));
		//Ó4
		//R1 - R6
		//és nekik is mind
	}

	/**
	 * Beállítja a gyűjthető dolgokat a mezőkre
	 * Raktárba: az összes lehetséges anyag
	 * Laborba: minden mezőn található egy ágenshez tartozó genetikai kód és az egyik mező fertőz beardance-szel
	 * Óvóhelyre: minden használható tárgy
	 */
	public void setCollectables2(){
		/**
		 * Raktárak
		 */
		Material valin= new Material("Valin");
		tiles.get(40).setCollectable(valin);
		for(int i = 0; i < 50; i++){
			valin = new Material("Valin");
			tiles.get(40).setCollectable(valin);
		}
		Material lizin= new Material("Lizin");
		tiles.get(39).setCollectable(lizin);
		for(int i = 0; i < 50; i++){
			lizin = new Material("Lizin");
			tiles.get(39).setCollectable(lizin);
		}
		Material szerin= new Material("Szerin");
		tiles.get(38).setCollectable(szerin);
		for(int i = 0; i < 50; i++){
			szerin = new Material("Szerin");
			tiles.get(38).setCollectable(szerin);
		}
		Material tdp= new Material("TDP");
		tiles.get(37).setCollectable(tdp);
		for(int i = 0; i < 50; i++){
			tdp = new Material("TDP");
			tiles.get(37).setCollectable(tdp);
		}
		Material cdp= new Material("CDP");
		tiles.get(36).setCollectable(cdp);
		for(int i = 0; i < 50; i++){
			cdp = new Material("CDP");
			tiles.get(36).setCollectable(cdp);
		}
		Material dutp= new Material("dUTP");
		tiles.get(35).setCollectable(dutp);
		for(int i = 0; i < 50; i++){
			dutp = new Material("dUTP");
			tiles.get(35).setCollectable(dutp);
		}

		/**
		 * Óvóhelyek
		 */
		Cape cape = new Cape("Cape");
		tiles.get(34).setCollectable(cape);
		Glove glove = new Glove("Glove");
		tiles.get(33).setCollectable(glove);
		Axe axe = new Axe("Axe");
		tiles.get(32).setCollectable(axe);
		BonusBag bonusBag = new BonusBag("Bag");
		tiles.get(31).setCollectable(bonusBag);

		/**
		 * Laboratóriumok
		 */
		ArrayList<Material> agent1materials = new ArrayList<>();
		agent1materials.add(new Material("Valin"));
		agent1materials.add(new Material("Valin"));
		agent1materials.add(new Material("CDP"));
		agent1materials.add(new Material("CDP"));
		agent1materials.add(new Material("CDP"));
		ForgetAgent forgetAgent = new ForgetAgent(agent1materials, "Forget agent");
		GeneticCode geneticCode1 = new GeneticCode(forgetAgent);
		tiles.get(30).setCollectable(geneticCode1);

		ArrayList<Material> agent2materials = new ArrayList<>();
		agent2materials.add(new Material("dUTP"));
		agent2materials.add(new Material("dUTP"));
		agent2materials.add(new Material("CDP"));
		agent2materials.add(new Material("CDP"));
		agent2materials.add(new Material("Szerin"));
		agent2materials.add(new Material("Szerin"));
		agent2materials.add(new Material("Szerin"));
		UntouchableAgent untouchableAgent = new UntouchableAgent(agent2materials, "Untouchable agent");
		GeneticCode geneticCode2 = new GeneticCode(untouchableAgent);
		tiles.get(29).setCollectable(geneticCode2);

		ArrayList<Material> agent3materials = new ArrayList<>();
		agent3materials.add(new Material("TDP"));
		agent3materials.add(new Material("Lizin"));
		agent3materials.add(new Material("Lizin"));
		agent3materials.add(new Material("Lizin"));
		agent3materials.add(new Material("Lizin"));
		VitusDanceAgent vitusDanceAgent = new VitusDanceAgent(agent3materials, "Vitusdance agent");
		GeneticCode geneticCode3 = new GeneticCode(vitusDanceAgent);
		tiles.get(28).setCollectable(geneticCode3);

		ArrayList<Material> agent4materials = new ArrayList<>();
		agent4materials.add(new Material("TDP"));
		agent4materials.add(new Material("TDP"));
		agent4materials.add(new Material("TDP"));
		agent4materials.add(new Material("Lizin"));
		agent4materials.add(new Material("Lizin"));
		agent4materials.add(new Material("CDP"));
		ParalyzeAgent paralyzeAgent = new ParalyzeAgent(agent4materials, "Paralyze agent");
		GeneticCode geneticCode4 = new GeneticCode(paralyzeAgent);
		tiles.get(27).setCollectable(geneticCode4);

		ArrayList<Material> agent5materials = new ArrayList<>();
		BearDanceAgent bearDanceAgent = new BearDanceAgent(agent5materials, "Beardance agent");
		Laboratory laboratory = (Laboratory) (tiles.get(27));
		laboratory.setBearDance(bearDanceAgent);
	}

	/**Ezzel a függvénnyel lekérdezhető egy adott indexű mező:	*/
	public Tile getTile(int i) {return tiles.get(i);}

	/**Ezzel pedig az összes mező:	*/
	public List<Tile> getTiles() {
		return tiles;
	}

	/**
	 * Beállítja, hogy melyik mapen játszunk
	 * @param mapNumber
	 */
	public void setMapNumber(int mapNumber) {
		this.mapNumber = mapNumber;
	}

	/**
	 * visszaadja az aktuális térkép számát
	 * @return mapNumber, a térkép száma
	 */
	public int getMapNumber() { return mapNumber; }

	/**
	 * Beállítja, hogy mennyi virológus játszik
	 * @param virologistNumber
	 */
	public void setVirologistNumber(int virologistNumber) {
		this.virologistNumber = virologistNumber;
	}

	/**
	 * Visszaadja a virológusokat, akik életben vannak
	 * @return
	 */
	public List<Virologist> getVirologists() {
		return virologists;
	}

	/**
	 * A virológus halálánál kiveszi a játékban lévők közül
	 * @param v
	 */
	public void virologistDie(Virologist v){
		int i = 0;
		for(Virologist virologist : virologists){
			if(v == virologist)
				break;
			i++;
		}
		if(i < game.getActive()){
			game.setActiveDie();
		}
		virologists.remove(v);
		virologistNumber--;
	}
}
