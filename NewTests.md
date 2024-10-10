# NEW TESTS

### Notez:
"Un" test est considéré comme testant pour une seule méthode de la classe originale.  
Il est possible que plusieurs @test soient écrits pour traiter différents cas(branches, etc)  
de la méthode mais ceux-si seront considérés comme faisant partie du même test.

Un commentaire(en anglais) présent avant chaque test décrit son intention.

| N    | TEST_DIR                                                               | TEST_NAME(S)                                                | 
|------|------------------------------------------------------------------------|-------------------------------------------------------------|
| 1    | src/test/java/com/marginallyclever/convenience/QuadGraphTest           | [testInsertInside, testInsertOutside]                       |                   
| 2    | src/test/java/com/marginallyclever/convenience/QuadGraphTest           | [testSplit]                                                 |                   
| 3    | src/test/java/com/marginallyclever/convenience/QuadGraphTest           | [testSearchInside, testSearchOutside, testSearchInChildren] |
| 4    | src/test/java/com/marginallyclever/convenience/QuadGraphTest           | [testCountPoints]                                           |
| 5(6) | src/test/java/com/marginallyclever/convenience/noise/SimplexNoiseTest  | [testNoise1D, testNoise2D, testNoise3D]                     |                  
| 6(5) | src/test/java/com/marginallyclever/convenience/noise/SimplexNoiseTest  | [testNoiseOutputDistribution]                               |
| 7    | src/test/java/com/marginallyclever/convenience/noise/CellularNoiseTest | [testNoise1D, testNoise2D, testNoise3D]                     |
| 8    | src/test/java/com/marginallyclever/convenience/Point2DTest             | [testNormalize]                                             |
| 9    | src/test/java/com/marginallyclever/convenience/Point2DTest             | [testAdd]                                                   |
| 10   | src/test/java/com/marginallyclever/convenience/Point2DTest             | [testScale]                                                 |

# Choice Justification (grouped by class)

### 1.QuadGraph
Cette classe fait partie de convenience package, un des package avec le plus grand nombre  
d'instructions dans le module. Quad graph avait un couvrage de 0% (aucuns test) mais contient  
des methodes importantes quant au traçage dans une dimension donnée.

### 2.SimplexNoise
Cette classe fait partie de convenience noise package, un des package avec le plus grand  
nombre d'instructions dans le module, avec un couvrage minime. Simplex noise est un algorithme  
pour la generation de "noise" et le code est basé sur des hypothèses mathématiques  
complexes. En lisant le code, on devient un peu confus quant aux raisons des opérations.  
Il est alors important de vérifier avec plusieurs tests si les résultats de ces opérations 
génèrent bel et bien du "noise" adéquat.

**note**: en réalité 5 et 6 testent trois fonctions totales mais ce regroupement fait plus de sens. 5 teste  
pour les 3 fonctions et 6 s'assure que, pour un grand nombre, le "noise" est valide.

### 3. CellularNoise
Cette classe fait partie de convenience noise package, un des package avec le plus grand  
nombre d'instructions dans le module, avec un couvrage minime.  
De la même manière que pour la classe SimplexNoise, la classe utilise des concepts mathématiques poussés dont il faut
s'assurer le bon fonctionnement dans le cadre désiré et que le 'noise' généré est correct.

### 4. Point2D
Cette classe fait partie de convenience package, un des package avec le plus grand  
nombre d'instructions dans le module.  
Certaines instructions étaient déjà testées dans les fichiers de tests mais une bonne partie de fonctions primordiales comme 
le scaling, la normalisation ou l'addition n'étaient pas testées.


# Coverage achieved after new tests
**CLASS** QuadGraph.java:  
Instruction 0 -> 80%  
Branch 0 -> 82%

**CLASS** SimplexNoise.java:  
Instruction 0 -> 69%  
Branch: 0 -> 22%

**CLASS** CellularNoise.java:  
Instruction 0 -> 88%  
Branch: 0 -> 84%

**CLASS** Point2D.java:  
Instruction 45% -> 100%  
Branch 50% -> 100%  

# Overall coverage improvement
Couvrage avant l'ajout des tests:  
Instruction: **25%**
Branch: **17%**


Couvrage après l'ajout des tests: 
Instruction: **28%**
Branch: **19%**
