<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all"/>
<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
</head>

<body style="width:620px; min-width:620px;">
		<s:form id="checkMonth_form" action="alterCheckInfoeeee" method="post">
			<div id="Below_new" style="text-align:center; color:red"></div>
		       <table>
		            <tr>
		            <td align="right">考核年份：</td>
		            <td>
		            <select id="checkYear" name="checkYear" onchange="searchMonth()" >
		            <option vlue="2013">2013</option>
		            <option vlue="2014">2014</option>
		            <option vlue="2015">2015</option>
		            <option vlue="2016">2016</option>
		            <option vlue="2017">2017</option>
		            <option vlue="2018">2018</option>
		            <option vlue="2019">2019</option>
		            <option value='2020'>2020</option>	            
		            </select>
		            </td>
		            <td>&nbsp;</td>   
		            <td align="right">
		            <input type="checkbox" id="selectSame"  onclick="clickChekbox();"/>
		            </td>
		            <td>
		                                 每月相同
		            </td>
		            <td>
		                                本月
		            </td>
		            <td>
		            <select id="thisMonth" name="thisMonth" class="selectButton" disabled>
		            <option vlue="2">2</option>
		            <option vlue="3">3</option>
		            <option vlue="4">4</option>
		            <option vlue="5">5</option>
		            <option vlue="6">6</option>
		            <option vlue="7">7</option>		     
		            <option vlue="8">8</option>
		            <option vlue="9">9</option>
		            <option vlue="10">10</option>
		            <option vlue="11">11</option>
		            <option vlue="12">12</option>
		            <option vlue="13">13</option>
		            <option vlue="14">14</option>		     
		            <option vlue="15">15</option>
		            <option vlue="16">16</option>
		            <option vlue="17">17</option>
		            <option vlue="18">18</option>
		            <option vlue="19">19</option>
		            <option vlue="20">20</option>
		            <option vlue="21">21</option>		     
		            <option vlue="22">22</option>
		            <option vlue="23">23</option>
		            <option vlue="24">24</option>
		            <option vlue="25">25</option>
		            <option vlue="26">26</option>
		            <option vlue="27">27</option>
		            <option vlue="28">28</option>		       
		            </select>
		            </td>
		            <td>
		                                至次月
		            </td>
		            <td>
		            <select id="nextMonth" name="nextMonth" class="selectButton" disabled>
		            <option vlue="1">1</option>
		            <option vlue="2">2</option>
		            <option vlue="3">3</option>
		            <option vlue="4">4</option>
		            <option vlue="5">5</option>
		            <option vlue="6">6</option>
		            <option vlue="7">7</option>		     
		            <option vlue="8">8</option>
		            <option vlue="9">9</option>
		            <option vlue="10">10</option>
		            <option vlue="11">11</option>
		            <option vlue="12">12</option>
		            <option vlue="13">13</option>
		            <option vlue="14">14</option>		     
		            <option vlue="15">15</option>
		            <option vlue="16">16</option>
		            <option vlue="17">17</option>
		            <option vlue="18">18</option>
		            <option vlue="19">19</option>
		            <option vlue="20">20</option>
		            <option vlue="21">21</option>		     
		            <option vlue="22">22</option>
		            <option vlue="23">23</option>
		            <option vlue="24">24</option>
		            <option vlue="25">25</option>
		            <option vlue="26">26</option>
		            <option vlue="27">27</option>
		            <option vlue="28">28</option>		               
		            </select>
		            </td>
		            
		            <td>
		             <!-- <input type="button" id="checkValueSame" onclick="showSameMonth();" value="确定"/>-->
		            <a href="#" id="checkValueSame" class="buttontwo" onclick="showSameMonth();" disabled>确定</a>	
		            </td>
		            
		            <td>		            
		             <!-- <input type="button" id="checkNatureMonth" onclick="showNatureMonth();" value="使用自然月份"/> -->
		             <a href="#" id="checkNatureMonth" class="buttontwo" onclick="showNatureMonth();"  >使用自然月份</a>	
		             </td>
		            </tr>
		            
		            </table>
		               <table id="monthList" >
					    <tr>
					     <td align="right">1月：</td>
					     <td class="fsBlack">
								<input id="begin1" name="begin1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end1\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
								<input id="end1" name="end1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin1\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
                        </td>
                        <td align="right">2月：</td>
                        <td class="fsBlack">
								<input id="begin2" name="begin2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end2\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
								<input id="end2" name="end2"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin2\')}' })" class="Wdate" readonly="true"  onkeydown="keyDown()"/>
								
                        </td>
					    </tr>
					    
					    
					    <tr><td>&nbsp;</td></tr>
					    
					    
					    
					    <tr>
                         <td align="right">3月：</td>
					     <td class="fsBlack">
					     	<input id="begin3" name="begin1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end3\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="begin3" name="begin3" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
                        	<input id="end3" name="end3" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin3\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="end3" name="end3" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                         <td align="right">4月：</td>
                        <td  class="fsBlack">
                        	<input id="begin4" name="begin4" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end4\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="begin4" name="begin4" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
                        	<input id="end4" name="end4" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin4\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="end4" name="end4" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
					    </tr>
					     
					    
					    <tr><td>&nbsp;</td></tr>
					    
					    
					    
					    <tr>
                         <td align="right">5月：</td>
					     <td class="fsBlack">
					     	<input id="begin5" name="begin5" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end5\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="begin5" name="begin5" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
                        	<input id="end5" name="end5" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin5\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="end5" name="end5" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                         <td align="right">6月：</td>
                        <td  class="fsBlack">
                        	<input id="begin6" name="begin6" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end6\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="begin6" name="begin6" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
                        	<input id="end6" name="end6" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin6\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="end6" name="end6" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
					    </tr>
					    
					     
					    
					    <tr><td>&nbsp;</td></tr>
					    
					    
					    
					    <tr>
                         <td align="right">7月：</td>
					     <td class="fsBlack">
					     	<input id="begin7" name="begin7" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end7\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="begin7" name="begin7" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
                        	<input id="end7" name="end7" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin7\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="end7" name="end7" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                         <td align="right">8月：</td>
                        <td  class="fsBlack">
                        	<input id="begin8" name="begin8" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end8\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="begin8" name="begin8" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
                        	<input id="end8" name="end8" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin8\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="end8" name="end8" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
					    </tr>
					    
					     
					    
					    <tr><td>&nbsp;</td></tr>
					    
					    
					    
					    <tr>
                         <td align="right">9月：</td>
					     <td class="fsBlack">
					     	<input id="begin9" name="begin9" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end9\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="begin9" name="begin9" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
                        	<input id="end9" name="end9" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin9\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="end9" name="end9" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                         <td align="right">10月：</td>
                        <td  class="fsBlack">
                        <input id="begin10" name="begin10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end10\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="begin10" name="begin10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
                        	<input id="end10" name="end10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin10\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="end10" name="end10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
					    </tr>
					    
					     
					    
					    <tr><td>&nbsp;</td></tr>
					    
					    
					    
					    <tr>
                         <td align="right">11月：</td>
					     <td class="fsBlack">
					     <input id="begin11" name="begin11" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end11\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="begin11" name="begin11" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
                        	<input id="end11" name="end11" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin11\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="end11" name="end11" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                         <td align="right">12月：</td>
                        <td  class="fsBlack">
                        <input id="begin12" name="begin12" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,maxDate:'#F{$dp.$D(\'end12\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="begin12" name="begin12" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
                        <td align="center">至</td>
                        <td class="fsBlack">
                        	<input id="end12" name="end12" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true,minDate:'#F{$dp.$D(\'begin12\')}' })" class="Wdate" readonly="true" onkeydown="keyDown()" />
<%-- 								<s:textfield id="end12" name="end12" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',autoPickDate:true})" cssClass="Wdate" readonly="true" onkeydown="keyDown()"></s:textfield> --%>
                        </td>
					    </tr>	
					    </table>
					    <table align="center">
					    <tr>					   
							<td>							
								<a href="#" class="buttontwo" onclick="submitForm();">保存</a>
									
							</td>                            
                            <td>							
								<a href="#" class="buttontwo" onclick="goBack();" >取消</a>	
                            </td>
						</tr>		
	   </table>
		</s:form>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
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

function showSameMonth(){
	var checkYear=jQuery("#checkYear").val();
	var thisMonth=jQuery("#thisMonth").val();
	var nextMonth=jQuery("#nextMonth").val();
	if(thisMonth<=nextMonth){
		alert("次月日期设置需小于当月日期，否则油耗统计时会有重复。");
		return false;
	}
	
	jQuery("#begin1").val(checkYear+"-01-"+thisMonth);
	jQuery("#end1").val(checkYear+"-02-"+nextMonth);
	
	jQuery("#begin2").val(checkYear+"-02-"+thisMonth);
	jQuery("#end2").val(checkYear+"-03-"+nextMonth);
	
	jQuery("#begin3").val(checkYear+"-03-"+thisMonth);
	jQuery("#end3").val(checkYear+"-04-"+nextMonth);
	
	jQuery("#begin4").val(checkYear+"-04-"+thisMonth);
	jQuery("#end4").val(checkYear+"-05-"+nextMonth);
	
	jQuery("#begin5").val(checkYear+"-05-"+thisMonth);
	jQuery("#end5").val(checkYear+"-06-"+nextMonth);
	
	jQuery("#begin6").val(checkYear+"-06-"+thisMonth);
	jQuery("#end6").val(checkYear+"-07-"+nextMonth);
	
	jQuery("#begin7").val(checkYear+"-07-"+thisMonth);
	jQuery("#end7").val(checkYear+"-08-"+nextMonth);
	
	jQuery("#begin8").val(checkYear+"-08-"+thisMonth);
	jQuery("#end8").val(checkYear+"-09-"+nextMonth);
	
	jQuery("#begin9").val(checkYear+"-09-"+thisMonth);
	jQuery("#end9").val(checkYear+"-10-"+nextMonth);
	
	jQuery("#begin10").val(checkYear+"-10-"+thisMonth);
	jQuery("#end10").val(checkYear+"-11-"+nextMonth);
	
	jQuery("#begin11").val(checkYear+"-11-"+thisMonth);
	jQuery("#end11").val(checkYear+"-12-"+nextMonth);
	
	jQuery("#begin12").val(checkYear+"-12-"+thisMonth);
	jQuery("#end12").val(parseInt(checkYear)+1+"-01-"+nextMonth);
}


function showNatureMonth(){
	var checkYear=jQuery("#checkYear").val();
	
	jQuery("#begin1").val(checkYear+"-01-01");
	jQuery("#end1").val(checkYear+"-01-31");
	
	jQuery("#begin2").val(checkYear+"-02-01");         //判断是否是闰年
	if( ( (checkYear%4==0)   &&   (checkYear%100!=0) ) || (checkYear%400==0)){
		jQuery("#end2").val(checkYear+"-02-29");
	}
	else{
		jQuery("#end2").val(checkYear+"-02-28");
	}
	
	jQuery("#begin3").val(checkYear+"-03-01");
	jQuery("#end3").val(checkYear+"-03-31");
	
	jQuery("#begin4").val(checkYear+"-04-01");
	jQuery("#end4").val(checkYear+"-04-30");
	
	jQuery("#begin5").val(checkYear+"-05-01");
	jQuery("#end5").val(checkYear+"-05-31");
	
	jQuery("#begin6").val(checkYear+"-06-01");
	jQuery("#end6").val(checkYear+"-06-30");
	
	jQuery("#begin7").val(checkYear+"-07-01");
	jQuery("#end7").val(checkYear+"-07-31");
	
	jQuery("#begin8").val(checkYear+"-08-01");
	jQuery("#end8").val(checkYear+"-08-31");
	
	jQuery("#begin9").val(checkYear+"-09-01");
	jQuery("#end9").val(checkYear+"-09-30");
	
	jQuery("#begin10").val(checkYear+"-10-01");
	jQuery("#end10").val(checkYear+"-10-31");
	
	jQuery("#begin11").val(checkYear+"-11-01");
	jQuery("#end11").val(checkYear+"-11-30");
	
	jQuery("#begin12").val(checkYear+"-12-01");
	jQuery("#end12").val(checkYear+"-12-31");
	
	
}

function clickChekbox(){
	  
	  if(jQuery("#selectSame").attr("checked")==true){
		  //jQuery("#monthList").attr({"disabled":"disabled"});
		  jQuery(".Wdate").attr({"disabled":"disabled"});
			 jQuery(".selectButton").removeAttr("disabled");
		  //jQuery("#input[type=text]").attr("disabled","disabled");
		  jQuery("#checkNatureMonth").attr({"disabled":"disabled"});
		  jQuery("#checkValueSame").removeAttr("disabled");
		  
		}else{		
			//jQuery("#monthList").removeAttr("disabled");
			jQuery(".Wdate").removeAttr("disabled");
			jQuery(".selectButton").attr({"disabled":"disabled"});
			//jQuery("#input[type=text]").removeAttr("disabled");
			jQuery("#checkNatureMonth").removeAttr("disabled");
			jQuery("#checkValueSame").attr({"disabled":"disabled"});
		}
}
function keyDown(){
    // 禁止使用backspace键
    if(window.event.keyCode == 8){
        //alert("不能使用backspace键");
        event.returnValue=false;
    }
}


function submitForm(){	
	
	 if(jQuery("#selectSame").attr("checked")==true){
		 if(jQuery("#checkYear").val()==''||jQuery("#thisMonth").val()==""||jQuery("#nextMonth").val()==''){
			 //alert("请检查每一项是否为空");
			 jQuery("#Below_new").html("");
			 return false;
		 }
		  var value = {
				    sameFlag:'0',
				    checkYear:jQuery("#checkYear").val(),
		  			thisMonth:jQuery("#thisMonth").val(),
		  			nextMonth:jQuery("#nextMonth").val()
		  			};
	  }else{
		  if(jQuery("#checkYear").val()==''||jQuery("#begin1").val()==""||jQuery("#end1").val()==''
				  ||jQuery("#begin2").val()==""||jQuery("#end2").val()==''
				  ||jQuery("#begin3").val()==""||jQuery("#end3").val()==''
				  ||jQuery("#begin4").val()==""||jQuery("#end4").val()==''
				  ||jQuery("#begin5").val()==""||jQuery("#end5").val()==''
				  ||jQuery("#begin6").val()==""||jQuery("#end6").val()==''
				  ||jQuery("#begin7").val()==""||jQuery("#end7").val()==''
				  ||jQuery("#begin8").val()==""||jQuery("#end8").val()==''
				  ||jQuery("#begin9").val()==""||jQuery("#end9").val()==''
				  ||jQuery("#begin10").val()==""||jQuery("#end10").val()==''
				  ||jQuery("#begin11").val()==""||jQuery("#end11").val()==''
				  ||jQuery("#begin12").val()==""||jQuery("#end12").val()==''){
				 //alert("请检查每一项是否为空");
				 jQuery("#Below_new").html("考核月度的开始、结束时间都不能为空，请确认！");
				 return false;
			}
		  var value = {	
				    sameFlag:'1',
				    checkYear:jQuery("#checkYear").val(),
				    
		  			begin1:jQuery("#begin1").val(),
		  			end1:jQuery("#end1").val(),
		  			
		  			begin2:jQuery("#begin2").val(),
		  			end2:jQuery("#end2").val(),
		  			
		  			begin3:jQuery("#begin3").val(),
		  			end3:jQuery("#end3").val(),
		  			
		  			begin4:jQuery("#begin4").val(),
		  			end4:jQuery("#end4").val(),
		  			
		  			begin5:jQuery("#begin5").val(),
		  			end5:jQuery("#end5").val(),
		  			
		  			begin6:jQuery("#begin6").val(),
		  			end6:jQuery("#end6").val(),
		  			
		  			begin7:jQuery("#begin7").val(),
		  			end7:jQuery("#end7").val(),
		  			
		  			begin8:jQuery("#begin8").val(),
		  			end8:jQuery("#end8").val(),
		  			
		  			begin9:jQuery("#begin9").val(),
		  			end9:jQuery("#end9").val(),
		  			
		  			begin10:jQuery("#begin10").val(),
		  			end10:jQuery("#end10").val(),
		  			
		  			begin11:jQuery("#begin11").val(),
		  			end11:jQuery("#end11").val(),
		  			
		  			begin12:jQuery("#begin12").val(),
		  			end12:jQuery("#end12").val()
		  			
		  			};
	             }
	
	
	
	jQuery.post('checkOilSet/setChcekMonth.shtml',value,function(){
		//goBack('checkOilSetInit.shtml');
		var mess="vehcileinfo.savemonthsuccess.message";
		var url="<s:url value='/checkOilSet/checkOilSetInit.shtml' />?message=" + mess;
		window.parent.document.forms[0].action=url;
		window.parent.document.forms[0].submit();
		art.dialog.close();
	});
	
}
function goBack(url) {
	Wit.goBack(url);
}
function goBack(){
	art.dialog.close();
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

function reWrite(tdDiv,pid){	
	var number=pid.split("-");
   return	'<input type="text" id='+number[1]+'  value='+tdDiv+'>';
	
}

function initTextMonth(data){
	var arrs = $("input[type=text]");
	var len = data.length;
	var j = 0;
	for(var i = 0; i < len; i++){
		jQuery(arrs[j]).val(data[i].startTime);j = j+1;
		jQuery(arrs[j]).val(data[i].endTime);j = j+1;
	}
	for(var m=len;m<12;m++){
		jQuery(arrs[j]).val("");j = j+1;
		jQuery(arrs[j]).val("");j = j+1;
	}
}

function searchMonth(){
	var value = {checkYear: jQuery("#checkYear").val()+"-1-1"};
	jQuery.post('../checkOilSetlist/selectCheckMonth.shtml',value,function(data){
		if(data.length > 0){
			initTextMonth(data);
		}
		else{
			initTextMonth(data);
			//return false;
		}
		
	});
}
jQuery(function() {
	searchMonth();
});
</script>
</body>
</html>


