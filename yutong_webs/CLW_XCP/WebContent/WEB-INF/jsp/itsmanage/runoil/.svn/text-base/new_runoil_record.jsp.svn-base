<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/pop.css" />" />
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>

<body>
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
		<div class="popBox">
		<div id="popArea" class="mk-sidelayer mk-sidelayer-small" style="z-index:901;overflow:hidden;">
			<div class="mk-sidelayer-toolbar">        
		    	<span class="mk-sidelayer-bar-btn show"></span>
		        <h1 class="mk-sidelayer-bar-title"></h1>
		        <div class="mk-sidelayer-tools" style="overflow:hidden;">
		         <!--  <ul>
		              <li id="aa5"><a href="#" onclick="sidelayerButtonControl('aa5')"><img src="../newimages/qipaoimages/btn_tool1.png"/></a></li>
		              <li id="aa4"><a href="#" onclick="sidelayerButtonControl('aa4')"><img src="../newimages/qipaoimages/btn_tool2.png"/></a></li>
		              <li id="aa3"><a href="#" onclick="sidelayerButtonControl('aa3')"><img src="../newimages/qipaoimages/btn_tool3.png"/></a></li>
		              <li id="aa7"><a href="#" onclick="sidelayerButtonControl('aa7')"><img src="../newimages/qipaoimages/btn_tool7.png"/></a></li>
		              <li id="aa1"><a href="#" onclick="sidelayerButtonControl('aa1')"><img src="../newimages/qipaoimages/btn_tool5.png"/></a></li>
		              <li id="aa2"><a href="#" onclick="sidelayerButtonControl('aa25')"><img src="../newimages/qipaoimages/btn_tool6.png"/></a></li>
		              <li id="aa6"><a href="#" onclick="sidelayerButtonControl('aa6')"><img src="../newimages/qipaoimages/ico_warning.png"/></a></li>
		          </ul>-->
		        </div>
		  	</div>
		  <div class="mk-sidelayer-content"></div>
		</div>
		</div>
		<s:form>
			<s:hidden id="user_org_id" name="user_org_id"/>	
			<input type="hidden" id="newDayer" value=""/>
			<s:hidden id="chooseorgid" name="chooseorgid" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id='leftdiv' valign="top" class="leftline">
					<div id="content_leftside">
					<div id="leftInfoDiv" class="treeTab">
									<a href="#" class="tabfocus">组织</a>
									<a href="#" onclick="HideandShowControl()" class="hideLeft"></a>
					</div>
		          <div class="newsearchPlan">
		            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		                <td width="130" align="right"><input id="vehicleLn" type="text" class="input120" maxlength="32" onkeypress="if(event.keyCode==13){searchTree();}"/></td>
		                <td align="center"><a onclick="javascript:searchTree()" class="btnbule">查询</a></td>
		              </tr>            
		            </table>
		          </div>			
					<div id="lefttree" class="treeBox">
						<ul id="treeDemo" class="ztree"></ul>
				    </div>	
					</div>	
					</td>
					<td valign="top" class="sleftline" id="middeldiv" style="display: none;">
			                   <div id="content_middelside">
				                <div>
			            	     <a href="#" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
			          	       </div>
			                  </div>
		            </td>
		            
		            <td valign="top">
		            <div id="content_rightside">
		            <div class="titleBar">
			        <div class="title"><s:text name="menu2.rxcjl" /></div>
				        <s:if test="#session.perUrlList.contains('111_3_4_5_2')">
				            <div class="workLink">
				              <div class="export">
				              <a href="#" class="export_icon" onfocus="this.blur()" onclick="javascript:exportData();"><s:text name="button.daochu" /></a>
				              </div>
				            </div>   
				        </s:if>      
			        </div>
			        <div id="right_main">
						<div class="car-info">
						<table border="0" cellpadding="0" cellspacing="0" style="margin:0px 10px;">
							<tr>
		                    	<th align="right">时&nbsp;&nbsp;间：</th>
		                    	<td align="left" style="padding: 0 5px;">
									<input readonly="readonly" id="queryTime" name="queryTime" value="${queryObj.queryTime}" 
										class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
										  autoPickDate:true,
										  maxDate:'%y-%M-%d',
										  isShowClear:false,
										  onpicked:function(){queryWeekList();}})"/>                          
		                    	</td>
		                    	<td><a  onclick="javascript:queryWeekList();" class="btn-blue">查询</a></td>
		                	</tr>
						</table>
						</div>
						<div class="week-list">
			           		 <div style=" float:left; margin-right:5px;"><img src="../newimages/week_arrow_left.png" width="16" height="50" onclick="previousWeek()"/></div>
			              	<ul>
			                	<li id="li0" onclick="changeChoose('0')"><span id="week0" class="week_bold"></span><span id="day0"></span></li>
			                    <li id="li1" onclick="changeChoose('1')"><span id="week1" class="week_bold"></span><span id="day1"></span></li>
			                    <li id="li2" onclick="changeChoose('2')"><span id="week2" class="week_bold"></span><span id="day2"></span></li>
			                    <li id="li3" onclick="changeChoose('3')"><span id="week3" class="week_bold"></span><span id="day3"></span></li>
			                    <li id="li4" onclick="changeChoose('4')"><span id="week4" class="week_bold"></span><span id="day4"></span></li>
			                    <li id="li5" onclick="changeChoose('5')"><span id="week5" class="week_bold"></span><span id="day5"></span></li>
			                    <li id="li6" class="today" onclick="changeChoose('6')"><span id="week6" class="week_bold"></span><span id="day6"></span></li>
			                </ul>
			                <div style=" float:left; margin-left:5px;"><img src="../newimages/week_arrow_right.png" width="16" height="50" onclick="nextWeek()"/></div>
			            </div>
						<div class="table_list">
							<table cellspacing="4" width="100%">
			   					<tr>
			    					<td><table id="galaList" width="100%" cellspacing="0"></table></td>
		              				</tr>
		              			</table>
						</div>
					</div>
					</div>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<s:form id="export_form" action="" method="post">
	<s:hidden id="exportObj.str_vins" name="exportObj.str_vins"/>
	<s:hidden id="exportObj.organization_id" name="exportObj.organization_id"/>
	<s:hidden id="exportObj.queryTime" name="exportObj.queryTime"/>
</s:form>

<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript" src="<s:url value='/scripts/md5/base64.js' />"></script>
<script type="text/javascript" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="/WEB-INF/jsp/treemanage/zTree_withoutonline.jsp"%>
<%@include file="runoil_record_validate.jsp"%>
</body>
</html>