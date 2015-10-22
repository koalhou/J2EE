<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="width:700px; min-width:700px;">
<s:hidden id ="stu_id" name ="stu_id" ></s:hidden>
<s:hidden id ="update" name ="update" ></s:hidden>
<s:hidden id ="vehicle_vin_old" name ="vehicle_vin_old" ></s:hidden>
<s:hidden id ="siteid" name ="siteid" ></s:hidden>
<s:hidden id ="route_id" name ="route_id" ></s:hidden>
<s:hidden id ="upstudentids" name ="upstudentids" ></s:hidden>
<s:hidden id ="downstudentids" name ="downstudentids" ></s:hidden>
<s:hidden id ="studentids" name ="studentids" ></s:hidden>
<s:hidden id ="flag" name ="flag" ></s:hidden>
<table  width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td  valign="top">
		<table width="100%" border="0" cellspacing="5" cellpadding="0">
			<tr>
			  <td valign="top">
			   <table width="100%" border="0" cellspacing="0" cellpadding="0">
			     <tr>
			        <td class="reportOnline">
			        <table width="100%" border="0" cellspacing="0" cellpadding="0">		
                     <tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
						<tr>
                           <td class="titleStyle">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td width="30%" class="titleStyleZi">学生信息</td>
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
		</td>
	</tr>
</table>
</body>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid.js'/>"></script>
<script type="text/javascript">
	jQuery(function() {
	 var gala = jQuery('#gala');
	 var url= "../ridingplanpkg/ridingSTShow.shtml?info.stu_id="+jQuery('#studentids').val();	 
	 gala.flexigrid({
	  url: '<s:url value="'+url+'" />', 
	  dataType: 'json',    //json格式
	  height: 371,
	  width:670,
	  colModel : [ 
            {display: '',name : '', width : 20, sortable : false, align: 'center',hide:true,toggle:false}, 
	        {display: '学号',name : 'stu_code', width : 60, sortable : true, align: 'center', escape:true}, 
	        {display: '姓名', name : 'stu_name', width : 128, sortable : true, align: 'center', escape:true},	       
	        {display: '学校', name : 'stu_school', width : 100, sortable : true, align: 'center', escape:true},
	        {display: '班级', name : 'stu_class', width : 200, sortable : true, align: 'center', escape:true}     
	        ],
	       sortname: 'stu_id',
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
</script>
</html>

