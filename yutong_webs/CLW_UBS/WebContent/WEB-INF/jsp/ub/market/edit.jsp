<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<s:url value='/styles/common.css' />"
	type="text/css" media="all" />
<style type="text/css">
body{
font-size: 14px;
color: #333;

}
p{
margin: 5px;
}
label{
margin-right: 4px;
}
a{
margin-top:10px;
    background: url("../../images/btn_blue.gif") repeat-x scroll left top transparent;
    color: white;
    display: inline-block;
    height: 28px;
    padding-top: 5px;
    text-align: center;
    text-decoration: none;
    width: 76px;
}

</style>
<script type="text/javascript" src="<s:url value='/scripts/jquery-easyui-1.2.2/jquery-1.4.4.min.js' />"></script>
<title></title>
<script type="text/javascript">
jQuery(function() {
	$('#submit').click(function(){
		var plan=$.trim($('#plan').val());
		var num = new RegExp(/^\d{1,10}$/);
		if(plan.length==0||!num.test(plan)){
			alert('请输入正确的计划完成量');
			$('#plan').focus();
			return false;
		}
		$.getJSON("json/editTask.shtml", { id: <s:property value="task.id"/>, queryDate: $('#month').val() ,plan:$('#plan').val() }, function(json){
				if(json.error!=null){
					alert(json.error);
				}  
				if(json.ret=='ok'){
					alert("设置成功");
					parent.cancelArtWinClosed('editAMPlan');
				}  
				if(json.ret=='fail'){
					alert("设置失败");
				}  
			});
	});
	$('#reset').click(function(){
		parent.cancelArtWinClosed('editAMPlan');
	});
});

</script>
</head>
<body>
<input id="month" type="hidden" value="<s:property value="task.month"/>">
<p style="margin-top: 20px"><label>月份：</label><s:property  value="task.month"/> </p>
<p><label>市场经理：</label><s:property value="task.name"/> </p>
<p><label>所属大区：</label><s:property value="task.area"/> </p>
<p><label>计划完成：</label><input style="width: 56px;" id="plan" name="plan" value='<s:if test="task.plan !='-1'"><s:property value="task.plan"/></s:if>'>   </p>
<p>&nbsp;&nbsp;
<s:if
												test="#session.perUrlList.contains('111_4_8_4_2')">
<a id="submit" href="javascript:void(0);"  >提&nbsp;&nbsp;交</a></s:if>
 &nbsp;&nbsp;&nbsp;&nbsp;<a id="reset" href="javascript:void(0);"  >取&nbsp;&nbsp;消</a> </p>
</body>
</html>