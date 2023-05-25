# Táska tesztesetei

### Készítette:
Litavecz Marcell

## Tesztosztályok és Eredmények:
***

### ***BagTest***

#### ***Tesztelt funkciók:***

Táska méretének növelése bónusszal (Bag.SetBonusSize -> Test_SetBonusSize) Eredmény: PASSED 

Táskában lévő ágensek, tárgyak és anyagok együttes méretének lekérdezése (Bag.getUsedSize -> Test_UsedSize) Eredmény: PASSED 

Ágens eldobása a táskából (Bag.Discard(Agent) -> Test_Discard_Agent) Eredmény: PASSED

Tárgy eldobása táskából, ha nem nincs viselve (Bag.Discard(ProtectiveGear) -> Test_Discard_ProtectiveGear_WhenProtectiveGearIsNotWorn) Eredmény: PASSED

Tárgy eldobása táskából, ha viselve van (Bag.Discard(ProtectiveGear) -> Test_Discard_ProtectiveGear_WhenProtectiveGearIsWorn) Eredmény: PASSED

Ágens hozzáadása a táskához (Bag.Add(Agent) -> Test_Add_Agent) Eredmény: PASSED

Tárgy hozzáadása a táskához (Bag.Add(ProtectiveGear) -> Test_Add_ProtectiveGear) Eredmény: PASSED

Anyag hozzáadása a táskához (Bag.Add(Material) -> Test_Add_Material) Eredmény: PASSED