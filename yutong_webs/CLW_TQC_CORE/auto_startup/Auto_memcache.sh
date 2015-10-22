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
crontab -l > Memcache_cron
CRONID=`cat Memcache_cron | grep $PWD/memcache_startup.sh`

echo $PWD
echo "$CRONID" 

# if ran in
if [ -z "$CRONID" ]; then
        echo '0,5,10,15,20,25,30,35,40,45,50,55 * * * *' $PWD'/memcache_startup.sh' $PWD $USER >> $PWD/Memcache_cron
        crontab Memcache_cron
        echo "Memcache crontab is start at $DATE"

        echo "Memcache crontab is start at $DATE" >> $PWD/Memcache.log
fi
