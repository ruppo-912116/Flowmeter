#! /bin/sh

echo "starting..."

banner IDS 

cd flowmeter

nohup python3 -u server/server.py turkey2.man@gmail.com hivfwrangmakvycy >> server.log &

sleep 5
echo "30% done"


nohup  gradle exeCmd >> cmd.log &

sleep 20

echo "60% done"

nohup python3 server/traffice.py &

sleep 5

echo "100% complete"



