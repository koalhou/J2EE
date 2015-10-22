<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<style type="text/css">
	body {
	width:990px;
	height: 390px;
	min-width:990px;
}
	
	</style>
	
</head>
<body>
	<div id="wrapper" style="width:990px;height: 380px;">
		
		
			<s:form id="route_form" action="" method="post">
			</s:form>
			<table   border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
					<table width="100%" border="0" cellspacing="5" cellpadding="0">

						<tr>
						  <td valign="top">
						   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
						     <tr>
						        <td>
						        <table width="100%" border="0" cellspacing="0" cellpadding="0">
						          <tr>
			                         <td class="titleStyle">
				                         <table width="100%" border="0" cellspacing="0" cellpadding="0">
				                             <tr>
				                               <td  class="titleStyleZi">告警显示设置 &nbsp;&nbsp; <span style="color: red">(勾选的告警类型将在监控页面以红色车辆显示。)</span></td>
				                              
				                             </tr>
				                          </table>
			                          </td>
			                      </tr>
						        </table>
						        </td>
						     </tr>
						     <tr>
						        <td class="reportInline">
						          	<%-- 列表内容 --%>
									<table id="gala"></table>
						        </td>
						     </tr>
						   </table>
						  </td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
				<td>&nbsp;&nbsp;注：其中三类告警（超速告警、SOS告警、GPS故障告警）因为车辆安全运行至关重要，默认开启红色显示，不能取消。</td>
				</tr>
			</table>
			
		
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
	<script type="text/javascript">
	//保存告警id，以逗号分隔的数字
	function save(){
		var aids=[];
		jQuery(':checkbox[name="aid"]').each(function(){
		       if(jQuery(this).attr("checked") == true){  
		           aids.push(jQuery(this).val());
		           }
		}); 
		$.ajax({
			   type: "GET",
			   url: '<s:url value="/alarmSetting/set.shtml" />',
			   async: false,
			   data: "aids="+aids.toString().replace(/(')/g,''),
			   success: function(json){
				   console.log(json);
					if('ok'==json.ret){
						alert('设置成功');
					}
			   }
			});
		
		
	}
	//添加checkbox
	function editCheck(tdDiv,pid,row)
	{
		if(tdDiv=='1'){
			return '<input type="checkbox"  name="aid"  disabled checked value="'+pid+'"/> ';
		}
		return '<input type="checkbox"  name="aid" value="'+pid+'"/> ';
	}
	//页面初始化
		jQuery(function() {
			 var gala = jQuery('#gala');
			 gala.flexigrid({
			  url: '<s:url value="/alarmSetting/list.shtml" />', 
			  dataType: 'json',    //json格式
			  height: 320,
			  width:970,
			  colModel : [ 
			        {display: '类型',  width : 300, align: 'center'},
			        {display: '子类型',  width : 300, align: 'center'},
			        {display: '是否红色显示', width : 300, align: 'center',process: editCheck}
			        ],
			        sortable: false,
					striped :true,
					usepager :false, 
					resizable: false,
			    	useRp: true,
			    	rp:50,	
					rpOptions : [50,100],
			    	showTableToggleBtn: true,
			    	 onSuccess:function(){
			    		 //初始化选中的
				       		jQuery.getJSON('checked.shtml',function(json){
				       			jQuery.each(json.list.split(','),function(i,n){
				       				jQuery(':checkbox[value='+n+']').attr('checked','checked');
				       			})
				       		});
					    	return true;
					   }
			     });
			});
			
	</script>
</body>
</html>