package Control;

import Control.GamePanel;
import Model.Map;

/**
 * A játék indítása és befejezése a feladata.
 */
public class Game {
	private GamePanel panel;

	private Map map;
	private boolean gamerunning;
	private int active = 0;
	private boolean hasMoved = false;

	/**
	 * A játékot irányító osztály konstruktora
	 */
	public Game(){
		map = new Map();
		map.setGame(this);
	}

	/**
	 * Elindítja a játékot. Meghívja a Map Build metódusát
	 */
	public void startGame() {
		map.Build();
		gamerunning = true;
	}

	/**
	 * Beállítja az aktív játékost
	 */
	public void setActive() {
		if(active != map.getVirologists().size() - 1)
			active++;
		else
			active = 0;
	}

	/**
	 * Beállítja, hogy lépet-e
	 * @param move
	 */
	public void setHasMoved(Boolean move) {
		this.hasMoved = move;
	}

	/**
	 * Megmondja, hogy a virológus lépe-e már az adott kőrben
	 * @return
	 */
	public Boolean getHasMoved() {
		return this.hasMoved;
	}

	public void setActiveDie() {
		this.active--;
	}

	/**
	 * Leállítja a játékot
	 */
	public void endGame() {  gamerunning = false; }

	/**
	 * visszaadja az aktív játékos sorszámát
	 * @return active - a sorszám
	 */
	public int getActive(){
		return active;
	}

	/**
	 * Visszaadja a térképet, amin a játék zajlik
	 * @return
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Visszaadja, hogy fut e a játék
	 * @return gameRunning
	 */
	public boolean isGamerunning() {return gamerunning; }
}
