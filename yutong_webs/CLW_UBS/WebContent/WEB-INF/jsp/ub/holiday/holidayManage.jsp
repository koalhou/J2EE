<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../../common/taglibs.jsp" %>
<html>
<head>
<%@include file="../../common/meta.jsp" %>
<title><s:property value="getText('menu.ub.holiday')" /></title>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<style type="text/css">
  .reportOnline5{ 
    background: #fff; 
    border-bottom: 1px solid #c3c3c3; 
    border-left: 1px solid #c3c3c3; 
    border-right: 1px solid #c3c3c3;
  }
</style>


</head>
<body>
  <%@include file="../../common/menu.jsp"%>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
  
  
  function queryForm() {	  
	  var f=$('holidaymanager_form');//此处$调用的事mootools的方法
	  f.action = "<s:url value='/ub/holiday/queryholiday.shtml' />";	   
      Wit.commitAll(f);
  }   
</script>
  <s:form id="holidaymanager_form" action="queryholiday" method="post">

  <table height="628px" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr valign="top">
      <td class="reportOnline5" style="padding:5px;">
      <div class="ubtab">
      <s:url id="workdaymanage" action="workdaymanage"/>
			<ul>
                  <li class="ubtabselect">
                    <a href="#" >
                      <s:property value="getText('ub.holiday.holidaytitle')" />
                    </a>
                  </li>
                  <li>
                    <a href="${workdaymanage}">
                      <s:property value="getText('ub.holiday.workdaytitle')" />
                    </a>
                  </li>
                </ul>
			<div class="ubtabcontent" >
				<div style="margin:5px">
				<!-- tab内容 -->
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
                
                <tr>
                  <td class="reportOnline2">
                    <table width="100%" border="0" cellspacing="8" cellpadding="0">
                      <tr>
                        <td>
                          <table border="0" cellspacing="4" cellpadding="0" >
                            <tr>
                             
                              
                              <td>
                                <s:property value="getText('ub.holiday.select')" />：
                               
                               <s:select id="selYear" 
                                          name="searchparam.year_select" 
                                          list="yearList" cssStyle="width:80px" onchange="queryForm()"> 
		                        </s:select> 
		                        
		                        
                              </td>
                              
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
                                    <td width="30%" class="titleStyleZi">
                                      <s:property value="getText('ub.holiday.list')" />
                                    </td>
                                    <td width="69%"align="right">
	                                    <s:if test="#session.perUrlList.contains('111_4_8_3_1_2')">
		                                <div class="buhuanhangbut">
		                                  <a href="<s:url value='/ub/holiday/holidaymanage_goto_add.shtml' />" class="btnAdd" title="<s:property value="getText('msg.add')" />">
		                                  </a>
		                                </div>
		                                </s:if>
                                    </td>
                                    <td width="1%">&nbsp;</td>
                                  </tr>
                                </table>
                              </td>
                            </tr>
                            <tr>
                              <td class="reportInline">
                               <div id="message">
                                          <s:actionerror theme="mat" />
                                          <s:fielderror theme="mat"/>
                                          <s:actionmessage theme="mat"/>
                                        </div>
                                <div id="list">
                                  <table width="100%" cellspacing="0" class="reportInline2" >
                                    <thead>
                                      <tr>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="5%" scope="col">
                                          <s:property value="getText('list.number')" />
                                        </th>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="9%" scope="col">
                                          <s:property value="getText('ub.holiday.name')" />
                                        </th>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="10%" scope="col">
                                          <s:property value="getText('ub.holiday.duration')" />
                                        </th>
                                        <th height="22" background="<s:url value='/images/listTitle_bg.gif'/>" width="15%" scope="col">
                                          <s:property value="getText('ub.holiday.daycount')" />
                                        </th>
                                        
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <s:iterator value="staticsList" status="rowstatus">
                                      <tr>
                                        <td>
                                          <s:property value="#rowstatus.index+1" /> 
                                        </td>
                                        <td>
						                   <s:url id="showDetail" action="holidaymanage_goto_edit">
						                      <s:param name="groupname" value="groupname" />
									          <s:param name="page">${page}</s:param>
									          <s:param name="pageSize">${pageSize}</s:param>
								            </s:url>
								            <a href="#" onclick="Wit.goBack('${showDetail}');">
								              <s:property value="day_name" />
								            </a>
                                        </td>
                                        <td>
						                  <s:property value="date_section" />
                                        </td>
                                        <td>
                                          <s:property value="statics_count" />
                                        </td>
                                                                   
                                      </tr>
                                      </s:iterator>
                                    </tbody>
                                  </table>
                                  
                                  <div id="pageCtrl" align="right">
                                    <s:property value="pageBar" escape="false" />
                                  </div>
                                  
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
				</div>
			</div>
		</div>
      
        
      </td>
    </tr>
  </table>
  </s:form>
    
  <%@include file="../../common/copyright.jsp"%>
</body>
</html>