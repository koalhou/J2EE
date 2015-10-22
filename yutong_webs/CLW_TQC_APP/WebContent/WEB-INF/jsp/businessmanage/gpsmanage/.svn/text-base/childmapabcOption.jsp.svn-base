<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/md5/base64.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>

<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/flexigrid/jquery.wresize.js'/>"></script>
<script>
//localhost key: f03346eb3a99be025979045e8fa1a281c515961134a26532ef6e3b523f5069353995b80a03a330ad
//本机ip key：d979de456a3d333c47a35717730b869ea89f76f91332f6ffb828669e72b4deab2667017a0546a40e
// 测试服务器 key：1017658fa4ddaf28b5656e240781fa7240e388998bcdeb98ddae0f0d55161cc3b71e7de953e9cb22
var flushMap = 15000;
var flushAlart= 15000;
var intalerm = 0;//window.setInterval("alarmTableInit()",flushAlart);
var intOneinit = 0;//window.setInterval("reallookvreal()",flushMap);

var tipOpenVin="";
var tipIsOpen=false;
var funcClose=false;
var tabflag="";

function closeMapDiv(){
	var div = document.getElementById('selectline');
	div.style.display="none";
	var divmap = document.getElementById('iCenter');
	divmap.style.height="370px";
	document.getElementById('hiddenVin').value="";
	
}
function showMapDiv(vin){
	var div = document.getElementById('selectline');
	div.style.display="";
	var divmap = document.getElementById('iCenter');
	//divmap.style.width="900px";
	divmap.style.height="320px";
	document.getElementById('hiddenVin').value=vin;
}

// 加载地图对象
var mapObj = null; 
// 自适应zoom数组
var arrForFitView=new Array();
//保存当前列表中的点数据
var tablePointList = new Array();

// 加载地图
function mapInit(divid) {   
	
    var mapOptions = new MMapOptions();//构建地图辅助类   
    mapOptions.zoom=4;//设置地图zoom级别   
    mapOptions.center=new MLngLat(116.397428,39.90923);   //设置地图中心点   
    mapOptions.hasDefaultMenu=false;
    mapOptions.toolbar = DEFAULT;//工具条   
    mapOptions.toolbarPos = new MPoint(5,15);  //工具条   
    mapOptions.overviewMap = MINIMIZE;//是否显示鹰眼   
    mapOptions.logoUrl = " ";
    mapOptions.scale = SHOW;//是否显示比例尺   
    mapOptions.returnCoordType = COORD_TYPE_OFFSET;//返回数字坐标   
    mapOptions.zoomBox = true;//鼠标滚轮缩放和双击放大时是否有红框动画效果。   
    mapObj=new MMap(divid,mapOptions); //地图初始   

    mapObj.setKeyboardEnabled(false);

    //var div = draw();
   
   //mapObj.addControl(div);
   var div2 = draw2();
   mapObj.addControl(div2);


   initPointInfo();

   //alarmTableInit();
   changeChoose("tab4");
   tabcount();
   
   mapObj.addEventListener(mapObj,TIP_OPEN,openTip0_Open); 
   
   mapObj.addEventListener(mapObj,TIP_CLOSE,openTip_Close); 
   

   //intalerm = window.setInterval("autoflushtab()",flushAlart);
   intOneinit = window.setInterval("initPointInfo()",flushMap);
}   

function openTip0_Open(param){

	tipIsOpen = true;
	tipOpenVin=param.overlayId;

	
}
function openTip_Close(param){

	tipIsOpen = false;
	if(!funcClose){
		tipOpenVin="";
	}
}


//加载车辆坐标值
function initPointInfo(){
	

	var userid = document.getElementById("optionUserid").value;
	GPSDwr.getChildGpsInfo(userid,_getPoint);
	
	function _getPoint(data){
		
		mapObj.removeAllOverlays();
		if(data != null && data.length > 0){
			//alert(data.length);
			//alert(data[0].STU_NAME);
			addmarkerObjNew(data);
			boundshowpoint();
			 
			 
		}
		 
	}
	
	
}

//根据点的分布范围，显示对应的缩放比例
function boundshowpoint(){
	//setLngLatBounds(bounds)
	var points = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);

	if(points.length != 0){
		var maxx = points[0].lnglat.lngX;
		var maxy = points[0].lnglat.latY;
		var minx = points[0].lnglat.lngX;
		var miny = points[0].lnglat.latY;

		for(var i = 1; i < points.length; i++){
			if(points[i].lnglat.lngX >= maxx){
				maxx = points[i].lnglat.lngX;
			}
			else if(points[i].lnglat.lngX <= minx){
				minx = points[i].lnglat.lngX;
			}

			if(points[i].lnglat.latY >= maxy){
				maxy = points[i].lnglat.latY;
			}
			else if(points[i].lnglat.latY <= miny){
				miny = points[i].lnglat.latY;
			}
			
		}
		//alert(maxx +","+maxy+";"+minx+";"+miny);

		mapObj.setLngLatBounds(new MLngLatBounds(new MLngLat(minx,miny),new MLngLat(maxx,maxy))); 
	}

	

	
}
function addmarkerObjNew(array){
	 
	   if(array != null && array.length > 0){
		   
			   var arr = new Array();
			   for(var i=0; i < array.length;i++){
		
						   var lon = "";
						   var lat = "";
						   //alert(array[i].VSS_FLAG);
					if(array[i].VSS_FLAG ==0){
						lon = array[i].LONGITUDE;
						lat = array[i].LATITUDE;
					}
					else if(array[i].VSS_FLAG ==1){
						lon = array[i].ST_LONGITUDE;
						lat = array[i].ST_LATITUDE;
					} 
					if(array[i].VSS_FLAG ==2){
						//alert("");
						return;
					}
					   
					
					   if(lon >=0 && lon <=180 && lat>=0 && lat <=90){
			
						//点的属性设置
						var markerOption = new MMarkerOptions(); 
		
						  
						  if(array[i].color=="b"){
							  markerOption.imageUrl="../images/arrow_blue_bak.gif";
						  }
						  else if(array[i].color=="r"){
							  markerOption.imageUrl="../images/arrow_red_bak.gif";
						  }
						  else{
							  markerOption.imageUrl="../images/arrow_gray_bak.gif";
						  }
						   
						   
						   markerOption.imageAlign=MIDDLE_CENTER;
						   var hudu = 0;
						   
							if(array[i].DIRECTION!="FFFF" && array[i].DIRECTION != null){
								   hudu = array[i].DIRECTION;
							}
						   
						   markerOption.rotation = hudu;
						   
						   markerOption.labelOption=addMarkerLabel(array[i].STU_NAME,TOP_CENTER);
						      
						   //markerOption.tipOption = addFlashTip(array[i]); 
						   markerOption.canShowTip= true;
						
							   var marker = new MMarker(new MLngLat(lon,lat),markerOption);
									 //alert(array[i].VEHICLE_VIN+","+array[i].STU_ID);
									 var markid = array[i].VEHICLE_VIN+","+array[i].STU_ID;
							   marker.id=markid;
				
							  arr.push(marker);
							  
						   }
			   }
			   
			  
			   if(arr.length>0){
				   mapObj.addOverlays(arr,true);
			   }
			   
			   var mks = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);

			   if(mks.length>0){
					for(var i = 0; i < mks.length; i++){
						mapObj.addEventListener(mks[i],MOUSE_CLICK,mouse_click);
					}
			   }
	   }
	   else{
			alert("没有坐标数据,无法标点!");
	   }
	
	
}

function mouse_click(event){
	//alert(event.overlayId);
	
	var mkid = event.overlayId;
	//tipOpenVin = mkid;
	GPSDwr.returnChildTipInfo(mkid,returnTipInfo_callback);
	
}

function returnTipInfo_callback(date){
	
	var TerminalViBean = date[0];
	var vin = TerminalViBean.VEHICLE_VIN+","+TerminalViBean.STU_ID;
	//alert(vin);
	var mk = mapObj.getOverlayById(vin);
	if(mk != null){
		mk.option.tipOption = addFlashTip(TerminalViBean);
		mk.option.canShowTip=true;
		
		mapObj.updateOverlay(mk);
		mapObj.openOverlayTip(vin);
	}
	
	
}
//添加点的文字样式
function addMarkerLabel(pointname,direction){   
    
    var fontstyle=new MFontStyle();//创建字体风格对象   
    fontstyle.name="";//设置字体名称，默认为宋体   
    fontstyle.size=14;//设置字体大小，默认为12   
    fontstyle.color=0xFFFFFF;//设置字体的颜色，默认为0x000d46(黑色)   
    fontstyle.bold=false;//设置字体是否为粗体，true，是，fasle，否（默认）   
    var labeloption=new MLabelOptions();//添加标注   
    labeloption.fontStyle=fontstyle;//设置标注的字体样式   
    labeloption.alpha=0.8;//设置标背景及边框的透明度，默认为1，及不透明   
    labeloption.hasBackground=true;//设置标注是否有背景，默认为false，即没有背景   
    labeloption.hasBorder=true;//设置标注背景是否有边框，默认为false，即没有边框   
    labeloption.backgroundColor=0x000000;//设置标注的背景颜色   
    labeloption.borderColor=0x000088;//设置标注的边框颜色   
    labeloption.content=pointname;//标注的显示内容   
   //设置标注左上角相对于面对象中心的锚点。标注左上角与面对象中心重合时，像素坐标原点(0,0)   
    labeloption.labelPosition=new MPoint(0,18);   
    //设置对准点正下方的文字标签锚点   
    labeloption.labelAlign=direction;//TOP_CENTER;   
  
   return  labeloption;
}

//添加气球提示信息
function addFlashTip(TerminalViBean){ 
	//var seldiv = document.getElementById('selectline');
	    var tipOption=new MTipOptions();   
	    tipOption.tipType=HTML_CUSTOM_TIP;
	      
	    tipOption.content=gethtml(TerminalViBean);   
	       
	    tipOption.tipAlign=BOTTOM_CENTER;   
        tipOption.tipPosition=new MPoint(0,-15);   
	      

	    return tipOption;
} 

//构建气球框内容
function gethtml(TerminalViBean){
	//alert(TerminalViBean.TerminalViBean.DIRECTION);
	var httpinfo = "";//"<div  style='width:240px; height:140px;top:8px;left:6px;'>";
	    httpinfo +=	"<div class='balloon'>";
	    httpinfo +=	"<div class='balloonLeft'></div>";
	    httpinfo +=	"<div class='balloonMain'>";
	    httpinfo +=	"<div class='balloonTil'>";
	    httpinfo +=	"<a id='BalloonTab1' href='#' class='tabfocus' onclick='BalloonConsole(1)'>实时监控</a>";
	    httpinfo +=	"<a id='BalloonTab2' href='#' class='tab' onclick='BalloonConsole(2)'>相关信息</a>";
	    httpinfo +=	"<a href='#' class='close' onclick='mapObj.closeTip()'></a>";
	    httpinfo +=	"</div>";
	    httpinfo +=	"<div id='BalloonInfo1' class='data' style='z-index:1'>";
	    httpinfo +=	"<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
	    httpinfo +=	"<tr>";
	    httpinfo +=	"<td width='20%' height='24'>姓名：</td>";
	    httpinfo +=	"<td width='30%'><strong>"+(TerminalViBean.STU_NAME==null?'':TerminalViBean.STU_NAME)+"</strong></td>";
	    httpinfo +=	"<td width='20%'>车牌号：</td>";
	    httpinfo +=	"<td width='30%'><strong>"+(TerminalViBean.VEHICLE_LN==null?'':TerminalViBean.VEHICLE_LN)+"</strong></td>";
	    httpinfo +=	"</tr>";
	    httpinfo +=	"<tr>";   
	    httpinfo +=	"<td height='24'>上车站点：</td>";       
	    httpinfo +=	"<td><strong><label title='"+(TerminalViBean.UP_SITE_ID==null?'':TerminalViBean.UP_SITE_ID)+"'>"+(((TerminalViBean.UP_SITE_ID==null?'':TerminalViBean.UP_SITE_ID).length>7)?((TerminalViBean.UP_SITE_ID==null?'':TerminalViBean.UP_SITE_ID).substring(0,7)):(TerminalViBean.UP_SITE_ID==null?'':TerminalViBean.UP_SITE_ID))+"</label></strong></td>";
	    //httpinfo +=	"<td><strong>"+(TerminalViBean.VSS_FLAG==1?'已下车':'已上车')+"</strong></td>";     
	    httpinfo +=	"<td>时间：</td>";
	    httpinfo +=	"<td><strong>"+ timeoptval(TerminalViBean.UP_SITE_TIME) +"</strong></td>"; 
	    //httpinfo +=	"<td><strong>"+ nullToZore(TerminalViBean.STU_NUM)+"/"+nullToZore(TerminalViBean.LIMITE_NUMBER)+"(人)</strong></td>"; 
	    httpinfo +=	"</tr>"; 
	    httpinfo +=	"<tr>";
	    httpinfo +=	"<td height='24'>下车站点：</td>";
	    httpinfo +=	"<td ><strong><label title='"+(TerminalViBean.DOWN_SITE_ID==null?'':TerminalViBean.DOWN_SITE_ID)+"'>"+(((TerminalViBean.DOWN_SITE_ID==null?'':TerminalViBean.DOWN_SITE_ID).length>7)?((TerminalViBean.DOWN_SITE_ID==null?'':TerminalViBean.DOWN_SITE_ID).substring(0,7)):(TerminalViBean.DOWN_SITE_ID==null?'':TerminalViBean.DOWN_SITE_ID))+"</label></strong></td>";    
	    httpinfo +=	"<td>时间：</td>";
	    httpinfo +=	"<td><strong>"+ timeoptval(TerminalViBean.DOWN_SITE_TIME) +"</strong></td>";     
	    httpinfo +=	"</tr>";
	    httpinfo +=	"<tr>";
	    httpinfo +=	"<td height='24'></td>";
	    httpinfo +=	"<td colspan='3'></td>";        
	    httpinfo +=	"</tr>";        
	    httpinfo +=	"</table>";        
	    httpinfo +=	"</div>";
	    httpinfo +=	"<div id='BalloonInfo2' class='data' style='display:none;z-index:2'>";
	    httpinfo +=	"<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
	    httpinfo +=	"<tr>";  
	    httpinfo +=	"<td height='24'>学校班级：</td>";    
	    httpinfo +=	"<td colspan='3'><strong>"+(TerminalViBean.STU_SCHOOL==null?'':TerminalViBean.STU_SCHOOL)+(TerminalViBean.STU_CLASS==null?'':TerminalViBean.STU_CLASS)+"</strong></td>";     
	    httpinfo +=	"</tr>";      
	    httpinfo +=	"<tr>";  
	    httpinfo +=	"<td width='20%' height='24'>驾驶员：</td>";       
	    httpinfo +=	"<td width='30%'><strong>"+(TerminalViBean.DRIVER_NAME==null?'':TerminalViBean.DRIVER_NAME)+"</strong></td>";   
	    httpinfo +=	"<td width='20%'>车牌号：</td>";    
	    httpinfo +=	"<td><strong>"+TerminalViBean.VEHICLE_LN+"</strong></td>";         
	    httpinfo +=	"</tr>";      
	    httpinfo +=	"<tr>";    
	    httpinfo +=	"<td height='24'>车辆颜色：</td>";    
	    httpinfo +=	"<td><strong>"+(TerminalViBean.VEHICLE_COLOR==null?'':TerminalViBean.VEHICLE_COLOR)+"</strong></td>";      
	    httpinfo +=	"<td height='24'>线路名称：</td>";      
	    httpinfo +=	"<td><strong>"+(TerminalViBean.ROUTE_NAME==null?'':TerminalViBean.ROUTE_NAME)+"</strong></td>";    
	    httpinfo +=	"</tr>";     
	    httpinfo +=	"<tr>";
	    httpinfo +=	"<td height='24'></td>";
	    httpinfo +=	"<td colspan='3'></td>";        
	    httpinfo +=	"</tr>";   
	    httpinfo +=	"</table>";      
	    httpinfo +=	"</div>";    
	    httpinfo +=	"<div class='toolbarqp'>";        
	    httpinfo +=	"<a class='tool1' title='轨迹回放' onclick=\"openIframeDivLine(2,'";
	    httpinfo += TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.STU_ID+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID;
	    httpinfo +="')\"></a>";      
	    /*httpinfo +=	"<a class='tool2' title='重点监控' onclick=\"callrealtimelook('";
	    httpinfo += TerminalViBean.VEHICLE_VIN;
	    httpinfo +="')\"></a>";      
	    httpinfo +=	"<a class='tool3' title='视频监控' onclick=\"video_monitor('";
	    httpinfo += TerminalViBean.VEHICLE_VIN;
	    httpinfo +="')\"></a>";        
	    httpinfo +=	"<a class='tool4' title='车辆拍照' onclick=\"openIframeDivPhoto('";
	    httpinfo += TerminalViBean.VEHICLE_VIN;
	    httpinfo +="')\"></a>";        
	    httpinfo +=	"<a class='tool5' title='信息调度' onclick=\"callsendMsg('";
	    httpinfo += TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.VEHICLE_LN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE;
	    httpinfo +="')\"></a>";      
	    httpinfo +=	"<a class='tool6' title='语音监听' onclick=\"Audio_monitor('";
	    httpinfo += TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.VEHICLE_LN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE;
	    httpinfo +="')\"></a>";          
	    httpinfo +=	"<a class='tool7' title='周边服务' onclick=\"callsevicepointlook('";
	    httpinfo += TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE;
	    httpinfo +="')\"></a>"; */ 
	    httpinfo +=	"</div>";  
	    httpinfo +=	"<div class='arrow'></div>";    
	    httpinfo +=	"</div>";    
	    httpinfo +=	"<div class='balloonRight'></div>";    
	    httpinfo +=	"</div>";   
	    
	  
	return httpinfo;
	
}

function openIframeDivLine(flag,vin,stuid,lon,lat,routeid){
	//alert("lon:"+lon+",lat:"+lat);
	art.dialog.open("<s:url value='/gps/childbitlookAction.shtml' />?vin="+vin+"&stu_id="+stuid+"&lookflag="+flag+"&lon="+lon+"&lat="+lat+"&route_id="+routeid+"&rad="+Math.random(),
			//userupdatetreeAction.shtml?userID=" + userid,
			{
				title: '轨迹回放',
				skin: 'aero',
				limit: true,
				lock: true,
				drag: true,
				fixed: false,
				width :905,
				height:445
				/*yesFn: function(iframeWin, topWin){
		        	//对话框iframeWin中对象
		        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
		        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
		        	//当前页面中对象
		            var topOrgName =  window.document.getElementById('organizationName');
		            var topOrgID = window.document.getElementById('organizationID');
		            //赋值
		        if (topOrgName) topOrgName.value = orgNameValue;
		        if (topOrgID) topOrgID.value = orgIDValue;
		    }*/
			});
}

function BalloonConsole(f){
	BalloonTabFlag = f;
	var BalloonTab1 = document.getElementById("BalloonTab1");
	var BalloonTab2 = document.getElementById("BalloonTab2");
	var BalloonInfo1 = document.getElementById("BalloonInfo1");
	var BalloonInfo2 = document.getElementById("BalloonInfo2");
	if(f==1){
		BalloonTab1.className="tabfocus";
		BalloonTab2.className="tab";
		//BalloonInfo1.style.visibility="visible";
		//BalloonInfo1.style.z-index="1";
		BalloonInfo1.style.display="";
		//BalloonInfo2.style.visibility="hidden";
		//BalloonInfo2.style.z-index="2";
		BalloonInfo2.style.display="none";
		
	}
	else if(f==2){
		BalloonTab1.className="tab";
		BalloonTab2.className="tabfocus";
		//BalloonInfo1.style.visibility="visible";
		BalloonInfo1.style.display="none";
		//BalloonInfo2.style.visibility="hidden";
		BalloonInfo2.style.display="";
	}
}

function nullToZore(str){
	if(str == null || str == "" ||  str == "undefined" || str == " "||str =="FFFF"){
		return 0;
	}
	else{
		return str;
	}
}

function timeoptval(time){
	if(time != null && time.trim() != "" && time.trim() != "undefined"){
		return time.substring(0, time.length-2);
	}
	else{
		return "0";
	}
}

function draw2() {
	var mapdiv = document.getElementById("MapbarDivNO");
	
	mapdiv.style.bottom = "0px";
	mapdiv.style.left = "100px";
	mapdiv.style.zIndex = "1005";
	mapdiv.unselectable = "on";
	mapdiv.style.position = "absolute";
	return mapdiv;
	
}

/**
* 选择TAB页
*/
function changeChoose(id){
	
	if(id=="tab4"){
		
		//document.getElementById("tab4").className="current2";
		tabflag="73,74,79,80";
		//jQuery("#alermtab").parent().parent().hide();
		//jQuery('#gala2').parent().parent().show();
		if(jQuery("#gala2").parent().parent().attr('class').indexOf("flexigrid")>=0){
			searchTabList();
		}else{
			stalarmload();
		}				
	}
}

function tabcount(){
	var usrorgid=document.getElementById("Userorganizationid").value;
	GPSDwr.getChildTabCount(usrorgid,getTabcount_CallBack);
}
function getTabcount_CallBack(data){
	//var strs = data.split(",");
	//var tab1count=document.getElementById("tab1count");
	//tab1count.innerHTML="("+strs[0]+")";
	//var tab2count=document.getElementById("tab2count");
	//tab2count.innerHTML="("+strs[1]+")";
	//var tab3count=document.getElementById("tab3count");
	//tab3count.innerHTML="("+strs[2]+")";
	var tab4count=document.getElementById("tab4count");
	tab4count.innerHTML="("+data+")";
}

function searchTabList() {
	   if(tabflag=="73,74,79,80")
	    {
		   jQuery('#gala2').flexOptions({
		    	newp: 1,
		    	params: [
		    	{ name: 'alarmtypeid', value: tabflag}]
		    		});
		    		jQuery('#gala2').flexReload();		 	
		 } else{
	    	jQuery('#alermtab').flexOptions({
	    	newp: 1,
	    	params: [
	    	{ name: 'alarmtypeid', value: tabflag}]
	    		});
	    		jQuery('#alermtab').flexReload();		 	
	    	} 
	}

/**
* chengjin
*加载学生违规告警
*/
function stalarmload(){
    var userid = document.getElementById("optionUserid").value;
	
	var gala2 = jQuery('#gala2');
	 gala2.flexigrid({
	  url: '<s:url value='/AlermGrid/getChildCheckInfo.shtml' />?userid='+userid,  //加载表格走的action
	  dataType: 'json',    //json格式
	  params: [{name: 'alarmtypeid', value:tabflag }],
	  height:90,
	  width:850,
	  colModel : [  {display: ''        , name : '',width : 20, sortable : false ,align: 'center' ,process: reWritephoto},  
	  		        {display: '车牌号',name : 'vehicle_ln', width : 60, sortable : false, align: 'center'},
					{display: '站点名称', name : 'site_name', width : 65, sortable : false, align: 'center',hide:true},
					{display: '学生卡号', name : 'stu_card_id', width : 80, sortable : false, align: 'center'},
					{display: '告警类型', name : 'alarm_type_name', width : 100, sortable : false, align: 'center'},			        		        
			        {display: '告警描述', name : 'alarm_type_comments', width : 120, sortable :false, align: 'center'},
			        {display: '状态', name : 'operate_flag', width : 80, sortable : false, align: 'center',process: reWriteoperate_flag},
			        {display: '告警时间', name : 'terminal_time', width : 120, sortable : false, align: 'center'},
			        {display: '处理', name : '', width : 80, sortable : false, align: 'center',process: reWritestuLink,hide:true},
			        {display: '经度' ,name : 'longitude',width : 80,hide:true},
			        {display: '纬度' ,name : 'latitude',width : 80,hide:true},
			        {display: 'VIN' ,name : 'vehicle_vin',width : 80,hide:true},
			        {display: 'flag' ,name : 'operate_flag',width : 80,hide:true}
	        ],
	       sortname: 'terminal_time', //首次加载的排序字段
	       sortorder: 'desc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :false,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: true,    // 是否可以动态设置每页显示的结果数
	       rp: 6,  //每页显示记录数
	       rpOptions : [ 2, 5, 10, 15, 20 ],   // 可选择设定的每页结果数
	       showTableToggleBtn: true   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	     });
}
function reWritephoto(tdDiv,pid){
    tdDiv.innerHTML = '<input name=\'imageFieldA\' type=\'image\' id=\'imageFieldA\' src=\'../images/gaojing.gif\' style=\' width:16px; height:16px;\'/>';
}
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}
/**
* 学生违规
*CHENGJIN
*/
function reWriteoperate_flag(tdDiv,pid){
	var deal_flag = get_cell_text(pid, 6);
  if(deal_flag ==0 ){
     tdDiv.innerHTML = '<s:text name="vehcileinfo.alarmno" />';
  }
  else{
     tdDiv.innerHTML = '<s:text name="vehcileinfo.alarmyes" />';
  }
}

function reWriteLink(tdDiv,pid){
	var lon = get_cell_text(pid,11);
	var lat = get_cell_text(pid,12);
	var vin = get_cell_text(pid,13);
	var flag=get_cell_text(pid,14);
	var alarmtypeid=get_cell_text(pid,15);
    tdDiv.innerHTML = '<a href="#" onclick="openAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+flag+'\',\''+alarmtypeid+'\')">' + "处理" +'</a>';
}

/**
* 学生违规处理
*CHENGJIN
*/
function reWritestuLink(tdDiv,pid){
	var lon = get_cell_text(pid,9);
	var lat = get_cell_text(pid,10);
	var vin = get_cell_text(pid,11);
	var flag=get_cell_text(pid,12);
   tdDiv.innerHTML = '<a href="#" onclick="openStuAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\')">' + "处理" +'</a>';
}

function openStuAlarmPosition(longitude, latitude, vehicle_vin, alarm_id) {
	art.dialog.open("<s:url value='/busalarm/openstualarmposition.shtml' />?stualarm.longitude="+ longitude + "&stualarm.latitude=" + latitude + "&stualarm.vehicle_vin=" + vehicle_vin + "&stualarm.id="+ alarm_id,
			{
				title: '学生违规处理',
				skin: 'aero',
				limit: true,
				lock: true,
				drag: true,
				fixed: false,
				width :610,
				height:418
			});
}

//页面自适应
(function (jQuery) {
	
	jQuery(window).wresize(function(){
	
		testWidthHeight();
		jQuery('#alermtab').fixHeight();
		
		
	});
	jQuery(window).load(function (){
	
		testWidthHeight();
		jQuery('#alermtab').fixHeight();
		
	});
	
})(jQuery);
//获取页面高度
function get_page_height() {
	var height = 0;
	
	if (jQuery.browser.msie) {
	    height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
	} else {
	    height = self.innerHeight;
	}
	return height;
}
//获取页面宽度
function get_page_width() {
	var width = 0;
	if(jQuery.browser.msie){ 
		width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
	} else { 
		width = self.innerWidth; 
	} 
	return width;
}

//计算控件宽高
function testWidthHeight(){
	//jQuery(".flexigrid").width(1);
	var h = get_page_height();
	var w = get_page_width();
	//alert(h);

	//var meunid = document.getElementById("meunid");
	var maincurrentid = document.getElementById("main_currentid");
	//var leftInfoDiv = document.getElementById("leftInfoDiv");
	//var leftlist = document.getElementById("list");
	//var maptopid = document.getElementById("maptopid");
	var iCenter = document.getElementById("iCenter");
	var AlermInfoDiv =  document.getElementById("AlermInfoDiv");
	var copyrightid = document.getElementById("copyrightid");

	
	var meunidh = 90;//meunid.clientHeight; //菜单高度
	var maincurrentidh = 40;//maincurrentid.clientHeight;//导航条高度
	//var leftInfoDivh = leftInfoDiv.clientHeight; //左侧区域高度
	//var maptopidh = maptopid.clientHeight; //地图上方头高度
	var iCenterh = iCenter.clientHeight;   //地图高度
	var AlermInfoDivh = AlermInfoDiv.clientHeight; //地图下方告警表高度
	var copyrightidh = 40;//copyrightid.clientHeight;//页脚高度
	var maptop = 40;

	var mh = h - meunidh - maptop - copyrightidh;
	var leftlisth = mh-230;
	//alert(jQuery('#meunid').height() );
	//alert("h:"+h+",meunidh:"+meunidh+",maincurrentidh:"+maincurrentidh+",copyrightidh:"+copyrightidh+",mh:"+mh);
	//leftInfoDiv.style.height = h-137;

	jQuery(".flexigrid").width(w - 60);
	
		
	if(AlermInfoDiv.style.display ==""){
	
		if(h>(170+150)){
			iCenter.style.height = h-177-165;
		}
		else{
			iCenter.style.height =0;
		}
		 
		
		jQuery("#AlermInfoDiv").height(100);
		jQuery("#list").height(leftlisth);	
		jQuery(".bDiv").height(90);
	}
	else if(AlermInfoDiv.style.display =="none"){
		 
		iCenter.style.height = h-10; 
		
		jQuery("#list").height(leftlisth);
			
	}

	var iCw = jQuery("#iCenter").width();
	
	
}



</script>

