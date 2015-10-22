
var textApp={
	clearEnter:function(str){
		str=str.replace(/\r/ig,"").replace(/\n/ig,"");
		return str;
	}
};
/** 过滤通配符、空格 **/
function formatSpecialChar(str) {
		return str.replace(/\\/g,"\\\\")
		          .replace(/%/g,"\\%")
		          .replace(/_/g,"\\_")
		          .replace(/％/g,"\\％")
		          .replace(/＿/g,"\\＿")
		          .replace(/^\s+|\s+$/g, '');
}
function formatSpecialCharreplce(str) {
	var pattern = new RegExp("[`~!@%\"#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）—|{}【】‘；：”“'。，、？]");
    var rs = "";
	for (var i = 0; i < str.length; i++) {
	    rs = rs + str.substr(i, 1).replace(pattern, '');
	}
	return rs;
}
/**
 * html特殊字符转换
 * @param s
 * @return
 */
function encodeHtml(s){  
	var REGX_HTML_ENCODE = /"|&|'|<|>|[\x00-\x20]|[\x7F-\xFF]|[\u0100-\u2700]/g;
      return (typeof s != "string") ? s :  
          s.replace(REGX_HTML_ENCODE,  
                    function($0){  
                        var c = $0.charCodeAt(0), r = ["&#"];  
	                        c = (c == 0x20) ? 0xA0 : c;  
                        r.push(c); r.push(";");  
	                       return r.join("");  
	                    });  
}
/**
 * 格式化短日期
 * @param d
 * @returns {String}
 */
function date2ShortString(myDate){
	var month = '';
	var day = '';
	if(myDate.getMonth()<9)
		month = "0"+(myDate.getMonth()+1);
	else
		month = (myDate.getMonth()+1);
	if(myDate.getDate()<=9)
		day = "0"+(myDate.getDate());
	else
		day = myDate.getDate();
	return myDate.getFullYear()+"-"+month+"-"+day;
}
function formatTime(dat){
	var hour = dat.getHours();//取得小时
 	var minutes = dat.getMinutes();//取得分钟
 	var second = dat.getSeconds();//取得秒
 	if(hour<10){
 		hour='0'+hour;
 	}
 	if(minutes<10){
 		minutes='0'+minutes;
 	}
 	if(second<10){
 		second='0'+second;
 	}
 	return hour+':'+minutes+':'+second;
}
/**
 * 格式化长日期，
 * @param d
 * @param flag : 0-设置时间为00:00:00   1-设置时间为23:59:59 其他-设置时间为当前时间
 * @returns {String}
 */
function date2LongString(myDate,flag){
	var month = '';
	var day = '';
	var tail='';
	if(flag==1){
		tail=' 23:59:59';
	}else if(flag==0){
		tail=' 00:00:00';
	}
	tail=' '+formatTime(myDate);
	if(myDate.getMonth()<9)
		month = "0"+(myDate.getMonth()+1);
	else
		month = (myDate.getMonth()+1);
	if(myDate.getDate()<=9)
		day = "0"+(myDate.getDate());
	else
		day = myDate.getDate();
	return myDate.getFullYear()+"-"+month+"-"+day+tail;
}
/* 
* 获得时间差,时间格式为 年-月-日 小时:分钟:秒 或者 年/月/日 小时：分钟：秒 
* 其中，年月日为全格式，例如 ： 2010-10-12 01:00:00 
* 返回精度为：秒，分，小时，天
*/
function GetDateDiff(startTime, endTime, diffType) {
    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式 
    startTime = startTime.replace(/\-/g, "/");
    endTime = endTime.replace(/\-/g, "/");

    //将计算间隔类性字符转换为小写
    diffType = diffType.toLowerCase();
    var sTime = new Date(startTime);      //开始时间
    var eTime = new Date(endTime);  //结束时间
    //作为除数的数字
    var divNum = 1;
    switch (diffType) {
        case "second":
            divNum = 1000;
            break;
        case "minute":
            divNum = 1000 * 60;
            break;
        case "hour":
            divNum = 1000 * 3600;
            break;
        case "day":
            divNum = 1000 * 3600 * 24;
            break;
        default:
            break;
    }
    return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(divNum));
}
function string2Date(myDate){
	 return new Date(myDate.replace(/\-/g, "/"));      //开始时间
}

/**
 * 获取上月的明天
 * @returns {Date}
 */
function getLastMonthTomorrow(){
	var myDate = new Date();
	return new Date(myDate.setDate(myDate.getDate()-30));
}
/**
 * 获取当月的第一天
 * @returns {Date}
 */
function getFirstDayInCurMonth(){
	var myDate = new Date();
	return new Date(myDate.getFullYear(),myDate.getMonth(),1);
}
/**
 * 获取的当前日期的回滚天的日期
 * @returns {Date}
 */
function getCurrentDayInRollThree(rollDay){
	var myDate = new Date();
	return new Date(myDate.setDate(myDate.getDate()-rollDay));
}
/**  
 *   Usage:  format_number(12345.678,2);  
 *   result: 12345.68  
 **/ 

function format_number(pnumber,decimals){  
    if (isNaN(pnumber)) { return 0};  
    if (pnumber=='') { return 0};  

    var snum = new String(pnumber);  
    var sec = snum.split('.');  
    var whole = parseFloat(sec[0]);  
    var result = '';  

    if(sec.length > 1){  
        var dec = new String(sec[1]);  
        dec = String(parseFloat(sec[1])/Math.pow(10,(dec.length - decimals)));  
        dec = String(whole + Math.round(parseFloat(dec))/Math.pow(10,decimals));  
        var dot = dec.indexOf('.');  
        if(dot == -1){  
            dec += '.';  
            dot = dec.indexOf('.');  
        }  

        while(dec.length <= dot + decimals) { dec += '0'; }  
        result = dec;  
    } else{  
        var dot;  
        var dec = new String(whole);  
        dec += '.';  
        dot = dec.indexOf('.');      
        while(dec.length <= dot + decimals) { dec += '0'; }  
        result = dec;  
    }    
    return result;  
} 

/**
 * 文本框/文本域 转换 oracle长度
 * @param str
 * @returns {Number}
 */
function getConverLength(str) {
    ///<summary>获得字符串实际长度，中文3，英文1</summary>
    ///<param name="str">要获得长度的字符串</param>
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 3;
    }
    return realLength;
};
