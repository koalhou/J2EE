<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:property value="getText('common.title')" /></title>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
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
	        <div id="content" style="min-height: 595px">
   			<s:form id="adduserform" action="holidayaddAction.shtml" method="post"> 
    			<table id="noticeTable" width="100%" border="0" cellspacing="0" cellpadding="0" >
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
      				
      				
						
                    	<table id="msgBox2" class="msgBox2" border="0" align="center" cellpadding="0" cellspacing="0" style="padding-right:0px; padding-left:0px; width:750px;">
                      	<tr>
			            	<td height="32">
			            		<span class="msgTitle" style="padding-left: 10px"></span>
			            	</td>
			          	</tr>
                      	<tr>
                       		<td style="padding-left: 20px;">
		                       
		                       	<input type="hidden" id="orgID" name="orgID" value="<s:property  value="orgID"/>" />
	<input type="hidden" id="year" name="year" value="<s:property  value="year"/>" />
	<input type="hidden" id="month" name="month" value="<s:property  value="month"/>" />
	<input type="hidden" id="orgs" name="orgs" value="<s:property  value="orgID"/>" />
	<input type="hidden" id="action" name="action" value="<s:property  value="action"/>" />
	<input type="hidden" id="collection" name="collection" value="<s:property  value="collection"/>" />

	<table border="0" width="100%" >
	 <tr height="30">
	 	<td></td>
	 	<td><span style="float:right;">维保日期:</span></td>
	 	<td>
<input readonly="readonly" id="update_time" name="update_time" value="" class="Wdate" type="text"
onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'%y-%M-%d',isShowClear:false})" />
	 	</td>	 	
	 <td><span style="float:right;">车辆:</span></td>
	 	<td>
<s:textfield id="vehicle_ln" name="vehicle_ln" onclick="choiceCarln();" readonly="true" /><font color="red">*</font>
<s:hidden id="vehicle_vin" name="vehicle_vin"></s:hidden>
<s:hidden id="vehicle_code" name="vehicle_code"></s:hidden>
	 	</td>	 	
	 </tr>
	 <tr height="30">
	 	<td></td>
	 	<td><span style="float:right;">所属组织:</span></td>
	 	<td><div id="vehicleOrg"></div></td>
	 	<td></td>
	 	<td></td>
	 </tr>
	 <tr height="30"><td colspan="5"><hr/></td></tr>
	 <tr height="30">
	 	<td><b>维修</b></td>
	 	<td></td>
	 	<td></td>
	 	<td></td>
	 </tr>
	 <tr height="30">
	 	<td></td>
	 	<td><span style="float:right;">是否正常维修:</span></td>
	 	<td> 
	 		<input type="radio" name="fix_flag" id="fix_flag1" style="vertical-align:middle;" checked/><label for="fix_flag1" style="vertical-align:middle;">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
	 		<input type="radio" name="fix_flag" id="fix_flag2" style="vertical-align:middle;"/><label for="fix_flag2" style="vertical-align:middle;">&nbsp;否</label>
	 	</td>
	 	<td><span style="float:right;">责任人:</span></td>
	 	<td>
	 		<s:textfield id="owner" name="owner" />
	 	</td>
	 </tr>
	 <tr height="30">
	 	<td></td>
	 	<td><span style="float:right;">故障描述:</span></td>
	 	<td>
	 	<textarea id="fault_desc" rows="3" cols="24" label="内容" name="fault_desc" onKeyDown="MaxTextArea(this)" onKeyUp="MaxTextArea(this)" onkeypress="MaxTextArea(this)" ></textarea>	 	
	 	</td>
	 	<td><span style="float:right;">维保项目:</span></td>
	 	<td>
	 	<textarea id="fault_type" rows="3" cols="24" label="内容" name="fault_type" onKeyDown="MaxTextArea(this)" onKeyUp="MaxTextArea(this)" onkeypress="MaxTextArea(this)" ></textarea>	 	
	 	</td>
	 </tr>
	 <tr height="30">
	  <td></td>
	  <td></td>
	  <td style="color:#888 ">不超过<font size="3">30</font>个字</td>
	  <td></td>	     
	  <td style="color:#888 ">不超过<font size="3">30</font>个字</td>
	 </tr>
	 <tr height="30">
	 	<td></td>
	 	<td><span style="float:right;">工时费用:</span></td>
	 	<td>
	 	<input name="cost_time" type="text" id="cost_time" value="0" style="color:#888;"   
onfocus="if(this.value!='0'){this.style.color='#000'}else{this.value='';this.style.color='#888'}" 
onblur="if(this.value==''){this.value='0';this.style.color='#888'}" 
onkeydown="this.style.color='#000'" />元</td>	 	
	 <td><span style="float:right;">配件费用:</span></td>
	 	<td>
	 	
	 	<input name="cost_component" type="text" id="cost_component" value="0" style="color:#888;"   
onfocus="if(this.value!='0'){this.style.color='#000'}else{this.value='';this.style.color='#888'}" 
onblur="if(this.value==''){this.value='0';this.style.color='#888'}" 
onkeydown="this.style.color='#000'" />元</td>
	 	
	 </tr>
	 <tr height="30"><td colspan="5"><hr/></td></tr>
	 <tr height="30">
	 	<td><b>保养</b></td>
	 	<td></td>
	 	<td></td>
	 	<td></td>
	 	<td></td>
	 </tr>
	 <tr height="30">
	 	<td></td>
	 	<td><span style="float:right;">维保项目:</span></td>
	 	<td>
	 	<textarea id="fault_type1" rows="3" cols="24" label="内容" name="fault_type1" onKeyDown="MaxTextArea(this)" onKeyUp="MaxTextArea(this)" onkeypress="MaxTextArea(this)" ></textarea>
	 	</td>
	 	<td></td>
	 	<td></td>
	 </tr>
	  <tr height="30">
	  <td></td>
	  <td></td>
	  <td style="color:#888 ">不超过<font size="3">30</font>个字</td>
	  <td></td>	     
	  <td></td>
	 </tr>
	 <tr height="30">
	 	<td></td>	 	
	 	<td><span style="float:right;">工时费用:</span></td>
	 	<td>
	 	<input name="cost_time1" type="text" id="cost_time1" value="0" style="color:#888;"   
onfocus="if(this.value!='0'){this.style.color='#000'}else{this.value='';this.style.color='#888'}" 
onblur="if(this.value==''){this.value='0';this.style.color='#888'}" 
onkeydown="this.style.color='#000'" />元</td>

<td><span style="float:right;">配件费用:</span></td>
<td>
<input name="cost_component1" type="text" id="cost_component1" value="0" style="color:#888;"   
onfocus="if(this.value!='0'){this.style.color='#000'}else{this.value='';this.style.color='#888'}" 
onblur="if(this.value==''){this.value='0';this.style.color='#888'}" 
onkeydown="this.style.color='#000'" />元
	 	</td>
	 </tr>
	</table>
<div style="clear: both;margin: 10px  50px 10px 0;text-align: right;">
<p class="lineP"> 
<a href="javascript:void(0)" id='saveRepare' class="sbutton">保存</a> 
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
jQuery(document).ready(function(){
	jQuery(window).resize(function(){
		//testWidthHeight();		
	});
	jQuery(window).load(function (){
		//testWidthHeight();
	});
	
	
	function getCurrentDay(){
		var now = new Date();
		
		var mon = now.getMonth() + 1;
		if (mon < 10) {
		      mon = '0' + mon;
		    }
		
		var day = now.getDate();
		if (day < 10) {
		      day = '0' + day;
		    }
		return now.getFullYear() + "-" + mon + "-"+day;
	}
	 $('#update_time').val(getCurrentDay());
	 $('#saveRepare').click(function(){
		 var update_time=$('#update_time').val();
		 var vehicle_ln=$('#vehicle_ln').val();
		 
		 
		 
			var fix_flag = "";
		 	var fix_flag1 = $('#fix_flag1');
		 	var fix_flag2 = $('#fix_flag2');
		 	if(fix_flag1[0].checked) {
		 		fix_flag = "0";
		 	}
		 	if(fix_flag2[0].checked) {
		 		fix_flag = "1";
		 	}
		 	
		 	
		 var owner=$('#owner').val();
		 var fault_desc=$('#fault_desc').val();
		 var fault_type=$('#fault_type').val();
		 var cost_time=$('#cost_time').val();
		 var cost_component=$('#cost_component').val();
		 
		 
		 var fault_type1=$('#fault_type1').val();
		 var cost_time1=$('#cost_time1').val();		 
		 var cost_component1=$('#cost_component1').val();
		 
		 if(isNaN(cost_time)||isNaN(cost_component)||isNaN(cost_time1)||isNaN(cost_component1)){
			 alert("费用填写值必须为数值类型！");
			 return false;
		 }
		 
		 if(update_time==""){
		    alert("维保日期不能为空！");
		    return false;
		}  
		    
		 if(vehicle_ln==""){
			 alert("车牌号不能为空！");
			 return false;
		 }
		 
		 if(fault_desc==""&&fault_type1==""){
			 alert("维修和保养项不能同时为空！");
			 return false;
		 }
		
		 if(fault_desc.length>30){
			 alert("故障描述不能超过30个字！");
			 return false;
		 }
		 if(fault_type.length>30){
			 alert("维保项目不能超过30个字！");
			 return false;
		 }
		 if(fault_type1.length>30){
			 alert("维保项目不能超过30个字！");
			 return false;
		 }
		 if(owner.length>12){
			 alert("责任人不能超12个字！");
			 return false;
		 }
		 if(fix_flag==1&&owner==""){
			 alert("责任人不能为空！");
			 return false;
		 }
		 
		 if(fault_desc!=""){
			 
			 if(fault_type==""){
				 alert("维保项目不能为空！");
				 return false;
			 }
			 
			 if(cost_time==""&&cost_component==""){
				 alert("工时费用和配件费用不能同时为空！");
				 return false;
			 }
		 }
		 
          if(fault_type1!=""){
			 
			 if(cost_time1==""&&cost_component1==""){
				 alert("工时费用和配件费用不能同时为空！");
				 return false;
			 }
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
		 
  		if(cost_time1!=""){
			  var data=cost_time1.split(".");
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
		 if(cost_component1!=""){
			  var data=cost_component1.split(".");
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
  		 
  		 
			jQuery.ajax({
				type: 'POST',  
				url: "<s:url value='/rightranking/addRepare.shtml' />",
				data: {
					vehicle_vin:$('#vehicle_vin').val(),
					fault_type:$('#fault_type').val(),
					fault_type1:$('#fault_type1').val(),
					fault_desc:$('#fault_desc').val(),
					fix_type:$('#fix_type').val(),
					fix_flag:fix_flag,
					cost_time:$('#cost_time').val(),
					cost_time1:$('#cost_time1').val(),
					cost_component:$('#cost_component').val(),
					cost_component1:$('#cost_component1').val(),
					owner:$('#owner').val(),
					update_time:$('#update_time').val()
				},	
				dataType: 'json',
				success: function(data) {
					if(data.returns=="success"){
						//alert("维保添加成功！");
						goBack('repare.shtml?msg=addsucc');
					}else{
						if(data.msg){
							//alert("维保添加失败！");
							goBack('repare.shtml?msg=adderror');
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
   
//获取页面高度
function get_page_height() {
	  var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
	  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
}
//获取页面宽度
function get_page_width() {
	var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
	  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
}
 
//计算控件宽高
function testWidthHeight(){
	var h = get_page_height();
	var test=document.getElementById("msgBox2");
	if(h>225){
		test.style.height = h-225;
	}
	jQuery(window).mk_autoresize({
	       height_include: '#content',
	       height_exclude: ['#header', '#footer'],
	       height_offset: 0,
	       width_include: ['#header', '#content', '#footer'],
	       width_offset: 0
	    });
	h = get_page_height();
	if(h>225){
		test.style.height = h-225;
	}
}
</script>
</body>
</html>