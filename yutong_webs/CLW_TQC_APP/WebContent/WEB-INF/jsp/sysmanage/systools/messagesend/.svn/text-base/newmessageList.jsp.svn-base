<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
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
         <s:form id="message_form" action="messageSendList" method="post">
		  <s:hidden id="chooseorgid" name="chooseorgid" />
		  <s:hidden id="year" name="year"/>
		  <input id="start_time_hidden" value="${end_time}" type="hidden"/>
		  <input id="sms_type" type="hidden" value="下行"/>
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
                            <div class="title">历史消息</div>
                         </div>
                        <div id="statusManger">
                            <div class="car-info">
                              <h1 id="carln">未选车</h1>
                              <span id="messagetime" class="times"><s:property value="year"/>-00-00 00:00:00</span>
                             </div>
                              <div class="car-status">
              	                  <table width="700" border="0" cellpadding="0" cellspacing="0">
                	                <tr>
                    	               <th align="left" width="50">状&nbsp;&nbsp;&nbsp;态：</th>
                                       <td width="140"><input id="zhuangtai" type="text" style="background:url(../newimages/text_bg_140.png) no-repeat left top; border:none; width:140px; height:22px; text-indent:5px; padding-right:0px; line-height:22px;text-align:left;" readonly="readonly"/></td>
                                       <th align="right" width="100">发消息人：</th>
                                       <td width="140"><input id="user" type="text" style="background:url(../newimages/text_bg_140.png) no-repeat left top; border:none; width:140px; height:22px; text-indent:5px; padding-right:0px; line-height:22px;text-align:left;" readonly="readonly"/></td>
                                       
                                       <th align="right"></th>
                                       <td align="left" id="upanddown" ></td>
                                       
                                    </tr>
                                    <tr >
                                     <td colspan="6" height="10px;">
                                     </td>
                                    </tr>
                                    <tr>
                                       <th align="left" width="50">内&nbsp;&nbsp;&nbsp;容：</th>
                                       <td align="left" colspan="5">
                                        <input id="neirong" type="text" style="background:url(../newimages/text_bg_520.png) no-repeat left top; border:none; width:520px; height:22px; text-indent:5px; padding-right:0px; line-height:22px;text-align:left;" readonly="readonly"/>
                                       </td>
                                     </tr>
                                 </table>
                             </div>
                             <div class="list-area">
                                <div class="list-sech">
              	                  <table border="0" cellpadding="0" cellspacing="0">
                	                <tr>
                    	               <th align="right">班车号：</th>
                                       <td align="left" style="padding: 0 5px;">
                                       		<s:textfield id="vehicle_code" name="vehicle_code"  maxlength="32" cssClass="input100" onkeypress="chaxun()"/>
                                       </td>
                                       <th align="right">车牌号：</th>
                                       <td align="left" style="padding: 0 5px;">
                                       		<s:textfield id="vehicle_ln" name="vehicle_ln"  maxlength="32" cssClass="input100" onkeypress="chaxun()"/>
                                       </td>
                                       <td>&nbsp;&nbsp;</td>
                                       <%-- 
                                       <th align="right">上下行：</th>
                                       <td align="left" style="padding: 0 5px;">
							                <select name="sms_type" id="sms_type" style="width: 90px;height: 20px;" onchange="mychange()">
																					<!--  onchange="changeMe(this.options[this.options.selectedIndex].value)">-->
																							<option value="">
																								全部
																							</option>
																							<option value="上行">
																								上行
																							</option>
																							<option value="下行">
																								下行
																							</option>
											</select>                                       
                                       </td>
                                       --%>
                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                       <th align="right">时 &nbsp;&nbsp;段：</th>
                                         <td  align="left" style="padding: 0 5px;">
							               <input readonly="readonly" id="start_time" name="start_time" value="${start_time}" 
																class="Wdate" type="text" 
																style="width:100px;"
																onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end_time\')}',isShowClear:false})" style="width: 90px"/>
																<b>&nbsp;至&nbsp;</b>
										   <input readonly="readonly" id="end_time" name="end_time" value="${end_time}"
																class="Wdate" type="text" 
																style="width:100px;"
																onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'start_time\')}',maxDate:'#F{$dp.$D(\'start_time_hidden\')}',isShowClear:false})" style="width: 90px"/>                                         
                                         </td>
                                         <td>&nbsp;&nbsp;</td>
                                         <td><a href="#" class="btn-blue" onclick="searchList()">查询</a></td>
                                    </tr>
                                 </table>                
                                </div>
                                <table cellspacing="4" width="100%">
			                     <tr>
			                      <td>
                                   <table id="gala" width="100%" cellspacing="0">
				                   </table>
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
   <script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
		<script type="text/javascript" language="JavaScript1.2"
			src="<s:url value='/scripts/flexigrid/flexigrid2.0.js' />"></script>
   <%@include file="/WEB-INF/jsp/sysmanage/systools/messagesend/newmessageList_js.jsp"%>
</body>
</html>