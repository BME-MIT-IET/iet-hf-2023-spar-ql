# Mezők tesztesetei

### Készítette:
Litavecz Marcell

## Tesztosztályok és Eredmények:
***

### ***TileTest***

#### ***Tesztelt funkciók:***

Virológus eltávolítása a mezőről (Tile.Remove -> Test_Remove) Eredmény: PASSED

Virológus hozzáadása a mezőhöz, ha nincs már hely (Tile.Accept -> Test_Accept_WhenCapacityIsFull) Eredmény: PASSED

Virológus hozzáadása a mezőhöz, ha van hely (Tile.Accept -> Test_Accept_WhenCapacityIsNotFull) Eredmény: PASSED

Az n-edik szomszédos mező megadása, ha az n-edik mező nem szomszéd (Tile.GetNeighbor -> Test_GetNeighbor_WhenNIsInvalid) Eredmény: PASSED

Az n-edik szomszédos mező megadása, ha az n-edik mező szomszéd (Tile.GetNeighbor -> Test_GetNeighbor_WhenNIsValid) Eredmény: PASSED

Szomszédos mező beállítása (Tile.SetNeighbor -> Test_SetNeighbor) Eredmény: PASSED

Szomszédság beállítása visszafele is (Tile.setTheOtherNeighbor -> Test_SetOtherNeighbor) Eredmény: PASSED

Gyűjthető objektum visszaadása (Tile.GetCollectable -> Test_GetCollectable) Eredmény: PASSED

Másik virológus visszaadása a mezőről, ha nincs más virológus a mezőn (Tile.GetOtherVirologist -> Test_GetOtherVirologist_WhenThereIsNoOtherVirologist) Eredmény: PASSED

Másik virológus visszaadása a mezőről, ha van más virológus a mezőn (Tile.GetOtherVirologist -> Test_GetOtherVirologist_WhenThereIsOtherVirologist) Eredmény: PASSED