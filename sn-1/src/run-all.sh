#!/bin/bash
#przed wykonaniem skryptu należy zbudować projekt sn-1, wyeksportować go do fatjara

#przegranie projektu na halpha
#cp sn-1.jar /home/w/work/halpha/

#uruchomienie jobów obliczających sumy wystąpień znaków
hadoop jar sn-1.jar -i /user/wlangiewicz/wikipedia-pl/chunks -o /user/wlangiewicz/wikipedia-pl-out
hadoop jar sn-1.jar -i /user/wlangiewicz/wikipedia/chunks -o /user/wlangiewicz/wikipedia-en-out
hadoop jar sn-1.jar -i /user/wlangiewicz/wikipedia-de/chunks -o /user/wlangiewicz/wikipedia-de-out
hadoop jar sn-1.jar -i /user/wlangiewicz/wikipedia-es/chunks -o /user/wlangiewicz/wikipedia-es-out
hadoop jar sn-1.jar -i /user/wlangiewicz/wikipedia-fr/chunks -o /user/wlangiewicz/wikipedia-fr-out
hadoop jar sn-1.jar -i /user/wlangiewicz/wikipedia-simple/chunks -o /user/wlangiewicz/wikipedia-simple-out
hadoop jar sn-1.jar -i /user/wlangiewicz/wikipedia-cs/chunks -o /user/wlangiewicz/wikipedia-cs-out
hadoop jar sn-1.jar -i /user/wlangiewicz/wikipedia-sk/chunks -o /user/wlangiewicz/wikipedia-sk-out

#ściągnięcie danych z HDFS
hadoop fs -get /user/wlangiewicz/wikipedia-pl-out/part-m-0000* .
cat part-m-0000* > wikipedia-pl-out.txt
rm part-m-0000*
hadoop fs -get /user/wlangiewicz/wikipedia-en-out/part-m-0000* . 
cat part-m-0000* > wikipedia-en-out.txt
rm part-m-0000*
hadoop fs -get /user/wlangiewicz/wikipedia-de-out/part-m-0000* . 
cat part-m-0000* > wikipedia-de-out.txt
rm part-m-0000*
hadoop fs -get /user/wlangiewicz/wikipedia-es-out/part-m-0000* . 
cat part-m-0000* > wikipedia-es-out.txt
rm part-m-0000*
hadoop fs -get /user/wlangiewicz/wikipedia-fr-out/part-m-0000* . 
cat part-m-0000* > wikipedia-fr-out.txt
rm part-m-0000*
hadoop fs -get /user/wlangiewicz/wikipedia-simple-out/part-m-0000* . 
cat part-m-0000* > wikipedia-simple-out.txt
rm part-m-0000*
hadoop fs -get /user/wlangiewicz/wikipedia-cs-out/part-m-0000* . 
cat part-m-0000* > wikipedia-cs-out.txt
rm part-m-0000*
hadoop fs -get /user/wlangiewicz/wikipedia-sk-out/part-m-0000* . 
cat part-m-0000* > wikipedia-sk-out.txt
rm part-m-0000*

#ściągnięcie danych testowych z HDFS
hadoop fs -get /user/wlangiewicz/wikipedia-pl-out/part-m-000*0 .
cat part-m-000* > test-wikipedia-pl-out.txt
rm part-m-000*
hadoop fs -get /user/wlangiewicz/wikipedia-en-out/part-m-000*0 . 
cat part-m-000* > test-wikipedia-en-out.txt
rm part-m-000*
hadoop fs -get /user/wlangiewicz/wikipedia-de-out/part-m-000*0 . 
cat part-m-000* > test-wikipedia-de-out.txt
rm part-m-000*
hadoop fs -get /user/wlangiewicz/wikipedia-es-out/part-m-000*0 . 
cat part-m-000* > test-wikipedia-es-out.txt
rm part-m-000*
hadoop fs -get /user/wlangiewicz/wikipedia-fr-out/part-m-000*0 . 
cat part-m-000* > test-wikipedia-fr-out.txt
rm part-m-000*
hadoop fs -get /user/wlangiewicz/wikipedia-simple-out/part-m-000*0 . 
cat part-m-000* > test-wikipedia-simple-out.txt
rm part-m-000*
hadoop fs -get /user/wlangiewicz/wikipedia-cs-out/part-m-000*0 . 
cat part-m-000* > test-wikipedia-cs-out.txt
rm part-m-000*
hadoop fs -get /user/wlangiewicz/wikipedia-sk-out/part-m-000*0 . 
cat part-m-000* > test-wikipedia-sk-out.txt
rm part-m-000*

#uruchomienie programów liczących prawdopodobieństwo wystąpienia znaków dla każdego z języków
java -Xmx6g -cp /home/wlangiewicz/sn-1.jar network.LanguageNetwork 3400 17 0.0 0.00 10 0.05 0.7 |less
