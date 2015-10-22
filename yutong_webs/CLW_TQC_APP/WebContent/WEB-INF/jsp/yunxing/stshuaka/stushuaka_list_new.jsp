<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
<!-- 引入照片列表页样式  -->
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/photo.css" />" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<s:hidden id="user_org_id" name="user_org_id"/>
		<s:hidden id="vehicle_vins" name="vehicle_vins" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>			
					<td id='leftdiv' valign="top" class="leftline">
						<!-- 组织 车辆 树  -->
						<div id="content_leftside">
							<div id="leftInfoDiv" class="treeTab">
								<a href="#" class="tabfocus" onfocus="this.blur()">组织</a>
								<a href="#" class="hideLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a>
							</div>
							<div class="newsearchPlan">
					            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					              <tr>
					                <td width="130" align="right"><input id="vehicleLn" type="text" class="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchTree();}"/></td>
					                <td align="center"><a href="#" class="btnbule" onfocus="this.blur()" onClick="searchTree()">查询</a></td>
					              </tr>            
					            </table>
							</div>
          					<div id="treeDemoDiv" class="treeBox"><ul id="treeDemo" class="ztree"></ul></div>
				  		</div>
					</td>
				<td id="middeldiv" valign="top" class="sleftline" style="display: none;">
	                   <div id="content_middelside">
		                <div>
	            	     <a href="#" id="showleftbutton" class="showLeft" onfocus="this.blur()" onclick="HideandShowControl()"></a>
	          	       </div>
	                  </div>
            	</td>
				<td id="rightdiv" valign="top">
					<div id="content_rightside"><!-- content_rightside 开始 -->	 
					<div class="titleBar">
						<div class="title">学生刷卡记录</div>
						<div class="workLink"></div>
					</div><!-- statusManger 开始 -->
					<div id="statusManger">	
						<div class="car-info">
							<table width="192" border="0" cellspacing="0" cellpadding="0">
								<tr>
		  							<td width="15">&nbsp;</td>
		  							<s:hidden id="st_ride_flag" name="st_ride_flag"/>
									<td><input type="checkbox" name="st_ride_flag_1" id="st_ride_flag_1" checked="true" onclick="searchList();"/>
									</td>
									<td>上车刷卡</td>
									
									<td><input type="checkbox" name="st_ride_flag_2" id="st_ride_flag_2" checked="true" onclick="searchList();"/>
									</td>
									<td>下车刷卡</td>
								</tr>
							</table>
						</div>
					</div>
					
					<div id="info_stu_div">
					<div style="height: 5px;"></div><!-- 详情显示区域（开始） -->
					<table width="800" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="30" align="right"><strong>姓&nbsp;&nbsp;&nbsp;名：</strong>
							</td>
							<td class="text-bg-132">
							<input id="stu_name_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
							</td>
							<td align="right"><strong>学&nbsp;&nbsp;&nbsp;校：</strong>
							</td>
							<td class="text-bg-132">
							<input id="stu_school_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
							</td>
							<td align="right"><strong>班&nbsp;&nbsp;&nbsp;级：</strong>
							</td>
							<td class="text-bg-132">
							<input id="stu_class_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
							</td>
							<td align="right"><strong>学&nbsp;&nbsp;&nbsp;号：</strong>
							</td>
							<td class="text-bg-132">
							<input id="stu_code_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
							</td>
						</tr>
						<tr>
							<td height="30" align="right"><strong>车牌号：</strong>
							</td>
							<td class="text-bg-132">
							<input id="vehicle_ln_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
							</td>
							<td align="right"><strong>状&nbsp;&nbsp;&nbsp;态：</strong>
							</td>
							<td class="text-bg-132">
							<input id="st_ride_flag_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
							</td>
							<td align="right"><strong>时&nbsp;&nbsp;&nbsp;间：</strong>
							</td>
							<td class="text-bg-132">
							<input id="terminal_time_v" type="text" style="background:url(../newimages/input_bg_chart.png) no-repeat left top; border:none; width:132px; height:22px; text-indent:5px; padding-right:5px; line-height:22px;" readonly="readonly"/>							
							</td>
							<td align="right"><strong>位&nbsp;&nbsp;&nbsp;置：</strong>
							</td>
							<td class="text-bg-132"><span id="location_v"/></td>
						</tr>
					</table>
					<div style="height: 5px;"></div><!-- 详情显示区域（结束） -->
					</div>

					<div class="search_condition"><!-- 查询区域 开始 -->
						<table id="search_stu_tab" width="980" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="65" align="right"><strong>姓&nbsp;&nbsp;&nbsp;名：</strong></td>
								<td class="text100">
									<s:textfield id="stu_name" name="stu_name" cssClass="text100"  maxlength="16" onkeypress="if(event.keyCode==13){searchList();}"/>
								</td>
								<td width="65" align="right"><strong>学&nbsp;&nbsp;&nbsp;校：</strong></td>
								<td class="text100">
									<s:textfield id="stu_school" name="stu_school" cssClass="text100"  maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
								</td>
								<td width="65" align="right"><strong>班&nbsp;&nbsp;&nbsp;级：</strong></td>
								<td class="text100">
									<s:textfield id="stu_class" name="stu_class" cssClass="text100"  maxlength="32" onkeypress="if(event.keyCode==13){searchList();}"/>
								</td>
								<td width="65" align="right"><strong>时&nbsp;&nbsp;&nbsp;段：</strong>
								</td>
								<td align="right" width="100">
									<input id="begTime" name="begTime" value="${queryObj.begTime}" 
									class="Wdate" type="text" readonly="readonly" style="width:100px;" 
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'endTime\')}',position:{top:'under'},isShowClear:false})"/>
								</td>
								<td width="20" align="center">至</td>
								<td align="left" width="100">
									<input id="endTime" name="endTime"  value="${queryObj.endTime}" 
									class="Wdate" type="text" readonly="readonly" style="width:100px;" 
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begTime\')}',maxDate:'%y-%M-%d',position:{top:'under'},maxDate:'%y-%M-%d',isShowClear:false})"/>
								</td>
								<td width="90" align="left"><a href="javascript:void(0);" class="btn-blue"
									onfocus="this.blur()" onclick="javascript:searchList();">查询</a>
								</td>
								<td>&nbsp;</td>
							</tr>
						</table><!-- 查询区域 开始 -->
						<!-- 下面是列表不细作,不使用 -->
						<div class="table_list">
							<table cellspacing="4" width="100%">
			   					<tr>
			    					<td><table id="gala" width="100%" cellspacing="0"></table></td>
                				</tr>
                			</table>
						</div>
					 </div><!-- 查询区域  结束-->
				  </div><!-- content_rightside 结束 -->
				</td>
			</tr>
	</table>
	<!-- 弹出层（地图）开始   -->
	<div id="popArea3" class="popArea3">
			<table id="divTitle" width="100%" border="0" cellspacing="0" cellpadding="0">
	  		<tr>
	    		<td width="3"><img src="../newimages/photo_del_title_bg1.gif" width="3" height="31" /></td>
	    		<td class="popArea3_title_bg">
	    		<h3>位置信息<a href="#" class="close" onfocus="this.blur()" onclick="HideStuLocation()"></a></h3>
	    		</td>
	    		<td width="3"><img src="../newimages/photo_del_title_bg3.gif" width="3" height="31" /></td>
	  		</tr>
	  		</table>
	  		<iframe id="iframeshowArea" name="iframeshowArea" src='' height="385px" width="100%" frameborder="0" scrolling="no"></iframe>
	</div>
	<!-- 弹出层（地图）结束 -->
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="stushuaka_validate_new.jsp"%>
<!-- 引入拖拽工具类  -->
<script type="text/javascript" src="<s:url value='/scripts/jQuery/jquery.easydrag.handler.js' />"></script>
<!-- 引入组织树结构  (组织 车辆)  -->
<%@include file="/WEB-INF/jsp/treemanage/zTree_withoutonline.jsp"%>
<script type="text/javascript">
jQuery(function() {
	auto_size();
	//重新刷新页面 适配IE9的高分辨率
	jQuery(window).mk_autoresize({
		height_include: '#content',
		height_exclude: ['#header', '#footer'],
		height_offset: 0,
		width_include: ['#header', '#content', '#footer'],
		width_offset: 0});
	auto_size();	
	//添加拖动事件  浮动层div（z-index:99）
	jQuery("#popArea3").easydrag();
	//设置拖拽区域
	jQuery("#popArea3").setHandler("divTitle");
});
function auto_size(){
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : [ '#content_rightside', '#content_leftside' ],
		height_offset : 0,
		width_exclude: ['#content_leftside'],
		width_include : [ '#content_rightside'],
		width_offset : 1
	});
	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize( {
		height_exclude : [ '#leftInfoDiv', '.newsearchPlan' ],
		height_include : '#treeDemoDiv',
		height_offset : 10
	});
	//计算树区域高度
	/*
	jQuery('#treeDemoDiv').mk_autoresize( {
		height_include : [ '#treeDemo' ],
		height_offset : 20
	});	*/

	//计算右测区域高度
	jQuery('#content_rightside').mk_autoresize( {
		height_exclude : [ '.titleBar' ,'#statusManger','#info_stu_div'],
		height_include : '.search_condition',
		height_offset : 0,
		width_include : [ '.titleBar','#statusManger','.search_condition','#info_stu_div'],
		width_offset : 0
	});
	jQuery('.search_condition').mk_autoresize( {
		height_exclude : [ '#search_stu_tab'],
		height_include : '.table_list',
		height_offset : 0,
		width_include : [ '.table_list','#search_stu_tab'],
		width_offset : 0
	});		
	jQuery('.table_list').mk_autoresize({
	    height_include: '.bDiv',
	    height_offset: 78, // 该值各页面根据自己的页面布局调整
	    width_include: '.flexigrid',
	    width_offset: 8// 该值各页面根据自己的页面布局调整
	  });
}

/**
 * 左侧树区域显示控制
 */
function HideandShowControl(){//leftdiv
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').css("display","block");
		jQuery('#leftdiv').css("display","none");
		//计算中区高度
		jQuery('#content').mk_autoresize( {
			height_include : [ '#content_rightside', '#content_middelside' ],
			height_offset : 0,
			width_exclude: ['#content_middelside'],
			width_include : [ '#content_rightside'],
			width_offset : 1
		});	

		//计算右测区域高度
		jQuery('#content_rightside').mk_autoresize( {
			height_exclude : [ '.titleBar' ,'#statusManger','#info_stu_div'],
			height_include : '.search_condition',
			height_offset : 0,
			width_include : [ '.titleBar','#statusManger','.search_condition','#info_stu_div'],
			width_offset : 0
		});
		jQuery('.search_condition').mk_autoresize( {
			height_exclude : [ '#search_stu_tab'],
			height_include : '.table_list',
			height_offset : 0,
			width_include : [ '.table_list','#search_stu_tab'],
			width_offset : 0
		});	
		jQuery('.table_list').mk_autoresize({
		    height_include: '.bDiv',
		   height_offset: 78, // 该值各页面根据自己的页面布局调整
		    width_include: '.flexigrid',
		    width_offset: 8// 该值各页面根据自己的页面布局调整
		  });
	}else{//隐藏状态
		jQuery('#middeldiv').css("display","none");
		jQuery('#leftdiv').css("display","block");
		auto_size();
	}
}

</script>
</body>
</html>