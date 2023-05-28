# Virologist tesztjei

### Készítette: 
Dudich Gábor

## Tesztosztályok és Eredmények: 
VirologistTest

Tesztelt funkciók: A Virologist minden publikus metódusa.
Minden teszt előtt inicializálom a virológust az alap konstruktorával, a teszt céljából a megfelelő attribútumait is.

Move: Virológus egyik mezőről a másikra lép, mező ID alapján.

Eredmény: PASSED

UseAgent: A virológus a paraméterül kapott ágenst felhanszálja a paraméterül kapott virológus ellen.

Eredmény: PASSED

TakeGear: A virológus a pataméterül kapott védőfelszerelést elveszi a paraméterül kapott virológustól.

Eredmény: PASSED

learnCode: A virológus a paraméterül kapott genetikai kódot megtanulja, hozzáadja a listájába, inkrementálja  a nyerési feltételt kielégíthető attribútumát.

Eredmény: PASSED

collectProtectiveGear: A virológus az óvóhely mezőjén felveszi a védőfelszerelést, ha van elég helye a táskájában.

Eredmény: PASSED

palpateWall: A virológus a laboratórium mezőjén letapogatja a laboratórium genetikai kódját és megtanulja, ha még nem tanulta meg.

Eredmény: PASSED

wear: A virológus a pataméterül kapott védőfelszerelést felveszi, ha még nem vette fel.

Eredmény: PASSED

hitByAgent: A virológus a paraméterül kapott ágenssel megfertőzödik.

Eredmény: PASSED

giveGear: Komment "A virológus odaadja a védőfelszerelést egy másik virológusnak", ez önmagában nem igaz, a TakeGear metódusa használja fel, a giveGear csak eltávolítja a paraméterül kapott védőfelszerelést a táskájából.

Eredmény: PASSED

collectMaterial: A virológus a raktár mezőjén begyűjt egy anyagot, ha van elég hely a táskában.

Eredmény: PASSED

addAgent: A virológus a paraméterül kapott ágenst leltárába veszi, ha van elég hely a táskában.

Eredmény: PASSED

unwear: A virológus a paraméterül kapott védőfelszerelést leveszi.

Eredmény: PASSED

die: A virológus meghal, tehát a mezőjéről és a map-ról eltávolításra kerül.

Eredmény: PASSED

useMaterials: A virológus megnézi, hogy a paraméterül kapott material-okkal rendelkezik-e a leltárában, boolean visszatérési érték.

Eredmény: PASSED

addEffect: A virológus a paraméterül kapott effect példányt az effect listájához adja, ergó megfertőzödik.

Eredmény: PASSED

vitusDanceActionPerform: A virológus vitustáncot végez, ekkor random mozog egy szomszédos mezőre.

Eredmény: PASSED

bearDanceActionPerform: A virológus a medvetáncát végzi el, ekkor a véletlenszerűen mozog és a kiinduló mezőn és a célmezőn lévő virlógust megfertőzi, ha a célmező raktár, akkor minden material-t elpusztít.

Eredmény: PASSED

removeEffect: A virlógust a paraméterül kapott effect példányt az effect listájából eltávolítja, ergó meggyógyul.

Eredmény: PASSED

Minden setter

Eredmény: PASSED

