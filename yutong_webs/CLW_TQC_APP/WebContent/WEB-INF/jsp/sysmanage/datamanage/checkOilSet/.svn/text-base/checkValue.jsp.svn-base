<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body style="width:340px; min-width:340px;">
		<s:form id="alertCheck_form" action="alterCheckInfoeeee" method="post">		
		<input type="hidden" id="vehicle_vin"  value="${checkInfo.vehicle_vin}"/>
		<input type="hidden" id="checkTimeCode" value="${checkInfo.checkTimeCode}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">					     
					     <tr>
					        <td class="reportInline" align="left">
					        <div>
					           <div id="Below_new" style="text-align:center; color:red"></div>				        
		                       <table id="vehicletbl" width="100%"  cellspacing="0">
							   </table>
					        </div>
					        </td>
					     </tr>
					    <tr>
							<td>
							<div class="btnBar">
											<a href="#" class="buttontwo" onclick="goBack();" >取消</a>
											<a href="#" class="buttontwo" onclick="deleteForm();">删除</a>
											<a href="#" class="buttontwo" onclick="submitForm();">保存</a>
										</div>
							</td>
						</tr>		
					   </table>
		</s:form>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
var real_mereg=/^\d+$/;
function submitForm(){
	//trimAllObjs();
	//Wit.commitAll($('alertCheck_form'));
	if(!real_mereg.test(jQuery("#01").val())||!real_mereg.test(jQuery("#02").val())||!real_mereg.test(jQuery("#03").val())
			  ||!real_mereg.test(jQuery("#04").val())||!real_mereg.test(jQuery("#05").val())||!real_mereg.test(jQuery("#06").val())
			  ||!real_mereg.test(jQuery("#07").val())||!real_mereg.test(jQuery("#08").val())||!real_mereg.test(jQuery("#09").val())
			  ||!real_mereg.test(jQuery("#10").val())||!real_mereg.test(jQuery("#11").val())||!real_mereg.test(jQuery("#12").val())
			  ){
		  alert("考核百公里油耗只能输入1-99之间的整数！");
		  return;
	  }
	  if(jQuery("#01").val()>99||jQuery("#01").val()<1||jQuery("#02").val()>99||jQuery("#02").val()<1||jQuery("#03").val()>99||jQuery("#03").val()<1
			  ||jQuery("#04").val()>99||jQuery("#04").val()<1||jQuery("#05").val()>99||jQuery("#05").val()<1||jQuery("#06").val()>99||jQuery("#06").val()<1
			  ||jQuery("#07").val()>99||jQuery("#07").val()<1||jQuery("#08").val()>99||jQuery("#08").val()<1||jQuery("#09").val()>99||jQuery("#09").val()<1
			  ||jQuery("#10").val()>99||jQuery("#10").val()<1||jQuery("#11").val()>99||jQuery("#11").val()<1||jQuery("#12").val()>99||jQuery("#12").val()<1
			  ){
		  alert("考核百公里油耗只能输入1-99之间的整数！");
		  return;
	  }
		  var value = {	
				    sameFlag:'1',
				    checkTimeCode:jQuery("#checkTimeCode").val(),
				    vehicle_vin:jQuery("#vehicle_vin").val(),
		  			text1:jQuery("#01").val(),
		  			text2:jQuery("#02").val(),
		  			text3:jQuery("#03").val(),
		  			text4:jQuery("#04").val(),
		  			text5:jQuery("#05").val(),
		  			text6:jQuery("#06").val(),
		  			text7:jQuery("#07").val(),
		  			text8:jQuery("#08").val(),
		  			text9:jQuery("#09").val(),
		  			text10:jQuery("#10").val(),
		  			text11:jQuery("#11").val(),
		  			text12:jQuery("#12").val()	
		  			};
	jQuery.post('checkOilSet/updateCheckInfo.shtml',value,function(){
		//var mess="保存车辆考核油耗成功";
		//window.parent.document.getElementById("Below_new").value=mess;
		//art.dialog.close();
		//goBack();
		//alert("修改成功");
		var mess="vehcileinfo.saveoilsuccess.message";
		var url="<s:url value='/checkOilSet/checkOilSetInit.shtml' />?message=" + mess;
		window.parent.document.forms[0].action=url;
		window.parent.document.forms[0].submit();
		art.dialog.close();
	});
	
}

function goBack(){
	art.dialog.close();
}

function deleteForm(){
		  var value = {				   
		  			vehicle_vin:jQuery("#vehicle_vin").val()
		  			};	
	if (!confirm("确定要删除吗？")) {return;}	
	jQuery.post('checkOilSet/deleteCheckInfo.shtml',value,function(){
		//var mess="vehcileinfo.deloilsuccess.message";
		//var mess="删除车辆考核油耗成功";
		//window.parent.document.getElementById("Below_new").value=mess;
		//
		//goBack('checkOilSetInit.shtml?');
		//art.dialog.close();
		//alert("删除车辆油耗考核明细成功")
		var mess="vehcileinfo.deloilsuccess.message";
		var url="<s:url value='/checkOilSet/checkOilSetInit.shtml' />?message=" + mess;
		window.parent.document.forms[0].action=url;
		window.parent.document.forms[0].submit();
		art.dialog.close();
	});
}
function operate(str1,str2){
		window.parent.document.getElementById("vehicle_ln").value=str1;
		window.parent.document.getElementById("vehicle_vin").value=str2;
		//window.parent.document.getElementById("vehicle_vin").value=str2;
		art.dialog.close();
	}

function reWriteLink(tdDiv,pid,row){
	 return '<a href="javascript:operate(\'' + row.cell[0] + '\',\'' + row.cell[1] + '\')">' + tdDiv +'</a>';
}

function getNull(tdDiv,pid,row){
	 if(!row.cell[1]&&!row.cell[2]){
		 var mess="您还没有设置"+pid.substring(0,4)+"年的考核月度，请设置！";
		 jQuery("#Below_new").html(mess);
		 //jQuery("#Below_new").show();
		 return;
	 }
	 return row.cell[1];
		 
}

function reWrite(tdDiv,pid){	
	var number=pid.split("-");
   return	'<input type="text" id='+number[1]+'  value='+tdDiv+'>';
	
}

jQuery(function() {
	
	var vehicle_vin=jQuery('#vehicle_vin').val();
	var checkTimeCode=jQuery('#checkTimeCode').val();
	var gala = jQuery('#vehicletbl');
	gala.flexigrid({
		url: '<s:url value="/checkOilSetlist/getCheckValueTime.shtml" />?vehicle_vin='+vehicle_vin+'&checkTimeCode='+checkTimeCode,
		dataType: 'json',
		height: 310,
		width:620,
		colModel : [
                    {display: '月份', name : 'check_time_code', width :110, sortable : true, align: 'center',escape: true},
                    {display: '考核开始日期', name : 'start_time', width : 170, sortable : true, align: 'center',process:getNull,escape: true},
                    {display: '考核结束日期', name : 'end_time', width : 170, sortable : true, align: 'center',escape: true},
                    {display: '考核百公里油耗（L）', name : 'check_value', width : 120, sortable : true, align: 'center',process:reWrite,escape: true},
                    {display: '', name : 'VEHICLE_VIN', width : 10,hide:true, sortable : true, align: 'center',escape: true}
		    		],		    		    		
		    	sortname: 'check_time_code',
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


