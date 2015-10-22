<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/My97DatePicker/WdatePicker.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/OilSet.js'></script>
<script type="text/javascript">
//type in car brand
function carBrandType(){
  var _userOrgId = $('oilset.organization_id');
  //brand of car apply to search
  var _brand = $('oilset.carBrand');
  
  OilSet.getCarTypeLists(_userOrgId.value,_brand.value, callBackFun);
 
  function callBackFun(data) {
	
    var _carType = $('oilset.carType'); 
    
    DWRUtil.removeAllOptions(_carType); 

    DWRUtil.addOptions(_carType,['请选择']); 
    if(data!=null) {
        	DWRUtil.addOptions(_carType,data);

    }else{
    alert("获取车型信息出错！请联系系统管理员");
    }
      
  }
}

function firstrisize(){
	jQuery('#content').mk_autoresize({
        height_include: '#main',
        height_offset: 0 
      });
}
jQuery(function() {
	firstrisize();
	jQuery(window).mk_autoresize({
	     height_include: '#content',
	     height_exclude: ['#header', '#footer'],
	     height_offset: 0,
	     width_include: ['#header', '#content', '#footer'],
	     width_offset: 0
	  });
	 firstrisize();
});
</script>