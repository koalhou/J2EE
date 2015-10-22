<%@page language="java" contentType="text/html;charset=utf-8"%>
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
var VidiconType="HI";
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

function openWinThread(){
	intWinThread = window.setTimeout("getControlValue()",flashvalue);
}

function closeWinThread(){
	window.clearTimeout(intWinThread);
}

function showtextshow(){
	loadvideofile();
	m_vidiconId=document.getElementById("VIDEO_ID").value;
	if(m_vidiconId==""){
		tishiShow("该车未安装3G视频设备！");
	}
}
//关闭视频监控
function closeMapDiv(){
	StopPlayAllWindows();
}

//关闭视频
function closeVideoDiv(div){
	//关闭所有视频
	StopPlayAllWindows();
}

function showFactoryDiv(){
	VidiconType = document.getElementById('VIDEO_FACTORY').value;
	m_vidiconId=document.getElementById("VIDEO_ID").value;
	var ipport = document.getElementById('VIDEO_SERVER_IP').value;
	var ss = ipport.split(":");
	var user = document.getElementById('VIDEO_USER').value;
	var passwd = document.getElementById('VIDEO_PASSWORD').value;
	if(VidiconType == "HI"){
		m_szRegIP = ss[0];
		m_iRegPort = ss[1];
		m_user = user;
		m_password = passwd;
	}else if(VidiconType == "DA"){
		m_szRegIP = ss[0];
		m_iRegPort = ss[1];
		m_user = user;
		m_password = passwd;
	}
}

function pickedstarttime(){
    var line_end_time=document.getElementById('line_end_time').value.replace(/-/g,"/");
	var enddate=new Date(line_end_time);	
	var today =new Date();
	if(enddate.pattern("yyyy-MM-dd") != today.pattern("yyyy-MM-dd")){
		enddate.setTime(enddate.getTime() + 1000*60*60*24);
		$dp.$('line_end_time').value=enddate.pattern("yyyy-MM-dd") ;
	}
}

function pickedendtime(){
	var line_end_time=document.getElementById('line_end_time').value.replace(/-/g,"/");
	var enddate=new Date(line_end_time);	
	enddate.setTime(enddate.getTime() - 1000*60*60*24);
	$dp.$('line_end_time').value=enddate.pattern("yyyy-MM-dd") ;
}

Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "\u65e5",         
    "1" : "\u4e00",         
    "2" : "\u4e8c",         
    "3" : "\u4e09",         
    "4" : "\u56db",         
    "5" : "\u4e94",         
    "6" : "\u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
};

//大华视频文件列表
var DAvideoresultstrs = "";
function selectgj(){
	closedialog();
	var routeway2 = document.all.routeway2;
	var wayNo = "";
	if(routeway2.length > 1){
		for(var j =0; j < routeway2.length; j++){
			if(routeway2[j].checked){
				wayNo += routeway2[j].value+",";
			}
		}
	}else{
		if(routeway2.checked){
			wayNo += routeway2.value+",";
		}
	}
	var endtime = document.getElementById("line_end_time").value;
	if(wayNo ==null || wayNo==""){
		tishiShow("请选择视频通道！");
		return ;
	}
	wayNo = wayNo.substring(0, wayNo.length - 1);
	var ways = wayNo.split(",");
	
	//test date
	//if(VidiconType == "HI"){
	//	reloadvideofile("aa,2012-12-01 10:10:00,2012-12-01 10:10:00,10,dd,1~ff,2012-12-01 10:10:00,2012-12-01 10:10:00,10,20,jj,1","HI");
	//}
	if(VidiconType == "HI"){
		var PlayOCX = document.getElementById("PPVSHI1");
		if(0 > lConDevice){
			//针对宇通网络环境需调用该接口
	 		if(m_vidiconId!=""){
		 		lConDevice = PlayOCX.ConnectDeviceByACS(m_vidiconId, m_szRegIP, m_iRegPort, m_user, m_password);
		 		if(0 > lConDevice){
		 			tishiShow("连接设备失败！");
		 			return;
		 		}
		 		PlayOCX.SetActiveXShowMode(5, 0);
	 		}
		}
		var str = "";
		for(var i = 0; i < ways.length; i++){
			var xmlresult = PlayOCX.PlaybackSearchFile(ways[i], 0, endtime+" 00:00:00",endtime+" 23:59:59");
			if(xmlresult == "" || xmlresult == "undefined"){
				tishiShow("获取视频文件失败！");
				continue;
			}else if(xmlresult == null ){
				tishiShow("通道无视频文件！");
			}else{
				var xmldoc = loadXML(xmlresult);
				var elements = xmldoc.getElementsByTagName("File");
				for (var j = 0; j < elements.length; j++) {
					var FileName = elements[j].getElementsByTagName("FileName")[0].firstChild.nodeValue;
	                var StartTime = elements[j].getElementsByTagName("StartTime")[0].firstChild.nodeValue;
	                var StopTime = elements[j].getElementsByTagName("StopTime")[0].firstChild.nodeValue;
	                var FileSize = elements[j].getElementsByTagName("FileSize")[0].firstChild.nodeValue;
	                var FileType = elements[j].getElementsByTagName("FileType")[0].firstChild.nodeValue;
					str = str+FileName+","+StartTime+","+StopTime+","+FileSize+","+FileType+","+ways[i]+"~";	                
				}	
			}
		}
		if(str != ""){
			reloadvideofile(str,"HI");	
		}else{
			tishiShow("通道无视频文件！");
			return;
		}
	}
	if(VidiconType == "DA"){
		var ocx = document.getElementById("ocx");
		DAvideoresultstrs = "";
		for(var k = 0; k < ways.length; k++){
			var vid = m_vidiconId;
			var xmlresultDA = ocx.QueryRecords(vid,(ways[k]-1),endtime+" 00:00:00",endtime+" 23:59:59",0);
		}	
	}
}

/**
 * 海康视频播放
 */
function selectPlayFile(way,FileName,FileSize,starttime,endtime){
	var time = document.getElementById("line_end_time").value;
	starttime  = time +" "+starttime;
	endtime = time +" " + endtime;
	if(0 > lConDevice){
		lConDevice = PlayOCX.ConnectDeviceByACS(m_vidiconId, m_szRegIP, m_iRegPort, m_user, m_password);
		if(0 > lConDevice){
			tishiShow("无法连接视频服务,请稍后再试！");
			return;
		}else{
			var PlayOCX = document.getElementById("PPVSHI1");
			PlayOCX.SetActiveXShowMode(5, 0);
			var iRet =  PlayOCX.StartPlaybackByFileEx(way, FileName,FileSize,starttime,endtime);
		}
	}else{
		var PlayOCX = document.getElementById("PPVSHI1");
		PlayOCX.StopPlaybackByFile();
		PlayOCX.SetActiveXShowMode(5, 0);
		var iRet =  PlayOCX.StartPlaybackByFileEx(way, FileName,FileSize,starttime,endtime);
	}

	//获得Interval状态码
	/*var intervalValue=window.setInterval(function(){
		var xmlresult = PlayOCX.GetDeviceStatus();
		if(xmlresult == "" || typeof(xmlresult) == "undefined" ){
			closedialog();
			tishiShow("3G信号不稳定或设备已下线,请稍后重试!");
			PlayOCX.StopPlaybackByFile();
			//jQuery(".trDbSelected").removeClass('trDbSelected');
			window.clearInterval(intervalValue);
			return false;
		}else if(xmlresult == null ){
			closedialog();
			tishiShow("通道无视频文件!");
			PlayOCX.StopPlaybackByFile();
			//jQuery(".trDbSelected").removeClass('trDbSelected');
			window.clearInterval(intervalValue);
			return false;
		}else{
			var xmldoc = loadXML(xmlresult);
			var elements = xmldoc.getElementsByTagName("CHStatus");
			for (var i = 0; i < elements[0].childNodes.length; i++) {
				var str = elements[0].getElementsByTagName("CH")[i].firstChild.nodeValue;
				if(str.split("-")[0]==way){
					netstatus=str.split("-")[2];
				}
			}
		}
		if(netstatus>0){
			closedialog();
			tishiShow("连接信号丢失!");
			PlayOCX.StopPlaybackByFile();
			//jQuery(".trDbSelected").removeClass('trDbSelected');
			window.clearInterval(intervalValue);
			return false;
		}
	}, 2000);*/
}

//大华生成数据表格 播放
//strName:	文件名
//strBeginTime:	开始时间
//strEndTime:	结束时间
//lSource:	录像源 0：设备 1：中心
//lRecordType: 	录像类型
//nLength: 	录像长度
//nPlanID: 	计划ID
//nSsID:		SS服务器ID
//strDiskId:	硬盘ID
//nFileHandle:	文件句柄
function selectPlayFileDA(strName, strBeginTime, strEndTime, lSource, lRecordType, nLength, nPlanID, nSsID, strDiskId, nFileHandle){
	var ocx = document.getElementById("ocx");
	var time = document.getElementById("line_end_time").value;
	strBeginTime  = time +" "+strBeginTime;
	strEndTime = time +" " + strEndTime;
	ocx.PlaybackByFile(strName, strBeginTime, strEndTime, lSource, lRecordType, nLength, nPlanID, nSsID, strDiskId, nFileHandle);
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

/*************************************************
Function:		StartPlayAllBAWindows
Description:	本安窗口进行播放(1-4通道)
*************************************************/
function StartPlayAllBAWindows(){
	if(VidiconType=="HI"){
		PlayOCX=document.getElementById("PPVSHI1");
		PlayOCX.SetPlayWndText("请先查询视频文件...");
 		m_vidiconId=document.getElementById("VIDEO_ID").value;
 		if(PlayOCX == null){
 	 		alert("视频控件加载失败!");
			return ;
 	 	}
 		//针对宇通网络环境需调用该接口
 		PlayOCX.SetDeviceNetLine( 1 );
 		if(m_vidiconId!=""){
	 		lConDevice = PlayOCX.ConnectDeviceByACS(m_vidiconId, m_szRegIP, m_iRegPort, m_user, m_password);
	 		if(0 > lConDevice){
	 			tishiShow("连接设备失败！");
	 			return;
	 		}
	 		PlayOCX.SetActiveXShowMode(5, 0);
 		}
	}
	else if(VidiconType=="DA"){
		window.setTimeout("RunPlayDA()",1000);
	}
}

/*************************************************
 Function:		RunPlayDA
 Description:	当前指定窗口开始播放(大华)
 *************************************************/
 function RunPlayDA(){
	 var ocx = document.getElementById("ocx");
	 if(ocx == null){
		 tishiShow("视频控件加载失败！");
			return ;
	 }
	 m_vidiconId = document.getElementById("VIDEO_ID").value;
	 var mode=0;
	 if(m_vidiconId!=""){
		var logret = ocx.LoginServer(m_szRegIP,m_iRegPort,m_user,m_password );	
	 }
}

//播放按钮控制
function playbutton(){
	if(isplay==2){
		tishiShow("请选择视频文件！");
		return;
	}
	var PlayOCX = document.getElementById("PPVSHI1");
	if(isplay==0){
		var rel = PlayOCX.PlaybackControl(2,1);//2 暂停
		if(rel == 0){
			isplay = 1;
			//更换暂停图片
			document.getElementById("playButton").src = "../images/playimages/break.gif";
			closeWinThread();
		}
	}
	if(isplay == 1){
		var rel = PlayOCX.PlaybackControl(3,1);//3 继续播放
		if(rel == 0){
			isplay = 0;
			//更换播放图片
			document.getElementById("playButton").src = "../images/playimages/play.gif";
			openWinThread();
		}
	}
}

/*
 * modify by yg
 */
function loadvideofile() {	 
	jQuery('#videoFileList').flexigrid({
		  //url:'<s:url value="/SendInfo/tabNodeSelectDwrList.shtml" />',
		  dataType: 'json',    //json格式
		  height: 100,
		  width:500,
		  colModel : [
		        {display: '下载',name:'download',width:30,sortable:false, align: 'center',process: campic},
				{display: '名称', name : 'FileName', width : 55, sortable : false, align: 'center',toggle:false,hide:true},
		        {display: '开始时间', name : 'starttime', width : 110, sortable : false, align: 'center'},
		        {display: '结束时间', name : 'endtime', width : 110, sortable : false, align: 'center'},
		        {display: '时长', name : 'howlong', width : 85, sortable : false, align: 'center'},
		        {display: '通道', name : 'way', width : 85, sortable : false, align: 'center',process: reWritephoto},
		        {display: '大小', name : 'FileSize', width : 55, sortable : false, align: 'center',toggle:false,hide:true},
		        {display: 'sourceType', name : 'sourceType', width : 55, sortable : false, align: 'center',toggle:false,hide:true},
		        {display: 'recordType', name : 'recordType', width : 55, sortable : false, align: 'center',toggle:false,hide:true},
		        {display: 'planId', name : 'planId', width : 55, sortable : false, align: 'center',toggle:false,hide:true},
		        {display: 'ssId', name : 'ssId', width : 55, sortable : false, align: 'center',toggle:false,hide:true},
		        {display: 'diskId', name : 'diskId', width : 55, sortable : false, align: 'center',toggle:false,hide:true},
		        {display: 'fileHandle', name : 'fileHandle', width : 55, sortable : false, align: 'center',toggle:false,hide:true},
		        {display: 'flag', name : 'flag', width : 55, sortable : false, align: 'center',toggle:false,hide:true},
		        {display: 'way2', name : 'way2', width : 55, sortable : false, align: 'center',toggle:false,hide:true}
		        ],
		       sortname: 'starttime',
		       sortorder: 'desc',  //升序OR降序
		       sortable: false,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :false,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: false,    // 是否可以动态设置每页显示的结果数
		       showTableToggleBtn: true,
		       setConflicts:true,  //解决行点击事件冲突
		       setDBClick:true,    //设置双击
		       onRowDblclick:rowstandbyclick
	});	
	
	if(jQuery(window.parent.iframeshowArea).width() > 500){
		jQuery('.bDiv').height("164px");
		jQuery('.flexigrid').width("655px");
	}else{
		jQuery('.bDiv').height("100px");
		jQuery('.flexigrid').width("500px");
	}
}

/*
 * 在表格中创建下载图标
 */
 /*
function campic(tdDiv,pid){
	var str='<span class="downBtn">'+
			'<span id=\'pic_'+pid+'\' class="downpress" onclick=\'javascript:downfilm("'+pid+'")\'>'+
				'<span style="display:none;"></span>'+
				'<span style="display:none;"></span>'+
				'<span style="display:none;"></span>'+
				'<span style="display:none;"></span>'+
			'</span>'+
			'<span class="progress">&nbsp;0%&nbsp;</span>'+    //当前下载进度展示
			'<span style="display:none;"></span>'+             //当前下载进度
			'<span style="display:none;"></span>'+             //返回下载数据流标识
			'<span style="display:none;"></span>'+ 			   //setInterval标识
			'</span>'
	tdDiv.innerHTML = str;
}*/

function downloadvideo(){
	var str='';
	if(VidiconType == "HI"){
		str='<span class="downBtn">'+
		'<a id=\'pic_'+pid+'\' class=\'downpress\' hidefocus=\'true\' href =\'../gps/downLoadHIReplayVideo.shtml?vehicle_vin='+m_vidiconId+'&pid='+pid+
														'&filename='+get_cell_text(pid,1)+
														'&starttime='+get_cell_text(pid,2)+
														'&endtime='+get_cell_text(pid,3)+
														'&way='+get_cell_text(pid,5)+
														'&size='+get_cell_text(pid,6)+
														'&vehicle_ln='+jQuery('#VEHICLE_LN').val()+
														'&VidiconType=HI'+
														'\' target=\'_blank\'>'+
			'<span style="display:none;"></span>'+
			'<span style="display:none;"></span>'+
			'<span style="display:none;"></span>'+
			'<span style="display:none;"></span>'+
		'</a>'+
		'<span class="progress">&nbsp;0%&nbsp;</span>'+    //当前下载进度展示
		'<span style="display:none;"></span>'+             //当前下载进度
		'<span style="display:none;"></span>'+             //返回下载数据流标识
		'<span style="display:none;"></span>'+ 			   //setInterval标识
		'</span>'
	}else if(VidiconType=="DA"){
		var time = document.getElementById("line_end_time").value;		
		//alert(get_cell_text(pid,7)+','+get_cell_text(pid,8)+','+get_cell_text(pid,9)+','+get_cell_text(pid,10)+','+get_cell_text(pid,11)+','+get_cell_text(pid,12));
		str='<span class="downBtn">'+
				'<a id=\'pic_'+pid+'\' class=\'downpress\' href =\'#\'>'+
			/*'<a id=\'pic_'+pid+'\' class=\'downpress\' href =\'../gps/downLoadDAReplayVideo.shtml?vehicle_vin='+m_vidiconId+'&pid='+pid+
															'&filename='+get_cell_text(pid,1)+
															'&starttime='+get_cell_text(pid,2)+
															'&endtime='+get_cell_text(pid,3)+
															'&way='+get_cell_text(pid,5)+
															'&size='+get_cell_text(pid,6)+
															'&sourceType='+get_cell_text(pid,7)+
															'&recordType='+get_cell_text(pid,8)+
															'&planId='+get_cell_text(pid,9)+
															'&ssId='+get_cell_text(pid,10)+
															'&diskId='+get_cell_text(pid,11)+
															'&fileHandle='+get_cell_text(pid,12)+
															'&vehicle_ln='+jQuery('#VEHICLE_LN').val()+
															'&VidiconType=DA'+
															'&time='+time+
															'\' target=\'_blank\'>'+*/
			'</a>'+
		'</span>'
	}			
	tdDiv.innerHTML = str;
}

function showdownloaddialog(pid,filename,starttime,endtime,way,size,vehicle_ln,VidiconType){
	var tempPath=getcookies('savevideopath');
	if(tempPath==""||tempPath==null){
		tempPath="C:\\安芯视频\\视频回放\\";
	}
	
	var DateTime = new  Date();
	var szDate   = DateTime.getYear()+((DateTime.getMonth()+1)+"").ToStringByZero(2)+(DateTime.getDate()+"").ToStringByZero(2);
	var filenamego=vehicle_ln+"_0"+way+waynorecode(way)+"_"+szDate+""+starttime+"_"+szDate+""+endtime+"_"+RndNum(6)+".mp4";
	art.dialog({
		id:'downloadfiledialog',
		title:'文件下载',
		lock:true,
		width:350,
		height:100,
		fixed:true,
	    content:'<div class="dialogCon">'+
				    '<p>'+
				        '<label>文件名称：</label><input type="text" id="downloadfilename"  maxlength="60" class="wtext">'+
				    '</p>'+
				    '<p>'+
				        '<label>保存路径：</label><input type="text" id="downloadcookpath" class="wtext small" readonly="readonly"><a href="#" class="browseBtn" onclick="veiw_event()" ><span>浏览</span></a>'+
				    '</p>'+
				    '<p>'+
				        '<label>文件大小：</label><span class="wsize">MP4文档 |</span> <span id="downloadfilesize" class="wsize"></span>'+
				    '</p>'+
				'</div>',
				yesText:'立即下载',
				yesFn: function(){
					if(jQuery("#downloadfilename").val().length<5){
						return false;
					}else{
						savecookies('palybackvideoname',jQuery("#downloadfilename").val());
						savecookies('savevideopath',addpath(jQuery("#downloadcookpath").val()));
						window.open ('../gps/downLoadHIReplayVideo.shtml?vehicle_vin='+m_vidiconId+'&pid='+pid+
				    		'&filename='+filename+
				    		'&starttime='+starttime+
				    		'&endtime='+endtime+
				    		'&way='+way+
				    		'&size='+size+
				    		'&vehicle_ln='+vehicle_ln+
				    		'&VidiconType='+VidiconType,'_blank'); 
					}
			    },
			    noFn: true , 
	});
	
	jQuery("#downloadcookpath").val(tempPath);
	jQuery("#downloadfilename").val(filenamego);
	
	if((size/1024).toFixed(2)>1024){
		jQuery("#downloadfilesize").text((size/1024/1024).toFixed(2)+" MB");
	}else{
		jQuery("#downloadfilesize").text(Math.floor(size/1024)+" KB");
	}

}

function veiw_event(){
	 var ppocx = document.getElementById("PPVSHI1"); 
	 var path = ppocx.ChangeSaveFilePath(jQuery('#downloadcookpath').val());
	 if(path!=""){
		 jQuery('#downloadcookpath').val(path);
	 }
}


function RndNum(n){
	var rnd="";
	for(var i=0;i<n;i++)
	rnd+=Math.floor(Math.random()*10);
	return rnd;
}

/*备用
 * 
 */
 function campic(tdDiv,pid){
	var str='';
	if(VidiconType == "HI"){
		str='<span class="downBtn">'+
		'<a id=\'pic_'+pid+'\' class=\'downpress\' hidefocus=\'true\' onclick = \' showdownloaddialog("'+ pid +'","'+
																										get_cell_text(pid,1)+'","'+
																										get_cell_text(pid,2).replace(/\:/g,"")+'","'+
																										get_cell_text(pid,3).replace(/\:/g,"")+'","'+
																										get_cell_text(pid,5)+'","'+
																										get_cell_text(pid,6)+'","'+
																										jQuery("#VEHICLE_LN").val()+'","'+
																										VidiconType+ '") \'>'+
		
			'<span style="display:none;"></span>'+
			'<span style="display:none;"></span>'+
			'<span style="display:none;"></span>'+
			'<span style="display:none;"></span>'+
		'</a>'+
		'<span class="progress">&nbsp;0%&nbsp;</span>'+    //当前下载进度展示
		'<span style="display:none;"></span>'+             //当前下载进度
		'<span style="display:none;"></span>'+             //返回下载数据流标识
		'<span style="display:none;"></span>'+ 			   //setInterval标识
		'</span>'
	}else if(VidiconType=="DA"){
		var time = document.getElementById("line_end_time").value;		
		//alert(get_cell_text(pid,7)+','+get_cell_text(pid,8)+','+get_cell_text(pid,9)+','+get_cell_text(pid,10)+','+get_cell_text(pid,11)+','+get_cell_text(pid,12));
		str='<span class="downBtn">'+
				'<a id=\'pic_'+pid+'\' class=\'downpress\' href =\'#\'>'+
			/*'<a id=\'pic_'+pid+'\' class=\'downpress\' href =\'../gps/downLoadDAReplayVideo.shtml?vehicle_vin='+m_vidiconId+'&pid='+pid+
															'&filename='+get_cell_text(pid,1)+
															'&starttime='+get_cell_text(pid,2)+
															'&endtime='+get_cell_text(pid,3)+
															'&way='+get_cell_text(pid,5)+
															'&size='+get_cell_text(pid,6)+
															'&sourceType='+get_cell_text(pid,7)+
															'&recordType='+get_cell_text(pid,8)+
															'&planId='+get_cell_text(pid,9)+
															'&ssId='+get_cell_text(pid,10)+
															'&diskId='+get_cell_text(pid,11)+
															'&fileHandle='+get_cell_text(pid,12)+
															'&vehicle_ln='+jQuery('#VEHICLE_LN').val()+
															'&VidiconType=DA'+
															'&time='+time+
															'\' target=\'_blank\'>'+*/
			'</a>'+
		'</span>'
	}			
	tdDiv.innerHTML = str;
}

jQuery( function() {
	jQuery(window.parent.iframeshowArea).resize(function(){
		styleControl();
	});
	styleControl();
	/*jQuery("#LayFooter").css("left","20").css("top","330");
	jQuery(window.frames["snapshotpicframe"].document).find("body").bind("click",function(){
		snapshotpic();
	});*/
});
 
var popWidth ="500px";
var popMaxWidth ="655px";
var popHeight = "484px";
var popMaxHeiht = "660px"; 
function styleControl(){
	if(jQuery(window.parent.iframeshowArea).width() > 500){
		jQuery('.popArea').width(popMaxWidth);
		jQuery('.popArea').height(popMaxHeiht);
		jQuery('#map').height("400px");
		jQuery('#PPVSHI1').width("655px").height("400px");
		jQuery('.bDiv').height("164px");
		jQuery('.flexigrid').width("655px");
	}else{
		jQuery('.popArea').width(popWidth);
		jQuery('.popArea').height(popHeight);
		jQuery('#map').height("280px");
		jQuery('#PPVSHI1').width("500px").height("280px");
		jQuery('.bDiv').height("100px");
		jQuery('.flexigrid').width("500px");
	}
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

function waynorename(name){
	if(name == "驾驶员"){
		return 4;
	}else if(name == "整车"){
		return 1;
	}else if(name == "车门"){
		return 3;
	}else if(name == "路况"){
		return 2;
	}
}
/*
 * 点击下载按钮开始下载任务
 */
function downfilm(pid){
	if(0 > lConDevice){
		tishiShow("无法连接视频服务,请稍后再试！");
		return;
	}else{
        var searchStatus=true; 
		if(VidiconType == "HI"){
			//判断按钮状态，是否是下载状态
			if(jQuery("#pic_"+pid).parent().hasClass("downBtn")){
				if(jQuery(".bDiv span").hasClass("downingBtn")){
					tishiShow("不能同时下载多个视频！");
					searchStatus=false;
					return false;
				}
				/*jQuery(".bDiv .downingBtn").each(function(index,domele){
					if(index>0){
						tishiShow("不能同时下载多个视频！");
						searchStatus=false;
						return false;
					}
				});*/
				if(!searchStatus){
					return false;
				}
				jQuery("#pic_"+pid).parent().removeClass().addClass("downingBtn");
				var PlayOCX=document.getElementById("PPVSHI1");
				var tempPath=getcookies('savevideopath');
				if(tempPath==""||tempPath==null){
					tempPath="C:\\安芯视频\\视频回放\\";
				}
				
				// 日期当文件名，注：文件名不支持中间出现":"符号，故用"_"、"-"符号分割的时间
				var DateTime = new  Date();
				var szDate   = DateTime.getYear()+((DateTime.getMonth()+1)+"").ToStringByZero(2)+(DateTime.getDate()+"").ToStringByZero(2);
				tempPath=tempPath+jQuery('#VEHICLE_LN').val()+"_0"+waynorename(get_cell_text(pid,5))+get_cell_text(pid,5)+"_"+szDate+"-"+get_cell_text(pid,2).replace(/\:/g,"")+"_"+szDate+"-"+get_cell_text(pid,3).replace(/\:/g,"")+".mp4";

				//返回下载数据流标识
				returnstr=PlayOCX.StartDownloadFile(waynorename(get_cell_text(pid,5)),get_cell_text(pid,1),get_cell_text(pid,6),tempPath);

				if(returnstr==-2){
					tishiShow("磁盘空间不够！");
					return false;
				}else if(returnstr==-5){
					tishiShow("3G信号不稳定或设备已下线，请稍后重试！");
					return false;
				}
				
				jQuery("#pic_"+pid).children("span").eq(0).val(waynorename(get_cell_text(pid,5)));
				jQuery("#pic_"+pid).children("span").eq(1).val(get_cell_text(pid,1));
				jQuery("#pic_"+pid).children("span").eq(2).val(get_cell_text(pid,6));
				jQuery("#pic_"+pid).children("span").eq(3).val(tempPath);

				//存放下载数据流标识，用于判断下载进度
				jQuery("#pic_"+pid).next().next().next().val(returnstr);

				//循环列表中所有下载状态的数据
				jQuery(".bDiv span").each(function(index,domele){
					if(jQuery(domele).is('.downingBtn')){
						//取得下载状态的ID
						var thisid=jQuery(domele).children().attr("id");
						var netstatus=-1;
						//jQuery("#"+thisid).next().next().next().next().val(returnstr);
						//消除Interval状态
						window.clearInterval(jQuery("#"+thisid).next().next().next().next().val());
						//获得Interval状态码
						jQuery("#"+thisid).next().next().next().next().val(window.setInterval(function(){
							var per=PlayOCX.GetDownloadPos(jQuery("#"+thisid).next().next().next().val());
							//jQuery("#pic_"+pid).next().next().next().next().val(per);
							if(per<0){
								per=0;
							}
							jQuery("#"+thisid).next().next().val(per);
							if(jQuery("#"+thisid).next().next().val()==100){
								var complet=PlayOCX.StopDownloadFile(jQuery("#"+thisid).next().next().next().val());
								if(complet=="1"){
									tishiShow("视频成功！<br> 存储位置:"+jQuery("#"+thisid).children("span").eq(3).val());
									jQuery("#"+thisid).next().html('&nbsp;0%&nbsp;').css("display","none");
									jQuery("#"+thisid).parent().removeClass().addClass("downBtn");
								}
								jQuery("#"+thisid).next().next().val(0);
								jQuery("#"+thisid).next().next().next().val(0);
								jQuery("#"+thisid).children("span").eq(0).val();
								jQuery("#"+thisid).children("span").eq(1).val();
								jQuery("#"+thisid).children("span").eq(2).val();
								jQuery("#"+thisid).children("span").eq(3).val();
								window.clearInterval(jQuery("#"+thisid).next().next().next().next().val());
								return false;
							}
							/*var getDownloadStatus=PlayOCX.StartDownloadFile(jQuery("#"+thisid).children("span").eq(0).val(),
																jQuery("#"+thisid).children("span").eq(1).val(),
																jQuery("#"+thisid).children("span").eq(2).val(),
																jQuery("#"+thisid).children("span").eq(3).val());*/
							//if(jQuery("#"+thisid).next().next().val()>10||jQuery("#"+thisid).next().next().next().next().val()<0){
							
							var xmlresult = PlayOCX.GetDeviceStatus();
							if(xmlresult == "" || typeof(xmlresult) == "undefined" ){
								tishiShow("3G信号不稳定或设备已下线,请稍后重试!");
								//alert("获取视频文件失败!");
								jQuery("#"+thisid).next().html('&nbsp;0%&nbsp;').css("display","none");
								jQuery("#"+thisid).parent().removeClass().addClass("downBtn");
								jQuery("#"+thisid).next().next().val(0);
								jQuery("#"+thisid).next().next().next().val(0);
								jQuery("#"+thisid).children("span").eq(0).val();
								jQuery("#"+thisid).children("span").eq(1).val();
								jQuery("#"+thisid).children("span").eq(2).val();
								jQuery("#"+thisid).children("span").eq(3).val();
								window.clearInterval(jQuery("#"+thisid).next().next().next().next().val());
								return false;
							}else if(xmlresult == null ){
								tishiShow("通道无视频文件！");
								//alert(ways[i]+"通道无视频文件!");
								jQuery("#"+thisid).next().html('&nbsp;0%&nbsp;').css("display","none");
								jQuery("#"+thisid).parent().removeClass().addClass("downBtn");
								jQuery("#"+thisid).next().next().val(0);
								jQuery("#"+thisid).next().next().next().val(0);
								jQuery("#"+thisid).children("span").eq(0).val();
								jQuery("#"+thisid).children("span").eq(1).val();
								jQuery("#"+thisid).children("span").eq(2).val();
								jQuery("#"+thisid).children("span").eq(3).val();
								window.clearInterval(jQuery("#"+thisid).next().next().next().next().val());
								return false;
							}else{
								var xmldoc = loadXML(xmlresult);
								var elements = xmldoc.getElementsByTagName("CHStatus");
								for (var i = 0; i < elements[0].childNodes.length; i++) {
									var str = elements[0].getElementsByTagName("CH")[i].firstChild.nodeValue;
									if(str.split("-")[0]==jQuery("#"+thisid).children("span").eq(0).val()){
										netstatus=str.split("-")[2];
									}
								}
							}

							if(netstatus<0){
								var complet=PlayOCX.StopDownloadFile(jQuery("#"+thisid).next().next().next().val());
								if(complet==1){
									tishiShow("下载终止！");
									jQuery("#"+thisid).next().html('&nbsp;0%&nbsp;').css("display","none");
									jQuery("#"+thisid).parent().removeClass().addClass("downBtn");
								}
								jQuery("#"+thisid).next().next().val(0);
								jQuery("#"+thisid).next().next().next().val(0);
								jQuery("#"+thisid).children("span").eq(0).val();
								jQuery("#"+thisid).children("span").eq(1).val();
								jQuery("#"+thisid).children("span").eq(2).val();
								jQuery("#"+thisid).children("span").eq(3).val();
								window.clearInterval(jQuery("#"+thisid).next().next().next().next().val());
								return false;
							}

							/*
							if(jQuery("#"+thisid).next().next().next().val()==per){
								var dataid=parseInt(jQuery("#pic_"+pid).next().next().val());
								jQuery("#"+thisid).next().next().val(dataid+1);
							}else{
								jQuery("#"+thisid).next().next().next().val(per);
								jQuery("#"+thisid).next().next().val(0);
							}*/
							jQuery("#"+thisid).next().css("display","block").html("&nbsp;"+per+"%&nbsp;");
						}, 3000)); 
					}
				});
			}
			
		}else if(VidiconType == "DA"){
			tishiShow("该功能暂未提供!");
		}
	}
}

function SnapShotT(){
	var PlayOCX=document.getElementById("PPVSHI1");
	var tempPath=getcookies('savevideopath');
	if(tempPath==""||tempPath==null){
		tempPath="C:\\安芯视频\\视频回放\\";
	}
	var pathsource=tempPath;
	// 日期当文件名，注：文件名不支持中间出现":"符号，故用"_"、"-"符号分割的时间
	var DateTime = new  Date();
	var szDate   = DateTime.getYear()+((DateTime.getMonth()+1)+"").ToStringByZero(2)+(DateTime.getDate()+"").ToStringByZero(2);
	var szTime   = DateTime.getHours()+"h"+((DateTime.getMinutes()+1)+"").ToStringByZero(2)+"m"+(DateTime.getSeconds()+"").ToStringByZero(2)+"s"+(DateTime.getMilliseconds()+"").ToStringByZero(2)+"ms";
	tempPath=tempPath+jQuery('#VEHICLE_LN').val()+"_0"+waynorename(pathstatus)+pathstatus+"_"+szDate+"-"+szTime+".bmp";
	if(PlayOCX.PlayBackSnapshot(tempPath)){
		jQuery('.alert.alert-success').show();
		jQuery('.text').html("截屏成功！")
		jQuery('.path-uri').html("存储位置:"+setShortPath(pathsource));
		//tishiShow("截屏成功！<br> 存储位置:"+setShortPath(tempPath));
	}else{
		tishiShow("截屏失败！");
	}
}

function savecookies(cookiename,cookievalue){
	jQuery.cookie(cookiename,cookievalue,{ expires: 30 });
}

function getcookies(cookiename){
	var cookievalue=jQuery.cookie(cookiename);
	return cookievalue;
}

/**
 * 为路径最后一段增加\
 */
function addpath(path){
	if(path.substr(path.length-1)!='\\') 
        path+='\\';
    return path;
}

function valid_path(path){
	// 盘符为a-z ,路径不能包含特殊字符 \/:*?"<>|
    //var patrn=/^[a-z]:\\([^\/*\"><|:?*])+$/;
    var temp=path.indexOf("\\\\");
    if(temp!=-1){ 
        return false;
    }
    var patrn=/^[a-zA-Z]:\\([^\/*\><|:?*\"])*$/;
    if(patrn.test(path)){
        return true;
    }
    return false;
}

function settingPath(){
	closedialog();
	jQuery("#pathSetting").css("display","block");
	jQuery(".warn").css("display","none");
	var cookiepath=getcookies('savevideopath');
	if(cookiepath==null||cookiepath==""){
		jQuery('#orderpath').val("C:\\安芯视频\\视频回放\\");
	}else{
		jQuery('#orderpath').val(cookiepath);
	}
}

function closedown(){
	jQuery(".warn").css("display","block");
	jQuery("#pathSetting").css("display","none");
}

function savepath(){
	var downloadpath=jQuery('#orderpath').val();
	if(downloadpath.length<=0){
		jQuery("#tishitu").attr('src','../newimages/sidelayerimages/ico_alarm.png');
		tishiShow("路径不合法");
		return;
	}else{
		downloadpath=addpath(downloadpath);
		var pathflag=valid_path(downloadpath);
		if(pathflag){
			savecookies('savevideopath',downloadpath);
		}else{
			jQuery("#tishitu").attr('src','../newimages/sidelayerimages/ico_alarm.png');
			tishiShow('路径不合法');	
			return;
		}
	}
	closedown();
}

function get_cell_text(pid, cell_idx) {
	var cellName = jQuery('#videoFileList').flex_colModel()[cell_idx].name;
	var name;
	jQuery.each(jQuery('#row' + pid).parents("div.flexigrid").find('th'),function(i){  
		if(jQuery(this).attr("colname") == cellName) {
			name = jQuery('#row' + pid).children('td').eq(i).eq(0).text();
			return false;
		}
	});
	return name;
}

function reWritephoto(tdDiv,pid){
    var way = get_cell_text(pid,5);
    tdDiv.innerHTML = waynorecode(way);
}
var pathstatus="";
var rowstandbyclick =function(rowData,pid) {
	closedialog();
	//if(!jQuery(".trSelected span").hasClass("downingBtn")){
	if(jQuery(rowData).data("flag")=="HI"){
		selectPlayFile(jQuery(rowData).data("way2"),jQuery(rowData).data("FileName"),jQuery(rowData).data("FileSize"),
				jQuery(rowData).data("starttime"),jQuery(rowData).data("endtime"));

		pathstatus=jQuery(rowData).data("way");
	}
	if(jQuery(rowData).data("flag")=="DA"){
		selectPlayFileDA(jQuery(rowData).data("FileName"), 
				jQuery(rowData).data("starttime"), jQuery(rowData).data("endtime"), jQuery(rowData).data("sourceType"), 
				jQuery(rowData).data("recordType"), jQuery(rowData).data("FileSize"), jQuery(rowData).data("planId"), 
				jQuery(rowData).data("ssId"), jQuery(rowData).data("diskId"), jQuery(rowData).data("fileHandle"));
	}
	//}else{
		//jQuery(".trSelected .downingBtn").removeClass("trSelected");
		
	//}
};
function reloadvideofile(str,flag){
	jQuery('#videoFileList').flexOptions({
		newp: 1,
	    url:'<s:url value="/SendInfo/videoFileloadList.shtml" />',
	    params: [{name: 'terminalViBean.videoFilelist', value: str},
	             {name: 'terminalViBean.VEHICLE_VIN', value: flag}]
	    });	
	jQuery('#videoFileList').flexReload();
}

function StopPlayAllWindows(){
	 if(m_vidiconId=="HI"){
		 PlayOCX=document.getElementById("PPVSHI1");
	     PlayOCX.StopPlaybackByFile();
	 }else if(m_vidiconId=="DA"){
		 var ocx = document.getElementById("ocx");
		 ocx.CloseVideo(0);
		 ocx.LoginOutServer();
	 }
}

var ral_resultShowflash= 2000;
function hideshowresultDiv(flag){
	if(flag==1){
		jQuery('#Layer1').css('display','none');
	}
	else if(flag==0){
		jQuery('#Layer1').css('display','block');
	}
}
function tishiShow(info){
	hideshowresultDiv(0);
	document.getElementById("inforeault").innerHTML=info;
	tishiHide();
}
function tishiHide(){
	window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
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

function photoOnline(){
	var vin = document.getElementById("vin").value;	//alert(vin);
	GPSDwr.returnTipInfo(vin,returnTipInfo_callback);
}
function returnTipInfo_callback(date){
	
	var TerminalViBean = date[0];
	var vin = TerminalViBean.VEHICLE_VIN;
	
	if(TerminalViBean.color =="b" && TerminalViBean.VEH_EXT_INFO  == 1){

	}
	else{
		tishiShow("3G视频不在线，请稍后再尝试视频回放！");
		//alert("车辆已熄火，请稍后再尝试车辆拍照！");
	}
}

function closedialog(){
	jQuery('.alert.alert-success').hide();
}

function setShortPath(path){
	if(path.length>25){
		path=path.substring(0, 25)+"...&nbsp;&nbsp;&nbsp;&nbsp;";
	}else{
		path=path+"&nbsp;&nbsp;&nbsp;&nbsp;";
	}
	return path;
}

function copePath(){
	var tempPath="";
	tempPath=getcookies('savevideopath');
	if(tempPath==""||tempPath==null){
		tempPath="C:\\安芯视频\\视频回放\\";
	}
	var ppocx = document.getElementById("PPVSHI1");
	ppocx.OpenFolder(tempPath);
}
</script>