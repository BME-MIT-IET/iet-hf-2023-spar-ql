# UI Tesztek

A teszteket JUnitot illetve AssertJ használatával végeztük el.

## Main menu tesztelése

A Main menu a játék indításakor jelenik meg,
illetve ha játék közben új játékot indítanánk.

### setUp

Létrehoz egy új játékot ahhoz, hogy megjelenítse az új Main menu-t.
Létrehoz egy BasicRobotot, majd ezt egy FrameFixture-be helyezi.
Majd megjeleníti a FrameFixture-t.

### tearDown

Kiüríti a FrameFixture-t.

### test1_Init

Megvizsgálja, hogy az indítást követően megfelelően jelenik-e meg a menü.
Alapból a Map1 és a 4 player opcióknak kell kijelölve lennie.
A tesztelés során lekérjük a rádiógombok állapotát, majd megvizsgáljuk, hogy melyek vannak kiválasztva.

### test2_Change

Az előző feladathoz hasonló módon vizsgáljuk ezt is. A rádiógombok közül kiválasztjuk a Map2-t és a 6 player opciót. Majd leellenőrizzük, hogy valóban megváltozott-e a gombok állapota.

### test3_Start

Megvizsgáljuk, hogy a Main menu tényleg eltűnik-e a képernyőről. Ezt követően beállítjuk a Game menu-t a FrameFixture-nek és ellenőrizzük, hogy jó-e a megjelenő rész címe.

## Game menu tesztelése

A Game menu az a képernyő, ahol a játék zajlik.

### setUp

Létrehoz egy új játékot ahhoz, hogy megjelenítse az új Main menu-t.
Létrehoz egy BasicRobotot, majd ezt egy FrameFixture-be helyezi.
Ezt követően a Main menu-ben az alap beállításokkal új játékot indítunk és
ezt állítjuk be a FrameFixture-nek. Majd megjeleníti a FrameFixture-t.

### tearDown

Kiüríti a FrameFixture-t.

### test1_NewGame

Leteszteli, hogy tudunk-e új játékot létrehozni a Game menu-ből.
Ezt azzal tudjuk elérni, hogy az ablakban található Game menu pontra rákattintunk,
és az lenyílik, majd miután ez megtörtént, rákattintunk az új játék opcióra. Ezzel
megjelenik a Main menu, amit beállítunk a FrameFixture-nek és itt teszteljük,
hogy valóban a Main menu jelenítődött-e meg. Ezt azzal tesszük, hogy a Main menu
címe valóban "New Game".

### test2_Bag

Ebben a tesztben a Main menu megnyitásához hasonló módszereket használunk.
Itt viszont a Bag menüt nyitjuk meg, amit a Game menu Bag gombjával tehetjük meg.

### test3_GeneticCodes

Ebben a tesztben a Main menu megnyitásához hasonló módszereket használunk.
Itt viszont a Genetic Codes menüt nyitjuk meg, amit a Game menu Genetic Codes gombjával
tehetjük meg.

### test4_Collect

Végigmegy az összes játékoson, ezt az End Turn gomb lenyomásával.
Minden játékosnál megnézi, hogy milyen típusú mezőn van. Ha nem egy sima mezőn
áll, akkor megnyomja a Collect gombot, majd ellenőrzi, hogy növekedett-e a
mennyiség az adott tárgyból a virológus statisztikáit taglaló panelen.

### test5_Wear

Ebben a tesztben a Main menu megnyitásához hasonló módszereket használunk.
Itt viszont a Wear menüt nyitjuk meg, amit a Game menu Wear gombjával
tehetjük meg.

### test6_EndTurn

Ebben a tesztben azt teszteljük, hogy az End Turn gomb valóban változtatja-e
a virológust. Ezt azzal tesszük meg, hogy megnézzük, hogy a statisztikák kiírásai
megfelelőek-e és hogy a Game menu által visszaadott virológus különbözik-e
az End Turn előtti virológustól. Emellett ellenőrizzük, hogy valóban
megváltozott-e a virológus karakterének színe.

### test7_Move

Ebben a tesztben lekérjük a virológus 0-dik szomszédos mezőjét és ennek a
középpontját. Majd lekérjük az aktuális mezőt, amin a virológus van.
Ezt követően a robottal a játéktér megjelenítő panelen a lekért pontra kattintunk.
Majd lekérjük az új mezőt és összehasonlítjuk az előző mezővel.
