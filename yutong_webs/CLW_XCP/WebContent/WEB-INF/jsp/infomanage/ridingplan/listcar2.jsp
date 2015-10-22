<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="width:700px; min-width:700px;">
<s:form id="routeveh_form" action="report_veh" method="post">
</s:form>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td  valign="top">
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td class="reportOnline2">
				<table width="100%" border="0" cellspacing="8" cellpadding="0">
					<tr>
						<td>
						<table border="0" cellspacing="4" cellpadding="0">
							<tr>
								<td>
								车牌号：
								<s:textfield id="vehicle_ln" name="vehicle_ln" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchCar();}"/>
								</td>
								<td>
								车辆VIN：
								<s:textfield id="vehicle_vin" name="vehicle_vin" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchCar();}"/>
								</td>
								<td><a href="#" onclick="searchCar()" class="sbutton">查询</a></td>
								<!--<td><a href="#" onclick="operate2()" class="sbutton">清空</a></td>-->
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
                                   <td width="69%"align="right"></td>
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
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
//查询
function searchCar() {
	jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'vehicle_vin', value: formatSpecialChar(jQuery('#vehicle_vin').val()) },
		         {name: 'vehicle_ln', value: formatSpecialChar(jQuery('#vehicle_ln').val()) }			 		
			 		]
	});
	jQuery('#gala').flexReload();
}

//转换链接
function reWriteLink(tdDiv,pid,row){
	 return '<a href="javascript:operate(\'' + row.cell[0] + '\',\'' + row.cell[1] + '\')">' + tdDiv +'</a>';
}

function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

jQuery(function() {
	 var gala = jQuery('#gala');
	 gala.flexigrid({
	  url: '<s:url value="/ridingplanpkg/getCarList.shtml" />', 
	  dataType: 'json',    //json格式
	  height: 271,
	  width:670,
	  colModel : [ 
	        {display: '<s:text name="common.vehcileln" />',name : 'vehicle_ln', width : 60, sortable : true, align: 'center',process:reWriteLink, escape:true}, 
	        {display: '<s:text name="vehcileinfo.vehicle_vin" />', name : 'vehicle_vin', width : 128, sortable : true, align: 'center', escape:true},
	       
	        {display: '<s:text name="vehcileinfo.vehicle_code" />', name : 'vehicle_code', width : 100, sortable : true, align: 'center',toggle:false,hide:true},
	        {display: '<s:text name="vehcileinfo.short_allname" />', name : 'short_allname', width : 200, sortable : true, align: 'center', escape:true}
	       
	        ],
	       sortname: 'vehicle_ln',
	       sortorder: 'asc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :true,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: true,    // 是否可以动态设置每页显示的结果数
	       rp: 20,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true   // 是否允许显示隐藏列
	     });
	});
	var carvin="";
	function operate(str1,str2){
  		window.parent.document.getElementById("vehicle_ln").value=str1;
  		window.parent.document.getElementById("vehicle_vin").value=str2;
  		window.parent.checkVehicle_ln();
  		art.dialog.close();
  	}
	function operate2(str1,str2){
  		window.parent.document.getElementById("vehicle_ln").value="";
  		window.parent.document.getElementById("vehicle_vin").value=str2;
  		window.parent.checkVehicle_ln();
  		art.dialog.close();
  	}
  
  function closewindow(){
		window.returnValue=carvin;
		window.close();
  }
</script>
</body>
</html>

