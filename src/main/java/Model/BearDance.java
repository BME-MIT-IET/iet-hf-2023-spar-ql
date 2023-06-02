package Model;

/**
 * Az ágens virológusra gyakorolt hatás idejét tárolja, valmaint kifejti a hatását a virológusra
 */
public class BearDance extends Effects implements Steppable {

    private long effected;

    /**
     * Lépteti az időt. Addig hívja a függvényt, amíg nincs vége a játéknak, vagy nem
     * hal meg a virológus
     */
    public BearDance(){
        effected = 0;
    }

    /**
     * Beállítja, hogy meddig hat a virológusra az ágens
     */
    @Override
    public void Step() {
        if(effected > 0) {
            effected--;
            if(effected == 0){
                virologist.removeEffect(this);
            }
        }
    }

    /**
     * Visszaadja, hogy meddig gyakorol hatást a virológusra
     * @param n az idő amíg hat
     */
    @Override
    public void setTimeEffected(long n) {
        effected = n;
    }

    /**
     * Meddig hat az effect
     * @return
     */
    public long getTime() {
        return effected;
    }
}
