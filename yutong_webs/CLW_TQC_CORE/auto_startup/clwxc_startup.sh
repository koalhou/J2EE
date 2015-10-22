#!/usr/bin/sh
# AUTHOR: chenqiong
# DATE: 2012
LANG=zh_CN.GBK;export LANG
env |grep LANG
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
echo $DAT_A >> $PWD/CLW_XC.log
PID=`ps -fu $USER -o pid,ppid,args|grep 'DCLW_XCC'|grep -v grep|grep -v ps|wc -l`
echo $PID >> $PWD/CLW_XC.log
if [ "$PID" -eq "0" ]; then
echo "\n CLW_XC is Start up at $DATE by $USER ."
echo "\n CLW_XC is Start up at $DATE by $USER ." >> $PWD/CLW_XC.log
cd $PWD
echo "\n $PWD" >> $PWD/CLW_XC.log
$PWD/apache-tomcat-6.0.33/bin/startup.sh
echo "\n start successful!" >> $PWD/CLW_XC.log

else 
echo "CLW_XC is already exist at $DATE ." >> $PWD/CLW_XC.log
echo "CLW_XC is application is start up already." >> $PWD/CLW_XC.log
fi
DAT_B=`date '+结束执行时间 20%y/%m/%d %H:%M:%S'`
echo $DAT_B >> $PWD/CLW_XC.log
echo '**********************************************************************************************************'
exit 0;
