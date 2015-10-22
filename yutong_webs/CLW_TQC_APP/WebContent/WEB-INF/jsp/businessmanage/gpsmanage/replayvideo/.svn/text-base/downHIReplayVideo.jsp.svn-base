<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title>正在下载</title>
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
	  <p>文件名称：<label id="filename"></label></p>
   	  <p>文件大小：<label id="filesize"></label></p>
	  <p>保存路径：<label id="filepath"></label></p>
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
    	<span class="text">连接中请稍等...</span>
    </div>
    <div class="clearFix"></div>
    <div class="btns">
    	<span class="changeBtn"><a href="#" onclick="cancelDownLoad()"></a><span class="aOpenfile"><a onclick="openfolder()">打开文件夹</a></span></span>
    </div>
</div>
<object
	classid="clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778"
	codebase="<s:url value='/codebase/ppvsguard.cab' />#version=2,2,0,21460"
	standby="Waiting..." id="PPVSGUARD" width="0px" height="0px"
	name="ocx2"> 
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
			return "驾驶员";
		}else if(no == 1){
			return "整车";
		}else if(no == 3){
			return "车门";
		}else if(no == 2){
			return "路况";
		}
	}

	function openfolder(){
		var ppocx = document.getElementById("PPVSGUARD");
		ppocx.OpenFolder(getcookies('savevideopath'));
	}

	function startDownloadVideo(){
		var tempPath=getcookies('savevideopath');
		var filesetname=getcookies('palybackvideoname');
		$("#filepath").attr("title",tempPath);
		$("#filepath").text(setShortPath(tempPath));
		// 日期当文件名，注：文件名不支持中间出现":"符号，故用"_"、"-"符号分割的时间
		//var DateTime = new  Date();
		//var szDate   = DateTime.getYear()+((DateTime.getMonth()+1)+"").ToStringByZero(2)+(DateTime.getDate()+"").ToStringByZero(2);
		//var filenametrue=vehicle_ln+"_0"+way+waynorecode(way)+"_"+szDate+""+starttime.replace(/\:/g,"")+"_"+szDate+""+endtime.replace(/\:/g,"")+"_"+RndNum(6)+".mp4";

		$("#filename").attr("title",filesetname);
		$("#filename").text(setShortPath(filesetname));
		//返回下载数据流标识
		returnstr=HIOCX.StartDownloadFile(way,filename,size,tempPath+filesetname);
		if(returnstr==-2){
			$(".msg").show();
			$(".text").html("磁盘空间不够!");
			window.clearInterval(intervalValue);
			return false;
		}else if(returnstr==-5){
			$(".msg").show();
			$(".text").html("3G信号不稳定或设备已下线，请稍后重试!");
			window.clearInterval(intervalValue);
			return false;
		}else if(returnstr<0){
			$(".msg").show();
			$(".text").html("下载失败,请稍后重试!");
			window.clearInterval(intervalValue);
			return false;
		}
		
		var szInfo="";
		//获得Interval状态码
		intervalValue=window.setInterval(function(){
			if(HIOCX == null){
	 			$(".msg").show();
	 			$(".text").html("视频控件加载失败!");
	 	 		window.clearInterval(intervalValue);
				return ;
	 	 	}
			szInfo = HIOCX.GetDeviceInfoOnServer(m_vidiconId, m_szRegIP, m_iRegPort, m_user, m_password);
			var iDeviceOnLine = 1;//该变量0：表示不在线；1：表示在线			
			if( typeof szInfo!="undefined" && szInfo!=null && szInfo != ""){
				var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
				xmlDoc.async="false";
				xmlDoc.loadXML(szInfo);
				if(xmlDoc.documentElement.childNodes[2].hasChildNodes()){
					iDeviceOnLine = xmlDoc.documentElement.childNodes[2].childNodes[0].nodeValue;
				}
			}else{
				$(".msg").show();
				$(".text").html("3G信号不稳定或设备已下线,请稍后重试!");
				window.clearInterval(intervalValue);
				return false;
			}
			if(iDeviceOnLine == 0){//不在线
				var complet=HIOCX.StopDownloadFile(returnstr);
				$(".msg").show();
				$(".text").html("连接信号丢失!");
				window.clearInterval(intervalValue);
				return;
			}
			var per=HIOCX.GetDownloadPos(returnstr);
			//jQuery("#pic_"+pid).next().next().next().next().val(per);
			if(per<0){
				per=0;
			}
			$(".progress").css("width",per+"%");
			$('#myvalue').html(per+"%");
			if(per==100){
				$(".msg").show();
				$(".text").html("下载成功!");
				document.title="下载完成";
				$(".changeBtn").removeClass().addClass('closeBtn');
				
				if($("#autoclose").attr("checked")){
					window.setTimeout(function(){
						window.open('','_top');
						window.top.close();
						window.clearInterval(intervalValue);
					},2000);
				}else{
					window.setTimeout(function(){
						window.clearInterval(intervalValue);
					},2000);
				}
			}
		}, 5000);
	}

	function cancelDownLoad(){
		//var HIOCX = document.getElementById("PPVSGUARD");
		if(HIOCX == null){
 			$(".msg").show();
 			$(".text").html("视频控件加载失败!");
 	 		window.clearInterval(intervalValue);
			return ;
 	 	}
		var complet=HIOCX.StopDownloadFile(returnstr);
		if(complet==1){
			window.clearInterval(intervalValue);
			window.open('','_top');
			window.top.close();
		}else{
			window.clearInterval(intervalValue);
			window.open('','_top');
			window.top.close();
		}
		
	}
	var HIOCX = null;
	$(document).ready( function() {
		var url=window.location.search.substr(1);
		var geturlvalue = url.split("&");
		if (geturlvalue) {
			for (var i=0;i<geturlvalue.length;i++) {
				if(geturlvalue[i].substring(0,geturlvalue[i].indexOf("="))=="VidiconType"){
					VidiconType=geturlvalue[i].substring(geturlvalue[i].indexOf("=")+1);
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
		if((size/1024).toFixed(2)>1024){
			$("#filesize").text((size/1024/1024).toFixed(2)+" MB ( "+formatNumber(size)+" 字节)");
			$("#filesize").attr("title",(size/1024/1024).toFixed(2)+" MB ( "+formatNumber(size)+" 字节)");
		}else{
			$("#filesize").text(Math.floor(size/1024)+" KB ( "+formatNumber(size)+" 字节)");
			$("#filesize").attr("title",Math.floor(size/1024)+" KB ( "+formatNumber(size)+" 字节)");
		}
		
		HIOCX = document.getElementById("PPVSGUARD");
		if(HIOCX == null){
 			$(".msg").show();
 			$(".text").html("视频控件加载失败!");
 	 		window.clearInterval(intervalValue);
			return ;
 	 	}
 	 	
		//针对宇通网络环境需调用该接口
 		HIOCX.SetDeviceNetLine( 1 );
 		
		if(0 > lConDevice){
			lConDevice = HIOCX.ConnectDeviceByACS(m_vidiconId, m_szRegIP, m_iRegPort, m_user, m_password);
			if(0 > lConDevice){
				$(".msg").show();
	 			$(".text").html("连接设备失败!");
	 			window.clearInterval(intervalValue);
	 			return;
			}
			HIOCX.SetActiveXShowMode(5, 0);
			//var iRet =  HIOCX.StartPlaybackByFileEx(way, filename,size,starttime,endtime);
			startDownloadVideo();
		}
	});
	
	function formatNumber(strNum){    
		if (strNum.length <= 3) {
			return strNum;
		}
		if (!/^(\+|-)?(\d+)(\.\d+)?$/.test(strNum)) {
			return strNum;
		}
		var a = RegExp.$1, b = RegExp.$2, c = RegExp.$3;
		var re = new RegExp();
		re.compile("(\\d)(\\d{3})(,|$)");
		while (re.test(b)) {
			b = b.replace(re, "$1,$2$3");
		}
		return a + "" + b + "" + c;
	}

	//解析xml字符串
	function loadXML(xmlString){
	    var xmlDoc=null;
	    //判断浏览器的类型
	    //支持IE浏览器 
	    if(!window.DOMParser && window.ActiveXObject){   //window.DOMParser 判断是否是非ie浏览器
	        var xmlDomVersions = ['MSXML.2.DOMDocument.6.0','MSXML.2.DOMDocument.3.0','Microsoft.XMLDOM'];
	        for(var i=0;i<xmlDomVersions.length;i++){
	            try{
	                xmlDoc = new ActiveXObject(xmlDomVersions[i]);
	                xmlDoc.async = false;
	                xmlDoc.loadXML(xmlString); //loadXML方法载入xml字符串
	                break;
	            }catch(e){
	            	
	            }
	        }
	    }
	    //支持Mozilla浏览器
	    else if(window.DOMParser && document.implementation && document.implementation.createDocument){
	        try{
	            /* DOMParser 对象解析 XML 文本并返回一个 XML Document 对象。
	             * 要使用 DOMParser，使用不带参数的构造函数来实例化它，然后调用其 parseFromString() 方法
	             * parseFromString(text, contentType) 参数text:要解析的 XML 标记 参数contentType文本的内容类型
	             * 可能是 "text/xml" 、"application/xml" 或 "application/xhtml+xml" 中的一个。注意，不支持 "text/html"。
	             */
	            domParser = new  DOMParser();
	            xmlDoc = domParser.parseFromString(xmlString, 'text/xml');
	        }catch(e){
	        	
	        }
	    }
	    else{
	        return null;
	    }

	    return xmlDoc;
	}

	function setShortPath(path){
		if(path.length>55){
			path=path.substring(0, 55)+"...";
		}
		return path;
	}
</script>
</body>
</html>
