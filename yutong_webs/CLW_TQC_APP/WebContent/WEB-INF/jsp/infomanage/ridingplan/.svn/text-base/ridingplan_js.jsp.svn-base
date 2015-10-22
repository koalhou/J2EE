<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	(function($){
		$.dialog.defaults.skin = ['aero'];// 预缓存皮肤，数组第一个为默认皮肤
	})(art);
	var checkValue="";
	function clickEnterEvent(obj) {
		checkValue=obj.id;
		document.getElementById('chooseorgid').value=checkValue;
		var orgid="<%=session.getValue("ChooseEnterID_tree")%>";
		if(orgid.indexOf("|")>-1)
			orgid=orgid.substring(orgid.lastIndexOf("|")+1);
		searchList();
	}

    //查询乘车规划
	function searchList() {
		jQuery("input[name='carChoiceAll']").removeAttr("checked"); 
		jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'vehicle_ln', value: formatSpecialChar(jQuery('#vehicle_ln').val()) },
		         {name: 'vehicle_code', value: formatSpecialChar(jQuery('#vehicle_code').val()) },
		 		 {name: 'chooseorgid', value: formatSpecialChar(jQuery('#chooseorgid').val())}]
		});
		jQuery('#gala').flexReload();
	}
	//查询车辆信息---按钮屏蔽
	function choiceCarln() {	
		art.dialog.open("<s:url value='/infomanage/getchooseCarList.shtml' />",{
			title:"车辆信息",
			lock:true,
			width:700,
			height:435
		});
	} 
	//每行修改链接
	function reWriteEditLink(tdDiv,pid,row){
		return '<a href="../infomanage/updateRidingReady.shtml?vehcileInfo.trip_id='+row.cell[9]+'">修改</a>';
	}
	//每行删除链接
	function reWriteLink2(tdDiv,pid,row){
	  	return '<a href="#" onclick="deleteRidingPlan(\''+row.cell[3]+'\',\''+pid+'\')">删除</a>';
	}
	//每行删除方法
	function deleteRidingPlan(vin,trip_id){
		if (confirm("您确定要删除吗？")) {
			window.location="../infomanage/deleteRidingPlan.shtml?ridingReady.vehicle_vin="+vin+"&ridingReady.trip_id="+trip_id;				
		}			
	}
	//每行人员信息查询链接
	function reWritePersonLink(tdDiv,pid,row){
		return '<a href="#" onclick="showStudent(\''+row.cell[3]+'\',\''+row.cell[9]+'\')">'+tdDiv+'</a>';
	}
	//每行查询人员方法
	function showStudent(v_vin,trip_id) {
		var url="../infomanage/showStudent.shtml?vehicle_vin="+v_vin+"&trip_id="+trip_id;
		art.dialog.open("<s:url value='"+url+"' />",{
			title:"人员信息",
			lock:true,
			width:700,
			height:450
		});
	}
	//全选checkbox
	function reWriteCheckBox(tdDiv,pid,row){
		return '<input name="carChoice" style="_margin-top:-3px;" value="' + row.cell[3]+'||'+ pid + '"  type="checkbox" />';
	}
	function get_cell_text(pid, cell_idx) {
		return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
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
	/**
	* 线路下发
	*/
	function sendRouteVin() {
		var carsChoice = document.getElementsByName("carChoice");
	   	var carIdList = "";
	    var tripidList="";
	   	for(var i=0; i<carsChoice.length; i++) {
	       if(carsChoice[i].checked==true) {
	           if(carIdList=="") {
	           	carIdList = carsChoice[i].value.split("||")[0] ;
	           	tripidList = carsChoice[i].value.split("||")[1] ;
	           } else {
	           	carIdList=carIdList + "," + carsChoice[i].value.split("||")[0] ;
	           	tripidList = tripidList+","+carsChoice[i].value.split("||")[1] ;
	           }
	       }
	   	}
	   
	   	var carArr=unique(carIdList.split(","));
		var newstr="";
		if(typeof(carArr)!="undefined"){
			newstr=carArr.join(",");
		}
	   	if(newstr==""){
			alert("请选择车辆进行下发！");
			return false;
		}
	   	var info="确认下发所选车辆？";
	  	if(confirm(info)) {
	  		jQuery.blockUI({ message: "<h1>正在下发,请稍等...</h1>" }); 
	  		jQuery.ajax({
		   		  type: 'POST',  
		   		  url: '../infomanage/tSendRouteFileGo.shtml',	
		   		  data: {carsVinInfos: newstr,
	  					 tripidInfos:tripidList
		   		  },	
		   		  complete: function() { 
	                jQuery.unblockUI(); 
	              }
		    });
	  	}
	}
	
	 /**
	  * Array unique function,同时将去掉null及undefined
	  * @param {Array} ary 需要进行unique的数组.
	  * @return {Array} 返回经过去重的新的数组，
	  * 不会修改原来的数组内容.
	  */
	 function unique(ary) {
	     var i = 0,
	         gid='_'+(+new Date)+Math.random(),
	         objs = [],
	         hash = {
	             'string': {},
	             'boolean': {},
	             'number': {}
	         }, p, l = ary.length,
	         ret = [];
	     for (; i < l; i++) {
	         p = ary[i];
	         if (p == null) continue;
	         tp = typeof p;
	         if (tp in hash) {
	             if (!(p in hash[tp])) {
	                 hash[tp][p] = 1;
	                 ret.push(p);
	             }
	         } else {
	             if (p[gid]) continue;
	             p[gid]=1;
	             objs.push(p);
	             ret.push(p);
	         }
	     }
	     for(i=0,l=objs.length;i<l;i++) {
	         p=objs[i];
	         p[gid]=undefined;
	         delete p[gid];
	     }
	     return ret;
	 }
	
	jQuery(function() {
		var gala = jQuery('#gala');
		gala.flexigrid({
			url: '<s:url value="/ridingplanpkg/showridingplanlist.shtml" />',
			dataType: 'json',
			height: 150,
			width:350,
			colModel : [
						{display: '<input id="carChoiceAll" style="_margin-top:-3px;" name="carChoiceAll" value="choiceflagAll" type="checkbox" onclick="javascript:setOrCancelSelect(this)"/>', name : '', width : 20, process:reWriteCheckBox,align: 'center'},
						{display: '班车号', name : "VEHICLE_CODE", width : 60, sortable : true, align: 'center', escape:true},
			    		{display: '车牌号', name : "NLSSORT(VEHICLE_LN,'NLS_SORT = SCHINESE_PINYIN_M')", width : 60, sortable : true, align: 'center', escape:true},
			    		{display: '车辆VIN', name : 'VEHICLE_VIN', width : 100, sortable : true, align: 'center',toggle:false,hide:true},
			    		{display: '所属组织', name : "NLSSORT(FULL_NAME,'NLS_SORT = SCHINESE_PINYIN_M')", width : 120, sortable : true, align: 'center', escape:true},
			    		{display: '核载人数', name : 'LIMITE_NUMBER', width : 50, sortable : true, align: 'center'},
			    		{display: '规划乘车人数', name : 'PLAN_NUM', width : 80, sortable : true, align: 'center', process: reWritePersonLink},
			    		{display: '修改时间', name : 'EDIT_TIME', width : 115, sortable : true, align: 'center'},
			    		{display: '车载终端同步时间', name : 'TER_TIME', width : 115, sortable : true, align: 'center'},
			    		{display: '规划ID', name : 'TRIPID', width : 100, sortable : true, align: 'center',toggle:false,hide:true},
			    		<s:if test="#session.perUrlList.contains('111_3_5_14_4')">
		  				{display: '', name : '', width : 30, sortable : false, align: 'center', process: reWriteEditLink}
		  				</s:if>
		  	            <s:if test="#session.perUrlList.contains('111_3_5_14_3')">
		  				,{display: '', name : '', width : 30, sortable : true, align: 'center', process: reWriteLink2}
		  	            </s:if>
			    		],
			    	sortname: 'VEHICLE_CODE',
			    	sortorder: 'asc',
			    	sortable: true,
					striped :true,
					usepager :true, 
					resizable: false,
			    	useRp: true,
			    	rp: 20,
					rpOptions : [10,20,50,100 ],// 可选择设定的每页结果数
			    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		});
	});
	
	function exportIni(){
		var carsChoice = document.getElementsByName("carChoice");
	   	var carIdList = "";
	    var tripidList="";
	   	for(var i=0; i<carsChoice.length; i++) {
	       if(carsChoice[i].checked==true) {
	           if(carIdList=="") {
	           	carIdList = carsChoice[i].value.split("||")[0] ;
	           	tripidList = carsChoice[i].value.split("||")[1] ;
	           } else {
	           	carIdList=carIdList + "," + carsChoice[i].value.split("||")[0] ;
	           	tripidList = tripidList+","+carsChoice[i].value.split("||")[1] ;
	           }
	       }
	   	}
	
	   	if(carIdList==""){
			alert("请选择车辆，进行导出乘车规划！");
			return false;
		}
	
		if(carIdList.split(",").length>1){
			alert("只能选择一辆车，进行导出乘车规划！");
			return false;
		}
	
	 	jQuery.blockUI({ message: "<h1>导出乘车规划,请稍等...</h1>" }); 
	 	var form = jQuery('#exportIniForm');
	 	document.getElementById('vehicle_vin_pop').value=carIdList;
	 	document.getElementById('trip_id').value=tripidList;
		form.submit();
		jQuery.unblockUI(); 
	}

	 function formatSpecialChar(str) {
			return str.replace(/\\/g,"\\\\")
			          .replace(/%/g,"\\%")
			          .replace(/_/g,"\\_")
			          .replace(/％/g,"\\％")
			          .replace(/＿/g,"\\＿")
			          .replace(/^\s+|\s+$/g, '');
	}
	 
	//页面自适应
		(function (jQuery) {
			
			jQuery(window).resize(function(){
				testWidthHeight();
				jQuery('#gala').fixHeight();
			});
			jQuery(window).load(function (){
		    	testWidthHeight();
		    	jQuery('#gala').fixHeight();
			});
		})(jQuery);
		
		function get_page_height(){
			var min_height=jQuery.data(window,'mk-autoresize').options.min_height;
			return (parseInt(jQuery(window).height())<=min_height)?min_height:jQuery(window).height();
		}
		function get_page_width(){
			var min_width=jQuery.data(window,'mk-autoresize').options.min_width;
			return (parseInt(jQuery(window).width())<=min_width)?min_width:jQuery(window).width();
		}
				 
		//计算控件宽高
		function testWidthHeight(){
			jQuery(window).mk_autoresize({
		    	height_include: '#content',
		    	height_exclude: ['#header', '#footer'],
		    	height_offset: 0,
		    	width_include: ['#header', '#content', '#footer'],
		    	width_offset: 0
			});
			
			var h = get_page_height();
			var leftdiv=document.getElementById("leftInfoDiv");
			var treechang=document.getElementById("treechang");
			leftdiv.style.height = h-250;
			treechang.style.height=h-160;
			jQuery(".bDiv").height(h-333); 
			var w = get_page_width();
			/* if(jQuery("#leftInfoDiv").is(":hidden")){
				jQuery(".flexigrid").width("100%");
			}else{
				jQuery(".flexigrid").width("100%");
			} */
			jQuery(".flexigrid").width(jQuery("#content").width()-290);
		}
		
		function LeftScreen(){
			var left = document.getElementById("leftInfoDiv");
			var Lefttab = document.getElementById("leftTab");
			left.style.display="none";
			Lefttab.style.display="";
			var searchPlanId = document.getElementById("searchPlanId");
			searchPlanId.style.display="none";
			jQuery(".flexigrid").width(jQuery("#content").width()-290);
		}
		
		function midScreen(){
			var left = document.getElementById("leftInfoDiv");
			var Lefttab = document.getElementById("leftTab");
			left.style.display="";
			Lefttab.style.display="none";
			var searchPlanId = document.getElementById("searchPlanId");
			searchPlanId.style.display="";
			jQuery(".flexigrid").width(get_page_width()-280);
		}
</script>