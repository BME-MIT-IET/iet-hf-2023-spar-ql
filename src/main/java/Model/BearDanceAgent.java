package Model;

import java.util.ArrayList;

/**
 * Az ágens virológusra gyakorolt hatás idejét tárolja, valmaint kifejti a hatását a virológusra
 */
public class BearDanceAgent extends Agent{

    private long duration;

    /**
     * Superrel beállítja a kapott nevet és a szükséges anyagokat
     * @param m szükséges anyagok
     * @param name név
     */
    public BearDanceAgent(ArrayList<Material> m, String name){
        super(m,name);
        duration = Long.MAX_VALUE;
    }

    /**
     * Az ágens virológusra kifejtet hatását állítja be. A
     * paraméterben kapott értékekre állítja be, ezt az ehhez tartozó osztály SetTimeEffected
     * metódusával teszi meg
     * @param n hatás hossza
     * @param v virológus, akire hat
     */
    @Override
    public void setStatus(long n, Virologist v) {
        BearDance bearDance = new BearDance();
        bearDance.setTimeEffected(n);
        v.addEffect(bearDance);
    }

    /**
     * Visszaadja, hogy meddig lehet felhasználni
     * @return medig hat
     */
    public long getDuration(){
        return duration;
    }

    /**
     * Beállítja, hogy meddig lehet felhasználni
     * @param n mennyi ideig hat
     */
    public void setDuration(long n){
        duration = n;
    }
}
