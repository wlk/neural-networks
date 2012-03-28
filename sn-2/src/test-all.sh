#!/bin/bash

#ilość wektorów wejściowych
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 10 10 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 10 30 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 10 50 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 10 70 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 10 100 &


#ilość epok
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 1 100 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 2 100 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 4 100 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 8 100 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 16 100 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 32 100 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 64 100 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 128 100 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 500 100 &
java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 1000 100 &


java -Xmx2g -cp /home/wlangiewicz/sn-2/sn-2.jar w.SOM2 100 100 &
