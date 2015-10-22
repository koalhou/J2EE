<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
    var siteList = [];
    var upupcount = 0;
	var updowncount = 0;
	var downupcount = 0;
	var downdowncount = 0; 
    /*
    * 点击页签显示不同列表
    */
    function changeChoose(id){
    	if(id=="tab1"){
    		document.getElementById("tab1").className="current2";
    		document.getElementById("tab2").className="";
    		//此时如果没有点击TAB2页签，那么就不隐藏TAB2页签
    		if(jQuery("#gala3").parent().parent().attr('class').indexOf("flexigrid")>=0){
    			jQuery("#gala3").parent().parent().hide();
    		}		
    		jQuery('#gala2').parent().parent().show();	
    	}
    }   
	(function($){
		// 预缓存皮肤，数组第一个为默认皮肤
		$.dialog.defaults.skin = ['aero'];
	})(art);
	
	jQuery(function() {
	  var gala = jQuery('#gala');
	  gala.flexigrid({
	  url: '<s:url value="/ridingplanpkg/routeList.shtml" />', 
	  dataType: 'json',    //json格式
	  height: 300,
	  width:242,
	  colModel : [ 
			{display: '', name : '', width : 20, process:reWriteRadioBox},
	        {display: '编码',name : 'route_id', width : 40, sortable : false, align: 'center',toggle:false,hide:true}, 
	        {display: '名称', name : 'route_name', width : 100, sortable : false, align: 'center', escape:true},
	        {display: '状态', name : 'route_incharge_person', width : 50, sortable : false, align: 'center',process:setPropertyInnerHtml}	        
	        ],
	       sortname: 'route_id',
	       sortorder: 'asc',  //升序OR降序
	       sortable: true,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :false,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: false,    // 是否可以动态设置每页显示的结果数
	       rp: 10000,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	     });
	  
	  var gala2 = jQuery('#gala2');
	  gala2.flexigrid({
	  url: '<s:url value="/ridingplanpkg/siteList.shtml" />', 
	  dataType: 'json',    //json格式
	  height: 300,
	  width:'971',
	  colModel : [ 
					{display: '序号',name : '', width : 30, sortable : true, align: 'center'}, 
					{display: '站点名称', name : '', width : 120, sortable : true, align: 'center', escape:true},
					{display: '预计到站时间', name : '', width : 120, sortable : true, align: 'center'},
					{display: '预计停留时间', name : '', width : 120, sortable : true, align: 'center'},
					//{display: '上下行', name : '', width : 40, sortable : true, align: 'center'},
					{display: '地址', name : '', width : 120, sortable : true, align: 'center', escape:true},
					{display: 'up_stu_ids', name : '', width : 0, sortable : true, align: 'center',toggle:false,hide:true},
					{display: 'down_stu_ids', name : '', width : 0, sortable : true, align: 'center',toggle:false,hide:true},
					{display: '上车学生', name : '', width : 70, sortable : true, align: 'center',process:upStudent},
					{display: '下车学生', name : '', width : 70, sortable : true, align: 'center',process:downStudent},
					{display: '关联学生', name : '', width : 70, sortable : true, align: 'center'},
					{display: '', name : '', width : 0, sortable : true, align: 'center',process:reWriteChoiceStudent,toggle:false,hide:true}	               
	        ],
	       sortname: 'site_id',
	       sortorder: 'asc',  //升序OR降序
	       sortable: false,   //是否支持排序
	       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
	       usepager :false,  //是否分页
	       resizable: false,  //是否可以设置表格大小
	       useRp: false,    // 是否可以动态设置每页显示的结果数
	       rp: 10000,  //每页显示记录数
	       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
	       showTableToggleBtn: true   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
	     });
	});
	
	function setPropertyInnerHtml(tdDiv,pid){
		tdDiv.innerHTML =get_cell_text1(pid, 3)=='0'?'上学':'放学';
	}
	function get_cell_text1(pid, cell_idx) {
		return jQuery('#gala #row'+pid).children('td').eq(cell_idx).eq(0).text();		
	}
	function get_cell_text2(pid, cell_idx) {
		return jQuery('#gala2 #row'+pid).children('td').eq(cell_idx).eq(0).text();	
	}
	function reWriteRadioBox(tdDiv,pid){		
		tdDiv.innerHTML = '<input name="carChoice" style="_margin-top:-3px;" value="' + pid + '" type="radio" onclick="selectRoute(this)"/>';
	}
	function reWriteChoiceStudent(tdDiv,pid){
	   var strs = tdDiv.innerHTML.split(";");
       var obj={};
       obj.id = strs[0];
	   obj.upids = strs[1];
	   obj.downids = strs[2];
	   obj.type = strs[3];
	   obj.upnotexistids="";//增加参数：上行不需要显示的学生
	   obj.downnotexistids="";//增加参数：下行不需要显示的学生
	   obj.delupexistdata="";//增加参数：删除已选上行的学生
	   obj.deldownexistdata="";//增加参数：删除已选下行的学生
	   siteList.push(obj);
	}
	function choiceStudent(siteid,upstu_ids,downstu_ids,selectRow) {
		var upstudentids = "";
		var downstudentids ="";	
		var upnotexist="";
		var downnotexist="";
		var delupexistdata="";
		var deldownexistdata="";
		for(var i=0;i<siteList.length;i++){
			if(siteList[i].upnotexistids!=""){
				upnotexist = siteList[i].upnotexistids;
			}
	        if(siteList[i].downnotexistids!=""){
	        	downnotexist = siteList[i].downnotexistids;
			}
			if(siteList[i].delupexistdata!=""){
				delupexistdata = siteList[i].delupexistdata;
			}
	        if(siteList[i].deldownexistdata!=""){
	        	deldownexistdata = siteList[i].deldownexistdata;
			}
	        if(siteList[i].id ==siteid){
	        	upstudentids = siteList[i].upids;
	        	downstudentids = siteList[i].downids;
	        	break;
	        }
	    } 
	     
	    var studentListFlag=document.getElementById("route_status").value;
	    var url="../infomanage/chooseStudent.shtml?siteid="+siteid+
											      "&route_id="+chart_route_id+
												  "&upstudentids="+upstudentids+
												  "&downstudentids="+downstudentids+
												  "&studentListFlag="+studentListFlag+
												  "&selectRow="+selectRow+
												  "&upnotexist="+upnotexist+
												  "&downnotexist="+downnotexist+
												  "&delupexistdata="+delupexistdata+
		                                          "&deldownexistdata="+deldownexistdata;
		art.dialog.open("<s:url value='"+url+"' />",{
			title:"学生信息",
			lock:true,
			width:700,
			height:485
		});
	} 
	function showStudent(siteid,stu_ids,flag) {
		var studentids = "";
		for(var i=0;i<siteList.length;i++){
	         if(siteList[i].id ==siteid){
		         if(flag=='0'){
	        	   studentids = siteList[i].upids;
		         }else{
		           studentids = siteList[i].downids;
			     }
	        	 break;
	         }
	    } 
		var url="../infomanage/showStudent.shtml?siteid="+siteid+"&studentids="+studentids+"&flag="+flag;
		art.dialog.open("<s:url value='"+url+"' />",{
			title:"学生信息",
			lock:true,
			width:700,
			height:485
		});
	} 
	function upStudent(tdDiv,pid){
	}
	function downStudent(tdDiv,pid){
	}		
	function addUpStudentData(siteid,stu_ids,stu_names,upnotexist,delupexistdata){	
      if(stu_ids==""){
          document.getElementById('upDIV_'+siteid).innerHTML="0";
      } 
      if(stu_ids!=""){
         document.getElementById('upDIV_'+siteid).innerHTML=stu_names;
      }      
      for(var i=0;i<siteList.length;i++){
         if(siteList[i].id ==siteid){
        	 siteList[i].upids = stu_ids;
         }
         siteList[i].upnotexistids = upnotexist;
         siteList[i].delupexistdata=delupexistdata;
      }   
    }
	function addDownStudentData(siteid,stu_ids,stu_names,downnotexist,deldownexistdata){
       if(stu_ids==""){
         document.getElementById('downDIV_'+siteid).innerHTML="0";
       }     
       if(stu_ids!=""){
         document.getElementById('downDIV_'+siteid).innerHTML=stu_names;
       }
       for(var i=0;i<siteList.length;i++){
          if(siteList[i].id ==siteid){
          	 siteList[i].downids = stu_ids;
          }
          siteList[i].downnotexistids = downnotexist;
          siteList[i].deldownexistdata=deldownexistdata;
       }  
	}
	//查询
	function searchRoute() {
		document.getElementById('Below_new').style.display='none';
		jQuery('#gala').flexOptions({
			newp: 1,
			params: [{name: 'routeInfo.route_name', value: jQuery('#route_name').val() }]
		});
		jQuery('#gala').flexReload();
		chart_route_id ="";
		jQuery('#gala2').flexOptions({
			newp: 1,
			params: []
		});
		jQuery('#gala2').flexReload();
		siteList=[];
	}

	function choiceCar() {	
		art.dialog.open("<s:url value='../infomanage/chooseCar.shtml' />",{
			title:"车辆信息",
			lock:true,
			width:700,
			height:435
		});
	}
	function choiceDriver() {	
		var driver_ids = document.getElementById("driver_ids").value;
		var url = "../infomanage/chooseDriver.shtml?driver_ids="+driver_ids;
		art.dialog.open("<s:url value='"+url+"' />",{
			title:"驾驶员信息",
			lock:true,
			width:700,
			height:435
		});
	}
	function choiceSichen() {
		var steward_ids = document.getElementById("steward_ids").value;
		var url = "../infomanage/chooseSichen.shtml?steward_ids="+steward_ids;
		art.dialog.open("<s:url value='"+url+"' />",{	
			title:"跟车老师信息",
			lock:true,
			width:700,
			height:435
		});
	}
	//add by yg start
	function checkInputValue(thisObj){
		var patrn=/^[0-9]*$/;  
		if (!patrn.exec(thisObj.value)){
			alert("请输入整数！");
			thisObj.value="";
			thisObj.focus();
			return false;
		}  
	}
	//add by yg end
       var chart_route_id="";
	/* 点击左侧路线，获得线路编号 begin*/
	function selectRoute(rodio){
		var checkValue= new Array();
		checkValue=rodio.value.split("_");
		//if(chart_route_id != checkValue[0]){
			if(rodio.checked ==true){
				chart_route_id = checkValue[0];
				siteList=[];
				jQuery('#gala2').flexOptions({
					newp: 1,
					params: [
							 {name: 'siteInfo.routeid', value: chart_route_id },
					         {name: 'siteInfo.updownflag', value: checkValue[1]}
					         ]
				});
				document.getElementById("route_status").value=checkValue[1];
				jQuery('#gala2').flexReload();				
			}				
		//}			
	}
	//先按;分隔，然后按！分隔最后按 ，分隔
	function createSite_student(){
		var str_site_student="none";
		upupcount = 0;
		updowncount = 0;
		downupcount = 0;
		downdowncount = 0; 
		for(var i=0;i<siteList.length;i++){					  
		  //if(siteList[i].upids!="none" ||siteList[i].downids!="none"){
			  var upids="none"; 
			  var downids="none";
			  var cometime = document.getElementById("come_"+siteList[i].id).value;
			  var leavetimeData = document.getElementById("leave_"+siteList[i].id).value;
			  var leavetime1="none";
			  var leavetime2="none";
			  if(cometime==""){
				  if(siteList[i].type=="0"){
					  alert("上学预计到站时间不能为空，请添写！");
					  //changeChoose("tab1");
					  document.getElementById("come_"+siteList[i].id).focus();
					  return false;
				  }else{
					  alert("放学预计到站时间不能为空，请添写！");
					  //changeChoose("tab2");
					  document.getElementById("come_"+siteList[i].id).focus();
					  return false;
				  }
			  }
			  
			  if(leavetimeData==""){
				  if(siteList[i].type=="0"){
					  alert("上学预计停留时间不能为空，请添写！");
					  //changeChoose("tab1");
					  document.getElementById("leave_"+siteList[i].id).focus();
					  return false;
				  }else{
					  alert("放学预计停留时间不能为空，请添写！");
					  //changeChoose("tab2");
					  document.getElementById("leave_"+siteList[i].id).focus();
					  return false;
				  }
			  }
			  if(Number(leavetimeData)>86400){
					alert("预计停留时间不能跨天！");
					return false;
			  }
			  if(cometime!=""&&leavetimeData!=""){
				  if(parseInt(parseInt(cometime.split(":")[0],10)+parseInt(parseInt(cometime.split(":")[1],10)+parseInt(leavetimeData,10))/60)>=24){
					  alert("预计停留时间不能跨天！");
					  document.getElementById("leave_"+siteList[i].id).focus();
					  return false;
				  }else{
					  if(parseInt(cometime.split(":")[1],10)+parseInt(leavetimeData,10) >= 60){
					  	  leavetime1=parseInt(parseInt(cometime.split(":")[0],10)+parseInt(parseInt(cometime.split(":")[1],10)+parseInt(leavetimeData,10))/60);
						  leavetime2=parseInt(parseInt(cometime.split(":")[1],10)+parseInt(leavetimeData,10))%60;
					  }else{
						  leavetime1=parseInt(cometime.split(":")[0],10);
						  leavetime2=parseInt(cometime.split(":")[1],10)+parseInt(leavetimeData,10);
					  }
					  var leavetime = leavetime1+":"+leavetime2;
				  }
			  }
			  var type= siteList[i].type;			
			  if(siteList[i].upids!=""){
				  upids = siteList[i].upids;
				  if(type=='0'&& upids !="none"){
					  upupcount = upupcount +upids.split(",").length; 
			      }else if(type=='1'&& upids !="none"){
			    	  downupcount = downupcount +upids.split(",").length; 
				  }
			  }
			  if(siteList[i].downids!=""){
				  downids = siteList[i].downids;
				  if(type=='0'&& downids !="none"){
					  updowncount = updowncount +downids.split(",").length; 
			      }else if(type=='1'&& downids !="none"){
			    	  downdowncount = downdowncount +downids.split(",").length; 
				  }
			  }
			if(str_site_student=="none"){
                   str_site_student = siteList[i].id+"!"+upids+"!"+downids+"!"+cometime+"!"+leavetime;
		    }else{
		        str_site_student=str_site_student+";"+siteList[i].id+"!"+upids+"!"+downids+"!"+cometime+"!"+leavetime;
			}	
		//}         
	  }  
		document.getElementById("site_students").value = str_site_student;
		return true;
    }


	//页面自适应
	(function (jQuery) {
		 jQuery(window).load(function (){
			 testWidthHeight();
			  jQuery('#gala').fixHeight();
			  jQuery('#gala2').fixHeight();
			  //jQuery('#gala3').fixHeight();
			 });			
		 jQuery(window).resize(function(){
		  testWidthHeight();
		  jQuery('#gala').fixHeight();
		  jQuery('#gala2').fixHeight();
		  //jQuery('#gala3').fixHeight();
		 });
	})(jQuery);

	function get_page_height(){
		var min_height=jQuery.data(window,'mk-autoresize').options.min_height;
		return (parseInt(jQuery(window).height())<=min_height)?min_height:jQuery(window).height();
	}
	function get_page_width(){
		var min_width=jQuery.data(window,'mk-autoresize').options.min_width;
		return (parseInt(jQuery(window).width())<=min_width)?min_width:jQuery(window).width();
	}

	//计算控件宽高
	function testWidthHeight(){
		var h = get_page_height();
		var w = get_page_width();
		var leftdiv=document.getElementById("leftInfoDiv");
		jQuery('#gala').reHeight(h-240);
		jQuery('#gala2').reHeight(h-335);
		if(jQuery("#leftInfoDiv").is(":hidden")){
			 jQuery('#gala2').reWidth(w-50);
		}else{
			 jQuery('#gala2').reWidth(w-280);
		}
		jQuery(window).mk_autoresize({
	    	height_include: '#content',
	    	height_exclude: ['#header', '#footer'],
	    	height_offset: 0,
	    	width_include: ['#header', '#content', '#footer'],
	    	width_offset: 0
		});

		 h = get_page_height();
		 w = get_page_width();
		 //jQuery('#gala3').reWidth(w-280);			 
		 jQuery('#gala').reHeight(h-240);
		 jQuery('#gala2').reHeight(h-335);
		 //jQuery('#gala3').reHeight(h-378);
		 
		if(jQuery("#leftInfoDiv").is(":hidden")){
			 jQuery('#gala2').reWidth(w-50);
		}else{
			 jQuery('#gala2').reWidth(w-280);
		}
	}
	function LeftScreen(){
		var left = document.getElementById("leftInfoDiv");
		var Lefttab = document.getElementById("leftTab");
		left.style.display="none";
		Lefttab.style.display="";
		
		var searchPlanId = document.getElementById("searchPlanId");
		searchPlanId.style.display="none";
		
		var leftTabtd= document.getElementById("leftTabtd");
	    leftTabtd.style.display="";
	    jQuery('#gala2').reWidth(get_page_width()-50);
	}
	function midScreen(){
		var left = document.getElementById("leftInfoDiv");
		var Lefttab = document.getElementById("leftTab");
		var searchPlanId = document.getElementById("searchPlanId");
		left.style.display="";
		Lefttab.style.display="none";
		searchPlanId.style.display="";
		
		var leftTabtd= document.getElementById("leftTabtd");
		leftTabtd.style.display="none";		
		jQuery('#gala2').reWidth(get_page_width()-280);
	}
	/** 加载事件 **/
	function setFormEvent() {
		$('vehicle_ln').onblur = checkVehicle_ln;
		//$('driver_names').onblur = checkDriver_ids;
		//$('steward_names').onblur = checkSteward_ids;
	}
	/**车辆检查**/
	function checkVehicle_ln() {
		var vehicle_ln = $('vehicle_ln');
		if (!Mat.checkRequired(vehicle_ln))
			return false;
		return true;
	}
	/**驾驶员检查**/
	function checkDriver_ids() {
		var driver_ids = $('driver_ids');
		if (!Mat.checkRequired(driver_ids))
			return false;
		return true;
	}
	/**司乘检查**/
	function checkSteward_ids() {
		var steward_ids = $('steward_ids');
		if (!Mat.checkRequired(steward_ids))
			return false;
		return true;
	}	

	//数组包括元素判断
	Array.prototype.S=String.fromCharCode(2);  
	Array.prototype.in_array=function(e){  
		var r=new RegExp(this.S+e+this.S);  
		return (r.test(this.S+this.join(this.S)+this.S));  
	}
	
	function submitForm(){
           if(chart_route_id==""){
               alert("请选择线路");
               return false;
           }else{
           	   document.getElementById("routeid").value=chart_route_id;
           }
           if(!createSite_student()){
               return false;
           }
           if(upupcount == 0 && updowncount==0 && downupcount==0 && downdowncount==0){
               alert("请给站点选择学生");
               return false;
           }
           if(upupcount!=updowncount){
           	   alert("上学学生上车"+upupcount+"个，下车"+updowncount+"个,存在不一致请检查");
               return false;
           }
           if(downupcount!=downdowncount){
           	   alert("放学学生上车"+downupcount+"个，下车"+downdowncount+"个,存在不一致请检查");
               return false;
           }

         //上下车学生，判断是不是同一个人校验
           var upstudentid="";
           var downstudentid="";
           for(var i=0;i<siteList.length;i++){
               if(siteList[i].upids!="none"&&siteList[i].upids!=""){
               	if(upstudentid==""){
               		upstudentid = siteList[i].upids;
          			}else{
          				upstudentid = upstudentid+","+siteList[i].upids;
          			}
               }
           }

           for(var q=0;q<siteList.length;q++){
            if(siteList[q].downids!="none"&&siteList[q].downids!=""){
            	if(downstudentid==""){
            		downstudentid = siteList[q].downids;
       			}else{
       				downstudentid = downstudentid+","+siteList[q].downids;
       			}
            }
           }
          
			//数组排序
           var upidsarr=upstudentid.split(",").sort();
           var downidsarr=downstudentid.split(",").sort();
           var diffuparr=[];
           var diffdownarr=[];
           if(upidsarr.toString()!=downidsarr.toString()){
               //上行数组中是否有下行数组中不包括的元素，有则存放到数组中
	           	for(var j=0;j<upidsarr.length;j++){
	           		if(!downidsarr.in_array(upidsarr[j])){
		               		if(upidsarr[j]!=""){
		                   		diffuparr.push(upidsarr[j]);
		                   	}
					}
	           }
	           	//下行数组中是否有上行数组中不包括的元素，有则存放到数组中
               for(var h=0;h<downidsarr.length;h++){
                   if(!upidsarr.in_array(downidsarr[h])){
	                   	if(downidsarr[h]!=""){
							diffdownarr.push(downidsarr[h]);
	                   	}
			   	   }
               }
				var studentupstr="";
				var studentdownstr="";
	           	jQuery.ajax({
	           		  type: 'POST', 
	           		  async:false, 
	           		  url: '../infomanage/getupstudentname.shtml',	
	           		  data: {upstudentids: diffuparr.toString()},
	           		  success: function(studentname) {
	           			  studentupstr=studentname;
							//alert("上车学生：“"+studentname+"” 没有在下车学生中！");
	           		  }  
	           	});
	
	           	jQuery.ajax({
	          		  type: 'POST',  
	          		  async:false,
	          		  url: '../infomanage/getupstudentname.shtml',	
	          		  data: {upstudentids: diffdownarr.toString()},
	          		  success: function(studentname) {
	          		      studentdownstr=studentname;
	          		  }  
	         		});
	         		if(studentupstr!=""&&studentdownstr==""){
	         			alert("上车学生：“"+studentupstr+"” 没有在下车学生中！");
	             	}
	         		if(studentupstr==""&&studentdownstr!=""){
	         			alert("下车学生：“"+studentdownstr+"” 没有在上车学生中！");
	             	}
	         		if(studentupstr!=""&&studentdownstr!=""){
	         			alert("上车学生：“"+studentupstr+"” 没有在下车学生中！    下车学生：“"+studentdownstr+"” 没有在上车学生中！");
	             	}
	           	return false;
           }
           if(checkVehicle_ln()){
		   		var form = document.getElementById('addridingPlanForm');
		   		form.submit();
           }
	}
	window.addEvent('domready', setFormEvent);	
</script>