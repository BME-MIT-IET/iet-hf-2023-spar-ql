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

## Bag menu tesztelése

### test1_BagDropItems

Leteszteli, hogy tudunk-e a Bagmenuből kidobni tárgyakat.
Három fajta tárgy van: ProtectiveGear, Material, Agent.
Mindegyiket külön teszteljük, a tesztelés menete azonban hasonló mindegyiknél:
Inicializációs állapot: mindegyik tesztesetnél a táskát feltöltjük az adott itemmel.
Ellenőrzi, hogy sikeresen hozzá lett-e adva.
Ezek után a robottal a táskára kattintunk, majd az adott tárgyra, és a felugró ablakon a discardra a tárgy eltávolításához.
Ellenőrzi, hogy a tárgy sikeresen el lett-e távolítva a táskából.

### test2_BagWearItems

Ez a teszt metódus ellenőrzi a táskából való tárgyak felvételek funkcionalitását. Az alábbi műveleteket végzi el:
Hozzáad egy Glove objektumot az aktív virológus táskájához és ellenőrzi, hogy sikeresen hozzá lett-e adva.
Ellenőrzi, hogy a Glove jelenleg nem viselt a virológus által.
Megnyitja a BagMenut, majd kattint az első sorra a védőfelszerelés táblázatban a BagMenuben, ezután "Wear!" opcióra kattintva felveszi a Glove-ot
Ezután ellenőrzi, hogy a védőfelszerelés sikeresen hozzá lett-e adva a táskából a viselt tárgyakhoz.

## Wear menu tesztelése

### test1_WearMenuUnWearItems

A viselt tárgyak levételének funkcionalitását teszteli a következőképpen:
Lekéri az aktív virológust a játék térképéről, majd hozzáad egy Glove objektumot a táskájához.
Ellenőrzi, hogy a táskában 1 védőfelszerelés található.
A virológus felveszi a táskájában lévő első védőfelszerelést.
Ellenőrzi, hogy a virológus 1 védőfelszerelést visel.
A robot rákattint a "Wear!" gombra a GameMenu ablakon.
Ellenőrzi, hogy a WearMenu ablak látható.
A robot rákattint az első cellára a védőfelszerelés táblázatban a WearMenuben.
Rákattint az "Unwear!" feliratú gombra a megjelenő lehetőségek közül.
Ellenőrzi, hogy a virológus nem visel védőfelszerelést, és a táskában továbbra is 1 védőfelszerelés található.

### test2_WearMenuDiscardItems

A viselt tárgyak kidobását teszteli a WearMenuben.
Hasonlóan működik, mint az első teszt, de az "Unwear!" gomb helyett a "Discard!" gombra kattint.
Ellenőrzi, hogy a virológus nem visel védőfelszerelést, és a táskájában sincs már egy védőfelszerelés sem.

## GeneticCodesMenuTest

### test1_MakeAgentFromGeneticCode

Ágens létrehozását teszteljük.
Tesztelésre kerül, hogy a létező GeneticCodeból és elegendő Materialból létre lehet-e hozni Agenteket.
Ehhez először létrehozunk egy új Agentet (ParalyzeAgent) a hozzá tartozó Materialokkal.
Az aktív virológus hozzáadja az Agent Materialjait a táskájához, majd megtanulja a genetikai kódot.
Ellenőrizzük, hogy az Agent, Materialok és a GeneticCode megfelelően kerültek-e beállításra.
A játék ablakon belül rákattintunk a "GeneticCodes" gombra, majd ellenőrizzük, hogy az ablak láthatóvá válik-e.
Rákattintunk az első sorra a GeneticCodes menünek táblázatában.
Rákattintunk a "Create Agent!" gombra az Agent létrehozásához.
Ellenőrizzük, hogy az aktív virológus táskájában egy Agent található, valamint a Materialok száma annyival csökkent-e, mint amennyit felhasználtunk az Agent létrehozásához.


## AttackTest

### testAttack

Azt a szcenáriót teszteljük, hogy egy virológus megfertőz egy másik virológust ágenssel.
Az alanyt vitusdance ágenssel fertőzzük meg, ehhez szüksége van a támadónak egy táskára és benne egy vitusdance ágensre.
A támadásnak még az is feltétele, hogy a 2 virológus egy mezőn legyen, így az elsőként sorra kerülő virológus lesz a célpont, a második a támadó.
Ehhez azért van szükség, hogy le tudjuk kérni az áldozat mezőjét és beállítsuk a támadó mezőjét erre, egy mezőre helyezve a kettőt.
Ehhez a kattintanunk kell az "End turn" gombra.
Miután beállítottuk az értékeket megfelelően a szcenárió tesztelésére, ellenőrízzuk, hogy a 2 virológus különböző entitások.
Rákattintunk a "Bag" gombra és az első ágensre a listából. Következőleg a "Use agent on other virologist".
Végezetül leellenőrizzük, hogy a célpont megfertőzödött-e az ágenssel.

## BearColorTest

### testBearColor

Azt a szcenáriót teszteljük, hogy egy virológus megfertőz egy másik virológust beardance ágenssel és az áldozat színe megfelelően változik-e.
A teszt előkészítéséhez a támadónak adunk egy táskát egy beardance ágenssel. A támadót az áldozat mezőjére állítjuk, ehhez az "End turn" gombot meg kell nyomni.
Miután beállítottuk az értékeket megfelelően a szcenárió tesztelésére, ellenőrízzuk, hogy a 2 virológus különböző entitások.
Rákattintunk a "Bag" gombra és az első ágensre a listából. Következőleg a "Use agent on other virologist" majd "End turn" gombra.
Végezetül leellenőrizzük, hogy az áldozat színe valóban fekete lett.

## ZGameOverTest

### gameOverNewGame

Azt a szcenáriót teszteljük, hogy amikor egy virológus kielégíti a nyerési kondíciókat, akkor feljön-e a dialógus ablak és új játékot lehet-e ezen keresztül indítani.
Ehhez az aktív virológus codeCount-ját 1-re állítjuk, hogy a következő inkrementálásnál nyerjen.
Inicializálunk egy laboratórium mezőt egy vitusdance ágens genetikai kódjával, ezt a mezőt beállítjuk az aktív virológus mezőjének.
Ezek után a "Collect"-re kattintunk, virológusunk megtanulja a genetikai kódot és ellenőrizzük, hogy a dialógus ablak megnyílt-e.
Következőleg a "New Game!" gombra kattintunk és ellenőrizzük, hogy az újonnan megnyílt menü valóban látható-e.

### gameOverZNewGame

Azt a szcenáriót teszteljük, hogy amikor egy virológus kielégíti a nyerési kondíciókat, akkor feljön-e a dialógus ablak és ki lehet az egész programból lépni.
Ehhez az aktív virológus codeCount-ját 1-re állítjuk, hogy a következő inkrementálásnál nyerjen.
Inicializálunk egy laboratórium mezőt egy vitusdance ágens genetikai kódjával, ezt a mezőt beállítjuk az aktív virológus mezőjének.
Ezek után a "Collect"-re kattintunk, virológusunk megtanulja a genetikai kódot és ellenőrizzük, hogy a dialógus ablak megnyílt-e.
Következőleg a "Exit!" gombra kattintunk, amivel System.exit(0) kódsorra futunk, ami az egész szoftvert leállítja.
A teszt sikeresnek tekinthető ha a státusza: "Test terminated"

Fontos, hogy ezt a tesztet utolsóként indítsa a tesztelő, mert maga a tesztelési folyamatból is kilép a System.exit(0), amivel a soron következő tesztek státusza "terminated" lesz.
Ezért kapott egy Z kezdést az osztály neve.

## Eredmények és tanulságok
### Eck Richárd
A házi feladat legnehezebb része és legidőigényesebb része számomra a megfelelő tesztelési környzetet kiválsztása volt.
Több lehetséges megoldást is kipróbáltam mielőtt megtaláltam volna az AssertJ-t. Szóval a tanulgás számomra az ebből az, hogy
nagyon fontos, hogy megtalálja az ember azt a tesztelési környezetet, amivel az igényei szerint tud tesztelni, de ez akár sokkal több időbe is telhet, mint az elején gondolta.
A tesztelés maga egészen egyszerű volt miután pár példán begyakorlotam az AssertJ-t. A tesztek írása illetve futtatása közben több helyen is észleltem hibákat a kódban és ezeket javítottam is.
Ezek a hibák főleg abból adódtak, hogy lokális változóként voltak tárolva a dolgok nem pedig globális változóként getter-rel és setter-rel.