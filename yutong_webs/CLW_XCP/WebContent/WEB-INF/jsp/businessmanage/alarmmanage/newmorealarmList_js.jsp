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
	var parm={"alarmmanage.alarm_type_id":tabflag,vehicle_ln:ln};
	if(tabflag=="'09','10','13','25','26','64','65','66','67','68','69','70','71','88','89','90'")
	{	
		load_page("<s:url value='/busalarm/newmoreotheralarm.shtml'/>",parm);
	}else if(tabflag=="'32'"){
		load_page("<s:url value='/busalarm/newchaosualarm.shtml'/>",parm);
	}else if(tabflag=="'73','74','79','80'"){
		load_page("<s:url value='/busalarm/newstualarm.shtml'/>",parm);
	}else{
		load_page("<s:url value='/busalarm/newsosalarm.shtml'/>",parm);
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

function load_page(url,parm) {
	
	jQuery('#content_rightside').load(url, parm, function() {
		jQuery(window).mk_autoresize({
            height_include: '#content',
            height_exclude: ['#header', '#footer'],
            height_offset: 0,
            width_include: ['#header', '#content', '#footer'],
            width_offset: 0
         });
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
	var ln=jQuery('#vehicle_ln').val();
	if(flag==1){
		jQuery('#piliangchuli').css('display','');
		var parm={"alarmmanage.alarm_type_id":"'40','72'","vehicle_ln":ln};
		load_page("<s:url value='/busalarm/newsosalarm.shtml'/>",parm);
	}

	if(flag==2){
		jQuery('#piliangchuli').css('display','');
		var parm={"alarmmanage.alarm_type_id":"'32'","vehicle_ln":ln};
		load_page("<s:url value='/busalarm/newchaosualarm.shtml'/>",parm);
	}

	 if(flag==3){
		jQuery('#piliangchuli').css('display','');
		var parm={"alarmmanage.alarm_type_id":"'09','10','13','25','26','64','65','66','67','68','69','70','71','88','89','90'","vehicle_ln":ln};
		load_page("<s:url value='/busalarm/newmoreotheralarm.shtml'/>",parm);
	 }

	 if(flag==4){
		 jQuery('#piliangchuli').css('display','');
		 var parm={"alarmmanage.alarm_type_id":"'73','74','79','80'","vehicle_ln":ln};
	     load_page("<s:url value='/busalarm/newstualarm.shtml'/>",parm); 
	 }
}

//点击树
/*
function mytreeonClick(event, treeId, treeNode){
	document.getElementById('chooseorgid').value=treeNode.id;
    searchList();
}*/



	// add by jinp begin 20120817
	function openPopUp(vehicle_vin,alarmid,alarmtime,alarmtypeid,lon,lat) {
		DWREngine.setAsync(false);   
		 GPSDwr.returnTipInfo(vehicle_vin,function (obj){
			  if (obj != null) {  
				  TerminalViBean=obj[0]
			  }
		    });
		 DWREngine.setAsync(true); 
         //alert(alarmtime);
         //alert(alarmtime.substr(0,10));
		 //alert(alarmtime.substr(11,8));
		 var alarmtime1=alarmtime.substr(0,10);
		 var alarmtime2=alarmtime.substr(11,8);
		 alarmtime=alarmtime1+alarmtime2;
		
		 openFramePage(TerminalViBean.VEHICLE_LN,TerminalViBean.VEHICLE_VIN,lon,lat,
				 TerminalViBean.ROUTE_ID,'6',TerminalViBean.color,TerminalViBean.VEH_EXT_INFO,TerminalViBean.STAT_INFO,
				 alarmid,alarmtypeid,alarmtime,TerminalViBean.dvrState);
	     
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
	testsidelayerHead(TerminalViBean.VEHICLE_LN,TerminalViBean.VEHICLE_VIN,TerminalViBean.LONGITUDE,TerminalViBean.LATITUDE,TerminalViBean.ROUTE_ID,jiaodianid,
		    		TerminalViBean.color,TerminalViBean.VEH_EXT_INFO,TerminalViBean.STAT_INFO,alarmid,alarmtypeid,alarmtime,TerminalViBean.dvrState);
	startsideflash(jiaodianid,TerminalViBean.VEHICLE_VIN,alarmid,alarmtypeid,alarmtime);
	
}

	/**
	 * 刷新浮动框按钮交点
	 */
	function change_side_images(target) {
		jQuery('.mk-sidelayer-tools').find('li').removeClass('selected');
		jQuery(target).addClass('selected');
    }

	function testsidelayerHead(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime,dvrState){
		//openFramePage(flag,ln,vin,lon,lat,routeid,optpageid);
		//jQuery('#popArea').mk_sidelayer('set_title', ln);
		//alert(color +";"+STAT_INFO+";"+VEH_EXT_INFO);
		ln = encodeHtml(ln);
		var sidebuttonHtml="<ul>";
		//消息调度	
		<s:if test="#session.perUrlList.contains('111_3_1_8')"> 
		sidebuttonHtml +="<li id='aa5'><a href='#' title='信息调度' onclick=openFramePage('";
		sidebuttonHtml += ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','5";	    
		sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"','"+dvrState+"')><img src='../newimages/qipaoimages/btn_tool1.png'/></a></li>";
		</s:if>
		//拍照
		<s:if test="#session.perUrlList.contains('111_3_1_5')"> 
		if(color =="b"   && STAT_INFO == 1){
			sidebuttonHtml+="<li id='aa4'><a href='#' title='车辆拍照' onclick=PhotoImage('";
			sidebuttonHtml+= ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','4";	    
			sidebuttonHtml+="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"','"+dvrState+"')><img src='../newimages/qipaoimages/btn_tool2.png'/></a></li>";
		}else{
			sidebuttonHtml+="<li id='aa4'><a href='#' title='车辆拍照' onclick=PhotoImage('";
			sidebuttonHtml+= ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','4";	    
			sidebuttonHtml+="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"','"+dvrState+"')><img src='../newimages/qipaoimages/btn_tool2h.png'/></a></li>";
			
		}
		</s:if>
		//视频监控
		<s:if test="#session.perUrlList.contains('111_3_1_6')"> 
		if(dvrState == 1){ 
			sidebuttonHtml +="<li id='aa3'><a href='#' title='视频监控' onclick=VideoImage('";
			sidebuttonHtml += ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','3";
			sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"','"+dvrState+"')><img src='../newimages/qipaoimages/btn_tool3.png'/></a></li>";
		}else{
			sidebuttonHtml +="<li id='aa3'><a href='#' title='视频监控' onclick=VideoImage('";
			sidebuttonHtml += ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','3";
			sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"','"+dvrState+"')><img src='../newimages/qipaoimages/btn_tool3h.png'/></a></li>";
		}
		</s:if>
		//视频回放
		<s:if test="#session.perUrlList.contains('111_3_1_11')"> 
		if(dvrState == 1){
			sidebuttonHtml+="<li id='aa7'><a href='#' title='视频回放' onclick=replayVideoImage('";
			sidebuttonHtml += ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','7";
			sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"','"+dvrState+"')><img src='../newimages/qipaoimages/btn_tool7.png'/></a></li>";
		}else{
			sidebuttonHtml+="<li id='aa7'><a href='#' title='视频回放' onclick=replayVideoImage('";
			sidebuttonHtml += ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','7";
			sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"','"+dvrState+"')><img src='../newimages/qipaoimages/btn_tool7_1.png'/></a></li>";
		}
		</s:if> 
		//轨迹回放
		<s:if test="#session.perUrlList.contains('111_3_1_4')"> 
		sidebuttonHtml+="<li id='aa1'><a href='#' title='轨迹回放' onclick=openFramePage('";
		sidebuttonHtml += ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','1";	    
		sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"','"+dvrState+"')><img src='../newimages/qipaoimages/btn_tool5.png'/></a></li>";
		</s:if>
		//重点监控
		<s:if test="#session.perUrlList.contains('111_3_1_10')"> 
		sidebuttonHtml+="<li id='aa2'><a href='#' title='重点监控' onclick=openFramePage('";
		sidebuttonHtml += ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','2";	    
		sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"','"+dvrState+"')><img src='../newimages/qipaoimages/btn_tool6.png'/></a></li>";
		</s:if>
		//告警处理
		<s:if test="#session.perUrlList.contains('111_3_1_12')"> 
		sidebuttonHtml+="<li id='aa6'><a href='#' title='告警处理' onclick=openFramePage('";
		sidebuttonHtml += ln+"','"+vin+"','"+lon+"','"+lat+"','"+routeid+"','6";	    
		sidebuttonHtml +="','"+color+"','"+VEH_EXT_INFO+"','"+STAT_INFO+"','"+alarmid+"','"+alarmtypeid+"','"+alarmtime+"','"+dvrState+"')><img src='../newimages/qipaoimages/ico_warning.png'/></a></li>";
		</s:if>
		sidebuttonHtml+="</ul>";
		//alert(sidebuttonHtml);
		
		jQuery('.mk-sidelayer-tools').html(sidebuttonHtml);
		
		if(jiaodianid != ""){
			//alert(jiaodianid);
			change_side_images('#aa'+jiaodianid);
		}
		
		//openFramePage(flag,ln,vin,lon,lat,routeid,optpageid);
	}
	
	function openFramePage(ln,vin,lon,lat,
			routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,
			alarmid,alarmtypeid,alarmtime,dvrState){
		var iframeobj = document.getElementById("iframeshowArea");
    	if(iframeobj != null){
    		iframeobj.src ="";
    	}
		testsidelayerHead(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime,dvrState);

		var titleln=ln;
		if(titleln.length>7){
			titleln=titleln.substr(titleln.length-7);
			titleln="*"+titleln;
		}
		
		jQuery('#popArea').mk_sidelayer('set_title',encodeHtml(titleln));
		
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
	function PhotoImage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime,dvrState){
		//alert(color +';'+STAT_INFO);
		//if(color =="b"   && STAT_INFO  == 1){
			openFramePage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime,dvrState);
		//}
		//else{
		//	alert("车辆已熄火，请稍后再尝试车辆拍照！");
		//}
	}
	// 视频点击处理
	function VideoImage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime,dvrState){
		//alert(color +';'+VEH_EXT_INFO);
		//if(color =="b"   && VEH_EXT_INFO == 1){
			openFramePage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime,dvrState);
		//}
		//else{
		//	alert("3G视频不在线，请稍后再尝试视频监控！");
		//}
		
		
	}
	//视频回放点击处理
	function replayVideoImage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime,dvrState){
		//alert(color +';'+VEH_EXT_INFO);
		//if(color =="b"   && VEH_EXT_INFO == 1){
			openFramePage(ln,vin,lon,lat,routeid,optpageid,color,VEH_EXT_INFO,STAT_INFO,alarmid,alarmtypeid,alarmtime,dvrState);
		//}
		//else{
		//	alert("3G视频不在线，请稍后再尝试视频回放！");
		//}	
	}
	// add by jinp end 20120817
</script>