<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	var gala="";
	var oilShowWidth = 1140;
	var choiceVin = "";
	var chooseEnterpriseId = "";
	
	function closeOilDetail() {
		jQuery("#BgDiv").css("display","none");
		jQuery("#oilDetail").css("display","none");
	}

	function showOilDetail() {
		jQuery("#BgDiv").css("display","block");

		jQuery("#oilDetail").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#oilDetail").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#oilDetail").height())/2))+"px");
        
		var orgname="";
		if(selectName==""){
			var zTree = jQuery.fn.zTree.getZTreeObj("treeDemo");
			var node = zTree.getNodeByParam("level", "0");
			orgname=node.name;
		}else{
			orgname=selectName;
		}
		jQuery("#oilDetail").css("display","block");
		//var chooseorgid = jQuery('#chooseorgid').val();
		var check_time_code = jQuery('#check_time_code').val();
		
		if("" == choiceVin) {
			showNoDataCharts();
		} else {
			// 油耗趋势
			OilLine.getOilLineChart(chooseEnterpriseId,choiceVin,check_time_code,{
			     callback:function(str){
			        var dateInfo=str.split('|');
				 	var chart1 = new FusionCharts("../scripts/fusioncharts/Line.swf?ChartNoDataText=无查询结果", "ChartId", "1130", "250", "0", "0");
				 	chart1.setDataXML(dateInfo[0]);	
				    chart1.render("oilChartDetail"); 
			 	}
			 });
		}
		//var vehCoce = jQuery(rowData).data("vehicle_code");
		//alert(vehCoce);
		if(formatSpecialChar(jQuery("#carln").html())=="总计"){
			jQuery("#vehicle_code_vs").val("");
			jQuery("#vehicle_ln_vs").val("总计");
			jQuery("#org_name_vs").val(orgname);
			document.getElementById("messagetime").style.display="none";
			//document.getElementById("totalmessageandoil").style.display="none";
			jQuery("#time_name_vs").val(jQuery("#messagetime").html());
		}else{
			jQuery("#vehicle_code_vs").val(vehCoce);
			jQuery("#vehicle_ln_vs").val(vehln);
			jQuery("#org_name_vs").val(short_name);
			//jQuery("#org_name_vs").val(jQuery("#orgname").html());
			document.getElementById("messagetime").style.display="";
			//document.getElementById("totalmessageandoil").style.display="";
			jQuery("#time_name_vs").val(jQuery("#messagetime").html());
		}
	}

	var selectName="";
	function mytreeonClick(event, treeId, treeNode){
		document.getElementById('chooseorgid').value=treeNode.id;
		selectName=treeNode.name;
		jQuery("#totaloil").html("");
		jQuery("#countmileage").html("");
		jQuery("#avgOilPerMile").html("");
		
		searchOilused();
	}
	

	function get_cell_text(pid, cell_idx) {
		var cellName = jQuery('#gala').flex_colModel()[cell_idx].name;
		var name;
		jQuery.each(jQuery('#row' + pid).parents("div.flexigrid").find('th'),function(i){  
			if(jQuery(this).attr("colname") == cellName) {
				name = jQuery('#row' + pid).children('td').eq(i).eq(0).text();
				return false;
			}
		});
		return name;
	}

	function sumStyle(tdDiv,pid,row){
		if(pid=="sumid"){
			jQuery("#totaloil").html(row.cell[10] + "(L)");
			jQuery("#countmileage").html(row.cell[12] + "(km)");
			if(row.cell[12]>0){
				jQuery("#avgOilPerMile").html(toDecimal2(parseInt(row.cell[10])/parseInt(row.cell[12])*100)+ "(L/100km)");
			}else{
				jQuery("#avgOilPerMile").html("0.00(L/100km)");
			}
		}else{
			return tdDiv;
		}
	}
	
	function toDecimal2(x) {    
	           var f = parseFloat(x);    
		           if (isNaN(f)) {    
		                return false;    
		            }    
		           var f = Math.round(x*100)/100;    
		            var s = f.toString();    
		            var rs = s.indexOf('.');    
		            if (rs < 0) {    
		               rs = s.length;    
		                s += '.';    
		            }    
		            while (s.length <= rs + 2) {    
		               s += '0';    
		            }    
		            return s;    
		       }    

	function showOil(tdDiv,pid,row){
		var saveOil = tdDiv;
		if(tdDiv=='0.00')
			return "--";
	    else
	    	return saveOil;
	    	
	}
	function loadTab1Date(){
		 gala.flexigrid({
		  url: '<s:url value="/oilUsedGridEcu/oilUsedList.shtml" />', 
		  dataType: 'json',    //json格式
		  params: [{name: 'vehicle_ln', value: jQuery('#vehicle_ln').val() }, 
			         {name: 'chooseorgid', value: jQuery('#chooseorgid').val()},
			         {name: 'vehicle_code', value: jQuery('#vehicle_code').val()},
			 		 {name: 'check_time_code', value: jQuery('#check_time_code').val()}],
		  height: 100,
		  width:200,
		  colModel : [ 
			//{display: '节油排名',name : 'rn', width : 50, sortable : true, align: 'center',hide: true},//process:sumStyle,
			{display: '班车号',name : 'vehicle_code', width : 50, sortable : true, align: 'center', escape: true},
			{display: '车牌号',name : 'vehicle_ln', width : 70, sortable : true, align: 'center', escape: true},
			{display: '车辆VIN', name : 'vehicle_vin',width : 160,sortable : true, align: 'center',hide:true,escape: true}, 
			//{display: '驾驶员', name : 'driver_name', width : 60, sortable : true, align: 'center',sortable:true,hide: true},
			{display: '考核百公里油耗(L/100km)', name : 'check_value', width :190, sortable : true, align: 'center'},
			{display: '实际百公里油耗(L/100km)', name : 'hkm_oilused', width : 190, sortable : true, align: 'center'},
			{display: '燃油差距(L/100km)', name : 'minusoil', width :140, sortable : true, align: 'center'},
			{display: '总耗油量(L)', name : 'COUNT_OIL_TOTAL', width :90, sortable : true, align: 'center', process:sumStyle},
			//{display: '标准耗油量（L）',name : 'check_value_mile', width : 90, sortable : true, align: 'center',hide: true},
			//{display: '允许最高耗油量（L）',name : 'max_oil', width : 120, sortable : true, align: 'center',hide: true},
			//{display: '节标准',name : 'SAVE_STANDARD', width : 70, sortable : true, align: 'center',process:showOil,hide: true},
			//{display: '超标准',name : 'over_standard', width : 70, sortable : true, align: 'center',process:showOil,hide: true}, 
			{display: '总里程(km)', name : 'COUNT_MILEAGE', width : 100, sortable : true, align: 'center', process:sumStyle},		        
			{display: '所属部门', name : 'short_allname',width : 100,sortable : true, align: 'center',hide:true,escape: true},
			{display: '',name : 'mileage', width : 50, sortable : true, align: 'center',hide: true},
			{display: '',name : 'oil_total', width : 50, sortable : true, align: 'center',hide: true}
		        ],
		       sortname: 'SAVE_STANDARD desc,over_standard desc,vehicle_code',
		       sortorder: 'asc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true,   // 是否允许显示隐藏列
		       onRowSelect:rowclick,
		       onFirstRowSelected:rowclick,
		       onSuccess:function(){
			  	   jQuery("#rowsumid").css("display","none");
			  	   if($(gala).find("td").length == 0){
			  		 	showChart(null);
			  		 	jQuery("#carln").html("未选车");
			  		 	document.getElementById("messagetime").style.display="none";
			  		 	//document.getElementById("totalmessageandoil").style.display="none";
			  		 	//jQuery("#messagetime").html("");
			  	   }
			       return true;
		       }
		     });
		 changStyle();
	}

	function showNoDataCharts() {
		jQuery('#lineChart').hide();
		jQuery('#3dChart').hide();
		jQuery('#noDataDiv').show();
	}

	function changStyle() {
		if("" == choiceVin) {
			showNoDataCharts();
		} else {
			showChart(choiceVin);
		}
	}
	var vehCoce;
	var vehln;
	var short_name;
	var rowclick =function(rowData) {
		vehln=jQuery(rowData).data("vehicle_ln");
		vehCoce = jQuery(rowData).data("vehicle_code");
		//alert(jQuery(rowData).data("short_allname"));
		short_name=jQuery(rowData).data("short_allname");
		if(Number(vehln.length)>7){
			if(vehCoce == '外租')
				jQuery("#carln").html(vehCoce+"<font style='font-size: 12px;'>("+encodeHtml(vehln.substr(vehln.length-7))+")</font>");
			else
				jQuery("#carln").html(vehCoce+"号<font style='font-size: 12px;'>("+encodeHtml(vehln.substr(vehln.length-7))+")</font>");
			jQuery("#carln").attr("title",vehln);
		}else{
			if(vehCoce == '外租')
				jQuery("#carln").html(vehCoce+"<font style='font-size: 12px;'>("+encodeHtml(vehln)+")</font>");
			else
				jQuery("#carln").html(vehCoce+"号<font style='font-size: 12px;'>("+encodeHtml(vehln)+")</font>");
			jQuery("#carln").attr("title","");
		}
		 //jQuery("#carln").html(jQuery(rowData).data("vehicle_ln"));
		 document.getElementById("messagetime").style.display="";
		 //document.getElementById("totalmessageandoil").style.display="";
		 jQuery("#messagetime").html(jQuery('#check_time_code').val());
		 //jQuery("#totalmessageandoil").html("总里程："+jQuery(rowData).data("mileage")+"&nbsp;&nbsp;&nbsp;总油耗："+jQuery(rowData).data("oil_total"));
		 //jQuery("#totalmessageandoil").html("");
		 chooseEnterpriseId = jQuery('#chooseorgid').val();
		 var vin = jQuery(rowData).data("vehicle_vin");
		 var ln=jQuery(rowData).data("vehicle_ln");
		 jQuery("#orgname").val(jQuery(rowData).data("short_allname"));
		 if(ln.length>7){
			 jQuery("#carln").html("*"+ln.substr(ln.length-7));
			 jQuery("#carln").attr("title",ln);
		 }else{
			 jQuery("#carln").removeAttr("title");
		 }
		 choiceVin = vin;
// 		 if("" != vin) {
			 showChart(vin);
// 		 }
	}
	
	function zoomPositionCalc() {
		if("" != choiceVin) {
			var leftPx = (jQuery(".car-status3").width() - oilShowWidth)/2 + 40;
			jQuery("#zoomPic").css("display", "block");
		} else {
			jQuery("#zoomPic").css("display", "none");
		}
	}

	function showChart(vehicle_vin){
	    var check_time_code = jQuery('#check_time_code').val();
	    jQuery('#noDataDiv').hide();
		jQuery('#lineChart').show();
		jQuery('#3dChart').hide();
		jQuery("#zoomPic").css("display", "none");
		// 油耗趋势
		OilLine.getOilLineChart(chooseEnterpriseId,vehicle_vin,check_time_code,{
		     callback:function(str){
				if(str==null||str==""){
					showNoDataCharts();
				}else{
					var dateInfo=str.split('|');
				 	if(str.split('||')[1]=="0"||str.split('||')[1]==null||str.split('||')[1]==""){
				 		showNoDataCharts();
					}else{
					 	var chart1 = new FusionCharts("../scripts/fusioncharts/Line.swf?ChartNoDataText=无查询结果", "ChartId", oilShowWidth, "195", "0", "0");
					 	chart1.setDataXML(dateInfo[0]);	
				    	chart1.render("lineChart");
				    	zoomPositionCalc();
					}
				} 
		 	}
		 });
		    
	}
	
	/*
	 * 点击页签显示不同列表
	 */
	function changeChoose(id){
		if(id=="tab1"){
			gala = jQuery('#gala');
			loadTab1Date();
			//searchOilused();
			
		}
	}
    jQuery(function() {
    	getMonthList1("check_time_code");
	    changeChoose("tab1");
	});
	//查询
	function searchOilused() {
		jQuery("#totaloil").html("");
		jQuery("#countmileage").html("");
		jQuery("#avgOilPerMile").html("");
			jQuery('#gala').flexOptions({
				newp: 1,
				params: [{name: 'vehicle_ln', value: jQuery('#vehicle_ln').val() }, 
				         {name: 'chooseorgid', value: jQuery('#chooseorgid').val()},
				         {name: 'vehicle_code', value: jQuery('#vehicle_code').val()},
				 		 {name: 'check_time_code', value: jQuery('#check_time_code').val()}]
			});
			jQuery('#gala').flexReload();
	}

	
	function exportcar() {
		if(jQuery(gala).find("td").length == 0){
			alert("没有油耗与里程统计数据!");
			return;	
		}
		if(jQuery(gala).flex_totalc > 50000){
			alert("无法导出，系统最多可一次导出5W条记录!");
			return;	
		}
		if(confirm("确定要导出油耗与里程统计吗？")) {
			
			document.getElementById('oilused.sortname').value = jQuery(gala).flex_sortname();	
			document.getElementById('oilused.sortorder').value = jQuery(gala).flex_sortorder();
			
			var form = document.getElementById('oil_form');
			form.action="<s:url value='/oilusedEcu/exportOilbyCar.shtml' />";
			form.submit();
		}
	}

	//页面自适应
	jQuery(function() {
		
		jQuery("#oilDetail").easydrag();// oilDetail为层ID
		jQuery("#oilDetail").setHandler("divTitle"); 
		/* jQuery(window).mk_autoresize({
			min_width: 1320
		}); */
		resizeBgDiv();
		auto_size();
	});

	/* jQuery(window).wresize(function(){
    	jQuery("#oilDetail").css("left",((jQuery(document).width())/2-(parseInt(jQuery("#oilDetail").width())/2))+"px")
        .css("top",((jQuery(document).height())/2-(parseInt(jQuery("#oilDetail").height())/2))+"px");
	}); */

	function auto_size(){
		//计算中区高度
		jQuery('#content').mk_autoresize( {
			height_include : [ '#content_rightside', '#content_leftside' ],
			height_offset : 0,
			width_exclude: ['#content_leftside'],
			width_include : [ '#content_rightside'],
			width_offset : 1
		});
		//计算左测区域高度
		jQuery('#content_leftside').mk_autoresize( {
			height_exclude : '#leftInfoDiv',
			height_include : '.treeBox',
			height_offset : 5
		});

		//计算右测区域高度
		jQuery('#content_rightside').mk_autoresize( {
			height_exclude : '.titleBar',
			height_include : '#statusManger',
			height_offset : 0,
			width_include : [ '#statusManger','.titleBar'],
			width_offset : 0
		});	

		jQuery('#statusManger').mk_autoresize( {
			height_exclude : ['.car-info','car-status3'],
			height_include : '.list-area',
			height_offset : 205,
			width_include : ['.list-area','.car-info','car-status3'],
			width_offset : 0
		});	
		jQuery('.list-area').mk_autoresize({
		    height_include: '#parentSpan1 .bDiv',
		    height_offset: 125, // 该值各页面根据自己的页面布局调整
		    width_include: '#parentSpan1 .flexigrid',
		    width_offset: 5// 该值各页面根据自己的页面布局调整
		});
	}

	/**
	 * 左侧树区域显示控制
	 */
	function HideandShowControl(){//leftdiv
		if(jQuery('#middeldiv').css("display")=="none"){//展开状态
			jQuery('#middeldiv').css("display","block");
			jQuery('#leftdiv').css("display","none");

			//计算中区高度
			jQuery('#content').mk_autoresize( {
				height_include : [ '#content_rightside', '#content_leftside' ],
				height_offset : 0,
				width_exclude: ['#content_leftside'],
				width_include : [ '#content_rightside'],
				width_offset : 25
			});
			//计算左测区域高度
			jQuery('#content_leftside').mk_autoresize( {
				height_exclude : '#leftInfoDiv',
				height_include : '.treeBox',
				height_offset : 5
			});

			//计算右测区域高度
			jQuery('#content_rightside').mk_autoresize({
				height_exclude : '.titleBar',
				height_include : '#statusManger',
				height_offset : 0,
				width_include : [ '#statusManger','.titleBar'],
				width_offset : 0
			});	

			jQuery('#statusManger').mk_autoresize({
				height_exclude : ['.car-info','car-status3'],
				height_include : '.list-area',
				height_offset : 195,
				width_include : [ '.list-area','.car-info','car-status3'],
				width_offset : 0
			});	
			jQuery('.list-area').mk_autoresize({
			    height_include: '#parentSpan1 .bDiv',
			    height_offset: 145, // 该值各页面根据自己的页面布局调整
			    width_include: '#parentSpan1 .flexigrid',
			    width_offset: 10// 该值各页面根据自己的页面布局调整
			});

			jQuery('#zoomPic').css('left','160px');

		}else{//隐藏状态
			jQuery('#middeldiv').css("display","none");
			jQuery('#leftdiv').css("display","block");
			auto_size();
			jQuery('#zoomPic').css('left','50px');
		}
	}
</script>