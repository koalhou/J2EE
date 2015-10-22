<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
jQuery(function() {
	 jQuery('#popArea').mk_sidelayer({
	      // 'top': '130px',
	      'height': '540px',
	      'width': '500px',
	      //'url': '<s:url value="/carrun/ready.shtml" />',
	      'is_show': false,
	      'can_close': true,
	      close_callback: function() {
		    	var iframeobj = document.getElementById("iframeshowArea");
		    	if(iframeobj != null){
		    		iframeobj.src ="";
		    	}
	      },
	      hide_callback: function() {
		    	var iframeobj = document.getElementById("iframeshowArea");
		    	if(iframeobj != null){
			    	var test=iframeobj.src;
			    	if(test.indexOf("Video")==-1){
			    		iframeobj.src ="";
			    	}
			    	if(test.indexOf("Video")!=-1)
			    	{
						if(test.indexOf("RePlayVideo")==-1){
							parent.frames["iframeshowArea"].window.allstop();	
						}
			    	} 	
		    	}
	    	}
	     // reload_callback: function() {alert('reload');} 
	    });
});

//页面自动加载
jQuery(function(){
	var tabflag=jQuery('#newalarmtypeid').val();
	var ln=jQuery('#newveh_ln').val();
	var parm = null;
	if(tabflag=="'40'" || tabflag=="" || tabflag==null){//紧急
		parm = {"alarmmanage.alarm_type_id":tabflag,vehicle_ln:ln};
		load_page("<s:url value='/busalarm/newsosalarm.shtml'/>",parm);
	}else if(tabflag=="'1'"){//未满发车告警
		parm = {"alarmmanage.alarm_type_id":tabflag,vehicle_ln:ln,flag: "7"};
		load_page("<s:url value='/busalarm/newnofullalarm1.shtml'/>",parm);
	}else if(tabflag=="'3'"){//站外开门
		parm = {"alarmmanage.alarm_type_id":tabflag,vehicle_ln:ln,flag: "6"};
		load_page("<s:url value='/busalarm/newnotsitealarm.shtml'/>",parm);
	}else if(tabflag=="'4'"){//迟到告警
		parm = {"alarmmanage.alarm_type_id":tabflag,vehicle_ln:ln,flag:"4"};
		load_page("<s:url value='/busalarm/newlatealarm.shtml'/>",parm);
	}else if(tabflag=="'2','5'" || tabflag == "'2'" || tabflag == "'5'"){//非时发车
		parm = {"alarmmanage.alarm_type_id":tabflag,vehicle_ln:ln,flag: "5"};
		load_page("<s:url value='/busalarm/newnofullalarm.shtml' />",parm);
	}else if(tabflag=="'32'"){//超速告警
		parm = {"alarmmanage.alarm_type_id":tabflag,vehicle_ln:ln};
		load_page("<s:url value='/busalarm/newmoreotheralarm.shtml' />",parm);
	}else if(tabflag=="'010','001'" || tabflag=="'010'" || tabflag=="'001'"){//油量告警 
		parm = {"alarmmanage.alarm_type_id":tabflag,vehicle_ln:ln,flag: "8"};
		load_page("<s:url value='/busalarm/newoilalarm.shtml' />",parm);
	}

});

function firstrisize(){
	//计算中区高度
	jQuery('#content').mk_autoresize( {
		height_include : [ '#content_rightside', '#content_leftside' ],
		height_offset : 0
	});

	//计算左测区域高度
	jQuery('#content_leftside').mk_autoresize( {
		height_exclude : '#leftInfoDiv',
		height_include : '#lefttree',
		height_offset :8

	});

	//计算树区域高度
	/*
	jQuery('#lefttree').mk_autoresize( {
		height_include : '#treeDemo',
		height_offset : 5
	});	*/ 
	
	 if(jQuery('#middeldiv').css("display")=="none"){

		 jQuery('#content').mk_autoresize({
			    width_exclude:'#content_leftside',
			    //height_include: '.bDiv',
			    //height_offset: 290, // 该值各页面根据自己的页面布局调整
			    width_include: '.flexigrid',
			    width_offset: 10// 该值各页面根据自己的页面布局调整
			  });

			 
			jQuery('#content_rightside').mk_autoresize({
			    //width_exclude:'#content_leftside',
			    height_include: '.bDiv',
			    height_offset: 304 // 该值各页面根据自己的页面布局调整
			    //width_include: '.flexigrid',
			    //width_offset: -5// 该值各页面根据自己的页面布局调整
			  });
		 /*
		 jQuery('#content').mk_autoresize({
			 width_exclude:'#content_leftside',
			    height_include: '.bDiv',
			    height_offset: 306,
			    width_include: '.flexigrid',
			    width_offset: 10 // 该值各页面根据自己的页面布局调整
			});*/
	 }else{

		 jQuery('#content').mk_autoresize({
			    width_exclude:'#content_leftside',
			    //height_include: '.bDiv',
			    //height_offset: 290, // 该值各页面根据自己的页面布局调整
			    width_include: '.flexigrid',
			    width_offset: 32// 该值各页面根据自己的页面布局调整
			  });

			 
			jQuery('#content_rightside').mk_autoresize({
			    //width_exclude:'#content_leftside',
			    height_include: '.bDiv',
			    height_offset: 304 // 该值各页面根据自己的页面布局调整
			    //width_include: '.flexigrid',
			    //width_offset: -5// 该值各页面根据自己的页面布局调整
			  });

		/*	  
		 jQuery('#content').mk_autoresize({
			 width_exclude:'#content_leftside',
			    height_include: '.bDiv',
			    height_offset: 306,
			    width_include: '.flexigrid',
			    width_offset: 32 // 该值各页面根据自己的页面布局调整
			});*/
	 }
	

}
function chooseAlarmSearch() {}

function load_page(url,parm) {
	
	jQuery('#statusManger').load(url, parm, function() {
		jQuery(window).mk_autoresize({
            height_include: '#content',
            min_width: 1340,
            height_exclude: ['#header', '#footer'],
            height_offset: 0,
            width_include: ['#header', '#content', '#footer'],
            width_offset: 0
         });
		firstrisize();
		
		jQuery(window).mk_autoresize({
            height_include: '#content',
            min_width: 1340,
            height_exclude: ['#header', '#footer'],
            height_offset: 0,
            width_include: ['#header', '#content', '#footer'],
            width_offset: 0
         });
		firstrisize();
				
	});
}

/**
* 左侧树区域显示控制
*/
function HideandShowControl(){//leftdiv
	if(jQuery('#middeldiv').css("display")=="none"){//展开状态
		jQuery('#middeldiv').css("display","block");
		jQuery('#leftdiv').css("display","none");
		 jQuery('#content').mk_autoresize({
		        width_exclude: '#content_leftside',
		        width_include: '.flexigrid',
		        width_offset: 32,// 该值各页面根据自己的页面布局调整
		        is_handler: false
	    });	
	}else{//隐藏状态
		jQuery('#middeldiv').css("display","none");
		jQuery('#leftdiv').css("display","block");
		jQuery('#content').mk_autoresize({
	        width_exclude: '#content_leftside',
	        width_include: '.flexigrid',
	        width_offset: 10,// 该值各页面根据自己的页面布局调整
	        is_handler: false
	    });
	}
}

//切换告警标题
function changetab(obj,flag){
	//var changename=jQuery(obj).html();
	//jQuery('#alarmname').html(changename);
	//jQuery('#tabname').css('display','none');
 	jQuery("#piliangchuli").css("display","block");
	if(jQuery('#popArea').mk_sidelayer('is_show')) {
		jQuery('#popArea').mk_sidelayer('close');
	} 

	var ln=jQuery('#vehicle_ln').val();
	if(flag==1){//紧急告警 
// 		jQuery('#piliangchuli').css('display','');
		var parm={"alarmmanage.alarm_type_id":"'40'","vehicle_ln":ln};
		load_page("<s:url value='/busalarm/newsosalarm.shtml'/>",parm);
	}

	if(flag==2){
// 		jQuery('#piliangchuli').css('display','');
		var parm={"alarmmanage.alarm_type_id":"'32'","vehicle_ln":ln};
		load_page("<s:url value='/busalarm/newmoreotheralarm.shtml'/>",parm);
	}

//	 if(flag==3){
//		jQuery('#piliangchuli').css('display','');
//		var parm={"alarmmanage.alarm_type_id":"'09','10','13','25','26','64','65','66','67','68','69','70','71'","vehicle_ln":ln};
//		load_page("<s:url value='/busalarm/newmoreotheralarm.shtml'/>",parm);
//	 }

	 if(flag==4){
// 		 jQuery('#piliangchuli').css('display','');
		 var parm={"alarmmanage.alarm_type_id":"'94'","vehicle_ln":ln,"flag":"4"};
	     load_page("<s:url value='/busalarm/newlatealarm.shtml'/>",parm); 
	 }
	 if(flag==5){
// 		 jQuery('#piliangchuli').css('display','');
		 var parm={"alarmmanage.alarm_type_id":"'92'","vehicle_ln":ln,"flag":"5"};
	     load_page("<s:url value='/busalarm/newnofullalarm.shtml'/>",parm); 
	 }
	 if(flag==6){
// 		 jQuery('#piliangchuli').css('display','');
		 var parm={"alarmmanage.alarm_type_id":"'93'","vehicle_ln":ln,"flag":"6"};
	     load_page("<s:url value='/busalarm/newnotsitealarm.shtml'/>",parm); 
	 }	 
	 if(flag==7){
// 		 jQuery('#piliangchuli').css('display','');
		 var parm={"alarmmanage.alarm_type_id":"'91'","vehicle_ln":ln,"flag":"7"};
	     load_page("<s:url value='/busalarm/newnofullalarm1.shtml'/>",parm); 
	 }
	 if(flag==8){
// 		 jQuery('#piliangchuli').css('display','');
		 var parm={"alarmmanage.alarm_type_id":"'001'","vehicle_ln":ln,"flag":"8"};
		 load_page("<s:url value='/busalarm/newoilalarm.shtml'/>",parm);
	 }
}

//点击树

function mytreeonClick(event, treeId, treeNode){
	document.getElementById('chooseorgid').value=treeNode.id;
    searchList();
}



	// add by jinp begin 20120817
	function openPopUp(vehicle_vin,alarmid,alarmtime,alarmtypeid,lon,lat) {
		DWREngine.setAsync(false);   
		 GPSDwr.returnTipInfo(vehicle_vin,function (obj){
			  if (obj != null) {  
				  TerminalViBean=obj[0];
			  }
		    });
		 DWREngine.setAsync(true); 
		 var alarmtime1=alarmtime.substr(0,10);
		 var alarmtime2=alarmtime.substr(11,8);
		 alarmtime=alarmtime1+""+alarmtime2;
		 openFramePage(TerminalViBean.vehicle_code,TerminalViBean.VEHICLE_LN,TerminalViBean.VEHICLE_VIN,lon,lat,
				 TerminalViBean.ROUTE_ID,'6',TerminalViBean.color,TerminalViBean.VEH_EXT_INFO,TerminalViBean.STAT_INFO,
				 alarmid,alarmtypeid,alarmtime);
	     
	}
/*
* 刷新浮动框头
*/
var jiaodianid = "";
var sidevin = "";
var intsideflash =0;

/**
* 浮动框刷新
*/
function startsideflash(vjiaodianid,vin,valarmid,valarmtypeid,valarmtime){
	jiaodianid = vjiaodianid;
	sidevin = vin;
	alarmid = valarmid;
	alarmtypeid = valarmtypeid;
	alarmtime = valarmtime;
	intsideflash = window.setTimeout("sidelayerrealflash()",10000);
}

var alarmid,alarmtypeid,alarmtime = null;
	function sidelayerrealflash(){
		clearTimeout(intsideflash);
		if(sidevin != null && sidevin !=""){
			if(jQuery('#popArea').mk_sidelayer('is_show')){
				GPSDwr.returnTipInfo(sidevin,returnsidelayerFlash_callback);
			}
			else{
				clearTimeout(intsideflash);
			}
		}
		else{
			clearTimeout(intsideflash);
		}
	}
function returnsidelayerFlash_callback(data){

	var TerminalViBean = data[0];
	testsidelayerHead(TerminalViBean.vehicle_code,TerminalViBean.VEHICLE_LN,TerminalViBean.VEHICLE_VIN,TerminalViBean.LONGITUDE,TerminalViBean.LATITUDE,TerminalViBean.ROUTE_ID,jiaodianid,
		    		TerminalViBean.color,TerminalViBean.VEH_EXT_INFO,TerminalViBean.STAT_INFO,alarmid,alarmtypeid,alarmtime);
	startsideflash(jiaodianid,TerminalViBean.VEHICLE_VIN,alarmid,alarmtypeid,alarmtime);
	
}

	/**
	 * 刷新浮动框按钮交点
	 */
	function change_side_images(target) {
		jQuery('.mk-sidelayer-tools').find('li').removeClass('selected');
		jQuery(target).addClass('selected');
    }

	function testsidelayerHead(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
		//openFramePage(flag,ln,vin,lon,lat,routeid,optpageid);
		//jQuery('#popArea').mk_sidelayer('set_title', ln);
		ln = encodeHtml(ln);
		var sidebuttonHtml="<ul>";
		//消息调度	
		 <s:if test="#session.perUrlList.contains('111_3_1_4')">   
		 sidebuttonHtml +="<li id='aa5'><a  title='信息调度' onclick=openFramePage('";
		sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','5";	    
		sidebuttonHtml += "','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool1.png'/></a></li>";
		</s:if>
		/*** 拍照 ***/
		<s:if test="#session.perUrlList.contains('111_3_1_10')">
		if(color =="b"   && STAT_INFO == 1){
			sidebuttonHtml+="<li id='aa4'><a href='javascript:void(0)' title='车辆拍照' onclick=PhotoImage('";
			sidebuttonHtml+= code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','4";	    
			sidebuttonHtml+="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool2.png'/></a></li>";
		}else{
			sidebuttonHtml+="<li id='aa4'><a href='javascript:void(0)' title='车辆拍照' onclick=PhotoImage('";
			sidebuttonHtml+= code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','4";	    
			sidebuttonHtml+="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool2h.png'/></a></li>";
		}
		</s:if>
		//轨迹回放
		<s:if test="#session.perUrlList.contains('111_3_1_5')">
		sidebuttonHtml+="<li id='aa1'><a  title='轨迹回放' onclick=openFramePage('";
		sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','1";	    
		sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool5.png'/></a></li>";
		</s:if> 
		//重点监控
		 <s:if test="#session.perUrlList.contains('111_3_1_6')"> 
		 sidebuttonHtml+="<li id='aa2'><a  title='重点监控' onclick=openFramePage('";
			sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','2";	    
			sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/btn_tool6.png'/></a></li>";
		</s:if> 
		//告警处理
		 <s:if test="#session.perUrlList.contains('111_3_1_7')"> 
		 sidebuttonHtml+="<li id='aa6'><a  title='告警处理' onclick=openFramePage('";
			sidebuttonHtml += code+"','"+ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','6";	    
			sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"')><img src='../newimages/qipaoimages/ico_warning.png'/></a></li>";
		</s:if> 
		sidebuttonHtml+="</ul>";

		jQuery('.mk-sidelayer-tools').html(sidebuttonHtml);

		if(jiaodianid != ""){
			change_side_images('#aa'+jiaodianid);
		}

		//openFramePage(flag,ln,vin,lon,lat,routeid,optpageid);
	}
	
	function openFramePage(code,ln,vin,lon,lat,
			routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,
			alarmid,alarmtypeid,alarmtime){
		var iframeobj = document.getElementById("iframeshowArea");
    	if(iframeobj != null){
    		iframeobj.src ="";
    	}
		testsidelayerHead(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);
		var titleln=ln;
		if(titleln.length>7){
			titleln=code+",*"+titleln.substr(titleln.length-7);
		}
		
		else{
			titleln=code+','+titleln;
		}
		jQuery('#popArea').mk_sidelayer('set_title',encodeHtml(titleln));
		//jQuery('.mk-sidelayer-tools').html();
		if(optpageid == 1){//轨迹回放
			//jQuery('#popArea1').mk_sidelayer('show');
		    jQuery('#popArea').mk_sidelayer('reload', 
				    '<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
				    +'&route_id='+routeid+'&optpageid='+optpageid 
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		}
		if(optpageid == 2){//重点监控
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		}
		if(optpageid == 6){//告警处理
			/*
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);*/
		   jQuery('#popArea').mk_sidelayer('reload', {
			    	    'url': '<s:url value="/gps/iFramechooseAction.shtml" />', 
			    	     'query_param': {'vin': vin, 'lon':lon,'lat':lat,'routeid':routeid,
				    	                  'optpageid':optpageid,'alarmid':alarmid,'alarmtypeid':alarmtypeid,
				    	                  'alarmtime':alarmtime,'sourceid':'2'}
                    });
			change_side_images('#aa'+optpageid);
		}
		if(optpageid == 5){//消息调度
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		}
		if(optpageid == 4){//车辆拍照
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		}
		
		if(optpageid == 3){//视频监控
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		}
		if(optpageid == 7){//视频回放
			jQuery('#popArea').mk_sidelayer('reload', 
					'<s:url value="/gps/iFramechooseAction.shtml" />?vin='+vin+'&lon='+lon+'&lat='+lat
					+'&route_id='+routeid+'&optpageid='+optpageid
				    +'&alarmid='+alarmid+'&alarmtypeid='+alarmtypeid+'&alarmtime='+alarmtime);
			change_side_images('#aa'+optpageid);
		}
		
		startsideflash(optpageid,vin,alarmid,alarmtypeid,alarmtime);
		//intsideflash = window.setTimeout("sidelayerrealflash()",flushMap);    
	}

	// 拍照图片点击处理
	function PhotoImage(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
		//if(color =="b"   && STAT_INFO  == 1){
			openFramePage(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);
		//}
		//else{
		//	alert("车辆已熄火，请稍后再尝试车辆拍照！");
		//}
	}
	// 视频点击处理
	function VideoImage(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
		//if(color =="b"   && VEH_EXT_INFO == 1){
			openFramePage(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);
		//}
		//else{
		//	alert("3G视频不在线，请稍后再尝试视频监控！");
		//}
		
		
	}
	//视频回放点击处理
	function replayVideoImage(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime){
		//if(color =="b"   && VEH_EXT_INFO == 1){
			openFramePage(code,ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime);
		//}
		//else{
		//	alert("3G视频不在线，请稍后再尝试视频回放！");
		//}	
	}
	// add by jinp end 20120817
	
</script>