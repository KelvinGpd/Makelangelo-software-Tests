# NEW TESTS

### Notez:
"Un" test est considéré comme testant pour une seule méthode de la classe originale.  
Il est possible que plusieurs @test soient écrits pour traiter différents cas(branches, etc)  
de la méthode mais ceux-si seront considérés comme faisant partie du même test.

Un commentaire(en anglais) présent avant chaque test décrit son intention.

| N   | TEST_DIR                                                              | TEST_NAME(S)                                                | 
|-----|-----------------------------------------------------------------------|-------------------------------------------------------------|
| 1   | src/test/java/com/marginallyclever/convenience/QuadGraphTest          | [testInsertInside, testInsertOutside]                       |                   
| 2   | src/test/java/com/marginallyclever/convenience/QuadGraphTest          | [testSplit]                                                 |                   
| 3   | src/test/java/com/marginallyclever/convenience/QuadGraphTest          | [testSearchInside, testSearchOutside, testSearchInChildren] |                   
| 4   | src/test/java/com/marginallyclever/convenience/noise/SimplexNoiseTest | [testNoise1D, testNoise2D, testNoise3D]                     |                  
| 5   | src/test/java/com/marginallyclever/convenience/noise/SimplexNoiseTest | [testNoiseOutputDistribution]                               |                  


# Choice Justification (grouped by class)

### 1.QuadGraph

### 2.SimplexNoise


# Coverage achieved after new tests
**CLASS** QuadGraph.java: 0 -> 73%  
**CLASS** SimplexNoise.java: 0 ->

# Overall coverage improvement
