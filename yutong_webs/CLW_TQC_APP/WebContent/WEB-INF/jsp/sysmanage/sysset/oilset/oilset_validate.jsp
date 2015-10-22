<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/OilSet.js'></script>
<script type="text/javascript">
function checkMonth(){

	var _month = $('check_time_code');

	if(_month.value==""){

		Wit.removeAllSpan(_month);
		var spanElement = new Element('span', {
			'class' : 'error'
		});
		spanElement.setHTML('&nbsp;' + "<s:property value="getText('please.select.month')" />");
		spanElement.injectAfter(_month);
		
	//Wit.showErr(_month,"<s:property value="getText('please.select.month')" />");
	return false;
	}

	Wit.removeAllSpan(_month);
	var spanElement = new Element('span', {
		'class' : 'success'
	});
	spanElement.setText(' ' + Mat.succMsg);
	spanElement.injectAfter(_month);
	
	//Wit.showSucc(_month,Mat.succMsg);
	return true;
	
}

function checkCarBrand(){

	var _carBrand = $('oilset.carBrand');
	
	if(_carBrand.value==""){
		
		Wit.showErr(_carBrand,"<s:property value="getText('please.select.brand')" />");
		return false;
	}

	Wit.showSucc(_carBrand,Mat.succMsg);
	return true;
}

function checkCarType(){
	var _carType = $('oilset.carType');

	

	if(_carType.value==""||_carType.value=="请选择"){
		Wit.showErr(_carType,"<s:property value="getText('please.select.Type')" />");
		return false;
	}

	Wit.showSucc(_carType,Mat.succMsg);
	return true;
}



   //check month unique
   function checkMonthUnique(){

	   //check month time
	   var _month = $('check_time_code');

	   if(_month.value==""){
		  Wit.showErr(_month,"<s:property value="getText('msg.check.required')" />");
		  return false;
	   }else{
	   //car vin code
	   //var _carLn = $('vehicle_ln');
	   var _carVin  = $('oilset.vehicle_vin');
	   //check id
	   var _checkId = $('oilset.check_id');

	   var res = getCheckMonthUniqueRes(_month.value,_carVin.value,_checkId.value);

	   if(!res){
			Wit.showErr(_month,"<s:property value="getText('oil.month.unique')" />");
			return false;
		}else{
			return true;
		}
	   }
	   
   }


   function getCheckMonthUniqueRes(_month,_carLn,_checkId){

   	var _monthSigle = false; 
      //Set to synchronize 
      DWREngine.setAsync(false);
          
      //call UserDwr
      OilSet.checkMonthUnique(_month,_carLn,_checkId,callBackFun);
          
      //Settings for asynchronous 
      DWREngine.setAsync(true);
      
      //Callback function 
      function callBackFun(data)
      {
    	  _monthSigle = data;
      }
      return _monthSigle;
   }

   
	
	function checkValue() {
		var check_value = $('check_value');
		var _format = check_value.value.match(/^(0|[1-9]|[1-9]\d+)$|^([0-9]+\.\d+)$/g);
		if (!Mat.checkRequired(check_value))
			return false;
		else if (!Mat
				.checkLength(
						check_value,
						10,
						'<s:text name="oilset.check_value"/>' + '<s:text name="val.feild.overlength"/>' + '10'))
			return false;
		else if(_format==null){
			Wit.showErr(check_value,"<s:property value="getText('set.oil.format')" />");
			return false;
			}
		
		return true;
	}
	
	

	/** 初始化样式 **/
	function setFormMessage() {
		
		Mat.setDefault($('check_value'),'<span class="noticeMsg">*</span>');
		
	}

	/** 加载事件 **/
	function setFormEvent() {
		
		$('check_value').onkeyup = checkValue;

		$('check_time_code').onkeyup = checkMonth;

	//	$('oilset.carBrand').onkeyup = checkCarBrand;

	//	$('oilset.carType').onkeyup = checkCarBrand;
		

	}

	function submitForm() {

		var f2 = checkMonth();
		var f3 = checkCarBrand();
		var f4 = checkCarType();
		var f1=  checkValue();
		if (f1&&f2&&f3&&f4) {
			
			Wit.commitAll($('add_oilsetform'));
			
		} else {
			return false;
		}
	}

	function submitalterForm(){

		//var f2 = checkMonth();
		var f1=  checkValue();
		var f3 = checkMonthUnique();
		if (f1&&f3) {
			var form = document.getElementById('alter_oilsetform');
			Wit.commitAll(form);
		} else {
			return false;
		}
		
	
	}

	function submitDeleteForm(){

		var f2 = checkMonth();
		var f3 = checkCarBrand();
		var f4 = checkCarType();
	
		if (f2&&f3&&f4) {
			Wit.delConfirm('${delete}', '<s:text name="common.delete.confirm" />')
		} else {
			return false;
		}
		
	
	}

	/** 车主类型用户不需要关联企业 **/
	function changeUserType() {

	}

	function goBack(url) {
		Wit.goBack(url);
	}

	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);



	//***********************add by suyingtao-multselect begin*****************************************



	function moveSelect(sl1, sl2, type) {
	     if( sl1 == undefined || sl1 == null) return;
	     if( sl2 == undefined || sl2 == null) return;
	     for (var i = 0; i < sl1.length; ++i){
	        if (sl1.options[i].selected) {
	        
	        if (findOption(sl1.options[i].value, sl2))
	        { 
	           sl1.options[i] = null;
	           --i;
	           continue;
	        }
	       var pp=document.getElementById(type+'type');
	         moveOption(sl1, sl2, i, false);
	         moveOption(sl1, pp, i, true);              
	          --i;                
	         }               
	       }
	}



	function deleteSelect(sl1, sl2, type) {
	if( sl1 == undefined || sl1 == null) return;
	if( sl2 == undefined || sl2 == null) return;
	for (var i = 0; i < sl2.length; ++i)
	    if (sl2.options[i].selected) {
	    
	        if (findOption(sl2.options[i].value, sl1)) {
	            sl2.options[i] = null;
	            --i;
	            continue;
	        }
	        
	        moveOption(sl2, sl1, i, true);
	        document.getElementById(type+'type').options[i]=null;
	        --i;
	   }     
	}

	function findOption(op, sl) {
		   if( sl == undefined || sl == null) return false;
	    for (var i = 0; i < sl.length; ++i)
	        if (sl.options[i].value == op) return true;
	        
	    return false;
	}

	function moveOption(sl1, sl2, i, flagDel) {
	if( sl1 == undefined || sl1 == null) return;
	if( sl2 == undefined || sl2 == null) return;
	if (i < sl1.length) {
	    var newOption = new Option(sl1.options[i].text, sl1.options[i].value);
	    newOption.selected=true;
	    sl2.options.add(newOption);
			newOption.title=sl1.options[i].title;
	    if (flagDel) sl1.options[i] = null;
	}
	} 

	function res(type){
		var selectLeft= document.getElementById('selectLeft'+type);		
		var selectRight= document.getElementById('selectRight'+type);		
		var type= document.getElementById(type+'type');	
		
		for (var i = 0; i < selectRight.length; ++i){
		
		        if (findOption(selectRight.options[i].value, selectLeft)) {
		             selectRight.options[i] = null;
		             --i;
		             continue;
		         }
		         
		         moveOption(selectRight, selectLeft, i, true);
		         type.options[i]=null;
		         --i;		            		           		   
		}
	}

	function checkOS() {	 	
		var osSelect = document.getElementById('ostype');
		if(osSelect.length <= 0){
		    Wit.showErr($('ostag'), "<s:property value="getText('msg.check.required')" />");
			return false;
		}
		Wit.showSucc($('ostag'),Mat.succMsg);
		return true; 		 
	}
	//************************add by suyingtao end******************************************
	
	//************************add by yugang start*******************************************
	
	function moveSelectAll(sl1, sl2, type) {
	     if( sl1 == undefined || sl1 == null) return;
	     if( sl2 == undefined || sl2 == null) return;
	     for (var i = 0; i < sl1.length; ++i){
	        
	        if (findOption(sl1.options[i].value, sl2))
	        { 
	           sl1.options[i] = null;
	           --i;
	           continue;
	        }
	       var pp=document.getElementById(type+'type');
	       moveOptionAll(sl1, sl2, i, false);
	       moveOptionAll(sl1, pp, i, true);              
	          --i;                
	     }
	}

	function moveOptionAll(sl1, sl2, i, flagDel) {
		if( sl1 == undefined || sl1 == null) return;
		if( sl2 == undefined || sl2 == null) return;
		if (i < sl1.length) {
		    var newOption = new Option(sl1.options[i].text, sl1.options[i].value);
		    newOption.selected=true;
		    sl2.options.add(newOption);
				newOption.title=sl1.options[i].title;
		    if (flagDel) sl1.options[i] = null;
		}
		} 

	function deleteSelectAll(sl1, sl2, type) {
		if( sl1 == undefined || sl1 == null) return;
		if( sl2 == undefined || sl2 == null) return;
		for (var i = 0; i < sl2.length; ++i){
			if (findOption(sl2.options[i].value, sl1)) {
	            sl2.options[i] = null;
	            --i;
	            continue;
	        }
	        
			moveOptionAll(sl2, sl1, i, true);
	        document.getElementById(type+'type').options[i]=null;
	        --i;
		}
	    
	        
	}
	
	//************************add by yugang end*******************************************	
</script>