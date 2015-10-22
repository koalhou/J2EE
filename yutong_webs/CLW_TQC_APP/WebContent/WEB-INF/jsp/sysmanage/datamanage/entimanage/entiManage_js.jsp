<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
//隐藏树
function treeView(){
	var barDisplay = jQuery("#showBar").css("display");
	if(barDisplay == "none"){
		jQuery("#showTree").css("display","none");
		jQuery("#showBar").css("display","");
	}else{
		jQuery("#showTree").css("display","");
		jQuery("#showBar").css("display","none");
	}
} 
function isIE(){ //ie? 
	   if (window.navigator.userAgent.toLowerCase().indexOf("msie")>=1) 
	    return true; 
	   else 
	    return false; 
	} 
/*
 * 新增一个公共参数，用以确定当前车企业被分配辆个数
 */
 	var carsnumber=0;

 	var firstflag=true;
//显示具体企业信息
function clickEnterEvent(obj) {
	var elm=document.getElementById('ChooseEnterID_edit_show');
	elm.innerHTML="";
	if(true==firstflag){
		firstflag=false;	
	}else{
		 document.getElementById('message').style.display='none';
	}
	EntiDwr.getEnterpriseDataInfoToShow(obj.id ,{
		callback : function(data) {
		   if(isIE()){
			   document.getElementById('FULL_NAME').innerText=data.full_name;
				document.getElementById('SHORT_NAME').innerText=data.short_name;
				document.getElementById('ENTERPRISE_COUNTRY').innerText=data.enterprise_country;
				document.getElementById('ENTERPRISE_PROVINCE').innerText=data.enterprise_province;
				document.getElementById('ENTERPRISE_CITY').innerText=data.enterprise_city;
				if('0'==data.enterprise_type){
					//document.getElementById('ENTERPRISE_TYPE').innerText='<s:property value="getText('enterprise.type0')" />';
				}else{
					//document.getElementById('ENTERPRISE_TYPE').innerText='<s:property value="getText('enterprise.type1')" />';
				}
				document.getElementById('ADDRESS').innerText=data.address;
				document.getElementById('EMAIL').innerText=data.email;
				document.getElementById('POSTCODE').innerText=data.postcode;
				document.getElementById('CONTACT_P').innerText=data.contact_p;
				document.getElementById('CONTACT_TEL').innerText=data.contact_tel;
				//document.getElementById('MSG_NUM').innerText=data.msg_num;
				document.getElementById('ENTERPRISE_DESC').innerText=data.enterprise_desc;
		   }else{
			    //document.getElementById('ENTERPRISE_CODE').textContent=data.enterprise_code;
				document.getElementById('FULL_NAME').textContent=data.full_name;
				document.getElementById('SHORT_NAME').textContent=data.short_name;
				document.getElementById('ENTERPRISE_COUNTRY').textContent=data.enterprise_country;
				document.getElementById('ENTERPRISE_PROVINCE').textContent=data.enterprise_province;
				document.getElementById('ENTERPRISE_CITY').textContent=data.enterprise_city;
				if('0'==data.enterprise_type){
					//document.getElementById('ENTERPRISE_TYPE').textContent='<s:property value="getText('enterprise.type0')" />';
				}else{
					//document.getElementById('ENTERPRISE_TYPE').textContent='<s:property value="getText('enterprise.type1')" />';
				}
				document.getElementById('ADDRESS').textContent=data.address;
				document.getElementById('EMAIL').textContent=data.email;
				document.getElementById('POSTCODE').textContent=data.postcode;
				document.getElementById('CONTACT_P').textContent=data.contact_p;
				document.getElementById('CONTACT_TEL').textContent=data.contact_tel;
				//document.getElementById('MSG_NUM').textContent=data.msg_num;
				document.getElementById('ENTERPRISE_DESC').textContent=data.enterprise_desc;
		   }				
		},
		errorHandler : function(msg, ex) {
			alert(msg);
		}
		});
	//if(Number(obj.attributes.getNamedItem('left_num').value)+1==Number(obj.attributes.getNamedItem('right_num').value)){
		EntiDwr.getEnterpriseCarsNumsToShow(obj.id ,{
			callback : function(data) {
			if(isIE()){
				document.getElementById('CARSNUMS').innerText=data;
				carsnumber=Number(data);
			}else{
				document.getElementById('CARSNUMS').textContent=data;
				carsnumber=Number(data);
				}
			},
			errorHandler : function(msg, ex) {
				alert(msg);
			}
			});
/*
		}else{
		if(isIE()){
			document.getElementById('CARSNUMS').innerText="0";
			carsnumber=0;
		}else{
			document.getElementById('CARSNUMS').textContent="0";
			carsnumber=0;
		}
	}*/
	/**
	$.ajax( {
				async : false,
				type : "POST",
				url : "qx/ajax_entiinfo.shtml?enterId="+obj.id,
				data : "",
				success : function(msg) {
					if (msg != ""&&msg.length>4) {
						if(msg.substr(0,4)=='msg:'){
							var msgs=msg.substr(4,msg.length).split('|');
							document.getElementById('ENTERPRISE_CODE').innerText=msgs[0];
							document.getElementById('FULL_NAME').innerText=msgs[1];
							document.getElementById('SHORT_NAME').innerText=msgs[2];
							document.getElementById('ENTERPRISE_COUNTRY').innerText=msgs[3];
							document.getElementById('ENTERPRISE_PROVINCE').innerText=msgs[4];
							document.getElementById('ENTERPRISE_CITY').innerText=msgs[5];
							document.getElementById('ENTERPRISE_TYPE').innerText=msgs[6];
							document.getElementById('ADDRESS').innerText=msgs[7];
							document.getElementById('EMAIL').innerText=msgs[8];
							document.getElementById('POSTCODE').innerText=msgs[9];
							document.getElementById('CONTACT_P').innerText=msgs[10];
							document.getElementById('CONTACT_TEL').innerText=msgs[11];
							document.getElementById('MSG_NUM').innerText=msgs[12];
							document.getElementById('ENTERPRISE_DESC').innerText=msgs[13];
						}
					}

				}
			});
	**/
}
//跳转到新增页面
function goto_add(){
	var elm=document.getElementById('ChooseEnterID_edit_show');
	var ent =document.getElementById(onfocusEnterID);
	if(isIE()){
		if(onfocusEnterID==null){
			elm.innerText="<s:property value="getText('enterprise.js.info.needInsertID')"/>";
			return;
		}
		if(onfocusEnterID=='222'){
			elm.innerText="<s:property value="getText('enterprise.js.info.222NotInsert')"/>";
			return;
		}
		if(carsnumber!=0){
			elm.innerText="<s:property value="getText('enterprise.js.info.HaveCars2')"/>";
			return;
		}
		
	}else{
		if(onfocusEnterID==null){
			elm.textContent="<s:property value="getText('enterprise.js.info.needInsertID')"/>";
			return;
		}
		if(onfocusEnterID=='222'){
			elm.textContent="<s:property value="getText('enterprise.js.info.222NotInsert')"/>";
			return;
		}
		if(carsnumber!=0){
			elm.textContent="<s:property value="getText('enterprise.js.info.HaveCars2')"/>";
			return;
		}
	}
	//树结构形状传递
	setEnterTreeMode();
	document.getElementById('ChooseEnterID_edit').value=onfocusEnterID;
	document.getElementById('clwForm').action='<s:url value='entimanage_goto_add.shtml' />';
	document.getElementById('clwForm').submit();
}
function goto_edit(){
	var elm=document.getElementById('ChooseEnterID_edit_show');
	var userenid=document.getElementById('userenid').value;
	if(isIE()){
		if(onfocusEnterID==null){
			elm.innerHTML="<s:property value="getText('enterprise.js.info.needUpdateID')"/>";
			return;
		}
		/*
		if(onfocusEnterID==userenid){
			elm.innerHTML="<s:property value="getText('enterprise.js.info.NotUpdate')"/>";
			return;
		}*/
		if(onfocusEnterID=='222'){
			elm.innerHTML="宇通杯企业不可修改";
			return;
		}
	}else{
		if(onfocusEnterID==null){
			elm.textContent="<s:property value="getText('enterprise.js.info.needUpdateID')"/>";
			return;
		}
		if(onfocusEnterID==userenid){
			elm.textContent="<s:property value="getText('enterprise.js.info.NotUpdate')"/>";
			return;
		}
		if(onfocusEnterID=='222'){
			elm.textContent="宇通杯企业不可修改";
			return;
		}
	}
	document.getElementById('ChooseEnterID_edit').value=onfocusEnterID;
	elm.innerHTML="";
	//树结构形状传递
	setEnterTreeMode();
	document.getElementById('clwForm').action='<s:url value='entimanage_goto_edit.shtml' />';
	document.getElementById('clwForm').submit();
}
function do_del(){

	var elm=document.getElementById('ChooseEnterID_edit_show');
	var userenid=document.getElementById('userenid').value;
	if(isIE()){
		if(onfocusEnterID==null){
			elm.innerHTML="<s:property value="getText('enterprise.js.info.needDelID')"/>";
			return;
		}
		if(onfocusEnterID==userenid){
			elm.innerHTML="<s:property value="getText('enterprise.js.info.NotDel')"/>";
			return;
		}
		if(onfocusEnterID=='222'){
			elm.innerHTML="宇通杯企业不可删除";
			return;
		}
		var ent =document.getElementById(onfocusEnterID);
		if(Number(ent.attributes.getNamedItem('left_num').value)+1!=Number(ent.attributes.getNamedItem('right_num').value)){
			elm.innerHTML="<s:property value="getText('enterprise.js.info.OnlyDelChildNode')"/>";
			return;
		}
		/*
		if(ent.attributes.getNamedItem('concate_cr_flag').value=='1'){
			elm.innerHTML="<s:property value="getText('enterprise.js.info.HaveCars')"/>";
			return;
		}*/
		if(!confirm('<s:property value="getText('enterprise.js.info.sureDel')"/>'+ent.attributes.getNamedItem('short_name').value+'?')){
			return;
		}
	}else{
		if(onfocusEnterID==null){
			elm.textContent="<s:property value="getText('enterprise.js.info.needDelID')"/>";
			return;
		}
		if(onfocusEnterID==userenid){
			elm.textContent="<s:property value="getText('enterprise.js.info.NotDel')"/>";
			return;
		}
		if(onfocusEnterID=='222'){
			elm.textContent="宇通杯企业不可删除";
			return;
		}
		var ent =document.getElementById(onfocusEnterID);
		if(Number(ent.attributes.getNamedItem('left_num').value)+1!=Number(ent.attributes.getNamedItem('right_num').value)){
			elm.textContent="<s:property value="getText('enterprise.js.info.OnlyDelChildNode')"/>";
			return;
		}
		if(ent.attributes.getNamedItem('concate_cr_flag').value=='1'){
			elm.textContent="<s:property value="getText('enterprise.js.info.HaveCars')"/>";
			return;
		}
		if(!confirm('<s:property value="getText('enterprise.js.info.sureDel')"/>'+ent.attributes.getNamedItem('short_name').value+'?')){
			return;
		}
	}
	elm.innerHTML="";
	document.getElementById('ChooseEnterID_edit').value=onfocusEnterID;
	//树结构形状传递
	setEnterTreeMode();
	//确认窗口
	document.getElementById('clwForm').action='<s:url value='entimanage_do_del.shtml' />';
	document.getElementById('clwForm').submit();
	
}

//页面自适应
(function (jQuery) {
 jQuery(window).resize(function(){
 
  testWidthHeight();
  
 });
 jQuery(window).load(function (){
  
  testWidthHeight();
  
 });
 
})(jQuery);
//获取页面高度
function get_page_height() {
	/*
 var height = 0;
 
 if (jQuery.browser.msie) {
     height = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
 } else {
     height = self.innerHeight;
 }
 return height;*/
 var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
 return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
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
	jQuery("#treezuo").height(h-160);
	jQuery(window).mk_autoresize({
        height_include: '#content',
        height_exclude: ['#header', '#footer'],
        height_offset: 0,
        width_include: ['#header', '#content', '#footer'],
        width_offset: 0
     });
    h = get_page_height();
	jQuery("#treezuo").height(h-160);
	jQuery("#treehide").height(h-130);
	//alert(h);
	//var test=document.getElementById("contenttable");
	//var treetest=document.getElementById("treezuo");
	//if(h>165){
		//test.style.height = h-120;
		//treetest.style.height=h-160;
	//}else{
	//}
	
}

</script>