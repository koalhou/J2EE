<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name=" " /></title>
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
    			<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top">
							<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
							<tr>
							<td height="38" background="../images/contentTil_bg.gif"><span class="contentTil"><s:text name="假期设置" /></span></td>
							</tr>
			  				</table>
        				</td>
      				</tr>
      				<tr>
      				
						<td  valign="top">
						<td height="48"></td>
                    	<table class="msgBox2" border="0" align="center" cellpadding="0" cellspacing="0" style="padding-right:0px; padding-left:0px; width:520px;">
                    	
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
	<div>
		<p class="lineP">组织：<input  style="cursor: pointer;width: 260px;"  id="orgName" name="orgName" value="<s:property  value="orgName"/>"  readonly="readonly" /></p>
		<p class="lineP">年份：<select id="yearS" name="year" style="width: 80px; height: 20px;">
											 	<!--<option value="请选择" >请选择</option>-->
											 	<option value="2013">2013</option>
											 	<option value="2014">2014</option>
											 	<option value="2015">2015</option>
											 	<option value="2016">2016</option>
											 	<option value="2017">2017</option>
											 	<option value="2018">2018</option>
											 	<option value="2019">2019</option>
											 	<option value="2020">2020</option>
											 	</select>
		月份：<select id="monthS" name="month" style="width: 80px; height: 20px;">
											 <!--     <option value="请选择" >请选择</option>-->
												<option value="01" >1月</option>
												<option value="02">2月</option>
												<option value="03">3月</option>
												<option value="04">4月</option>
												<option value="05">5月</option>
												<option value="06">6月</option>
												<option value="07">7月</option>
												<option value="08">8月</option>
												<option value="09">9月</option>
												<option value="10">10月</option>
												<option value="11">11月</option>
												<option value="12">12月</option>
											 	</select>						<!--  	<a  id="showDay" href="javascript:void(0)"  class="sbutton">确定</a>	    -->	
											 	<a  id="showDay" href="javascript:void(0)" ></a> 	
		</p>
	</div>
	<div style="height: 120px"><span style="float: left;">日期：</span>
	<div id="dayMonthList" style="float: left;width: 400px;margin-top: 5px;">
	
	</div>   </div>
<div  style="clear: both;margin: 10px  50px 10px 0;text-align: right;">
<p   class="lineP"> 
<a  href="javascript:void(0)" id='saveVacation' class="sbutton">保存</a> 
<a  href="init.shtml"  class="sbutton">返回</a> 

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
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script>
jQuery(document).ready(function(){
	 $('select').change(function(){
		 $('#saveVacation').show();
		 $('#showDay').click();
	 });
	 $('#showDay').click(function(){
		 var all=new Date($('#yearS').find("option:selected").val(), $('#monthS').find("option:selected").val(), 0).getDate();
		 if($('#action').val()=='add'&&$.trim($('#orgID').val()).length>0){
			 jQuery.ajax({
					type: 'POST',  
					url: 'getDays.shtml',	
					data: {orgID: $('#orgID').val(),year:$('#yearS').find("option:selected").val(),month:$('#monthS').find("option:selected").val()},	
					dataType: 'json',
					success: function(data) {
						if(data.returns!="0"){
							alert("你选择的机构(包含其子机构)在"+$('#yearS').val()+"年"+$('#monthS').val()+"月已经设置了假期，请返回选择'修改'");
							return false;
						}
					}  
				});
			 
		 }
		 $('#dayMonthList').empty();
		 for(var i=1;i<=all;i++){
		 $('#dayMonthList').append("<span class=\"chkList\">  <input type=\"checkbox\" name=\"day\" value=\""+i+"\" /><label style=\"padding-left: 5px\">"+i+"</label></span>");
		 }
	 });
	
	
	 $('#saveVacation').click(function(){
		 var year;
		 var month;
		 if($('select').attr('disabled')){
		     year=$('#year').val();
			 month=$('#month').val();
		 }else{
			 year=$('#yearS').find("option:selected").val();
			 month=$('#monthS').find("option:selected").val();
		 }
			var orgs=$('#orgs').val();
			if($.trim(orgs).length==0){
				alert('请选择机构');
				return ;
			}
			var str="";
			$(":checkbox[checked]").each(function(){
		     	str+=$(this).val()+",";
		    });
		    if($.trim(str).length==0){
		    	alert('请选择日期');
				return ;
		    }
		    str=str.slice(0,-1);
				jQuery.ajax({
					type: 'POST',  
					url: 'add.shtml',	
					data: {orgs: orgs,year:year,month:month,collection:str,orgID: $('#orgID').val(),action:$('#action').val()},	
					dataType: 'json',
					success: function(data) {
						if(data.returns=="success"){
							alert("假期保存成功！");
							goBack('init.shtml');
						}else{
							if(data.msg){
								alert("你选择的机构(包含其子机构)在"+$('#yearS').val()+"年"+$('#monthS').val()+"月已经设置了假期，请返回选择'修改'");
							}else{
								alert("假期保存失败！");
							}
						}
					}  
				});
	 });
	
	 //初始化:1 设置日期 2 控件和事件处理 3 日历显示
	 var yearV=<s:property  value="year"/>;
	 var monthV=<s:property value="month"/>;
	 var orgsV=$('#collection').val();
	// if(yearV>0&&monthV>0){
		 $('#yearS').val(yearV);
		 $('#monthS').val($('#month').val());
	// }

	 
	 if($('#action').val()!='add'){
		 $('#yearS').attr('disabled','disabled');
		 $('#monthS').attr('disabled','disabled');
		 $('#showDay').hide();
	 }else{
		 $('#orgName').click(function(){
				art.dialog.open("orgs.shtml",{
					title:"选择组织",
					width :260,
					height:300,
					id: 'treeOrgsID',
					skin: 'aero',
					limit: true,
					lock: true,
					drag: true,
					fixed: false,
					yesFn: function(iframeWin, topWin){}});
			});
	 }
	$('#showDay').click();
	 if($.trim(orgsV).length>0){
		 var ss=orgsV.split(',');
		 for ( var s in ss) {
			 $(':checkbox[value='+parseInt(ss[s],10)+']').attr('checked',true);
		}
	 }
	 var a=$('#action').val();
	 if(a=='add'){
		 $('.msgTitle').text("新增假期");
		 document.title='新增假期';
	 }
	 else{
		 $('.msgTitle').text("修改假期");
		 document.title='修改假期';
	 }
});
function goBack(url) {
	Wit.goBack(url);
}
function  setOrg(id,name,childrenIDs,childrenNames){
	//$('#orgID').val(id);
	//$('#orgName').val(childrenNames);
	//$('#orgName').attr('title',childrenNames);
	//$('#orgs').val(childrenIDs);
	
	$('#orgID').val(id);   //最上面组织
	$('#orgName').val(name);
	$('#orgName').attr(name);
	$('#orgs').val(id);
} 
</script>
</body>
</html>