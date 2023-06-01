# Load Test 

Készítette: Koppa Dániel 

### Teszt célja: 

Kideríteni, hogy több indítás esetén, hogyan reagál a program, milyen következményei lehetnek. 

### Hibák: 

A program nem ismeri fel, ha többször ugyanazt a példányt megnyitják, ezt semmilyen módon nem állítja meg.
Ugyanakkor belső működésében egyik megnyitott példányban sem okoz hibát a többszöri megnyitás. A modern számítógépek memóriája pedig elbír akár 100 megnyitott példányt is, így nem okoz nagy gondot ez a hiba. 
