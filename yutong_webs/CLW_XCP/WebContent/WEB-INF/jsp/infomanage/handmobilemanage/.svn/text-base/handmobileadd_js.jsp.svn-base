<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>

<script>

	/** 取消填加操作 **/
	function goBack(url) {
	    Wit.goBack(url);
	}
	
		
	function goToBack(){
		var url = "/vehicle/vehiclemanage.shtml?page=";
		var page = ${page};
		var pageSize = ${pageSize};
		url+=page+"&pageSize="+pageSize;
		alert(url);
		Wit.goBack(url);
	}
	
	function checkOrgid(){
		var orgid = document.getElementById('organization_id');
 		if(orgid.value==""){
		    Wit.showErr($('orgidtag'), "<s:property value="getText('msg.check.required')" />");
			return false;
		}
 		Wit.showSucc($('orgidtag'),Mat.succMsg);
		return true; 		 
	}

	function addForm() {
		trimAllObjs();
		var f0=checkOS();
		var f1=checkOrgid();
		if(f0&&f1)
		{
			//var form = document.getElementById('add_form');
			Wit.commitAll($('add_form'));
			//form.submit();
		}else{
			return false;
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
	
	function setFormEvent() {
		//$('hdmf').onkeyup = checkHdmf;
		//$('hdmf').onblur = checkHdmf;
		//$('hdnm').onkeyup = checkHdnm;
		//$('hdnm').onblur = checkHdnm;
		//$('addbutton').onclick = addForm;	
	}
	
	//window.addEvent('domready', setFormMessage);
	//window.addEvent('domready', setFormEvent);
	


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
//-->
	//页面自适应
	(function (jQuery) {
	 jQuery(window).resize(function(){
	 
	  testWidthHeight();
	  
	 });
	 jQuery(window).load(function (){
	  
	  testWidthHeight();
	  jQuery(window).resize();
	 });
	 
	})(jQuery);
	
	//获取页面高度
	function get_page_height() {
	 var height = 0;
	 
	 if (jQuery.browser.msie) {
	     height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
	 } else {
	     height = self.innerHeight;
	 }
	 return height;
	}
	//获取页面宽度
	function get_page_width() {
	 var width = 0;
	 if(jQuery.browser.msie){ 
	  width = document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
	 } else { 
	  width = self.innerWidth; 
	 } 
	 return width;
	}
	 
	//计算控件宽高
	function testWidthHeight(){
	 
		var h = get_page_height();
		var test=document.getElementById("handmobiletable");
		if(h>165){
			test.style.height = h-165;
		}else{
		}
	}

	(function($){
		// 预缓存皮肤，数组第一个为默认皮肤
		$.dialog.defaults.skin = ['aero'];
	})(art);

	function clickEnterEvent(obj) {
		    var orgid=document.getElementById('organization_id');
		    orgid.value=obj.id;
		}

	function openorganizidtree(){
		art.dialog.open("<s:url value='/handmobile/handmobiletreeAction.shtml' />?rad="+Math.random(),
				//userupdatetreeAction.shtml?userID=" + userid,
				{
				width :260,
				height:300,
				id: 'treeOID',
				title: ' ',
				skin: 'aero',
				limit: true,
				lock: true,
				drag: true,
				fixed: false,
					//follow: document.getElementById('organizationName'),
					yesFn: function(iframeWin, topWin){
			        	//对话框iframeWin中对象
			        	var orgNameValue = iframeWin.document.getElementById('organizationName').value;
			        	var orgIDValue = iframeWin.document.getElementById('organizationID').value;
			        	//当前页面中对象
			            var topOrgName =  window.document.getElementById('organizationName');
			            var topOrgID = window.document.getElementById('organization_id');
			            //赋值
			        	if (topOrgName) topOrgName.value = orgNameValue;
			        	if (topOrgID) topOrgID.value = orgIDValue;
			    	}
			  
				});

	}
</script>

