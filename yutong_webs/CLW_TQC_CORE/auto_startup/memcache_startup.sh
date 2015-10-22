#!/usr/bin/sh
# AUTHOR: chenqiong
# DATE: 2012
LANG=zh_CN.GBK;export LANG
env |grep LANG
#JAVA_HOME=/usr;export JAVA_HOME
USER=`who am i | awk '{print $1}'`
DATE=`date`
PWD=`pwd`
 
if [ -n "$1" ]; then
PWD=$1
fi
 
if [ -n "$2" ]; then
USER=$2
fi
 
DAT_A=`date '+开始执行时间 20%y/%m/%d %H:%M:%S'`
echo $DAT_A >> $PWD/Memcache.log
PID=`ps -fu $USER -o pid,ppid,args|grep 11211|grep -v grep|grep -v ps|wc -l`
echo $PID >> $PWD/Memcache.log
if [ "$PID" -eq "0" ]; then
echo "\n Memcache is Start up at $DATE by $USER ."
echo "\n Memcache is Start up at $DATE by $USER ." >> $PWD/Memcache.log
cd $PWD
echo "\n $PWD" >> $PWD/Memcache.log
#ip处需要自己根据实际环境更改
$PWD/bin/memcached -d -u root -m 300 -l 10.8.2.203 -p 11211
echo "\n Memcache start successful!" >> $PWD/Memcache.log

else 
echo "Memcache is already exist at $DATE ." >> $PWD/Memcache.log
echo "Memcache is application is start up already." >> $PWD/Memcache.log
fi
DAT_B=`date '+结束执行时间 20%y/%m/%d %H:%M:%S'`
echo $DAT_B >> $PWD/Memcache.log
echo '**********************************************************************************************************'
exit 0;
