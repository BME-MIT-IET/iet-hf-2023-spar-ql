# Maven és CI beüzemelése:
## A maven hozzáadásának menete: 
Intliij Idea projekt jobb klikk -> Add Framework Support -> Kiválasztottam a Mavent

Ez a lépés az Issue-ban a maven added pull request-ben van.

*Magarázat:*

Azért esett a választás a Mavenre, mert azzal dolgozunk már mindannyian.

## CI hozzáadása:

A GitHub-on az Actions fülön létrehoztam egy új Java with Maven-t. Az így keletkező fájlból aztán kitöröltem az Update Dependency Graph részt, mert úgy ítéltem meg, hogy arra nem lesz szükségünk.

Ez a Issue-ban Add Actions pull request-ben van.

*Magarázat:*

A CI-k közül a GitHub Actions-t választottam, mert az már csináltam a laboron.

## Eredmények és tanulságok
### Eck Richárd
Eredményként itt azt tudnám megemlíteni, hogy a Maven és a CI hozzáadásra került a projekthez.
Tanulságként pedig azt, hogy ezeket a műveleteket viszonlag könnyű elvégezni, de az Update Dependency Graph része a CI-nek egy nehezebb része.
Több megoldással is próbálkoztam, de nekem nem sikerült egy működőképes változatott csinálnom.