***Changements à la GitHub action:***  
blablabla à faire par Kelvin.
  
**Build 1 - Flag: "UseG1GC"**  
Ce flag permet d'activer le G1 (Garbage-First) collector.  
Il est approprié lorsque l'on utilise des tas (Heap) de grande taille et  
permet une performance accrue pour une application a utilisation en temps réel comme MakelAngelo.    
Il réduit les lags dûs à la collecte de mémoire.

**Build 2 - Flag: "MaxHeapSize"**  
Ce flag permet de modifier la quantité de mémoire que le tas peut accumuler avant de devoir refaire une collecte.  
Ça peut avoir différentes conséquences: 
- Si l'application ne nécessite pas de grande quantité de mémoire,  
on augmente sa performance en évitant d'accumuler trop de données.
- Si l'application est gourmande en mémoire, on peut provoquer des exceptions sur les données.  
  
Bien que ces exceptions ne soient pas voulues, elles peuvent permettre de mettre en lumière des parties de code sensibles à la casse mémoire.

**Build 3 - Flag: "TieredCompilation"**  
Ce flag permet d'activer ou désactiver une compilation par niveaux.  
Elle combine l'interprétation et la compilation JIT.  
En désactivant ce flag, on va tester la JVM sans l'optimisation de cette compilation.

**Build 4 - Flags: "UnlockDiagnosticVMOptions" et "LogCompilation"**  
Le premier flag permet simplement de débloquer l'utilisation de flags de diagnostics plus avancés.  
LogCompilation est un flag qui permet de suivre la compilation du projet tout en renvoyant un "log" contenant plusieurs informations pertinentes sur les états du système.  
Par exemple, les méthodes compilées, les différentes optimisations en place pour chaque méthode, la raison de l'utilisation de telle ou telle compilation ou bien encore des durées de compilation/d'exécutions entre autres.

**Build 5 - Flag: "UseCompressedOops"**  
Ce flag permet de compresser des références d'objets dans 64 bits, ce qui optimise et réduit l'utilisation de mémoire dans le tas.  
