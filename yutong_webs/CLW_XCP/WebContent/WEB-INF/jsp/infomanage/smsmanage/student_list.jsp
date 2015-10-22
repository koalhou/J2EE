<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/flexigrid/flexigrid.css'/>" />
</head>
<body style="width:800px; min-width:800px;">
<s:form id="routeveh_form" action="report_veh" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
								学号：
								<s:textfield id="student_code" name="student_code" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStu();}"/>&nbsp;&nbsp;
								</td>
								<td>
								姓名：
								<s:textfield id="student_name" name="student_name" cssClass="input120" maxlength="16" onkeypress="if(event.keyCode==13){searchStu();}"/>&nbsp;&nbsp;
								</td>
								<td>
								学校：
								<s:textfield id="student_school" name="student_school" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStu();}"/>&nbsp;&nbsp;
								</td>
								<td>
								班级：
								<s:textfield id="student_class" name="student_class" cssClass="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchStu();}"/>
								</td>
								<td><a href="#" onclick="searchStu()" class="sbutton">查询</a></td>
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
                                      <td width="30%" class="titleStyleZi">学生信息</td>
                                   <td width="69%"align="right"></td>
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
</s:form>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
<script type="text/javascript">
//查询
function searchStu() {
	jQuery('#gala').flexOptions({		
		newp: 1,
		params: [{name: 'studentInfo.student_code', value: formatSpecialChar(jQuery('#student_code').val()) },
		         {name: 'studentInfo.student_name', value: formatSpecialChar(jQuery('#student_name').val()) },
		         {name: 'studentInfo.student_school', value: formatSpecialChar(jQuery('#student_school').val()) },
		         {name: 'studentInfo.student_class', value: formatSpecialChar(jQuery('#student_class').val()) }			 		
			 		]
	});
	jQuery('#gala').flexReload();
}

//转换链接
function reWriteLink(tdDiv,pid,row){
	return '<a href="javascript:operate(\'' + pid + '\')">' + tdDiv +'</a>';
}

jQuery(function() {
	 var gala = jQuery('#gala');
	 gala.flexigrid({
	  url: '<s:url value="/smsGrid/stuList.shtml" />', 
	  dataType: 'json',    //json格式
	  height: 271,
	  width:775,
	  colModel : [ 
	        {display: '学号',name : 'STU_CODE', width : 120, sortable : true, align: 'center', escape:true, process:reWriteLink}, 
	        {display: '姓名', name : 'STU_NAME', width : 100, sortable : true, align: 'center', escape:true},
	        {display: '学校', name : 'STU_SCHOOL', width : 200, sortable : true, align: 'center', escape:true},
	        {display: '班级', name : 'STU_CLASS', width : 150, sortable : true, align: 'center', escape:true}	       
	        ],
	       sortname: 'STU_ID',
	       sortorder: 'asc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :true,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: true,    // 是否可以动态设置每页显示的结果数
	       rp: 10,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true   // 是否允许显示隐藏列
	     });
	});

	function operate(id){
		jQuery.ajax({
			type: 'POST', 
			url: "<s:url value='/holidayGrid/getStudentNameById.shtml' />",	
			data:  {"studentInfo.student_id":id},
			success: function(data) {
				window.parent.document.getElementById("student_id").value=id;
		  		window.parent.document.getElementById("student_name").value=data;
		  		art.dialog.close();
			}  
		});
  	}
</script>
</body>
</html>