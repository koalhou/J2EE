<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/md5/base64.js' />"></script>	
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery.wresize.js'/>"></script>
<script>
//验证硬件版本
/**角色校验**/
	function checkHardver() {
		var hardver = $('hardver');
		if (!Mat.checkRequired(hardver))
			return false;
		return true;
	}
	function checkFirmver() {
		var firmver = $('firmver');
		if (!Mat.checkRequired(firmver))
			return false;
		return true;
	}	
	function checkIp() {
		var ip = $('ip');
		if (!Mat.checkRequired(ip))
			return false;
		checkIP2();
		return true;
	}	
	function checkIP2(){		
		var ip = $('ip');
		if (!Mat.checkIp(ip,'IP地址不正确'))
			return false;
		return true;
	}
	function checkTcpport() {
		var tcpport = $('tcpport');
		if (!Mat.checkRequired(tcpport))
			return false;
		checkTcpport2();
		return true;
	}
	function checkMainuser() {
		var mainuser = $('mainuser');
		if (!Mat.checkRequired(mainuser))
			return false;
		return true;
	}	
	function checkMainpass() {
		var mainpass = $('mainpass');
		if (!Mat.checkRequired(mainpass))
			return false;
		return true;
	}
	function checkTcpport2() {
		var tcpport = $('tcpport');
		if (!Mat.checkNaturalNumber(tcpport,'必须为数字'))
			return false;
		//checklength(tcpport);
		return true;
	}
	function checklength(obj){
		if (!Mat.checkPort(obj,'端口不合法'));
			return false;
		return true;		
	}
    function checkURL(){
    	var url = $('url');
    	if (!Mat.checkURL(url))
			return false;
		return true;
    }
	function checkUdpport() {
		var udpport = $('udpport');
		if (!Mat.checkNaturalNumber(udpport,'必须为数字'))
			return false;
		//checklength(udpport);
		return true;
	}
	function checkTimePer() {
		var timePer = $('timePer');
		if (!Mat.checkNaturalNumber(timePer,'必须为数字'))
			return false;
		return true;
	}				
    /** 加载事件 **/
    function setFormEvent() {
		$('hardver').onkeyup = checkHardver;
		$('hardver').onblur = checkHardver;
		$('firmver').onkeyup = checkFirmver;
		$('firmver').onblur = checkFirmver;
		$('ip').onkeyup = checkIp;
		$('ip').onblur = checkIp;
		$('tcpport').onkeyup = checkTcpport;
		$('tcpport').onblur = checkTcpport;
		$('mainuser').onkeyup = checkMainuser;
		$('mainuser').onblur = checkMainuser;
		$('mainpass').onkeyup = checkMainpass;
		$('mainpass').onblur = checkMainpass;
		$('udpport').onkeyup = checkUdpport;
		$('udpport').onblur = checkUdpport;
		$('timePer').onkeyup = checkTimePer;
		$('timePer').onblur = checkTimePer;
		//$('url').onkeyup = checkURL;
		//$('url').onblur = checkURL;
    }
    /** 初始化样式 **/

    function setFormMessage() {

      Mat.setDefault($('tcpport'),'<span class="noticeMsg">*</span>');
      Mat.setDefault($('ip'),'<span class="noticeMsg">*</span>');
      Mat.setDefault($('mainpass'),'<span class="noticeMsg">*</span>');
      Mat.setDefault($('mainuser'),'<span class="noticeMsg">*</span>');
      Mat.setDefault($('firmver'),'<span class="noticeMsg">*</span>');
      Mat.setDefault($('hardver'),'<span class="noticeMsg">*</span>');

      Mat.setDefault($('timePer'),'');
      Mat.setDefault($('url'),'');
      Mat.setDefault($('mainapn'),'');
      Mat.setDefault($('udpport'),'');
    }   
	function searchList(vehicleVin,teminalList,simList,lnlist) {
		//alert(vehicleVin);
    	jQuery('#empDiv').css("display","none");
		jQuery('#terminalList').flexOptions({
			newp: 1,
			params: [{name: 'vehicleVin', value: vehicleVin } 
			         ]
		});
		jQuery('#terminalList').flexReload();
		document.getElementById("vinList").value = vehicleVin;
		document.getElementById("lnList").value = lnlist;
		document.getElementById("teminalList").value = teminalList;
		document.getElementById("simList").value = simList;
	}
	function trimAllObjs(){
		inputObjs=document.body.getElementsByTagName("INPUT");
		for(var i=0;i<inputObjs.length;i++){
			if(inputObjs[i].type=='text'){
				mytrim(inputObjs[i]);
			}
		}
	}
	function mytrim(obj){
		obj.value= obj.value.replace(/(^\s*)|(\s*$)/g, "");
	}
	function submitForm(){
		trimAllObjs();
		var vehicleVin=document.getElementById("vinList").value;
		var udp=document.getElementById("udpport").value;
		var timeper=document.getElementById("timePer").value;
		var tcp=document.getElementById("tcpport").value;
		if(vehicleVin==""){
			alert("请选择车辆");
	    }
		var f1 = checkHardver();
		var f2 = checkFirmver();
		var f3 = checkIp();
		var f4 = checkTcpport();
		var f5 = checkMainuser();
		var f6 = checkMainpass();
		var f7 = true;
		var f9 = true;
		var f10 = true;
			//checkURL();
		if(udp!=""){
			f7 = checkUdpport();
		}
		if(timeper!=""){
			f9 = checkTimePer();
		}
		if(f1&f2&f3&f4&f5&f6&f7&f9&f10){
		 var form = document.getElementById('terminalRomote_form');
		 form.submit();
		}else{
          return false;
		}
	}
	//window.addEvent('domready', setFormEvent);	
	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	(function($){
		// 预缓存皮肤，数组第一个为默认皮肤
		$.dialog.defaults.skin = ['aero'];
	})(art);
	/** 列表加载 **/
	jQuery(function() {
		document.getElementById("vinList").value="";
    	document.getElementById("lnList").value="";
		document.getElementById("teminalList").value="";
		document.getElementById("simList").value="";
		document.getElementById("lnList").value="";
		// 预缓存皮肤，数组第一个为默认皮肤	   
		var terminalList = jQuery('#terminalList');
		var url = '<s:url value="/xjf/vList2.shtml?vehicleVin=\'-1\'"/>';
		//alert(url);
		terminalList.flexigrid({
			url: url,
			dataType: 'json',
			height: '375',
			width: '2000',
			colModel : [
			    		{display: '<s:text name="list.number" />', name : 'rowNumber', width : 30, sortable : false, align: 'left',hide:true},
			    		{display: '<s:text name="common.list.vehicleln" />', name : 'VEHICLE_LN', width : 60, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.vehiclevin" />', name : 'VEHICLE_VIN', width : 100, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.terminal" />', name : 'TERMINAL_ID', width : 100, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.simcardnumber" />', name : 'SIM_CARD_NUMBER', width : 100, sortable : true, align: 'left',hide:true},
			    		{display: '手机号', name : 'CELLPHONE', width : 80, sortable : true, align: 'left',hide:true},
			    		{display: '<s:text name="enterprise.info.ENTERPRISE_CODE" />', name : 'ENTERPRISE_CODE', width : 80, sortable : true, align: 'left',hide:true},
			    		{display: '<s:text name="common.list.enterprise" />', name : 'FULL_NAME', width : 140, sortable : true, align: 'left',hide:true},
			    		{display: '主机硬件版本', name : 'host_hard_ver', width : 80, sortable : true, align: 'left'},
			    		{display: '主机固件版本', name : 'host_firm_ver', width : 80, sortable : true, align: 'left'},
			    		{display: '显示屏硬件版本', name : 'xianshi_hard_ver', width : 80, sortable : true, align: 'left'},
			    		{display: '显示屏固件版本', name : 'xianshi_firm_ver', width : 80, sortable : true, align: 'left'},
			    		{display: 'DVR硬件版本', name : 'DVR_hard_ver', width : 80, sortable : true, align: 'left'},
			    		{display: 'DVR固件版本', name : 'DVR_firm_ver', width : 80, sortable : true, align: 'left'},
			    		{display: '射频读卡器硬件版本', name : 'SHEPIN_hard_ver', width :110, sortable : true, align: 'left'},
			    		{display: '射频读卡器固件版本', name : 'SHEPIN_firm_ver', width : 110, sortable : true, align: 'left'},
			    		{display: 'SIM卡ICCID', name : 'SIM_SCCID', width : 80, sortable : true, align: 'left'}
			    		],
			    	   		
			    	sortname: 'VEHICLE_LN',
			    	sortorder: 'desc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: true,
			    	rp:10,	
					rpOptions : [10,20,50,100],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});
	});

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

    function choiseVehicle() {
    	var vehicleVin =document.getElementById("vinList").value;
    	var vehicleLN = document.getElementById("lnList").value;
		var teminalList = document.getElementById("teminalList").value;
		var simList = document.getElementById("simList").value;	
		var lnlist = document.getElementById("lnList").value;	
		var url = "../xj/terminalRomoteAdd.shtml?type=1&vinList="+vehicleVin;
		art.dialog.open("<s:url value='"+url+"' />",{
		title:"车辆信息",
		lock:true,
		width:950,
		height:550
	 });
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
    function changeWidthHeight(){
		var h = get_page_height();
		var w = get_page_width();
		jQuery(".flexigrid").width(w-20);
		jQuery(".bDiv").height(h-390); 
    }
    
    //页面自适应
    (function (jQuery) {
	    jQuery(window).wresize(function(){
	    changeWidthHeight();
	    jQuery('#terminalList').fixHeight();
	});
		
    jQuery(window).load(function (){
    	changeWidthHeight();
    	jQuery('#terminalList').fixHeight();
    });
    })(jQuery);
    window.addEvent('domready', setFormEvent);
    window.addEvent('domready', setFormMessage);	
</script>