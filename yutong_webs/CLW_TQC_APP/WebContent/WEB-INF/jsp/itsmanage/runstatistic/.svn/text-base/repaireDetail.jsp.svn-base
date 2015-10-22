<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body style="width:450px; min-width:450px;">
<s:hidden id="subVin" name="repaire.vehicle_vin"></s:hidden>
<s:hidden id="subStart_time" name="repaire.start_time"></s:hidden>
<s:hidden id="subEnd_time" name="repaire.end_time"></s:hidden>

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
								时间：
								<input readonly="readonly"
									id="beginTime" name="repaire.start_time" value="${repaire.start_time}"
									class="Wdate" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
											autoPickDate:true,
											isShowToday:false,
											maxDate:'#F{$dp.$D(\'endTime\')}',
											isShowClear:false})" />
									至 <input readonly="readonly" id="endTime" name="repaire.end_time" 
									value="${repaire.end_time}" class="Wdate" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',
											autoPickDate:true,
											isShowToday:false,
											minDate:'#F{$dp.$D(\'beginTime\')}',
											maxDate:'#F{$dp.$D(\'yesterday\')}',
											isShowClear:false})" />
									<s:hidden id="yesterday" name="repaire.pre_date"/>
								</td>
								<td>
								是否正常维修：
								<select id="fix_flag"
										name="fix_flag" onchange="searchList()">
											<option value="">全部</option>
											<option value="1">否</option>
											<option value="0">是</option>
									</select>
									&nbsp;&nbsp;
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
                                      <td width="30%" class="titleStyleZi">维保详情信息</td>
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
					        <!--  div id="noData" style='text-align:center'>
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
			name : 'repaire.vehicle_vin',
			value : jQuery('#subVin').val()
		}, {
			name : 'repaire.start_time',
			value : jQuery('#beginTime').val()
		}, {
			name : 'repaire.end_time',
			value : jQuery('#endTime').val()
		}, {
			name : 'repaire.fix_flag',
			value : jQuery('#fix_flag').val()
		}]
	});
	jQuery('#gala').flexReload();
}
//初始化
jQuery(function() {
	 var gala = jQuery('#gala');
	 var url= "../runStatisticList/repaireDetailListJson.shtml";
	 gala.flexigrid({
	  url: '<s:url value="'+url+'" />', 
	  dataType: 'json',    //json格式
	  params : [ {
			name : 'repaire.vehicle_vin',
			value : jQuery('#subVin').val()
		}, {
			name : 'repaire.start_time',
			value : jQuery('#subStart_time').val()
		}, {
			name : 'repaire.end_time',
			value : jQuery('#subEnd_time').val()
		}],
	  height: 271,
	  width:870,
	  colModel : 
		  [ {display: '序号', name : 'rowNumber', width : calcColumn(0.02,40,0), align: 'center'},
		    {display: '维保日期', name : 'update_time', width : calcColumn(0.2,120,0), sortable : true, align: 'center', escape:true},
	        {display: '故障描述', name : 'fault_desc', width : calcColumn(0.13,178,0), align: 'center'},
	        {display: '维保项目', name : 'fault_type', width : calcColumn(0.08,60,0), sortable : true, align: 'center'},
	        {display: '是否正常维修', name : 'fix_flag', width : calcColumn(0.08,60,0), sortable : true, align: 'center',process : reWriteLink},
	        {display: '责任人', name : 'driver_name', width : calcColumn(0.08,60,0), sortable : true, align: 'center'},
	        {display: '工时费用(元)', name : 'workTimeFee', width : calcColumn(0.08,80,0), sortable : true, align: 'center'},
	        {display: '配件费用(元)', name : 'partFee', width : calcColumn(0.08,80,0), sortable : true, align: 'center'},
	        {display: '合计费用(元)', name : 'totalFee', width : calcColumn(0.08,80,0), sortable : true, align: 'center'}
	        ],
	       sortname: 'update_time',
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
	function reWriteLink(tdDiv,pid,row){
		var sname="";
		if(tdDiv=='0'){
			sname="是";
		}else if(tdDiv=='1'){
			sname="否";
		}else if(tdDiv=='2'){
			sname="--";
		}
		return sname;
	}
</script>
</body>
</html>
