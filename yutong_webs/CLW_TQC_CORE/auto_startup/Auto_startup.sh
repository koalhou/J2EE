#!/usr/bin/sh

DATE=`date`
PWD=`pwd`
USER=`who am i | awk '{print $1}'`

if [ -n "$1" ];then
        PWD=$1
fi

if [ -n "$2" ];then
        USER=$2
fi

cd $PWD

#startup crontab
crontab -l > CLW_XC_cron
CRONID=`cat CLW_XC_cron | grep $PWD/clwxc_startup.sh`

echo $PWD
echo "$CRONID" 

# if ran in
if [ -z "$CRONID" ]; then
        echo '0,5,10,15,20,25,30,35,40,45,50,55 * * * *' $PWD'/clwxc_startup.sh' $PWD $USER >> $PWD/CLW_XC_cron
        crontab CLW_XC_cron
        echo "CLW_XC crontab is start at $DATE"

        echo "CLW_XC crontab is start at $DATE" >> $PWD/CLW_XC.log
fi
