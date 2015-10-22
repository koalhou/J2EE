<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>

<html>
	<head>
	    <%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	    <%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
		<title><s:text name="common.title" /></title>
		<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
		<%@include file="/WEB-INF/jsp/treemanage/css/zTree_enterpriseonly_css.jsp"%>
		<link rel="stylesheet" type="text/css" href="<s:url value="/stylesheets/status.css" />" />
    </head>
	<body>
	  <div id="wrapper">
	    <%@include file="/WEB-INF/jsp/common/header.jsp"%>
	    <div id="content">
		<s:form id="overload_form" action="sendcmdhis" method="post">
		<s:hidden id="chooseorgid" name="chooseorgid" />
		<s:hidden id="year" name="year"/>
			<table  width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id='leftdiv' valign="top" class="leftline">
						<div id="content_leftside">
							<div id="leftInfoDiv" class="treeTab">
								<a href="#" class="tabfocus">组织</a>
								<a href="#" onclick="HideandShowControl()" class="hideLeft"></a>
							</div>
							<div id="lefttree" class="treeBox">
						    	<ul id="treeDemo" class="ztree"></ul>
		    				</div>	
						</div>
					</td>
					<td  valign="top" class="sleftline" id="middeldiv" style="display: none;">
	                   <div id="content_middelside">
		                <div>
	            	     <a href="#" class="showLeft" onfocus="this.blur()" id="showleftbutton" onclick="HideandShowControl()"></a>
	          	       </div>
	                  </div>
            		</td>

					<td valign="top" id="rightdiv">
						<div id="content_rightside">
							<div class="titleBar">
								<div class="title">超载记录</div>
								<div class="workLink">
								 <s:if test="#session.perUrlList.contains('111_3_1_20_2')">
					              	<div class="export">
					              		<a href="#" class="export_icon" onfocus="this.blur()" onclick="exportOverload();">导出</a>
					              	</div>
					              </s:if>
            					</div>
							</div>
							
							<div id="statusManger">
								<div class="car-info">
									<h1 id="carln">未选车</h1>
									<span id="messagetime" class="times"><s:property value="year"/>-00-00 00:00:00</span>
								</div>
								<div class="car-status2">
									<table width="99%" border="0" cellpadding="0" cellspacing="0">
					                	<tr>
					                    	<td align="center" valign="middle" class="pic_box"><img id="overimg" src="../newimages/backgroup.gif" onclick="window.open(this.src)" width="208px" height="160"/></td>
					                        <td valign="top">
					                        <table width="94%" border="0" align="center" cellpadding="0" cellspacing="0">
					                          <tr>
					                            <th width="68" height="30" align="right">状&nbsp;&nbsp;&nbsp;态：</th>
					                            <td align="left" style="padding-left:5px;"><input id="zhuangtai" type="text" style="background:url(../newimages/text_bg_140.png) no-repeat left top; border:none; width:140px; height:22px; text-indent:5px; padding-right:0px; line-height:22px;text-align:center;" readonly="readonly"/></td>
					                            
					                          </tr>
					                          <tr>
					                            <th width="68" height="30" align="right">操作人：</th>
					                            <td align="left" style="padding-left:5px;">
					                            <input id="operuser" type="text" style="background:url(../newimages/text_bg_140.png) no-repeat left top; border:none; width:140px; height:22px; text-indent:5px; padding-right:0px; line-height:22px;text-align:center;" readonly="readonly"/>
					                            <!--  
					                            <div id="operuser" class="text-bg-140"></div>
					                            -->
					                            </td>
					                            
					                          </tr>
					                          <tr>
					                            <th height="30" align="right" valign="top" style="padding-top:5px;">描&nbsp;&nbsp;&nbsp;述：</th>
					                            <td id="miaoshu" align="left" valign="top" style="padding-top:5px;padding-left:6px;"></td>
					                          </tr>
					                        </table></td>
					                        
					                    </tr>
					                </table>
								</div>
								<div class="list-area">
									<div class="list-sech">
										<div class="div_float">
											<table border="0" cellpadding="0" cellspacing="0">
											  <tr>
											     <th align="right"><s:text name="common.vehcileln" />：</th>
								                 <td align="left" style="padding: 0 5px;">
								                     <s:textfield id="vehicle_ln" name="vehicle_ln" maxlength="32" cssClass="input100" onkeypress="chaxun()"/>
								                 </td>
								                 <td>&nbsp;&nbsp;</td>
								                 <th align="right">操作人：</th>
								                 <td align="left" style="padding: 0 5px;">
								                     <s:textfield id="operate_user_name" name="operate_user_name" maxlength="16" cssClass="input100" onkeypress="chaxun()"/>
								                 </td>
								                 <td>&nbsp;&nbsp;</td>
								                 <th align="right">时段：</th>
								                 <td>
								                 	<input readonly="readonly" 
								                 	       id="start_time" 
								                 	       name="start_time" 
								                 	       value="${start_time}"
											               class="Wdate" 
											               type="text"
											               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end_time\')}',isShowClear:false})" />
													<input type="hidden" id="nullEle" /> 
													<s:text name="common.zhi" />
													<input readonly="readonly" 
													       id="end_time" 
													       name="end_time"
											               value="${end_time}" 
											               class="Wdate" 
											               type="text"
											               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'start_time\')}',maxDate:'%y-%M-%d',isShowClear:false})" /></td>
								                  <td>&nbsp;&nbsp;</td>
								                  <td><a href="#" class="btn-blue" onclick="searchList()">查询</a></td>
											  </tr>
											</table>
										</div>
									</div>
									<table cellspacing="4" width="100%">
									  <tr>
									    <td>
											<table id="gala" width="100%" cellspacing="0"></table>
									 	</td>
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
        <script type="text/javascript" src="<s:url value='/scripts/common/function_common.js' />"></script>
        <%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
        <%@include file="/WEB-INF/jsp/treemanage/zTree_enterpriseonly_js.jsp"%>
        <script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
        <script type="text/javascript" language="JavaScript1.2"	src="<s:url value='/scripts/flexigrid/flexigrid2.0.js' />"></script>
        <%@include file="/WEB-INF/jsp/businessmanage/overload/newoverload_js.jsp"%>
</body>
</html>