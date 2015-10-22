<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/flexigrid/flexigrid.css'/>" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.pack.js'/>"></script>
</head>
<%-- 当前位置 --%>
<body  style="width:670px; min-width:670px;">
<div id="wrapper">
<table height="271" border="0" cellspacing="0" cellpadding="0">
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
								<td>
								<!-- 查询 -->
								<s:text name="driverinfo.driver_name" />：
								<s:textfield id="driver_name" name="driverInfo.driver_name" maxlength="16" cssClass="input120" onkeypress="if(event.keyCode==13){searchDriver();}" />&nbsp;&nbsp;
								</td>
								<td>
								<!-- 查询 -->
								<s:text name="driverinfo.driver_license" />：
								<s:textfield id="driver_license" name="driverinfo.driver_license" maxlength="32" cssClass="input120" onkeypress="if(event.keyCode==13){searchDriver();}" />&nbsp;&nbsp;
								</td>
								<td><a href="#" onclick="searchDriver()" class="sbutton"><s:text name="common.chaxun" /></a></td>
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
			        <td class="reportInline">
			        <div id="Below_new" style="text-align:center"><s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/></div>
			        <%-- 列表内容 --%>
					<table id="gala" width="100%"  cellspacing="0"></table>
			        
			        </td>
			     </tr>
			   </table>
			  </td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
</div>
<script type="text/javascript">
		jQuery(function() {
		 var url = document.location.href;
		 var orgId = url.split("?")[1].split("=")[1];
		 var gala = jQuery('#gala');
		 gala.flexigrid({
		  url: '<s:url value="/vehiclemanagepkg/selectdriver.shtml" />', 
		  dataType: 'json',    //json格式
		  height: 271,
		  width:670,
		  colModel : [ 
		        {display: '<s:text name="driverinfo.driver_name" />', name : "NLSSORT(DRIVER_NAME,'NLS_SORT = SCHINESE_PINYIN_M')", width : 80, sortable : true, align: 'left',process: checknametrue}, 
		        {display: '<s:text name="driverinfo.driver_license" />',name : 'driver_license', width : 200, sortable : true, align: 'left'},
		        {display: '<s:text name="sex" />', name : 'driver_sex', width : 80, sortable : true, align: 'left', process: reWriteSex},
		        {display: '<s:text name="birthday" />', name : 'driver_birth', width : 80, sortable : true, align: 'left'},
		        {display: '<s:text name="driverinfo.short_allname" />', name : 'short_allname', width : 100, sortable : true, align: 'left'}
		        ],
		       sortname: "NLSSORT(DRIVER_NAME,'NLS_SORT = SCHINESE_PINYIN_M')",
		       sortorder: 'asc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 10,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		     });
		});
		//转换链接
		//function reWriteLink111(tdDiv,pid){
			//alert("1234");
		     //tdDiv.innerHTML =  '<a href="javascript:operate(\'' + pid + '\',\'' + get_cell_text(pid, 0) + '\')">' + tdDiv.innerHTML +'</a>';
		    // tdDiv.innerHTML =  '<a href="javascript:operate11(\'' + pid + '\',\'' + get_cell_text111(pid, 0) + '\')">' + tdDiv.innerHTML +'</a>';
		 //}
		//转换链接
		function checknametrue(tdDiv,pid,row){
			return '<a href="javascript:void(0);" onclick="operate(\''+ pid +'\',\''+row.cell[0]+'\')">'+tdDiv+'</a>';
		}
		function get_cell_text(pid, cell_idx) {
			return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
		}
		function operate(id,name){
	  		window.parent.document.getElementById("driver_id").value=id;
	  		window.parent.document.getElementById("driver_name").value=name;
	  		window.parent.checkDriver();
	  		art.dialog.close();
	  	    //window.close();
	  	}
		//判断性别
		//function reWriteSex(tdDiv){
			//if(tdDiv.innerHTML == '0'){
				//alert("111");
				//tdDiv.innerHTML = '<s:text name="male" />';
			//}else{
				//alert("222");
				//tdDiv.innerHTML = '<s:text name="female" />';
			//}
		//}
		//判断性别
		function reWriteSex(tdDiv){
			if (tdDiv == '0') {
				return '<s:text name="male" />';
			} else {
				return '<s:text name="female" />';
			}
		}
		//查询
		function searchDriver() {
			 document.getElementById('Below_new').style.display='none';
			jQuery('#gala').flexOptions({
				newp: 1,
				params: [{name: 'driverInfo.driver_name', value: formatSpecialChar(jQuery('#driver_name').val())},
				         {name: 'driverInfo.driver_license', value: formatSpecialChar(jQuery('#driver_license').val())}]
			});
			jQuery('#gala').flexReload();
		}

		
	</script>
</body>

</html>

