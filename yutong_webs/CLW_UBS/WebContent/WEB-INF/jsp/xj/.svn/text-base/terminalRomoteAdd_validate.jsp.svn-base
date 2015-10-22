<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/flexigrid/flexigrid.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script>
 //车辆List
 var vehicleList = [];
 //车辆数量
 var vehiclecount = 0;
 /**
  * 选择车辆方法
  */
 function choiceStudent(vehicleLN,vehicleVin,teminal_id,sim) {
	 //列表中是否已有该VIN，有就返回
	 for(var i=0;i<vehicleList.length;i++){	
	    if(vehicleList[i].vid == vehicleVin){
			return;
		} 
	 }
    var tb = document.getElementById("showvehicle");
    var tr;
    var td;
    var vehicle;
    //如果行里有位置是空的，优先占这个位置
    for(i=0;i<vehicleList.length;i++){					  
		if(vehicleList[i].vid == ""){
			td = document.getElementById(vehicleList[i].id);
			vehicle = vehicleList[i];
			break;
		} 
    }
    //如果，没有空位，就看是否需要换行
    if(td == undefined){
        if(vehiclecount%6 == 0){
     	   //alert(0);	   
            tr = tb.insertRow();
            tr.id= "tr"+parseInt(vehiclecount/6);
        }else{
         	//alert(parseInt(vehiclecount/5));
            tr= document.getElementById("tr"+parseInt(vehiclecount/6));
            //alert(tr.id);
        }
       td = tr.insertCell();
       td.id = "td"+vehiclecount;
       td.width= "150px";
	   vehiclecount++;
    }
      //显示用的	    
      td.innerHTML=vehicleLN+"<img src=\"../images/gaoj1.gif\" alt=\"删除\" onclick=\"del(this)\"/>";        
      //没车就建车
      if(vehicle== undefined){
    	  vehicle = {};
    	  vehicle.id = td.id;
          vehicle.vid = vehicleVin;
          vehicle.teminal_id = teminal_id;
          vehicle.sim = sim;
          vehicle.ln = vehicleLN;
          vehicleList.push(vehicle);
       } else{
    	   vehicle.id = td.id;
           vehicle.vid = vehicleVin;
           vehicle.teminal_id = teminal_id;
           vehicle.sim = sim;
           vehicle.ln = vehicleLN;
       }     
    } 
    //删除车的方法
    function del(img){
	   var td = img.parentNode;
	   td.innerHTML="";
	   for(var i=0;i<vehicleList.length;i++){					  
		  if(vehicleList[i].id == td.id){					
			  vehicleList[i].vid ="";
			  vehicleList[i].teminal_id ="";
			  break;
		  }  
	   }
    }		
	function searchList() {
		jQuery('#terminalList').flexOptions({
			newp: 1,
			params: [{name: 'vehicleVin', value: jQuery('#vehicleVin').val() }, 
			         {name: 'vehicleLN', value: jQuery('#vehicleLN').val()},
			         {name: 'enterprise_name', value: jQuery('#enterprise_name').val()},
			         {name: 'enterprise_code', value: jQuery('#enterprise_code').val()}]
		});
		jQuery('#terminalList').flexReload();
	}
	function rechoise(){
		 vehicleList = [];
		 vehiclecount = 0;
		 var tb = document.getElementById("showvehicle");
		 while(tb.hasChildNodes()) {           //当tb下还存在子节点时 循环继续
		     tb.removeChild(tb.firstChild);
		 }				 
    }
    //选择车辆
    function choise(){
       var vinlist="'-1'";
       var teminalList ="";
       var simlist = "";
       var lnlist ="";
	   for(var i=0;i<vehicleList.length;i++) {
		     //vin加 '号是为了查询是使用
			 if(vinlist=="'-1'"){
				 vinlist = "'"+vehicleList[i].vid+"'";
				 teminalList = vehicleList[i].teminal_id;
				 simlist = vehicleList[i].sim;
				 lnlist = vehicleList[i].ln;
		     }else{
		    	 vinlist = vinlist+","+"'"+vehicleList[i].vid+"'";
		    	 teminalList = teminalList+","+vehicleList[i].teminal_id;
		    	 simlist = simlist+","+vehicleList[i].sim;
		    	 lnlist = lnlist+","+vehicleList[i].ln;
			 }		
	   }
	  window.parent.searchList(vinlist,teminalList,simlist,lnlist); 	 		
	  art.dialog.close();
	}
	//取消
    function quxiao(){
    	art.dialog.close();
    }
	
	/** 获取表格行内容 **/
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
	}
	   
	/** 列表加载 **/
	jQuery(function() {
		initdata();
		var terminalList = jQuery('#terminalList');
		terminalList.flexigrid({
			url: '<s:url value="/xjf/vList.shtml" />',
			dataType: 'json',
			height: '220',
			width: '900',
			colModel : [
			    		{display: '<s:text name="list.number" />', name : 'rowNumber', width : 30, sortable : false, align: 'left',hide:true},
			    		{display: '<s:text name="common.list.vehicleln" />', name : 'VEHICLE_LN', width : 60, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.vehiclevin" />', name : 'VEHICLE_VIN', width : 140, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.terminal" />', name : 'TERMINAL_ID', width : 140, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.simcardnumber" />', name : 'SIM_CARD_NUMBER', width : 140, sortable : true, align: 'left',hide:true},
			    		{display: '手机号', name : 'CELLPHONE', width : 80, sortable : true, align: 'left',hide:true},
			    		{display: '<s:text name="enterprise.info.ENTERPRISE_CODE" />', name : 'ENTERPRISE_CODE', width : 80, sortable : true, align: 'left'},
			    		{display: '<s:text name="common.list.enterprise" />', name : 'FULL_NAME', width : 220, sortable : true, align: 'left'},
			    		{display: '操作', name : '', width : 80, sortable : true, align: 'left',process:reWriteChoise}
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
	function reWriteChoise(tdDiv,pid){	
		tdDiv.innerHTML = '<a href="#" onclick="choiceStudent(\''+get_cell_text(pid, 1)+'\',\''+get_cell_text(pid, 2)+'\',\''+get_cell_text(pid, 3)+'\',\''+get_cell_text(pid, 4)+'\')">选择</a>';
	}
	//初始化车辆
	function initdata(){
		var type =document.getElementById("type").value;
		var vinList =document.getElementById("vinList").value;
		var lnList=document.getElementById("lnList").value;
		var teminalList=document.getElementById("teminalList").value;
		var simList=document.getElementById("simList").value;
		//当是修改页面时，type值是二
		if(type=="2"&& vinList!=""){
			vinList = vinList.split(",");
			lnList = lnList.split(",");
			teminalList = teminalList.split(",");
			simList = simList.split(",");
			for(var i=0;i<vinList.length;i++) {
				choiceStudent(lnList[i],vinList[i],teminalList[i],simList[i]);
		   }
		}
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
	
</script>