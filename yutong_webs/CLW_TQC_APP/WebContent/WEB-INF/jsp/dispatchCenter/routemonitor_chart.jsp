<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='../mooko-ui/themes/default/mk.routemonitor.css' />">
<script type="text/javascript" src="<s:url value="../mooko-ui/core/mk.routemonitor.js" />"></script>
<script type="text/javascript" src="<s:url value='/scripts/sidelayerMapUitl.js'/>"></script>

<script>
	function arrayRemove(array, item){
		var index = jQuery.inArray(item, array);
		if (index > -1){
			array.splice(index, 1);
		}
	};
	function writetableinfo() {
		/* jQuery("#vehicle_code").html(''); */
		jQuery("#driver_name").html('');
		jQuery("#vehicle_ln").val('');
		jQuery("#vehicle_vin").val('');
		//jQuery("input[name=turnonvehiclechico]:eq(0)").attr("checked","checked");
		jQuery("#send_time_s").val("07:00");
	}
	function show_rm(data,v) {
		if(v==0) {
			jQuery('#rm_route').mk_routemonitor('reload', data);
		}else if(v==1){
			jQuery('#rm_route2').mk_routemonitor('reload', data);
		}else if(v==2){
			jQuery('#rm_route3').mk_routemonitor('reload', data);
		}
	}
	var deldata = null;
	function delcarlistvalue(i,ii) {
		jQuery("#route_list_ii").val(ii);
		if(ii==1) {
			deldata = {
				"routeChart.route_id":data_list_car[i].route_id,
				"routeChart.trip_id":data_list_car[i].trip_id,
				"routeChart.VIN":data_list_car[i].VIN,
				"routeChart.send_order":data_list_car[i].send_order,
				"routeChart.exe_date":jQuery("#queryTime").val(),
				"routeChart.add_flag":data_list_car[i].add_flag,
				"routeChart.trip_group":data_list_car[i].trip_group
			};
		} else if (ii==2) {
			deldata = {
				"routeChart.route_id":data_list_car2[i].route_id,
				"routeChart.trip_id":data_list_car2[i].trip_id,
				"routeChart.VIN":data_list_car2[i].VIN,
				"routeChart.send_order":data_list_car2[i].send_order,
				"routeChart.exe_date":jQuery("#queryTime").val(),
				"routeChart.add_flag":data_list_car2[i].add_flag,
				"routeChart.trip_group":data_list_car2[i].trip_group
			};
		} else if(ii==3) {
			deldata = {
				"routeChart.route_id":data_list_car3[i].route_id,
				"routeChart.trip_id":data_list_car3[i].trip_id,
				"routeChart.VIN":data_list_car3[i].VIN,
				"routeChart.send_order":data_list_car3[i].send_order,
				"routeChart.exe_date":jQuery("#queryTime").val(),
				"routeChart.add_flag":data_list_car3[i].add_flag,
				"routeChart.trip_group":data_list_car3[i].trip_group
			};
		} else {
			//alert("请选择行列！");
		}
		return deldata;
	}
	function delcarlist(i,ii) {
		/* var route_car_length = "1"; */
		var data = delcarlistvalue(i,ii);
		jQuery.post("../dispatchroute_chart/route_del_car.shtml",data,function(v){
			//删除后刷新整个线路	修改为刷新当前线路
			searchCarRunList("1");
		});
	}
	function delcarlistexe(i,ii) {
		if(jQuery("input[name=turnonvehiclechico]:checked").val() == '1') {
			//如果是循环发车校验发车是否存在
			if(sendcar_return(jQuery("#vehicle_vin").val(),ii)) {
				//alert("循环发车车辆不可重复添加！");
				//return false;
			}
		}
		var data = delcarlistvalue(i,ii);
		//校验 防止删除后添加缺少字段
		if(jQuery("input[name=turnonvehiclechico]:checked").val()==2&&jQuery("#send_time_s").val()=="") {
			alert("请选择发车时间");
			return false;
		}
		jQuery.post("../dispatchroute_chart/route_del_car_nosend.shtml",data,function(v){
			//判断是否是更新	如果是更新不刷新车辆列
			if(v=='success') {
				addcarlistinfo(1);
			} else if(v=='run') {
				alert("车辆线路已跑过！");
			}
		});
	}
	var addorderinfohtml = "";
	function addorderinfoto(i,ii,type) {
		if(type == 1)
			addorderinfohtml = "<option>"+(i+2)+"</option>";
		else if(type == 2)
			addorderinfohtml = "<option>"+(i+1)+"</option>";
		if(ii == 1) {
			if(type == 1) {
				for(var v=0;v<data_list_car.length+1;v++) {
					if(v!=(i+1))
						addorderinfohtml += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car.length;v++) {
					if(v!=i)
						addorderinfohtml += "<option>"+(v+1)+"</option>";
				}
			}
			jQuery("#routecarseqordernum").html(addorderinfohtml);
		} else if(ii == 2) {
			if(type == 1) {
				for(var v=0;v<data_list_car2.length+1;v++) {
					if(v!=(i+1))
						addorderinfohtml += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car2.length;v++) {
					if(v!=i)
						addorderinfohtml += "<option>"+(v+1)+"</option>";
				}
			}
			jQuery("#routecarseqordernum").html(addorderinfohtml);
		} else if(ii == 3) {
			if(type == 1) {
				for(var v=0;v<data_list_car3.length+1;v++) {
					if(v!=(i+1))
						addorderinfohtml += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car3.length;v++) {
					if(v!=i)
						addorderinfohtml += "<option>"+(v+1)+"</option>";
				}
			}
			jQuery("#routecarseqordernum").html(addorderinfohtml);
		}
	}
	//i为发车顺序号  ii为页面列值(1,2,3)
	function addcarlist(i,ii) {
		if(true) {
			document.getElementById("send_s_time_s").disabled = false;
			document.getElementById("send_e_time_s").disabled = false;
			document.getElementById("vehicle_ln").disabled = false;
			
			document.getElementById("send_condition_1").disabled = false;
			document.getElementById("send_condition_2").disabled = false;
			document.getElementById("send_condition_3").disabled = false;
			document.getElementById("send_condition_span").disabled = false;
			document.getElementById("send_condition_span2").disabled = false;
			document.getElementById("send_time_s").disabled = false;
			document.getElementById("routecarseqordernum").disabled = false;
			
			document.getElementById("send_s_time_s").disabled = false;
			document.getElementById("send_e_time_s").disabled = false;
			
			/* document.getElementById("popinfosurebut").disabled = false;
			document.getElementById("popinfodelbut").disabled = false; */
		}
		if(jQuery("#treetqcid").hasClass('tabfocus')) {
			document.getElementById("send_condition_1").disabled = true;
			document.getElementById("send_condition_span").disabled = true;
			jQuery("input[name=turnonvehiclechico][value=1]").attr("checked",'checked');
		} else {
			document.getElementById("send_condition_span").disabled = false;
			document.getElementById("send_condition_1").disabled = false;
		}
		if(jQuery("#treeupid").hasClass('tabfocus')||jQuery("#treedownid").hasClass('tabfocus')) {
			document.getElementById("send_condition_2").disabled = true;
			document.getElementById("send_condition_span2").disabled = true;
			jQuery("input[name=turnonvehiclechico][value=0]").attr("checked",'checked');
		} else {
			document.getElementById("send_condition_span2").disabled = false;
			document.getElementById("send_condition_2").disabled = false;
		}
		if(jQuery("#siteStuDetail").css("display")=='none') {
			jQuery("#BgDiv").css("display","block");
			jQuery("#BgDiv").width(jQuery(window).width());
			jQuery("#BgDiv").height(jQuery(window).height());
			jQuery("#siteStuDetail").css("display","block");
		} else {
			jQuery("#BgDiv").css("display","none");
			jQuery("#siteStuDetail").css("display","none");
		}
		document.getElementById("vehicle_ln").disabled = false;
		jQuery("#vehicle_ln").unbind();
		jQuery("#vehicle_ln").bind("click",function(){
			choiceCarln();
		});
		jQuery("#driver_name").css("color","black").unbind();
		jQuery("#driver_name").css("color","#0078c8").bind("click",function(){
			searchDriver();
		});
		document.getElementById("driver_name").disabled = false;
		addorderinfoto(i,ii,1);
		jQuery("#route_list_i").val(i);
		jQuery("#route_list_ii").val(ii);
		jQuery("#addupdatetype").val("1");
		jQuery("#addupdatetimetype").val("1");
		writetableinfo();
		/* document.getElementById("popinfosurebut").disabled = false; */
		jQuery("#popinfosurebut").show();
		jQuery("#popinfodelbut").hide();
		jQuery("#detailTile").html("新增临时发车安排");
		document.getElementById("send_s_time_s").disabled = true;
		document.getElementById("send_s_time_s").value = getsettime();
		document.getElementById("send_e_time_s").disabled = false;
		document.getElementById("send_e_time_s").value = getsettime();
		
		document.getElementById("driver_id").value = '';
		/* document.getElementById("route_driver_s").disabled = true; */
	}
	function popinfoaddupdate() {
		//jQuery("#addupdatetype").val() == 1 增加	==2更新
		if(jQuery("#addupdatetype").val() == '1') {
			addcarlistinfo(0);
		} else if(jQuery("#addupdatetype").val() == '2') {
			//判断值有没有修改
			if(checkInputifchange(jQuery("#route_list_i").val(),jQuery("#route_list_ii").val())) {
				updateroutecarexe();
			}else {
				closeViloationDetail();
				return false;
			}
		}
	}
	function popinfodel() {
		jQuery("#BgDiv").css("display","none");
		jQuery("#siteStuDetail").css("display","none");
		delcarlist(jQuery("#route_list_i").val(),jQuery("#route_list_ii").val());
		//searchCarRunList();
	}
	var addcar_route_id_=null;
	//runover	判断0添加1修改
	function addcarlistinfo(runover) {
		var route_car_length="1";
		if(jQuery("#vehicle_vin").val()=='') {
			alert("请选择车辆！");
			return false;
		} else if(jQuery("input[name=turnonvehiclechico]:checked").val()==null) {
			alert("请选择发车条件！");
			return false;
		}
		if(jQuery("input[name=turnonvehiclechico]:checked").val()==2&&jQuery("#send_time_s").val()=="") {
			alert("请选择发车时间");
			return false;
		}
	
		if(jQuery("#send_s_time_s").val()==''||jQuery("#send_e_time_s").val()=='') {
			alert("请填写有效期限！");
			return false;
		}
		if(runover == 0) {
			if(jQuery("input[name=turnonvehiclechico]:checked").val() == '1') {
				//如果是循环发车校验发车是否存在
				if(sendcar_return(jQuery("#vehicle_vin").val(),jQuery("#route_list_ii").val())) {
					alert("循环发车车辆不可重复添加！");
					return false;
				}
			}
		}
		if(jQuery("#route_list_ii").val()==1) {
			addcar_route_id_ = jQuery("#routeid1").val();
			route_car_length = showcarchangeinfo;
		}else if(jQuery("#route_list_ii").val()==2) {
			addcar_route_id_ = jQuery("#routeid2").val();
			route_car_length = showcarchangeinfo2;
		}else if(jQuery("#route_list_ii").val()==3) {
			addcar_route_id_ = jQuery("#routeid3").val();
			route_car_length = showcarchangeinfo3;
		}
		
		var data = {
			"routeChart.route_id":addcar_route_id_,
			"routeChart.VIN":jQuery("#vehicle_vin").val(),
			"routeChart.send_condition":jQuery("input[name=turnonvehiclechico]:checked").val(),
			"routeChart.send_time":jQuery("#send_time_s").val(),
			"routeChart.send_order":jQuery("#routecarseqordernum").val(),
			"routeChart.exe_date":jQuery("#queryTime").val(),
			"routeChart.start_time":jQuery("#send_s_time_s").val(),
			"routeChart.end_time":jQuery("#send_e_time_s").val(),
			"routeChart.add_flag":jQuery("#addupdatetimetype").val(),
			"routeChart.driver_id":jQuery("#driver_id").val()
		};
		jQuery.post("../dispatchroute_chart/route_add_car.shtml",data,function(v){
			if(v.indexOf("su")>=0) {
		   		closeViloationDetail();
		   		
		   		//全部刷新改为当前修改线路刷新
				if(jQuery("#route_list_ii").val()=='1')
					DispatchrouteChartDWR.getoneChart(jQuery("#routeid1").val(),treeType,jQuery("#queryTime").val(), pagenumlength, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
				if(jQuery("#route_list_ii").val()=='2')
					DispatchrouteChartDWR.getoneChart(jQuery("#routeid2").val(),treeType,jQuery("#queryTime").val(), pagenumlength, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo2);
				if(jQuery("#route_list_ii").val()=='3')
					DispatchrouteChartDWR.getoneChart(jQuery("#routeid3").val(),treeType,jQuery("#queryTime").val(), pagenumlength, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo3);
				//searchCarRunList("1");
			} else {
				alert(v);
				//全部刷新改为当前修改线路刷新
				if(jQuery("#route_list_ii").val()=='1')
					DispatchrouteChartDWR.getoneChart(jQuery("#routeid1").val(),treeType,jQuery("#queryTime").val(), pagenumlength, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
				if(jQuery("#route_list_ii").val()=='2')
					DispatchrouteChartDWR.getoneChart(jQuery("#routeid2").val(),treeType,jQuery("#queryTime").val(), pagenumlength, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo2);
				if(jQuery("#route_list_ii").val()=='3')
					DispatchrouteChartDWR.getoneChart(jQuery("#routeid3").val(),treeType,jQuery("#queryTime").val(), pagenumlength, jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo3);
				//searchCarRunList("1");
			}
		});
		
	}
	function getnowandsearcht(type) {
		if(type == 'now') {
			//var now = new Date();
			//var lktime = new Date(now.getFullYear(), now.getMonth()+1, now.getDate());
			var time = getnowtime();
			var arr = time.split("-");
			var lktime = new Date(arr[0], arr[1]-1, arr[2]);
			return lktime.getTime();
		} else if (type == 'search') {
			var time = jQuery("#queryTime").val();
			var arr = time.split("-");
			var starttime = new Date(arr[0], arr[1]-1, arr[2]);
			return starttime.getTime();
		} else if (type == 'max') {
			//判断不能大于30天
			var now = new Date();
			var maxtime = new Date(now.getFullYear(), now.getMonth(), now.getDate()+30);
			return maxtime.getTime();
		} else if(type == 'endtime') {
			var time = jQuery("#send_e_time_s").val();
			var arr = time.split("-");
			var starttime = new Date(arr[0], arr[1]-1, arr[2]);
			return starttime.getTime();
		}
	}
	var showdetailinfov = 0;
	function updateroutecarinfo(i,ii) {
		//判断运行中司机更换
		var runningdriver = true;
		if(true) {
			document.getElementById("send_s_time_s").disabled = false;
			document.getElementById("send_e_time_s").disabled = false;
			document.getElementById("vehicle_ln").disabled = false;
			
			if(jQuery("#treetqcid").hasClass('tabfocus')) {
				document.getElementById("send_condition_1").disabled = true;
				document.getElementById("send_condition_span").disabled = true;
				jQuery("input[name=turnonvehiclechico][value=1]").attr("checked",'checked');
			} else {
				document.getElementById("send_condition_span").disabled = false;
				document.getElementById("send_condition_1").disabled = false;
			}
			if(jQuery("#treeupid").hasClass('tabfocus')||jQuery("#treedownid").hasClass('tabfocus')) {
				document.getElementById("send_condition_2").disabled = true;
				document.getElementById("send_condition_span2").disabled = true;
				jQuery("input[name=turnonvehiclechico][value=0]").attr("checked",'checked');
			} else {
				document.getElementById("send_condition_span2").disabled = false;
				document.getElementById("send_condition_2").disabled = false;
			}
			document.getElementById("send_condition_3").disabled = false;
			document.getElementById("send_time_s").disabled = false;
			document.getElementById("routecarseqordernum").disabled = false;
			
			document.getElementById("send_s_time_s").disabled = true;
			document.getElementById("send_e_time_s").disabled = false;
			
			/* document.getElementById("popinfosurebut").disabled = false;
			document.getElementById("popinfodelbut").disabled = false; */
		}
		jQuery("#popinfosurebut").show();
		jQuery("#popinfodelbut").show();
		var starttimes = getnowandsearcht("search");
		
		var lktimes = getnowandsearcht("now");
		
		/* document.getElementById("route_driver_s").disabled = false; */
		
		jQuery("#BgDiv").css("display","block");
		jQuery("#BgDiv").width(jQuery(window).width());
		jQuery("#BgDiv").height(jQuery(window).height());
		jQuery("#siteStuDetail").css("display","block");
		if(ii == 1) {
			if(data_list_car[i].vehicle_code == '外租')
				jQuery("#vehicle_ln").val(data_list_car[i].vehicle_ln);
			else
				jQuery("#vehicle_ln").val(data_list_car[i].vehicle_ln+"("+data_list_car[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car[i].send_order);
			jQuery("#addupdatetimetype").val(data_list_car[i].add_flag);
			//jQuery("#vehicle_code").html(data_list_car[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+data_list_car[i].driver_name);
			jQuery("#driver_id").val(data_list_car[i].driver_id);
			jQuery("#send_s_time_s").val(data_list_car[i].start_time);
			jQuery("#send_e_time_s").val(data_list_car[i].end_time);
			//if((data_list_car[i].updriver_id!=null&&data_list_car[i].updriver_id.length>0)||(data_list_car[i].status!=null&&data_list_car[i].status!='0')) {
			if(data_list_car[i].status!=null&&data_list_car[i].status!='0') {//即时车辆有驾驶员上报也允许修改
				runningdriver = false;
				jQuery("#popinfosurebut").hide();
				jQuery("#popinfodelbut").hide();
			}
			if(data_list_car[i].add_flag==0) {
				//长期的话不可用
				document.getElementById("send_e_time_s").disabled = true;
			} else if(data_list_car[i].add_flag==1) {
				//临时的话不可用
				document.getElementById("send_e_time_s").disabled = false;
			}
			showdetailinfov =showcarchangeinfo;
			if(data_list_car[i].valid_flag == '1')
				showdetailinfov = '1';
		} else if (ii == 2) {
			if(data_list_car2[i].vehicle_code == '外租')
				jQuery("#vehicle_ln").val(data_list_car2[i].vehicle_ln);
			else
				jQuery("#vehicle_ln").val(data_list_car2[i].vehicle_ln+"("+data_list_car2[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car2[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car2[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car2[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car2[i].send_order);
			jQuery("#addupdatetimetype").val(data_list_car2[i].add_flag);
			//jQuery("#vehicle_code").html(data_list_car2[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+data_list_car2[i].driver_name);
			jQuery("#driver_id").val(data_list_car2[i].driver_id);
			jQuery("#send_s_time_s").val(data_list_car2[i].start_time);
			jQuery("#send_e_time_s").val(data_list_car2[i].end_time);
			//if((data_list_car2[i].updriver_id!=null&&data_list_car2[i].updriver_id.length>0)||(data_list_car2[i].status!=null&&data_list_car2[i].status!='0')) {
			if(data_list_car2[i].status!=null&&data_list_car2[i].status!='0') {//即时车辆有驾驶员上报也允许修改
				runningdriver = false;
				jQuery("#popinfosurebut").hide();
				jQuery("#popinfodelbut").hide();
			}
			if(data_list_car2[i].add_flag==0) {
				//长期的话不可用
				document.getElementById("send_e_time_s").disabled = true;
			} else if(data_list_car2[i].add_flag==1) {
				//临时的话不可用
				document.getElementById("send_e_time_s").disabled = false;
			}
			
			showdetailinfov =showcarchangeinfo2;
			if(data_list_car2[i].valid_flag == '1')
				showdetailinfov = '1';
		} else if (ii == 3) {
			if(data_list_car3[i].vehicle_code == '外租')
				jQuery("#vehicle_ln").val(data_list_car3[i].vehicle_ln);
			else
				jQuery("#vehicle_ln").val(data_list_car3[i].vehicle_ln+"("+data_list_car3[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car3[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car3[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car3[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car3[i].send_order);
			jQuery("#addupdatetimetype").val(data_list_car3[i].add_flag);
			//jQuery("#vehicle_code").html(data_list_car3[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+data_list_car3[i].driver_name);
			jQuery("#driver_id").val(data_list_car3[i].driver_id);
			jQuery("#send_s_time_s").val(data_list_car3[i].start_time);
			jQuery("#send_e_time_s").val(data_list_car3[i].end_time);
			//if((data_list_car3[i].updriver_id!=null&&data_list_car3[i].updriver_id.length>0)||(data_list_car3[i].status!=null&&data_list_car3[i].status!='0')) {
			if(data_list_car3[i].status!=null&&data_list_car3[i].status!='0') {//即时车辆有驾驶员上报也允许修改
				runningdriver = false;
				jQuery("#popinfosurebut").hide();
				jQuery("#popinfodelbut").hide();
			}
			if(data_list_car3[i].add_flag==0) {
				//长期的话不可用
				document.getElementById("send_e_time_s").disabled = true;
			} else if(data_list_car3[i].add_flag==1) {
				//临时的话不可用
				document.getElementById("send_e_time_s").disabled = false;
			}
			
			showdetailinfov =showcarchangeinfo3;
			if(data_list_car3[i].valid_flag == '1')
				showdetailinfov = '1';
		}
		document.getElementById("vehicle_ln").disabled = true;
		jQuery("#vehicle_ln").unbind();
		jQuery("#driver_name").css("color","black").unbind();
		document.getElementById("driver_name").disabled = true;
		addorderinfoto(i,ii,2);
		jQuery("#route_list_i").val(i);
		jQuery("#route_list_ii").val(ii);
		jQuery("#addupdatetype").val("2");
		
		if(starttimes>=lktimes) {
		} else {
			jQuery("#popinfosurebut").hide();
			jQuery("#popinfodelbut").hide();
		}
		if(showdetailinfov=='1') {
			document.getElementById("send_s_time_s").disabled = true;
			document.getElementById("send_e_time_s").disabled = true;
			document.getElementById("vehicle_ln").disabled = true;
			
			document.getElementById("send_condition_1").disabled = true;
			document.getElementById("send_condition_2").disabled = true;
			document.getElementById("send_condition_3").disabled = true;
			document.getElementById("send_condition_span").disabled = true;
			document.getElementById("send_condition_span2").disabled = true;
			document.getElementById("send_time_s").disabled = true;
			document.getElementById("routecarseqordernum").disabled = true;
			
			document.getElementById("send_s_time_s").disabled = true;
			document.getElementById("send_e_time_s").disabled = true;
			
			/* document.getElementById("popinfosurebut").disabled = true;
			document.getElementById("popinfodelbut").disabled = true; */
			jQuery("#popinfosurebut").hide();
			jQuery("#popinfodelbut").hide();
			/* document.getElementById("route_driver_s").disabled = true; */
			jQuery("#detailTile").html("查看发车安排");
		} else {
			if(starttimes>=lktimes&&runningdriver) {
				document.getElementById("driver_name").disabled = false;
				jQuery("#driver_name").css("color","#0078c8").bind("click",function(){
					searchDriver();
				});
			}
			jQuery("#detailTile").html("修改发车安排");
		}
	}
	function updateroutecarexe() {
		var max = getnowandsearcht("max");
		var end = getnowandsearcht("end");
		if (max<end) {
			alert("结束时间过大！");
			return false;
		}
		delcarlistexe(jQuery("#route_list_i").val(),jQuery("#route_list_ii").val());
		//addcarlistinfo();
	}
	function cutstring(str) {
		return str.length>8?str.substring(0,8):str;
	}
/* 	function add_flag_check(v) {
		return v==0?"":"(临)";
	} */
	function isonlinecolor(v,type) {
	var back = v=="offline"?"tableroute_car_info_offline":"tableroute_car_info_online";
		if(type=='1') 
			return "tableroute_car_info_online";
		else {
			return back;
		}
	}
	function changesendtype_show(v) {
		return v=='1'?"display:none;":"";
	}
	function changenosend(s) {
		//0失败 1成功
		return s==null?"0":s=="-1"?"0":s;
	}
	function sendtypechoice(info) {
		var tname = "";
		if(info.send_condition==2) {
			tname= info.send_time;
		} else if(info.send_condition==0) {
			tname= "坐满发车";
		} else if(info.send_condition==1) {
			tname= "循环发车";
		}
		tname += info.add_flag==0?"":"(临)";
		return tname;
	}
	function getcarlinemove(v,status,type) {
		//先判断是否是已执行 如果不是	判断是否是执行中
		if(v!=null&&v==1) {
			return "取消发车";
		} else {
			if(status!=null) {
				if(status=='1') {
					return "执行中";
				}
				if(status=='2') {
					return "已执行";
				} else {
					return "未执行";
				}
			}
		}
		/* if(type!=null&&type=='1')
			return "取消发车"; */
		return "";
	}
	function sendifsuccess(crc,info,vehitype) {
		if(vehitype!=null&&vehitype=='1') {
			return "";
		}
		if(crc==null||crc.length<1) {
			return "";
		} else {
			if(info==null||info=='0') {
				return "<img title='尚未下发成功！' style='height:15px;vertical-align: middle;' src='../images/pageslipe/redflag.png'/>";
			}else
				return "";
		}
	}
	//crc valid_flag
	function sendifsuccessbgsrc(crc,info,type) {
		if(type!=null&&type=='1') {
			return "<div class='tableroute_car_info'>";
		}
		if(crc==null||crc.length<1) {
			return "<div class='tableroute_car_info_nosend'>";
		} else {
			if(info!=null&&info=='1')
				return "<div class='tableroute_car_info_nosend'>";
			else
				return "<div class='tableroute_car_info'>";
		}
	}
	function sendvehicle_codenull(type,v) {
		if(type==null||type.length<1)
			return "未知";
		else {
			if(type=='1') 
				return "外租";
			else
				return v;
		}
	}
	function checklimitnum(num) {
		if(num == null)
			return '0';
		else
			return num;
	}
	var reloadhtml = "";
	var commondx_send = "";
	function reloadlistcar(data_list,va) {
		var starttimes = getnowandsearcht("search");
			
		var lktimes = getnowandsearcht("now");
		
		var tongqin = null;
		if(va==1) {
			tongqin = jQuery("#tonqincar");
		}else if(va==2){
			tongqin = jQuery("#tonqincar2");
		}else if(va==3){
			tongqin = jQuery("#tonqincar3");
		}
		
		tongqin.empty();
		
		reloadhtml = "";
		reloadhtml += "<ul>";
		reloadhtml += "<li style='position:relative;width:60px;float:left;margin-left:10px;'>";
		reloadhtml +="<div style='height:35px;line-height:35px;'><b>发车安排:</b></div>";
		reloadhtml +="</li>";
		
		if(va==1) {
			var changes = jQuery('<div></div>').attr('id', "tonqincar_change").addClass("tonqincar_list_change").appendTo(tongqin);
			if(starttimes>=lktimes) {
				//if(starttimes==lktimes) {
					/* <s:if test="#session.perUrlList.contains('111_3_3_1_3')"> */
					commondx_send = '<div style="padding-left: 20px;padding-top: 10px;"><a href="javascript:void(0);" style="'+changesendtype_show(showcarchangeinfo)+'" id="showaddbutton_check_1" class="scommondbutton" onclick="sendcommondbt(1)">下&nbsp;发</a></div>';
					jQuery(commondx_send).appendTo(changes);
					/* </s:if> */
				//}
				/* <s:if test="#session.perUrlList.contains('111_3_3_1_2')"> */
				
				var divshow = "<div style='padding-left: 20px;padding-top: 10px;'><a id='buttonchangecar1' class='scommondbutton' href='javascript:void(0);' onclick='showchangebutton(this,1);' >"+changetypen(showcarchangeinfo)+"</a></div>";
				jQuery(divshow).appendTo(changes);
				/* </s:if> */
				
				reloadhtml += "<li style='position:relative;width:26px;'>";
				if(showcarchangeinfo == '1')
					reloadhtml +="<a class='changecarinfobutton' style='display:none;' href='javascript:void(0);' onclick='addcarlist(-1,1)'></a></li>";
				else
					reloadhtml +="<a class='changecarinfobutton' href='javascript:void(0);' onclick='addcarlist(-1,1)' ></a></li>";
			}
		}else if(va==2) {
			var changes = jQuery('<div></div>').attr('id', "tonqincar_change2").addClass("tonqincar_list_change").appendTo(tongqin);
			if(starttimes>=lktimes) {
				//if(starttimes==lktimes) {
					/* <s:if test="#session.perUrlList.contains('111_3_3_1_3')"> */
					commondx_send = '<div style="padding-left: 20px;padding-top: 10px;"><a href="javascript:void(0);" style="'+changesendtype_show(showcarchangeinfo2)+'" id="showaddbutton_check_2" class="scommondbutton" onclick="sendcommondbt(2)">下&nbsp;发</a></div>';
					jQuery(commondx_send).appendTo(changes);
					/* </s:if> */
				//}
				/* <s:if test="#session.perUrlList.contains('111_3_3_1_2')"> */
				var divshow = "<div style='padding-left: 20px;padding-top: 10px;'><a id='buttonchangecar2' class='scommondbutton' href='javascript:void(0);' onclick='showchangebutton(this,2);' >"+changetypen(showcarchangeinfo2)+"</a></div>";
				jQuery(divshow).appendTo(changes);
				/* </s:if> */
				
				reloadhtml += "<li style='position:relative;width:26px;'>";
				if(showcarchangeinfo2 == '1')
					reloadhtml +="<a class='changecarinfobutton2' style='display:none;' href='javascript:void(0);' onclick='addcarlist(-1,2)'></a></li>";
				else
					reloadhtml +="<a class='changecarinfobutton2' href='javascript:void(0);' onclick='addcarlist(-1,2)' ></a></li>";
			}
		}else if(va==3) {
			var changes = jQuery('<div></div>').attr('id', "tonqincar_change3").addClass("tonqincar_list_change").appendTo(tongqin);
			if(starttimes>=lktimes) {
				//if(starttimes==lktimes) {
					/* <s:if test="#session.perUrlList.contains('111_3_3_1_3')"> */
					commondx_send = '<div style="padding-left: 20px;padding-top: 10px;"><a href="javascript:void(0);" style="'+changesendtype_show(showcarchangeinfo3)+'" id="showaddbutton_check_3" class="scommondbutton" onclick="sendcommondbt(3)">下&nbsp;发</a></div>';
					jQuery(commondx_send).appendTo(changes);
					/* </s:if> */
				//}
				/* <s:if test="#session.perUrlList.contains('111_3_3_1_2')"> */
				var divshow = "<div style='padding-left: 20px;padding-top: 10px;'><a id='buttonchangecar3' class='scommondbutton' href='javascript:void(0);' onclick='showchangebutton(this,3);' >"+changetypen(showcarchangeinfo3)+"</a></div>";
				jQuery(divshow).appendTo(changes);
				/* </s:if> */
				
				reloadhtml += "<li style='position:relative;width:26px;float:left;'>";
				if(showcarchangeinfo3 == '1')
					reloadhtml +="<a class='changecarinfobutton3' style='display:none;' href='javascript:void(0);' onclick='addcarlist(-1,3)'></a></li>";
				else
					reloadhtml +="<a class='changecarinfobutton3' href='javascript:void(0);' onclick='addcarlist(-1,3)' ></a></li>";
			}
		}
		
		var station = jQuery('<div></div>').attr('id', "tonqincar_list").addClass("tonqincar_list").appendTo(tongqin);
		var wid = null;
		if(data_list.length>5)
			wid = data_list.length * 175+70;
		else
			wid = "auto";
		
		var div = jQuery('<div></div>').attr('id', "tonqincar_list_cl"+va).addClass("car_list_style").css("width",wid).appendTo(station);//
		
		if (data_list && data_list.length > 0) {
			for(var v=0;v<data_list.length;v++) {
				var info = data_list[v];
				reloadhtml +="<li style='position:relative;width:170px;'>";
				reloadhtml +=sendifsuccessbgsrc(info.crc,info.valid_flag,info.vehicle_type);
				/* if(changenosend(info.mesg_flag) == '0') {
					reloadhtml += "<div id='"+info.VIN+va+"dpops' class='tableroute_car_info_red'>"+info.vehicle_code+"</div>";
				} else {
					reloadhtml += "<div id='"+info.VIN+va+"dpops' class='tableroute_car_info_grey'>"+info.vehicle_code+"</div>";
				} */
				reloadhtml +="<div id='car"+info.VIN+"' class='tableroute_car_info_s'>";
				reloadhtml +="<span valign='middle'><a href='javascript:void(0);' class='"+isonlinecolor(info.car_state,info.vehicle_type)+"' >"+sendvehicle_codenull(info.vehicle_type,info.vehicle_code)+"</a>";
				if(starttimes==lktimes) {
					reloadhtml +="<a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;' onclick='onevmouse_click(this,\""+info.VIN+"\",\""+info.vehicle_type+"\")'>"+cutstring(info.vehicle_ln)+"("+checklimitnum(info.limite_number)+"座)";
				} else {
					reloadhtml +="<a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;'>"+cutstring(info.vehicle_ln)+"("+info.limite_number+"座)";
				}
				reloadhtml +="</a></span>";
				
				reloadhtml +="<span valign='middle'><a href='javascript:void(0);' class='tableroute_car_info_go_l'>"+getcarlinemove(info.valid_flag,info.status,info.vehicle_type)+"</a><a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;' onclick='updateroutecarinfo("+v+","+va+")'>"+sendtypechoice(info);
				
				if(starttimes==lktimes) {
					reloadhtml +=sendifsuccess(info.crc,info.mesg_flag,info.vehicle_type)+"</a></span>";
				} else {
					reloadhtml +="</a></span>";
				}
				/* if(info.send_condition == 2) {
					reloadhtml +="<a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;' onclick='updateroutecarinfo("+v+","+va+")'>"+info.send_time+add_flag_check(info.add_flag)+"</a>";
				}else if(info.send_condition == 0) {
					reloadhtml +="<a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;' onclick='updateroutecarinfo("+v+","+va+")'>"+"坐满发车"+add_flag_check(info.add_flag)+"</a>";
				}else if(info.send_condition == 1) {
					reloadhtml +="<a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;' onclick='updateroutecarinfo("+v+","+va+")'>"+"循环发车"+add_flag_check(info.add_flag)+"</a>";
				} */
					
				reloadhtml +="</div></div></li><li style='position:relative;width:26px;'>";//</td><td style='width:26px;'>";
				if(starttimes>=lktimes) {
					if(va==1) {
						if(showcarchangeinfo == '1')
							reloadhtml +="<a class='changecarinfobutton' style='display:none;' href='javascript:void(0);' onclick='addcarlist("+v+","+va+")'></a>";
						else
							reloadhtml +="<a class='changecarinfobutton' href='javascript:void(0);' onclick='addcarlist("+v+","+va+")'></a>";
					} else if(va==2) {
						if(showcarchangeinfo2 == '1')
							reloadhtml +="<a class='changecarinfobutton2' style='display:none;' href='javascript:void(0);' onclick='addcarlist("+v+","+va+")'></a>";
						else
							reloadhtml +="<a class='changecarinfobutton2' href='javascript:void(0);' onclick='addcarlist("+v+","+va+")'></a>";
					} else if(va==3) {
						if(showcarchangeinfo3 == '1')
							reloadhtml +="<a class='changecarinfobutton3' style='display:none;' href='javascript:void(0);' onclick='addcarlist("+v+","+va+")'></a>";
						else
							reloadhtml +="<a class='changecarinfobutton3' href='javascript:void(0);' onclick='addcarlist("+v+","+va+")'></a>";
					}
				}
				reloadhtml +="</li>";
			}
		} else {
			if(va==1) {
				if(showcarchangeinfo == '1') {
					reloadhtml +="<li style='position:relative;width:170px;'><div style='line-height: 35px; height: 35px;'><font size='2'>暂无发车安排！</font></div>";
					reloadhtml +="</li>";
				}
			} else if(va==2) {
				if(showcarchangeinfo2 == '1') {
					reloadhtml +="<li style='position:relative;width:170px;'><div style='line-height: 35px; height: 35px;'><font size='2'>暂无发车安排！</font></div>";
					reloadhtml +="</li>";
				}
			} else if(va==3) {
				if(showcarchangeinfo3 == '1') {
					reloadhtml +="<li style='position:relative;width:170px;'><div style='line-height: 35px; height: 35px;'><font size='2'>暂无发车安排！</font></div>";
					reloadhtml +="</li>";
				}
			} 
		}
		jQuery(reloadhtml+"</ul>").appendTo(div);
	}
	function clear_rm(v) {
		if(v==1) {
			jQuery('#rm_route').mk_routemonitor('clear');
			jQuery('#rm_route .mk-rm-route-til').empty();
			jQuery('#tonqincar #tonqincar_change').empty();
			jQuery("#tonqincar #tonqincar_list").empty();
		}else if(v==2) {
			jQuery('#rm_route2').mk_routemonitor('clear');
			jQuery('#rm_route2 .mk-rm-route-til').empty();
			jQuery('#tonqincar2 #tonqincar_change2').empty();
			jQuery("#tonqincar2 #tonqincar_list").empty();
		}else if(v==3) {
			jQuery('#rm_route3').mk_routemonitor('clear');
			jQuery('#rm_route3 .mk-rm-route-til').empty();
			jQuery('#tonqincar3 #tonqincar_change3').empty();
			jQuery("#tonqincar3 #tonqincar_list").empty();
		}
	}

	/* 实体对象定义*/
	function mk_routemonitor(routeid, up_site_arr, down_site_arr, up_car_arr,
			down_car_arr) {
		this.line_id = routeid;
		this.up_data = up_site_arr;
		this.down_data = down_site_arr;
		this.up_cars = up_car_arr;
		this.down_cars = down_car_arr;
	}

	function mk_routemonitor_site(site_id, site_name, site_status, up_num,
			down_num) {
		this.id = site_id;
		this.name = site_name;
		this.status = site_status;
		this.up = up_num;
		this.down = down_num;
	}

	function mk_routemonitor_car(vin, ln, site_id, inout_flag) {
		this.vin = vin;
		this.site_id = site_id;
		this.vehicle_ln = ln;
		this.inout_flag = inout_flag;
	}
	jQuery(function() {
		jQuery('#rm_route').mk_routemonitor({
				line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
				/* 'station_callback' : function() {
					sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
					//alert('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
					// console.log('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
					//onevmouse_click("YZB12345678912346");
				}, */
				'car_callback' : function() {
					//alert(jQuery(this).attr('data-vin'));
					//alert('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
					// console.log('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
					//onevmouse_click(jQuery(this).attr('data-vin'));
				},
				"route_up_id" : 'mk_routemonitor_route_up',
				"up_station_id" : 'mk_up_station',
				"up_text":"",
				"tonqincar":"tonqincar",
				"add_cars":true,
				"car_color":false
		});
		jQuery('#rm_route2').mk_routemonitor({
			line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
			/* 'station_callback' : function() {
				sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
				//alert('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
				// console.log('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
				//onevmouse_click("YZB12345678912346");
			}, */
			'car_callback' : function() {
				//alert(jQuery(this).attr('data-vin'));
				//alert('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
				// console.log('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
				//onevmouse_click(jQuery(this).attr('data-vin'));
			},
			"up_text":"",
			"route_up_id" : 'mk_routemonitor_route_up2',
			"up_station_id" : 'mk_up_station2',
			"route_plan_class" : 'mk-rm-route-plan',
			"route_class" : 'mk-rm-route',
			"route_title_class" : 'mk-rm-route-til',
			"route_station_class" : 'mk-rm-route-station',
			"tonqincar":"tonqincar2",
			"add_cars":true,
			"car_color":false
		});
		jQuery('#rm_route3').mk_routemonitor({
			line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
			/* 'station_callback' : function() {
				sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
				//alert('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
				// console.log('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
				//onevmouse_click("YZB12345678912346");
			}, */
			'car_callback' : function() {
				//alert(jQuery(this).attr('data-vin'));
				//alert('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
				// console.log('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
				//onevmouse_click(jQuery(this).attr('data-vin'));
			},
			"up_text":"",
			"route_up_id" : 'mk_routemonitor_route_up3',
			"up_station_id" : 'mk_up_station3',
			"route_plan_class" : 'mk-rm-route-plan',
			"route_class" : 'mk-rm-route',
			"route_title_class" : 'mk-rm-route-til',
			"route_station_class" : 'mk-rm-route-station',
			"tonqincar":"tonqincar3",
			"add_cars":true,
			"car_color":false
		});
		
		
	});
	function sendcar_return(vin,ii) {
		if(ii == '1') {
			for(var v=0;v<data_list_car.length;v++) {
				if(data_list_car[v].VIN == vin&&data_list_car[v].send_condition == '1') 
					return true;
			}
		} else if(ii == '2') {
			for(var v=0;v<data_list_car2.length;v++) {
				if(data_list_car2[v].VIN == vin&&data_list_car2[v].send_condition == '1') 
					return true;
			}
		} else if(ii == '3') {
			for(var v=0;v<data_list_car3.length;v++) {
				if(data_list_car3[v].VIN == vin&&data_list_car3[v].send_condition == '1') 
					return true;
			}
		}
		return false;
	}
	function checkInputifchange(i,ii) {
		if(ii == '1') {
			if(data_list_car[i].VIN != jQuery("#vehicle_vin").val()||data_list_car[i].send_condition !=jQuery("input[name=turnonvehiclechico]:checked").val()) 
				return true;
			if(data_list_car[i].send_order != jQuery("#routecarseqordernum").val()) 
				return true;
			if(data_list_car[i].driver_id != jQuery("#driver_id").val()) 
				return true;
			if(data_list_car[i].end_time != jQuery("#send_e_time_s").val()) 
				return true;
			if(data_list_car[i].send_condition == '2') {
				if(data_list_car[i].send_time != jQuery("#send_time_s").val())
					return true;
			}
		} else if(ii == '2') {
			if(data_list_car2[i].VIN != jQuery("#vehicle_vin").val()||data_list_car2[i].send_condition !=jQuery("input[name=turnonvehiclechico]:checked").val()) 
				return true;
			if(data_list_car2[i].send_order != jQuery("#routecarseqordernum").val()) 
				return true;
			if(data_list_car2[i].driver_id != jQuery("#driver_id").val()) 
				return true;
			if(data_list_car2[i].end_time != jQuery("#send_e_time_s").val()) 
				return true;
			if(data_list_car2[i].send_condition == '2') {
				if(data_list_car2[i].send_time != jQuery("#send_time_s").val())
					return true;
			}
		} else if(ii == '3') {
			if(data_list_car3[i].VIN != jQuery("#vehicle_vin").val()||data_list_car3[i].send_condition !=jQuery("input[name=turnonvehiclechico]:checked").val()) 
				return true;
			if(data_list_car3[i].send_order != jQuery("#routecarseqordernum").val()) 
				return true;
			if(data_list_car3[i].driver_id != jQuery("#driver_id").val()) 
				return true;
			if(data_list_car3[i].end_time != jQuery("#send_e_time_s").val()) 
				return true;
			if(data_list_car3[i].send_condition == '2') {
				if(data_list_car3[i].send_time != jQuery("#send_time_s").val())
					return true;
			}
		}
		return false;
	}
</script>