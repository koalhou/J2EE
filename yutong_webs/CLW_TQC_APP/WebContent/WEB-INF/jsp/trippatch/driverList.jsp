<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="width:450px; min-width:450px;">
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
								驾驶员姓名：
								<s:textfield id="driver_name" maxlength="16" name="driverInfo.driver_name" cssClass="input120" onkeypress="if(event.keyCode==13){searchDriver();}"/>&nbsp;&nbsp;
								</td>
								<td><a href="#" onclick="searchDriver()" class="sbutton">查询</a></td>
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
                                      <td width="30%" class="titleStyleZi">驾驶员信息</td>
                                       <td width="69%"align="right">                                     
                                       </td>
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
					        <div id="noDriverData" style='text-align:center'>
									查询不到内容
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
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
//查询
function searchDriver() {
	jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'driverInfo.driver_name', value: encodeHtml(formatSpecialChar(jQuery('#driver_name').val()))}]
	});
	jQuery('#gala').flexReload();
}
//转换链接
function checknametrue(tdDiv,pid,row){
	return '<a href="javascript:void(0);" onclick="operate5(\''+pid+'\',\''+row.cell[1]+'\')">'+tdDiv+'</a>';
}

jQuery(function() {
	 var gala = jQuery('#gala');
	 var url= "../driverGrid/driverList.shtml";
	 gala.flexigrid({
	  url: '<s:url value="'+url+'" />', 
	  dataType: 'json',    //json格式
	  height: 271,
	  width:420,
	  colModel : 
		  [ 
			
			{display: '序号', name : 'rowNumber', width : calcColumn(0.02,40,0),align: 'center',hide:true},
			{display: '驾驶员姓名', name : 'DRIVER_NAME', width : 150,align: 'center', process:checknametrue,escape:true},
	        {display: '<s:text name="driverinfo.driver_license" />',name : 'DRIVER_LICENSE', width : 150, align: 'center', escape:true},
	        {display: '卡号', name : 'DRIVER_CARD_ID', width : calcColumn(0.12,120,0),  align: 'center',hide:true},
	        {display: '<s:text name="sex" />', name : 'DRIVER_SEX', width : calcColumn(0.05,50,0), align: 'center', hide:true},
	        {display: '<s:text name="birthday" />', name : 'DRIVER_BIRTH', width : calcColumn(0.08,80,0), align: 'center',hide:true},
	        {display: '地址', name : 'DRIVER_ADDRESS', width : calcColumn(0.13,180,0), align: 'center', escape:true,hide:true},
	        {display: '电话', name : 'DRIVER_TEL', width : calcColumn(0.13,100,0),  align: 'center', escape:true,hide:true},
	        {display: '车辆', name : 'vehicle_ln', width : calcColumn(0.2,80,0),align: 'center', escape:true,hide:true}
	        ],
	       sortname: 'driver_id',
	       sortorder: 'asc',  //升序OR降序
	       sortable: false,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :false,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: false,    // 是否可以动态设置每页显示的结果数
	       rp: 10000,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showToggleBtn: false,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错             
		   onSuccess:function(){
				if(this.total == 0) {
						jQuery("#noDriverData").show();
			  	   } else {
			  		jQuery("#noDriverData").hide();
			  	   }
			       return true;
		       }
	     });
	});
    function quxiao(){		
  		window.parent.document.getElementById("driver_name").value="";
  		window.parent.document.getElementById("driver_id").value=""; 
  		art.dialog.close();
  	}
    function operate5(id,name){
    	window.parent.document.getElementById("driver_id").value=id;
  		window.parent.document.getElementById("driver_name").value=name;
  		if(driver_name.length>0){
		   window.parent.document.getElementById("driver_nameDesc").innerHTML='';
		}
  		art.dialog.close();
	}
</script>
</body>
</html>
