<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="width:450px; min-width:450px;">
<s:hidden id="subVin" name="work.vehicle_vin"></s:hidden>
<s:hidden id="subStart_time" name="work.start_time"></s:hidden>
<s:hidden id="subEnd_time" name="work.end_time"></s:hidden>
<s:hidden id="subFlag" name="work.route_class"></s:hidden>

<table  width="100%" border="0" cellspacing="0" cellpadding="0">
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
								线路类型：
								<select id="route_class"
										name="work.route_class" onchange="searchList()">
											<option value="">全部</option>
											<option value="0">早班</option>
											<option value="1">晚班</option>
									</select>
									&nbsp;&nbsp;
								</td>
								<td>
								行驶线路：
<%-- 								<s:textfield id="route_name"  name="work.route_name" cssClass="input120"  onkeypress="if(event.keyCode==13){searchList();}" />&nbsp;&nbsp; --%>
								<input maxlength="120" id="route_name"  name="work.route_name" cssClass="input120"  onkeypress="if(event.keyCode==13){searchList();}" />&nbsp;&nbsp;
								</td>
								<td>
								时间：
								<input readonly="readonly"
									id="beginTime" name="work.start_time" value="${work.start_time}"
									class="Wdate" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
											autoPickDate:true,
											isShowToday:false,
											maxDate:'#F{$dp.$D(\'endTime\')}',
											isShowClear:false})" />
									至 <input readonly="readonly" id="endTime" name="work.end_time" 
									value="${work.end_time}" class="Wdate" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
											autoPickDate:true,
											isShowToday:false,
											minDate:'#F{$dp.$D(\'beginTime\')}',
											maxDate:'#F{$dp.$D(\'yesterday\')}',
											isShowClear:false})" />
									<s:hidden id="yesterday" name="work.pre_date"/>
								</td>
								<td><a href="#" onclick="searchList()" class="sbutton">查询</a></td>
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
                                      <td width="30%" class="titleStyleZi">通勤详情信息</td>
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
					        <!--  div id="noData" style='text-align:center;display:none'>
									查询不到内容
						        </div-->
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
<script type="text/javascript" src="<s:url value='/scripts/lib/dialog/dialog.min.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script type="text/javascript">
//查询
function searchList() {
	jQuery('input').each(function(){
		var value=jQuery.trim(jQuery(this).val());
		jQuery(this).val(value);
	});
	jQuery('#gala').flexOptions({
		newp: 1,
		params : [ {
			name : 'work.vehicle_vin',
			value : jQuery('#subVin').val()
		}, {
			name : 'work.start_time',
			value : jQuery('#beginTime').val()
		}, {
			name : 'work.end_time',
			value : jQuery('#endTime').val()
		}, {
			name : 'work.route_class',
			value : jQuery('#route_class').val()
		}, {
			name : 'work.route_name',
			value : jQuery('#route_name').val()
		}]
	});
	jQuery('#gala').flexReload();
}
//初始化
jQuery(function() {
	 var gala = jQuery('#gala');
	 var url= "../runStatisticList/workDetailListJson.shtml";
	 gala.flexigrid({
	  url: '<s:url value="'+url+'" />', 
	  dataType: 'json',    //json格式
	  params : [ {
			name : 'work.vehicle_vin',
			value : jQuery('#subVin').val()
		}, {
			name : 'work.start_time',
			value : jQuery('#subStart_time').val()
		}, {
			name : 'work.end_time',
			value : jQuery('#subEnd_time').val()
		}, {
			name : 'work.route_class',
			value : jQuery('#subFlag').val()
		}],
	  height: 271,
	  width:870,
	  colModel : 
		  [ {display: '序号', name : 'rowNumber', width : calcColumn(0.02,40,0), align: 'center'},
	        {display: '行驶线路', name : 'route_name', width : calcColumn(0.13,180,0), sortable : true, align: 'center'},
	        {display: '线路类型', name : 'route_class', width : calcColumn(0.08,60,0), sortable : true, align: 'center',process : reWriteClass},
	        {display: '乘车人数', name : 'load_number', width : calcColumn(0.08,60,0), sortable : true, align: 'center'},
	        {display: '核载人数', name : 'limite_number', width : calcColumn(0.08,60,0), sortable : true, align: 'center'},
	        {display: '离开起点时间', name : 'start_time', width : calcColumn(0.2,120,0), sortable : true, align: 'center', escape:true},
	        {display: '到达终点时间', name : 'end_time', width : calcColumn(0.2,120,0), sortable : true, align: 'center', escape:true},
	        {display: '总里程', name : 'total_mileage', width : calcColumn(0.08,55,0), sortable : true, align: 'center'},
	        {display: '驾驶员', name : 'driver_name', width : calcColumn(0.08,60,0), sortable : true, align: 'center'}
	        ],
	       sortname: 'start_time',
	       sortorder: 'desc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :true,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: true,    // 是否可以动态设置每页显示的结果数
	       rp: 20,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true,   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错             
		   onSuccess:function(){
				if(this.total == 0) {
						jQuery("#noData").show();
			  	   } else {
			  		jQuery("#noData").hide();
			  	   }
			       return true;
		       }
	     });
	});
	function reWriteClass(tdDiv,pid,row){
		var sname="早班";
		if(tdDiv=='1'){
			sname="晚班";
		}
		return sname;
	}
	jQuery('#route_class').val(jQuery('#subFlag').val());
</script>
</body>
</html>
