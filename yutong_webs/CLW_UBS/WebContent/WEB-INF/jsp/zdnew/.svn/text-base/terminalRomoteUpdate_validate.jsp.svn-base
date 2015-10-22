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
		// 预缓存皮肤，数组第一个为默认皮肤	   
		var terminalList = jQuery('#terminalList');
		terminalList.flexigrid({
			url: '<s:url value="/zdnf/getTerminalUpdate.shtml"/>',
			dataType: 'json',
			height: '375',
			width: '200',
			data: {vehicle_ln: jQuery('#vehicleln').val(),
				host_firm_ver: jQuery('#host_firm_ver').val(),
				xianshi_firm_ver: jQuery('#xianshi_firm_ver').val(),
				SHEPIN_firm_ver: jQuery('#SHEPIN_firm_ver').val()
			},
			colModel : [
			    		{display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', 
		                 name : '', 
		                 width : 20, 
		                 align: 'center', 
		                 process:reWriteCheckBox,hide:false,toggle:false},
			    		{display: '<s:text name="common.list.vehicleln" />', name : 'VEHICLE_LN', width : 60, sortable : true, align: 'center'},
			    		{display: '<s:text name="common.list.vehiclevin" />', name : 'VEHICLE_VIN', width : 100, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '<s:text name="common.list.terminal" />', name : 'TERMINAL_ID', width : 100, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '<s:text name="common.list.simcardnumber" />', name : 'SIM_CARD_NUMBER', width : 100, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '手机号', name : 'CELLPHONE', width : 80, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '<s:text name="enterprise.info.ENTERPRISE_CODE" />', name : 'ENTERPRISE_CODE', width : 80, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '<s:text name="common.list.enterprise" />', name : 'FULL_NAME', width : 140, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '主机硬件版本', name : 'host_hard_ver', width : 80, sortable : true, align: 'center'},
			    		{display: '主机固件版本', name : 'host_firm_ver', width : 80, sortable : true, align: 'center'},
			    		{display: '显示屏硬件版本', name : 'xianshi_hard_ver', width : 80, sortable : true, align: 'center'},
			    		{display: '显示屏固件版本', name : 'xianshi_firm_ver', width : 80, sortable : true, align: 'center'},
			    		{display: 'DVR硬件版本', name : 'DVR_hard_ver', width : 80, sortable : true, align: 'center'},
			    		{display: 'DVR固件版本', name : 'DVR_firm_ver', width : 80, sortable : true, align: 'center'},
			    		{display: '射频读卡器硬件版本', name : 'SHEPIN_hard_ver', width :110, sortable : true, align: 'center'},
			    		{display: '射频读卡器固件版本', name : 'SHEPIN_firm_ver', width : 110, sortable : true, align: 'center'},
			    		{display: 'SIM卡ICCID', name : 'SIM_SCCID', width : 80, sortable : true, align: 'center',hide:true,toggle:false},
			    		{display: '升级版本', name : 'UPDATE_VERSION', width : 80, sortable : true, align: 'center',process:setVersionTEXT},
			    		{display: '升级命令下发时间', name : 'OPERATE_TIME', width : 120, sortable : true, align: 'center'},
			    		{display: '状态', name : 'DEAL_STATE', width : 80, sortable : true, align: 'center',process:setStateTEXT}
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

		jQuery.ajax({
			type: 'POST',  
			url: '../zdnf/getUpdateInfo.shtml',	
			dataType: 'json',
			success: function(data) {
				if(data.length>0){
					if(data[0].host_hard_ver!=null){
						jQuery('#hardver').val(data[0].host_hard_ver);
					}
					if(data[0].host_firm_ver!=null){
						jQuery('#firmver').val(data[0].host_firm_ver);
					}
					if(data[0].connection_time!=null){
						jQuery('#timePer').val(data[0].connection_time);
					}
					if(data[0].url_address!=null){
						jQuery('#url').val(data[0].url_address);
					}
					if(data[0].dial_peer_name!=null){
						jQuery('#mainapn').val(data[0].dial_peer_name);
					}
					if(data[0].dial_peer_account!=null){
						jQuery('#mainuser').val(data[0].dial_peer_account);
					}
					if(data[0].dial_password!=null){
						jQuery('#mainpass').val(data[0].dial_password);
					}
					if(data[0].ip_address!=null){
						jQuery('#ip').val(data[0].ip_address);
					}
					if(data[0].tcp_port!=null){
						jQuery('#tcpport').val(data[0].tcp_port);
					}
					if(data[0].udp_port!=null){
						jQuery('#udpport').val(data[0].udp_port);
					}
				}
			}  
		});
		
	});

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
	function updateInfo(){
		var carsChoice = document.getElementsByName("carChoice");
		var idList = "";
		var teminalList = "";
		var simList = "";
		var vinList = "";
		for(var i=0; i<carsChoice.length; i++) {
		    if(carsChoice[i].checked==true) {
		    	var checkValue= new Array();
				checkValue=carsChoice[i].value.split("||");
		        if(idList=="") {
		        	idList = "'" + checkValue[0] + "'";
		        } else {
		        	idList=idList + "," + "'" + checkValue[0] + "'";
		        }
		        if(teminalList=="") {
		        	teminalList = "'" + checkValue[2] + "'";
		        } else {
		        	teminalList=teminalList + "," + "'" + checkValue[2] + "'";
		        }
		        if(vinList=="") {
		        	vinList = "'" + checkValue[1] + "'";
		        } else {
		        	vinList=vinList + "," + "'" + checkValue[1] + "'";
		        }
		        if(simList=="") {
		        	simList = "'" + checkValue[3] + "'";
		        } else {
		        	simList=simList + "," + "'" + checkValue[3] + "'";
		        }
		    }
		}
		
		trimAllObjs();

		if(idList==""){
			alert("请选择车辆");
			return false;
	    }

	    var udp=document.getElementById("udpport").value;
		var timeper=document.getElementById("timePer").value;
		
		var f1 = checkHardver();
		var f2 = checkFirmver();
		var f3 = checkIp();
		var f4 = checkTcpport();
		var f5 = checkMainuser();
		var f6 = checkMainpass();
		var f7 = true;
		var f9 = true;
		var f10 = true;
		if(udp!=""){
			f7 = checkUdpport();
		}
		if(timeper!=""){
			f9 = checkTimePer();
		}
		if(f1&f2&f3&f4&f5&f6&f7&f9&f10){
			if(!confirm("确认对所选车辆进行远程升级吗？")){
		    	return false;
			}else{
				jQuery.ajax({
					type: 'POST',  
					url: '../zdnew/updateInfo.shtml',	
					data: {host_hard_ver: jQuery('#hardver').val(),
							host_firm_ver: jQuery('#firmver').val(),
							connection_time: jQuery('#timePer').val(),
							url_address: jQuery('#url').val(),
							dial_peer_name: jQuery('#mainapn').val(),
							dial_peer_account: jQuery('#mainuser').val(),
							dial_password: jQuery('#mainpass').val(),
							ip_address: jQuery('#ip').val(),
							tcp_port: jQuery('#tcpport').val(),
							udp_port: jQuery('#udpport').val(),
							pID:idList,
							teminalList:teminalList,
							vinList:vinList,
							simList:simList},
					success: function(data) {
						alert("命令已下发");
						jQuery('#terminalList').flexReload();
						
					}  
				});
			}
			
		}else{
          return false;
		}
		return true;
	}

	function reWriteCheckBox(tdDiv,pid){
		tdDiv.innerHTML = '<input name="carChoice" value="' + pid + '||'+get_cell_text(pid, 2)+'||'+get_cell_text(pid, 3)+'||'+get_cell_text(pid, 4)+'"  type="checkbox" />';
	}

	function setStateTEXT(tdDiv,pid){
		if(get_cell_text(pid, 9)==get_cell_text(pid, 17)||get_cell_text(pid, 11)==get_cell_text(pid, 17)){
			if(get_cell_text(pid, 17)!='null'){
				tdDiv.innerHTML = '升级完毕';
			}else{
				tdDiv.innerHTML = '';
			}
			
		}else{
			if(get_cell_text(pid, 19)!="3"){
				if(get_cell_text(pid, 19)!='null'){
					tdDiv.innerHTML = '命令已下发';
				}else{
					tdDiv.innerHTML = '';
				}
			}else{
				tdDiv.innerHTML = '终端升级中';
			}
		}
	}

	function setVersionTEXT(tdDiv,pid){
		if(get_cell_text(pid, 17)=='null'){
			tdDiv.innerHTML = '';
		}
	}
	function setOrCancelSelect(obj) {
		 if(jQuery(obj).attr("checked")){
			jQuery("input[name='carChoice']").each(function(){
			jQuery("input[name='carChoice']").attr("checked","true");
			});
		 }else{
			 jQuery("input[name='carChoice']").each(function(){
				 jQuery("input[name='carChoice']").removeAttr("checked"); 
			});
		}
	}
	/** 查询信息 **/
	function searchVehicleList() {
		jQuery('#terminalList').flexOptions({
			newp: 1,
			params: [{name: 'enterpriseId', value: jQuery('#enterpriseId').val()},
			         {name: 'vehicle_ln', value: jQuery('#vehicleln').val()},
			         {name: 'host_firm_ver', value: jQuery('#host_firm_ver').val()},
			         {name: 'xianshi_firm_ver', value: jQuery('#xianshi_firm_ver').val()},
			         {name: 'SHEPIN_firm_ver', value: jQuery('#SHEPIN_firm_ver').val()}]
		});
		jQuery('#terminalList').flexReload();
	}
	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);
	//页面自适应
	(function (jQuery) {
		 jQuery(window).load(function (){
			 changeWidthHeight();
			 jQuery('#carList').fixHeight();
		});			
		 jQuery(window).resize(function(){
			 changeWidthHeight();
		});
	})(jQuery);
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
	
	//计算控件宽高
	function changeWidthHeight(){
		 var h = get_page_height();
		 var w = get_page_width();
		 var leftInfoDiv = document.getElementById("leftInfoDiv");
		 if(screenFlag == false && h>490) {
			 jQuery("#leftInfoDiv").height(h-165);
			 jQuery(".ztree").css('height',h-215);
			 jQuery(".bDiv").height(h-490);
			 jQuery(".flexigrid").width(w-290);
		 } else if(screenFlag == true && h>490) {
			 jQuery("#leftTabtd").height(h-165);
			 jQuery(".ztree").css('height',h-215);
			 jQuery('.bDiv').height(h-490);
			 jQuery(".flexigrid").width(w-55);
		 } else {
			 if(h>165) {
				 jQuery("#leftTabtd").height(h-165);
			 } else {
				 jQuery("#leftTabtd").height(h);
			 }
		 }
	}
	
	var screenFlag = false;
	function LeftScreen(){
		screenFlag = true;
		var left = document.getElementById("leftInfoDiv");
		var Lefttab = document.getElementById("leftTab");
		left.style.display="none";
		Lefttab.style.display="";
		
		var searchPlanId = document.getElementById("searchPlanId");
		searchPlanId.style.display="none";
		
		var leftTabtd= document.getElementById("leftTabtd");
	    leftTabtd.style.display="";	
	    changeWidthHeight();		
	}
	function midScreen(){
		screenFlag = false;
		var left = document.getElementById("leftInfoDiv");
		var Lefttab = document.getElementById("leftTabtd");
		var searchPlanId = document.getElementById("searchPlanId");
		left.style.display="";
		Lefttab.style.display="none";
		searchPlanId.style.display="";
		changeWidthHeight();			
	}  

</script>