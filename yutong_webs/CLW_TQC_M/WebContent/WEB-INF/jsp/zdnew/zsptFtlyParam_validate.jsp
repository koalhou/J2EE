<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script>
	/**
	 * 验证邮箱标定
	 */
	function checkOilDemarcate() {
	  var elm = $('oilDemarcate');
	  if(Wit.checkErr(elm,/^(\d)*$/)){
		 if(parseInt(elm.value)<0 || parseInt(elm.value)>1000){
	    	 Wit.showErr(elm, "请输入正确数值");
		     return false;
	     }else{	 
		     Mat.showSucc(elm);
		     return true;
	     } 
	  } else {
	    Wit.showErr(elm, "请输入数字");
	    return false;
	  }
	}
	/**
	   * 验证AD落差
	   */
	function checkAdGap() {
	    var elm = $('adGap');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	    	 if(parseInt(elm.value)<0 || parseInt(elm.value)>4095){
		    	 Wit.showErr(elm, "请输入正确数值");
			     return false;
		     }else{	 
			     Mat.showSucc(elm);
			     return true;
		     } 
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	}

	  /**
	   * 验证加油门限
	   */
	function checkAddOilLimit() {
	    var elm = $('addOilLimit');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	    	 if(parseInt(elm.value)<0 || parseInt(elm.value)>50){
		    	 Wit.showErr(elm, "请输入正确数值");
			     return false;
		     }else{	 
			     Mat.showSucc(elm);
			     return true;
		     } 
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	  }

	  /**
	   * 验证偷油门限
	   */
	  function checkStealOilLimit() {
	    var elm = $('stealOilLimit');
	    if(Wit.checkErr(elm,/^(\d)*$/)){
	    	 if(parseInt(elm.value)<0 || parseInt(elm.value)>50){
		    	 Wit.showErr(elm, "请输入正确数值");
			     return false;
		     }else{	 
			     Mat.showSucc(elm);
			     return true;
		     } 
	    } else {
	      Wit.showErr(elm, "请输入数字");
	      return false;
	    }
	  }

	  
	function setTerminalParamsEvent() {
	  Mat.setDefault($('oilDemarcate'),'');
	  Mat.setDefault($('adGap'),'');
	  Mat.setDefault($('addOilLimit'),'');
	  Mat.setDefault($('stealOilLimit'),'');
	}

	function setTerminalParamsMessage() {
      $('stealOilLimit').onkeyup = checkOilDemarcate;
      $('adGap').onblur = checkAdGap;
      $('addOilLimit').onkeyup = checkAddOilLimit;
      $('stealOilLimit').onblur = checkStealOilLimit;
	}

	// 判断参数是否有输入项目
	function checkParamsInput() {
		var ret = false;
		// 判断页面上的下拉类信息是否选择
		selectObjs=document.body.getElementsByTagName("select");
		for(var i=0;i<selectObjs.length;i++){
			  if(selectObjs[i].value.length > 0) {
				  ret = true;
				  break;
			  }
		}

		// 判断文本类信息是否录入
		inputObjs=document.body.getElementsByTagName("INPUT");
		for(var i=0;i<inputObjs.length;i++){
			  if(inputObjs[i].type=='text' && 
			     inputObjs[i].value.length > 0 && 
			     inputObjs[i].name != "enterpriseName" && // 企业查询
			     inputObjs[i].name != "vehicleLnQuery" && // 车牌查询
			     inputObjs[i].size != 4) { // 表格翻页栏中text
				  ret = true;
				  break;
			  }
		}
		return ret;
	}
	
	/** 清空页面 **/
	function clearPage() {
		inputObjs=document.body.getElementsByTagName("INPUT");
		selectObjs=document.body.getElementsByTagName("select");
		
		for(var i=0;i<inputObjs.length;i++){
			  if(inputObjs[i].type=='text' && 
			     inputObjs[i].value.length > 0 && 
			     inputObjs[i].name != "enterpriseName" && 
			     inputObjs[i].name != "vehicleLnQuery" &&
			     inputObjs[i].size != 4) {
				  inputObjs[i].value = "";
			  }
		}
		
		for(var i=0;i<inputObjs.length;i++){
			  if(inputObjs[i].checked==true && inputObjs[i].name != "carChoice" ) {
				  inputObjs[i].checked = false;
			  }
		}
		
		for(var i=0;i<selectObjs.length;i++){
			  if(selectObjs[i].value.length > 0) {
				  selectObjs[i].value = "";
			  }
		}
	}
	
	function vehicleIsChecked() {
		var carsChoice = document.getElementsByName("carChoice");
	    var carIdList = "";
	    for(var i=0; i<carsChoice.length; i++) {
	        if(carsChoice[i].checked==true) {
	            if(carIdList=="") {
	            	//carIdList = "'" + carsChoice[i].value + "'";
	            	carIdList =carsChoice[i].value;
	            } else {
	            	//carIdList=carIdList + "," + "'" + carsChoice[i].value  + "'";
	            	carIdList=carIdList + "," + carsChoice[i].value;
	            }
	        }
	    }
	
	    if(carIdList==""){
			alert("请选择车辆进行参数设置！");
			return false;
		} else {
			document.getElementById("carIdList").value = carIdList;
			return true;
		}
	}

	/** 判断是否单选车辆 **/
	function vehicleIsSingleChecked() {
		var carsChoice = document.getElementsByName("carChoice");
		var carIdList = "";
		var carCnt = 0;
		for(var i=0; i<carsChoice.length; i++) {
	        if(carsChoice[i].checked==true) {
		        carCnt = carCnt + 1;
		        if(carCnt > 1) {
					break;
		        }
	            if(carIdList=="") {
	            	//carIdList = "'" + carsChoice[i].value + "'";
	            	carIdList =carsChoice[i].value;
	            } else {
	            	//carIdList=carIdList + "," + "'" + carsChoice[i].value  + "'";
	            	carIdList=carIdList + "," + carsChoice[i].value;
	            }
	        }
	    }
	    
		if( carCnt == 1) {
			document.getElementById("carIdList").value = carIdList;
			return true;
		} else {
			alert("请选择一台车辆！");
			return false;
		}
	    
	}

	/** 查询参数 **/
	function queryParams() {
		if(!vehicleIsSingleChecked()) {
			return false;
		}
		document.getElementById("currentpage").value = jQuery('#carList').flex_current_page();
		document.getElementById("currentsortname").value = jQuery('#carList').flex_sortname();
		document.getElementById("currentsortorder").value = jQuery('#carList').flex_sortorder();
		$('ftlyparams_form').action="<s:url value='/zdnew/queryFtlyParams.shtml' />";
		Wit.commitAll($('ftlyparams_form'));
	}

	// 刷新参数信息
	function refreshParams() {
		if(!vehicleIsSingleChecked()) {
			return false;
		}
		document.getElementById("currentpage").value = jQuery('#carList').flex_current_page();
		document.getElementById("currentsortname").value = jQuery('#carList').flex_sortname();
		document.getElementById("currentsortorder").value = jQuery('#carList').flex_sortorder();
		$('ftlyparams_form').action="<s:url value='/zdnew/refreshFtlyParams.shtml' />";
		Wit.commitAll($('ftlyparams_form'));
	}

	// 参数设置  
	function submitForm() {
		trimAllObjs();
		if(!checkParamsInput()) {
			alert("请设置至少一个参数");
			return false;
		}
		if(!vehicleIsChecked()) {
			return false;
		}
	    var f1 = checkOilDemarcate();
	    var f2 = checkAdGap();
	    var f3 = checkAddOilLimit();
	    var f4 = checkStealOilLimit();
		var characteristicOefficientValue="";
		var wheelPulseCountValue="";
		var fuelCapacityValue="";
		var odometerValue="";
		var vehicleLnValue="";
		if (f1 && f2 && f3 && f4) {
			if(confirm("<s:property value="getText('ftlyparams.set.confirm')" />")) {
			  document.getElementById("currentpage").value = jQuery('#carList').flex_current_page();
			  document.getElementById("currentsortname").value = jQuery('#carList').flex_sortname();
			  document.getElementById("currentsortorder").value = jQuery('#carList').flex_sortorder();
			  Wit.commitAll($('ftlyparams_form'));
		} else  {
		  return false;
		}
	  }
	}

	/** 全选 **/
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

	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	
	function reWriteCheckBox(tdDiv,pid){
		if(get_cell_text(pid, 2) == "1" ) {
			// 是否为已选车
			tdDiv.innerHTML = '<input name="carChoice" value="' + pid + '"  type="checkbox" checked="checked"/>';
		} else {
			tdDiv.innerHTML = '<input name="carChoice" value="' + pid + '"  type="checkbox" />';
		}
	}
	
	jQuery(function() {
		var gala = jQuery('#carList');
		gala.flexigrid({
			url: '<s:url value="/zdnf/getVehicleListByEnterpriseId.shtml" />',
			params: [{name: 'enterpriseId', value: jQuery('#enterpriseId').val() },
			         {name: 'carIdList', value: jQuery('#carIdList').val() },
			         {name: 'vehicleLnQuery', value: jQuery('#vehicleLnQuery').val()}],
			dataType: 'json',
			height: 240,
			width:264,
			colModel : [
	                    {display: '<input id="carChoiceAll" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', 
		                 name : '', 
		                 width : 30, 
		                 align: 'center', 
		                 process:reWriteCheckBox},
			    		{display: '车牌号', name : 'VEHICLE_LN', width : 175, sortable : true, align: 'center'},
			    		{display: '', name : '', width : 50, sortable : false, align: 'left', hide:true, toggle:false}
				    	   ],
				    newp: jQuery('#currentpage').val() != "" ? jQuery('#currentpage').val() : 1,
			    	sortname: jQuery('#currentsortname').val() != "" ? jQuery('#currentsortname').val() : 'VEHICLE_LN',
			    	sortorder: jQuery('#currentsortorder').val() != "" ? jQuery('#currentsortorder').val() : 'asc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: false,
			    	rp: 20,
					rpOptions : [10,20,50,100],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});
	});	

	/** 查询车辆信息 **/
	function searchVehicleList() {
		jQuery('#carList').flexOptions({
			newp: 1,
			params: [{name: 'enterpriseId', value: jQuery('#enterpriseId').val() },
			         {name: 'vehicleLnQuery', value: jQuery('#vehicleLnQuery').val()}]
		});
		jQuery('#carList').flexReload();
	}
	
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
		 if(screenFlag == false && h>520) {
			 jQuery("#leftInfoDiv").height(h-165);
			 jQuery(".bDiv").height(h-165-315-40);
			 jQuery("#rightDiv").height(h-245);
		 } else if(screenFlag == true && h>490) {
			 jQuery("#leftTabtd").height(h-165);
			 jQuery('.bDiv').height(h-165-315-40);
			 jQuery("#rightDiv").height(h-245);
		 } else {
			 if(h>245) {
				 jQuery("#leftTabtd").height(h-165);
				 jQuery('.bDiv').height(0);
				 jQuery("#rightDiv").height(h-245);
			 } else {
				 jQuery("#leftTabtd").height(h);
				 jQuery('.bDiv').height(0);
				 jQuery("#rightDiv").height(h);
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

	window.addEvent('domready', setTerminalParamsEvent);
	window.addEvent('domready', setTerminalParamsMessage);
</script>

