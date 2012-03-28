#!/bin/bash

#rozmiar danych testowych
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 2 17 0.0 0.00 1000 0.2 0.8 > 1.2 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 4 17 0.0 0.00 1000 0.2 0.8 > 1.4 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 8 17 0.0 0.00 1000 0.2 0.8 > 1.8 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 16 17 0.0 0.00 1000 0.2 0.8 > 1.16 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 32 17 0.0 0.00 1000 0.2 0.8 > 1.32 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 64 17 0.0 0.00 1000 0.2 0.8 > 1.64 & 
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 128 17 0.0 0.00 1000 0.2 0.8 > 1.128 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1000 0.2 0.8 > 1.256 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 512 17 0.0 0.00 1000 0.2 0.8 > 1.512 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 1024 17 0.0 0.00 1000 0.2 0.8 > 1.1024 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 2048 17 0.0 0.00 1000 0.2 0.8 > 1.2048 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 3400 17 0.0 0.00 1000 0.2 0.8 > 1.3400 &
#ilość epok
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 2 0.2 0.8 > 2.2 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 4 0.2 0.8 > 2.4 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 8 0.2 0.8 > 2.8 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 16 0.2 0.8 > 2.16 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 32 0.2 0.8 > 2.32 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 64 0.2 0.8 > 2.64 & 
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 128 0.2 0.8 > 2.128 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 256 0.2 0.8 > 2.256 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 512 0.2 0.8 > 2.512 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1024 0.2 0.8 > 2.1024 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 2048 0.2 0.8 > 2.2048 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 4096 0.2 0.8 > 2.4096 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 8192 0.2 0.8 > 2.8192 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 16384 0.2 0.8 > 2.16384 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 50000 0.2 0.8 > 2.50000 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 100000 0.2 0.8 > 2.100000 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 200000 0.2 0.8 > 2.200000 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 500000 0.2 0.8 > 2.500000 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1000000 0.2 0.8 > 2.1000000 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 2000000 0.2 0.8 > 2.2000000 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 5000000 0.2 0.8 > 2.5000000 &
#wartość momentum
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 10000 0.2 0.6 > 3.0.6 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 10000 0.2 0.65 > 3.0.65 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 10000 0.2 0.7 > 3.0.7 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 10000 0.2 0.75 > 3.0.75 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 10000 0.2 0.8 > 3.0.8 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 10000 0.2 0.85 > 3.0.85 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 10000 0.2 0.9 > 3.0.9 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 10000 0.2 0.95 > 3.0.95 &
#wsp. uczenia
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1000 0.05 0.8 > 4.0.05 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1000 0.1 0.8 > 4.0.1 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1000 0.15 0.8 > 4.0.15 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1000 0.2 0.8 > 4.0.2 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1000 0.25 0.8 > 4.0.25 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1000 0.3 0.8 > 4.0.3 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1000 0.35 0.8 > 4.0.35 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1000 0.4 0.8 > 4.0.4 &
#ilość neuronów ukrytych
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 14 0.0 0.00 1000 0.2 0.8 > 5.14 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 15 0.0 0.00 1000 0.2 0.8 > 5.15 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 16 0.0 0.00 1000 0.2 0.8 > 5.16 & 
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 1000 0.2 0.8 > 5.17 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 18 0.0 0.00 1000 0.2 0.8 > 5.18 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 19 0.0 0.00 1000 0.2 0.8 > 5.19 & 
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 20 0.0 0.00 1000 0.2 0.8 > 5.20 & 
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 21 0.0 0.00 1000 0.2 0.8 > 5.21 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 22 0.0 0.00 1000 0.2 0.8 > 5.22 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 23 0.0 0.00 1000 0.2 0.8 > 5.23 & 
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 24 0.0 0.00 1000 0.2 0.8 > 5.24 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 25 0.0 0.00 1000 0.2 0.8 > 5.25 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 26 0.0 0.00 1000 0.2 0.8 > 5.26 & 
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 27 0.0 0.00 1000 0.2 0.8 > 5.27 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 28 0.0 0.00 1000 0.2 0.8 > 5.28 &
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 29 0.0 0.00 1000 0.2 0.8 > 5.29 & 

#max
java -Xmx6g -cp /srv/tmp/sn-1/sn-1.jar network.LanguageNetwork 256 17 0.0 0.00 100000 0.2 0.6 > test-max &