# Tache 3 - Flags JVM

## Changemements a test.yml
Description des modification sur test.yml  

1. L'ajout de cette stratégie permet de définir la liste de flags qu'on souhaite modifer. On pourra ensuite itérer cette matrix de configurations pour les 5 builds. 
```
strategy:
    matrix:
    jvm_flag:
        - "-XX:-UseG1GC"
        - "-XX:MaxHeapSize=256m"
        - "-XX:-TieredCompilation"
        - "-XX:-UnlockDiagnosticVMOptions -XX:+LogCompilation"
        - "-XX:-UseCompressedOops"
```

2. Cette étape est rajoutée afin d'améliorer la clarté lors de chaque build, en imprimant en sortie le flag modifié et sa valeur.
```
- name: Log JVM Flag
    run: |
    echo "Running tests with JVM flag: ${{ matrix.jvm_flag }}"
```

3. Nous avons modifié cette étape pour inclure l'option MAVEN_OPTS, qui, pour chacun des 5 builds, règle l'un des flags à sa valeur spécifiée précédemment par la matrice dans la stratégie.
```
- name: Build and Test with Maven
    timeout-minutes: 15
    run: |
    unset MAVEN_OPTS
    MAVEN_OPTS="${{ matrix.jvm_flag }}" ./mvnw clean test -Djava.awt.headless=true
```

## Liste de Flags modifiés
Description et justification des flags choisis (impact possible sur la qualité, la performance, l'observabilité)

**Build 1 - Flag: "UseG1GC"**  
Ce flag permet d'activer le G1 (Garbage-First) collector.  
Il est approprié lorsque l'on utilise des tas (Heap) de grande taille et permet une performance accrue pour une application a utilisation en temps réel comme MakelAngelo.    
Il réduit les lags dûs à la collecte de mémoire.

La valeur booléenne par défaut est true et nous la mettons à false, ce qui pourrait causer des fautes sur la performance et la stabilité (Qualité).

**Build 2 - Flag: "MaxHeapSize"**  
Ce flag permet de modifier la quantité de mémoire que le tas peut accumuler avant de devoir refaire une collecte.  
Ça peut avoir différentes conséquences: 
- Si l'application ne nécessite pas de grande quantité de mémoire,  
on augmente sa performance en évitant d'accumuler trop de données.
- Si l'application est gourmande en mémoire, on peut provoquer des exceptions sur les données.  
  
Bien que ces exceptions ne soient pas voulues, elles peuvent permettre de mettre en lumière des parties de code sensibles à la casse mémoire. La valeur par défaut est de 12876513280, ce qui est énorme et potentiellement trop permissible. Nous la mettons a 256, pour tester cette sensibilité à la casse mémoire.

**Build 3 - Flag: "TieredCompilation"**  
Ce flag permet d'activer ou désactiver une compilation par niveaux.  
Elle combine l'interprétation et la compilation JIT.
En désactivant ce flag, on va tester la JVM sans l'optimisation de cette compilation, ce qui pourrait avoir des impacts sur la performance, qui est maintenant non-optimisée. On doit s'assurer que l'absence de cette optimisation n'a pas d'impact important sur la qualité, cad le code n'est pas trop dépendant de l'optimisation JIT.

**Build 4 - Flags: "UnlockDiagnosticVMOptions" et "LogCompilation"**  
Le premier flag permet simplement de débloquer l'utilisation de flags de diagnostics plus avancés.  
LogCompilation est un flag qui permet de suivre la compilation du projet tout en renvoyant un "log" contenant plusieurs informations pertinentes sur les états du système.  
Par exemple, les méthodes compilées, les différentes optimisations en place pour chaque méthode, la raison de l'utilisation de telle ou telle compilation ou bien encore des durées de compilation/d'exécutions entre autres.

En modifiant la valeur de ces flags, on peut mieux observer les informations sur les optimisations en temps réel et potentiellement identifier des bottlenecks. (Note: ici, on aurait pu laisser UnlockDiagnosticVMOptions = true, mais le point focal est plutot sur l'activation de LogCompilation)

**Build 5 - Flag: "UseCompressedOops"**  
Ce flag permet de compresser des références d'objets dans 64 bits, ce qui optimise et réduit l'utilisation de mémoire dans le tas.

En mettant ce flag a false, on s'assure encore une fois de la robustesse du code et sa capacité à bien gérer sa mémoire, même sans optimisations comme UseCompressedOops.
