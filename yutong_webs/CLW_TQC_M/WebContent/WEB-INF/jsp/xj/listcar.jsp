<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/list.css'/>">
<script type="text/javascript"
	src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<link rel="stylesheet" type="text/css"
	href="<s:url value='/scripts/flexigrid/flexigrid.css'/>" />
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/flexigrid/flexigrid.pack.js'/>"></script>
<script type="text/javascript">

//查询
function searchOil() {

	jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'vehicle_ln', value: jQuery('#vehicle_ln').val() },
		         {name: 'vehicle_vin', value: jQuery('#vehicle_vin').val() }			 		
			 		]
	});
	jQuery('#gala').flexReload();
}



//转换链接
function reWriteLink(tdDiv,pid){
	 tdDiv.innerHTML = '<a href="javascript:operate(\'' + get_cell_text(pid, 0) + '\',\''+get_cell_text(pid, 1)+'\')">' + tdDiv.innerHTML +'</a>';
}
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

jQuery(function() {
	 var gala = jQuery('#gala');
	 gala.flexigrid({
	  url: '<s:url value="/xjf/vehList.shtml" />', 
	  dataType: 'json',    //json格式
	  height: 271,
	  width:670,
	  colModel : [ 
	        {display: '<s:text name="common.list.vehicleln" />',name : 'vehicle_ln', width : 60, sortable : true, align: 'left', process:reWriteLink}, 
	        {display: '<s:text name="common.list.vehiclevin" />', name : 'vehicle_vin', width : 128, sortable : true, align: 'left'},
	        {display: '<s:text name="common.list.enterprise" />', name : 'FULL_NAME', width : 200, sortable : true, align: 'left'}
	        ],
	       sortname: 'vehicle_ln',
	       sortorder: 'asc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :true,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: true,    // 是否可以动态设置每页显示的结果数
	       rp: 10,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true   // 是否允许显示隐藏列
	     });
	});
  
  function submit() {
	  var form=document.getElementById('routeveh_form');
	  form.submit();
  }
	var carvin="";
	function operate(str1,str2){
		
  		//window.dialogArguments.document.getElementById("VEHICLE_LN").value=str1;
  		//window.dialogArguments.document.getElementById("VEHICLE_VIN").value=str2;
  	 	//window.close();
  		window.parent.document.getElementById("vehicle_ln").value=str1;
  		//window.parent.document.getElementById("vehicle_vin").value=str2;
  	
  		art.dialog.close();
  	}
  
  function closewindow(){
		window.returnValue=carvin;
		window.close();
  }
</script>
</head>
<body style="width: 700px; min-width: 700px;">
<s:form id="routeveh_form" action="report_veh_save_alarm_g"
	method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td class="reportOnline2">
					<table width="100%" border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td>
							<table border="0" cellspacing="4" cellpadding="0">
								<tr>
									<td>车牌号： <s:textfield id="vehicle_ln" name="vehicle_ln"
										cssClass="input120" /></td>
									<td><a href="#" onclick="searchOil()" class="sbutton">查询</a></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="reportOnline">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="titleStyle">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="30%" class="titleStyleZi">车辆信息</td>
											<td width="69%" align="right"></td>
											<td width="1%">&nbsp;</td>
										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td class="reportInline">
									<div>
									<table id="gala" width="100%" cellspacing="0"></table>
									</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>

