#!/bin/bash
#
# Use this shell script to compile (if necessary) your code and then execute it. Below is an example of what might be found in this file if your program was written in Python
#
#python ./src/pharmacy_counting.py ./input/itcont.txt ./output/top_cost_drug.txt
javac ./src/pharmacy_counting.java
cd src
java pharmacy_counting ../input/itcont.txt ../output/top_cost_drug.txt
# java pharmacy_counting "C:/Users/sfwang/Desktop/de_cc_data.txt" ../output/top_cost_drug_de_cc_data.txt