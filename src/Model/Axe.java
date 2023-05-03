package Model;

import java.util.ArrayList;

/**
 * Lehetővé teszi azt, hogy a virológus megöljön egy másik virológust, ha használható a balta.
 */
public class Axe extends ProtectiveGear{
    private boolean usable;

    /**
     * A balta konstruktora. A useable-t true-ra állítja és superrel beállítja a
     * kapott nevet
     * @param name a balta neve
     */
    public Axe(String name){
        super(name);
        usable = true;
    }

    /**
     * A virológus által viselt tárgyak listájából kiveszi a baltát,
     * valamint a táskájából is
     * @param v A virológus akitől elveszi
     */
    @Override
    public void takeAway(Virologist v) {
        v.getBag().Discard(this);
        ArrayList<ProtectiveGear> wear = v.getWear();
        for(ProtectiveGear pg : wear){
            if(this == pg){
                v.Unwear(this);
            }
        }
    }

    /**
     * Beállítja, hogy melyik virulogusé a balta
     * @param v A virológus, akié a lesz a tárgy
     */
    @Override
    public void setAttribute(Virologist v) {
        virologist = v;
    }

    /**
     * A viselés, de ezt a tárgyat nem viselheti
     */
    @Override
    public void Wear() { }

    /**
     * Megtámad egy virológus és megöli. Meghívja rajta a Die
     * metódusát
     * @param v akit megtámad
     */
    public void Attack(Virologist v){
        if(usable) {
            v.Die();
            usable = false;
        }
    }

    /**
     * Elpusztítja a baltát, ehhez meghívja a takeAway függvényt
     */
    public void Destroy(){
        this.takeAway(this.virologist);
    }

    /**
     * Használja a baltát, mai nála van, ehhez meghívja az Attack- ot
     * @param v a virológus, akit megtámad
     * @param a Az ágensa, ami ebben az esetben null
     */
    public void Use(Virologist v, Agent a){
        this.Attack(v);
    }

    /**
     * Megmondja, hogy a balta használható-e még
     * @return használható-e a balta
     */
    public boolean getUsable(){
        return usable;
    }
}
