# Scalability Test

Alaposabb utánajárás után azt ítéltem meg, hogy az alkalmazásunk szempontjából nem lehet általános ScalabilityTest-et véghez vinni.
Alapvetően a Scalability Test a következő adatok méréséről szól: válaszidő, teljesítmény, átadó képesség(internet). Majd ugyanezeknek az adatoknak a mérése, miután skálázzuk(fel-le) az egy időben alkalmazásunkat felhasználók számát, és az átlagos hívások számát az alkalmazásunkhoz.
Mivel az alkalmazásunk nem használ internet kapcsolatot, nincs se fájl feltöltés,se letöltés, és nem multiplayer, így a ScalabilityTest elvégzése lehetetlen/értelmetlen alkalmazásunknál.
