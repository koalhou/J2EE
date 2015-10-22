<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/md5/base64.js' />"></script>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/GPSDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/ZoneDwr.js'></script>
<script src="<s:url value='/scripts/swf/swfobject_modified.js'/>" type="text/javascript"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery.wresize.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<script>
var flushMap = 10000;
    var flushAlart= 15000;
    var intOneinit = 0;//window.setInterval("reallookvreal()",flushMap);
    //tip窗体所属vin
    var tipOpenVin="";
  	//tip窗体所属位置
    var tipOpenLon = ""; 
    var tipOpenLat="";
    // tip窗体打开状态
    var tipIsOpen=false;
    // 是否为函数关闭
    var funcClose=false;
    // tab标签状态
    var tiptabNo = false;
    
    
    var shanflag=true;
	// 加载地图对象
	var mapObj = null;
	// 自适应zoom数组
	var arrForFitView = new Array();
	//保存当前列表中的点数据
	var tablePointList = new Array();

	var tabflag="'40'";

	var schoolcarlist = "";

	var sercListThread =0; //左侧车辆列表定时刷新线程

	var mouseControlFlag = false; // 鼠标操作标识

	/*
	* 刷新浮动框头
	*/
	var jiaodianid = "";
	var sidevin = "";
	var intsideflash =0;
	
	/*
	* 
	*/
	var start_time = "";
	var end_time = "";

	// 加载地图
	function mapInit(divid) {
		var mapoption = new MMapOptions();   
		mapoption.zoom = 10;//要加载的地图的缩放级别   "113.686","34.693"
		mapoption.hasDefaultMenu = false;
		mapoption.toolbar = MConstants.ROUND; //设置地图初始化工具条，ROUND:新版圆工具条   *
		mapoption.toolbarPos=new MPoint(15,15); //设置工具条在地图上的显示位置   
		mapoption.overviewMap = MConstants.MINIMIZE; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认）   *
		mapoption.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。   *
		mapoption.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标  *
		mapoption.logoUrl = "../newimages/sidelayerimages/mask.png";
		 /* test="#session.adminProfile.en_code == '0000600001' or #session.adminProfile.en_code == '0000600002' or #session.adminProfile.en_code == '0000600003' or #session.adminProfile.en_code == '0000600004' or #session.adminProfile.en_code == '0000105556'" */
		var latlon = '<s:property value="#session.themeProfile.ismapgroundlogo" />';
		
		/* <s:if test="#session.adminProfile.en_code == '0000850260'">
		mapoption.center = new MLngLat(117.08 , 36.11);//要加载的地图的中心点经纬度坐标   
		mapoption.groundLogo = "../newimages/taian/mapbackgroud.jpg";
	    </s:if>
	    <s:else>
	    mapoption.center = new MLngLat(113.686, 34.693);//要加载的地图的中心点经纬度坐标   
		mapoption.groundLogo = "../newimages/sidelayerimages/mapbackgroud.jpg";
		</s:else> */
		if(latlon!=null&&latlon.split(",").length==2) {
			mapoption.center = new MLngLat(latlon.split(",")[0] , latlon.split(",")[1]);//要加载的地图的中心点经纬度坐标
		}else
			mapoption.center = new MLngLat(113.686, 34.693);
		//mapoption.mapComButton = MConstants.SHOW_NO;
		//mapoption.maxZoomLevel=17;
		//mapoption.minZoomLevel = 4;
		mapoption.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图   **
		mapoption.fullScreenButton = MConstants.SHOW;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏   **
		mapoption.centerCross = MConstants.HIDE;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏   **
		mapoption.requestNum=1000;//设置地图切片请求并发数。默认100。   **
		mapoption.isQuickInit=false;//设置是否快速显示地图，true显示，false不显示。   **
		//mapoption.viewBounds=(new MLngLatBounds(new MLngLat("62.314453","17.392579"),new MLngLat("137.06543","53.748711")));
		//mapoption.viewBounds=(new MLngLatBounds(new MLngLat(-143.261719,-11.350797),new MLngLat("15.820313","69.411242")));
		//mapoption.viewBounds=(new MLngLatBounds(new MLngLat("55.458984","3.207459"),new MLngLat("154.775391","66.213861")));
		
		mapObj = new MMap(divid, mapoption); //地图初始化
		//swfobject.registerObject(mapObj.id, "11.5.502.146", "../scripts/swf/expressInstall.swf"); 
		swfobject.registerObject("FlashID");
		
		/*
		var mapOptions = new MMapOptions();//构建地图辅助类   
		mapOptions.zoom = 4;//设置地图zoom级别   
		mapOptions.center = new MLngLat(116.397428, 39.90923); //设置地图中心点   
		mapOptions.hasDefaultMenu = false;
		mapOptions.toolbar = DEFAULT;//工具条   
		mapOptions.toolbarPos = new MPoint(5, 15); //工具条   
		mapOptions.overviewMap = MINIMIZE;//是否显示鹰眼   
		mapOptions.logoUrl = " ";
		mapOptions.scale = SHOW;//是否显示比例尺   
		mapOptions.returnCoordType = MConstants.COORD_TYPE_OFFSET;//返回数字坐标   
		mapOptions.zoomBox = true;//鼠标滚轮缩放和双击放大时是否有红框动画效果。   
		mapObj = new MMap(divid, mapOptions); //地图初始   
*/
		mapObj.setKeyboardEnabled(false);//屏蔽+ -号和方向键

		//changeChoose("tab1");
		

		mapObj.addEventListener(mapObj,MConstants.MAP_READY,map_ready);
		
		jQuery("#sendMsglist").easydrag();// 
		jQuery("#sendphotolist").easydrag();// 
	}

	function map_ready(param){  
		//addMarker();
		//mapObj.addEventListener(mapObj,MConstants.CLUSTER_MOUSE_CLICK,CLUSTER_clickMouse);
		mapObj.addEventListener(mapObj,MConstants.TIP_OPEN,openTip0_Open); 
		//mapObj.addEventListener(mapObj,MConstants.TIP_CLOSE,openTip_Close); 
		
		mapObj.addEventListener(mapObj, MConstants.MOUSE_DOWN,mouseDown);
		mapObj.addEventListener(mapObj, MConstants.MOUSE_UP,mouseUp);
		
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);

		loadsostab();
		tabcount();

		
		sethtmlInterval();
		
    }
	function mouseDown(param){  //
		//sethtmlclearInterval();
		mouseControlFlag = true; //
	}
	function mouseUp(param){  //
		//sethtmlInterval();
		mouseControlFlag = false;//
	}
				    

	//页面刷新线程定时启动
    function sethtmlInterval(){//
    	sercListThread = window.setTimeout("treebtnRefresh()",300000);
		intOneinit = window.setTimeout("reallookvreal()",flushMap);
		intalerm = window.setTimeout("autoflushtab()",flushAlart);
    }
    //页面刷新线程关闭
    function sethtmlclearInterval(){//
    	window.clearTimeout(sercListThread);
    	window.clearTimeout(intOneinit);
    	window.clearTimeout(intalerm);
    	
    }
	
	    
	function openTip0_Open(param){	//
		tipIsOpen = true;
		//tipOpenVin=param.overlayId;	
	}
	function openTip_Close(param){//
		
		tipIsOpen = false;
		if(!funcClose){
			tipOpenVin = "";
			tipOpenLon = "";
			tipOpenLat = "";
			tiptabNo = false;
		}
	}
	function openTip_Close(){
		tipIsOpen = false;
		funcClose = false;
		if(!funcClose){
			tipOpenVin = "";
			tipOpenLon = "";
			tipOpenLat = "";
			tiptabNo = false;
		}
	}

	/**
	 * 自动刷新各TAB页的值
	 */
	var tabflushflag=true;
	function autoflushtab(){
		if(tabflushflag){
		tabcount();
		searchTabList();
		}
		intalerm = window.setTimeout("autoflushtab()",flushAlart);
	}

	/**
	 * chengjin
	 *TAB各处COUNT
	 */
	function tabcount(){
		tabflushflag=false;
		//DWREngine.setAsync(false);
		var usrorgid=document.getElementById("Userorganizationid").value;
		GPSDwr.getTabCount(usrorgid,getTabcount_CallBack);
		//flag=true;
		//DWREngine.setAsync(true);
	}

	function showvoice(){  
		//art.dialog({id:'showvoiceid',left:'right',top:'bottom',time: 2, content: '<bgsound id="embedid" src="../images/newalert.wav" autostart="true" hidden="true">请注意车辆发生紧急告警'});
		var tipmsg=jQuery('#tipmsg');
		tipmsg.css('display','');
		jQuery('#embedid').attr('src', '<s:url value="/images/newalert.wav" />'); 
		intOneinit = window.setTimeout("voicehide()",2000);
	}

	/**
	 * chengjin
	 *TAB各处COUNT返回值处理
	 */
	function getTabcount_CallBack(data){
		var strs = data.split(",");
		var tab1count=document.getElementById("tab1count");
	    if(strs.length>1){
	    	if(jQuery("#tab1count").length>0) {
				if(strs[0]==0){
					tab1count.style.color="";
					tab1count.innerHTML=strs[0];
					shanflag=true;
				}else{
					tab1count.style.color="red";
					showvoice();
					tab1count.innerHTML=strs[0];
				}
	    	}
	    	if(jQuery("#tab2count").length>0) {
				var tab2count=document.getElementById("tab2count");
				if(strs[1]==0){
					tab2count.style.color="";
				}else{
					tab2count.style.color="red";
				}
				tab2count.innerHTML=strs[1];
	    	}
	    	if(jQuery("#tab3count").length>0) {
				var tab3count=document.getElementById("tab3count");
				if(strs[2]==0){
					tab3count.style.color="";
				}else{
					tab3count.style.color="red";
				}
				tab3count.innerHTML=strs[2];
	    	}
	    	if(jQuery("#tab4count").length>0) {
				var tab4count=document.getElementById("tab4count");
				if(strs[3]==0){
					tab4count.style.color="";
				}else{
					tab4count.style.color="red";
				}
				tab4count.innerHTML=strs[3];
	    	}
			if(jQuery("#tab5count").length>0) {
				var tab5count=document.getElementById("tab5count");
				if(strs[4]==0){
					tab5count.style.color="";
				}else{
					tab5count.style.color="red";
				}
				tab5count.innerHTML=strs[4];
			}
			if(jQuery("#tab6count").length>0) {
				var tab6count=document.getElementById("tab6count");
				if(strs[5]==0){
					tab6count.style.color="";
				}else{
					tab6count.style.color="red";
				}
				tab6count.innerHTML=strs[5];
			}
			if(jQuery("#tab7count").length>0) {
				var tab7count=document.getElementById("tab7count");
				if(strs[6]==0){
					tab7count.style.color="";
				}else{
					tab7count.style.color="red";
				}
				tab7count.innerHTML=strs[6];
			}
			tabflushflag=true;			
	    }
	}

	function voicehide(){
		var tipmsg=jQuery('#tipmsg');
		tipmsg.css('display','none');
		jQuery('#embedid').attr('src', '');
	}
	function chooseAlarmSearch() {
		tabcount();
		searchTabList();
	}
	/**
	 * 选择TAB页
	 */
	function changeChoose(id){
		if(jQuery('#popArea').mk_sidelayer('is_show')) {
			jQuery('#popArea').mk_sidelayer('close');
		}
		getTimes();
		jQuery('#litab1').removeClass();
		jQuery('#litab2').removeClass();
		jQuery('#litab3').removeClass();
		jQuery('#litab4').removeClass();
		jQuery('#litab5').removeClass();
		jQuery('#litab6').removeClass();
		jQuery('#litab7').removeClass();
		if(jQuery('#alarmDateListssArea').css("display")=="none"){
		    alarmAreaShowControl(2);
		}
		//HideandShowControl();
		if(id=="tab1"){
			jQuery('#litab1').addClass('select');
// 			tabflag="'40','72'";
			tabflag="'40'";
			
			if(jQuery("#overspdtab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#overspdtab").parent().parent().hide();
			}
			if(jQuery("#nottimetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nottimetab").parent().parent().hide();
			}
			if(jQuery("#latetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#latetab").parent().parent().hide();
			}			
			if(jQuery("#nofulltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nofulltab").parent().parent().hide();
			}
			if(jQuery("#notsitetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#notsitetab").parent().parent().hide();
			}
			if(jQuery("#oiltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#oiltab").parent().parent().hide();
			}
			//tabcount();
			jQuery('#sostab').parent().parent().show();
			if(jQuery("#sostab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				searchTabList();
			}else{
				loadsostab();
			}
		}
		if(id=="tab2"){
			jQuery('#litab2').addClass('select');
			tabflag="'32'";
			
			if(jQuery("#sostab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#sostab").parent().parent().hide();
			}
			if(jQuery("#nottimetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nottimetab").parent().parent().hide();
			}
			if(jQuery("#latetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#latetab").parent().parent().hide();
			}
			if(jQuery("#notsitetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#notsitetab").parent().parent().hide();
			}
			if(jQuery("#nofulltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nofulltab").parent().parent().hide();
			}			
			if(jQuery("#oiltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#oiltab").parent().parent().hide();
			}
			jQuery('#overspdtab').parent().parent().show();
			if(jQuery("#overspdtab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				searchTabList();
			}else{
				loadoverspdtab();
			}		
		}
		if(id=="tab3"){
			jQuery('#litab3').addClass('select');
			
			tabflag="'2','5'";
			if(jQuery("#sostab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#sostab").parent().parent().hide();
			}
			if(jQuery("#overspdtab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#overspdtab").parent().parent().hide();
			}
			if(jQuery("#notsitetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#notsitetab").parent().parent().hide();
			}
			if(jQuery("#latetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#latetab").parent().parent().hide();
			}			
			if(jQuery("#nofulltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nofulltab").parent().parent().hide();
			}			
			if(jQuery("#oiltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#oiltab").parent().parent().hide();
			}
			jQuery('#nottimetab').parent().parent().show();
			//tabcount();
			if(jQuery("#nottimetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				searchTabList();
			}else{
				nottimealarmload();
			}
		}
		if(id=="tab4"){
			jQuery('#litab4').addClass('select');
			
			tabflag="'1'";
			if(jQuery("#sostab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#sostab").parent().parent().hide();
			}
			if(jQuery("#overspdtab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#overspdtab").parent().parent().hide();
			}
			if(jQuery("#nottimetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nottimetab").parent().parent().hide();
			}
			if(jQuery("#notsitetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#notsitetab").parent().parent().hide();
			}
			if(jQuery("#latetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#latetab").parent().parent().hide();
			}
			if(jQuery("#nofulltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nofulltab").parent().parent().hide();
			}
			if(jQuery("#oiltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#oiltab").parent().parent().hide();
			}
			jQuery('#nofulltab').parent().parent().show();
			//tabcount();
			if(jQuery("#nofulltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				searchTabList();
			}else{
				nofullalarmload();
			}
		}		
		if(id=="tab5"){
			jQuery('#litab5').addClass('select');
			
			tabflag="'3'";
			if(jQuery("#sostab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#sostab").parent().parent().hide();
			}
			if(jQuery("#overspdtab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#overspdtab").parent().parent().hide();
			}
			if(jQuery("#nottimetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nottimetab").parent().parent().hide();
			}
			if(jQuery("#latetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#latetab").parent().parent().hide();
			}			
			if(jQuery("#nofulltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nofulltab").parent().parent().hide();
			}
			if(jQuery("#oiltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#oiltab").parent().parent().hide();
			}
			jQuery('#notsitetab').parent().parent().show();
			//tabcount();
			if(jQuery("#notsitetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				searchTabList();
			}else{
				notsitealarmload();
				//AutoWidthHeight();	
			}
		}
		if(id=="tab6"){
			jQuery('#litab6').addClass('select');
			
			tabflag="'4'";
			if(jQuery("#sostab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#sostab").parent().parent().hide();
			}
			if(jQuery("#overspdtab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#overspdtab").parent().parent().hide();
			}
			if(jQuery("#nottimetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nottimetab").parent().parent().hide();
			}
			if(jQuery("#notsitetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#notsitetab").parent().parent().hide();
			}
			if(jQuery("#nofulltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nofulltab").parent().parent().hide();
			}
			if(jQuery("#oiltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#oiltab").parent().parent().hide();
			}
			jQuery('#latetab').parent().parent().show();
			//tabcount();
			if(jQuery("#latetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				searchTabList();
			}else{
				latealarmload();
				//AutoWidthHeight();	
			}
		}		

		if(id=="tab7"){
			jQuery('#litab7').addClass('select');
			
			tabflag="'010','001'";
			if(jQuery("#sostab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#sostab").parent().parent().hide();
			}
			if(jQuery("#overspdtab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#overspdtab").parent().parent().hide();
			}
			if(jQuery("#nottimetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nottimetab").parent().parent().hide();
			}
			if(jQuery("#notsitetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#notsitetab").parent().parent().hide();
			}
			if(jQuery("#latetab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#latetab").parent().parent().hide();
			}
			if(jQuery("#nofulltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				jQuery("#nofulltab").parent().parent().hide();
			}
			jQuery("#oiltab").parent().parent().show();
			//tabcount();
			if(jQuery("#oiltab").parent().parent().attr('class').indexOf("flexigrid")>=0){
				searchTabList();
			}else{
				oilalarmload();
				//AutoWidthHeight();	
			}
		}
		AutoWidthHeight();
	}
	//效验下拉菜单选择保存结果
	function getImage(id){
		//document.getElementById(id).style.display='block';
		//默认取消所有样式
		jQuery('#childClickDiv div').css('background-color','#FFF');
		jQuery('#childClickDiv div img').css('display','none');
		jQuery('#childClickDiv div a').css('float','none');
		if(id==1){
			document.getElementById("image1").style.display='block';
			jQuery("#image1").parent().css('background-color','#E2F5FF');
			jQuery("#image1").next().css('float','left');
		}else if(id==2){
			document.getElementById("image2").style.display='block';
			jQuery("#image2").parent().css('background-color','#E2F5FF');
			jQuery("#image2").next().css('float','left');
		}else if(id==3){
			document.getElementById("image3").style.display='block';
			jQuery("#image3").parent().css('background-color','#E2F5FF');
			jQuery("#image3").next().css('float','left');
		}else if(id==4){
			document.getElementById("image4").style.display='block';
			jQuery("#image4").parent().css('background-color','#E2F5FF');
			jQuery("#image4").next().css('float','left');
		}else if(id==5){
			document.getElementById("image5").style.display='block';
			jQuery("#image5").parent().css('background-color','#E2F5FF');
			jQuery("#image5").next().css('float','left');
		}else if(id==6){
			document.getElementById("image6").style.display='block';
			jQuery("#image6").parent().css('background-color','#E2F5FF');
			jQuery("#image6").next().css('float','left');
		}
	}
	
	/**
	 * chengjin
	 *点击TAB页
	 */
	function searchTabList() {
	if(jQuery('#alarmDateListssArea').css("display")=="block"){
		if(tabflag=="'1'"){
			 jQuery('#nottimetab').flexOptions({
			    	newp: 1,
			    	params: [
			    	{ name: 'alarmtypeid', value: tabflag},
			    	{ name: 'orgid', value:jQuery('#Userorganizationid').val()}]
			    		});
			    jQuery('#nofulltab').flexReload();	
			    	 	
	    } 
		else if(tabflag=="'3'"){
			 jQuery('#notsitetab').flexOptions({
			    	newp: 1,
			    	params: [
			    	{ name: 'alarmtypeid', value: tabflag},
			    	{ name: 'orgid', value:jQuery('#Userorganizationid').val()}]
			    		});
			    jQuery('#notsitetab').flexReload();	
			    	 	
	    } 
		else if(tabflag=="'4'"){
			 jQuery('#latetab').flexOptions({
			    	newp: 1,
			    	params: [
			    	{ name: 'alarmtypeid', value: tabflag},
			    	{ name: 'orgid', value:jQuery('#Userorganizationid').val()}]
			    		});
			    jQuery('#latetab').flexReload();	
			    	 	
	    } 
	    else if(tabflag=="'2','5'"){
			 jQuery('#nofulltab').flexOptions({
			    	newp: 1,
			    	params: [
			    	{ name: 'alarmtypeid', value: tabflag},
			    	{ name: 'orgid', value:jQuery('#Userorganizationid').val()}]
			    		});
			    jQuery('#nottimetab').flexReload();	
			    	 	
	    } 
	    else if(tabflag=="'40'"){
			 jQuery('#sostab').flexOptions({
			    	newp: 1,
			    	params: [
			    	{ name: 'alarmtypeid', value: tabflag},
			    	{ name: 'orgid', value:jQuery('#Userorganizationid').val()}]
			    		});
			    		jQuery('#sostab').flexReload();	 
	    } 
	    else if(tabflag=="'010','001'"){
	    	jQuery('#oiltab').flexOptions({
		    	newp: 1,
		    	params: [ 
					 		{ name: 'alarm_type_id', value: ""},
					 		{ name: 'deal_flag', value: "0"},
					 		{ name: 'chooseorgid', value: jQuery('#Userorganizationid').val()},
					 		{ name: 'start_time', value: start_time},
					 		{ name: 'end_time', value: end_time},
					 		{ name: 'gridflag', value: '1'},
					 		{ name: 'isChuli', value: '1'}]
		    	});
		    	jQuery('#oiltab').flexReload();	 
	    }
	    else if(tabflag=="'32'"){
	    	jQuery('#overspdtab').flexOptions({
	    	newp: 1,
	    	params: [
	    	{ name: 'alarmtypeid', value: tabflag},
	    	{ name: 'orgid', value: jQuery('#Userorganizationid').val()}]
	    		});
	    		jQuery('#overspdtab').flexReload();		 	
	    	} 
       }
	}

	/*2.0 更多跳转*/
	function newmorealarmlist(){
		 window.location="<s:url value='/busalarm/newmorealarm.shtml'/>?newalarm_type_id="+tabflag;
	}

	function reWritephoto(tdDiv,pid,row){
		return "<input name='imageFieldA' type='image' id='imageFieldA' src='../images/gaojing.gif' style=' width:16px; height:16px;'/>";
	}

	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}

	function getAdressInfo(tdDiv,pid,row){
		//tdDiv.innerHTML = "";
		DWREngine.setAsync(false);
		var lon = row.cell[0];
		var lat = row.cell[1];

		
		if( lon!=null&&lat!=null&&lon !=""  && lat != "" && lon != " " 
			&& lat != " " && lon !="undefined" && lat != "undefined"&& lon !="&nbsp;" && lat != "&nbsp;"
			&&lon!="FFFF" && lat!="FFFF"){
		  if(lon.length >1 && lat.length >1){

			 var lnglatXY=new MLngLat(lon,lat); 
			 var mls =new MReGeoCodeSearch();   
			 var opt= new MReGeoCodeSearchOptions();   
			 opt.poiNumber=10;;//返回周边的POI数量,默认10   
			 opt.range=100;//限定周边热点POI和道路的距离范围   
			 opt.pattern=1;//返回数据的模式,0表示返回地标性POI,1表示返回全部POI，   
			 opt.exkey="";//排除的关键字   
			 mls.setCallbackFunction(poiToAddressSearch_CallBack);   
			 mls.poiToAddress(lnglatXY,opt);   
			 		

		
			 var addr ="";
			function poiToAddressSearch_CallBack(data){
				
				if(data.error_message != null){   
					resultStr="查询异常！"+data.error_message;
					return "";   
				}else{   
					switch(data.message){
					case 'ok':
						if(data.SpatialBean.roadList!=null && data.SpatialBean.roadList.length>0){
							addr = data.SpatialBean.roadList[0].name;
						}
						
						
						if(addr != null && addr != "" && addr.trim != "undefined"){
							return addr+"附近";
						}
						else{
							return "";
						}
						break; 
					case 'error':
						addr ="";
						return "";
						break; 
					default: 
						addr ="";
						return "";
					}
				}
			}
		}
		else{
			return "";
		}
		
		DWREngine.setAsync(true);
		}
		else{
			return "";
		}
	}

	function  reWriteSosphoto(tdDiv,pid,row){
		var vin = row.cell[2];
		return '<a href="javascript:void(0)" onclick="opensosPhoto(\''+vin +'\')">' + "照片" +'</a>';	
	}

	//车辆SOS处理转换
	function reWriteSosLink(tdDiv,pid,row){
			var lon = row.cell[0];
			var lat = row.cell[1];
			var vin = row.cell[2];
			var flag=row.cell[3];
			var alarmtypeid=row.cell[4];
			var alarmtime=row.cell[10];
			var alarmtypename=row.cell[8];
			if(flag==1){
				 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
			}else{
				 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
			}
		}


	//其他车辆告警处理转换
	function reWriteLink(tdDiv,pid,row){
			var lon = row.cell[0];
			var lat = row.cell[1];
			var vin = row.cell[2];
			var flag=row.cell[3];
			var alarmtypeid=row.cell[4];
			var alarmtime=row.cell[10];
			var alarmtypename=row.cell[8];
			if(flag==1){
				 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
			}else{
				 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
			}
			
		}

	/*
	*2.0告警处理处泡泡弹出
	*/

	function openotherAlarmPosition(longitude,
			 latitude,
			 vehicle_vin,
			 alarm_id,
			 alarmtime,
			 alarmtypeid,
			 alarmtypename) {
		
		 var TerminalViBean = "";
		 DWREngine.setAsync(false);
		 GPSDwr.returnTipInfo(vehicle_vin,function (obj){
			  if (obj != null) {
				  TerminalViBean=obj[0]
			  }
		    });
		 DWREngine.setAsync(true);
		 var alarmtime1=alarmtime.substr(0,10);
		 var alarmtime2=alarmtime.substr(11,8);
		 alarmtime=alarmtime1+alarmtime2;
//			 TerminalViBean.ROUTE_ID+"-----6:----"+'6'+"-----TerminalViBean.color:----"+TerminalViBean.color+"-----TerminalViBean.VEH_EXT_INFO:----"+TerminalViBean.VEH_EXT_INFO+"----TerminalViBean.STAT_INFO:-----"+TerminalViBean.STAT_INFO+"----alarm_id-----"+
//				 alarm_id+"-----alarmtypeid:----"+alarmtypeid+"-----alarmtime:----"+alarmtime);
		 openFramePage(TerminalViBean.vehicle_code,TerminalViBean.VEHICLE_LN,TerminalViBean.VEHICLE_VIN,longitude,latitude,
				 TerminalViBean.ROUTE_ID,'6',TerminalViBean.color,TerminalViBean.VEH_EXT_INFO,TerminalViBean.STAT_INFO,
				 alarm_id,alarmtypeid,alarmtime);
		 //testsidelayerHead('2', TerminalViBean.VEHICLE_LN,TerminalViBean.VEHICLE_VIN,TerminalViBean.LONGITUDE,
				// TerminalViBean.LATITUDE,TerminalViBean.ROUTE_ID,'6',TerminalViBean.color,TerminalViBean.VEH_EXT_INFO,TerminalViBean.STAT_INFO);
		// jQuery('#popArea').mk_sidelayer('set_title', TerminalViBean.VEHICLE_LN);
		 //jQuery('#popArea').mk_sidelayer('reload','<s:url value="/busalarm/getdetailAlarm.shtml" />?vin=aa bb cc');
	     
	     //change_side_images('#aa'+'6');
		 
		}
	 /**
	  * 学生违规
	  *CHENGJIN
	  */
	 function reWritestuoperate_flag(tdDiv,pid,row){
	 	var operate_flag = row.cell[10];
	    if(operate_flag ==0 ){
	       return '<s:text name="vehcileinfo.alarmno" />';
	    }
	    else{
	    	return '<s:text name="vehcileinfo.alarmyes" />';
	    }
	 }
	 /**
	  * 通勤车告警处理
	  *CHENGJIN
	  */
	 function reWritestuLink1(tdDiv,pid,row){
	 	var lon = row.cell[9];
	 	var lat = row.cell[10];
	 	var vin = row.cell[11];
	 	var flag=row.cell[12];
	 	var alarmtypename=row.cell[2];
	 	var alarmtypeid=row.cell[7];
	 	var alarmtime=row.cell[8];
	 	if(flag==1){
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}else{
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}
		/*
	 	if(flag==1){
	 		tdDiv.innerHTML = '<a href="#" onclick="openStuAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+ alarmtypename + '\')">' + "点击处理" +'</a>';
	 	}else{
	 		tdDiv.innerHTML = '<a href="#" onclick="openStuAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+ alarmtypename + '\')">' + "点击处理" +'</a>';
	 	}*/
	     
	     
	 }	
	 
	 /**
	  * 通勤车告警处理(油量告警)
	  *CHENGJIN
	  */
	 function reWriteOilLink(tdDiv,pid,row){
	 	var lon = row.cell[13];
	 	var lat = row.cell[14];
	 	var vin = row.cell[19];
	 	var flag='0';
	 	var alarmtypename=row.cell[3];
	 	var alarmtypeid=row.cell[18];
	 	var alarmtime=row.cell[5];
	 	var alarmId = row.cell[17];
	 	if(flag==1){
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ alarmId + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}else{
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ alarmId + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}
	     
	 }
	 
	 /**
	  * 通勤车告警处理(迟到告警)
	  *CHENGJIN
	  */
	 function reWriteLaterLink1(tdDiv,pid,row){
	 	var lon = row.cell[4];
	 	var lat = row.cell[15];
	 	var vin = row.cell[16];
	 	var flag=row.cell[17];
	 	var alarmtypename=row.cell[3];
	 	var alarmtypeid=row.cell[12];
	 	var alarmtime=row.cell[5];
	 	if(flag==1){
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}else{
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}
		/*
	 	if(flag==1){
	 		tdDiv.innerHTML = '<a href="#" onclick="openStuAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+ alarmtypename + '\')">' + "点击处理" +'</a>';
	 	}else{
	 		tdDiv.innerHTML = '<a href="#" onclick="openStuAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+ alarmtypename + '\')">' + "点击处理" +'</a>';
	 	}*/
	     
	     
	 }	
	 
	 /**
	  * 通勤车告警处理(站外开门)
	  *CHENGJIN
	  */
	 function reWriteZhanWeiKaiMenLink1(tdDiv,pid,row){
		 var lon = row.cell[15];
		 var lat = row.cell[16];
		 var vin = row.cell[17];
		 var flag=row.cell[18];
		 var alarmtypename=row.cell[3];
		 var alarmtypeid=row.cell[13];
		 var alarmtime=row.cell[6];
	 	if(flag==1){
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}else{
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}
		/*
	 	if(flag==1){
	 		tdDiv.innerHTML = '<a href="#" onclick="openStuAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+ alarmtypename + '\')">' + "点击处理" +'</a>';
	 	}else{
	 		tdDiv.innerHTML = '<a href="#" onclick="openStuAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+ alarmtypename + '\')">' + "点击处理" +'</a>';
	 	}*/
	     
	     
	 }	
	 
	 /**
	  * 通勤车告警处理(非时)
	  *CHENGJIN
	  */
	 function reWritestuLink(tdDiv,pid,row){
		var lon = row.cell[16];
	 	var lat = row.cell[17];
	 	var vin = row.cell[18];
	 	var flag=row.cell[19];
	 	var alarmtypename=row.cell[3];
	 	var alarmtypeid=row.cell[14];
	 	var alarmtime=row.cell[6];

	 	if(flag==1){
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}else{
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}
		/*
	 	if(flag==1){
	 		tdDiv.innerHTML = '<a href="#" onclick="openStuAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+ alarmtypename + '\')">' + "点击处理" +'</a>';
	 	}else{
	 		tdDiv.innerHTML = '<a href="#" onclick="openStuAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+ alarmtypename + '\')">' + "点击处理" +'</a>';
	 	}*/
	     
	     
	 }//
	 
	 /**
	  * 通勤车告警处理(未满发车)
	  *CHENGJIN
	  */
	 function reWritesWeiManLink(tdDiv,pid,row){
		var lon = row.cell[15];
	 	var lat = row.cell[16];
	 	var vin = row.cell[17];
	 	var flag=row.cell[18];
	 	var alarmtypename=row.cell[3];
	 	var alarmtypeid=row.cell[13];
	 	var alarmtime=row.cell[5];
	 	if(flag==1){
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}else{
			 return '<a href="javascript:void(0)" onclick="openotherAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+alarmtime+'\',\''+alarmtypeid+'\',\''+alarmtypename+'\')">' + "点击处理" +'</a>';
		}
		/*
	 	if(flag==1){
	 		tdDiv.innerHTML = '<a href="#" onclick="openStuAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+ alarmtypename + '\')">' + "点击处理" +'</a>';
	 	}else{
	 		tdDiv.innerHTML = '<a href="#" onclick="openStuAlarmPosition(\''+lon+'\',\''+lat+'\',\''+vin +'\',\''+ pid + '\',\''+ alarmtypename + '\')">' + "点击处理" +'</a>';
	 	}*/
	     
	     
	 }//
	 
	 function reWritedeal_flag(tdDiv,pid,row){

			var deal_flag = row.cell[4];
		   if(deal_flag ==0 ){
		      return '<s:text name="vehcileinfo.alarmyes" />';
		   }
		   else{
		      return '<s:text name="vehcileinfo.alarmno" />';
		   }
		}
	 
		/*
		 * chengjin 
		 *初始化TAB处告警列表 
		 */
		 function loadsostab(){
				var sostab = jQuery('#sostab');
				 sostab.flexigrid({
				  url: '<s:url value='/busalarmpkg/gettabsosalarm.shtml' />',  //加载表格走的action
				  dataType: 'json',    //json格式
				  height:90,
				  width:800,
				  colModel : [
				        {display: '经度' ,name : 'longitude',width : 80,hide:true,toggle:false},
						{display: '纬度' ,name : 'latitude',width : 80,hide:true,toggle:false},
		                {display: 'VIN' ,name : 'vehicle_vin',width : 80,hide:true,toggle:false},
		                {display: 'deal_flag' ,name : 'deal_flag',width :80,hide:true,toggle:false},
		                {display: 'ALARM_TYPE_ID' ,name : 'ALARM_TYPE_ID',width :80,hide:true,toggle:false},
		                {display: '方向', name : 'direction', width : 30,hide:true,toggle:false},
				  		{display: ''        , name : '',width : 20, sortable : false ,align: 'center' ,process: reWritephoto},  
				  		{display: '班车号'  , name : 'vehicle_code',width : calcColumn(0.1,85,240), sortable : false, align: 'center',escape: true }, 
				  		{display: '车牌号'  , name : 'vehicle_ln',width : calcColumn(0.1,85,240), sortable : false, align: 'center',escape: true }, //加超链接（对于没有链接，无需加此属性）
				  		{display: '告警类型', name : 'alarm_type_name', width : calcColumn(0.1,100,240), sortable : false, align: 'center'} ,
				  		{display: '告警时间', name : 'alarm_time', width : calcColumn(0.12,115,240), sortable : false, align: 'center'},
				  		{display: '驾驶员'  , name : "NLSSORT(driver_name,'NLS_SORT = SCHINESE_PINYIN_M')", width : calcColumn(0.1,85,240), sortable : false, align: 'center',escape: true},
				  		{display: '跟车老师'    , name : 'sichen_name', width : calcColumn(0.1,85,240), hide: true,sortable : false, align: 'center',escape: true},
				  		{display: '乘坐人数', name : 'stu_num', width : calcColumn(0.02,85,240), hide: false,sortable : false, align: 'center'},
				        {display: '照片', name : '', width : calcColumn(0.05,65,240), sortable : false, align: 'center',hide:true,toggle:false}
				        <s:if test="#session.perUrlList.contains('111_3_1_9_1')">
				        ,{display: '地理位置' , name : '', width : calcColumn(0.17,150,240), sortable :false, align: 'center'},
				        {display: '处理' , name : '', width : 80, sortable :false, align: 'center',  process: reWriteSosLink}
				        </s:if>
				        <s:else>
				        ,{display: '地理位置' , name : '', width : calcColumn(0.25,230,240), sortable :false, align: 'center'}
				        </s:else>
				        ],
				       sortname: 'alarm_time', //首次加载的排序字段
				       sortorder: 'desc',  //升序OR降序
				       sortable: true,   //是否支持排序
				       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
				       usepager :false,  //是否分页
				       resizable: false,  //是否可以设置表格大小
				       useRp: true,    // 是否可以动态设置每页显示的结果数
				       rp: 6,  //每页显示记录数
				       rpOptions : [ 2, 5, 10, 15, 20 ],   // 可选择设定的每页结果数
				       showTableToggleBtn: true,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
				       showToggleBtn: false,
				       params: [{name: 'orgid', value: jQuery('#Userorganizationid').val() }
				 	   ]	  
				     });
			}

		 function loadoverspdtab(){
				var overspdtab = jQuery('#overspdtab');
				 overspdtab.flexigrid({
				  url: '<s:url value='/busalarmpkg/gettabvehalarm.shtml' />',  //加载表格走的action
				  dataType: 'json',    //json格式
				  height:90,
				  width:800,
				  colModel : [
				        {display: '经度' ,name : 'longitude',width : 80,hide:true,toggle:false},
				  	    {display: '纬度' ,name : 'latitude',width : 80,hide:true,toggle:false},
				  	    {display: 'VIN' ,name : 'vehicle_vin',width : 80,hide:true,toggle:false},
				  	    {display: 'deal_flag' ,name : 'deal_flag',width :80,hide:true,toggle:false},
				  	    {display: 'ALARM_TYPE_ID' ,name : 'ALARM_TYPE_ID',width :80,hide:true,toggle:false},
				  	    {display: '方向', name : 'direction', width : 30,hide:true,toggle:false},
				  		{display: ''        , name : '',width : 20, sortable : false ,align: 'center' ,process: reWritephoto},  
				  		{display: '班车号'  , name : 'vehicle_code',width : calcColumn(0.02,85,240), sortable : false, align: 'center' ,escape: true}, 
				        {display: '车牌号'  , name : 'vehicle_ln',width : calcColumn(0.05,85,240), sortable : false, align: 'center' ,escape: true}, //加超链接（对于没有链接，无需加此属性）
				        {display: '告警类型', name : 'alarm_type_name', width :  calcColumn(0.1,115,240), sortable : false, align: 'center'} ,
				        {display: '告警时间', name : 'alarm_time', width :  calcColumn(0.12,135,240), sortable : false, align: 'center'},
				        {display: '状态'    , name : 'deal_flag', width : 80, sortable : false, align: 'center',hide:true,toggle:false},
				        {display: '速度'    , name : 'speeding', width : calcColumn(0.04,85,240) , sortable : false, align: 'center'},
				        {display: '转速', name : 'engine_rotate_speed', width : calcColumn(0.04,85,240), sortable : false, hide:true,align: 'center'},
				        {display: '里程', name : 'mileage', width : calcColumn(0.04,85,240), sortable : false, align: 'center'}
				        <s:if test="#session.perUrlList.contains('111_3_1_9_1')">
				        ,{display: '地理位置', name : '', width : calcColumn(0.25,160,240), sortable : false, align: 'center'},
				        {display: '处理' , name : '', width : calcColumn(0.05,90,240), sortable :false, align: 'center',  process: reWriteLink}
				        </s:if>
				        <s:else>
				        ,{display: '地理位置', name : '', width : calcColumn(0.25,250,240), sortable : false, align: 'center'}
				        </s:else>
				        ],
				       sortname: 'alarm_time', //首次加载的排序字段
				       sortorder: 'desc',  //升序OR降序
				       sortable: true,   //是否支持排序
				       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
				       usepager :false,  //是否分页
				       resizable: false,  //是否可以设置表格大小
				       useRp: true,    // 是否可以动态设置每页显示的结果数
				       rp: 6,  //每页显示记录数
				       rpOptions : [ 2, 5, 10, 15, 20 ],   // 可选择设定的每页结果数
				       showTableToggleBtn: true,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
				       showToggleBtn: false,
				       params: [
			                   { name: 'alarmtypeid', value: tabflag},
				     	       {name: 'orgid', value: jQuery('#Userorganizationid').val() }
				 	   ]	  
				     });
			}

	 /**
	  * chengjin
	  *加载通勤车告警(非时发车)
	  */
	 function nottimealarmload(){
		 
	 	var nottimetab = jQuery('#nottimetab');
	 	  nottimetab.flexigrid({
	 	  url: '<s:url value='/busalarmpkg/gettabtqcnofullalarm.shtml' />',  //加载表格走的action
	 	  dataType: 'json',    //json格式
	 	  params: [{name: 'alarmtypeid', value:tabflag },
	     	       {name: 'orgid', value: jQuery('#Userorganizationid').val() }],
	 	  height:90,
	 	  colModel : [  {display: ''        , name : '',width : 20, sortable : false ,align: 'center' ,process: reWritephoto},  
	 	                {display: '班车号',name : 'vehicle_code', width : calcColumn(0.05,65,240), sortable : false, align: 'center',escape: true},
	 	  		        {display: '车牌号',name : 'vehicle_ln', width : calcColumn(0.05,65,240), sortable : false, align: 'center',escape: true},
	 					{display: '告警类型', name : 'alarm_type_name', width : calcColumn(0.08,45,240), sortable : false, align: 'center',escape: true},
	 					{display: '处理状态', name : 'operate_flag', width : calcColumn(0.05,75,100), hide: true, sortable : false, align: 'center',process: reWritedeal_flag},
	 					{display: '行驶线路', name : 'route_name', width : calcColumn(0.1,105,200), sortable : false, align: 'center'},
						{display: '告警时间', name : 'alarm_date', width : calcColumn(0.1,15,240), sortable : false, align: 'center'},
						{display: '非时发车站点', name : 'site_name', width : calcColumn(0.1,15,240), sortable : false, align: 'center'},
						{display: '计划发车时间', name : 'late_time', width : calcColumn(0.06,105,200), sortable : false, align: 'center'},
						{display: '实际发车时间', name : 'late_time', width : calcColumn(0.1,105,200), sortable : false, align: 'center'},
						{display: '发车时间差(分)', name : 'early_time', width : calcColumn(0.06,15,20), sortable : false, align: 'center'},
						{display: '空余座位数', name : 'early_time', width : calcColumn(0.04,15,20), hide: true,sortable : false,hide:true, align: 'center'},
						{display: '驾驶员', name : 'siter_left', width : calcColumn(0.04,15,20), sortable : false, align: 'center'},
						{display: '联系电话', name : 'siter_left', width : calcColumn(0.04,15,20), hide: true,sortable : false, align: 'center'},
	 			        {display: 'ALARM_TYPE', name : 'alarm_type', width : 90, sortable :false, align: 'center',hide:true,toggle:false},
	 			        {display: '处理', name : '', width : calcColumn(0.05,60,240), sortable : false, align: 'center',process: reWritestuLink},
	 			        {display: '经度' ,name : 'longitude',width : 80,hide:true,toggle:false},
	 			        {display: '纬度' ,name : 'latitude',width : 80,hide:true,toggle:false},
	 			        {display: 'VIN' ,name : 'vehicle_vin',width : 80,hide:true,toggle:false},
	 			        {display: 'flag' ,name : 'operate_flag',width : 80,hide:true,toggle:false}
	 	        ],
	 	       sortname: 'alarm_date', //首次加载的排序字段
	 	       sortorder: 'desc',  //升序OR降序
	 	       sortable: true,   //是否支持排序
	 	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	 	       usepager :false,  //是否分页
	 	       resizable: false,  //是否可以设置表格大小
	 	       useRp: true,    // 是否可以动态设置每页显示的结果数
	 	       rp: 6,  //每页显示记录数
	 	       rpOptions : [ 2, 5, 10, 15, 20 ],   // 可选择设定的每页结果数
	 	       showTableToggleBtn: true,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	 	       showToggleBtn: false
	 	       
	 	     });
	 }
	 /**
	  * chengjin
	  *加载通勤车告警(未满发车)
	  */
	 function nofullalarmload(){
		 
	 	var nofulltab = jQuery('#nofulltab');
	 	nofulltab.flexigrid({
	 	  url: '<s:url value='/busalarmpkg/gettabtqcnofullalarm1.shtml' />',  //加载表格走的action
	 	  dataType: 'json',    //json格式
	 	  params: [{name: 'alarmtypeid', value:tabflag },
	     	       {name: 'orgid', value: jQuery('#Userorganizationid').val() }],
	 	  height:90,
	 	  colModel : [  {display: ''        , name : '',width : 20, sortable : false ,align: 'center' ,process: reWritephoto},
	 	  		        {display: '班车号', name : 'vehicle_code', width : calcColumn(0.08,45,240), sortable : false, align: 'center',escape: true},
	 	  		        {display: '车牌号',name : 'vehicle_ln', width : calcColumn(0.05,65,240), sortable : false, align: 'center',escape: true},
	 					{display: '告警类型', name : 'alarm_type_name', width : calcColumn(0.08,45,240), sortable : false, align: 'center',escape: true},
	 					{display: '处理状态', name : 'operate_flag', width : calcColumn(0.05,75,100), hide: true, sortable : false, align: 'center',process: reWritedeal_flag},
	 			        {display: '告警时间', name : 'alarm_date', width : calcColumn(0.1,120,240), sortable : false, align: 'center'},
	 			        {display: '行驶线路', name : 'route_name', width : calcColumn(0.1,105,200), sortable : false, align: 'center'},
						{display: '未满发车站点', name : 'site_name', width : calcColumn(0.1,15,240), sortable : false, align: 'center'},
						{display: '核载人数', name : 'standard_cnt', width : calcColumn(0.06,105,200), sortable : false, align: 'center'},
						{display: '实载人数', name : 'real_cnt', width : calcColumn(0.06,15,20), sortable : false, align: 'center'},
						{display: '空余座数', name : 'siter_left', width : calcColumn(0.04,15,20), sortable : false,hide:true, align: 'center'},
						{display: '驾驶员', name : 'driver_name', width : calcColumn(0.04,15,20), sortable : false, align: 'center'},
						{display: '驾驶员电话', name : 'siter_left', width : calcColumn(0.04,15,20),hide:true, sortable : false, align: 'center'},
	 			        {display: 'ALARM_TYPE', name : 'alarm_type', width : 90, sortable :false, align: 'center',hide:true,toggle:false},
	 			        {display: '处理', name : '', width : calcColumn(0.05,60,240), sortable : false, align: 'center',process: reWritesWeiManLink},
	 			        {display: '经度' ,name : 'longitude',width : 80,hide:true,toggle:false},
	 			        {display: '纬度' ,name : 'latitude',width : 80,hide:true,toggle:false},
	 			        {display: 'VIN' ,name : 'vehicle_vin',width : 80,hide:true,toggle:false},
	 			        {display: 'flag' ,name : 'operate_flag',width : 80,hide:true,toggle:false}
	 	        ],
	 	       sortname: 'alarm_date', //首次加载的排序字段
	 	       sortorder: 'desc',  //升序OR降序
	 	       sortable: true,   //是否支持排序
	 	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	 	       usepager :false,  //是否分页
	 	       resizable: false,  //是否可以设置表格大小
	 	       useRp: true,    // 是否可以动态设置每页显示的结果数
	 	       rp: 6,  //每页显示记录数
	 	       rpOptions : [ 2, 5, 10, 15, 20 ],   // 可选择设定的每页结果数
	 	       showTableToggleBtn: true,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	 	       showToggleBtn: false
	 	       
	 	     });
	 }
	 
	 /**
	  * chengjin
	  *加载通勤车告警(站外开门)
	  */
	 function notsitealarmload(){
	 	var notsitetab = jQuery('#notsitetab');
	 	  notsitetab.flexigrid({
	 	  url: '<s:url value='/busalarmpkg/gettabtqcnotsitealarm.shtml' />',  //加载表格走的action
	 	  dataType: 'json',    //json格式
	 	  params: [{name: 'alarmtypeid', value:tabflag },
	     	       {name: 'orgid', value: jQuery('#Userorganizationid').val() }],
	 	  height:90,
	 	  colModel : [  {display: ''        , name : '',width : 20, sortable : false ,align: 'center' ,process: reWritephoto},  
	 	                {display: '班车号',name : 'vehicle_code', width : calcColumn(0.02,65,240), sortable : false, align: 'center',escape: true},
	 	  		        {display: '车牌号',name : 'vehicle_ln', width : calcColumn(0.05,65,240), sortable : false, align: 'center',escape: true},
	 					{display: '告警类型', name : 'alarm_type_name', width : calcColumn(0.06,45,240), sortable : false, align: 'center',escape: true},
	 					{display: '处理状态', name : 'operate_flag', width : calcColumn(0.05,75,100), hide: true, sortable : false, align: 'center',process: reWritedeal_flag},
						{display: '行驶线路', name : 'route_name', width : calcColumn(0.07,75,100), sortable : false, align: 'center'},
	 			        {display: '告警时间', name : 'alarm_date', width : calcColumn(0.08,120,240), sortable : true, align: 'center'},
						{display: '告警位置', name : 'position', width : calcColumn(0.24,15,20), sortable : false, align: 'center'},
						{display: '上一站点', name : 'up_site', width : calcColumn(0.08,15,240), hide: false,sortable : false, align: 'center'},
						{display: '下一站点', name : 'down_site', width : calcColumn(0.08,15,240), hide: true,sortable : false, align: 'center'},
						{display: '下一站点', name : 'site_name', width : calcColumn(0.08,15,20), hide: false, sortable : false, align: 'center'},
						{display: '驾驶员', name : 'driver_name', width : calcColumn(0.03,15,20), sortable : false, align: 'center'},
						{display: '驾驶员电话', name : 'driver_tel', width : calcColumn(0.1,15,20),hide: true, sortable : false, align: 'center'},
	 			        {display: 'ALARM_TYPE', name : 'alarm_type', width : 90, sortable :false, align: 'center',hide:true,toggle:false},
	 			        {display: '处理', name : '', width : calcColumn(0.05,60,240), sortable : false, align: 'center',process: reWriteZhanWeiKaiMenLink1},
	 			        {display: '经度' ,name : 'longitude',width : 80,hide:true,toggle:false},
	 			        {display: '纬度' ,name : 'latitude',width : 80,hide:true,toggle:false},
	 			        {display: 'VIN' ,name : 'vehicle_vin',width : 80,hide:true,toggle:false},
	 			        {display: 'flag' ,name : 'operate_flag',width : 80,hide:true,toggle:false}
	 	        ],
	 	       sortname: 'alarm_date', //首次加载的排序字段
	 	       sortorder: 'desc',  //升序OR降序
	 	       sortable: true,   //是否支持排序
	 	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	 	       usepager :false,  //是否分页
	 	       resizable: false,  //是否可以设置表格大小
	 	       useRp: true,    // 是否可以动态设置每页显示的结果数
	 	       rp: 6,  //每页显示记录数
	 	       rpOptions : [ 2, 5, 10, 15, 20 ],   // 可选择设定的每页结果数
	 	       showTableToggleBtn: true,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	 	       showToggleBtn: false
	 	       
	 	     });
	 }

	 /**
	  * chengjin
	  *加载通勤车告警(迟到告警)
	  */
	 function latealarmload(){
	 	var latetab = jQuery('#latetab');
	 	  latetab.flexigrid({
	 	  url: '<s:url value='/busalarmpkg/gettabtqclatealarm.shtml' />',  //加载表格走的action
	 	  dataType: 'json',    //json格式
	 	  params: [{name: 'alarmtypeid', value:tabflag },
	     	       {name: 'orgid', value: jQuery('#Userorganizationid').val() }],
	 	  height:90,
	 	  colModel : [  {display: ''        , name : '',width : 20, sortable : false ,align: 'center' ,process: reWritephoto},  
	 	                {display: '班车号',name : 'vehicle_code', width : calcColumn(0.05,65,240), sortable : false, align: 'center',escape: true},
	 	  		        {display: '车牌号',name : 'vehicle_ln', width : calcColumn(0.05,65,240), sortable : false, align: 'center',escape: true},
	 					{display: '告警类型', name : 'alarm_type_name', width : calcColumn(0.08,45,240), sortable : false, align: 'center',escape: true},
	 					{display: '处理状态', name : 'operate_flag', width : calcColumn(0.05,75,240), hide: true, sortable : false, align: 'center',process: reWritedeal_flag},
	 			        {display: '告警时间', name : 'alarm_date', width : calcColumn(0.1,120,240), sortable : false, align: 'center'},
	 			        {display: '行驶线路', name : 'route_name', width : calcColumn(0.1,105,240), sortable : false, align: 'center'},
	 			        {display: '终点站', name : 'site_name', width : calcColumn(0.1,105,240),hide:false, sortable : false, align: 'center'},
						{display: '实际到达时间', name : 'arrive_time', width : calcColumn(0.1,105,240), sortable : false, align: 'center'},
						{display: '迟到时长（分）', name : 'late_time', width : calcColumn(0.05,105,240), sortable : false, align: 'center'},
						{display: '驾驶员', name : 'driver_name', width : calcColumn(0.05,105,240), sortable : true, align: 'center'},
						{display: '驾驶员电话', name : 'driver_tel', width : calcColumn(0.05,105,240), hide: true,sortable : false, align: 'center'},
	 			        {display: 'ALARM_TYPE', name : 'alarm_type', width : 90, sortable :false, align: 'center',hide:true,toggle:false},
	 			        {display: '处理', name : '', width : calcColumn(0.05,60,240), sortable : false, align: 'center',process: reWriteLaterLink1},
	 			        {display: '经度' ,name : 'longitude',width : 80,hide:true,toggle:false},
	 			        {display: '纬度' ,name : 'latitude',width : 80,hide:true,toggle:false},
	 			        {display: 'VIN' ,name : 'vehicle_vin',width : 80,hide:true,toggle:false},
	 			        {display: 'flag' ,name : 'operate_flag',width : 80,hide:true,toggle:false}
	 	        ],
	 	       sortname: 'alarm_date', //首次加载的排序字段
	 	       sortorder: 'desc',  //升序OR降序
	 	       sortable: true,   //是否支持排序
	 	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	 	       usepager :false,  //是否分页
	 	       resizable: false,  //是否可以设置表格大小
	 	       useRp: true,    // 是否可以动态设置每页显示的结果数
	 	       rp: 6,  //每页显示记录数
	 	       rpOptions : [ 2, 5, 10, 15, 20 ],   // 可选择设定的每页结果数
	 	       showTableToggleBtn: true,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	 	       showToggleBtn: false
	 	       
	 	     });
	 }	
	 
	 /**
	  * chengjin
	  *加载通勤车告警(油量告警)
	  */
	 function oilalarmload(){
	 	var oiltab = jQuery('#oiltab');
	 	 oiltab.flexigrid({
			url: '<s:url value="/ftlyInfoNew/getStealAlarmDisposeList.shtml" />',
	  		dataType: 'json',    //json格式
      		params: [
		 		{ name: 'alarm_type_id', value: ""},
		 		{ name: 'deal_flag', value: "0"},
		 		{ name: 'chooseorgid', value: jQuery('#Userorganizationid').val()},
		 		{ name: 'start_time', value: start_time},
		 		{ name: 'end_time', value: end_time},
		 		{ name: 'gridflag', value: '1'},
		 		{ name: 'isChuli', value: '1'}],
  			height:90,
	 	 	colModel : [
				//{display: '<input type="checkbox" id="checkallalarm" name="checkallalarm"  value="123" onclick="allchooseFlag(this)"/>',name : '',width : 25,sortable : false,hide:false,align: 'center',process:rewritecheckbox},
				{display: ''        , name : '',width : 20, sortable : false ,align: 'center' ,process: reWritephoto},
				{display: '班车号', name : 'vehicle_code', width : calcColumn(0.02,90,240), sortable : false, align: 'center',escape: true},
				{display: '车牌号', name : 'vehicle_ln', width : calcColumn(0.04,90,240), sortable : false, align: 'center',escape: true},
				{display: '告警类型', name : 'oilbox_state', width : calcColumn(0.04,100,240), sortable : false, align: 'center'},
				{display: '处理状态', name : 'is_valid', width : calcColumn(0.05,75,240), sortable : false, align: 'center'},//,process: reWritedeal_flag
				{display: '告警时间', name : 'report_time', width : calcColumn(0.12,100,240), sortable : false, align: 'center'},
				{display: '变动油量(L)', name : 'add_Oill', width : calcColumn(0.04,85,240), sortable : false, align: 'center',escape: true},
				{display: '地理位置' , name : 'zonename', width : calcColumn(0.3,150,240), sortable :false, align: 'center'},
				{display: '处理', name : '', width : calcColumn(0.05,60,240), sortable : false, align: 'center',process: reWriteOilLink},
				{display: '处理人' , name : 'user_name', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true},
				{display: '处理意见' , name : 'operate_desc', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true},
				{display: '驾驶员' , name : 'driver_name', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true},
				{display: '联系电话' , name : 'driver_tel', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true},
				{display: '' , name : 'longitude', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true},
				{display: '' , name : 'latitude', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true},
				{display: '' , name : 'addOill', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true},
				{display: '' , name : 'oilboxMass', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true},
				{display: '' , name : 'ftly_id', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true},
				{display: '' , name : 'oilboxState', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true},
				{display: '' , name : 'vin_code', width : 80, sortable :false, align: 'center',hide:true,toggle:false,escape: true}
				],	
	 	       sortname: 'report_time', //首次加载的排序字段
	 	       sortorder: 'desc',  //升序OR降序
	 	       sortable: true,   //是否支持排序
	 	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	 	       usepager :false,  //是否分页
	 	       resizable: false,  //是否可以设置表格大小
	 	       useRp: true,    // 是否可以动态设置每页显示的结果数
	 	       rp: 3,  //每页显示记录数
	 	       rpOptions : [ 3, 5, 10, 15, 20 ],   // 可选择设定的每页结果数
	 	       showTableToggleBtn: true,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	 	       showToggleBtn: false
	 	     });
	 }
	 
	 /*function getonChecked(nodes,falg) {	
			var carList = new Array();
			var strsql = "";
				if(falg) {
					// 当前是复选选择状态
					for(var i = 0, len = nodes.length; i < len; i++) {
						nodes[i].checkedOld = true;
						if("pIcon" != nodes[i].iconSkin) {
							strsql += "'"+nodes[i].id+"'"+",";
						}
					}
				} else {
					for(var i = 0, len = nodes.length; i < len; i++) {
						nodes[i].checkedOld = false;
						if("pIcon" != nodes[i].iconSkin) {
							strsql += "'"+nodes[i].id+"'"+",";
						}
					}		
				}
				GPSDwr.getVehcile(strsql,falg);	
		}*/
	 
	 
	 
	 
	/**
	 * 选择树上车辆标点方法，list为vin数组，flag为选择状态
	 */
	function decodeSelectV(list, flag) {
		if (list.length > 0) {
			var strsql = "";
			if(flag == true){
				for ( var i = 0; i < list.length; i++) {
					if(i == list.length-1){
		        		strsql += "'"+list[i]+"'";
		            }
		        	else{
		        		strsql +="'"+list[i]+"',";
		           	}	
				}
				schoolcarlist = strsql;
				//GPSDwr.getVehcileByVinS(strsql,getVehcileByVin_callback);
				//通勤车二期修改,根据“筛选车辆“条件显示地图覆盖物
				isRefresh=false;
				childSel(strsql);
			}
			else{
				for(var i = 0; i < list.length; i++){
					if(list[i] == tipOpenVin){
						openTip_Close();
						mapObj.closeTip(); 
					}
					mapObj.removeOverlayById(list[i]);
					
				}
				//openTip_Close();
			}
			
		} /*else {
			if (flag==true) {
				GPSDwr.getVehcileByVin(list[0],getVehcileByVin_callback);
			}else{
				mapObj.removeOverlayById(list[0]);
			}
		}*/
	}
    //根据筛选条件显示地图车辆
    function childSel(strsql){
    	switch(id)
    	{
    	case 1:
    		GPSDwr.getVehcileBySel('all',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
    		break;
    	case 2:
    		GPSDwr.getVehcileBySel('online',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
    		break;
    	case 3:
    		GPSDwr.getVehcileBySel('offline',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
    		break;
    	case 4:
    		GPSDwr.getVehcileBySel('run',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
    		break;
    	case 5:
    		GPSDwr.getVehcileBySel('stop',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
    		break;
    	case 6:
    		GPSDwr.getVehcileBySel('alarm',strsql,jQuery("#organizationid").val(),quickSelectVehicle);
    		break;
    	}
    }
	function dvrButtonOnclick(treeNode){
		//var carList = new Array();
		//carList.push(treeNode.id);
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		treeObj.checkNode(treeNode,true,true,true);
		//treeNode.checkedOld = true;
		
		
		
		//decodeSelectV(carList, treeNode.checked);

		GPSDwr.returnTipInfo(treeNode.id,returnTipInfo_dvr_callback);
		
	}
function returnTipInfo_dvr_callback(date){
		
		var TerminalViBean = date[0];
		var vin = TerminalViBean.VEHICLE_VIN;
		VideoImage(TerminalViBean.vehicle_code,TerminalViBean.VEHICLE_LN,TerminalViBean.VEHICLE_VIN,
				TerminalViBean.LONGITUDE,TerminalViBean.LATITUDE,TerminalViBean.ROUTE_ID,
				'3',TerminalViBean.color,TerminalViBean.VEH_EXT_INFO,TerminalViBean.STAT_INFO,null,null,null);
}
    /**
    * 通勤车二期车辆筛选函数回调
    */
    function quickSelectVehicle(data){
    	mapObj.removeAllOverlays();//通勤车二期新增
    	if(data==null || data==''){
    		return ;
    	}
    	//根据筛选的车辆左侧树选中
    	var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
    	//treeObj.expandAll(false);//全部折叠
    	for(var i=0;i<data.length;i++){
    		var node=treeObj.getNodesByParam("id",data[i].VEHICLE_VIN, null);
    		if(node[0].isParent){
    			continue;
    		}
    		node[0].checkedOld = true;
    		treeObj.checkNode(node[0], true, true);
    		treeObj.expandNode(node[0].getParentNode(), true, false, false);
    	}
    	//在地图上添加车辆
    	getVehcileByVin_callback(data);
    }
	/**
	 * 单选车辆回调方法
	 */
	function getVehcileByVin_callback(data) {
		
		if (data != null && data.length > 0) {
			//addmarkerObj(data,false);
			addOnemarkerObjNew(data);
			//boundshowpoint();
			if(CLUSTER_OPEN_FLAG == true){
		 		normal();
			}
		}

		
		
	}
	 var clusterOptions=new MClusterOptions();
	 function setClusters(){
	 	//聚合的范围，以像素为单位
	 	clusterOptions.gridSize=27;
	 	//设置聚合的最大级别
	 	clusterOptions.maxZoom=15;
	 	//聚合点显示文字的样式
	 	clusterOptions.fontStyle=new MFontStyle();
	 	clusterOptions.fontStyle.color=0xfff000;
	 	clusterOptions.fontStyle.size=20;
	 	//设置绽放聚合中心点图标的URL，用于自定义绽放聚合中心点图标
	 	//clusterOptions.centerMarkerURL="http://api.mapabc.com/flashmap/2.3.3/images/m1.png";
	 	//设置绽放聚合绽放点图标的URL，用于自定义绽放聚合绽放点的图标
	 	//clusterOptions.aroundMarkerURL="http://www.mapabc.com/images/point.png";
	 	//绽放点离中心点的距离，以像素为单位
	 	clusterOptions.flareDistance=30;
	 	clusterOptions.isUseMarkerIcon = false;
	 }
	 function normal(){
			setClusters();
			mapObj.setClusterState(MClusterOptions.NORMAL_CLUSTER, clusterOptions);
	 }
			 
	 function flare(){
			setClusters();
			mapObj.setClusterState(MClusterOptions.FLARE_CLUSTER, clusterOptions);
		}

	 function CLUSTER_clickMouse(param){
		 if(param.clusterOverlayIds.length==1){   //如果数组长度为1，说明单击在展开点上
				var marker =mapObj.getOverlayById(param.clusterOverlayIds[0]);
				//记录打开tip的点的vin
				tipOpenVin = marker.id;
				tipOpenLon = marker.lnglat.lngX;
				tipOpenLat = marker.lnglat.latY;
				//document.write("onevmouse_click::"+lon+";"+lat);
				add_map_tiptions();
				
			}else if(param.clusterOverlayIds.length>1){  //如果数组长度大于1，说明单击在中心点上
				//var mkid = param.overlayId; 
				//var marker = mapObj.getOverlayById(mkid);
			}
	 }
			 	
	/**
	 * 单个车辆标点
	 */
	function addOnemarkerObjNew(array) {

		if (array != null && array.length > 0) {
			//mapObj.removeAllOverlays();//通勤车二期新增
			var points = mapObj.getOverlaysByType(MOverlay.TYPE_MARKER);
			var arr = new Array();
			for ( var i = 0; i < array.length; i++) {

				var lon = array[i].LONGITUDE;
				var lat = array[i].LATITUDE;
				if (lon >= 0 && lon <= 180 && lat >= 0 && lat <= 90) {

					//点的属性设置
					var markerOption = new MMarkerOptions();

					if (array[i].color == "b") {
						markerOption.imageUrl = "../newimages/arrow_blue.png";//arrow_blue.gif";
					} else if (array[i].color == "r") {
						markerOption.imageUrl = "../newimages/arrow_red.png";//arrow_red.gif";
					} else {
						markerOption.imageUrl = "../newimages/arrow_gray.png";//arrow_gray.gif";
					}
					markerOption.picAgent=false;
					markerOption.imageSize = new MSize(14, 32);

					markerOption.imageAlign = MConstants.MIDDLE_CENTER;
					var hudu = 0;
					if (array[i].DIRECTION != "FFFF") {
						hudu = array[i].DIRECTION;
					}
					markerOption.rotation = hudu;					
					if(trim(array[i].vehicle_code)=="外租"){
						   
						   markerOption.labelOption = addMarkerLabel(//
								   //array[i].VEHICLE_LN
								   "外租"
								   //nullToGb(array[i].vehicle_code) 
								   + "  "
									+ nullToZore(array[i].SPEEDING) + "km/h",
									MConstants.BOTTOM_CENTER);
					   }
					else{

						   markerOption.labelOption = addMarkerLabel(//array[i].VEHICLE_LN
							          nullToGb(array[i].vehicle_code) + "号  "
									+ nullToZore(array[i].SPEEDING) + "km/h",
									MConstants.BOTTOM_CENTER);
					  }
					

					//markerOption.tipOption = addFlashTip(array[i]); 
					markerOption.canShowTip = true;
					markerOption.isEditable=false; //设置点是否可编辑。
					markerOption.hasShadow=false;  //是否显示阴影。
					markerOption.zoomLevels=[]; //设置点的缩放级别范围。
					markerOption.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
					markerOption. dimorphicColor='0x00A0FF'; //设置第二种状态的颜色
					//document.write("MMarker::"+lon+";"+lat);
					var marker = new MMarker(new MLngLat(lon, lat),markerOption);
					marker.id = array[i].VEHICLE_VIN;
					mapObj.addEventListener(marker, MConstants.MOUSE_CLICK,onevmouse_click);

					arr.push(marker);
					//刷新，添加气泡提示，通勤车二期
					if(tipOpenVin !=null && tipOpenVin!="" && isRefresh){
						   if(array[i].VEHICLE_VIN == tipOpenVin){
							   tipOpenLon = lon;
							   tipOpenLat = lat;
							   add_map_tiptions();
						   }
					}else{
						//关闭所有气泡
						mapObj.closeTip();
						openTip_Close();
					}
				}
			}
			if(points.length>0){
				mapObj.addOverlays(arr,false);
				boundshowpoint();
			}
			else{
				if(arr.length > 1){
					mapObj.addOverlays(arr,false);
					boundshowpoint();
				}
				else{
					mapObj.addOverlays(arr,true);
					mapObj.setZoomLevel(16);
				}
			}
			 
			//add_map_tiptions();
		} else {
			alert("没有坐标数据,无法标点!");
		}
	}

	/*
	* 异步添加地图tip信息
	*/
	function add_map_tiptions(){
		//查询tip信息
		GPSDwr.returnTipInfoNew(tipOpenVin,tipOpenLon,tipOpenLat,returnTipInfoNew_callback);
	}

	
	//地图上点的鼠标点击事件
	function onevmouse_click(event){
		var mkid = event.overlayId;
		
		var marker = mapObj.getOverlayById(mkid);

		//记录打开tip的点的vin
		tipOpenVin = marker.id;
		tipOpenLon = marker.lnglat.lngX;
		tipOpenLat = marker.lnglat.latY;
		//document.write("onevmouse_click::"+lon+";"+lat);
		add_map_tiptions();

	}


	
/**
 * 气泡信息返回数据回调函数
 */
	function returnTipInfoNew_callback(date){
		
		var TerminalViBean = date[0];
// 		if(trim(TerminalViBean.vehicle_code) == '外租') {
// 			if(!isRefresh){
// 				alert("该车无GPS位置信息，不能查看车辆详情！");
// 			}
// 			isRefresh=false;//重置是否从刷新方法来
// 			return false;
// 		}
		var lon = TerminalViBean.LONGITUDE;
		var lat = TerminalViBean.LATITUDE;
		mapObj.openTip(new MLngLat(lon ,lat),addFlashTip(TerminalViBean));//显示地图信息窗口。
		/**var vin = TerminalViBean.VEHICLE_VIN;
		var mk = mapObj.getOverlayById(vin);
		if(mk != null){
			
			mk.option.tipOption = addFlashTip(TerminalViBean);
			mk.option.canShowTip=true;
			mapObj.updateOverlay(mk);
			
			var test = mapObj.openOverlayTip(vin);
			
			
		}*/
		//document.write("returnTipInfoNew_callback::"+lon+";"+lat);
	}
	// 添加气球提示信息
	function addFlashTip(TerminalViBean){ 
		
		//var seldiv = document.getElementById('selectline');
		    var tipOption=new MTipOptions();  
		    tipOption.tipType=MConstants.HTML_CUSTOM_TIP;  
		    tipOption.content=gethtml(TerminalViBean);        
		    tipOption.tipAlign=MConstants.BOTTOM_CENTER;   
	        tipOption.tipPosition=new MPoint(0,15);   
		    return tipOption;
	}
	/**
	 * 多点地图显示适应
	 */
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
			
			mapObj.setLngLatBounds(new MLngLatBounds(new MLngLat(minx,miny),new MLngLat(maxx,maxy))); 
		}
		
	}
/**
 * 过滤空字符和空
 */
function nullToZore(str){
	if(str == null || str == "" ||  str == "undefined" || str == " " || str =="FFFF"){
		return 0;
	}
	else{
		return str;
	}
}
/**
 * 过滤空字符为外租
 */
function nullToGb(str){
	if(str == null || str == ""|| str =="null" ||  str == "undefined" || str == " " || str =="FFFF"){
		return "外租";
	}
	else{
		return str;
	}
}
/**
 * 时间格式截取
 */
	function timeoptval(time){
		
		if(time != null && time.trim() != "" && time.trim() != "undefined"){
			
			return time.substring(0, time.length-2);
		}
		else{
			return "0";
		}
	}
/**
 * 添加地图上点的标签文字样式
 */
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
	    labeloption.backgroundColor=0x464545;//设置标注的背景颜色   
	    labeloption.borderColor=0x181818;//设置标注的边框颜色   
	    labeloption.content=pointname;//标注的显示内容   
	   //设置标注左上角相对于面对象中心的锚点。标注左上角与面对象中心重合时，像素坐标原点(0,0)   
	    //labeloption.labelPosition=new MPoint(0,25);   
	    //设置对准点正下方的文字标签锚点   
	    labeloption.labelAlign=direction;//TOP_CENTER;   
	   return  labeloption;
	}

	// 构建气球框内容
	function gethtml(TerminalViBean){
		var httpinfo = "";//"<div  style='width:240px; height:140px;top:8px;left:6px;'>";
		    httpinfo +=	"<div class='balloon'>";
		    httpinfo +=	"<div class='balloonLeft'></div>";
		    httpinfo +=	"<div class='balloonMain'>";
		    httpinfo +=	"<div class='balloonTil'>";
		    if(!tiptabNo){
		    	httpinfo +=	"<a id='BalloonTab1'  class='tabfocus' onclick='BalloonConsole(false);'>实时信息</a>";	
			    httpinfo +=	"<a id='BalloonTab2'  class='tab' onclick='BalloonConsole(true);'>车辆信息</a>";
			}else{
				httpinfo +=	"<a id='BalloonTab1'  class='tab' onclick='BalloonConsole(false);'>实时信息</a>";	
			    httpinfo +=	"<a id='BalloonTab2'   class='tabfocus' onclick='BalloonConsole(true);'>车辆信息</a>";
			}
		    
		    httpinfo +=	"<a  class='close' onclick='mapObj.closeTip();openTip_Close();'></a>";
		    httpinfo +=	"</div>";
		    
		    if(!tiptabNo){
		    	httpinfo +=	"<div id='BalloonInfo1' class='data' style='z-index:1'>";
			}
		    else{
		    	httpinfo +=	"<div id='BalloonInfo1' class='data' style='z-index:1;display:none;'>";
			}
		    
		    httpinfo +=	"<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
		    httpinfo +=	"<tr>";
		    httpinfo +=	"<td width='20%' height='24' align='left'>核载人数：</td>";
		    /* test="#session.adminProfile.en_code == '0000600001'" */
		    <s:if test="#session.adminProfile.en_code == '0000850260'">
	   	 		httpinfo +=	"<td width='24%' align='left'><strong>"+nullToZore(TerminalViBean.LIMITE_NUMBER)+"(人)</strong></td>";
	    	</s:if>
		    <s:else>
		   	 	httpinfo +=	"<td width='24%' align='left'><strong>"+nullToZore(TerminalViBean.STU_NUM)+"/"+nullToZore(TerminalViBean.LIMITE_NUMBER)+"(人)</strong></td>";
		    </s:else>
		    
		    //httpinfo +=	"<td width='30%'  align='right'><img src='../images/qipaoimages/"+(TerminalViBean.POWER_STATE=='1'?'dianping1.gif':'dianping0.gif')+"' width='22' height='22' title='"+(TerminalViBean.POWER_STATE=='1'?'终端主电源掉电':'终端主电源供电')+"'></td>";
		    if(nullToZore(TerminalViBean.ftly_flag==0)) {
		    	httpinfo +=	"<td width='20%'>油箱油量：</td>";
		    	httpinfo +=	"<td width='24%' ><strong>"+nullToZore(TerminalViBean.oilbox_mass)+"L</strong></td>";
		    } else if(nullToZore(TerminalViBean.ftly_flag==1)) {
		    	httpinfo +=	"<td width='20%'>瞬时油耗：</td>";
		    	httpinfo +=	"<td width='24%' ><strong>"+nullToZore(TerminalViBean.OIL_INSTANT)+"L</strong></td>";
		    } else {
		    	httpinfo +=	"<td width='20%'>油箱油量：</td>";
		    	httpinfo +=	"<td width='24%' ><strong>0L</strong></td>";
		    }
		    httpinfo +=	"</tr>";
		    httpinfo +=	"<tr>";   
		    httpinfo +=	"<td height='24' align='left'>行驶速度：</td>";       
		    httpinfo +=	"<td><strong>"+nullToZore(TerminalViBean.SPEEDING)+"&nbsp;km/h</strong></td>";  
		    httpinfo += "<td align='left'>点火状态：</td>";
		    httpinfo +=	"<td align='left'><strong>"+(TerminalViBean.STAT_INFO==0?'关':'开')+"</strong></td>";
		    /* httpinfo +=	"<td align='left'>引擎转速：</td>";
		    httpinfo +=	"<td align='left'><strong>"+nullToZore(TerminalViBean.ENGINE_ROTATE_SPEED)+"rpm</strong></td>";  */
		    httpinfo +=	"</tr>"; 
		    
		    httpinfo += "<tr>";
		    httpinfo += "<td height='24' align='left'>定位状态：</td>";
		    httpinfo += "<td align='left'><strong>"+(TerminalViBean.dingwei_stat!=0?'有效':'无效')+"</strong></td>";
		    httpinfo += "<td align='left'></td>";
		    httpinfo +=	"<td align='left'></td>";
		    //httpinfo += "<td><img src='../images/qipaoimages/"+(TerminalViBean.POWER_STATE=='1'?'dianping1.gif':'dianping0.gif')+"' width='22' height='22'></td>";
		    httpinfo += "</tr>";
		    
		    httpinfo +=	"<tr>";
		    httpinfo +=	"<td height='24' align='left'>上报时间：</td>";
		    httpinfo +=	"<td colspan='2' align='left'>"+timeoptval(TerminalViBean.TERMINAL_TIME)+"</td>"; 
		    httpinfo +=	"<td height='24' align='right'>";
			    
		    //httpinfo +=	"<img src='../images/btn_tool/"+(TerminalViBean.VEH_EXT_INFO=='1'?'camera.gif':'camera2.gif')+"' width='20' height='20' title='"+(TerminalViBean.VEH_EXT_INFO=='1'?'3G可用':'3G不可用')+"'>";        
			httpinfo +=	"<img src='../newimages/qipaoimages/"+(TerminalViBean.POWER_STATE=='1'?'dianping1.png':'dianping0.png')+"' width='24' height='20' title='"+(TerminalViBean.POWER_STATE=='1'?'终端主电源掉电':'终端主电源供电')+"'></td>";
		    httpinfo +=	"</tr>";        
		    httpinfo +=	"</table>";        
		    httpinfo +=	"</div>";

		    if(!tiptabNo){
		    	httpinfo +=	"<div id='BalloonInfo2' class='data' style='display:none;z-index:2;'>";
			}else{
				httpinfo +=	"<div id='BalloonInfo2' class='data' style='z-index:2'>";
			}
		    
		    
		    httpinfo +=	"<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
		    httpinfo +=	"<tr>";  
		    httpinfo +=	"<td height='24'>所属单位：</td>";    
		    httpinfo +=	"<td colspan='3'><strong>"+encodeHtml(TerminalViBean.ENTER_SHORT_NAME)+"</strong></td>";     
		    httpinfo +=	"</tr>";
		    //模式三企业车辆TAB内容
		     /* test="#session.adminProfile.en_code == '0000600001' or #session.adminProfile.en_code == '0000600002' or #session.adminProfile.en_code == '0000600003' or #session.adminProfile.en_code == '0000600004' or #session.adminProfile.en_code == '0000105556'" */
		    <s:if test="#session.adminProfile.en_code == '0000850260'">
		    </s:if>
		    <s:else>
		    httpinfo += "<tr>";
		    httpinfo += "<td height='24'>当前线路：</td>";
		    httpinfo += "<td colspan='3'><strong>"+encodeHtml((TerminalViBean.ROUTE_NAME==null?'':TerminalViBean.ROUTE_NAME))+"</strong></td>";
		    httpinfo += "</tr>"; 
			</s:else>
	 
		   	httpinfo += "<tr>";
		   	httpinfo += "<td width='20%'>车辆牌号：</td>";
		   	if(TerminalViBean.VEHICLE_LN.length>7){
				var titleln=TerminalViBean.VEHICLE_LN.substr(TerminalViBean.VEHICLE_LN.length-7);
				var titleln="*"+titleln;
				httpinfo += "<td width='24%'><strong>"+encodeHtml(titleln)+"</strong></td>";
			}
		   	else{
		   		httpinfo += "<td width='24%'><strong>"+encodeHtml(TerminalViBean.VEHICLE_LN)+"</strong></td>";
			}
		   	
		   	httpinfo += "<td width='20%' height='24'>班&nbsp;车&nbsp;号：</td>";
		   	httpinfo += "<td width='24%'><strong>"+TerminalViBean.vehicle_code+"</strong></td>";
		   	httpinfo += "</tr>";
		    /* test="#session.adminProfile.en_code == '0000600001' or #session.adminProfile.en_code == '0000600002' or #session.adminProfile.en_code == '0000600003' or #session.adminProfile.en_code == '0000600004' or #session.adminProfile.en_code == '0000105556'" */
		   	<s:if test="#session.adminProfile.en_code == '0000850260'">
		    httpinfo +=	"<tr>";
		    httpinfo += "<td width='20%' height='24'>驾&nbsp;驶&nbsp;员：</td>";
		    if(TerminalViBean.SETDRIVER_NAME!=null&&TerminalViBean.SETDRIVER_NAME.length>7){
				var lndriver=TerminalViBean.SETDRIVER_NAME.substr(0,6);
				var lndriver=lndriver+"*";
				httpinfo += "<td width='24%'><strong>"+encodeHtml(lndriver)+"</strong></td>";
			}
		   	else{
		   		httpinfo += "<td width='24%'><strong>"+encodeHtml((TerminalViBean.SETDRIVER_NAME==null?'未登录':TerminalViBean.SETDRIVER_NAME))+"</strong></td>";
			}
		   	//httpinfo += "<td width='24%'><strong>"+encodeHtml((TerminalViBean.SETDRIVER_NAME==null?'未登录':TerminalViBean.SETDRIVER_NAME))+"</strong></td>";
		    httpinfo +=	"<td height='24' width='20%'>手机号码：</td>";
		    if(encodeHtml((TerminalViBean.SETDRIVER_TEL==null?'未知':TerminalViBean.SETDRIVER_TEL)).length >11) {
		    	var dddriver_tel = "*"+encodeHtml((TerminalViBean.SETDRIVER_TEL==null?'未知':TerminalViBean.SETDRIVER_TEL)).substring(0,11);
				httpinfo +=	"<td width='24%'><strong>"+dddriver_tel+"</strong></td>";
		    } else {
		    	httpinfo +=	"<td width='24%'><strong>"+encodeHtml((TerminalViBean.SETDRIVER_TEL==null?'未知':TerminalViBean.SETDRIVER_TEL))+"</strong></td>";
		    }
		   	httpinfo +=	"</tr>";
		   	</s:if>
		    <s:else>
		    httpinfo +=	"<tr>";
		    httpinfo += "<td width='20%' height='24'>驾&nbsp;驶&nbsp;员：</td>";
		    if(TerminalViBean.DRIVER_NAME!=null&&TerminalViBean.DRIVER_NAME.length>0) {
		    	if(TerminalViBean.DRIVER_NAME!=null&&TerminalViBean.DRIVER_NAME.length>7){
					var lndriver=TerminalViBean.DRIVER_NAME.substr(0,6);
					var lndriver=lndriver+"*";
					httpinfo += "<td width='24%'><strong>"+encodeHtml(lndriver)+"</strong></td>";
				}else{
			   		httpinfo += "<td width='24%'><strong>"+encodeHtml((TerminalViBean.DRIVER_NAME==null?'未登录':TerminalViBean.DRIVER_NAME))+"</strong></td>";
				}
		    } else {
			    if(TerminalViBean.SETDRIVER_NAME!=null&&TerminalViBean.SETDRIVER_NAME.length>7){
					var lndriver=TerminalViBean.SETDRIVER_NAME.substr(0,6);
					var lndriver=lndriver+"*";
					httpinfo += "<td width='24%'><strong>"+encodeHtml(lndriver)+"</strong></td>";
				}else{
			   		httpinfo += "<td width='24%'><strong>"+encodeHtml((TerminalViBean.SETDRIVER_NAME==null?'未登录':TerminalViBean.SETDRIVER_NAME))+"</strong></td>";
				}
		    }
		   	//httpinfo += "<td width='24%'><strong>"+encodeHtml((TerminalViBean.SETDRIVER_NAME==null?'未登录':TerminalViBean.SETDRIVER_NAME))+"</strong></td>";
		    httpinfo +=	"<td height='24' width='20%'>手机号码：</td>";
		    if(encodeHtml((TerminalViBean.DRIVER_TEL==null?"未知":TerminalViBean.DRIVER_TEL)).length >11) {
		    	var dddriver_tel = "*"+encodeHtml((TerminalViBean.DRIVER_TEL==null?TerminalViBean.SETDRIVER_TEL:TerminalViBean.DRIVER_TEL)).substring(0,11);
				httpinfo +=	"<td width='24%'><strong>"+dddriver_tel+"</strong></td>";
		    } else {
		    	httpinfo +=	"<td width='24%'><strong>"+encodeHtml((TerminalViBean.DRIVER_TEL==null?"未知":TerminalViBean.DRIVER_TEL))+"</strong></td>";
		    }
		   	httpinfo +=	"</tr>";
			</s:else>
		    httpinfo +=	"</table>";      
		    httpinfo +=	"</div>";    
		    httpinfo +=	"<div class='toolbarqp'>"; 
		    <s:if test="#session.perUrlList.contains('111_3_1_4')">     
		    httpinfo +=	"<input class='btnLink_tool5' style='float: none;' title='信息调度' type='button' onclick=\"openFramePage('";// <a class='tool5' title='信息调度'
		    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','5";	    
		    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
		    			+null+","+null+","+null+")\" />"; 
		    </s:if>
		    <s:if test="#session.perUrlList.contains('111_3_1_10')"> 
		    if(TerminalViBean.color =="b"   && TerminalViBean.STAT_INFO == 1){
			    httpinfo +=	"<input class='btnLink_tool4' style='float: none;' title='车辆拍照' type='button' onclick=\"PhotoImage('";//<a class='tool4' title='车辆拍照' 
			    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','4";
			    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
			    +null+","+null+","+null+")\" />"; 
		    }else{
		    	httpinfo +=	"<input class='btnLink_tool4h' style='float: none;' title='车辆拍照'  type='button' onclick=\"PhotoImage('";//<a class='tool4h' title='车辆拍照' 
			    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','4";
			    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
			    +null+","+null+","+null+")\" />";
			}
		    </s:if>
		    /* 泰安用户显示 */
			
			<s:if test="#session.perUrlList.contains('111_3_1_11')"> 
		    if(TerminalViBean.color =="b" && TerminalViBean.STAT_INFO == 1  && TerminalViBean.VEH_EXT_INFO == 1){    
			    httpinfo +=	"<input class='btnLink_tool_vedio' style='float: none;' title='视频监控' type='button' onclick=\"VideoImage('";
			    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','3";
			    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
			    +null+","+null+","+null+")\" />"; 
		    }
		    else{
		    	httpinfo +=	"<input class='btnLink_tool_vedioh' style='float: none;' title='视频监控' type='button' onclick=\"VideoImage('";
			    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','3";
			    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
			    +null+","+null+","+null+")\" />";
			}
		    </s:if>
		    <s:if test="#session.perUrlList.contains('111_3_1_12')"> 
		    if(TerminalViBean.color =="b" && TerminalViBean.STAT_INFO == 1  && TerminalViBean.VEH_EXT_INFO == 1){  
			    httpinfo +=	"<input class='btnLink_tool_look'  style='float: none; title='视频回放' type='button'' onclick=\"replayVideoImage('";
			    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','7";	    
			    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
			    +null+","+null+","+null+")\" />";
		    }  
		    else{
		    	httpinfo +=	"<input class='btnLink_tool_lookh' style='float: none;' title='视频回放' type='button' onclick=\"replayVideoImage('";
			    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','7";	    
			    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
			    +null+","+null+","+null+")\" />";
			}
		    </s:if>
		    <s:if test="#session.perUrlList.contains('111_3_1_5')">
		    httpinfo +=	"<input class='btnLink_tool1' style='float: none;' title='轨迹回放'  type='button' onclick=\"openFramePage('";//<a class='tool1' title='轨迹回放' 
		    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','1";
		    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
		    +null+","+null+","+null+")\" />";
		    </s:if>
		    <s:if test="#session.perUrlList.contains('111_3_1_6')">    
		    
		    httpinfo +=	"<input class='btnLink_tool2' style='float: none;' title='重点监控'  type='button' onclick=\"openFramePage('";//<a class='tool2' title='重点监控' 
		    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','2";
		    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
		    		 +null+","+null+","+null+")\" />";
		    </s:if>
		    <s:if test="#session.perUrlList.contains('111_3_1_7')">
		    httpinfo +=	"<input class='btnLink_tool6' style='float: none;' title='告警处理'  type='button' onclick=\"openFramePage('";//<a class='tool6' title='告警处理' 
		    httpinfo += encodeHtml(TerminalViBean.vehicle_code)+"','"+encodeHtml(TerminalViBean.VEHICLE_LN)+"','"+TerminalViBean.VEHICLE_VIN+"','"+TerminalViBean.LONGITUDE+"','"+TerminalViBean.LATITUDE+"','"+TerminalViBean.ROUTE_ID+"','6";	    
		    httpinfo +="','"+TerminalViBean.color+"','"+TerminalViBean.VEH_EXT_INFO+"','"+TerminalViBean.STAT_INFO+"',"
		    			+null+","+null+","+null+")\" />";
		    </s:if>
		    
		    httpinfo +=	"</div>";  
		    httpinfo +=	"<div class='arrow'></div>";    
		    httpinfo +=	"</div>";
		    httpinfo +=	"<div class='balloonRight'></div>";    
		    httpinfo +=	"</div>";   
		    
		   // testsidelayerHead(TerminalViBean.VEHICLE_LN,TerminalViBean.VEHICLE_VIN,TerminalViBean.LONGITUDE,TerminalViBean.LATITUDE,TerminalViBean.ROUTE_ID,jiaodianid,
		    //		TerminalViBean.color,TerminalViBean.VEH_EXT_INFO,TerminalViBean.STAT_INFO,null,null,null);
			  
		return httpinfo;
		
	}
	
	function BalloonConsole(f){
		tiptabNo = f;
		var BalloonTab1 = document.getElementById("BalloonTab1");
		var BalloonTab2 = document.getElementById("BalloonTab2");
		var BalloonInfo1 = document.getElementById("BalloonInfo1");
		var BalloonInfo2 = document.getElementById("BalloonInfo2");
		if(!tiptabNo){
			BalloonTab1.className="tabfocus";
			BalloonTab2.className="tab";
			//BalloonInfo1.style.visibility="visible";
			//BalloonInfo1.style.z-index="1";
			BalloonInfo1.style.display="";
			//BalloonInfo2.style.visibility="hidden";
			//BalloonInfo2.style.z-index="2";
			BalloonInfo2.style.display="none";
			
		}
		else if(tiptabNo){
			BalloonTab1.className="tab";
			BalloonTab2.className="tabfocus";
			//BalloonInfo1.style.visibility="visible";
			BalloonInfo1.style.display="none";
			//BalloonInfo2.style.visibility="hidden";
			BalloonInfo2.style.display="";
		}
	}

/**
 * 轨迹回放，实时监控，消息调度，
 */
	function openFramePage(code,ln,vin,lon,lat,
			routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,
			alarmid,alarmtypeid,alarmtime){
		var iframeobj = document.getElementById("iframeshowArea");
    	if(iframeobj != null){
    		iframeobj.src ="";
    	}

		testsidelayerHead(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);

		var titleln=ln;
		if(titleln.length>7){
			titleln=code+"("+titleln.substr(titleln.length-7)+")";
			titleln="*"+titleln;
		}
		titleln=code+","+titleln;

		jQuery('#popArea').mk_sidelayer('set_title',encodeHtml(titleln));
		
		if(optpageid == 1){//轨迹回放
			//jQuery('#popArea1').mk_sidelayer('show');
		    jQuery('#popArea').mk_sidelayer('reload', 
				    '<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
				    +'&route_id='+routeid+'&optpageid='+optpageid 
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		}
		if(optpageid == 2){//重点监控
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		}
		if(optpageid == 6){//告警处理
			/*
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);*/
		   jQuery('#popArea').mk_sidelayer('reload', {
			    	    'url': '<s:url value="/gps/iFramechooseAction.shtml" />', 
			    	     'query_param': {'vin': vin, 'lon':lon,'lat':lat,'routeid':routeid,
				    	                  'optpageid':optpageid,'alarmid':alarmid,'alarmtypeid':alarmtypeid,
				    	                  'alarmtime':alarmtime,'sourceid':'1'}
                    });
			change_side_images('#aa'+optpageid);
		}
		if(optpageid == 5){//消息调度
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		}
		if(optpageid == 4){//车辆拍照
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		}
		
		if(optpageid == 3){//视频监控
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		} 
		if(optpageid == 7){//视频回放
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		} 
		
		startsideflash(optpageid,vin,alarmid,alarmtypeid,alarmtime);
		//intsideflash = window.setTimeout("sidelayerrealflash()",flushMap);    
	}
	/**
	 * 刷新浮动框按钮交点
	 */
	function change_side_images(target) {
		jQuery('.mk-sidelayer-tools').find('li').removeClass('selected');
		jQuery(target).addClass('selected');
    }

	
	function testsidelayerHead(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
		//openFramePage(flag,ln,vin,lon,lat,routeid,optpageid);
		//jQuery('#popArea').mk_sidelayer('set_title', ln);
		ln = encodeHtml(ln);
		var sidebuttonHtml="<ul>";
		//消息调度	
		<s:if test="#session.perUrlList.contains('111_3_1_4')">
		sidebuttonHtml +="<li id='aa5'><a href='javascript:void(0)' title='信息调度' onclick=openFramePage('";
		sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','5";	    
		sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool1.png'/></a></li>";
		</s:if>
		//拍照
		<s:if test="#session.perUrlList.contains('111_3_1_10')"> 
		if(color =="b"   && STAT_INFO == 1){
			sidebuttonHtml+="<li id='aa4'><a href='javascript:void(0)' title='车辆拍照' onclick=PhotoImage('";
			sidebuttonHtml+= code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','4";	    
			sidebuttonHtml+="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool2.png'/></a></li>";
		}else{
			sidebuttonHtml+="<li id='aa4'><a href='javascript:void(0)' title='车辆拍照' onclick=PhotoImage('";
			sidebuttonHtml+= code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','4";	    
			sidebuttonHtml+="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool2h.png'/></a></li>";
		}
		</s:if>
		
		<s:if test="#session.perUrlList.contains('111_3_1_11')"> 
		if(color =="b"  && STAT_INFO == 1 && VEH_EXT_INFO == 1){ 
			sidebuttonHtml +="<li id='aa3'><a href='javascript:void(0)' title='视频监控' onclick=VideoImage('";
			sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','3";
			sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool3.png'/></a></li>";
		}else{
			sidebuttonHtml +="<li id='aa3'><a href='javascript:void(0)' title='视频监控' onclick=VideoImage('";
			sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','3";
			sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool3h.png'/></a></li>";
		}
		</s:if>
		//视频回放
		<s:if test="#session.perUrlList.contains('111_3_1_12')"> 
		if(color =="b" && STAT_INFO == 1  && VEH_EXT_INFO == 1){
			sidebuttonHtml+="<li id='aa7'><a href='javascript:void(0)' title='视频回放' onclick=replayVideoImage('";
			sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','7";
			sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool7.png'/></a></li>";
		}else{
			sidebuttonHtml+="<li id='aa7'><a href='javascript:void(0)' title='视频回放' onclick=replayVideoImage('";
			sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','7";
			sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool7_1.png'/></a></li>";
		}
		</s:if>
		//轨迹回放
		<s:if test="#session.perUrlList.contains('111_3_1_5')"> 
		sidebuttonHtml+="<li id='aa1'><a href='javascript:void(0)' title='轨迹回放' onclick=openFramePage('";
		sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','1";	    
		sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool5.png'/></a></li>";
		</s:if>
		//重点监控
		<s:if test="#session.perUrlList.contains('111_3_1_6')"> 
		sidebuttonHtml+="<li id='aa2'><a href='javascript:void(0)' title='重点监控' onclick=openFramePage('";
		sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','2";	    
		sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool6.png'/></a></li>";
		</s:if>
		//告警处理
		<s:if test="#session.perUrlList.contains('111_3_1_7')"> 
		sidebuttonHtml+="<li id='aa6'><a href='javascript:void(0)' title='告警处理' onclick=openFramePage('";
		sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','6";	    
		sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/ico_warning.png'/></a></li>";
		</s:if>
		sidebuttonHtml+="</ul>";
		
		jQuery('.mk-sidelayer-tools').html(sidebuttonHtml);
		
		if(jiaodianid != ""){
			change_side_images('#aa'+jiaodianid);
		}
		
		//openFramePage(flag,ln,vin,lon,lat,routeid,optpageid);
	}


	// 拍照图片点击处理
	function PhotoImage(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
		//if(color =="b"   && STAT_INFO  == 1){
			openFramePage(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);
		//}
		//else{
		//	alert("车辆已熄火，请稍后再尝试车辆拍照！");
		//}
	}
	// 视频点击处理
	function VideoImage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
		//if(color =="b"   && VEH_EXT_INFO == 1){
			openFramePage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);
		//}
		//else{
		//	alert("3G视频不在线，请稍后再尝试视频监控！");
		//}
		
		
	}
	//视频回放点击处理
	function replayVideoImage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
		//if(color =="b"   && VEH_EXT_INFO == 1){
			openFramePage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);
		//}
		//else{
		//	alert("3G视频不在线，请稍后再尝试视频回放！");
		//}
	}

/**
 * 批量拍照
 */
	function callsendPhotoList(){
		//var vinlist = getCheckedCars();
		/*if(vinlist.length > 0){
			var vin = "";
			for(var i=0; i<vinlist.length; i++) {
		    	
		    		vin += vinlist[i]+",";	
		    }*/
			
			if(jQuery('#sendphotolist').css('display') == 'none'){
				document.getElementById("sendPhotoListIframe").src ="<s:url value='/gps/SendPhtotListAction.shtml'/>";
				jQuery('#sendphotolist').css('display','block');
				//jQuery('#sendphotolist').css('position','absolute');
				//jQuery('#sendphotolist').css('bottom','80px');
				//jQuery('#sendphotolist').css('left','5px');
				//document.getElementById("sendphotolist").style = "height:210px;position:absolute;bottom:84px;left:5px;";
				//document.getElementById("sendphotolist").className = "float-opt";
				jQuery('#sendMsglist').css('display','none');
			}
			
		//}
		/*else{
			alert("请选择车辆!");
			
		}*/
	}

		function closecallsendPhotoList(){
			jQuery('#sendphotolist').attr('style','width:230px;height:101px;position:absolute;bottom:84px;left:5px;z-index:100;display: none;');
			document.getElementById("sendPhotoListIframe").src = "";  
		}
/**
 * 批量消息
 */
	function callsendMsgList(){
		/*var vinlist = getCheckedCars();
		if(vinlist.length > 0){
			var vin = "";
			for(var i=0; i<vinlist.length; i++) {
		    		vin += vinlist[i]+",";
		    }*/
		    if(jQuery('#sendMsglist').css('display') == 'none'){
		    	document.getElementById("sendMsgListIframe").src ="<s:url value='/gps/SendMsgSAction.shtml'/>";
				jQuery('#sendMsglist').css('display','block');
				jQuery('#sendMsglist').removeClass('float-opt');
				jQuery('#sendMsglist').addClass('float-opt');
				jQuery('#sendphotolist').css('display','none');
			}
			
		/*}else{
			alert("请选择车辆!");
		}*/
	}
 function closecallsendMsgList(){
	 	document.getElementById("sendMsgListIframe").src ="";
		jQuery('#sendMsglist').attr('style','width:230px;height:220px;position:absolute;bottom:75px;left:5px;z-index:100;display: none;');
	}
//定时刷新车辆位置
 var isRefresh=false;
 function reallookvreal(){
	 isRefresh=true;//标识刷新方法
	 // 判断是否鼠标在地图上有操作
	 if(!mouseControlFlag){
		//刷新前判断tip是否是开启的
		 	if(tipIsOpen){//
		 		funcClose=true;
		 	}
		 	else{//
		 		funcClose=false;
		 	}
		 	var vinlist = getCheckedCars();

		 	if(vinlist!=null && vinlist.length>0){
		 		var strsql = "";
		 		for(var i=0; i<vinlist.length; i++) {

			   		if(i == vinlist.length-1){
			       		strsql += "'"+vinlist[i]+"'";
			        }
			       	else{
			       		strsql +="'"+vinlist[i]+"',";
			        }
		 	    }
		 	    if(strsql != null && strsql !=""){
		 	    	//GPSDwr.getVehcileByVinS(strsql,getVehcileByVinsReal_callback);
		 	    	childSel2(strsql);//通勤车二期新增，根据车辆筛选条件刷新地图车辆
		 		}
		 	}
		 	
	 }
	 intOneinit = window.setTimeout("reallookvreal()",flushMap);
 }
 
 
 
 function childSel2(strsql){
 	switch(id)
 	{
 	case 1:
 		GPSDwr.getVehcileBySel('all',strsql,jQuery("#organizationid").val(),getVehcileByVinsReal_callback);
 		break;
 	case 2:
 		GPSDwr.getVehcileBySel('online',strsql,jQuery("#organizationid").val(),getVehcileByVinsReal_callback);
 		break;
 	case 3:
 		GPSDwr.getVehcileBySel('offline',strsql,jQuery("#organizationid").val(),getVehcileByVinsReal_callback);
 		break;
 	case 4:
 		GPSDwr.getVehcileBySel('run',strsql,jQuery("#organizationid").val(),getVehcileByVinsReal_callback);
 		break;
 	case 5:
 		GPSDwr.getVehcileBySel('stop',strsql,jQuery("#organizationid").val(),getVehcileByVinsReal_callback);
 		break;
 	case 6:
 		GPSDwr.getVehcileBySel('alarm',strsql,jQuery("#organizationid").val(),getVehcileByVinsReal_callback);
 		break;
 	}
 }

 function getVehcileByVinsReal_callback(data){
 	updatemarkerObjNew(data);
 	//flare();
 	if(CLUSTER_OPEN_FLAG == true){
 		normal();
	}
 	//normal();
 	//boundshowpoint();
 	//intOneinit = window.setTimeout("reallookvreal()",flushMap);
 }
 
//全选车辆标点刷新
function updatemarkerObjNew(array){
	 
	if(array != null && array.length > 0){
		  if(CLUSTER_OPEN_FLAG == true){
			  mapObj.setClusterState(MClusterOptions.NO_CLUSTER, clusterOptions);
		  }
		  var arr = new Array();
		  if(array.length > 1){
			  for(var i=0; i < array.length;i++){
					
				  var lon = array[i].LONGITUDE;
				  var lat = array[i].LATITUDE;
				  var vin = array[i].VEHICLE_VIN;
		
					//点的属性设置	
				   var hudu = 0;
				   
					if(array[i].DIRECTION!="FFFF"){
						   hudu = array[i].DIRECTION;
					}
				
				   var marker = mapObj.getOverlayById(vin);
				   if(marker != null ){
					  if(array[i].color=="b"){
						   marker.option.imageUrl="../newimages/arrow_blue.png";
					  }
					  else if(array[i].color=="r"){
						  marker.option.imageUrl="../newimages/arrow_red.png";
					  }
					  else{
						  marker.option.imageUrl="../newimages/arrow_gray.png";
					  }
					   marker.option.rotation = hudu;//array[i].VEHICLE_LN
					   if(trim(array[i].vehicle_code)=="外租"){
						   marker.option.labelOption = addMarkerLabel(
								   //array[i].VEHICLE_LN
								   "外租"
									+ "  "
									+ nullToZore(array[i].SPEEDING) + "km/h",
									MConstants.BOTTOM_CENTER);
					   }else{
							marker.option.labelOption = addMarkerLabel(
									nullToGb(array[i].vehicle_code) + "号   "
									+ nullToZore(array[i].SPEEDING) + "km/h",
									MConstants.BOTTOM_CENTER);
					  }
					   //marker.option.labelOption=addMarkerLabel(nullToGb(array[i].vehicle_code)+"号   "+nullToZore(array[i].SPEEDING)+"km/h",MConstants.BOTTOM_CENTER);
					   
					   
					  /* marker.option.picAgent=false;
					   marker.option.canShowTip = true;
					   marker.option.isEditable=false; //设置点是否可编辑。
					   marker.option.hasShadow=false;  //是否显示阴影。
					   marker.option.zoomLevels=[]; //设置点的缩放级别范围。
					   marker.option.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
					   marker.option.dimorphicColor='ox04881d'; //设置第二种状态的颜色
					  */
					   marker.lnglat=new MLngLat(lon,lat);
					   //mapObj.addOverlay(marker, false);
					   mapObj.updateOverlay(marker);
					   mapObj.markerMoveTo(marker.id, new MLngLat(lon,lat), hudu, 0);
					   //mapObj.addEventListener(marker,MConstants.MOUSE_CLICK,onevmouse_click);	
				   }
				  if(tipOpenVin !=null && tipOpenVin!=""){
					   //mapObj.openOverlayTip(tipOpenVin);
					   if(vin == tipOpenVin){
						   tipOpenLon = lon;
						   tipOpenLat = lat;
						   add_map_tiptions();
					   }
				  }   
			 }
				 
		  }else {
			  var lon = array[0].LONGITUDE;
			  var lat = array[0].LATITUDE;
			  var vin = array[0].VEHICLE_VIN;
				
				//点的属性设置	
			   var hudu = 0;
			   
				if(array[0].DIRECTION!="FFFF"){
					   hudu = array[0].DIRECTION;
				}
				
			   var marker = mapObj.getOverlayById(vin);
			   if(marker != null ){
				   
				  if(array[0].color=="b"){
					   marker.option.imageUrl="../newimages/arrow_blue.png";
				  }
				  else if(array[0].color=="r"){
					  marker.option.imageUrl="../newimages/arrow_red.png";
				  }
				  else{
					  marker.option.imageUrl="../newimages/arrow_gray.png";
				  }
				   marker.option.rotation = hudu;
				   //	array[0].VEHICLE_LN
				   if(trim(array[0].vehicle_code)=="外租"){
					   marker.option.labelOption = addMarkerLabel(//
							   //array[0].VEHICLE_LN
							   "外租"
							   //nullToGb(array[0].vehicle_code) 
					   			+ "  "
								+ nullToZore(array[0].SPEEDING) + "km/h",
								MConstants.BOTTOM_CENTER);
				   }else{
						marker.option.labelOption = addMarkerLabel(//array[i].VEHICLE_LN
								nullToGb(array[0].vehicle_code) + "号  "
								+ nullToZore(array[0].SPEEDING) + "km/h",
								MConstants.BOTTOM_CENTER);
				  }
				   //marker.option.labelOption=addMarkerLabel(nullToGb(array[0].vehicle_code)+"号   "+nullToZore(array[0].SPEEDING)+"km/h",MConstants.BOTTOM_CENTER);

				  /* marker.option.picAgent=false;
				   marker.option.canShowTip = true;
				   marker.option.isEditable=false; //设置点是否可编辑。
				   marker.option.hasShadow=false;  //是否显示阴影。
				   marker.option.zoomLevels=[]; //设置点的缩放级别范围。
				   marker.option.isDimorphic=true;// 设置点是否高亮显示。高亮显示与可编辑有冲突，只能设置一个，不能同时设置。
				   marker.option.dimorphicColor='ox04881d'; //设置第二种状态的颜色
				   */
				   if(updateOnePointsBounds(lon,lat)){
					   mapObj.updateOverlay(marker);
					   mapObj.markerMoveTo(marker.id, new MLngLat(lon,lat), hudu, 0);
				   }else{
					   marker.lnglat=new MLngLat(lon,lat);
					   mapObj.addOverlay(marker,true);
					   
					   //boundshowpoint();
			       }
				   
				   //mapObj.setZoomLevel(16);
				   //mapObj.updateOverlay(marker);
				   
				   //var arr=new Array();,
				   //arr.push(marker.id);
				   //mapObj.setGPSFocus(MConstants.CENTER_FOCUS,arr);
				   //mapObj.markerMoveTo(marker.id, new MLngLat(lon,lat), hudu, 0);
				   //mapObj.panTo(new MLngLat(lon,lat));
				   //mapObj.setCenter(new MLngLat(lon,lat));
				  // mapObj.addEventListener(marker,MConstants.MOUSE_CLICK,onevmouse_click);	
			   }
			  if(tipOpenVin !=null && tipOpenVin!=""){//
				   //mapObj.openOverlayTip(tipOpenVin);
			       if(vin == tipOpenVin){
					   tipOpenLon = lon;
					   tipOpenLat = lat;
					   add_map_tiptions();
			   	   }
			  }  
				
		  }
		  
	}
 }	

function updateOnePointsBounds(lon,lat){
	var bounds = mapObj.getLngLatBounds();  
	
	if( bounds.southWest.lngX < lon && lon < bounds.northEast.lngX && bounds.southWest.latY < lat && lat < bounds.northEast.latY){
		return true;
    }else{return false;}
}

function MapMoveToPoint(lon,lat){
	var bounds=mapObj.getLngLatBounds();

	if(lon < bounds.southWest.lngX || lat < bounds.southWest.latY || lon > bounds.northEast.lngX || lat > bounds.northEast.latY){
		mapObj.panTo(new MLngLat(lon,lat));
	}
}

	/**
	 * 告警区显示控制
	 */
	function alarmAreaShowControl(sourceid){
		
		if(jQuery('#alarmDateListssArea').css("display")=="none"){//隐藏状态
			jQuery('#bottom-tab-upordown').removeClass('bottom-tab-up');
			
			jQuery('#bottom-tab-upordown').addClass('bottom-tab-down');
			
			jQuery('#alarmDateListssArea').css("display","block");
			
		}
		else if(jQuery('#alarmDateListssArea').css("display")=="block"){//展开状态
			jQuery('#bottom-tab-upordown').removeClass('bottom-tab-down');
			jQuery('#bottom-tab-upordown').addClass('bottom-tab-up');
			jQuery('#alarmDateListssArea').css("display","none");
		}

		if(jQuery('#alarmDateListssArea').css("display")=="block"&&sourceid=="1"){
			searchTabList();
		}
		
		AutoWidthHeight();
	}

/**
 * 左侧树区域显示控制
 */
function HideandShowControl(){
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').css("display","block");
		jQuery('#leftdiv').css("display","none");
	}else{//隐藏状态
		jQuery('#middeldiv').css("display","none");
		jQuery('#leftdiv').css("display","block");
	}
	AutoWidthHeight();
}
/**
 * 树tab且换
 */
 function tabSwitch(id){
     openNodesId = new Array();
	 checkNodesId = new Array();
	 closeNodesId = new Array();
	 if (!check()) {
			return;
		}
		 
 	jQuery("#vehicleLn").val("");
	if(id=="enttabid"){
		jQuery("#treetabid").removeClass();
		jQuery("#enttabid").addClass('tabfocus');
		jQuery("#treetabid").addClass('tab');
		//下两行加载树
		treeType='route1';
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}
	else if(id=="treetabid"){
		jQuery("#enttabid").removeClass();
		jQuery("#enttabid").addClass('tab');
		jQuery("#treetabid").addClass('tabfocus');
		//下两行加载树
		treeType='route';
		jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
	}
	mapObj.removeAllOverlays();
}
	function treebtnRefresh(){
		refreshTree();
		window.clearTimeout(sercListThread);
		sercListThread = window.setTimeout("treebtnRefresh()",300000);
	}

	function treebtnReset(){
		if (!check()) {
			return;
		}
		jQuery("#vehicleLn").val("");
		openNodesId = new Array();
		checkNodesId = new Array();
		closeNodesId = new Array();
		if(treeType=="route1"){//组织树重置
			treeType='route1';
			jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
		}
		else{//线路树重置
			treeType='route';
			jQuery.fn.zTree.init(jQuery('#treeDemo'), setting);
		}
		mapObj.removeAllOverlays();
	}

	//地图上方工具条缩放
	function maptoolbarIsshow(option){
		var map = document.getElementById("maptoolbar");
		//var up = document.getElementById("fudong_up");
		var down = document.getElementById("fudong_down");

		if(option=="up"){
			map.style.display = "none";

			//up.style.display = "none";
			down.style.display = "";
		}
		else if(option=="down"){
			map.style.display = "";
			//up.style.display = "";
			down.style.display = "none";
		}
	}

	//拉框缩小结束
	//测量距离
	function distanceObj(){   
	    this.lngX;this.latY;this.lngX1;this.latY1;this.polylineCoor;   
	}   
	var disObj=new distanceObj();   
	var IS_mousePolyline = false;
	function mousePolyline(){   
	    mapObj.removeOverlaysByType(MOverlay.TYPE_POLYLINE);  
	    mapObj.setCurrentMouseTool(MConstants.PAN_WHEELZOOM); 
	    if(!IS_mousePolyline){
	        //mapObj.addEventListener(mapObj,ADD_OVERLAY,mousePolylineDistance); 
	        var option={};   
   			option.hasCircle=true;   
			option.hasPrompt=true;  
	    	mapObj.setCurrentMouseTool(MConstants.RULER,option);
	    	IS_mousePolyline = true;
	    	var drawLine = document.getElementById("drawLine");
	    	drawLine.src="../images/fudong_test2.gif";

	    	var smallToBig = document.getElementById("smallToBig");
			smallToBig.src ="../images/fudong_big.gif";
			IS_mousezoomin = false;
	    	var bigToSmall = document.getElementById("bigToSmall");
	    	bigToSmall.src="../images/fudong_small.gif";
			IS_mousezooout = false;
			//changeCursor();
			//mapObj.addEventListener(mapObj,ADD_OVERLAY,changeCursor);
			//mapObj.addEventListener(mapObj,MEASURE_END,endMeasure);
			mapObj.addEventListener(mapObj, MConstants.MEASURE_END,endMeasure);
			//mapObj.addEventListener(mapObj, MConstants.MEASURE_STEP,measureStep);
			
						
	    }
	    else{
	    	mapObj.setCurrentMouseTool(MConstants.PAN_WHEELZOOM);
	    	IS_mousePolyline = false;
	    	var drawLine = document.getElementById("drawLine");
	    	drawLine.src="../images/fudong_test.gif";
	    	mapObj.removeEventListener(mapObj, MConstants.MEASURE_END,endMeasure);
	    	
	    	//endMeasure();
	    }
	       
	}  
	function endMeasure(param){  
		var option={};   
			option.hasCircle=true;   
		option.hasPrompt=true;  
    	mapObj.setCurrentMouseTool(MConstants.RULER,option);
    	
	}
	
	function changeCursor(){
		var map=document.getElementById("iCenter");
		var mapsub=map.getElementsByTagName("DIV");
		mapsub[0].style.cursor="../images/openhandcju.cur";
		//document.getElementById("iCenter").style.cursor="help";
	}

	// 聚合触发
	var CLUSTER_OPEN_FLAG = false;
	function CLUSTER_OPEN(){

		if(CLUSTER_OPEN_FLAG == false){
			CLUSTER_OPEN_FLAG = true;
			document.getElementById("CLUSTER_OPEN_ID").src="../newimages/sidelayerimages/fudong_together.gif";
			normal();
		}
		else{
			CLUSTER_OPEN_FLAG = false;
			mapObj.setClusterState(MClusterOptions.NO_CLUSTER, clusterOptions);
			document.getElementById("CLUSTER_OPEN_ID").src="../newimages/sidelayerimages/fudong_together2.gif";
		}
		
	}



	//添加实时交通
	var addTileLayer_TRAFFIC_ISSHOW = false;
	function addTileLayer_TRAFFIC(){
		IS_mousePolyline = false;
		if(!addTileLayer_TRAFFIC_ISSHOW){
			var tl = new MTileLayer(MConstants.TL_TRAFFIC);  //添加实时交通层
			mapObj.addTileLayer(tl); 
		    addTileLayer_TRAFFIC_ISSHOW = true;
		    var TILELAYERTRAFFIC = document.getElementById("TILELAYERTRAFFIC");
		    TILELAYERTRAFFIC.src = "../images/fudong_trans2.gif";
		}
		else{
			mapObj.removeTileLayer(MConstants.TL_TRAFFIC);
			addTileLayer_TRAFFIC_ISSHOW = false;
			var TILELAYERTRAFFIC = document.getElementById("TILELAYERTRAFFIC");
		    TILELAYERTRAFFIC.src = "../images/fudong_trans.gif";
		}
		
		 
	}

	//拉框放大
	var IS_mousezoomin = false;
	function mousezoomin(){
		
		if(!IS_mousezoomin){
			mapObj.setCurrentMouseTool(MConstants.FRAME_ZOOMIN);
			IS_mousezoomin = true;
			var smallToBig = document.getElementById("smallToBig");
			smallToBig.src ="../images/fudong_big2.gif";

			var drawLine = document.getElementById("drawLine");
	    	drawLine.src="../images/fudong_test.gif";
	    	IS_mousePolyline = false;
	    	var bigToSmall = document.getElementById("bigToSmall");
	    	bigToSmall.src="../images/fudong_small.gif";
			IS_mousezooout = false;
			//endMeasure();
		}
		else{
			mapObj.setCurrentMouseTool(MConstants.PAN_WHEELZOOM);
			IS_mousezoomin = false;
			var smallToBig = document.getElementById("smallToBig");
			smallToBig.src ="../images/fudong_big.gif";
		}
		
	}
	//拉框放大结束

	//拉框缩小
	var IS_mousezooout = false;
	function mousezooout(){
		
		if(!IS_mousezooout){
			mapObj.setCurrentMouseTool(MConstants.FRAME_ZOOMOUT);
			IS_mousezooout = true;
			var bigToSmall = document.getElementById("bigToSmall");
			bigToSmall.src="../images/fudong_small2.gif";

			
	    	var drawLine = document.getElementById("drawLine");
	    	drawLine.src="../images/fudong_test.gif";
	    	IS_mousePolyline = false;
			var smallToBig = document.getElementById("smallToBig");
			smallToBig.src ="../images/fudong_big.gif";
	    	IS_mousezoomin = false;
	    	//endMeasure();
		}
		else{
			mapObj.setCurrentMouseTool(MConstants.PAN_WHEELZOOM);
			IS_mousezooout = false;
			var bigToSmall = document.getElementById("bigToSmall");
			bigToSmall.src="../images/fudong_small.gif";
		}
	}
	//拉框缩小结束
	
	//地图全屏
	var isScreen = false;
	function AllScreen(){
		//HideandShowControl();
		//alarmAreaShowControl();
		
		var meunid = document.getElementById("wrapper");
		
		var leftdiv = document.getElementById("leftdiv");
		var daohangtiao = document.getElementById("titleBarMap");
		var iCenter = document.getElementById("iCenter");
		var AlermInfoDiv =  document.getElementById("alarmDateArea");
		//var copyrightid = document.getElementById("footer");
		var Lefttab = document.getElementById("middeldiv");
		var allscreenid = document.getElementById("allscreenid");
		var mapdiv = document.getElementById("mapbartab");
		if(isScreen){
			//设置正常
			//WALL_web();
			leftdiv.style.display="";
			daohangtiao.style.display="";
			AlermInfoDiv.style.display="";
			//meunid.style.display="";
			//copyrightid.style.display="";
			allscreenid.src="../images/fudong_fullscreen.gif";
			isScreen=false;
			mapdiv.style.top = "130px";
		}
		else{
			//WALL_web();
			leftdiv.style.display="none";
			daohangtiao.style.display="none";
			
			AlermInfoDiv.style.display="none";
			//meunid.style.display="none";
			//copyrightid.style.display="none";
			allscreenid.src="../images/fudong_back.gif";
			isScreen=true;
			mapdiv.style.top = "92px";
		}
		if(Lefttab)Lefttab.style.display="none";

		//testWidthHeight();
		AutoWidthHeight();
	}
	function WALL_web()
	{
	   var WshShell = new ActiveXObject('WScript.Shell')
	   WshShell.SendKeys('{F11}');
	}

/**
 * 浮动框刷新
 */
function startsideflash(vjiaodianid,vin,valarmid,valarmtypeid,valarmtime){
	jiaodianid = vjiaodianid;
	sidevin = vin;
	alarmid = valarmid;
	alarmtypeid = valarmtypeid;
	alarmtime = valarmtime;
	intsideflash = window.setTimeout("sidelayerrealflash()",flushMap);
}
 
 var alarmid,alarmtypeid,alarmtime = null;
	function sidelayerrealflash(){
		clearTimeout(intsideflash);
		if(sidevin != null && sidevin !=""){
			if(jQuery('#popArea').mk_sidelayer('is_show')){
				GPSDwr.returnTipInfo(sidevin,returnsidelayerFlash_callback);
			}
			else{
				clearTimeout(intsideflash);
			}
		}
		else{
			clearTimeout(intsideflash);
		}
	}
function returnsidelayerFlash_callback(data){

	var TerminalViBean = data[0];
	testsidelayerHead(TerminalViBean.vehicle_code,TerminalViBean.VEHICLE_LN,TerminalViBean.VEHICLE_VIN,TerminalViBean.LONGITUDE,TerminalViBean.LATITUDE,TerminalViBean.ROUTE_ID,jiaodianid,
		    		TerminalViBean.color,TerminalViBean.VEH_EXT_INFO,TerminalViBean.STAT_INFO,alarmid,alarmtypeid,alarmtime);
	startsideflash(jiaodianid,TerminalViBean.VEHICLE_VIN,alarmid,alarmtypeid,alarmtime);
	
}

function floatDivLocationControl(divid){
	var objstate = jQuery("#"+divid).css("display");
	if(objstate=="block"){
		var objwidth = parseInt(jQuery("#"+divid).width());
		var objleft = parseInt(jQuery("#"+divid).css("left"));

		var winWidth = parseInt(jQuery(window).width());
		var docWidth = parseInt(jQuery(document).width());
		if(1256 < winWidth < docWidth){
			//jQuery("#"+divid).css("left","");
			if((objwidth+objleft) >= winWidth){
				jQuery("#"+divid).css("left",winWidth-objwidth-19);
			}
			else{
				//jQuery("#"+divid).css("left",winWidth-objwidth-19);
			}	
		}else if(winWidth < 1256   < docWidth){
			if((objwidth+objleft) >= 1256){
				jQuery("#"+divid).css("left",1256-objwidth-19);
			}
			else{
				//jQuery("#"+divid).css("left",1256-objwidth-19);
			}
		}
	}
	
	
	
	 //jQuery("#"+divid).css("left",((jQuery(document).width())/2-(parseInt(jQuery("#"+divid).width())/2))+"px")
     //.css("top",((jQuery(document).height())/2-(parseInt(jQuery("#"+divid).height())/2))+"px");
	
}
	/*
	 * 页面中间高度自适应
	 *参数： 
	 *    |- width_exclude: 排除计算宽度的对象【例如左侧树宽度固定】
	 *    |- width_include: 需要计算宽度的对象
	 *    |- width_offset: 调整宽度像素值
	 *    |- height_exclude: 排除计算高度的对象
	 *    |- height_include: 需要计算高度的对象
	 *    |- height_offset:调整高度像素值
	 */
	jQuery( function() {
		mapInit('iCenter');
		initpop();
		 jQuery(window).wresize(function(){
			 floatDivLocationControl("sendMsglist");
			 floatDivLocationControl("sendphotolist");
			 AutoWidthHeight();
			 
			 popAutoWH();
		});
		AutoWidthHeight();

		popAutoWH();
	});

	function AutoWidthHeight(){
		
		//计算右侧区域高度
		if(jQuery('#alarmDateListssArea').css("display")=="none"){
			//jQuery('#bottom-tab-upordown').removeClass('bottom-tab-down');
			//jQuery('#bottom-tab-upordown').addClass('bottom-tab-up');
			jQuery('#content_rightside').mk_autoresize( {
				height_exclude : [ '.titleBar', '#alarmDateArea' ],
				height_include : '#mapdiv',
				height_offset : 5
			});
			
			
		}
		else{
			jQuery('#content_rightside').mk_autoresize( {
				height_exclude : [ '.titleBar', '#alarmDateArea' ],
				height_include : '#mapdiv',
				height_offset : 120
			});
			
		}
		//计算地图高度
		jQuery('#mapdiv').mk_autoresize( {
			height_include : '#iCenter',
			height_offset : 0
		});

		/*
		*表格宽度自适应
		*/
		if(jQuery('#alarmDateListssArea').css("display")=="block"){
			jQuery("#sostab").reWidth(0);
			jQuery("#sostab").reWidth(jQuery('#alarmDateListssArea').width());

			jQuery("#overspdtab").reWidth(0);
			jQuery("#overspdtab").reWidth(jQuery('#alarmDateListssArea').width());

			jQuery("#nottimetab").reWidth(0);
			jQuery("#nottimetab").reWidth(jQuery('#alarmDateListssArea').width());
			jQuery("#notsitetab").reWidth(0);
			jQuery("#notsitetab").reWidth(jQuery('#alarmDateListssArea').width());
			jQuery("#latetab").reWidth(0);
			jQuery("#latetab").reWidth(jQuery('#alarmDateListssArea').width());
			jQuery("#nofulltab").reWidth(0);
			jQuery("#nofulltab").reWidth(jQuery('#alarmDateListssArea').width());
			jQuery("#oiltab").reWidth(0);
			jQuery("#oiltab").reWidth(jQuery('#alarmDateListssArea').width());
		}

		/*if(jQuery('#titleBarMap').css("display")=="none"){

			jQuery('#content_rightside').mk_autoresize( {
				height_exclude : [ '.titleBar', '#alarmDateArea' ],
				height_include : '#mapdiv',
				height_offset : -60
			});
			//计算地图高度
			jQuery('#mapdiv').mk_autoresize( {
				height_include : '#iCenter',
				height_offset : 0
			});
			
			jQuery('#content').mk_autoresize( {
				width_include : '#content_leftside' ,
				width_offset : 0
			});
			jQuery('#content_rightside').mk_autoresize( {
				width_include : '#mapdiv' ,
				width_offset : 0
			});
			jQuery('#content').mk_autoresize( {
				width_include : '#iCenter',
				width_offset : 0
			});
		}*/
		jQuery(window).mk_autoresize({
			height_include: '#content',
			height_exclude: ['#header', '#footer'],
			height_offset: 0,
			width_include: ['#header', '#content', '#footer'],
			width_offset: 0});
		
		//计算中区高度
		jQuery('#content').mk_autoresize( {
			height_include : [ '#content_rightside', '#content_leftside' ],
			height_offset : 0
		});
		//计算左测区域高度
		jQuery('#content_leftside').mk_autoresize( {
			height_exclude : [ '.treeTab', '.msg-snapshot' ],
			height_include : '.treeBox',
			height_offset : 94

		});
		
		//计算右侧区域高度
		if(jQuery('#alarmDateListssArea').css("display")=="none"){
			//jQuery('#bottom-tab-upordown').removeClass('bottom-tab-down');
			//jQuery('#bottom-tab-upordown').addClass('bottom-tab-up');
			jQuery('#content_rightside').mk_autoresize( {
				height_exclude : [ '.titleBar', '#alarmDateArea' ],
				height_include : '#mapdiv',
				height_offset : 5
			});
			
			
		}
		else{
			jQuery('#content_rightside').mk_autoresize( {
				height_exclude : [ '.titleBar', '#alarmDateArea' ],
				height_include : '#mapdiv',
				height_offset : 120
			});
			
		}
		//计算地图高度
		jQuery('#mapdiv').mk_autoresize( {
			height_include : '#iCenter',
			height_offset : 0
		});

		/*
		*表格宽度自适应
		*/
		if(jQuery('#alarmDateListssArea').css("display")=="block"){
			jQuery("#sostab").reWidth(0);
			jQuery("#sostab").reWidth(jQuery('#alarmDateListssArea').width());

			jQuery("#overspdtab").reWidth(0);
			jQuery("#overspdtab").reWidth(jQuery('#alarmDateListssArea').width());

			jQuery("#nottimetab").reWidth(0);
			jQuery("#nottimetab").reWidth(jQuery('#alarmDateListssArea').width());
		}

		
		/*jQuery("#popArea").width("800px");
		jQuery("#iframeshowArea").width("800px");*/
	}

	var popwidth = "500px";
	var popheight = "540px";
	var popMaxwidth = "655px";
	var popMaxheight = "716px";
	var popIframeH = "484px";
	var popIframeMH = "660px";	

	function popAutoWH(){
		if(jQuery(window).height() >= 900){
			jQuery('#popArea').mk_sidelayer('set_width', popMaxwidth);
			jQuery('#popArea').mk_sidelayer('set_height', popMaxheight);
			if(jQuery('#popArea').mk_sidelayer("is_show") ==true){
				
				jQuery("#popArea").width(popMaxwidth);
				jQuery("#popArea").height(popMaxheight);
				jQuery("#iframeshowArea").width(popMaxwidth);
				jQuery("#iframeshowArea").height(popIframeMH);
			}
			
			//popwidth = "655px"; //
			//popheight = "716px";
		} 
		else{
			jQuery('#popArea').mk_sidelayer('set_width', popwidth);
			jQuery('#popArea').mk_sidelayer('set_height', popheight);
			if(jQuery('#popArea').mk_sidelayer("is_show") ==true){
				
				jQuery("#popArea").width(popwidth);
				jQuery("#popArea").height(popheight);
				jQuery("#iframeshowArea").width(popwidth);
				jQuery("#iframeshowArea").height(popIframeH);
			}
			//popwidth = "500px"; //
			//popheight = "540px";
		}
		 
	}
	
	function getTimes(){
		var myDate = new Date();
  		var month = '';
  		var day = '';
  		if(myDate.getMonth()<9){
  			month = "0"+(myDate.getMonth()+1);
  		}else{
  			month = (myDate.getMonth()+1);
  		}
  		if(myDate.getDate()<=9){
  			day = "0"+(myDate.getDate());
  		}else{
  			day = myDate.getDate();
  		}
  		  	
  	end_time = myDate.getFullYear()+"-"+month+"-"+day;
  	myDate.setDate(myDate.getDate() - 2); //查询三天，-2就够了
  		if(myDate.getMonth()<9){
  			month = "0"+(myDate.getMonth()+1);
  		}else{
  			month = (myDate.getMonth()+1);
  		}
  		if(myDate.getDate()<=9){
  			day = "0"+(myDate.getDate());
  		}else{
  			day = myDate.getDate();
  		}
  	start_time=myDate.getFullYear()+"-"+month+"-"+day;
	}
	//截取报告
	function trim(s){
	    return s==null?"":s.replace(/^\s*/, "").replace(/\s*$/, "");
	}
	
</script>


