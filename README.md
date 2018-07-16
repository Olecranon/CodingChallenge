# Author Info
Coding Challenge

Shefang Wang

sfwang2@gmail.com

646-416-2754

# Programing Language
java with default packages
```
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
```

# Testing cases
1. The given itcont.txt is tested. 

2. A large dataset de_cc_data.txt is tested as well, which will take about 2 mins to run

# Things to notice
1. When split each line, One need to escape , inside the " ". For example: 
```
1962514471,ABAD,JORGE,"PANCRELIPASE 5,000",669.89
``` 
2. When calculate the total cost, int is not large enough. the Long type is used to hold the total cost value. for example
```
HARVONI,5202,5.99222254194E9
```
3. The price is in the unit of cent, not the whole number. This has been taken care of as well.

# Sample output for de_cc_data
[top_cost_drug_de_cc_data.txt](https://github.com/Olecranon/CodingChallenge/blob/master/src/top_cost_drug_de_cc_data.txt)
```
drug_name,num_prescriber,total_cost
HARVONI,5202,5.99222254194E9
CRESTOR,138200,2.70409734621E9
LANTUS SOLOSTAR,101227,2.23768843824E9
ADVAIR DISKUS,120044,2.03832587797E9
SPIRIVA,108356,1.96611700496E9
JANUVIA,94504,1.91049392517E9
REVLIMID,5450,1.88715089236E9
NEXIUM,107854,1.76121386156E9
LANTUS,87111,1.62314986431E9
LYRICA,103305,1.52690605219E9
...
...

ULTILET ALCOHOL SWAB,3,68.07
INSULIN PEN NEEDLE,1,64.58
VIRT-VITE GT,8,0.32
VIRT NATE,4,0.15
VIRTPREX,1,0.04
```

# Sample output for online testing result
[top_cost_drug.txt](https://github.com/Olecranon/CodingChallenge/blob/master/src/top_cost_drug.txt)
```
Results:
Cloning repo (https://github.com/Olecranon/CodingChallenge) succeeded
========
RUNNING: test_1
(stdout): ----------------------------------------------------
(stdout): 30380369
(stdout): CHLORPROMAZINE,2,3000
(stdout): BENZTROPINE MESYLATE,1,1500
(stdout): AMBIEN,2,300
(stdout): 33974459
-------------
TEST RESULTS:

Started process: 09:43:41
Ended process: 09:43:42
* Matched (drug_name,num_prescriber,total_cost)!
* Matched (CHLORPROMAZINE,2,3000)!
* Matched (BENZTROPINE MESYLATE,1,1500)!
* Matched (AMBIEN,2,300)!
test_1: 4 out of 4 is/are correct
* Passed!
Executed key tests: 1 out of 1 passed.
```
