# Effeektusok tesztjei

### Készítette:
Koppa Dániel

## Tesztolyáok és Eredmények: 

EffectsTest -> Tesztelt funckiók: Effects minden publikus metódusa kivéve a setVirologist, mivel a virologist nem elérhető. 

stubEffect: anoním burkoló osztály a hiányzó funkció megvalósítására.
dummy{Objektum}ok: anonim burkoló osztályok a más osztályok reprezentálására.

Eredmény: Passed

UntouchableTest extends EffectsTest -> Tesztelt funckiók: Untouchable minden publikus metódusa

Eredmény: FAILED setTimeEffected nem várt működést hajt végre, az effektus által tartalmazott virológust is használni akarja. 

ParalyzeAndUnstoppableAgentTest extends EffectsTest -> Tesztelt funckiók: ParalyzeAgent és UnstoppableAgent minden publikus metódusa

Eredmény: Passed

VitusDanceAgentTest extends EffectsTest -> Tesztelt funckiók: VitusDanceAgent minden publikus metódusa

Eredmény: Passed

ForgetAgentTest extends EffectsTest -> Tesztelt funckiók: ForgetAgent minden publikus metódusa

Eredmény: Passed 