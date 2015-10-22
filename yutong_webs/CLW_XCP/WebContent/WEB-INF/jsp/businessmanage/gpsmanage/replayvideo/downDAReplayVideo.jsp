<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>

<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/global1.css" />" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/jquery-cookie.js'/>"></script>

<style type="text/css" >

#progressbar{
font:18px/20px 'Microsoft Yahei','微软雅黑',Arial,Verdana;
}

#myvalue{
	font:25px/28px 'Microsoft Yahei','微软雅黑',Arial,Verdana;
	color: #0066B4;
}
</style>

</head>
<body>


 <div class="download">
	<div class="info">
   	  <p>文件大小：<label id="filesize"></label></p>
	  <p>保存位置：<label id="filepath"></label></p>
      <p>文件名称：<label id="filename"></label></p>
    </div>
    <div class="percent">
    	<span id="myvalue"></span>
    </div>
    <div class="clearFix"></div>
    <div class="bar">
    	<div class="progress"></div>
    </div>

    <div class="ok">
    	<input type="checkbox" id="autoclose" />
        	下载完成后关闭该对话框
    </div>
    <div class="msg">
    	<span class="text">下载成功</span>
    </div>
    <div class="clearFix"></div>
    <div class="btns">
    	<span class="changeBtn"><a href="#" onclick="cancelDownLoad()"></a></span>
    </div>
</div>
   	   <object id="ocx" width="0px" height="0px" classid='CLSID:C2F93B39-9404-47A9-B254-2F64753B06DC' 
          codebase="<s:url value='/codebase/dssPlay.CAB' />#version=2,1,0,5" >
       <param name="nWndNum"  value="1">					
       </object>  
<script type="text/javascript">
	//注册服务器IP、端口
	var m_szRegIP = "125.46.82.46";
	var m_iRegPort = "7660";
	//流媒体服务器IP、端口、使用方式(3种～2种，参数不详细，默认1就ok)
	var m_szRsmIP = "125.46.82.46";
	var m_iRsmPort = "7554";
	//系统视频登录的用户名和密码
	var m_user="root";
	var m_password="12345";
	
	//当前处理视频窗口终端类型 HI=本安 HS=航盛
	var VidiconType="";
	//摄像设备ID
	var m_vidiconId="";//vehicle0423  tygj002
	//连接服务器标识
	var lConDevice =-1;
	
	//视频播放状态
	// 0播放，1暂停，2结束，3快进，4快退
	var isplay = 2;
	
	//播放数率级别 -4~~4
	var playLevel=0;
	
	//window循环线程标识
	var intWinThread = 0;
	//刷新频率
	var flashvalue = 1000*1;
	var pid="";
	var filename="";
	var starttime="";
	var endtime="";
	var way="";
	var size="";
    var sourceType="";
    var recordType="";
    var planId="";
    var ssId="";
    var diskId="";
    var fileHandle="";
	var vehicle_ln="";
	var time="";
	var returnstr=0;
	var intervalValue=0;
	var netstatus=-1;
	
	//获取页面宽度
	function get_page_width() {
		var width = 0;
		if (jQuery.browser.msie) {
			width = document.compatMode == "CSS1Compat" ? document.documentElement.clientWidth
					: document.body.clientWidth;
		} else {
			width = self.innerWidth;
		}
		return width;
	}

	function getcookies(cookiename){
		var cookievalue=jQuery.cookie(cookiename);
		return cookievalue;
	}
	/**********************************
	Function: 		String.prototype.ToStringByZero(iCount)
	Description: 	判断并填补数字位数
	Input:			iCount：位数
	***********************************/ 
	function String.prototype.ToStringByZero(iCount){   // 取自lostinet的函数库
	   var  szContent=this;   
	   while(szContent.length < iCount) 
	   szContent="0"+szContent;   
	   return   szContent;   
	}

	function waynorecode(no){
		if(no == 4){
			return "司机";
		}else if(no == 1){
			return "整车";
		}else if(no == 3){
			return "车门";
		}else if(no == 2){
			return "路况";
		}
	}

	function RndNum(n){
		var rnd="";
		for(var i=0;i<n;i++)
		rnd+=Math.floor(Math.random()*10);
		return rnd;
	}

	function startDownloadVideo(){
		var tempPath=getcookies('savevideopath');
		if(tempPath==""||tempPath==null){
			tempPath="C:\\安芯视频\\视频回放\\";
		}
		$("#filepath").text(tempPath);
		// 日期当文件名，注：文件名不支持中间出现":"符号，故用"_"、"-"符号分割的时间
		var DateTime = new  Date();
		var szDate   = DateTime.getYear()+((DateTime.getMonth()+1)+"").ToStringByZero(2)+(DateTime.getDate()+"").ToStringByZero(2);
		tempPath=tempPath+vehicle_ln+"_0"+way+waynorecode(way)+"_"+szDate+""+starttime.replace(/\:/g,"")+"_"+szDate+""+endtime.replace(/\:/g,"")+"_"+RndNum(6)+".mp4";
		$("#filename").text(vehicle_ln+"_0"+way+waynorecode(way)+"_"+szDate+""+starttime.replace(/\:/g,"")+"_"+szDate+""+endtime.replace(/\:/g,"")+"_"+RndNum(6)+".mp4");
		
			var ocx = document.getElementById("ocx");
			if(ocx == null){
				$(".msg").show();
				$(".text").html("视频控件加载失败!");
				window.clearInterval(intervalValue);
				return ;
			}

			//返回下载数据流标识
			ocx.DownloadRecord(filename,time+" "+starttime,time+" "+endtime,sourceType,recordType,size,planId,ssId,diskId,fileHandle,tempPath);

			/*if(returnstr==-2){
				tishiShow("磁盘空间不够！");
				window.clearInterval(intervalValue);
				window.open('','_top');
				window.top.close();
				return false;
			}else if(returnstr==-5){
				tishiShow("前端当前下载繁忙，请稍后再试！");
				window.clearInterval(intervalValue);
				window.open('','_top');
				window.top.close();
				return false;
			}*/

			//获得Interval状态码
			intervalValue=window.setInterval(function(){
				var per=0;
				//var per=ocx.OnDownloadProgress();
				if(per<0){
					per=0;
				}
				$(".progress").css("width",per+"%");
				if(per==100){
					window.clearInterval(intervalValue);
					if($("#autoclose").attr("checked")){
						window.open('','_top');
						window.top.close();
					}
				}
			}, 500);

	}

	function cancelDownLoad(){
			ocx.StopDownloadRecord(filename,starttime,endtime,sourceType,recordType,size,planId,ssId,diskId,fileHandle);
			window.clearInterval(intervalValue);
			window.open('','_top');
			window.top.close();
	}

	$(document).ready( function() {
		var url=window.location.search.substr(1);
		var geturlvalue = url.split("&");
		if (geturlvalue) {
			for (var i=0;i<geturlvalue.length;i++) {
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="VidiconType"){
					VidiconType=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="sourceType"){
					sourceType=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="recordType"){
					recordType=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="planId"){
					planId=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="ssId"){
					ssId=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="diskId"){
					diskId=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="fileHandle"){
					fileHandle=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}

				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="time"){
					time=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="vehicle_vin"){
					m_vidiconId=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="pid"){
					pid=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="way"){
					way=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="filename"){
					filename=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="starttime"){
					starttime=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="endtime"){
					endtime=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}

				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="size"){
					size=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="vehicle_ln"){
					vehicle_ln=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
				}
				
			}
		}
		$(".progress").css("width","0%");
		$(".msg").hide();
		if((size/1024).toFixed(2)>1){
			$("#filesize").text((size/1024/1024).toFixed(2)+" MB");
		}else{
			$("#filesize").text(Math.floor(size/1024)+" KB");
		}
		startDownloadVideo();
	});

</script>
</body>
</html>
