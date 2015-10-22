<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:property value="getText('common.title')" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/wit/tools.js' />"></script>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<style type="text/css">
	.lineStyle{ background: url(../images/wline.gif) repeat-x top left; float: left; height: 0px; padding-top: 10px; width: 100%;color: #2a2a2a;}
	a.sbutton, a.sbutton:link, a.sbutton:visited, a.sbutton:hover, a.sbutton:active {
    background: url("../images/sbutton_bg.gif") repeat-x scroll left top transparent;
    border: 1px solid #B4B4B4;
    color: #2A2A2A;
    display: inline-block;
    height: 20px;
    letter-spacing: 5px;
    line-height: 20px;
    margin: 0 2px;
    padding: 1px 0 0;
    text-align: center;
    text-decoration: none;
    width: 60px;
}
	.chkList{
display: inline-block;width:50px;float: left;height: 30px
}
.lineP{
margin: 8px 0;
}
</style>
</head>
<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
   			<s:form id="adduserform" action="holidayaddAction.shtml" method="post"> 
    			<table id="dong" class="dong" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top">
							<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
							<tr>
							<td height="38" background="../images/contentTil_bg.gif"><span class="contentTil"><s:text name="维保管理" /></span></td>
							</tr>
			  				</table>
        				</td>
      				</tr>
      				<tr>
      				
						<td  valign="top">
						<td height="48"></td>
                    	<table class="msgBox2" border="0" align="center" cellpadding="0" cellspacing="0" style="padding-right:0px; padding-left:0px; width:750px;">
                      	<tr>
			            	<td height="32">
			            		<span class="msgTitle" style="padding-left: 10px"></span>
			            	</td>
			          	</tr>
                      	<tr>
                       		<td style="padding-left: 20px;">
		                       
	<input type="hidden" id="repare_id" name="repare_id" value="<s:property  value="map.REPARE_ID"/>" />
	<input type="hidden" id="organizationNameSrc" name="organizationNameSrc" value="<s:property  value="map.ORGANIZATIONNAME"/>" />
	<input type="hidden" id="action" name="action" value="<s:property  value="action"/>" />

	<table border="0" width="100%" >
	 <tr height="30">
	 	<td></td>
	 	<td><span style="float:right;">维保日期:</span></td>
	 	<td>
	 	<div id="vehicleOrg"><s:property value='map.UPDATE_TIME' /></div>
	 	<input type="hidden" id="update_time" name="update_time" value="<s:property value='map.UPDATE_TIME' />" />
	 	<!-- 
<input readonly="readonly" id="update_time" name="update_time" value="<s:property value='map.UPDATE_TIME' />" class="Wdate" type="text"
onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'%y-%M-%d',isShowClear:false})" />
 -->
	 	</td>
	 	<td><span style="float:right;">车辆:</span></td>
	 	<td>
	 	<div id="vehicleOrg"><s:property value='map.VEHICLE_CODE' />(<s:property value='map.VEHICLE_LN' />)</div>
	 	<!--  
<input type="text" id="vehicle_ln" name="vehicle_ln" onclick="choiceCarln();" readonly="true" value="<s:property value='map.VEHICLE_LN' />" />
-->
<input type="hidden" id="vehicle_vin" name="vehicle_vin" value="<s:property value='map.VEHICLE_VIN' />" />
	 	</td>
	 </tr>
	 <tr height="30">
	 	<td></td>
	 	<td><span style="float:right;">所属组织:</span></td>
	 	<td><div id="vehicleOrgHou"></div></td>
	 	<td></td>
	 	<td></td>
	 </tr>
	 <tr height="30"><td colspan="5"><hr/></td></tr>
	 <tr height="30" id="weixiu1">
	 	<td><b>维修</b></td>
	 	<td></td>
	 	<td></td>
	 	<td></td>
	 	<td></td>
	 </tr>
	 <tr height="30" id="weixiu2">
	 	<td></td>
	 	<td><span style="float:right;">是否正常维修:</span></td>
	 	<td> 
	 		<input type="radio" name="fix_flag" id="fix_flag1" style="vertical-align:middle;" checked/><label for="fix_flag1" style="vertical-align:middle;">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
	 		<input type="radio" name="fix_flag" id="fix_flag2" style="vertical-align:middle;"/><label for="fix_flag2" style="vertical-align:middle;">&nbsp;否</label>
	 	</td>
	 	<td><span style="float:right;">责任人:</span></td>
	 	<td>
	 		<input type="text" id="owner" name="owner" value="<s:property value='map.OWNER' />" />
	 	</td>
	 </tr>
	 
	 <tr height="30" id="weixiu3">
	 	<td></td>
	 	<td><span style="float:right;">故障描述:</span></td>
	 	<td>
	 	<textarea id="fault_desc" rows="3" cols="24" name="fault_desc" value="" onKeyDown="MaxTextArea(this)" onKeyUp="MaxTextArea(this)" onkeypress="MaxTextArea(this)" ></textarea>
	 	<input type="hidden" id="fault_desc_hidden" name="fault_desc_hidden" value="<s:property  value="map.FAULT_DESC"/>" />
	 	</td>
	 	<td><span style="float:right;">维保项目:</span></td>
	 	<td>
	 		<textarea id="fault_type" rows="3" cols="24" name="fault_type" value="" onKeyDown="MaxTextArea(this)" onKeyUp="MaxTextArea(this)" onkeypress="MaxTextArea(this)" ></textarea>
	 		<input type="hidden" id="fault_type_hidden" name="fault_type_hidden" value="<s:property  value="map.FAULT_TYPE"/>" />
	 	</td>
	 </tr>
	  <tr height="30" id="weixiu4">
	  <td></td>
	  <td></td>
	  <td style="color:#888 ">不超过<font size="3">30</font>个字</td>
	  <td></td>	     
	  <td style="color:#888 ">不超过<font size="3">30</font>个字</td>
	 </tr>
	 <tr height="30" id="weixiu5">
	 	<td></td>
	 	<td><span style="float:right;">工时费用:</span></td>
	 	<td>
	 	<input name="cost_time" type="text" id="cost_time" value=""   
onblur="if(this.value==''){this.value='0';this.style.color='#000'}" 
onkeydown="this.style.color='#000'" />元
<input type="hidden" id="cost_time_hidden" name="cost_time_hidden" value="<s:property  value="map.COST_TIME"/>" />	 
	 	</td>

         <td><span style="float:right;">配件费用:</span></td>
	 	<td>	
	 	<input name="cost_component" type="text" id="cost_component" value=""   
onblur="if(this.value==''){this.value='0';this.style.color='#000'}" 
onkeydown="this.style.color='#000'" />元
<input type="hidden" id="cost_component_hidden" name="cost_component_hidden" value="<s:property  value="map.COST_COMPONENT"/>" />
	 	</td>	 	
	 </tr>
	 
	 <tr height="30" id="baoyang1">
	 	<td><b>保养</b></td>
	 	<td></td>
	 	<td></td>
	 	<td></td>
	 	<td></td>
	 </tr>
	 <tr height="30" id="baoyang2">
	 	<td></td>
	 	<td>维保项目</td>	 
	 	<td>
	 	<textarea id="fault_type1" rows="3" cols="24" name="fault_type1" value="" onKeyDown="MaxTextArea(this)" onKeyUp="MaxTextArea(this)" onkeypress="MaxTextArea(this)" ></textarea>
	 	<input type="hidden" id="fault_type1_hidden" name="fault_type1_hidden" value="<s:property  value="map.FAULT_TYPE"/>" />
	 	</td>
	 	</tr>
	 	
	 	 <tr height="30" id="baoyang3">
	 	 <td></td>
	 	<td>工时费用</td>
	 	<td>
	 	<input name="cost_time1" type="text" id="cost_time1" value=""   
onblur="if(this.value==''){this.value='0';this.style.color='#000'}" 
onkeydown="this.style.color='#000'" />元
<input type="hidden" id="cost_time1_hidden" name="cost_time1_hidden" value="<s:property  value="map.COST_TIME"/>" />	
	 	</td>
	 	<td>配件费用</td>
	 	<td>
	 	<input name="cost_component1" type="text" id="cost_component1" value=""   
onblur="if(this.value==''){this.value='0';this.style.color='#000'}" 
onkeydown="this.style.color='#000'" />元
<input type="hidden" id="cost_component1_hidden" name="cost_component1_hidden" value="<s:property  value="map.COST_COMPONENT"/>" />	 		
	 	</td>
	 </tr>
	</table>
<div style="clear: both;margin: 10px  50px 10px 0;text-align: right;">
<p class="lineP"> 
<a href="javascript:void(0)" id='saveRepare' class="sbutton">修改</a>
<a href="javascript:void(0)" id='deleteRepare' class="sbutton">删除</a>  
<a href="repare.shtml"  class="sbutton">取消</a> 

 </p>
</div>
		                       
	                  		</td>
                  		</tr>
                  		</table>
                  		
                  		
                  		
                  		
                  		
                  		
                  		
                  		
                  		</td>
      				</tr>
    			</table>
    		</s:form>  
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>  
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script>
function shortNameHou(s){
	var t=s.split("/@");
	var d="";

	for(var i=2;i<t.length-1;i++){
		d=d+t[i]+'-';
	}	
	d=d+t[i];
	return d;
}



jQuery(document).ready(function(){
	$('#fault_desc').val($('#fault_desc_hidden').val());
	$('#fault_type').val($('#fault_type_hidden').val());
	$('#fault_type1').val($('#fault_type1_hidden').val());
	$('#cost_time').val($('#cost_time_hidden').val());
	$('#cost_component').val($('#cost_component_hidden').val());
	$('#cost_time1').val($('#cost_time1_hidden').val());
	$('#cost_component1').val($('#cost_component1_hidden').val());
	
	var s=$('#organizationNameSrc').val();
	
	
	$('#vehicleOrgHou').html(shortNameHou(s));
	
	
	
	$('#deleteRepare').click(function(){
		if(window.confirm("确定要删除这条维保记录吗?")) {
			jQuery.ajax({
				type: 'POST',  
				url: "<s:url value='/rightranking/updateRepare.shtml' />",
				data: {
					valid_flag:"1",
					repare_id:$('#repare_id').val()
				},	
				dataType: 'json',
				success: function(data) {
					if(data.returns=="success"){
						alert("维保删除成功！");
						goBack('repare.shtml');
					}else{
						if(data.msg){
							alert("维保删除失败！");
						}
					}
				}  
			});
		}
	});
	 $('#saveRepare').click(function(){
		
		 	var fix_flag = "";
		 	var fault_type = "";
		 	var cost_time = "";
		 	var cost_component = "";
		 	var fix_flag1 = $('#fix_flag1');
		 	var fix_flag2 = $('#fix_flag2');
		 	var owner=$('#owner').val();
		 	if(fix_type == "0") {//维修
			 	if(fix_flag1[0].checked) {
			 		fix_flag = "0";
			 	}
			 	if(fix_flag2[0].checked) {
			 		fix_flag = "1";
			 	}
			 var	fault_desc = $('#fault_desc').val();
		 		fault_type = $('#fault_type').val();
		 		cost_time = $('#cost_time').val();
		 		cost_component = $('#cost_component').val();
		 		
		 		if(fault_desc==""){
					 alert("故障描述不能为空！");
					 return false;
				 }
		 		
		 		if(fault_desc.length>30){
					 alert("故障描述不能超过30个字！");
					 return false;
				 }
		 	}
		 	if(fix_type == "1") {//保养
			 	if(fix_flag1[0].checked) {
			 		fix_flag = "0";
			 	}
			 	if(fix_flag2[0].checked) {
			 		fix_flag = "1";
			 	}
		 		fault_type = $('#fault_type1').val();
		 		cost_time = $('#cost_time1').val();
		 		cost_component = $('#cost_component1').val();
		 	}
		 	
		 	
		 	

	 		 
	 		if(fault_type==""){
				 alert("维保项目不能为空！");
				 return false;
			 }
			 if(fault_type.length>30){
				 alert("维保项目不能超过30个字！");
				 return false;
			 }
			 if(fix_flag==1&&owner==""){
				 alert("责任人不能为空！");
				 return false;
			 }
			 
			 
			 
			 
			 if(cost_time==""&&cost_component==""){
				 alert("工时费用和配件费用不能同时为空！");
				 return false;
			 }
	 		
			 if(cost_time!=""){
				  var data=cost_time.split(".");
				  
				  if(data[0].length>5){
					  alert("费用范围限制：0<x<99999.9");
					  return false;
				  }
				  if(data.length>1){
					  if(data[1].length>1){
						  alert("费用费用精确到小数点后1位");
						  return false;
					  }
				  }
			 }
	  		 if(cost_component!=""){
				  var data=cost_component.split(".");
				  if(data[0].length>5){
					  alert("费用范围限制：0<x<99999.9");
					  return false;
				  }
				  if(data.length>1){
					  if(data[1].length>1){
						  alert("费用费用精确到小数点后1位");
						  return false;
					  }
				  }
			 }
		 	
		 	
		 	
	  		 if(owner.length>12){
				 alert("责任人不能超12个字！");
				 return false;
			 }
		 	
		 	
		 	
		 	
		 	
	  		 if(isNaN(cost_time)||isNaN(cost_component)){
				 alert("费用填写值必须为数值类型！");
				 return false;
			 }
		 	
		 	
		 	
		 	
		 	
		 	
		 	
			jQuery.ajax({
				type: 'POST',  
				url: "<s:url value='/rightranking/updateRepare.shtml' />",
				data: {
					vehicle_vin:$('#vehicle_vin').val(),
					fault_type:fault_type,
					fault_desc:$('#fault_desc').val(),
					fix_type:$('#fix_type').val(),
					fix_flag:fix_flag,
					cost_time:cost_time,
					cost_component:cost_component,
					owner:$('#owner').val(),
					update_time:$('#update_time').val(),
					repare_id:$('#repare_id').val()
				},	
				dataType: 'json',
				success: function(data) {
					if(data.returns=="success"){
						//alert("维保修改成功！");
						goBack('repare.shtml?msg=updsucc');
					}else{
						if(data.msg){
							//alert("维保修改失败！");
							goBack('repare.shtml?msg=upderror');
						}
					}
				}  
			});
	 });

// 	 var a=$('#action').val();
// 	 if(a=='add'){
// 		 $('.msgTitle').text("新增维保记录");
// 		 document.title='新增维保记录';
// 	 }
// 	 else{
// 		 $('.msgTitle').text("修改维保记录");
// 		 document.title='修改维保记录';
// 	 }
	 
	 var fix_type = "<s:property value='map.FIX_TYPE' />";
	 if(fix_type == "0") {//维修,隐藏保养输入框
		 $('#baoyang1').css("display", "none");
		 $('#baoyang2').css("display", "none");
		 $('#baoyang3').css("display", "none");
	 } else if(fix_type == "1") {//保养,隐藏维修输入框
		 $('#weixiu1').css("display", "none");
		 $('#weixiu2').css("display", "none");
		 $('#weixiu3').css("display", "none");
		 $('#weixiu4').css("display", "none");
		 $('#weixiu5').css("display", "none");
	 }
	 
	 var fix_flag = "<s:property value='map.FIX_FLAG' />";
	 if(fix_flag == "0") {
		 var fix_flag1 = $('#fix_flag1');
		 fix_flag1[0].checked = true;
		 var fix_flag2 = $('#fix_flag2');
		 fix_flag2[0].checked = false;
	 } else if(fix_flag == "1") {
		 var fix_flag2 = $('#fix_flag2');
		 fix_flag2[0].checked = true;
		 var fix_flag1 = $('#fix_flag1');
		 fix_flag1[0].checked = false;
	 }
});
function goBack(url) {
	Wit.goBack(url);
}
function  setOrg(id,name,childrenIDs,childrenNames){
	$('#orgID').val(id);   //最上面组织
	$('#orgName').val(name);
	$('#orgName').attr(name);
	$('#orgs').val(id);
}
function choiceCarln() {
	art.dialog.open("<s:url value='/infomanage/chooseCar_isfalse.shtml' />",{
		title:"车辆信息",
		lock:true,
		width:260,
		height:435
	});
}
function MaxTextArea(content){
    maxlimit=30;
    if (content.value.length > maxlimit)
    	content.value = content.value.substring(0, maxlimit);
      
   }
</script>
</body>
</html>