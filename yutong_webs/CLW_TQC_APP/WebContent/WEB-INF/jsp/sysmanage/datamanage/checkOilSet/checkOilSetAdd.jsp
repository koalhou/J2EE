<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>

<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
<link rel="stylesheet" href="<s:url value='/scripts/mktree.css' />" type="text/css" media="all" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>

</head>

<body>

<div id="wrapper">						
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>					
		<div id="content">					

			<s:form id="checkOilSet_add" action="checkOilSetAddSubmit" method="post">
			<input type="hidden"  id="entiID" name="entiID" value="<s:property value='#session.adminProfile.entiID'/>" />
			<div id="main" style="overflow: auto;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							
							<td valign="top">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="36" valign="top" background="../images/tree_tabBg.gif">
										<div class="toolbar">
										<div class="contentTil">考核油耗设置</div>
										
										</div>
										</td>
									</tr>
								</table>
								<table class="msgBox2" width="650" border="0" align="center"
								cellpadding="0" cellspacing="0">
									<tr>
										<td height="32" background="../images/msg_title_bg.gif"><span
											class="msgTitle">&nbsp;&nbsp;添加考核油耗</span></td>
									</tr>
									<tr>
										<td align="center">				
												
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									
									<tr>
										<td align="right">考核年份：</td>
										<td align="left">
										<select id="checkYear">
					                    <option value='2013'>2013</option>					                    
					                    <option value='2014'>2014</option>
					                    <option value='2015'>2015</option>
					                    <option value='2016'>2016</option>
					                    <option value='2017'>2017</option>
					                    <option value='2018'>2018</option>
										<option value='2019'>2019</option>
										<option value='2020'>2020</option>
				                        </select>										
										</td>
									</tr>
									
									<tr>
										<td align="right">车牌号：</td>
										<td align="left">
                              			<s:textfield id="vehicle_ln" name="insertInfo.vehicle_ln" onclick="openVehicleWindow()" readonly="true"/>
                              			<s:hidden id="vehicle_vin" name="insertInfo.vehicle_vin"></s:hidden>                              	
                              			</td>										
									</tr>
									<tr>
										<td align="right" height="28">考核百公里油耗：</td>
										<td align="left">
											<input type="checkbox" id="checkboxTwo" checked="checked"  onclick="clickChekbox();"/>
											每月相同:<input type="text" id="checkValueSame"/>
										</td>										
									</tr>
									<tr>
										<table id=tableTwo disabled>
										<tr>
										<td>1月</td><td>2月</td><td>3月</td><td>4月</td><td>5月</td><td>6月</td>
										</tr>
										<tr>
										<td><input type="text" id="text1" cssClass="input150" class="text" disabled></td><td><input type="text" id="text2" disabled></td><td><input type="text" id="text3" disabled></td>
										<td><input type="text" id="text4" class="text" disabled></td><td><input type="text" id="text5" disabled></td><td><input type="text" id="text6" disabled></td>
										</tr>
										<tr>
										<td>7月</td><td>8月</td><td>9月</td><td>10月</td><td>11月</td><td>12月</td>
										</tr>										
										<tr>
										<td><input type="text" id="text7" disabled></td><td><input type="text" id="text8" disabled></td><td><input type="text" id="text9" disabled></td>
										<td><input type="text" id="text10" disabled></td><td><input type="text" id="text11" disabled></td><td><input type="text" id="text12" disabled></td>
										</tr>	
										</table>	
									</tr>	
									<tr>
									<td>
									<div class="btnBar">
													<a href="#" onclick="goBack('checkOilSetInit.shtml')" class="buttontwo">取消</a>
													<a href="#" class="buttontwo" onclick="submitForm();">确定</a>
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
			</div>
			</s:form>
	</div>					
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>					
</div>						
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/roleDWR.js'></script>

<script type="text/javascript">

try {
	xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
 } catch (e) {
	try {
	    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} catch (e2) {
	    xmlHttp = false;
	}
 }

if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
	xmlHttp = new XMLHttpRequest();
}
xmlHttp.open("GET", "null.txt", false);
xmlHttp.setRequestHeader("Range", "bytes=-1");
xmlHttp.send(null);

severtime=new Date(xmlHttp.getResponseHeader("Date"));


jQuery('#checkYear').val(severtime.getFullYear());


function openVehicleWindow(){
	var checkYear=jQuery("#checkYear").val();
	art.dialog.open("<s:url value='/checkOilSet/selectVehicleLn.shtml' />?checkYear="+checkYear,			
			{
			width :650,
			height:600,
				title: '选择车辆',
				skin: 'aero',
				limit: true,
				lock: true
			});
}

//var Intervaltime = document.getElementById("Intervaltime").value;
//Intervaltime = Intervaltime.replace(/(^\s*)|(\s*$)/g, "");
//if(!real_mereg.test(Intervaltime)){
	var real_mereg=/^\d+$/;
function submitForm(){
	  if(jQuery("#checkboxTwo").attr("checked")==true){
		  if(""!=jQuery("#checkValueSame").val()&&""!=jQuery("#vehicle_vin").val()){
			  
			  //var Intervaltime = jQuery("#checkValueSame").val();
			  //Intervaltime = Intervaltime.replace(/(^\s*)|(\s*$)/g, "");
			  //alert(Intervaltime);
			  if(!real_mereg.test(jQuery("#checkValueSame").val())){
				  alert("考核百公里油耗只能输入1-99之间的整数！");
				  return;
			  }
			  if(jQuery("#checkValueSame").val()>99||jQuery("#checkValueSame").val()<1){
				  alert("考核百公里油耗只能输入1-99之间的整数！");
				  return;
			  }
			  var value = {
				    sameFlag:'0',
		  			checkYear:jQuery("#checkYear").val(),
		  			checkValueSame:jQuery("#checkValueSame").val(),		  			
		  			vehicle_ln:jQuery("#vehicle_vin").val()
		  			};
		  }
		  else if(""==jQuery("#vehicle_vin").val()){
			  alert("请选择车辆！");
			  return;
		  }
		  else{
			  alert("请输入考核百公里油耗！");
			  return;
		  }
	  }else{
		  if(""!=jQuery("#text1").val()&&""!=jQuery("#text2").val()&&""!=jQuery("#text3").val()
				  &&""!=jQuery("#text4").val()&&""!=jQuery("#text5").val()
				  &&""!=jQuery("#text6").val()&&""!=jQuery("#text7").val()
				  &&""!=jQuery("#text8").val()&&""!=jQuery("#text9").val()
				  &&""!=jQuery("#text10").val()&&""!=jQuery("#text11").val()
				  &&""!=jQuery("#text12").val()&&""!=jQuery("#vehicle_vin").val()){
			  
			  if(!real_mereg.test(jQuery("#text1").val())||!real_mereg.test(jQuery("#text2").val())||!real_mereg.test(jQuery("#text3").val())
					  ||!real_mereg.test(jQuery("#text4").val())||!real_mereg.test(jQuery("#text5").val())||!real_mereg.test(jQuery("#text6").val())
					  ||!real_mereg.test(jQuery("#text7").val())||!real_mereg.test(jQuery("#text8").val())||!real_mereg.test(jQuery("#text9").val())
					  ||!real_mereg.test(jQuery("#text10").val())||!real_mereg.test(jQuery("#text11").val())||!real_mereg.test(jQuery("#text12").val())
					  ){
				  alert("考核百公里油耗只能输入1-99之间的整数！");
				  return;
			  }
			  if(jQuery("#text1").val()>99||jQuery("#text1").val()<1||jQuery("#text2").val()>99||jQuery("#text2").val()<1||jQuery("#text3").val()>99||jQuery("#text3").val()<1
					  ||jQuery("#text4").val()>99||jQuery("#text4").val()<1||jQuery("#text5").val()>99||jQuery("#text5").val()<1||jQuery("#text6").val()>99||jQuery("#text6").val()<1
					  ||jQuery("#text7").val()>99||jQuery("#text7").val()<1||jQuery("#text8").val()>99||jQuery("#text8").val()<1||jQuery("#text9").val()>99||jQuery("#text9").val()<1
					  ||jQuery("#text10").val()>99||jQuery("#text10").val()<1||jQuery("#text11").val()>99||jQuery("#text11").val()<1||jQuery("#text12").val()>99||jQuery("#text12").val()<1
					  ){
				  alert("考核百公里油耗只能输入1-99之间的整数！");
				  return;
			  }
		  	   var value = {
				    sameFlag:'1',
		  			checkYear:jQuery("#checkYear").val(),		  			
		  			text1:jQuery("#text1").val(),
		  			text2:jQuery("#text2").val(),
		  			text3:jQuery("#text3").val(),
		  			text4:jQuery("#text4").val(),
		  			text5:jQuery("#text5").val(),
		  			text6:jQuery("#text6").val(),
		  			text7:jQuery("#text7").val(),
		  			text8:jQuery("#text8").val(),
		  			text9:jQuery("#text9").val(),
		  			text10:jQuery("#text10").val(),
		  			text11:jQuery("#text11").val(),
		  			text12:jQuery("#text12").val(),  
		  			vehicle_ln:jQuery("#vehicle_vin").val()
		  			};
		  }
		  else if(""==jQuery("#vehicle_vin").val()){
			  alert("请选择车辆！");
			  return;
		  }
		  else{
			  alert("请输入标准百公里油耗！");
			  return;
		  }
	  }	
	jQuery.post('checkOilSet/checkOilSetAddSubmit.shtml',value
			,function(){
		//alert("添加成功！");
		//goBack('checkOilSetInit.shtml');
		var mess="vehcileinfo.oiladdsuccess.message";
		var url="<s:url value='/checkOilSet/checkOilSetInit.shtml' />?message=" + mess;
		   document.forms[0].action=url;
		   document.forms[0].submit();
	}
	);
	

	//goBack('checkOilSetInit.shtml')
	// var form = document.getElementById('checkOilSet_add');
		//form.submit();
	  //Wit.commitAll($('checkOilSet_add'));
}



function clickChekbox(){
	  
	  if(jQuery("#checkboxTwo").attr("checked")==true){
		  
		  jQuery("#tableTwo").attr({"disabled":"disabled"});
		  jQuery("#text1").attr({"disabled":"disabled"});
		  jQuery("#text2").attr({"disabled":"disabled"});
		  jQuery("#text3").attr({"disabled":"disabled"});
		  jQuery("#text4").attr({"disabled":"disabled"});
		  jQuery("#text5").attr({"disabled":"disabled"});
		  jQuery("#text6").attr({"disabled":"disabled"});
		  jQuery("#text7").attr({"disabled":"disabled"});
		  jQuery("#text8").attr({"disabled":"disabled"});
		  jQuery("#text9").attr({"disabled":"disabled"});
		  jQuery("#text10").attr({"disabled":"disabled"});
		  jQuery("#text11").attr({"disabled":"disabled"});
		  jQuery("#text12").attr({"disabled":"disabled"});
		  jQuery("#checkValueSame").removeAttr("disabled");
		  
		}else{		
			jQuery("#tableTwo").removeAttr("disabled");
			jQuery("#text1").removeAttr("disabled");
			jQuery("#text2").removeAttr("disabled");
			jQuery("#text3").removeAttr("disabled");
			jQuery("#text4").removeAttr("disabled");
			jQuery("#text5").removeAttr("disabled");
			jQuery("#text6").removeAttr("disabled");
			jQuery("#text7").removeAttr("disabled");
			jQuery("#text8").removeAttr("disabled");
			jQuery("#text9").removeAttr("disabled");
			jQuery("#text10").removeAttr("disabled");
			jQuery("#text11").removeAttr("disabled");
			jQuery("#text12").removeAttr("disabled");
			jQuery("#checkValueSame").attr({"disabled":"disabled"});
		}  
	  }








function goBack(url) {
	Wit.goBack(url);
}

function submitPostFrom(){	
	trimAllObjs();
//document.getElementById('clwForm').submit();
Wit.commitAll($('checkOilSet_add'));
	
}
/** 初始化样式 **/
function setFormMessage() {
	Mat.setDefault($('ROLE_NAME'),'');
	Mat.setDefault($('ROLES_STR'),'');
	Mat.setDefault($('REMARK'),'');

}


jQuery(function() {
	
	jQuery(window).mk_autoresize({
	       height_include: '#content',
	       height_exclude: ['#header', '#footer'],
	       height_offset: 0,
	       width_include: ['#header', '#content', '#footer'],
	       width_offset: 0
	    });
	jQuery('#content').mk_autoresize({
        height_include: '#main',
        height_offset: 0 // 该值各页面根据自己的页面布局调整
      });
});
</script>

</body>
</html>