#!/bin/bash
# check app log script
# hongy@neusoft.com
# last modified 2010/11/10
#LANG=zh_CN.GBK;export LANG
STATE_OK=0
STATE_WARNING=1
STATE_CRITICAL=2
#############################################################################################
#命令说明: check_app_log.sh 文件路径 格式偏移量 检索行数 时间差阈值 "关键字1" "ERR:关键字2" ... ...
#关键字，如果一行需要检索多个不连续的关键字，各关键字用","分隔
#代表异常情况的关键字，以"ERR:"开头
##############################################################################################
verify_int_arg(){
        arg_tmp=`echo $1|sed 's/[0-9]//g'`
        if [ "$arg_tmp" != "" ];then
                echo "输入参数错误->"$1
                exit $STATE_CRITICAL   
        fi
}

transkey(){
        key_tmp=`echo $1|sed "s/,/ /g"`
        for list in $key_tmp
        do
                key_grep=$key_grep"grep \"$list\"|"
        done
        key_grep=`echo $key_grep|sed 's/|$//'`
        echo $key_grep
}

#check_log_time 文件路径 偏移量 时间差阈值
check_log_time(){
	if [ ! -z $1 -a -s $1 ];then
		real_time=`/usr/bin/date '+%m%d%H%M'`
		log_time=`/usr/bin/tail -1 $1`
		real_hour=${real_time:4:2}
		log_hour=${log_time:11:$2}
		real_minute=${real_time:6:2}
		log_minute=${log_time:14:$2}
		if [ $real_minute -ge $log_minute ];then
			diff_minute=`expr $real_minute - $log_minute`
		else
			diff_minute=`expr $real_minute + 60 - $log_minute`
			real_hour=`expr $real_hour - 1`
		fi
		if [ $real_hour -ge $log_hour ];then
			diff_hour=`expr $real_hour - $log_hour`
		else
			diff_hour=`expr $real_hour + 24 - $log_hour`
		fi
		diff=`expr $diff_hour \* 60 + $diff_minute`
		if [ -z "$diff" ];then
			echo "日志文件$1内容格式异常"
			exit $STATE_CRITICAL
		fi
		if [ $diff -gt $3 ];then
			echo "日志文件$1超过$diff分钟没有更新"
			exit $STATE_WARNING
		fi
	else
		echo "日志文件$1不存在或为空"
		exit $STATE_CRITICAL
	fi
}

#check_key_correct 文件路径 检索行数 关键字
check_key_correct(){
	if [ ! -z "$3" ];then
		key=`transkey $3`
		cmd="/usr/bin/tail -$2 $1|$key"
#		echo "check_correct_exec="$cmd
		ret=`eval $cmd`
		if [ -z "$ret" ];then
			echo "日志文件$1最近$2行未检索到关键字($3)"
			exit $STATE_WARNING
		fi
	fi
}

check_key_error(){
	if [ ! -z "$3" ];then
		key=`transkey $3`
		cmd="/usr/bin/tail -$2 $1|$key"
#		echo "check_error_exec="$cmd
		ret=`eval $cmd`
		if [ ! -z "$ret" ];then
			echo "日志文件$1最近$2行检索到关键字($3)"
			exit $STATE_CRITICAL
		fi
	fi

}
##############################################################################################
log_file=$1
verify_int_arg $2
offset=$2
verify_int_arg $3
check_lines=$3
verify_int_arg $4
threshold_time_diff=$4

declare -a argv_correct
declare -a argv_error
i=1
j=1
until [ $# -le 4 ]
do
	flag=`echo $5|grep '^ERR:'` 
	if [ -z "$flag" ];then
		argv_correct[$i]=$5
		((i+=1))
		shift
	else
		argv_error[$j]=${5#*ERR:}
		((j+=1))
		shift
	fi
done

check_log_time $log_file $offset $threshold_time_diff

i=1
while [ $i -le ${#argv_correct[@]} ]
do 
	check_key_correct $log_file $check_lines ${argv_correct[$i]}
	((i++))
done
i=1
while [ $i -le ${#argv_error[@]} ]
do
	check_key_error $log_file $check_lines ${argv_error[$i]}
        ((i++))
done

echo "日志文件$log_file正常"
exit $STATE_OK

#命令：./check_app_log.sh  /export/home1/sunxiwei/yt_xiaoche/core/logs/clw-c-info.log 2 500 1 "处理流程" "DBBuffer"