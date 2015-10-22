<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/md5/base64.js' />"></script>	
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/OilSet.js'></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type="text/javascript">
function carBrandType(){
  var _userenId = $('oilset.enterprise_id');
  var _brand = $('scarBrand');

  OilSet.getCarTypeLists(_userenId.value,_brand.value, callBackFun);

  function callBackFun(data) {
	
    var _carType = $('scarType');  
    DWRUtil.removeAllOptions(_carType); 

    DWRUtil.addOptions(_carType,['请选择']);  
    if(data!=null) {
        	DWRUtil.addOptions(_carType,data);

    }else{
    alert("获取车型信息出错！请联系系统管理员");
    }
      
  }
}

function searchList() {	
	    document.getElementById('Below_new').style.display='none';
	
	  	jQuery('#gala').flexOptions({
		newp: 1,
		params: [{name: 'oilset.carBrand', value: jQuery('#scarBrand').val() }, 
		 		{ name: 'oilset.carType', value: jQuery('#scarType').val()},
				{ name: 'oilset.check_time_code', value: jQuery('#check_time_code').val()}
 		]
	});
	jQuery('#gala').flexReload();
	  
  }
  
 function getJSONStr(formId) {
	var a = [];
	// 文本框
	$("#" + formId + " input[type=text]").each(function(i) {
		a.push({
			name : this.name,
			value : this.value
		});
	});
}
/*分页显示辅助函数*/
function getQuery(){
	return getJSONStr('message_form');
}

function reWriteLink(tdDiv,pid,row){
   var  page =jQuery('#gala').flex_current_page();
   var  pageSize =jQuery('#gala').flex_rp();
   
    return '<a href="editOilSetbefore.shtml?oilset.carBrand='+base64encode(utf16to8(row.cell[1]))
    +'&oilset.carType='+base64encode(utf16to8(row.cell[10]))
    +'&oilset.carTypeDesc='+base64encode(utf16to8(row.cell[2]))
    +'&oilset.check_time_code='+row.cell[3]
     +'&oilset.vehicle_vin='+row.cell[0]
    +'&oilset.check_value='+row.cell[4]
    +'&oilset.check_id='+row.cell[11]
    +'&page='+page
    +'&oilset.velTypeId='+row.cell[9]
    +'&pageSize='+pageSize+'">'+ tdDiv +'</a>';
}
//参数传递获取
function get_cell_text(pid, cell_idx) {
	return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
}

jQuery(function() {
	var gala = jQuery('#gala');
	gala.flexigrid({
		url: '<s:url value="/oilsetpkg/oilSetpkg.shtml" />',
		dataType: 'json',
		height: 375,
		width:0,
		colModel : [
                    {display: '车辆ＶＩＮ', name : 'vehicle_vin', width : 120, sortable : true, align: 'center', process: reWriteLink},
                    {display: '<s:text name="车辆品牌" />', name : 'carBrand', width : 100, sortable : true, align: 'center', escape:true},
                    {display: '<s:text name="车型" />', name : 'carTypeDesc', width : 150, sortable : true, align: 'center', escape:true},
                    {display: '<s:text name="考核月度" />', name : 'check_time_code', width : 100, sortable : true, align: 'center'},
                    {display: '<s:text name="考核油耗" />', name : 'to_number(check_value)', width : 100, sortable : true, align: 'center'},
                    {display: '<s:text name="创建人" />', name : 'creater', width : 100, sortable : true, align: 'center',escape:true},
                    {display: '<s:text name="创建时间" />', name : 'create_time', width : 150, sortable : true, align: 'center'},
                    {display: '<s:text name="最后修改人" />', name : 'modifier', width : 100, sortable : true, align: 'center',escape:true},
                    {display: '<s:text name="最后修改时间" />', name : 'modify_time', width : 150, sortable : true, align: 'center'},
                    {display: '', name : 'velTypeId', width : 150, sortable : true, align: 'center',hide:true,toggle:false},
                    {display: '', name : 'carType', width : 150, sortable : true, align: 'center',hide:true,toggle:false},
                    {display: '', name : 'check_id', width : 150, sortable : true, align: 'center',hide:true,toggle:false}
		    		],
		    		    		
		    	sortname: 'check_value',
		    	sortorder: 'asc',
		    	sortable: true,
				striped :true,
				usepager :true, 
				resizable: false,
		    	useRp: true,
		    	rp: 10, 	
				rpOptions : [10,20,50,100],// 可选择设定的每页结果数
		    	showTableToggleBtn: true // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		    	//getQuery :getQuery
	});

	jQuery(window).resize(function(){
	  testWidthHeight();
	});
	jQuery(window).load(function (){
	  testWidthHeight();
	});
}); 

//获取页面高度
function get_page_height() {
 var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
 return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
}
//获取页面宽度
function get_page_width() {
 var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
 return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
}

function firstrisize(){
	 var h = get_page_height();
	 var w = get_page_width();
	 jQuery(".flexigrid").width(w-30);
	 //jQuery("#reportTab").height(h-100);
	 jQuery(".bDiv").height(h-325);
}
 
//计算控件宽高
function testWidthHeight(){
 firstrisize();
 jQuery(window).mk_autoresize({
     height_include: '#content',
     height_exclude: ['#header', '#footer'],
     height_offset: 0,
     width_include: ['#header', '#content', '#footer'],
     width_offset: 0
  });
 firstrisize();
}
</script>