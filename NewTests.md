# NEW TESTS

### Notez:
"Un" test est considéré comme testant pour une seule méthode de la classe originale.  
Il est possible que plusieurs @test soient écrits pour traiter différents cas(branches, etc)  
de la méthode mais ceux-si seront considérés comme faisant partie du même test.

Un commentaire présent avant chaque test décrit son intention.

| N   | TEST_DIR                                                      | TEST_NAME(S)                                                 | WHY / DESCRIPTION |
|-----|---------------------------------------------------------------|--------------------------------------------------------------|-------------------|
| 1   | src/test/java/com/marginallyclever/convenience/QuadGraphTest  | [testInsertInside, testInsertOutside]                        |                   |
| 2   | src/test/java/com/marginallyclever/convenience/QuadGraphTest  | [testSplit]                                                  |                   |
| 3   | src/test/java/com/marginallyclever/convenience/QuadGraphTest  | [testSearchInside, testSearchOutside, testSearchInChildren ] |                   |

# Coverage achieved after new tests