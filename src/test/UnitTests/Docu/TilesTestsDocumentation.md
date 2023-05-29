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

### ***LaboratoryTest***

#### ***Tesztelt funkciók:***

Virológus fogadása, ha nincs medvetánc a mezőn (Laboratory.Accept -> Test_Accept_WhenBearDanceNotNull) Eredmény: PASSED

Gyűjthető objektum visszaadása (Laboratory.GetCollectable -> Test_GetCollectable) Eredmény: PASSED

A labor használja a medvetáncot a virológuson, ha az nem érinthetetlen és véletlen szám is jó (Laboratory.LaboratoryInfect -> Test_LaboratoryInfect_WhenVirologistIsNotUntouchableAndRandomIsGreater) Eredmény: PASSED

Fertőzés megadása, ha a medevetánc null (Laboratory.isInfects -> Test_IsInfects_WhenBearDanceIsNull) Eredmény: PASSED

Fertőzés megadása, ha a medevetánc nem null (Laboratory.isInfects -> Test_IsInfects_WhenBearDanceIsNotNull) Eredmény: PASSED

### ***ShelterTest***

#### ***Tesztelt funkciók:***

Gyűjthető objektum visszaadása (Shelter.GetCollectable -> Test_GetCollectable) Eredmény: PASSED

### ***StorageTest***

#### ***Tesztelt funkciók:***

Gyűjthető objektum visszaadása (Storage.GetCollectable -> Test_GetCollectable) Eredmény: PASSED

Gyűjthető objektum beállítása, ha az anyagok listája null (Storage.setCollectable -> Test_SetCollectable_WhenMaterialsIsNull) Eredmény: PASSED

Gyűjthető objektum beállítása, ha az anyagok listája nem null (Storage.setCollectable -> Test_SetCollectable_WhenMaterialsIsNotNull) Eredmény: PASSED

Anyag visszaadása, ha az anyagok listája üres(Storage.Collect -> Test_Collect_WhenMaterialsSizeIsZero) Eredmény: PASSED

Anyag visszaadása, ha az anyagok listája nem üresn (Storage.Collect -> Test_Collect_WhenMaterialsIsNotZero) Eredmény: PASSED

Anyagok listájának kiürítése (Storage.DestroyMaterial -> Test_DestroyMaterial) Eredmény: PASSED