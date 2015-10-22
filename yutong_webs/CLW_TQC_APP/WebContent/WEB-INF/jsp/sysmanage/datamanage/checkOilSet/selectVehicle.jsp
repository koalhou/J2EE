<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body style="width:340px; min-width:340px;">
		<s:form id="checkOilSet_form" action="vehiclemanage" method="post">
		<s:hidden id="checkYear" name="checkInfo.checkTimeCode"/>
		<table  width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td  valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">					
				</table>
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td class="reportOnline2">
						<table width="100%" border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td>
								
								<table border="0" cellspacing="4" cellpadding="0">
									<tr>
									<td>
										班车号：
										<s:textfield id="vehicleCode" name="vehicleCode" maxlength="32"  cssStyle="width:75px" onkeypress="if(event.keyCode==13){searchList();}"/>
										</td>									   
										<td>
										&nbsp;&nbsp;车牌号：
										<s:textfield id="vehicleLn" name="vehicleLn" maxlength="32" cssStyle="width:100px" onkeypress="if(event.keyCode==13){searchList();}"/>
										</td>
										<td>
										&nbsp;&nbsp;车辆VIN：
										<s:textfield id="vehicleVin" name="vehicleVin" maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
										</td>										
										<td><a href="javascript:void(0);" onClick="searchList()" class="sbutton"><s:text name="common.chaxun" /></a></td>										
									</tr>
								</table>
								</td>
							</tr>
						</table>		
						</td>
					</tr>
					<tr>
					  <td valign="top">
					   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
					     
					     <tr>
					        <td class="reportInline" align="left">
					        <div>					        
		                       <table id="vehicletbl" width="100%"  cellspacing="0">
							   </table>
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
		</s:form>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>






<script type="text/javascript">




function operate(str1,str2){
		window.parent.document.getElementById("vehicle_ln").value=str1;
		window.parent.document.getElementById("vehicle_vin").value=str2;
		//window.parent.document.getElementById("vehicle_vin").value=str2;
		art.dialog.close();
	}

function reWriteLink(tdDiv,pid,row){
	 return '<a href="javascript:operate(\'' + row.cell[1] + '\',\'' + row.cell[2] + '\')">' + tdDiv +'</a>';
}






function searchList() {
	jQuery('#vehicletbl').flexOptions({
		url: '<s:url value="/checkOilSetlist/getVehicleList.shtml" />',
		newp: 1,
		params: [
		{name: 'checkYear', value: jQuery('#checkYear').val()},
		{name: 'vehicle_code', value: formatSpecialChar(jQuery('#vehicleCode').val())},
		{name: 'vehicle_ln', value: formatSpecialChar(jQuery('#vehicleLn').val())},
		{name: 'vehicle_vin', value: formatSpecialChar(jQuery('#vehicleVin').val())}		
		]
	});	
	jQuery('#vehicletbl').flexReload();
}

jQuery(function() {
	var gala = jQuery('#vehicletbl');
	gala.flexigrid({
		url: '<s:url value="/checkOilSetlist/getVehicleList.shtml" />?checkYear='+jQuery("#checkYear").val(),
		dataType: 'json',
		height: 400,
		width:620,
		colModel : [
					{display: '班车号', name : 'vehicle_code', width : 100, sortable : true, align: 'center',escape: true,process:reWriteLink},
                    {display: '车牌号', name : 'vehicle_ln', width :150, sortable : true, align: 'center',process:reWriteLink,escape: true},
                    {display: '车辆VIN', name : 'vehicle_vin', width : 300, sortable : true, align: 'center',escape: true}
		    		],
		    	sortname: 'vehicle_code',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 20,		    	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	onSuccess:function(){
	               jQuery("input[name='carChoiceAll']").removeAttr("checked");  
                  }
		    	
	});
});

</script>
</body>
</html>


