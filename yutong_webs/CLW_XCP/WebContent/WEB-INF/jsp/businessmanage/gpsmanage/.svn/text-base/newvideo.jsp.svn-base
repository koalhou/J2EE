<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<script  type="text/javascript">
/**************************
 * 本安系统参数
 *************************************/
//注册服务器IP、端口
var m_szRegIP = "125.46.82.46";
var m_iRegPort = "7660";
//流媒体服务器IP、端口、使用方式(3种～2种，参数不详细，默认1就ok)
var m_szRsmIP = "125.46.82.46";
var m_iRsmPort = "7554";
//系统视频登录的用户名和密码
var m_user="root";
var m_password="12345";

var m_iRsmUseType = "1";
var iStreamType="1";
var iPuGetStreamMode="1";

/**********************
 * 基本参数
 *202.75.216.155
 uID: yutong
 PWD: yutong2012
 设备ID: yutong2c
 * 
 *************/
//最大通道数
 var m_kMaxNum="4";
 //当前焦点选定的窗口号（通道号），未指定时为空
 var m_atThisNum="";
 //当前处理视频窗口终端类型 HI=本安 HS=航盛
 var VidiconType="HI";
 var m_HSIpAdd = "117.135.136.130";
 var m_HSTypeNum ="121";
 //摄像设备ID
 var m_vidiconId="";//vehicle0423  tygj002
 //车牌
 var m_carNum="";
 //图片保存路径
 var m_picPath="C:\\安芯视频\\视频监控\\";
 //视频窗口DIV宽度高度

/*
*※当前浏览器是否支持ocx控件标志位，true：支持(IE)，false:不支持
*/
 var sys_ocx=true;

 
//记录所有本安视频窗口属性-是否播放状态属性 1:播放中 0：停播中
 var m_onshow=new Array();
 m_onshow[0]="0";//5窗口模式大监视窗口
 m_onshow[1]="0";
 m_onshow[2]="0";
 m_onshow[3]="0";
 m_onshow[4]="0";
 //记录所有海康声音播放状态
 var m_opensound=new Array();
 m_opensound[0]="0";//不使用
 m_opensound[1]="0";//0:未播放声音；1正播放声音
 m_opensound[2]="0";
 m_opensound[3]="0";
 m_opensound[4]="0";
//记录所有海康视频文件存放位置
 var m_hipath=new Array();
 m_hipath[1]="";
 m_hipath[2]="";
 m_hipath[3]="";
 m_hipath[4]="";
 //记录所有航盛视频窗口属性-是否播放状态属性 1:播放中 0：停播中
 var m_onshow_hs=new Array();
 m_onshow_hs[0]="0";
 m_onshow_hs[1]="0";
 m_onshow_hs[2]="0";
 m_onshow_hs[3]="0";
 m_onshow_hs[4]="0";

 var m_conflag_hi=new Array();
 m_conflag_hi[1]=-1;
 m_conflag_hi[2]=-1;
 m_conflag_hi[3]=-1;
 m_conflag_hi[4]=-1;

 //视频窗口属性-是否大窗口属性 1:大窗口 0：未大窗口
 var max_show_flag="0";

 

 /*************************************************
 Function:		showFourWindows
 Description:	初始化4个窗口大小
 *************************************************/
 function showFourWindows(){
	    //alert(VidiconType);
	 	//1号
		var ocxTable1=document.getElementById("PPVS"+VidiconType+"1");
		ocxTable1.style.margin="0 0 0 0";
		ocxTable1.style.height=m_videoDivHeight/2-2;
		ocxTable1.style.width=m_videoDivWidth/2-6;
		//2号
		var ocxTable2=document.getElementById("PPVS"+VidiconType+"2");
		ocxTable2.style.margin="0 0 0 0";
		ocxTable2.style.height=m_videoDivHeight/2-2;
		ocxTable2.style.width=m_videoDivWidth/2-6;
		//3号
		var ocxTable3=document.getElementById("PPVS"+VidiconType+"3");
		ocxTable3.style.margin="0 0 0 0";
		ocxTable3.style.height=m_videoDivHeight/2-2;
		ocxTable3.style.width=m_videoDivWidth/2-6;
		//4号
		var ocxTable4=document.getElementById("PPVS"+VidiconType+"4");
		ocxTable4.style.margin="0 0 0 0";
		ocxTable4.style.height=m_videoDivHeight/2-2;
		ocxTable4.style.width=m_videoDivWidth/2-6;

		
 	//设置大窗口状态
 	max_show_flag="0";
 }

 var popWidth ="500px";
 var popMaxWidth ="655px";
 var popHeight = "484px";
 var popMaxHeiht = "660px";
 var max_window_num="";

 jQuery( function() {
	 jQuery('#videoframearea').addClass('popArea');
	 <s:if test="terminalViBean.VIDEO_FACTORY=='HI'">
	 jQuery('#ocxHI').addClass('video');
	 jQuery('#carmera1').css('display','');
	 </s:if>
	 <s:if test="terminalViBean.VIDEO_FACTORY=='DA'">
	 jQuery('#ocxDA').addClass('video');
	 jQuery('#camera_boxda').css('display','');
	 </s:if>
	 <s:if test="terminalViBean.VIDEO_FACTORY=='YW'">
	 jQuery('#ocxYW').addClass('video');
	 jQuery('#camera_boxyw').css('display','');
	 </s:if>
 	 jQuery(window.parent.iframeshowArea).resize(function(){
 		 styleControl();
 	});
 	 styleControl();
 });
 function styleControl(){
 	if(jQuery(window.parent.iframeshowArea).width() > 500){
 		VidiconType = document.getElementById('VIDEO_FACTORY').value;
 		jQuery('#videoframearea').width(popMaxWidth);
 		jQuery('#videoframearea').height(popMaxHeiht);
 		if(VidiconType=="HI"){
 		
 		jQuery('#ocxHI').height("571px");
 		jQuery('#videoCenter').width("645px");
 		jQuery('#videoCenter').height("534px");
 		m_videoDivWidth=Number(645);
 	    m_videoDivHeight=Number(534);
 	    if(max_show_flag=="0"){
 	    	 showFourWindows();
 	    }
 	    if(max_show_flag=="1"){
 	    	maxVideoWindow(max_window_num);
 	     }
	    jQuery('#carmera1').width("645px");
 		}

 		if(VidiconType=="DA"){
 			jQuery('#ocx').width("645px");
 			jQuery('#ocxDA').height("571px");
 			jQuery('#ocx').height("556px");
 			jQuery('#camera_boxda').width("625px");
 		}

 		if(VidiconType=="YW"){
 			jQuery('#ocxyw').width("645px");;
 			jQuery('#ocxYW').height("571px");
 			jQuery('#ocxyw').height("556px");
 			jQuery('#camera_boxyw').width("625px");
 		}
 		
 	}
 	else{
 		jQuery('#videoframearea').width(popWidth);
 		jQuery('#videoframearea').height(popHeight);
 		VidiconType = document.getElementById('VIDEO_FACTORY').value;
 		if(VidiconType=="HI"){
 		
 		jQuery('#ocxHI').height("395px");
 		jQuery('#videoCenter').width("490px");
 		jQuery('#videoCenter').height("360px");
 		m_videoDivWidth=Number(490);
 	    m_videoDivHeight=Number(360);
 	   if(max_show_flag=="0"){
 	    showFourWindows();
 	   }
 	    if(max_show_flag=="1"){
	    	maxVideoWindow(max_window_num);
	     }
 	   jQuery('#carmera1').width("490px");
 		}

 		if(VidiconType=="DA"){
 			jQuery('#ocx').width("490px");
 			jQuery('#ocxDA').height("395");
 			jQuery('#ocx').height("380px");
 			jQuery('#camera_boxda').width("470px");
 		}

 		if(VidiconType=="YW"){
 			jQuery('#ocxyw').width("490px");
 			jQuery('#ocxYW').height("395px");
 			jQuery('#ocxyw').height("380px");
 			jQuery('#camera_boxyw').width("470px");
 		}
 		//jQuery('#opeerate_desc').width("470px");
 	}
 }		
 /*************************************************
 Function:		maxVideoWindow
 Description:	放大某视频窗口
 Input:			winodwNum:视频窗口号
 *************************************************/
 function maxVideoWindow(winodwNum){
	 max_window_num=winodwNum;
 	for(var i =1; i <= 4; i ++)
 	{
 		if(i==Number(winodwNum)&&winodwNum==1){
 	 		var ocxTable=document.getElementById("PPVS"+VidiconType+winodwNum);
 	 		ocxTable.style.margin="0 0 0 0";
 	 		ocxTable.style.height=m_videoDivHeight-5;
 	 		ocxTable.style.width=m_videoDivWidth-4;
 		}else
 		if(i==Number(winodwNum)&&winodwNum==3){
 	 		var ocxTable=document.getElementById("PPVS"+VidiconType+winodwNum);
 	 		var ocxdiv=document.getElementById("table"+VidiconType+winodwNum);
 			ocxdiv.style.margin="0px 0 0 0";
 	 		ocxTable.style.margin="0 0 0 0";
 	 		ocxTable.style.height=m_videoDivHeight-8;
 	 		ocxTable.style.width=m_videoDivWidth-5;
 		}
 		else if(i==Number(winodwNum)&&(winodwNum==2||winodwNum==4)){
 			var ocxTable=document.getElementById("PPVS"+VidiconType+winodwNum);
 			var ocxdiv=document.getElementById("table"+VidiconType+winodwNum);
 			ocxdiv.style.margin="0px 0 0 0";
 	 		ocxTable.style.margin="0 0 0px 0px";
 	 		ocxTable.style.height=m_videoDivHeight-5;
 	 		ocxTable.style.width=m_videoDivWidth-4;
 	 		}  
 		else{
 			//其他视频窗口不可视
 			document.getElementById("div"+VidiconType+i).style.display="none";
 		}
 	}
 	//设置大窗口状态
 	max_show_flag="1";
 }
 /*************************************************
 Function:		resizeVideoWindow
 Description:	恢复某视频窗口大小
 *************************************************/
 function resizeVideoWindow(){
 	//重置大小
 	
 	showFourWindows();
 	for(var i =1; i <= 4; i ++)
 	{
 			//同类视频窗口可视
 			if(sys_ocx){
 				document.getElementById("div"+VidiconType+i).style.display="";
 			}else{
 				document.getElementById("div"+VidiconType+i).style.display="table";
 			}
 			
 	}
 }
//异常情况
			function showFourWindows1(){
				//alert('phy===1');
				//1号
				var ocxTable1=document.getElementById("PPVS"+VidiconType+"1");
				ocxTable1.style.margin="0 0 0 0";
				ocxTable1.style.height=m_videoDivHeight/2-5.5;
				ocxTable1.style.width=m_videoDivWidth/2-2.5;
				//2号
				var ocxTable2=document.getElementById("PPVS"+VidiconType+"2");
				var ocxdiv2=document.getElementById("table"+VidiconType+"2");
				ocxdiv2.style.margin="-223px 0 1px 425px;";
				ocxTable2.style.margin="0px 0 0px 0";
				ocxTable2.style.height=m_videoDivHeight/2-5.5;
				ocxTable2.style.width=m_videoDivWidth/2-2.5;
				//3号
				var ocxTable3=document.getElementById("PPVS"+VidiconType+"3");
				var ocxdiv3=document.getElementById("table"+VidiconType+"3");
				ocxdiv3.style.margin="3px 0 1px 0";
				ocxTable3.style.margin="0px 0 0px 0";
				ocxTable3.style.height=m_videoDivHeight/2-5.5;
				ocxTable3.style.width=m_videoDivWidth/2-2.5;
				//4号
				var ocxTable4=document.getElementById("PPVS"+VidiconType+"4");
				var ocxdiv4=document.getElementById("table"+VidiconType+"4");
				ocxdiv4.style.margin="-222px 0 1px 425px;";
				ocxTable4.style.margin="0px 0 0px 0";
				ocxTable4.style.height=m_videoDivHeight/2-5.5;
				ocxTable4.style.width=m_videoDivWidth/2-2.5;
			
				
				//设置大窗口状态
				max_show_flag="0";

				for(var iForm=1; iForm<5; iForm++)
				{
						document.getElementById("table"+VidiconType+iForm).style.border="solid #171C20 1px";
				}
				
			}
 /*************************************************
 Function:		RunPlay
 Description:	当前指定窗口开始播放(本安)
 Input:			num : 播放对象编号（通道号）
 *************************************************/
 function RunPlay(num){
	 
 		PlayOCX=document.getElementById("PPVS"+VidiconType+num);
 		m_vidiconId=document.getElementById("VIDEO_ID").value;
 		//alert(PlayOCX);
 		if(PlayOCX == null){
 			tishiShow("视频控件加载失败！");
 	 		//alert("视频控件加载失败!");
			return ;
 	 	}
 		//设置提示语
 		var shows="监控...";
 		if(num==1){
 			shows="整车"+shows;
 		}else if(num==2){
 			shows="路况"+shows;
 		}else if(num==3){
 			shows="车门"+shows;
 		}else if(num==4){
 			shows="司机"+shows;
 		}
 		

 		//针对宇通网络环境需调用该接口
 		PlayOCX.SetDeviceNetLine( 1 );
 		
 		if(m_vidiconId!=""){
	 		lConDevice = PlayOCX.ConnectDeviceByACS(m_vidiconId, m_szRegIP, m_iRegPort, m_user, m_password);
	 		m_conflag_hi[num]=lConDevice;
	 		if(0>lConDevice){
	 			tishiShow("连接设备失败！");
	 			//alert("连接设备失败！");
	 			return;
	 		}
	 		PlayOCX.SetPlayWndText(shows);
	 		
	 		//alter by chengjin begin
	 		var name="视频通道:"+num;
	 		if(num==1){
		 		name=name+" 整车";
	 		}else if(num==2){
	 			name=name+" 路况";
	 		}else if(num==3){
	 			name=name+" 车门";
	 		}else if(num==4){
	 			name=name+" 司机";
	 		}
	 		PlayOCX.SetDeviceInfoForShow(name);
	 		
	 		//alter by chengjin end
 		
	 		
	 		//PlayOCX.StreamPlayStart(m_szRsmIP, m_iRsmPort ,m_vidiconId ,num , iStreamType , iPuGetStreamMode);
	 		iPlayHandle=PlayOCX.StreamPlayStartByTCP(m_szRsmIP, m_iRsmPort ,m_vidiconId ,num , iStreamType , iPuGetStreamMode);
	 		//alter by chengjin begin
	 		if(iPlayHandle<0){
	 			tishiShow("预览失败!");
	 			return;
	 		}else{
	 			//PlayOCX.SetIsSupportAudio(true);
	 			alldisplay(num);
	 			m_onshow[num]="1";
	 		}
	 		//alter by chengjin end
 		}
 	}


 
 //var LocString=String(window.document.location.href);
  /*************************************************
 Function:		RunPlayDA
 Description:	当前指定窗口开始播放(大华)
 *************************************************/
 function RunPlayDA(){
	 var ocx = document.getElementById("ocx");
	 //alert(ocx);
	 if(ocx == null){
		 tishiShow("视频控件加载失败！");
	 		//alert("视频控件加载失败!");
			return ;
	 	}
	 m_vidiconId = document.getElementById("VIDEO_ID").value;
	 
	 
	 var mode=0;
	 if(m_vidiconId!=""){
		// alert(m_szRegIP+","+m_iRegPort+","+m_user+","+m_password);
		var logret = ocx.LoginServer(m_szRegIP,m_iRegPort,m_user,m_password,mode );
		//alert('===1');
		//var logret = ocx.LoginServer("58.240.237.194","9000","1","1",0 );
		//alert('===2');
		//alert(logret);
		 //alert(lConDevice);
		 //if(0<logret){
				//alert("连接设备失败！");
				//return;
		 //}
		 //window.setTimeout("openDAVideo()",1000);
		 //openDAVideo();
	 }
}

 /*************************************************
 Function:		RunPlayYW
 Description:	当前指定窗口开始播放(有为)
 *************************************************/
 function RunPlayYW(){
	 var ocx = document.getElementById("ocxyw");
	 //alert(ocx);
	 if(ocx == null){
		 tishiShow("视频控件加载失败！");
	 		//alert("视频控件加载失败!");
			return ;
	 }
	 for(var i=1;i<5;i++){
	       var name="视频通道:"+i;
	 		if(i==1){
		 		name=name+" 整车";
	 		}else if(i==2){
	 			name=name+" 路况";
	 		}else if(i==3){
	 			name=name+" 车门";
	 		}else if(i==4){
	 			name=name+" 司机";
	 	}
	   ocx.SetSubTitle(i,name);
	   //ocx.ShowTitleButton(i, 1, false);
	}
	 m_vidiconId = document.getElementById("VIDEO_ID").value;
	 
	 if(m_vidiconId!=""){
		// alert(m_szRegIP+","+m_iRegPort+","+m_user+","+m_password);
		 //ocx.LoginMediaServer(m_szRegIP,m_iRegPort,m_user,m_password);
		 ocx.ConnectMediaServer(m_szRegIP,m_iRegPort);
		 //alert(1);
		 //openYWVideo();
		 
	 }
}

function openYWVideo(){
	var ocx = document.getElementById("ocxyw");
	var vehln=document.getElementById("vehln").value;
	for(var i=1;i<5;i++){
	       var name="视频通道:"+i;
	 		if(i==1){
		 		name=name+" 整车";
	 		}else if(i==2){
	 			name=name+" 路况";
	 		}else if(i==3){
	 			name=name+" 车门";
	 		}else if(i==4){
	 			name=name+" 司机";
	 		}
	 }
	for(var j=1;j<5;j++){

		var nResult = ocx.NetOpenEx(vehln, j,j, m_szRegIP, m_iRegPort, 0,0,0);
		if(nResult!=0){
			tishiShow(name+"无法播放, 可能正在连接服务器, 请稍后");
			continue;
		}else{
			if(!ocx.NetPlay(j)){
				tishiShow(name+"预览失败!");
			}
		}
	}
	
}

function openDAVideo(){
	var ocx = document.getElementById("ocx");
	//alert(m_vidiconId);
	for(var j=0;j<4;j++){

		var t= ocx.OpenVideo(m_vidiconId,j,j );
		//alert(t);
		 
	}
}
 
 /*************************************************
 Function:		RunPlayForHS
 Description:	当前指定窗口开始播放(航盛)
 Input:			num : 播放对象编号+1（通道号+1）
 *************************************************/
 function RunPlayForHS(num){
 		//alert("PPVS"+VidiconType+num);
 		PlayOCX=document.getElementById("PPVS"+VidiconType+num);
 		var i = PlayOCX.SetDeviceInfo2(m_HSTypeNum, m_vidiconId, m_HSIpAdd);
         if (i != 0) {
        	 tishiShow("设置视频失败！");
             //alert("设置视频失败");
             return;
         }
         i = PlayOCX.OpenVideo(Number(num)-1);
         if (i != 0) {
        	 tishiShow("打开视频失败！");
             //alert("打开视频失败");
             return;
         }
 		m_onshow_hs[num]="1";
 	}

 /*************************************************
 Function:		button
 Description:	开关当前指定视频，并刷新图片
 *************************************************/
 function StopOrRunPlay(){
 	if(m_atThisNum==""){
 		tishiShow("请选择要操作的窗口！");
 		//alert('请选择要操作的窗口');
 	}else if(m_vidiconId==""){
 		tishiShow("没有选择可播放视频的终端！");
 		//alert('没有选择可播放视频的终端');
 	}else{
 		if(VidiconType=="HI"){
 			if("1"==m_onshow[m_atThisNum]){
 				StopPlay(m_atThisNum);
 			}else{
 				if("0"==m_atThisNum){
 					RunPlayMain(m_mainOnShow);
 				}
 				else{
 					RunPlay(m_atThisNum);
 				}
 			}
 		}else if(VidiconType=="HS"){
 			if("1"==m_onshow_hs[m_atThisNum]){
 				StopPlayForHS(m_atThisNum);
 			}else{
 				if("0"==m_atThisNum){
 					RunPlayMainForHS(m_mainOnShow);
 				}
 				else{
 					RunPlayForHS(m_atThisNum);
 				}
 			}
 		}
 	}
 	//reShowButtionGif();
 }
 /*************************************************
 Function:		reShowButtionGif
 Description:	刷新按钮图片
 *************************************************/
 function reShowButtionGif(){
 	var buttonStopORPlay =document.getElementById("stopOrPlayButton");
 	if(m_atThisNum==""){
 		return;
 	}
 	var flag="1";
 	if(VidiconType=="HI"){
 		flag=m_onshow[m_atThisNum];
 	}else if(VidiconType=="HS"){
 		flag=m_onshow_hs[m_atThisNum];
 	}
 	if(flag=="1"){
 		buttonStopORPlay.src="images/video/stop.jpg";
 	}else {
 		buttonStopORPlay.src="images/video/start.jpg";
 	}
 }
 /*************************************************
 Function:		StopPlay
 Description:	停止当前指定窗口播放(本安)
 Input:			num : 关闭对象编号（通道号）
 *************************************************/
 function StopPlay(num){
 		PlayOCX=document.getElementById("PPVSHI"+num);
 		PlayOCX.StreamPlayStop();
 		m_onshow[num]="0";
 		alldisplaynone(num);
 		closepicshot();
 	}

 function StopPlayDA(num){
	 var ocx = document.getElementById("ocx");
	 
	 ocx.CloseVideo(num);
	 
	 ocx.LoginOutServer();

 }
 /*************************************************
 Function:		StopPlay
 Description:	停止当前指定窗口播放(航盛)
 Input:			num : 关闭对象编号（通道号）
 *************************************************/
 function StopPlayForHS(num){
 		//alert('===stop==='+"PPVSHS"+num);
 		PlayOCX=document.getElementById("PPVSHS"+num);
 		PlayOCX.CloseVideo();
 		m_onshow_hs[num]="0";
 	}
 /*************************************************
 Function:		StopPlayAllWindows
 Description:	停止所有窗口播放
 *************************************************/
 function StopPlayAllWindows(){
	 if(VidiconType=="HI"){
		 for(var i=1;i<5;i++){
	 			if(m_onshow[i]=="1"){
	 				StopPlay(i);
	 			}
	 		}

	 }else if(VidiconType=="DA"){
		 for(var i=0;i<4;i++){
	 			
			 StopPlayDA(i);
	 			
	 	  }
	 }else if(VidiconType=="YW"){
		 var ocx = document.getElementById("ocxyw");
		 ocx.NetStopAll()
		 ocx.NetCloseAll(); 		 
	 }
 		
 		//reShowButtionGif();
}

 function returnStartPlayAllBAWindows_callback(date){
	 var TerminalViBean = date[0];
		var vin = TerminalViBean.VEHICLE_VIN;
		//if(TerminalViBean.color =="b" && TerminalViBean.VEH_EXT_INFO  == 1){
		if(TerminalViBean.dvrState  == 1){//只判断3G状态位
			if(VidiconType=="HS"){
				for(var i=1;i<5;i++){
					if(m_onshow[i]=="0"){
						RunPlayForHS(i);
					}
				}
				resizeVideoWindow();
			}
			else if(VidiconType=="HI"){
				for(var j=1;j<5;j++){
					if(m_onshow[j]=="0"){
						RunPlay(j);
					}
				}
				resizeVideoWindow();
				//document.getElementById("PPVSBA1").SelectWindow;
			}
			else if(VidiconType=="DA"){
				
				//for(var j=0;j<4;j++){
					//alert("大华终端");
					window.setTimeout("RunPlayDA()",1000);
					//RunPlayDA();
					//resizeVideoWindow();
				//}
				
			}else if (VidiconType=="YW"){
				RunPlayYW();
			}
		}
		else{
			tishiShow("3G视频不在线，请稍后再尝试视频监控！");
			PlayOCX=document.getElementById("PPVS"+VidiconType+1);
			PlayOCX.SetPlayWndText("整车监控...");
			PlayOCX=document.getElementById("PPVS"+VidiconType+2);
			PlayOCX.SetPlayWndText("路况监控...");
			PlayOCX=document.getElementById("PPVS"+VidiconType+3);
			PlayOCX.SetPlayWndText("车门监控...");
			PlayOCX=document.getElementById("PPVS"+VidiconType+4);
			PlayOCX.SetPlayWndText("司机监控...");
			//alert("车辆已熄火，请稍后再尝试车辆拍照！");
		}
 }

 /*************************************************
 Function:		StartPlayAllBAWindows
 Description:	本安窗口进行播放(1-4通道)
 *************************************************/
 function StartPlayAllBAWindows(){
	 var vin = document.getElementById("vin").value;	
	 GPSDwr.returnTipInfo(vin,returnStartPlayAllBAWindows_callback);
}

 function showtextshow(){
		m_vidiconId=document.getElementById("VIDEO_ID").value;
		if(m_vidiconId==""){
			tishiShow("该车未安装3G视频设备！");
		 //alert("该车未安装3G视频设备!");
		}
	}
	
 /*************************************************
 Function:		SnapShotPic
 Description:	获取某窗口图片(本安)
 Input:			num:通道号
 *************************************************/
 function SnapShotPic(num){

 		PlayOCX=document.getElementById("PPVS"+VidiconType+num);
 		//alter by chengjin begin
 		var cookiepath=getcookies('newvideorecord');
 		var tempPath="";
 		var filepath="";
 		if(cookiepath==null||cookiepath=="")
 		{
 			tempPath=m_picPath;
 		}else{
 			tempPath=cookiepath;
 		}
 		//alter by chengjin end
 		
 		// 日期当文件名，注：文件名不支持中间出现":"符号，故用"_"、"-"符号分割的时间
 		var DateTime = new  Date();
 		var szDate   = DateTime.getYear()+((DateTime.getMonth()+1)+"").ToStringByZero(2)+(DateTime.getDate()+"").  ToStringByZero(2);
 		var szTime   = DateTime.getHours()+"h"+(DateTime.getMinutes()+"").ToStringByZero(2)+"m"+(DateTime.getSeconds()+"").ToStringByZero(2)+"s"+(DateTime.getMilliseconds()+"ms");
        //alert(szTime);
 	    //alert(DateTime.getMilliseconds());
 		var vehln=jQuery("#vehln").val();
 		var name="";
 		if(num==1){
	 		name=name+"整车";
 		}else if(num==2){
 			name=name+"路况";
 		}else if(num==3){
 			name=name+"车门";
 		}else if(num==4){
 			name=name+"司机";
 		}
 		filepath=tempPath+vehln+"_0"+num+name+"_"+szDate+"-"+szTime+".bmp";
 		//alert(tempPath);
 		if(PlayOCX.Snapshot(filepath))
 		{
            //截取存储路径
            tempPath=setShortPath(tempPath,25);
            jQuery("#picpath").html("存储位置:"+tempPath);
            jQuery("#picshot").css('display','');
 			//tishiShow("截屏成功！"+"<br>"+"存储位置:"+tempPath);
 			//alert("图片保存为"+tempPath+"!");
 		}else{
 			closepicshot();
 			tishiShow("截图失败,请确认通道状态是否正常,再尝试截屏！");
 			//alert("图片获取失败!");
 		}
 	
 }

 //add by chengjin  begin
 function setShortPath(path,num){
	if(path.length>num){
		//path=path.substring(0, 25)+"..."+"&nbsp;<a href='#' onclick='copePath()'>复制路径</a>";
		path=path.substring(0, num)+"...";
	}else{
		//path=path+"&nbsp;<a href='#' onclick='copePath()'>复制路径</a>";
		//paht=
	}
	return path;
}

function copePath(){
	var tempPath="";
	tempPath=getcookies('newvideorecord');
	if(tempPath==""||tempPath==null){
		tempPath=m_picPath;
	}
	 var ppocx = document.getElementById("PPVSHI1");
	 ppocx.OpenFolder(tempPath);
	/*
	if (window.clipboardData) {
		window.clipboardData.setData("Text", tempPath);
	}*/
}

 function copypichi(num){
	 jQuery("#tishitu").attr('src','../newimages/sidelayerimages/right.gif');
	 SnapShotPic(num); 
 }

 function startrecordhi(num,id){
	 jQuery("#tishitu").attr('src','../newimages/sidelayerimages/right.gif');
	 var id="#"+id;
	 PlayOCX=document.getElementById("PPVS"+VidiconType+num);
	 var vehln=jQuery("#vehln").val();
	 var name="";
	 if(num==1){
		 name="整车";
	 }else if(num==2){
		 name="路况";
	 }else if(num==3){
		 name="车门";
	 }else if(num==4){
		 name="司机";
	 }
	 var cookiepath=getcookies('newvideorecord');
	 var path="";
		if(cookiepath==null||cookiepath=="")
		{
			path=m_picPath;
		}else{
			path=cookiepath;
		}
	 var bRet = PlayOCX.StartRecord(vehln , num, path,name);
	 closepicshot();
		if(bRet>=0)
		{
			tishiShow("开始录像！");
			jQuery(id).removeClass();
			jQuery(id).addClass("videoStartRecord2");
			jQuery(id).attr('title','正在录制');
			if(num==1){
				jQuery("#ppvstop1").removeClass();
				jQuery("#ppvstop1").addClass("videoStopRecord1");
			}else if(num==2){
				jQuery("#ppvstop2").removeClass();
				jQuery("#ppvstop2").addClass("videoStopRecord1");
			}else if(num==3){
				jQuery("#ppvstop3").removeClass();
				jQuery("#ppvstop3").addClass("videoStopRecord1");
			}else if(num==4){
				jQuery("#ppvstop4").removeClass();
				jQuery("#ppvstop4").addClass("videoStopRecord1");
			}
			
			
		}else if(bRet==-1){
			tishiShow("录像失败,请稍后重新尝试！");
		}else if(bRet==-2){
			tishiShow("控件不在预览状态，无法录像！");
		}else if(bRet==-3){
			tishiShow("控件已在录像状态！");
		}else if(bRet==-4){
			tishiShow("录像文件夹创建失败！");
		}else if(bRet==-5){
			tishiShow("磁盘容量不足！");
		}else{
			tishiShow("录像失败,请稍后重新尝试！");
		}
 }

 function menustartrecordhi(num){
	 jQuery("#tishitu").attr('src','../newimages/sidelayerimages/right.gif');
	 PlayOCX=document.getElementById("PPVS"+VidiconType+num);
	 var vehln=jQuery("#vehln").val();
	 var name="";
	 if(num==1){
		 name="整车";
	 }else if(num==2){
		 name="路况";
	 }else if(num==3){
		 name="车门";
	 }else if(num==4){
		 name="司机";
	 }
	 var cookiepath=getcookies('newvideorecord');
	 var path="";
		if(cookiepath==null||cookiepath=="")
		{
			path=m_picPath;
		}else{
			path=cookiepath;
		}
	 var bRet = PlayOCX.StartRecord(vehln , num, path,name);
	 closepicshot();
		if(bRet>=0)
		{
			tishiShow("开始录像！");
			m_hipath[num]=path;
			
		}else if(bRet==-1){
			tishiShow("录像失败,请稍后重新尝试！");
		}else if(bRet==-2){
			tishiShow("控件不在预览状态，无法录像！");
		}else if(bRet==-3){
			tishiShow("控件已在录像状态！");
		}else if(bRet==-4){
			tishiShow("录像文件夹创建失败！");
		}else if(bRet==-5){
			tishiShow("磁盘容量不足！");
		}else{
			tishiShow("录像失败,请稍后重新尝试！");
		} 
 }

 function stoprecordhi(num,id){
	 jQuery("#tishitu").attr('src','../newimages/sidelayerimages/right.gif');
	 var id="#"+id;
	 var cssclass=jQuery(id).attr("class");
	 if(cssclass=="videoStopRecord2"){
		 return 0;
	 }
	 PlayOCX =document.getElementById("PPVS"+VidiconType+num);
	 var bRet=PlayOCX.StopRecord();
	 closepicshot();
	 if(bRet>=0)
		{	
			    tishiShow("停止录像成功！");
				jQuery(id).removeClass();
				jQuery(id).addClass("videoStopRecord2");
				if(num==1){
					jQuery("#ppvsrec1").removeClass();
					jQuery("#ppvsrec1").addClass("videoStartRecord");
					jQuery("#ppvsrec1").attr('title','开始录制');
				}else if(num==2){
					jQuery("#ppvsrec2").removeClass();
					jQuery("#ppvsrec2").addClass("videoStartRecord");
					jQuery("#ppvsrec2").attr('title','开始录制');
				}else if(num==3){
					jQuery("#ppvsrec3").removeClass();
					jQuery("#ppvsrec3").addClass("videoStartRecord");
					jQuery("#ppvsrec3").attr('title','开始录制');
				}else if(num==4){
					jQuery("#ppvsrec4").removeClass();
					jQuery("#ppvsrec4").addClass("videoStartRecord");
					jQuery("#ppvsrec4").attr('title','开始录制');
				}
				
			
		}else if(bRet==-1){
			tishiShow("控件未在预览或没有录像！");
		}else {
			tishiShow("停止录像失败！");
		}
 }

 /*
 * mod by yg 在停止录制视频的提示信息中，增加通道名，录制时长信息
 */
 function menustoprecordhi(num){
	 jQuery("#tishitu").attr('src','../newimages/sidelayerimages/right.gif');
	 PlayOCX =document.getElementById("PPVS"+VidiconType+num);
	 var bRet=PlayOCX.StopRecord();
	 closepicshot();
	 if(bRet>0){	
		 art.dialog(
				   {
				   id:'abcd',
				   title:'提示信息',
				   lock:true,
				   width:300,
				   height:55,
				   fixed:true,
				   content:'<div class="dialogCon"><p><label>【'+waynorecode(num)+'】录制时长  '+formatSeconds(bRet)+' 保存成功'+
				   		   '<br>保存位置:'+
				   		   '<span id="xinxi" style="text-decoration:underline;"></span><a class="browseBtn5" onclick="OpenFolder('+num+')"><span>打开文件夹</span></a></label></p></div>',
				      yesFn: function(){
				      }
				  });
		   var cookiepath=getcookies('newvideorecord');
		   var tempPath="";
		   if( m_hipath[num]!=""){
			   tempPath=m_hipath[num];
			   jQuery('#xinxi').html(setShortPath(tempPath,12)); 
			   jQuery('#xinxi').attr('desc',tempPath);
		   }
		   else if(cookiepath==null||cookiepath=="")
		   {
		    tempPath=setShortPath(m_picPath,15);
		    jQuery('#xinxi').html(tempPath);
		   }else{
			tempPath=setShortPath(cookiepath,15);  
		    jQuery('#xinxi').html(tempPath);
		   }
		   
		   var liveTip = jQuery('<div id="livetip"></div>').hide().appendTo('body');
			jQuery('#xinxi').bind('mouseover mouseout mousemove', function(event) {
				var tipTitle = '';
				if (event.type == 'mouseover') {
					tipTitle = jQuery('#xinxi').attr("desc");
					liveTip.html('<label>' + tipTitle + '</label>').show();
				};
				if (event.type == 'mouseover' || event.type == 'mousemove') {
					liveTip.css({
						top: event.pageY-10,
						left: event.pageX +10
					});
				};

				if (event.type == 'mouseout') {
					liveTip.hide();
				};
			});
		   
			//tishiShow("停止录像成功！");
		}else if(bRet==-1){
			 art.dialog(
					   {
					   id:'abcd',
					   title:'提示信息',
					   lock:true,
					   width:300,
					   height:35,
					   fixed:true,
					      content:'控件未在预览或没有录像！ ',
					      yesFn: function(){
					      }
					  });
			//tishiShow("控件未在预览或没有录像！");
		}else {
			 art.dialog(
					   {
					   id:'abcd',
					   title:'提示信息',
					   lock:true,
					   width:300,
					   height:35,
					   fixed:true,
					      content:'停止录像失败 ！',
					      yesFn: function(){
					       
					      }
					  });
			//tishiShow("停止录像失败！");
		}
 }
 
 	/* add by yg  2013-02-28
 	*  秒转  00:00:00 格式
 	*/
	function formatSeconds(seconds){
		var hh;
		var mm;
		var ss;
		//传入的时间为空或小于0
		if(seconds==null||seconds<0){
			return;
		}
		//得到小时
		hh=seconds/3600|0;
		seconds=parseInt(seconds)-hh*3600;
		if(parseInt(hh)<10){
			hh="0"+hh;
		}
		//得到分
		mm=seconds/60|0;
		//得到秒
		ss=parseInt(seconds)-mm*60;
		if(parseInt(mm)<10){
			mm="0"+mm;    
		}
		if(ss<10){
			ss="0"+ss;      
		}
		return hh+":"+mm+":"+ss;
	}

 function allstop(){
	 if(VidiconType=="HI"){
		 for(var i =1; i <= 4; i ++){
			 stophirecord(i); 
		 }
	 }
 }

 function stophirecord(num){
	 PlayOCX =document.getElementById("PPVS"+VidiconType+num);
	 if(PlayOCX==null || m_conflag_hi[num]<0){
		
	 }else{
		 PlayOCX.StopRecord(); 
	 }
	
 }

 function OpenFolder(num){
	 var ppocx = document.getElementById("PPVSHI1");
	 var cookiepath=getcookies('newvideorecord');
	 if( m_hipath[num]!=""){
		 ppocx.OpenFolder(m_hipath[num]);
	 }else if(cookiepath==null||cookiepath==""){
		   ppocx.OpenFolder(m_picPath);
	 }else{
		   ppocx.OpenFolder(cookiepath);
	 } 
}

function OpenSoundhi(num){
	 jQuery("#tishitu").attr('src','../newimages/sidelayerimages/right.gif');
	 PlayOCX =document.getElementById("PPVS"+VidiconType+num);
	 closepicshot();
	 if( m_onshow[num]==0 )
	    {
		  tishiShow( "请确保正在预览视频！" );
		  return;
		}
	  if(m_opensound[num]=="0"){
		  if(PlayOCX.OpenSound())
		  {
			  m_opensound[num]="1";
		  }else{
			  tishiShow("打开声音失败！");
		  }
	  }else{
		  if(PlayOCX.CloseSound()){
			  m_opensound[num]="0";
		  }else{
			  tishiShow("关闭声音失败！");
		  }
	  }	
}

function downfilm(){
		/** 原有存储路径设置部分
	    closepicshot();
		jQuery("#carmera1").css("display","none");
		jQuery("#carmera2").css("display","");
		var cookiepath=getcookies('newvideorecord');
 		var tempPath="";
 		if(cookiepath==null||cookiepath=="")
 		{
 			jQuery('#orderpath').val(m_picPath);
 		}else{
 			jQuery('#orderpath').val(cookiepath);
 		}
 		jQuery("#Layer1").removeClass();
		jQuery("#Layer1").addClass("LayerVideo2");
		**/
		art.dialog(
			{
			id:'abc',
			title:'保存路径设置',
			lock:true,
			width:300,
			height:50,
			fixed:true,
		    content:'<div class="dialogCon"><p><label>保存路径：</label><input type="text" id="orderpath"  readonly="true" class="wtext small"/><a id="savePathTextBtn" class="browseBtn" onclick="veiw_event()" ><span>浏览</span></a></p></div>',
		    yesFn: function(){
		    	savepath();
		    },
		    noFn: true , 
		    closeFn:function(){
			   // alert("123");
		    }
		});
		var cookiepath=getcookies('newvideorecord');
 		var tempPath="";
 		if(cookiepath==null||cookiepath=="")
 		{
 			jQuery('#orderpath').val(m_picPath);
 		}else{
 			jQuery('#orderpath').val(cookiepath);
 		}
		
}

/*
 * mod by yg 视频控年在选择路径对话框提示传入路径
 */
function veiw_event(){
	var ppocx = document.getElementById("PPVSHI1");
	var path = ppocx.ChangeSaveFilePath(jQuery('#orderpath').val());
	 if(path!=""){
		 jQuery('#orderpath').val(path);
	 }
}

function closedown(){
	closepicshot();
	jQuery("#carmera2").css("display","none");
	jQuery("#carmera1").css("display","");
	jQuery("#Layer1").removeClass();
	jQuery("#Layer1").addClass("LayerVideo");
	jQuery("#tishitu").attr('src','../newimages/sidelayerimages/right.gif');
}

function savepath(){
	var downloadpath=jQuery('#orderpath').val();
	closepicshot();
	if(downloadpath.length<=0){
		jQuery("#tishitu").attr('src','../newimages/sidelayerimages/ico_alarm.png');
		tishiShow("路径不合法！");
		return;
	}else{
		downloadpath=addpath(downloadpath);
		var pathflag=valid_path(downloadpath);
		if(pathflag){
			savecookies('newvideorecord',downloadpath);
		}else{
			jQuery("#tishitu").attr('src','../newimages/sidelayerimages/ico_alarm.png');
			tishiShow('路径不合法！');	
			return;
		}
		
	}
closedown();
}

//不显示截屏、录像、停止等按钮
function alldisplaynone(num){
	jQuery("#ppvstop"+num).css('display','none');
	jQuery("#ppvsrec"+num).css('display','none');
	jQuery("#ppvsscr"+num).css('display','none');
}

//显示截屏、录像、停止等按钮
function alldisplay(num){
	jQuery("#ppvstop"+num).css('display','');
	jQuery("#ppvsrec"+num).css('display','');
	jQuery("#ppvsscr"+num).css('display','');
}

//关闭截屏提示语DIV
function closepicshot(){
	jQuery("#picshot").css('display','none');
}
 //end by chengjin  end
 
 
 /*************************************************
 Function:		SnapShotThisPic
 Description:	获取当前指定窗口图片
 *************************************************/
 function SnapShotThisPic(){
 	if(m_atThisNum==""){
 		tishiShow("请选择需要截图的视频窗口！");
 		//alert("请选择需要截图的视频窗口");
 	}
 	if(m_onshow[Number(m_atThisNum)]=="1"||m_onshow_hs[Number(m_atThisNum)]=="1"){
 		PlayOCX=document.getElementById("PPVS"+VidiconType+m_atThisNum);
 		var tempPath=m_picPath;
 		var DateTime = new  Date();
 		var szDate   = DateTime.getYear()+((DateTime.getMonth()+1)+"").ToStringByZero(2)+(DateTime.getDate()+"").  ToStringByZero(2);
 		var szTime   = DateTime.getHours()+((DateTime.getMinutes()+1)+"").ToStringByZero(2)+(DateTime.getSeconds()+"").ToStringByZero(2);
 		if(VidiconType=="HI"){
 			tempPath=tempPath+m_carNum+"_通道"+m_atThisNum+"_"+szDate+"-"+szTime+".bmp";
 			if(PlayOCX.Snapshot(tempPath))
 			{
 				tishiShow("图片保存为"+tempPath+"！");
 				//alert("图片保存为"+tempPath+"!");
 			}else{
 				tishiShow("图片获取失败！");
 				//alert("图片获取失败!");
 			}
 		}else if(VidiconType=="HS"){
 			tempPath=tempPath+m_carNum+"_通道"+m_atThisNum+"_"+szDate+"-"+szTime+".jpg";
 			var n=PlayOCX.CapturePic(tempPath, 0);
 			if(n=='0'){
 				tishiShow("图片保存为"+tempPath+"！");
 				//alert("图片保存为"+tempPath+"!");
 			}else{
 				tishiShow("图片获取失败！");
 				//alert("图片获取失败!");
 			}
 		}
 	}else{
 		tishiShow("当前窗口无播放内容，无法截图！");
 		//alert("当前窗口无播放内容，无法截图");
 	}
 }
 /**********************************
 Function: 		String.prototype.ToStringByZero(iCount)
 Description: 	判断并填补数字位数
 Input:			iCount：位数
 ***********************************/ 
 function String.prototype.ToStringByZero(iCount)   // 取自lostinet的函数库
 {   
    var  szContent=this;   
    while(szContent.length < iCount) 
    szContent="0"+szContent;   
    return   szContent;   
 }
 

</script><!--



/*************************************************
以下为本安控件的事件 add by phy
SelectWindow:单击-左键选取
MaxWindow：双击-放大窗口(奇数/第一次双击)
FullScreenWindow:双击-取消放大(偶数双击，现在也是放大窗口)
PopMenuCloseRealPlay：右键-关闭当前预览
PopMenuCloseAllRealPlay:右键-关闭所有预览
PopMenuSnapShot:右键-截取当前窗口图像
PopMenuOpenSound:打开播放声音
*************************************************/

单击-左键选取 
-->
<script for=PPVSHI1 event="SelectWindow">
for(var iForm=1; iForm<5; iForm++)
{
	if(iForm==1)
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #00AA00 1px";
	}
	else
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #171C20 1px";
	}
}
m_atThisNum="1";
//reShowButtionGif();
</script>
<script for=PPVSHI2 event="SelectWindow">
for(var iForm=1; iForm<5; iForm++)
{
	if(iForm==2)
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #00AA00 1px";
	}
	else
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #171C20 1px";
	}
}
m_atThisNum="2";
//reShowButtionGif();
</script>
<script for=PPVSHI3 event="SelectWindow">
for(var iForm=1; iForm<5; iForm++)
{
	if(iForm==3)
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #00AA00 1px";
	}
	else
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #171C20 1px";
	}
}
m_atThisNum="3";
//reShowButtionGif();
</script>
<script for=PPVSHI4 event="SelectWindow">
for(var iForm=1; iForm<5; iForm++)
{
	if(iForm==4)
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #00AA00 1px";
	}
	else
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #171C20 1px";
	}
}
m_atThisNum="4";
//reShowButtionGif();
</script><!--
双击-放大窗口(奇数/第一次双击) 
-->
<script for=PPVSHI1 event="MaxWindow">
if(max_show_flag=='0'){
	maxVideoWindow('1');
}else{
	resizeVideoWindow();
}
</script>
<script for=PPVSHI2 event="MaxWindow">
if(max_show_flag=='0'){
	maxVideoWindow('2');
}else{
	resizeVideoWindow();
}
</script>
<script for=PPVSHI3 event="MaxWindow">
if(max_show_flag=='0'){
	maxVideoWindow('3');
}else{
	resizeVideoWindow();
}
</script>
<script for=PPVSHI4 event="MaxWindow">
if(max_show_flag=='0'){
	maxVideoWindow('4');
}else{
	resizeVideoWindow();
}
</script><!--
双击-取消放大(偶数双击，现在也是放大窗口) 
-->
<script for=PPVSHI1 event="FullScreenWindow">
if(max_show_flag=='0'){
	maxVideoWindow('1');
}else{
	resizeVideoWindow();
}
</script>
<script for=PPVSHI2 event="FullScreenWindow">
if(max_show_flag=='0'){
	maxVideoWindow('2');
}else{
	resizeVideoWindow();
}
</script>
<script for=PPVSHI3 event="FullScreenWindow">
if(max_show_flag=='0'){
	maxVideoWindow('3');
}else{
	resizeVideoWindow();
}
</script>
<script for=PPVSHI4 event="FullScreenWindow">
if(max_show_flag=='0'){
	maxVideoWindow('4');
}else{
	resizeVideoWindow();
}
</script>
<!--
右键-关闭当前窗口预览
-->
<script for=PPVSHI1 event="PopMenuCloseRealPlay">
    stophirecord(1);
	StopPlay(1);
	//reShowButtionGif();
</script>
<script for=PPVSHI2 event="PopMenuCloseRealPlay">
    stophirecord(2);
	StopPlay(2);
	//reShowButtionGif();
</script>
<script for=PPVSHI3 event="PopMenuCloseRealPlay">
    stophirecord(3);
	StopPlay(3);
	//reShowButtionGif();
</script>
<script for=PPVSHI4 event="PopMenuCloseRealPlay">
    stophirecord(4);
	StopPlay(4);
	//reShowButtionGif();
</script>
<!--
右键-关闭所有预览 
-->
<script for=PPVSHI1 event="PopMenuCloseAllRealPlay">
allstop();
StopPlayAllWindows();
</script>
<script for=PPVSHI2 event="PopMenuCloseAllRealPlay">
allstop();
StopPlayAllWindows();
</script>
<script for=PPVSHI3 event="PopMenuCloseAllRealPlay">
allstop();
StopPlayAllWindows();
</script>
<script for=PPVSHI4 event="PopMenuCloseAllRealPlay">
allstop();
StopPlayAllWindows();
</script><!--
右键-截取当前窗口图像
-->
<script for=PPVSHI1 event="PopMenuSnapShot">
SnapShotPic(1)
</script>
<script for=PPVSHI2 event="PopMenuSnapShot">
SnapShotPic(2)
</script>
<script for=PPVSHI3 event="PopMenuSnapShot">
SnapShotPic(3)
</script>
<script for=PPVSHI4 event="PopMenuSnapShot">
SnapShotPic(4)
</script>
<!--
右键-打开播放声音
-->
<script for=PPVSHI1 event="PopMenuOpenSound">
OpenSoundhi(1)
</script>
<script for=PPVSHI2 event="PopMenuOpenSound">
OpenSoundhi(2)
</script>
<script for=PPVSHI3 event="PopMenuOpenSound">
OpenSoundhi(3)
</script>
<script for=PPVSHI4 event="PopMenuOpenSound">
OpenSoundhi(4)
</script>
<!--
右键-开始视频录制
-->
<script for=PPVSHI1 event="PopMenuStartRecord">
menustartrecordhi(1)
</script>
<script for=PPVSHI2 event="PopMenuStartRecord">
menustartrecordhi(2)
</script>
<script for=PPVSHI3 event="PopMenuStartRecord">
menustartrecordhi(3)
</script>
<script for=PPVSHI4 event="PopMenuStartRecord">
menustartrecordhi(4)
</script>
<!--
右键-停止视频录制
-->
<script for=PPVSHI1 event="PopMenuStopRecord">
menustoprecordhi(1)
</script>
<script for=PPVSHI2 event="PopMenuStopRecord">
menustoprecordhi(2)
</script>
<script for=PPVSHI3 event="PopMenuStopRecord">
menustoprecordhi(3)
</script>
<script for=PPVSHI4 event="PopMenuStopRecord">
menustoprecordhi(4)
</script>
<!--
右键-设置路径
-->
<script for=PPVSHI1 event="PopMenuSavePath">
downfilm()
</script>
<script for=PPVSHI2 event="PopMenuSavePath">
downfilm()
</script>
<script for=PPVSHI3 event="PopMenuSavePath">
downfilm()
</script>
<script for=PPVSHI4 event="PopMenuSavePath">
downfilm()
</script>

<!--
事件-停止录像
-->
<script for=PPVSHI1 event="EventStopRecord">
menustoprecordhi(1)
</script>
<script for=PPVSHI2 event="EventStopRecord">
menustoprecordhi(2)
</script>
<script for=PPVSHI3 event="EventStopRecord">
menustoprecordhi(3)
</script>
<script for=PPVSHI4 event="EventStopRecord">
menustoprecordhi(4)
</script>


<!--
/*************************************************
以下为航盛控件的事件 add by phy
OnClick(x,y):单击-左键选取
OnDoubleClick(x,y)：双击-放大窗口
*************************************************/
-->
<script language="javascript" type="text/javascript" for="PPVSHS1" event="OnClick(x,y)">
for(var iForm=1; iForm<5; iForm++)
{
	if(iForm==1)
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #48abfc 2px";
	}
	else
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #171C20 2px";
	}
}
m_atThisNum="1";
reShowButtionGif();
</script>
<script language="javascript" type="text/javascript" for="PPVSHS2" event="OnClick(x,y)">
for(var iForm=1; iForm<5; iForm++)
{
	if(iForm==2)
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #48abfc 2px";
	}
	else
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #171C20 2px";
	}
}
m_atThisNum="2";
reShowButtionGif();
</script>
<script language="javascript" type="text/javascript" for="PPVSHS3" event="OnClick(x,y)">
for(var iForm=1; iForm<5; iForm++)
{
	if(iForm==3)
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #48abfc 2px";
	}
	else
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #171C20 2px";
	}
}
m_atThisNum="3";
reShowButtionGif();
</script>
<script language="javascript" type="text/javascript" for="PPVSHS4" event="OnClick(x,y)">
for(var iForm=1; iForm<5; iForm++)
{
	if(iForm==4)
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #48abfc 2px";
	}
	else
	{
		document.getElementById("table"+VidiconType+iForm).style.border="solid #171C20 2px";
	}
}
m_atThisNum="4";
reShowButtionGif();
</script>
<script language="javascript" type="text/javascript" for="PPVSHS1" event="OnDoubleClick(x,y)">
if(max_show_flag=='0'){
	maxVideoWindow('1');
}else{
	resizeVideoWindow();
}
</script>
<script language="javascript" type="text/javascript" for="PPVSHS2" event="OnDoubleClick(x,y)">
if(max_show_flag=='0'){
	maxVideoWindow('2');
}else{
	resizeVideoWindow();
}
</script>
<script language="javascript" type="text/javascript" for="PPVSHS3" event="OnDoubleClick(x,y)">
if(max_show_flag=='0'){
	maxVideoWindow('3');
}else{
	resizeVideoWindow();
}
</script>
<script language="javascript" type="text/javascript" for="PPVSHS4" event="OnDoubleClick(x,y)">
if(max_show_flag=='0'){
	maxVideoWindow('4');
}else{
	resizeVideoWindow();
}
</script>

<script language="javascript" for="ocx" event="OnCloseCurrVideo()">
     ocx.CloseVideo(ocx.GetSelWndIndex());	
</script>

<script language="javascript" for="ocx" event="OnSnap()">

	var tempPath="D:\\SnapShotPic\\";
	// 日期当文件名，注：文件名不支持中间出现":"符号，故用"_"、"-"符号分割的时间
	var DateTime = new  Date();
	var szDate   = DateTime.getYear()+((DateTime.getMonth()+1)+"").ToStringByZero(2)+(DateTime.getDate()+"").  ToStringByZero(2);
	var szTime   = DateTime.getHours()+((DateTime.getMinutes()+1)+"").ToStringByZero(2)+(DateTime.getSeconds()+"").ToStringByZero(2);

	tempPath=tempPath+m_carNum+"_通道"+(ocx.GetSelWndIndex()+1)+"_"+szDate+"-"+szTime+".bmp";
	
	
    var val = ocx.CapturePicture(tempPath);
	if(val ==0){
		tishiShow("图片保存为"+tempPath+"！");
		//alert("图片保存为"+tempPath+"!");	
	}
	else{
		tishiShow("图片保存失败！");
		//alert("图片保存失败!");
	}

    	
</script>

<script language="javascript" for="ocx" event="OnCloseAllVideo()">
     ocx.CloseVideo(0);	
     ocx.CloseVideo(1);	
     ocx.CloseVideo(2);	
     ocx.CloseVideo(3);	
</script>
<script language="javascript" for="ocx" event="OnMessage(lType,lResult,szDescription)">
		switch(lType){
		case 1:
			//$('loginbody').style.display="none";
			//$('mpage').style.top="0";
			tishiShow(szDescription+"！");
			//alert("1."+szDescription);
			break;	
		case 0:
			//$('loginbody').style.display="block";
			//$('mpage').style.top="-10000px";
			//add by suyingtao 20120615 修正现网视频无法连接 
			if(lResult == 0){
				openDAVideo();
			}else{
				tishiShow(szDescription+"！");
				//alert("0."+szDescription);
			}			
			//alert(szDescription+"0");
			break;
		case 2:
			//$('loginbody').style.display="block";
			//$('mpage').style.top="-10000px";
			tishiShow(szDescription+"！");
			//alert("2."+szDescription);
			break;
		}

</script>

<script language="javascript" for="ocxyw" event="OnConnectMediaServerOver(bIsSuccess,nErrCode)">
 //有为控件的登陆事件通知
		if(bIsSuccess){
			openYWVideo();
		}else{
			if (nErrCode == 1)
			{
				tishiShow('用户名或密码错误!');
			}
					 
			 if(nErrCode==2){
				 tishiShow('连接超时');
		     }
		}
</script>


<script type="text/javascript">
//提示信息隐藏
var ral_resultShowflash= 2000;
	//关闭视频监控
	function closeMapDiv() {
		allstop();
		StopPlayAllWindows();
		//addclosespeed();
		
	}

	function setonunload(){
		StopPlayAllWindows();
		addclosespeed();
	}

	function addclosespeed(){
		<s:if test="terminalViBean.VIDEO_FACTORY=='HI'">
		  document.getElementById("videoCenter").innerHTML = "";
		  document.getElementById("PPVSHI1")=null;
		  document.getElementById("PPVSHI2")=null;
		  document.getElementById("PPVSHI3")=null;
		  document.getElementById("PPVSHI4")=null;
		</s:if>
	}
    
	//关闭视频
	function closeVideoDiv(div) {
		//关闭所有视频
		StopPlayAllWindows();
	}

	function returnShowFactoryDiv_callback(date){
		var TerminalViBean = date[0];
		var vin = TerminalViBean.VEHICLE_VIN;
		//if(TerminalViBean.color =="b" && TerminalViBean.VEH_EXT_INFO  == 1){
		if(TerminalViBean.dvrState  == 1){//只判断3G状态位
			VidiconType = document.getElementById('VIDEO_FACTORY').value;
			//终端视频ID
			m_vidiconId = document.getElementById("VIDEO_ID").value;
			//视频IPPORT
			var ipport =document.getElementById('VIDEO_SERVER_IP').value;
			var ss = ipport.split(":");
			//用户名
			var user =document.getElementById('VIDEO_USER').value;
			//密码
			var passwd =document.getElementById('VIDEO_PASSWORD').value;

			//alert(VidiconType);
			if (VidiconType == "HI") {
				//document.getElementById("ocxHI").style.display="";
				//document.getElementById("ocxDA").innerHTML = "";
				m_szRegIP = ss[0];
				m_iRegPort = ss[1];
				var sipport = document.getElementById('STREAM_SERVER_IP').value;
				if(sipport != null && sipport != ""){
					var sss = sipport.split(":");
					m_szRsmIP = sss[0];
					m_iRsmPort = sss[1];
				}
				
				m_user = user;
				m_password = passwd;
			} else if (VidiconType == "DA") {
				//document.getElementById("ocxDA").style.display="";
				//document.getElementById("ocxHI").innerHTML = "";
				m_szRegIP = ss[0];
				m_iRegPort = ss[1];
				m_user = user;
				m_password = passwd;
			}else if (VidiconType == "YW") {
				//document.getElementById("ocxDA").style.display="";
				//document.getElementById("ocxHI").innerHTML = "";
				m_szRegIP = ss[0];
				m_iRegPort = ss[1];
				m_user = user;
				m_password = passwd;
			}
		}else{
			PlayOCX=document.getElementById("PPVS"+VidiconType+1);
			PlayOCX.SetPlayWndText("整车监控...");
			PlayOCX=document.getElementById("PPVS"+VidiconType+2);
			PlayOCX.SetPlayWndText("路况监控...");
			PlayOCX=document.getElementById("PPVS"+VidiconType+3);
			PlayOCX.SetPlayWndText("车门监控...");
			PlayOCX=document.getElementById("PPVS"+VidiconType+4);
			PlayOCX.SetPlayWndText("司机监控...");
			tishiShow("3G视频不在线，请稍后再尝试视频监控！");
			//alert("车辆已熄火，请稍后再尝试车辆拍照！");
		}
	}
	
	
	function showFactoryDiv() {
		var vin = document.getElementById("vin").value;	
		GPSDwr.returnTipInfo(vin,returnShowFactoryDiv_callback);
	}


	//拍照
	function postPhoto(factype){

		if(factype=="HI")
		{
			closepicshot();
		}
		var vin = document.getElementById("vin").value;
		var userid = document.getElementById('optionUserid').value;
		var routeway2 = document.all.routeway2;
		var wayNo = "";
//alert(vin+","+userid);
		if(routeway2.length > 1){
			for(var j =0; j < routeway2.length; j++){
				if(routeway2[j].checked){
					wayNo += routeway2[j].value+",";
				}
			}
		}
		else{
			if(routeway2.checked){
				wayNo += routeway2.value+",";
			}
		}

		if(wayNo != ""){
			GPSDwr.postSendPhoto(vin,wayNo,1,5,userid,126,65,65,126,postPhoto_CallBack);
		}
		else{
			tishiShow("请选择拍照通道！");
			//hideshowresultDiv(0);
			//document.getElementById("inforeault").innerHTML="请选择拍照通道！";
			//window.setTimeout("hideshowresultDiv(1)",ral_resultShowflash);
			
		}

		function postPhoto_CallBack(data){

			//var str = data;
			var result = "";
			var sccess = "";
			var error  = "";
			var wait = "";

			if(data == "8000"){
				tishiShow("终端不在线 ，请稍后再试！");
				//hideshowresultDiv(0);
				//document.getElementById("inforeault").innerHTML="终端不在线 ，请稍后再试！";
			}
			else if(data == "8001"){
				tishiShow("车辆已熄火，请稍后再尝试车辆拍照！");
				//hideshowresultDiv(0);
				//document.getElementById("inforeault").innerHTML="车辆已熄火，请稍后再尝试车辆拍照！";
			}else{
				var strs = data.split(",");
				if(strs.length >0){

					for(var j =0 ; j < strs.length; j++){

						if(strs[j] != null && strs[j] != "" && strs[j] != "undefined"){

							var s = strs[j].split("-");
							//alert(s[1]);
							if(s[1]==0){
								sccess += waynorecode(s[0])+"，";
							}
							else if(s[1]==611){
								wait += waynorecode(s[0])+"，";
							}
							else {
								error += waynorecode(s[0])+"，";
									
							}
						}
						
					}
					if(sccess != ""){
						result += sccess+"拍照指令已下发！";
					}

					if(error != ""){
						result += error+"拍照指令下发失败！";
					}

					if(wait != ""){
						result += wait+"拍照延迟请等待！";
					}
					tishiShow(result);
				}
			}
		}
	}
	
	function hideshowresultDiv(flag){
		//alert(111);
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
			return "司机";
		}else if(no == 1){
			return "整车";
		}else if(no == 3){
			return "车门";
		}else if(no == 2){
			return "路况";
		}
	}

	function photoOnline(){
		var vin = document.getElementById("vin").value;	
		GPSDwr.returnTipInfo(vin,returnTipInfo_callback);
	}
	function returnTipInfo_callback(date){
		var TerminalViBean = date[0];
		var vin = TerminalViBean.VEHICLE_VIN;
		//if(TerminalViBean.color =="b" && TerminalViBean.VEH_EXT_INFO  == 1){
		if(TerminalViBean.dvrState  == 1){//只判断3G状态位

		}
		else{
			tishiShow("3G视频不在线，请稍后再尝试视频监控！");
			//alert("车辆已熄火，请稍后再尝试车辆拍照！");
		}
		
		
	}
</script>