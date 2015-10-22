<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="width:450px; min-width:450px;">
<s:hidden id ="route_id" name ="route_id" ></s:hidden>
<table  width="100%" border="0" cellspacing="0" cellpadding="0">
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
								名称：
								<s:textfield id="name" maxlength="16" name="name" cssClass="input120" onkeypress="if(event.keyCode==13){searchTripRoute();}"/>
								时间：
								<s:textfield id="time" maxlength="16" name="create_time" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,isShowClear:false})" onkeypress="if(event.keyCode==13){searchTripRoute();}"/>
								</td>
								<td><a href="#" onclick="searchTripRoute()" class="sbutton">查询</a></td>
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
                                      <td width="30%" class="titleStyleZi">发车安排模板信息</td>
                                       <td width="69%" align="right">                                     
                                       </td>
                                      <td width="1%">&nbsp;</td>
                                      
                                    </tr>
                          </table>
                          </td>
                      </tr>
                      
                      <tr>
				        <td class="reportInline">
				        
					        <div id="haveDriverData">
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
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">

//查询
function searchTripRoute() {
	jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'route_id', value: jQuery('#route_id').val()},
		         {name:'create_time',value: jQuery('#time').val()},
		         {name:'name',value: jQuery('#name').val()}
			 	]
	});
	jQuery('#gala').flexReload();
}

//转换链接
function selectdelm(tdDiv,pid,row){
	return '<a href="javascript:void(0);" onclick="operate(\''+row.cell[3]+'\',\''+row.cell[4]+'\')">选择</a>'+'&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="del(\''+row.cell[3]+'\')">删除</a>';
}
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

jQuery(function() {
	 var gala = jQuery('#gala');
	 var url= "../carrunmodule_history/searchcarrunmodule.shtml";
	 gala.flexigrid({
	  url: url, 
	  dataType: 'json',    //json格式
	  params: [{name: 'route_id', value: jQuery('#route_id').val() }],
	  height: 271,
	  width:420,
	  colModel : 
		  [ 
			{display: '名称', name : 'name', width : 70, sortable : true, align: 'center',toggle:false},
			{display: '描述', name : 'module_desc', width : 120, sortable : true, align: 'center', escape:true},
	        {display: '创建时间', name : 'create_time', width : 120, sortable : false, align: 'center',toggle:false},
	        {display: '操作',name : '', width : 60, sortable : false, align: 'center', escape:true,process: selectdelm}        
	        ],
	       sortname: 'create_time',
	       sortorder: 'desc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :false,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: false,    // 是否可以动态设置每页显示的结果数
	       rp: 10,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错             
		});
	});
	function operate(id,route_id){
		if(confirm("确定要选择该模板？")){
			//数据同步
			var param = {"id":id,
						"route_id":route_id};
			jQuery.post("../carrunmodule/searchmoduleto.shtml",param,function(data){
				if(data == 'success') {
					window.parent.refreshChart();
					art.dialog.close();
				} else {
					alert("操作失败！");
				}
			},'text');
		} else 
			return false;
  	}
	function del(id) {
		if(confirm("确定要删除该模板？")){
			//删除
			var param = {"id":id};
			jQuery.post("../carrunmodule/delmoduleto.shtml",param,function(data){
				searchTripRoute();
			},'text');
		} else 
			return false;
	}
  function closewindow(){
	  
		window.returnValue=carvin;
		window.close();
  }

</script>
</body>
</html>

