# Tárgyak tesztesetei

### Készítette:
Litavecz Marcell

## Tesztosztályok és Eredmények:
***

### ***AxeTest***

#### ***Tesztelt funkciók:***

Támadás a baltával (Axe.Attack -> Test_Attack) Eredmény: PASSED 

Balta használata (Axe.Use -> Test_Use) Eredmény: PASSED 

Balta megsemmisítése (Axe.Destroy -> Test_Destroy) Eredmény: PASSED

Balta elvétele (Axe.TakeAway -> Test_TakeAway) Eredmény: PASSED

### ***BonusBagTest***

#### ***Tesztelt funkciók:***

Bónusz táska hozzáadása a Virológushoz (BonusBag.SetAttribute -> Test_SetAttribute) Eredmény: PASSED

Bónusz táska elvétele (BonusBag.TakeAway -> Test_TakeAway) Eredmény: PASSED

Bónusz táska viselése (BonusBag.Wear -> Test_Wear) Eredmény: PASSED

Bónusz táska megsemmisítése (BonusBag.Destroy -> Test_Destroy) Eredmény: PASSED

### ***CapeTest***

#### ***Tesztelt funkciók:***

Köpeny hozzáadása a Virológushoz (Cape.SetAttribute -> Test_SetAttribute) Eredmény: PASSED

Köpeny elvétele (Cape.TakeAway -> Test_TakeAway) Eredmény: PASSED

Köpeny viselése (Cape.Wear -> Test_Wear) Eredmény: PASSED

Köpeny megsemmisítése (Cape.Destroy -> Test_Destroy) Eredmény: PASSED

### ***GloveTest***

#### ***Tesztelt funkciók:***

Kesztyű hozzáadása a Virológushoz (Glove.SetAttribute -> Test_SetAttribute) Eredmény: PASSED

Kesztyű elvétele (Glove.TakeAway -> Test_TakeAway) Eredmény: PASSED

Kesztyű használata, ha a használhatóság dobás után még mindig nem nulla (Glove.Use -> Test_Use_WhenRemainingUsesGreaterThanZero) Eredmény: PASSED

Kesztyű használata, ha a használhatóság dobás után nulla (Glove.Use -> Test_Use_WhenRemainingUsesBecomesZero) Eredmény: PASSED

Ágens visszadobása, ha a célpont nem érinthetetlen (Glove.ThrowBakc -> Test_ThrowBack_WhenNotUntouchable) Eredmény: PASSED

Ágens visszadobása, ha a célpont érinthetetlen (Glove.ThrowBakc -> Test_ThrowBack_WhenUntouchable) Eredmény: PASSED

### ***ItemTest***

Teszteléséhez egy leszármazott osztályt használtam hiszen ez Item absztrakt.

#### ***Tesztelt funkciók:***

A kapott tárgy összehasonlítása önmagával, ha nem egyeznek (Item.ItemEqual -> Test_ItemEqual_WhenItemsAreDifferent) -> Eredmény: PASSED

A kapott tárgy összehasonlítása önmagával, ha egyeznek (Item.ItemEqual -> Test_ItemEqual_WhenItemsAreSame) -> Eredmény: PASSED