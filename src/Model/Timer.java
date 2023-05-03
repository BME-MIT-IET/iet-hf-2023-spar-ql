package Model;

/**
 * Periodikus időzítőt reprezentál a játékban, a léptethető (Steppable) dolgokat lépteti.
 */
public class Timer {
    private Steppable stepable;

    /**
     * Minden léptethető dolog léptetése. Meghívja a Steppable inteface-t megvalósító
     * osztályok Step metódusát
     */
    public void Tick(){
        stepable.Step();
    }
}
